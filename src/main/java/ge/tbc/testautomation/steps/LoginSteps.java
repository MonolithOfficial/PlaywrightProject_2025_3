package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.LoginPage;
import io.qameta.allure.Step;

public class LoginSteps extends CommonSteps{
    LoginPage loginPage;

    public LoginSteps(Page page){
        super(page);
        loginPage = new LoginPage(page);
    }

    @Step("Fill username input")
    public LoginSteps fillUserNameInput(){
        loginPage.usernameInput.fill(Constants.STANDARD_USER);

        return this;
    }

    @Step("Fill username input with value {}")
    public LoginSteps fillUserNameInput(String username){
        loginPage.usernameInput.fill(username);

        return this;
    }

    @Step("Fill password input")
    public LoginSteps fillPasswordInput(){
        loginPage.passwordInput.fill(Constants.PASSWORD);

        return this;
    }

    @Step("Fill password input with value {}")
    public LoginSteps fillPasswordInput(String password){
        loginPage.passwordInput.fill(password);

        return this;
    }

    @Step("Click login button")
    public LoginSteps clickLoginButton(){
        loginPage.loginButton.click();

        return this;
    }
}
