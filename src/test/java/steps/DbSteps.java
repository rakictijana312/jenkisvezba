package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.junit.Assert;
import pages.AddEmployeePage;
import utils.CommonMethods;
import utils.DbUtils;
import utils.GlobalVariables;

import java.util.List;
import java.util.Map;

public class DbSteps extends CommonMethods {

    @When("capture the employeeId")
    public void capture_the_employee_id() {
        AddEmployeePage addEmployeePage=new AddEmployeePage();
        GlobalVariables.empId=addEmployeePage.employeeId.getAttribute("value");

    }

    @Then("query the HRMS database")
    public void query_the_hrms_database() {
        String query="select emp_firstname, emp_middle_name,emp_lastname " +
                "from hs_hr_employees where employee_id="+ GlobalVariables.empId;
        GlobalVariables.tableData= DbUtils.getTableDataAsList(query);
        GlobalVariables.dbFirstName=GlobalVariables.tableData.get(0).get("emp_firstname");
        GlobalVariables.dbMiddleName=GlobalVariables.tableData.get(0).get("emp_middle_name");
        GlobalVariables.dbLastName=GlobalVariables.tableData.get(0).get("emp_lastname");

    }



    @Then("verify the data from frontend and backend")
    public void verify_the_data_from_frontend_and_backend() {
        System.out.println("Backend ");
        System.out.println("DBFirstName "+GlobalVariables.dbFirstName);
        System.out.println("DBMiddleName "+GlobalVariables.dbMiddleName);
        System.out.println("DBLastName "+GlobalVariables.dbLastName);
        System.out.println("FrontEnd ");
        System.out.println("Firstname "+GlobalVariables.firstName);
        System.out.println("MiddleName "+GlobalVariables.middleName);
        System.out.println("Lastname "+GlobalVariables.lastName);
        Assert.assertEquals(GlobalVariables.firstName,GlobalVariables.dbFirstName);
        Assert.assertEquals(GlobalVariables.middleName,GlobalVariables.dbMiddleName);
        Assert.assertEquals(GlobalVariables.lastName,GlobalVariables.dbLastName);

    }

}
