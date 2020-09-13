package RunnerTest.webPages;

import org.openqa.selenium.By;

public class MessengerPage extends ElementUtil{
    private By messenger = By.linkText("Messenger");
    private By emailField = By.name("email");
    private By passwordField = By.id("pass");
    private By loginButton = By.xpath("//button[@id='loginbutton']");
//    private By errorMessage = By.xpath("//div[contains(text(), 'Incorrect Email')]");
    private By errorMessage = By.xpath("//div[contains(text(), 'Please re-enter your password')]");
    public void clickOnMessenger(){
        clickOn(messenger);
    }
    public void enterEmail(String email){
        sendValue(emailField, email);
    }
    public void enterPassword(String password){
        sendValue(passwordField, password);
    }

    public void clickOnLogin(){
        clickOn(loginButton);
    }

    public String getErrorMessageText(){
        return getTextFromElement(errorMessage);
    }
}
