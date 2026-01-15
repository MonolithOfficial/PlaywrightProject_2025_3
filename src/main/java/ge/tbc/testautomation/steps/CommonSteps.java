package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import ge.tbc.testautomation.pages.CommonPage;

public class CommonSteps {
    CommonPage commonPage;

    public CommonSteps(Page page){
        commonPage = new CommonPage(page);
    }

    public CommonSteps goToCart(){
        // sometimes needed for getting text
        commonPage.cartIcon.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        commonPage.cartIcon.click();

        return this;
    }

    public CommonSteps logout(){
        commonPage.burgerIcon.click();
        commonPage.logoutBtn.click();

        return this;
    }
}
