package pomTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lt.pages.HeaderSection;
import com.lt.pages.MegaMenuPage;
import com.lt.pages.ModulesPage;
import com.lt.pages.ProductOverviewPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;

import base.Driver;
import base.PlaywrightConnection;

public class MegaMenumApplePageTC extends PlaywrightConnection {
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
	public void openAppleProductPage() {
		HeaderSection header1 = new HeaderSection(page);

		try {
			header1.clickMegaMenu();

			String actualTitle = page.title();
			System.out.println("Actual title: " + actualTitle);
			Assert.assertEquals(actualTitle, "Apple");

		} catch (PlaywrightException err) {
			super.setTestStatus("failed", err.getMessage(), page);
			err.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void InitialValidations() {
		MegaMenuPage InitialDetails = new MegaMenuPage(page);
		InitialDetails.productOverview();
	}

	@Test(priority = 3)
	public void FilterSectionPriceAndSearch() {
		MegaMenuPage FilterSection1 = new MegaMenuPage(page);
		FilterSection1.FilterSectionPriceNSearch();
	}

	
	@Test(priority = 4)
	public void FilterSectionAvailability() {
		MegaMenuPage FilterSection2 = new MegaMenuPage(page);
		FilterSection2.FilterSectionItemAvailability();
	}

	@Test(priority = 5)
	public void ActionsItem() {
		MegaMenuPage ItemActions = new MegaMenuPage(page);
		ItemActions.FourActioButtons();
	}

	
	
	
	
	
}
