package ui_tests;

import dto.UserLombok;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import utils.UserFactory;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;

import java.util.Random;

public class RegistrationTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationLoginPage(){
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }
    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(1000);
        UserLombok user = UserLombok.builder()
                .username(getProperty("base.properties","email"))
                .password(getProperty("base.properties","password"))
                .build();
//        LoginPage loginPage = new LoginPage(getDriver());
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
    public void registrationPositiveWithFakerTest(){
        UserLombok user = positiveUser();
        System.out.println(user);
//        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationForm(user);
        loginPage.clickBtnRegistration();
        Assert.assertTrue(new ContactsPage(getDriver())
                .validateTextInMessageNoContacts("No Contacts here!"));



    }
    @Test
    public void registrationNegativeEmptyAllFieldsTest(){
        loginPage.clickBtnRegistration();
        Assert.assertTrue(loginPage.closeAlert()
                .contains("Wrong email or password format"));
    }


}
