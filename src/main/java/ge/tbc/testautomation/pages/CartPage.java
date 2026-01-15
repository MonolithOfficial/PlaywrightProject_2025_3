package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage extends CommonPage {
    public Locator cartItems;
    public Locator checkoutButton;

    public CartPage(Page page){
        super(page);
        this.cartItems = page.locator("div.cart_item");
        this.checkoutButton = page.locator("#checkout");
    }
}
