package ge.tbc.testautomation.tests;

import io.qameta.allure.*;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;


//@Listeners({CustomTestListener.class})
@Test(groups = {"E2E - successful product purchase - SCRUM-T18", "selenide_scenarios"})
@Description("Successful Purchase Scenario")
@Link("https://atlassian.net/scenario")
@Severity(SeverityLevel.BLOCKER)
@Epic("Transactions")
@Feature("Purchase product")
@Story("Successful product purchase")
public class SuccessfulPurchaseScenarioTest extends BaseTest{
    private static final Logger logger = LogManager.getLogger();

    @Test(description = "Login as standard user", priority = 1)
    public void loginAsStandardUser() {
        loginSteps
                .fillUserNameInput()
                .fillPasswordInput()
                .clickLoginButton();
    }

    @Test(description = "Add backpack to cart", priority = 2)
    public void addToCart() {
        dashboardSteps
                .clickAddToCart()
                .assertRemoveButtonVisibility();
    }

    @Test(description = "Review the cart", priority = 3)
    public void reviewCart() {
        cartSteps.goToCart();
        cartSteps.assertCartItemsSize(1);
    }

    @Test(description = "Go to checkout page", priority = 4)
    public void goToCheckout() {
        cartSteps.goToCheckout();
        checkoutSteps.assertCheckoutLabel();
    }

    @Test(description = "Enter checkout information", priority = 5)
    public void enterInformation() {
        checkoutSteps.fillInformation(
                "John", "Cena", "100100"
        );
    }

    @Test(description = "Proceed to final page", priority = 6)
    public void proceedToFinalPage() {
        checkoutSteps.goToCheckoutOverview();
        overviewSteps.assertOverviewLabel();
    }

    @Test(description = "Finish order", priority = 7)
    public void finishOrder() {
        overviewSteps
                .finishOrder()
                .assertSuccessMessage();
    }
}
