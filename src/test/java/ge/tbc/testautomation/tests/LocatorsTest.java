package ge.tbc.testautomation.tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.steps.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class LocatorsTest {
    private static final Logger logger = LogManager.getLogger();
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeClass
    public void initializeSteps(){
        playwright = Playwright.create();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.setHeadless(false);
        launchOptions.setTimeout(10000);
        launchOptions.setSlowMo(1000);

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
        page.navigate("https://practicesoftwaretesting.com/");
    }

    @Test
    public void testLocators(){
//        Locator handSawCheckBox = page.getByLabel("Hand Saw");
//        handSawCheckBox.click();
//
//        Locator combinationPliersCard = page.getByText("Wood Saw");
//        combinationPliersCard.click();

        Locator allCards = page.locator("//h5[@class='card-title']");
//        allCards.first().hover();
//        logger.info(allCards.count());
//        logger.info(allCards.first().textContent());
        for (Locator card : allCards.all()){
//            card.hover();
            PlaywrightAssertions.assertThat(card).hasText(Pattern.compile(".*[A-Za-z].*"));
//            card.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            logger.info(card.textContent());
        }

        Locator filteredCards = allCards.filter(
                new Locator.FilterOptions().setHas(page.getByText(Pattern.compile("Pliers")))
        );

        PlaywrightAssertions.assertThat(filteredCards).hasCount(4);


    }

    @Test
    public void getByRoleTest(){
        Locator measureCheckBox = page.getByRole(AriaRole.CHECKBOX,
                new Page.GetByRoleOptions().setName("Measures"));
        measureCheckBox.click();

        Locator searchBar = page.getByRole(AriaRole.TEXTBOX);
        searchBar.fill("I AM MR ROBOT");
    }

    @Test
    public void testChainLocators(){
         Locator wrenchCheckBox = page
                 .locator("fieldset")
                 .locator("div.checkbox")
                 .getByLabel("Wrench")
                 .locator("..");
        logger.info(wrenchCheckBox.innerHTML());
//         wrenchCheckBox.click();
    }

    @Test
    public void testPriceFormatting(){
        Locator allCards = page.locator("a.card");
        for (Locator card : allCards.all()){
            Locator priceSpan = card
                    .locator(".card-footer span span");
//            PlaywrightAssertions.assertThat(priceSpan).hasText(Pattern.compile(".*[A-Za-z].*"));
            PlaywrightAssertions.assertThat(priceSpan).containsText("$");
//            logger.info(card.textContent());
        }
    }

    @AfterClass
    public void tearDown(){
        page.close();
        browser.close();
        playwright.close();
    }
}
