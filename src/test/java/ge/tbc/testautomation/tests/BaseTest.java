package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.*;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;

import static ge.tbc.testautomation.data.Constants.EXTENSION_PATH;
import static ge.tbc.testautomation.data.Constants.TMP_PATH;

public class BaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    LoginSteps loginSteps;
    DashboardSteps dashboardSteps;
    CartSteps cartSteps;
    CheckoutSteps checkoutSteps;
    OverviewSteps overviewSteps;

    @BeforeClass
    @Parameters("browserType")
    public void initializeSteps(String browserType){
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false);

        if (browserType.equalsIgnoreCase("webkit")){
            browser = playwright.webkit().launch(launchOptions);
        } else if (browserType.equalsIgnoreCase("chrome")) {
            browser = playwright.chromium().launch(launchOptions);
        }

        context = browser.newContext();
//        ADBLOCKER
//        context = playwright.chromium().launchPersistentContext(
//                Paths.get(TMP_PATH),
//                new BrowserType.LaunchPersistentContextOptions()
//                        .setHeadless(false)
//                        .setSlowMo(10000)
//                        .setArgs(Arrays.asList(
//                                String.format("--load-extensions=%s", EXTENSION_PATH),
//                                String.format("--disable-extensions-except=%s", EXTENSION_PATH)
//                        ))
//        );
        page = context.newPage();
        page.navigate("https://www.saucedemo.com/");
        loginSteps = new LoginSteps(page);
        dashboardSteps = new DashboardSteps(page);
        cartSteps = new CartSteps(page);
        checkoutSteps = new CheckoutSteps(page);
        overviewSteps = new OverviewSteps(page);
    }

    @AfterClass
    public void tearDown(){
        page.close();
        browser.close();
        playwright.close();
    }
}
