package StepDefinition;

import RunnerTest.webPages.MessengerPage;
import Utils.BasePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class MessengerSD {

    private MessengerPage messengerPage = new MessengerPage();

    @Given("^I am on the messenger page$")
    public void clickOnMessenger(){
        messengerPage.clickOnMessenger();
        Assert.assertEquals(BasePage.get().getCurrentUrl(), "https://www.messenger.com/");
    }

    @When("^I enter (.+) into (username|password) field on the messenger page$")
    public void enterDataIntoTextField(String value, String textField){

        switch (textField){
            case "username":
                messengerPage.enterEmail(value);
                break;
            case "password":
                messengerPage.enterPassword(value);
                break;
        }
    }

    @And("^I click login button on the messenger page$")
    public void clickOnLoginButton(){
        messengerPage.clickOnLogin();
    }

    @Then("^I verify invalid login the message on the sign in page$")
    public void verifySignInErrorMessage(){
       Assert.assertEquals(messengerPage.getErrorMessageText(), "Please re-enter your password"); //"Incorrect Email"
    }

}
