package phonebook.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Class contains all ContactsPage locators and methods.
 */
public class ContactsPage extends Page{
    // locators
    private static final By profile = By.id("profile");

    // methods
    public void profilePresent() {
        $(profile).shouldHave(Condition.exist);
    }
}
