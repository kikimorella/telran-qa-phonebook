package phonebook.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Class contains all LoginPage locators and methods.
 */
public class LoginPage extends Page {

    // locators
    private static By loginBtn = By.xpath("//button");
    private static By emailField = By.name("email");
    private static By passwordField = By.name("password");
    private static By registerNewAccountLink = By.xpath("//a[@href='/user/registration']");
    private static By forgotPassLink = By.xpath("//a[@href='/user/forgot-password']");

    private static By errorMessage = By.id("error-message");
    private static By emailRequiredErrorMessage = By.xpath("//div[@id = 'email-error-required']");
    private static By emailInvalidErrorMessage = By.id("email-error-invalid");
    private static By passRequiredErrorMessage = By.id("password-error-required");
    private static By passMinErrorMessage = By.id("password-error-minlength");
    private static By passMaxErrorMessage = By.id("password-error-maxlength");

    // methods

    public void fillInCreds(String email, String pass) {
        $(emailField).sendKeys(email);
        $(passwordField).sendKeys(pass);
    }

    public void fillInEmail(String email) {
        $(emailField).sendKeys(email);
    }

    public void emailClick() {
        $(emailField).click();
    }

    public void passClick() {
        $(passwordField).click();
    }

    public ContactsPage loginBtnClick() {
        $(loginBtn).click();
        return page(ContactsPage.class);
    }

    public void loginBtnDisabled() {
        $(loginBtn).getAttribute("disabled");
    }

    public ForgotPage forgotPassLinkClick() {
        $(forgotPassLink).click();
        return page(ForgotPage.class);
    }

    public RegistrationPage registerNewAccountLinkClick() {
        $(registerNewAccountLink).click();
        return page(RegistrationPage.class);
    }

    public String errorMessageGetText() {
        return $(errorMessage).getText();
    }

    public String emailRequiredErrorMessageGetText() {
        return $(emailRequiredErrorMessage).getText();
    }

    public String emailInvalidErrorMessageGetText() {
        return $(emailInvalidErrorMessage).getText();
    }

    public String passRequiredErrorMessageGetText() {
        return $(passRequiredErrorMessage).getText();
    }

    public String passMinErrorMessageGetText() {
        return $(passMinErrorMessage).getText();
    }

    public String passMaxErrorMessageGetText() {
        return $(passMaxErrorMessage).getText();
    }
}
