package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DashboardPage extends CommonPage {
    public Locator addToCartButton;
    public Locator removeButton;

    public DashboardPage(Page page) {
        super(page);
        this.addToCartButton = page.locator("//div[text()='Sauce Labs Backpack']//following::button[1]");
        this.removeButton = page.locator("#remove-sauce-labs-backpack");
    }
}
