package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.CheckoutPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.data.Constants.CHECKOUT_LABEL;

public class CheckoutSteps extends CommonSteps{
    CheckoutPage checkoutPage;

    public CheckoutSteps(Page page){
        super(page);
        checkoutPage = new CheckoutPage(page);
    }

    public CheckoutSteps assertCheckoutLabel(){
        assertThat(checkoutPage.checkoutPageLabel).hasText(CHECKOUT_LABEL);
        return this;
    }

    public CheckoutSteps fillInformation(String firstname, String lastName, String zipCode){
        checkoutPage.firstNameInput.fill(firstname);
        checkoutPage.lastNameInput.fill(lastName);
        checkoutPage.zipCodeInput.fill(zipCode);

        return this;
    }

    public CheckoutSteps goToCheckoutOverview(){
        checkoutPage.continueButton.click();

        return this;
    }
}
