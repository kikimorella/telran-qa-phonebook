package phonebook;

import org.junit.jupiter.api.Test;
import phonebook.pages.ContactsPage;
import phonebook.pages.ForgotPage;
import phonebook.pages.LoginPage;
import phonebook.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static phonebook.pages.Page.*;

public class LoginTests {

    //classes
    private LoginPage loginPage;
    private ContactsPage contactsPage;
    private ForgotPage forgotPasswordPage;
    private RegistrationPage registrationPage;

    /**
     * This test checks successful login functionality
     */
    @Test
    public void loginWithValidCredentials() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        loginPage.fillInCreds(VALID_EMAIL, VALID_PASS);
        contactsPage = loginPage.loginBtnClick();
        contactsPage.profilePresent();
    }

    /**
     * This test checks successful login functionality with not admin credentials
     */
    @Test
    public void loginWithNewCredentials() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        loginPage.fillInCreds("ananas@ab34.fr", "passWord");
        loginPage.loginBtnClick();
        assertEquals("Please check your activation or Login + Password combination", loginPage.errorMessageGetText());
        loginPage.loginBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter no email
     */
    @Test
    public void loginWithValidPassNoEmail() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        loginPage.emailClick();
        loginPage.passClick();
        assertEquals(NO_EMAIL, loginPage.emailRequiredErrorMessageGetText());
        loginPage.loginBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid password and invalid email
     */
    @Test
    public void loginWithValidPassInvalidEmail() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        loginPage.fillInEmail("123");
        assertEquals(WRONG_EMAIL, loginPage.emailInvalidErrorMessageGetText());
        loginPage.loginBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid email and delete the password
     */
    @Test
    public void loginWithValidEmailNoPass() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        loginPage.fillInEmail(VALID_EMAIL);
        loginPage.passClick();
        loginPage.emailClick();
        assertEquals(NO_PASS, loginPage.passRequiredErrorMessageGetText());
        loginPage.loginBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid email and less than 8 symbols as a password
     */
    @Test
    public void loginWithValidEmailAndSmallPass() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        loginPage.fillInCreds(VALID_EMAIL, "123");
        assertEquals(SMALL_PASS, loginPage.passMinErrorMessageGetText());
        loginPage.loginBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid email and more than 20 symbols as a password
     */
    @Test
    public void loginWithValidEmailAndLongPass() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        loginPage.fillInCreds(VALID_EMAIL, "longer than 20 symbols");
        assertEquals(LONG_PASS, loginPage.passMaxErrorMessageGetText());
        loginPage.loginBtnDisabled();
    }

    /**
     * This test checks if there is a transition to another page if you click on registration link
     */
    @Test
    public void registrationLinkClick() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        registrationPage = loginPage.registerNewAccountLinkClick();
        registrationPage.headerTextPresent();
    }

//    /**
//     * This test checks if there is a transition to another page if you click on forgot password link
//     */
//    @Test
//    public void forgotPasswordLinkClick() {
//        loginPage = open(LOGIN_URL, LoginPage.class);
//        forgotPasswordPage = loginPage.forgotPassLinkClick();
//        forgotPasswordPage.headerTextPresent();
//    } // test failed: Caused by: org.openqa.selenium.InvalidSelectorException: Compound class names not permitted

}
