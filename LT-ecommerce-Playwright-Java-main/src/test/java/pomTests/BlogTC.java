package pomTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lt.pages.BlogPage;
import com.lt.pages.HeaderSection;
import com.lt.pages.MegaMenuPage;
import com.lt.pages.ModulesPage;
import com.lt.pages.ProductOverviewPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;

import base.Driver;
import base.PlaywrightConnection;

public class BlogTC extends PlaywrightConnection {
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

	@Test(priority = 1, description = "Get The titile of Blog Page")
	public void openBlogPage() {
		HeaderSection headerBlog = new HeaderSection(page);

		try {
			headerBlog.clickBlog();

			String actualTitle = page.title();
			System.out.println("Actual title: " + actualTitle);
			Assert.assertEquals(actualTitle, "Blog - Poco theme");

		} catch (PlaywrightException err) {
			super.setTestStatus("failed", err.getMessage(), page);
			err.printStackTrace();
		}
	}

	@Test(priority = 2, description = "Latest Article Section")
	public void LatestArticleSection() {
		BlogPage LatestArticles = new BlogPage(page);
		LatestArticles.LatestArticle();
	}
//
//	@Test(priority = 3)
//	public void FilterSectionPriceAndSearch() {
//		MegaMenuPage FilterSection1 = new MegaMenuPage(page);
//		FilterSection1.FilterSectionPriceNSearch();
//	}
//
//	@Test(priority = 4)
//	public void FilterSectionAvailability() {
//		MegaMenuPage FilterSection2 = new MegaMenuPage(page);
//		FilterSection2.FilterSectionItemAvailability();
//	}
//
//	@Test(priority = 5)
//	public void ActionsItem() {
//		MegaMenuPage ItemActions = new MegaMenuPage(page);
//		ItemActions.FourActioButtons();
//	}
//
//	@Test(priority = 6, description = "Mega Menu - Apple Page - Desktop Link")
//	public void DesktopPageLink() {
//		MegaMenuPage DesktopInitials = new MegaMenuPage(page);
//		DesktopInitials.DesktopPage();
//
//	}
//
//	@Test(priority = 7, description = "Mega Menu - Apple Page - Desktop Page - PC Link  ")
//	public void DesktopPagePClink() {
//		MegaMenuPage DesktopPCPage = new MegaMenuPage(page);
//		DesktopPCPage.DesktopPageClickPCLink();
//
//	}
//	
//	@Test(priority = 8, description = "Mega Menu - Apple Page - Desktop Page - Mac Link")
//	public void DesktopPageMaclink() {
//		MegaMenuPage DesktopMacPage = new MegaMenuPage(page);
//		DesktopMacPage.DesktopPageClickMacLink();

	}


