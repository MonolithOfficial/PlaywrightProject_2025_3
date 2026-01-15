package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.CartPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartSteps extends CommonSteps {
    Page page;
    CartPage cartPage;

    public CartSteps(Page page){
        super(page);
        this.cartPage = new CartPage(page);
    }

    public CartSteps assertCartItemsSize(int expectedSize){
        assertThat(cartPage.cartItems).hasCount(expectedSize);

        return this;
    }

    public CartSteps goToCheckout(){
        cartPage.checkoutButton.click();

        return this;
    }
}
