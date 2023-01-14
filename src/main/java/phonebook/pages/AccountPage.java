package phonebook.pages;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Class contains all AccountPage locators and methods.
 */
public class AccountPage extends Page {

    // locators
    private static By accountHeader = By.xpath("//h4[1]");
    private static By passHeader = By.className("card-title");
    private static By passField = By.name("passwordInput");
    private static By confirmPassField = By.name("confirmPasswordInput");
    private static By passRequiredErrorMessage = By.id("password-error-required");
    private static By passMinErrorMessage = By.id("password-error-minlength");
    private static By passMaxErrorMessage = By.id("password-error-maxlength");
    private static By confirmErrorMessage = By.id("password-confirm-error-matcher");
    private static By acceptBtn = By.xpath("//app-account-password/div/form/button");

    // methods
    public void fillInFakePass() {
        faker = new Faker();
        String pass = faker.internet().password();
        $(passField).setValue(pass);
    }

    public String accountHeaderText() {
        return $(accountHeader).getText();
    }

    public void fillInPass(String pass) {
        $(passField).sendKeys(pass);
    }

    public void fillInConfirmPass(String pass) {
        $(confirmPassField).sendKeys(pass);
    }

    public void passClick() {
        $(passField).click();
    }

    public void confirmPassClick() {
        $(confirmPassField).click();
    }

    public void passHeaderClick() {
        $(passHeader).click();
    }

    public void acceptBtnActive() {
        $(acceptBtn).shouldBe().isDisplayed();
    }

    public void acceptBtnDisabled() {
        $(acceptBtn).shouldHave(Condition.disabled);
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

    public String confirmPassErrorMessageGetText() {
        return $(confirmErrorMessage).getText();
    }
}
