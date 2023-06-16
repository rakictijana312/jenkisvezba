package APISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import utils.APICommonMethods;
import utils.apiConstants;
import utils.apiPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class APIWorkflowSteps {
    RequestSpecification request;
    Response response;
    static String employeeID;

    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        APICommonMethods.createEmployeeRequest(apiPayloadConstants.createEmployeeBody());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {

        response = request.when().post(apiConstants.CREATE_EMPLOYEE_URI);
        response.prettyPrint();
    }
    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String Message, String employeeCreated) {
        response.then().assertThat().body(Message, equalTo(employeeCreated));
    }
    @Then("the employeeID {string} is stored as a global variable to be used for other calls")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls(String empID) {

        employeeID = response.jsonPath().getString(empID);
        System.out.println(employeeID);

    }

    //------------------------- SECOND SCENARIO---------------------------------------------------------------------

    @Given("a request is prepared to retrieve the created employee")
    public void a_request_is_prepared_to_retrieve_the_created_employee() {
        request = given().header(apiConstants.Header_Content_type, apiConstants.Content_type).header(apiConstants.Header_Authorization, GenerateTokenSteps.token)
                .queryParam("employee_id",employeeID);
    }

    @When("a GET call is made to retrieve the created employee")
    public void a_get_call_is_made_to_retrieve_the_created_employee() {
        response = request.when().get(apiConstants.GET_ONE_EMPLOYEE_URI);
    }
    @Then("the status code for retrieving the created employee is {int}")
    public void the_status_code_for_retrieving_the_created_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("the retrieved employee ID {string} matches the globally stored employee ID")
    public void the_retrieved_employee_id_matches_the_globally_stored_employee_id(String employeeIDfromResponse) {
        String tempID = response.jsonPath().getString(employeeIDfromResponse);
        Assert.assertEquals(employeeID, tempID);
    }
    @Then("the retrieved data at {string} matches the data used to create an employee with employee ID {string}")
    public void the_retrieved_data_at_matches_the_data_used_to_create_an_employee_with_employee_id(String employeeObject, String responseEmployeeID,
                                                                                                   DataTable dataTable) {
        List<Map<String,String>> expectedData= dataTable.asMaps(String.class,String.class);

        Map<String,String> actualData = response.body().jsonPath().get(employeeObject);

        int index=0;
        for (Map<String,String> map: expectedData) {

            Set<String> keys= map.keySet();

            for (String key: keys) {
                String expectedValue= map.get(key);
                String actualValue = actualData.get(key);

                Assert.assertEquals(expectedValue, actualValue);

            }
            index ++;
        }
        String empID = response.body().jsonPath().getString(responseEmployeeID);
        Assert.assertEquals(empID, employeeID);
    }

    @Given("a request is prepared to create an employee with dynamic data {string}, {string}, {string}, {string}, {string} , {string}, {string}")
    public void a_request_is_prepared_to_create_an_employee_with_dynamic_data(String firstName, String lastName, String middleName, String gender,
                                                                              String employeeBday, String employeeStatus, String employeeJobTitle) {

        request= given().header(apiConstants.Header_Content_type, apiConstants.Content_type)
                .header(apiConstants.Header_Authorization, GenerateTokenSteps.token)
                .body(apiPayloadConstants.createEmployeeBodyMoreDynamic(firstName,lastName,middleName,gender,employeeBday,
                        employeeStatus,employeeJobTitle));
    }
}
