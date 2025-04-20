package pomTests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lt.pages.BlogPage;
import com.lt.pages.HeaderSection;
import com.lt.pages.HomePage;
import com.lt.pages.MegaMenuPage;
import com.lt.pages.ModulesPage;
import com.lt.pages.ProductOverviewPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.LoadState;

import base.Driver;
import base.PlaywrightConnection;

public class HomePageTC extends PlaywrightConnection {
	private Driver driver;
	private Page page;
	
	HeaderSection headerSection;
	HomePage homePage;
	HomePage compareProds;
	HomePage bannerImage;
	HomePage twoImages;
	HomePage topTrending;
	HomePage TopProducts;

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

	@Test(priority = 1, description = "Get The titile of Home Page")
	public void openHomePage() {
		headerSection = new HeaderSection(page);

		try {
			headerSection.clickHome();

			String actualTitle = page.title();
			System.out.println("Actual title: " + actualTitle);
			Assert.assertEquals(actualTitle, "Your Store");

		} catch (PlaywrightException err) {
			super.setTestStatus("failed", err.getMessage(), page);
			err.printStackTrace();
		}
	}

	@Test(priority = 2, description = "Search Bar actions")
	public void SearchBarActions() {
		homePage = new HomePage(page, null);
		homePage.searchFunction();
	}
	
	@Test(priority = 3, description = "Compare products")
	public void ProductCompare() {
		
		compareProds = new HomePage(page, null);
		compareProds.Comprison();
	}
	
	@Test(priority = 4, description = "Banner options")
	public void BannerImageOpenNewTab() {
		
		bannerImage  = new HomePage(page, null);
		bannerImage.BannerOptions();
	}

	@Test(priority = 5, description = "Two images upper top trending catagories")
	public void TwoImages() {
		
		twoImages = new HomePage(page, null);
		twoImages.BannerImages();
	}

	@Test(priority = 4, description = "Top Trending Category ")
	public void TopTrendCategorySection() {
		topTrending  = new HomePage(page, null);
		topTrending.TopTrendCategory();
	}

	@Test(priority = 5, description = "Top Products Section Carrosel ")
	public void TopProductsCarosel() {
		TopProducts  = new HomePage(page, null);
		TopProducts.TopProductsSection();
	}
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


