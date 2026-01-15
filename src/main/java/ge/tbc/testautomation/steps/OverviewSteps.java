package ge.tbc.testautomation.steps;


import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.LoginPage;
import ge.tbc.testautomation.pages.OverviewPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static ge.tbc.testautomation.data.Constants.CHECKOUT_OVERVIEW_LABEL;
import static ge.tbc.testautomation.data.Constants.SUCCESS_MESSAGE;

public class OverviewSteps extends CommonSteps {
    OverviewPage overviewPage;

    public OverviewSteps(Page page){
        super(page);
        overviewPage = new OverviewPage(page);
    }

    public OverviewSteps assertOverviewLabel(){
        assertThat(overviewPage.checkoutPageLabel).hasText(CHECKOUT_OVERVIEW_LABEL);

        return this;
    }

    public OverviewSteps finishOrder(){
        overviewPage.finish.click();

        return this;
    }

    public OverviewSteps assertSuccessMessage(){
        assertThat(overviewPage.successMessage).hasText(SUCCESS_MESSAGE);

        return this;
    }
}
