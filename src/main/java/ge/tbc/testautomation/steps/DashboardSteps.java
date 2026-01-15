package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.pages.DashboardPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DashboardSteps extends CommonSteps {
    DashboardPage dashboardPage;

    public DashboardSteps(Page page){
        super(page);
        dashboardPage = new DashboardPage(page);
    }

    public DashboardSteps clickAddToCart(){
        dashboardPage.addToCartButton.click();

        return this;
    }

    public DashboardSteps assertRemoveButtonVisibility(){
        assertThat(dashboardPage.removeButton).isVisible();

        return this;
    }
}
