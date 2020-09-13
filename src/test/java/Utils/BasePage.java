package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BasePage {

    /*
    https://javarevisited.blogspot.com/2012/01/how-to-write-thread-safe-code-in-java.html#:~:text=thread%2Dsafety%20or%20thread%2Dsafe,environment%20is%20not%20thread%2Dsafe.
    thread-safety or thread-safe code in Java refers to code
    which can safely be used or shared in concurrent
    or multi-threading environment and they will behave as expected.
     A thread is a thread of execution in a program.
     The Java Virtual Machine allows an application to have
     multiple threads of execution running concurrently.
     */

    /**
     * http://tutorials.jenkov.com/java-concurrency/threadlocal.html
     * The Java ThreadLocal class enables you to create variables
     * that can only be read and written by the same thread.
     * Thus, even if two threads are executing the same code,
     * and the code has a reference to the same ThreadLocal variable,
     * the two threads cannot see each other's ThreadLocal variables.
     * Thus, the Java ThreadLocal class provides a simple way to make code
     * thread safe that would not otherwise be so.
     *
     *
     */


    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<WebDriver>();

    public BasePage(){

    }

    public static WebDriver get(){
        if (driverPool.get() == null){

            String browserParamFromEnv = System.getProperty("browser");
            String browser = browserParamFromEnv == null ? ConfigReader.getProperty("browser") : browserParamFromEnv;

            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome_headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox_headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;

                default:
                    throw new RuntimeException("Invalid browser name! Use Chrome or Firefox");
            }
        }

        return driverPool.get();

    }

    public static void closeBrowser(){
        driverPool.get().quit();
        driverPool.remove();
    }
}
