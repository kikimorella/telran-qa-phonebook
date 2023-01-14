package phonebook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phonebook.pages.AccountPage;
import phonebook.pages.ContactsPage;
import phonebook.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static phonebook.pages.Page.*;

public class AccountTests {

    //classes
    private LoginPage loginPage;
    private ContactsPage contactsPage;
    private AccountPage accountPage;

    @BeforeEach
    public void onAccountPage() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        loginPage.fillInCreds(VALID_EMAIL, VALID_PASS);
        contactsPage = loginPage.loginBtnClick();
        contactsPage.profilePresent();
        accountPage = contactsPage.accountBtnClick();
        assertEquals("Учетная запись", accountPage.accountHeaderText());
    }

    /**
     * This test checks unsuccessful change of the password
     */
    @Test
    public void passChange() {
        accountPage.fillInPass(VALID_PASS);
        accountPage.fillInConfirmPass(VALID_PASS);
        accountPage.acceptBtnActive();
    }

    /**
     * This test checks if an error message appears if you enter password longer than 20 symbols
     */
    @Test
    public void fillInLongPass() {
        accountPage.fillInPass("password longer than 20 symbols");
        assertEquals("Пароль должен состоять не менее чем из 20 символов.", accountPage.passMaxErrorMessageGetText());
        accountPage.acceptBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter password less than 8 symbols
     */
    @Test
    public void fillInSmallPass() {
        accountPage.fillInPass("123");
        assertEquals("Пароль должен состоять не менее чем из 8 символов.", accountPage.passMinErrorMessageGetText());
        accountPage.acceptBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter no password
     */
    @Test
    public void fillInNoPass() {
        accountPage.passClick();
        accountPage.passHeaderClick();
        assertEquals("Необходимо ввести пароль", accountPage.passRequiredErrorMessageGetText());
        accountPage.acceptBtnDisabled();
    }

    /**
     * This test checks if an error message appears if you enter password but no confirmation
     */
    @Test
    public void fillInPassNoConfirm() {
        accountPage.fillInFakePass();
        accountPage.confirmPassClick();
        accountPage.passHeaderClick();
        accountPage.acceptBtnDisabled();
        assertEquals("Пароли не соответствуютю", accountPage.confirmPassErrorMessageGetText());
    }
}
