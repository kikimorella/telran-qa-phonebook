package phonebook;

import org.junit.jupiter.api.Test;
import phonebook.pages.ForgotPage;
import phonebook.pages.LoginPage;
import phonebook.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static phonebook.pages.Page.*;

public class ForgotPasswordTest {

    //classes
    private LoginPage loginPage;
    private ForgotPage forgotPasswordPage;
    private RegistrationPage registrationPage;

    /**
     * This test checks if a success message appears if you enter valid email
     */
    @Test
    public void validEmailEntered() {
        forgotPasswordPage = open(FORGOT_PASSWORD_URL, ForgotPage.class);
        forgotPasswordPage.fillInEmailField(VALID_EMAIL);
        forgotPasswordPage.sendBtnClick();
        forgotPasswordPage.successMessagePresent();
    }

    /**
     * This test checks if an error message appears if you do not enter any email
     */
    @Test
    public void noEmailEntered() {
        forgotPasswordPage = open(FORGOT_PASSWORD_URL, ForgotPage.class);
        forgotPasswordPage.fillInEmailField(" ");
        forgotPasswordPage.sendBtnDisabled();
        assertEquals(NO_EMAIL, forgotPasswordPage.getErrorMessageText());
    }

    /**
     * This test checks if an error message appears if you enter any invalid email
     */
    @Test
    public void anySymbolEntered() {
        forgotPasswordPage = open(FORGOT_PASSWORD_URL, ForgotPage.class);
        forgotPasswordPage.fillInEmailField("1@1.r");
        forgotPasswordPage.sendBtnDisabled();
        assertEquals(WRONG_EMAIL, forgotPasswordPage.getErrorMessageText());
    }

    /**
     * This test checks if there is a transition to another page if you click on registration link
     */
    @Test
    public void registrationLinkClick() {
        forgotPasswordPage = open(FORGOT_PASSWORD_URL, ForgotPage.class);
        registrationPage = forgotPasswordPage.registerNewAccountLinkClick();
        registrationPage.headerTextPresent();
    }

    /**
     * This test checks if there is a transition to another page if you click on login link
     */
    @Test
    public void alreadyHaveAnAccountLinkClick() {
        forgotPasswordPage = open(FORGOT_PASSWORD_URL, ForgotPage.class);
        loginPage = forgotPasswordPage.alreadyHaveAnAccountLinkClick();
        loginPage.headerTextPresent();
    }
}
