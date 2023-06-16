package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.AdminPage;
import pages.DashBoardPage;
import utils.CommonMethods;
import utils.DbUtils;
import utils.GlobalVariables;

import java.util.List;
import java.util.Map;

public class JobTitleSteps extends CommonMethods {
    @Given("user navigates to admin page")
    public void user_navigates_to_admin_page() {
        DashBoardPage dash= new DashBoardPage();
        click(dash.adminButton);
    }
    @Given("navigates to job title and clicks add button")
    public void navigates_to_job_title_and_clicks_add_button() {
        AdminPage adpage= new AdminPage();
        hover(adpage.jobButton);
        click(adpage.jobTitleTab);
        click(adpage.addJobTitleButton);
    }
    @And("user adds Job Title {string} and saves")
    public void user_adds_job_title_and_saves(String jobTitle) {
        AdminPage adminPage= new AdminPage();
        GlobalVariables.jobTitle=jobTitle;
        sendText(adminPage.jobTitleTextBox, jobTitle);
        click(adminPage.jobTitleSaveButton);
    }

    @Then("query the HRMS database for JobTitle")
    public void query_the_hrms_database_for_job_title() {
      String query1 ="select * from ohrm_job_title where job_title='"+GlobalVariables.jobTitle+"'";
        List<Map<String,String>> tableData= DbUtils.getTableDataAsList(query1);
        GlobalVariables.dbJobTitle=tableData.get(0).get("job_title");
    }
    @Then("verify job title from frontend and backend")
    public void verify_job_title_from_frontend_and_backend() {
        System.out.println("Backend");
        System.out.println(" Job Title: "+GlobalVariables.dbJobTitle);
        System.out.println("Frontend");
        System.out.println("JobTitle: "+GlobalVariables.jobTitle);
        Assert.assertEquals(GlobalVariables.jobTitle,GlobalVariables.dbJobTitle);
    }

}
