package ui_tests;

import data_providers.UserDataProvider;
import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.TestNGListener;
import utils.UserFactory;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;

import java.util.Random;

@Listeners(TestNGListener.class)

public class RegistrationTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void goToRegistrationLoginPage() {
        logger.info("Start registration test");
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }

    @Test(groups = "smoke")
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties", "email_for_registration"))
                .password(getProperty("base.properties", "password_for_registration"))
                .build();
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));
    }
//    @Test
//    public void testMethod(){
//        new HomePage(getDriver()).method();
//    }
//    @Test
//    public void testAjaxMethod() {
//        new HomePage(getDriver()).ajaxMethod();
//    }

    @Test
    public void registrationPositiveWithFakerTest() {
        UserLombok user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));


    }

    @Test
    public void registrationNegativeEmptyAllFieldsTest() {
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationNegativeEmptyEmailFieldTest() {
        UserLombok user = positiveUser();
        user.setUsername("");
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test
    public void registrationNegativeEmptyPasswordFieldTest() {
        UserLombok user = positiveUser();
        user.setPassword("");
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }


    @Test(dataProvider = "dataProviderWrongPasswordForRegistration", dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongPasswordTest(UserLombok user) {
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }

    @Test(dataProvider = "dataProviderWrongEmailForRegistration", dataProviderClass = UserDataProvider.class)
    public void registrationNegativeWrongEmailTest(UserLombok user) {
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }


}
