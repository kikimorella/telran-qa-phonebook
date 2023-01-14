package phonebook.pages;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Abstract class Page contains common locators, data and methods shared within the phonebook pages.
 */
public class Page {

    // classes
    static Faker faker = new Faker();

    // constant credentials
    public static String VALID_EMAIL = "test@gmail.com";
    public static String VALID_PASS = "test@gmail.com";

    // constant url
    public static String LOGIN_URL = "http://phonebook.telran-edu.de:8080/user/login";
    public static String REGISTRATION_URL = "http://phonebook.telran-edu.de:8080/user/registration";
    public static String FORGOT_PASSWORD_URL = "http://phonebook.telran-edu.de:8080/user/forgot-password";

    //common error message texts
    public static String NO_EMAIL = "Email is required.";
    public static String WRONG_EMAIL = "Email must be a valid email address.";
    public static String NO_PASS = "Password is required.";
    public static String SMALL_PASS = "Password must be at least 8 characters.";
    public static String LONG_PASS = "Password must be no longer than 20 characters.";

    // Common header locators
    public static final By exitBtn = By.xpath("//nav/div/div/button[2]");
    public static final By accountBtn = By.xpath("//nav/div/div/button[1]");
    public static final By languageSelect = By.xpath("//nav/div/div/select");
    public static final By newContactBtn = By.xpath("//nav/div/ul/li[2]");
    public static final By contactsBtn = By.xpath("//nav/div/ul/li[1]");
    public static final By headerText = By.xpath("//h3");

    // methods
    public LoginPage exitBtnClick() {
        $(exitBtn).click();
        return page(LoginPage.class);
    }

    public void headerTextPresent() {
        $(headerText).shouldHave(Condition.visible);
    }

    public AccountPage accountBtnClick() {
        $(accountBtn).click();
        return page(AccountPage.class);
    }
}
