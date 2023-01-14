package phonebook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import phonebook.pages.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static phonebook.pages.Page.*;

public class HeaderTest {

    //classes
    private LoginPage loginPage;
    private ContactsPage contactsPage;
    private AccountPage accountPage;

    @BeforeEach
    public void loginWithValidCredentials() {
        loginPage = open(LOGIN_URL, LoginPage.class);
        loginPage.fillInCreds(VALID_EMAIL, VALID_PASS);
        contactsPage = loginPage.loginBtnClick();
        contactsPage.profilePresent();
    }

    /**
     * This test checks if there is a transition to another page if you click on Exit Button
     */
    @Test
    public void exitBtnClick() {
        contactsPage.exitBtnClick();
        loginPage.headerTextPresent();
    }

    /**
     * This test checks if there is a transition to another page if you click on Account Button
     */
    @Test
    public void accountBtnClick() {
        accountPage = contactsPage.accountBtnClick();
        assertEquals("Учетная запись", accountPage.accountHeaderText());
    }
}
