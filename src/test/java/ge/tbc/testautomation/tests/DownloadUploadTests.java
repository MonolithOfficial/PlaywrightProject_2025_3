package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import ge.tbc.testautomation.steps.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadUploadTests {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void initializeSteps() {
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false);
        launchOptions.setSlowMo(4000);

        browser = playwright.webkit().launch(launchOptions);
        browser = playwright.chromium().launch(launchOptions);

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
    }

    @Test
    public void testDownload() throws IOException {
        page.navigate("https://the-internet.herokuapp.com/download");

        Locator pythonDownloadLink = page.getByText("tmpma3uq3i1.txt");
//        Download downloadedFile = page.waitForDownload(() -> {
//            pythonDownloadLink.click();
//        });
        Download downloadedFile = page.waitForDownload(pythonDownloadLink::click);
        downloadedFile.saveAs(Paths.get(System.getProperty("user.dir"), "/build/downloads", downloadedFile.suggestedFilename()));
        Path pathToDownloadedFile = downloadedFile.path();
        String fileContent = Files.readString(pathToDownloadedFile);
        System.out.println(fileContent);
    }

    @Test
    public void testUpload() {
        page.navigate("https://the-internet.herokuapp.com/upload");

        Locator uploadInput = page.locator("#file-upload");

        FileChooser fileChooser = page.waitForFileChooser(uploadInput::click);

        if (fileChooser.isMultiple()) {
            fileChooser.setFiles(new Path[]{
                    Paths.get(System.getProperty("user.dir"), "src/main/resources/ronaldokneeslide.jpg"),
                    Paths.get(System.getProperty("user.dir"), "src/main/resources/ronaldokneeslide.jpg")
            });
        } else {
            Paths.get(System.getProperty("user.dir"), "src/main/resources/ronaldokneeslide.jpg");
        }
    }

    @Test
    public void JSTest() {
        page.navigate("https://swoop.ge/");
        page.waitForFunction("() => document.readyState === 'complete'");


        String location = (String) page.evaluate("window.location.href");
        System.out.println(location);

        Locator swoopLogo = page.locator("//img[@src='/icons/swoop-new.svg' and @fetchpriority='high']");
        Locator searchBar = page.locator("//input[@type='text']");
        ElementHandle swoopLogoHandle = swoopLogo.elementHandle();

        page.evaluate("element => element.click();", swoopLogoHandle);
        swoopLogo.evaluate("element => element.click();");

        Map<String, ElementHandle> elementHandles = new HashMap<>();
        elementHandles.put("swoopLogo", swoopLogoHandle);
        elementHandles.put("searchBar", searchBar.elementHandle());


        page.evaluate("({ swoopLogo, searchBar }) => {swoopLogo.click(); searchBar.click()}");
    }

    @Test
    public void alertsFramesAndTabs(){
        page.navigate("https://demoqa.com/alerts");
        Locator alertButton = page.locator("button#alertButton");

        alertButton.click();

        // ONLY IF YOU WANNA DO SOMETHING ON THE ALERT..
        page.onDialog((dialog) -> {
            dialog.accept("something");
        });
    }

    @Test
    public void frameTest(){
        page.navigate("https://jqueryui.com/resizable/");

        Locator resizableLabel =
                page
                        .frameLocator(".demo-frame")
//                        .frameLocator(".other-frame") // if there are nested frames
                .getByText("Resizable");
        System.out.println(resizableLabel.textContent());
    }

    @Test
    public void testTabs(){
        page.navigate("https://stackoverflow.com");
        Page page2 = context.newPage();
        page2.navigate("https://google.come");

        Page page3 = context.newPage();
        page3.navigate("https://swoop.ge");

        page3.bringToFront();

        List<Page> allTabs = context.pages();
        page3.close();
    }

    @AfterClass
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }
}
