package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public WebDriver driver;
    public RemoteWebDriver remoteWebDriver;

    public WebDriver WebDriverManager() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String url = prop.getProperty("QAUrl");
        String browser_properties = prop.getProperty("browser");
        String browser_maven = System.getProperty("browser");
        String browser = browser_maven != null ? browser_maven : browser_properties;

        if (driver == null) {
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                //options.addArguments("--headless");
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-gpu");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                options.merge(capabilities);
                driver = new ChromeDriver(options);
            }
            if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe");
                driver = new FirefoxDriver();
            }
            assert driver != null;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
            driver.get(url);
        }
        return driver;
    }

    public RemoteWebDriver getRemoteWebDriver() throws IOException {
        String host = "localhost";
        DesiredCapabilities capabilities;

        if (System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {

            capabilities = new DesiredCapabilities();
        } else {
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            options.merge(capabilities);
        }

        if (System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }
        String completeUrl = "http://" + host + ":4444/wd/hub";
        Properties properties = new Properties();
        String file = "global.properties";
        InputStream inputstream = getClass().getClassLoader().getResourceAsStream(file);
        properties.load(inputstream);
        String url = properties.getProperty("QAUrl");
        remoteWebDriver = new RemoteWebDriver(new URL(completeUrl), capabilities);
        remoteWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        remoteWebDriver.manage().window().maximize();
        remoteWebDriver.get(url);
        return remoteWebDriver;
    }
}

