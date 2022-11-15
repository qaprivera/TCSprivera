package setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

//headless más WebDriver Manager
public class WebAutomator {

    public WebDriver driver;
    public WebDriverWait wait;

    public WebAutomator(Browser browser,HashMap<String, String> ... preferencias) throws Exception {

        final String arquitecturaOS = System.getProperty("os.arch");
        switch (browser) {
            case CHROME:
                if (arquitecturaOS.contains("64")) {
                    WebDriverManager.chromedriver().arch64();
                } else {
                    WebDriverManager.chromedriver().arch32();
                }
                WebDriverManager.chromedriver().setup();

                if (preferencias.length >0) {
                    HashMap <String,String> capabilities = preferencias[0];
                    if(Boolean.parseBoolean(capabilities.get("headless").toString())){
                        ChromeOptions chromeOptions = new ChromeOptions();

                        chromeOptions.addArguments("--disable-notifications");

                        driver = new ChromeDriver(chromeOptions);
                    }else{
                        ChromeOptions chromeOptions = new ChromeOptions();

                        chromeOptions.addArguments("--disable-notifications");
                        driver = new ChromeDriver(chromeOptions);
                    }
                }else{
                    ChromeOptions chromeOptions = new ChromeOptions();

                    chromeOptions.addArguments("--disable-notifications");
                    driver = new ChromeDriver(chromeOptions);
                }
                break;

            case IE:
                if(arquitecturaOS.contains("64")){
                    WebDriverManager.iedriver().arch64();
                }else{
                    WebDriverManager.iedriver().arch32();
                }
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            case FIREFOX:
                if(arquitecturaOS.contains("64")){
                    WebDriverManager.firefoxdriver().arch64();
                }else{
                    WebDriverManager.firefoxdriver().arch32();
                }
                WebDriverManager.firefoxdriver().setup();

                if (preferencias.length >0) {
                    HashMap <String,String> capabilities = preferencias[0];
                    if(Boolean.parseBoolean(capabilities.get("headless").toString())){
                        FirefoxOptions foptions = new FirefoxOptions();
                        foptions.setHeadless(true);
                        driver = new FirefoxDriver(foptions);
                    }else{
                        driver = new FirefoxDriver();
                    }
                }else{
                    driver = new FirefoxDriver();
                }
                break;

            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            case OPERA:
                if(arquitecturaOS.contains("64")){
                    WebDriverManager.operadriver().arch64();
                }else{
                    WebDriverManager.operadriver().arch32();
                }
                WebDriverManager.operadriver().setup();
                OperaOptions options = new OperaOptions();
                options.setBinary(Configuration.OPERA_WINDOWS_BINARY_PATH);
                driver = new OperaDriver(options);
                break;

            case HTMLUNIT:
                driver = new HtmlUnitDriver(true);
                break;

            default:
                throw new Exception("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Configuration.TIMEOUT_WAIT);
    }

    public WebAutomator() throws Exception{
        driver = new HtmlUnitDriver(true);
    }

    private UiElement wait(ExpectedCondition<WebElement> condition) {
        System.out.println("Esperando por la condicion: " + condition.toString());
        return new UiElement(this, wait.until(condition));
    }

    public UiElement waitUntilPresent(By by) {
        return wait(ExpectedConditions.presenceOfElementLocated(by));
    }


    public UiElement find(By by) {
        return waitUntilPresent(by);

    }

    public void cerrarNavegador() {
        driver.quit();
        System.out.println("Driver quit");
    }
    public String obtenerTitulo() {

        return driver.getTitle();
    }
    public WebDriverWait getWait(){
        return wait;
    }
    public WebDriver getDriver(){
        return driver;
    }

    public void abrirPagina(String urlDestino) {
        driver.get(urlDestino);
    }

    public UiElement abriPaginaV2(String url, By by) {
        System.out.println("Go to URL: " + url);
        driver.get(url);
        return waitUntilPresent(by);
    }

    public void assertPresent(By by) {
        try {
            waitUntilPresent(by);
        } catch (Exception e) {
            System.out.println("Assertion exception while checking presence of element by: " + by.toString());
            throw new AssertionError("Element not present" + e.getMessage());
        }
    }
}
