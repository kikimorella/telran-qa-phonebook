package phonebook.pages;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Class contains all RegistrationPage locators and methods.
 */
public class RegistrationPage extends Page {

    // locators
    private static final By accountLink = By.xpath("//a");
    private static final By emailField = By.name("email");
    private static final By passwordField = By.name("password");
    private static final By confirmPasswordField = By.name("confirm-password");
    private static final By signUpBtn = By.xpath("//button");
    private static final By registrationErrorMessage = By.id("error-message");
    private static final By emailRequiredErrorMessage = By.id("email-error-required");
    private static final By emailInvalidErrorMessage = By.id("email-error-invalid");
    private static final By passRequiredErrorMessage = By.id("password-error-required");
    private static final By passMinErrorMessage = By.id("password-error-minlength");
    private static final By passMaxErrorMessage = By.id("password-error-maxlength");
    private static final By confirmPassMatcherErrorMessage = By.id("confirm-password-error-matcher");
    private static final By confirmPassRequiredErrorMessage = By.id("confirm-password-error-required");

    // methods
    public void fillInFake() {
        faker = new Faker();
        $(emailField).setValue(faker.internet().emailAddress());
        String pass = faker.internet().password();
        $(passwordField).setValue(pass);
        $(confirmPasswordField).setValue(pass);
    }

    public void fillInCreds(String email, String pass, String confirmPass) {
        $(emailField).sendKeys(email);
        $(passwordField).sendKeys(pass);
        $(confirmPasswordField).sendKeys(confirmPass);
    }

    public void fillInEmail(String email) {
        $(emailField).sendKeys(email);
    }

    public void emailFieldClick() {
        $(emailField).click();
    }

    public void passFieldClick() {
        $(passwordField).click();
    }

    public void confirmPassFieldClick() {
        $(confirmPasswordField).click();
    }

    public void fillInCreds(String email, String pass) {
        $(emailField).sendKeys(email);
        $(passwordField).sendKeys(pass);
    }

    public void signUpBtnClick() {
        $(signUpBtn).click();
    }

    public void signUpBtnDisabled() {
        $(signUpBtn).shouldHave(Condition.disabled);
    }

    public LoginPage accountLinkClick() {
        $(accountLink).click();
        return page(LoginPage.class);
    }

    public String emailRequiredErrorMessageGetText() {
        return $(emailRequiredErrorMessage).getText();
    }

    public String emailInvalidErrorMessageText() {
        return $(emailInvalidErrorMessage).getText();
    }

    public String registrationErrorMessageGetText() {
        return $(registrationErrorMessage).getText();
    }

    public String passMaxErrorMessageGetText() {
        return $(passMaxErrorMessage).getText();
    }

    public String passMinErrorMessageGetText() {
        return $(passMinErrorMessage).getText();
    }

    public String passRequiredErrorMessageGetText() {
        return $(passRequiredErrorMessage).getText();
    }

    public String confirmPassMatcherErrorMessageGetText() {
        return $(confirmPassMatcherErrorMessage).getText();
    }

    public String confirmPassRequiredErrorMessageGetText() {
        return $(confirmPassRequiredErrorMessage).getText();
    }
}
