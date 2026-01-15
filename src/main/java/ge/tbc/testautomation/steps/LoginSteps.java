package ge.tbc.testautomation.steps;

import com.microsoft.playwright.Page;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.LoginPage;

public class LoginSteps extends CommonSteps{
    LoginPage loginPage;

    public LoginSteps(Page page){
        super(page);
        loginPage = new LoginPage(page);
    }

    public LoginSteps fillUserNameInput(){
        loginPage.usernameInput.fill(Constants.STANDARD_USER);

        return this;
    }

    public LoginSteps fillUserNameInput(String username){
        loginPage.usernameInput.fill(username);

        return this;
    }

    public LoginSteps fillPasswordInput(){
        loginPage.passwordInput.fill(Constants.PASSWORD);

        return this;
    }

    public LoginSteps fillPasswordInput(String password){
        loginPage.passwordInput.fill(password);

        return this;
    }

    public LoginSteps clickLoginButton(){
        loginPage.loginButton.click();

        return this;
    }
}
