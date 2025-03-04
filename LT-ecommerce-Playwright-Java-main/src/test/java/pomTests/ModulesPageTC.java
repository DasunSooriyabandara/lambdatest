package pomTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lt.pages.HeaderSection;
import com.lt.pages.ModulesPage;
import com.lt.pages.ProductOverviewPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;

import base.Driver;
import base.PlaywrightConnection;

public class ModulesPageTC extends PlaywrightConnection {
    private Driver driver;
    private Page page;

    @BeforeClass
    public void setUp() throws Exception {
        driver = super.createConnection();
        page = driver.getPage(); // Initialize page here

        // Navigate to the required URL once before all tests
        page.navigate("https://ecommerce-playground.lambdatest.io/");
        page.waitForLoadState(LoadState.LOAD);
        System.out.println("Navigated to: " + page.url());
    }

    @AfterClass
    public void tearDown() {
        super.closeConnection(driver);
    }

    @Test(priority = 1)
    public void openModulesPage() {
        HeaderSection header1 = new HeaderSection(page);

        try {
            header1.clickModules();

            String actualTitle = page.title();
            System.out.println("Actual title: " + actualTitle);
            Assert.assertEquals(actualTitle, "Available Modules");

        } catch (PlaywrightException err) {
            super.setTestStatus("failed", err.getMessage(), page);
            err.printStackTrace();
        }
    }

    @Test(priority = 2)
    public void productListing() {
        ModulesPage modPage = new ModulesPage(page);
        modPage.productListingSection();
    }
    
    
    
    @Test(priority = 4)
    public void productOverviewPageActions() {
    	ProductOverviewPage povp = new ProductOverviewPage(page);
    	povp.productOverviewPage1();
    }
    
    @Test(priority = 5)
    public void ContactUsFormActions() {
    	ProductOverviewPage ContactUs = new ProductOverviewPage(page);
    	ContactUs.ContactUsForm("Dasun", "Dasun@gmail.com", "Test Subject", "This a dummy message for Message section");
    }
    
    @Test
    public void WriteReviewSection() {
    	ProductOverviewPage Review = new ProductOverviewPage(page);
    	Review.reviewSection();
    }
    
}
