package ge.tbc.testautomation.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CommonPage {
    public Locator cartIcon;
    public Locator burgerIcon;
    public Locator logoutBtn;

    public CommonPage(Page page) {
        this.cartIcon = page.locator("a.shopping_cart_link");
        this.burgerIcon = page.locator("#react-burger-menu-btn");
        this.logoutBtn = page.locator("#logout_sidebar_link");
    }
}
