package phonebook.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Class contains all ForgotPasswordPage locators and methods.
 */
public class ForgotPage extends Page{

    // locators
    private static By registerNewAccountLink = By.xpath("//a[@href='/user/registration']");
    private static By alreadyHaveAnAccountLink = By.xpath("//a[@href='/user/login']");
    private static By emailField = By.name("email");
    private static By sendBtn = By.xpath("//button]");
    private static By successMessage = By.id("success-message");

    private static By errorMessage = By.className("alert alert-danger");

    // methods
    public void fillInEmailField(String email) {
        $(emailField).sendKeys(email);
    }

    public String getErrorMessageText() {
        return $(errorMessage).getText();
    }

    public void sendBtnClick() {
        $(sendBtn).click();
    }

    public void sendBtnDisabled() {
        $(sendBtn).shouldHave(Condition.disabled);
    }

    public void successMessagePresent() {
        $(successMessage).isDisplayed();
    }

    public LoginPage alreadyHaveAnAccountLinkClick() {
        $(alreadyHaveAnAccountLink).click();
        return page(LoginPage.class);
    }

    public RegistrationPage registerNewAccountLinkClick() {
        $(registerNewAccountLink).click();
        return page(RegistrationPage.class);
    }
}
