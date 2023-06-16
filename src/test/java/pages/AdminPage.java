package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AdminPage extends CommonMethods {

    @FindBy (id = "menu_admin_Job")
    public WebElement jobButton;
    @FindBy(linkText = "Job Titles")
    public  WebElement jobTitleTab;
    @FindBy (id = "btnAdd")
    public WebElement addJobTitleButton;
    @FindBy(id = "jobTitle_jobTitle")
    public WebElement jobTitleTextBox;
    @FindBy(id = "btnSave")
    public WebElement jobTitleSaveButton;

    public AdminPage(){
        PageFactory.initElements(driver, this);
    }
}


