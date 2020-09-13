package RunnerTest.webPages;

import Utils.BasePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class ElementUtil {

    /**
     * Function<> Interface is used!
     * Function<> takes one argument and one result!
     * It's used with the apply() function!
     * see details here!
     * https://www.geeksforgeeks.org/function-interface-in-java-with-examples/#:~:text=The%20Function%20Interface%20is%20a,argument%20and%20produces%20a%20result.
     */

    public static WebElement webAction(final By locator){
        Wait<WebDriver> wait = new FluentWait<>(BasePage.get())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementClickInterceptedException.class)
                .withMessage("WebDriver has waited for 15 seconds still" +
                        "not able to find the element, therefore" +
                        "TimeOutException has been thrown");
        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
    }

    public void clickOn(By locator){
        webAction(locator).click();
    }

    public void sendValue(By locator, String value){
        webAction(locator).sendKeys(value);
    }

    public String getTextFromElement(By locator){
        return webAction(locator).getText();
    }

    public void clear(By locator){
        webAction(locator).clear();
    }

    public boolean isElementDisplayed(By locator){
        return webAction(locator).isDisplayed();
    }
    public boolean isElementEnabled(By locator){
        return webAction(locator).isEnabled();
    }
    public boolean isElementSelected(By locator){
        return webAction(locator).isSelected();
    }

    public List<WebElement> webElements(By locator){
        return BasePage.get().findElements(locator);
    }

    public void selectFromDropDownText(By locator, String dropDownText){
        WebElement text = webAction(locator);
        Select selectText = new Select(text);
        selectText.selectByVisibleText(dropDownText);
    }

    public void selectFromDropDownIndex(By locator, int dropDownIndex){
        WebElement text = webAction(locator);
        Select selectText = new Select(text);
        selectText.selectByIndex(dropDownIndex);
    }

    public void scrollByElement(By locator){
        WebElement scrollElement = webAction(locator);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) BasePage.get();
        javascriptExecutor.executeScript("argument[0].scrollIntoView;", scrollElement);
    }

    public void scrollByPixel(String xPixel, String yPixel)  {
        String string = "window.scroll(" + xPixel + ", "+ yPixel+")";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) BasePage.get();
        javascriptExecutor.executeScript(string);
    }

    public void moveToElementByLocator(By locator){
        Actions actions = new Actions(BasePage.get());
        actions.moveToElement(webAction(locator)).build().perform();
    }

    public String getStringShot(String name){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("-yyyy-MM-dd-HH-mm");
        String date = simpleDateFormat.format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) BasePage.get();
        File source = takesScreenshot.getScreenshotAs((OutputType.FILE));
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return target;
    }

}
