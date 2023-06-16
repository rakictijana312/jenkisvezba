package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import org.junit.Assert;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    /*
     * NOTE:
     *
     * Given - Preparing the request
     *
     * When - making the request/making the call/hitting the endpoint
     *
     * Then - verification/assertions
     */
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzAwMTk1MDEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYzMDA2MjcwMSwidXNlcklkIjoiMzAxMCJ9.YW-eL7ggBznJLA_CI5kRCNq_XOWorohbLxjmWlVkU5A";
    static String employee_id;

    //@Test
    public void sampleTest() {
        //Given
        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json")
                .queryParam("employee_id", "24246A");

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        /*
         *Printing response using asString() method to convert JSON object to a string and printing using sysout.
         */

        System.out.println(response.asString());

    }

    @Test
    public void aPostCreateEmployee() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\n" +
                "  \"emp_firstname\": \"Whooptie\",\n" +
                "  \"emp_lastname\": \"Movie\",\n" +
                "  \"emp_middle_name\": \"blueCheese\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2021-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}").log().all();
        /*
         * log().all() will log and print all information being sent with the request
         */

        //when
        Response response = preparedRequest.when().post("/createEmployee.php");
        /*
         *response.prettyPrint() is the same as System.out.println(response.asString());
         */
        response.prettyPrint();

        /*
         * jsonPath() allows us to retrieve specific data from a jason object - just like an xpath with selenium
         */
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
        /*
         * Performing Assertions
         */
        //Then
        response.then().assertThat().statusCode(201);

        /*
         * Using Hamcrest Matchers class equalTo()
         * manually imported the class with-> import static org.hamcrest.Matchers.*;
         */
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        /*
        TASK
         *Write an assertion that verifies that the response body has the name you used
         */
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Whooptie"));
        /*
        TASK
         *Write an assertion that verifies the response server
         */
        response.then().assertThat().header("Server", equalTo("Apache/2.4.39 (Win64) PHP/7.2.18"));


    }

    @Test
    public void bGetCreatedEmployee() {

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");

        response.prettyPrint();

        String empID = response.jsonPath().getString("employee.employee_id");

        boolean comparingEmpIDs = empID.contentEquals(employee_id);

        Assert.assertTrue(comparingEmpIDs);

        //Assert.assertTrue(empID.contentEquals(employee_id));

      /*
      TASK
       *retrieve the first name and assert that the first name is the same as the one you used
       *Do not use HAMCREST MATCHERS
       */

        //Assert.assertEquals(response.jsonPath().getString("employee.emp_firstname"),"Whooptie");
        Assert.assertTrue(response.jsonPath().getString("employee.emp_firstname").contentEquals("Whooptie"));
        //same code below just stored in a string
        /*
         *String firstName= response.jsonPath().getString("employee.emp_firstname");
         *Assert.assertTrue(firstName.contentEquals("Whooptie"));
         */

    }

    //@Test
    public void cGetAllEmployees() {
        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json");
        Response response = preparedRequest.when().get("/getAllEmployees.php");
        //int count = response.jsonPath().getInt("Employees.size()");
        //same code below just created the json object instead of using the method like above
        String allEmployees = response.prettyPrint();
                /*
        Creating and object of JsonPath class
         */
        JsonPath js = new JsonPath(allEmployees);
        /*
        Retrieving number of employees in response body
         */
        int count = js.getInt("Employees.size()");
        //Print the count of all employee IDs from the response
        System.out.println(count);

        for (int i = 0; i < count; i++) {
            String allEmployeeIDs = js.getString("Employees["+i+"].employee_id");
            //String allEmployeeIDs=response.jsonPath().getString("Employees["+i+"].employee_id"); --> worked individually but not with the line in if statement
            //System.out.println(allEmployeeIDs);
            /*
            Verify stored employee ID from previous call is in response body and getting the first name for that employee
             */
            if(allEmployeeIDs.contentEquals(employee_id)){
                System.out.println("Employee ID "+ employee_id+" is present in response body");
                String firstName= js.getString("Employees["+i+"].emp_firstname");
                //String firstName=response.jsonPath().getString("employees["+i+"].emp_firstname"); --> worked individually but not with the other line that worked individually
                System.out.println("Employee name is "+ firstName);
                break;
            }
        }
    }
    @Test
    public void dPutUpdateCreatedEmployee(){
        /*
        * Update the created employee
        */

        RequestSpecification preparedRequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\n" +
                "  \"employee_id\": \""+employee_id+"\",\n" +
                "  \"emp_firstname\": \"Ruth\",\n" +
                "  \"emp_lastname\": \"Chris\",\n" +
                "  \"emp_middle_name\": \"Addicted\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2021-07-10\",\n" +
                "  \"emp_status\": \"Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Consultant\"\n" +
                "}").log().all();
        Response response = preparedRequest.when().put("/UpdateEmployee.php");
        String updatedEmployee= response.prettyPrint();
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Ruth"));
        //Assert.assertTrue(response.jsonPath().getString("Employee.emp_firstname").contentEquals("Ruth"));
        response.then().assertThat().statusCode(200);


    }

}
