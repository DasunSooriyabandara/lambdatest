package com.lt.pages;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class HomePage {
	private Page page;
	private Locator searchCategoryBtn;
	private Locator selectOption;
	private Locator searchField;
	private Locator searchBtn;
	private Locator optionOne;
	private Locator comparetBtn;
	private Locator comparetBtnInsidePage;
	private Locator productCompareBtn;
	private Locator carroselArrow;
	private Locator carroselImage;
	private Browser browser;

	public HomePage(Page page, Browser browser) {
		this.page = page;
		this.browser = browser; // Save it for later
		page.waitForLoadState(LoadState.NETWORKIDLE);
		initLocators();
	}

	private void initLocators() {
		this.searchCategoryBtn = page.locator("(//button[@type='button'])[1]");
		this.selectOption = page.locator("(//a[@class='dropdown-item'])[3]");
		this.searchField = page.locator("(//*[@name='search'])[1]");
		this.searchBtn = page.locator("(//button[@type='submit'])[1]");
		this.comparetBtn = page.locator("//*[@id='entry_217823']");

		
		
		this.optionOne = page.locator("//*[@id=\"search\"]/div[1]/div[2]/ul/li[1]/div[1]/a");
		
		this.comparetBtnInsidePage = page.locator("//*[@id='entry_216844']//child::button");
		this.productCompareBtn = page.locator("#notification-box-top > div > div.toast-body > a");
		
		
		this.carroselArrow = page.locator("(//*[@class='carousel-control-next'])[1]");
		this.carroselImage = page.locator("(//div[@class='carousel-item active'])[1]");
		//this.carroselArrow = page.locator("(//div[starts-with(@class,'carousel-item')])[1]");
				
	}

	public void searchFunction() {
		String title = page.title();
		System.out.println("Page Title: " + title);
		Assert.assertEquals(title, "Your Store");

		searchCategoryBtn.click();
		page.waitForTimeout(3000);
		selectOption.click();

		searchField.type("HTC Touch HD");
		searchBtn.click();

		String actualTitle = page.title();
		System.out.println("Actual title: " + actualTitle);
		Assert.assertEquals(actualTitle, "Search - HTC Touch HD");

		page.waitForTimeout(3000);
		page.goBack();

		
//		comparetBtn.click();
//		Locator NoProd = page.locator("//p[text()='You have not chosen any products to compare.']");
//		NoProd.textContent().trim();
//		Assert.assertEquals(NoProd, "Locator@//p[text()='You have not chosen any products to compare.");
//		Locator ContinueBtn = page.locator("//*[@id=\"content\"]/div/div/a");
//		ContinueBtn.click();
		
		
		searchCategoryBtn.click();
		selectOption.click();

		searchField.type("HTC");
		optionOne.click();
		System.out.println("clikc option one");

		String actualTitlesearch = page.title();
		//actualTitlesearch.wait(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		Assert.assertEquals(actualTitlesearch, "HTC Touch HD");

	}

	public void Comprison() {
		comparetBtnInsidePage.click();
		Locator tonotification1 = page.locator("//*[@id=\"notification-box-top\"]");

		// Wait for the notification to appear (optional if it takes time)
		tonotification1.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("-----------" + tonotification1.textContent() + "---------------");
		// Assert that the notification is visible
		Assert.assertTrue(tonotification1.isVisible(), "Notification did not appear!");

		productCompareBtn.click();
		System.out.println("Successfully disply notification........ Compair this product");

		String actualTitleProdCompare = page.title();
		System.out.println("Actual title: " + actualTitleProdCompare);
		Assert.assertEquals(actualTitleProdCompare, "Product Comparison");

		
		Locator RemoveBtn = page.locator("//*[@id=\"content\"]/table/tbody[2]/tr/td[2]/a");
		RemoveBtn.click();
		page.waitForTimeout(3000);
		
		
		Locator SuccessNotificstions = page.locator("#product-compare > div.alert.alert-success.alert-dismissible");
		// Wait for the notification to appear (optional if it takes time)
		SuccessNotificstions.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("-----------" + SuccessNotificstions.textContent() + "---------------");
		// Assert that the notification is visible
		Assert.assertTrue(SuccessNotificstions.isVisible(), "Notification did not appear!");

		System.out.println("Successfully disply notification Compair this product");
		
		
		Locator ContinueBtn = page.locator("//*[@id=\"content\"]/div/div/a");
		ContinueBtn.click();
		System.out.println("SUccesssfully click the continue buttona and come to Home page");
		

	}
	
	public void BannerOptions() {

		// 1. Locate the carousel image (you can use a specific one if needed)
		Locator carroselImage = page.locator("(//div[starts-with(@class,'carousel-item')])[1]");

		// 2. Wait until the image is visible
		carroselImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		// 3. Extract the image src attribute
		String carroselimageURL = carroselImage.getAttribute("src");
		System.out.println("Image URL: " + carroselimageURL);

		Browser browser = null;
		// 4. Use browser to create a brand new context (isolated tab)
		BrowserContext newContext = browser.newContext();  // <== browser should be passed to the class
		Page newPage = newContext.newPage();

		// 5. Navigate to the extracted URL
		newPage.navigate(carroselimageURL);
		System.out.println("Opened URL in new tab: " + newPage.url());

		// 6. Interact back on original page if needed
		carroselImage.click();

		String actualTitle = page.title();
		System.out.println("Actual title: " + actualTitle);
		Assert.assertEquals(actualTitle, "iPhone");

		page.goBack();
	}

	
	public void BannerImages() {

		// 1. Locate the banner image
		Locator bannerImage = page.locator("//*[@title='Lumix S Series From Panasonic']");

		// 2. Get the image source URL
		String imageURL = bannerImage.getAttribute("src");
		System.out.println("Image URL: " + imageURL);

		Browser browser = null;
		// 3. Use browser to create a brand new context (like a fresh incognito session)
		BrowserContext newContext = browser.newContext();

		// 4. Open a new page in that context
		Page newPage = newContext.newPage();

		// 5. Navigate to the image URL
		newPage.navigate(imageURL);

		// 6. Interact if needed
		System.out.println("Opened image in new tab: " + newPage.url());
	}

}
