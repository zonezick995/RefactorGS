package web.seller;

import org.apache.commons.lang.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.web.seller.login.DashboardLoginPage;
import utility.PropertiesUtils;
import utility.WebDriverManager;

/**
 * Test suite for verifying login functionality on the seller dashboard.
 */
public class DashboardLoginTest extends DashboardBaseTest {
    private DashboardLoginPage loginPage;
    private static final String langKey = PropertiesUtils.getLangKey();

    /**
     * Setup WebDriver and initialize login page before test execution.
     */
    @BeforeClass
    void setup() {
        driver = new WebDriverManager().getWebDriver();
        loginPage = new DashboardLoginPage(driver);
    }

    /**
     * Clean up resources after tests by quitting the WebDriver.
     */
    @AfterClass
    void tearDown() {
        if (driver != null) driver.quit();
    }

    /**
     * Test case LG01_LoginWithBlankFields: Verifies that error messages are displayed when username and password fields are left blank.
     */
    @Test
    public void LG01_LoginWithBlankFields() {
        loginPage.navigateToLoginPage()
                .selectDisplayLanguage(langKey)
                .inputUsername("")
                .inputPassword("")
                .verifyErrorWhenLeaveAllBlank();
    }

    /**
     * Test case LG02_LoginWithInvalidPhoneFormat: Verifies that an error is displayed when an invalid phone format is entered.
     * Checks both less than 8 and more than 15 digits.
     */
    @Test
    public void LG02_LoginWithInvalidPhoneFormat() {
        // Test with less than 8 digits
        String lessThan8Numbers = RandomStringUtils.random(7, false, true);
        loginPage.navigateToLoginPage()
                .selectDisplayLanguage(langKey)
                .inputUsername(lessThan8Numbers)
                .verifyWhenErrorInputInvalidPhoneFormat();

        // Test with more than 15 digits
        String greaterThan15Numbers = RandomStringUtils.random(16, false, true);
        loginPage.navigateToLoginPage()
                .selectDisplayLanguage(langKey)
                .inputUsername(greaterThan15Numbers)
                .verifyWhenErrorInputInvalidPhoneFormat();
    }

    /**
     * Test case LG03_LoginWithInvalidEmailFormat: Verifies that an error is displayed when an invalid email format is entered.
     */
    @Test
    public void LG03_LoginWithInvalidEmailFormat() {
        // Invalid email (missing '@' and '.')
        String notMailFormat = RandomStringUtils.random(7, true, false);
        loginPage.navigateToLoginPage()
                .selectDisplayLanguage(langKey)
                .inputUsername(notMailFormat)
                .verifyErrorWhenInputInvalidMailFormat();
    }

    /**
     * Test case LG04_LoginWithNonExistentAccount: Verifies that login fails with an error message when non-existent account credentials are used.
     * Tests both phone and email login failures.
     */
    @Test
    public void LG04_LoginWithNonExistentAccount() {
        // Test with a phone number
        loginPage.navigateToLoginPage()
                .selectDisplayLanguage(langKey)
                .inputUsername("12345678")
                .inputPassword("Abc@12345")
                .clickLoginBtn()
                .verifyLoginFailErrorWhenLoginNonExistAccount();

        // Test with an email
        loginPage.navigateToLoginPage()
                .selectDisplayLanguage(langKey)
                .inputUsername("abc@qa.team")
                .inputPassword("Abc@12345")
                .clickLoginBtn()
                .verifyLoginFailErrorWhenLoginNonExistAccount();
    }

    /**
     * Test case LG05_LoginWithValidAccount: Verifies successful login with correct account credentials.
     */
    @Test
    public void LG05_LoginWithValidAccount() {
        loginPage.navigateToLoginPage()
                .selectDisplayLanguage(langKey)
                .inputUsername(PropertiesUtils.getSellerCredentials().getUsername())
                .inputPassword(PropertiesUtils.getSellerCredentials().getPassword())
                .clickLoginBtn()
                .verifyLoginWithCorrectAccount();
    }
}