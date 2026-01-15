package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CheckoutPage extends CommonPage{
    public Locator checkoutPageLabel;
    public Locator firstNameInput;
    public Locator lastNameInput;
    public Locator zipCodeInput;
    public Locator continueButton;

    public CheckoutPage(Page page) {
        super(page);
        this.checkoutPageLabel = page.locator("span.title");
        this.firstNameInput = page.locator("#first-name");
        this.lastNameInput = page.locator("#last-name");
        this.zipCodeInput = page.locator("#postal-code");
        this.continueButton = page.locator("#continue");
    }
}
