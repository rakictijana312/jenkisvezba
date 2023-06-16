package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;


public class NIHPages extends CommonMethods{
    // first next button at Study intro
    @FindBy(id = "NextButton")
    public WebElement nextButton;


    public NIHPages(){PageFactory.initElements(driver, this);}
}


