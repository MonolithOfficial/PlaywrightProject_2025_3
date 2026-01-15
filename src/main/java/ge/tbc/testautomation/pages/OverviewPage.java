package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class OverviewPage extends CommonPage {
    public Locator checkoutPageLabel;
    public Locator finish;
    public Locator successMessage;

    public OverviewPage(Page page) {
        super(page);
        this.checkoutPageLabel = page.locator("span.title");
        this.finish = page.locator("#finish");
        this.successMessage = page.locator("h2.complete-header");
    }
}
