package StepDefinition;

import RunnerTest.webPages.ElementUtil;
import Utils.BasePage;
import Utils.ConfigReader;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class SharedSD {

    ElementUtil elementUtil = new ElementUtil();
    private static Logger logger = Logger.getLogger(SharedSD.class);

    //Hook class
    @Before
    public void setup(){
        logger.info("*********************************");
        logger.info("Test setup method is starting...!");
        ConfigReader.getProperty("browser");
        BasePage.get().get(ConfigReader.getProperty("url"));
        BasePage.get().manage().window().fullscreen();
        BasePage.get().manage().deleteAllCookies();
    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            logger.error("The test has failed!");
            byte[] screenshot = ( (TakesScreenshot) BasePage.get()).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
            elementUtil.getStringShot("pic");
        }
        else {
            logger.info("Cleanup!");
            logger.info("Test has been completed!");
        }
        logger.info("*********************************");
        BasePage.closeBrowser();
    }
}
