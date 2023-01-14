package phonebook;

import org.junit.jupiter.api.Test;
import phonebook.pages.LoginPage;
import phonebook.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static phonebook.pages.Page.*;

public class RegistrationTest {

    //classes
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    /**
     * This test checks successful registration functionality
     */
    @Test
    public void registrationWithSameEmailAndPass() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.fillInFake();
        registrationPage.signUpBtnClick();
        assertEquals("noErrorMsg", registrationPage.registrationErrorMessageGetText());
    }

    /**
     * This test checks if an error message appears if you enter valid email and password
     */
    @Test
    public void secondRegistrationWithValidCredentials() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.fillInCreds(VALID_EMAIL, VALID_PASS, VALID_PASS);
        registrationPage.signUpBtnClick();
        assertEquals("Error! User already exists Login now?", registrationPage.registrationErrorMessageGetText());
    }

    /**
     * This test checks if an error message appears if you delete email
     */
    @Test
    public void registrationWithoutEmail() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.emailFieldClick();
        registrationPage.passFieldClick();
        assertEquals(NO_EMAIL, registrationPage.emailRequiredErrorMessageGetText());
        registrationPage.signUpBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter invalid email
     */
    @Test
    public void registrationWithInvalidEmail() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.fillInEmail("123");
        assertEquals(WRONG_EMAIL, registrationPage.emailInvalidErrorMessageText());
        registrationPage.signUpBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid email and password longer than 20 symbols
     */
    @Test
    public void registrationWithValidEmailAndLongPass() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.fillInCreds(VALID_EMAIL, "longer than 20 symbols");
        assertEquals(LONG_PASS, registrationPage.passMaxErrorMessageGetText());
        registrationPage.signUpBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid email and password less than 8 symbols
     */
    @Test
    public void registrationWithValidEmailAndSmallPass() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.fillInCreds(VALID_EMAIL, "test");
        assertEquals(SMALL_PASS, registrationPage.passMinErrorMessageGetText());
        registrationPage.signUpBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid email and no password
     */
    @Test
    public void registrationWithValidEmailAndNoPass() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.fillInEmail(VALID_EMAIL);
        registrationPage.passFieldClick();
        registrationPage.confirmPassFieldClick();
        assertEquals(NO_PASS, registrationPage.passRequiredErrorMessageGetText());
        registrationPage.signUpBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid email and password, but no password confirmation
     */
    @Test
    public void registrationWithValidEmailAndPassWithoutConfirmation() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.fillInCreds(VALID_EMAIL, VALID_PASS);
        registrationPage.signUpBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid email and password, but wrong password confirmation
     */
    @Test
    public void registrationWithValidEmailAndPassWrongConfirmation() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.fillInCreds(VALID_EMAIL, VALID_PASS, " ");
        assertEquals("Passwords does not match", registrationPage.confirmPassMatcherErrorMessageGetText());
        registrationPage.signUpBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter valid email and password, but delete password confirmation
     */
    @Test
    public void registrationWithValidEmailAndPassNoConfirmation() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        registrationPage.fillInCreds(VALID_EMAIL, VALID_PASS);
        registrationPage.confirmPassFieldClick();
        registrationPage.emailFieldClick();
        assertEquals("Confirm password is required.", registrationPage.confirmPassRequiredErrorMessageGetText());
        registrationPage.signUpBtnDisabled();
    }

    /**
     * This test checks if there is a transition to another page if you click on account link
     */
    @Test
    public void accountLinkClick() {
        registrationPage = open(REGISTRATION_URL, RegistrationPage.class);
        loginPage = registrationPage.accountLinkClick();
        loginPage.headerTextPresent();
    }
}
