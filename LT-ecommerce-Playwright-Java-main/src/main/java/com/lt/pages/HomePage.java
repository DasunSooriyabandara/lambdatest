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
	private Locator topTrendTopic;
	private Locator swipBtnNext;
	private Locator swipBtnPrew;
	private Locator camersLink;
	private Locator topProducts;
	private Locator nextArrowTP;
	private Locator productTP;
	private Locator addToCart;
	private Locator successNoti;

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
		this.topTrendTopic = page.locator("//*[@id='entry_217969']");
		this.swipBtnNext = page.locator("(//a[@class='swiper-button-next'])[1]");
		this.swipBtnPrew = page.locator("(//a[@class='swiper-button-prev'])[1]");
		this.camersLink = page.locator("//*[@aria-label='7 / 8']//child::a");
		this.camersLink = page.locator("//*[@id='entry_217978']");
		this.nextArrowTP = page.locator("(//*[@class='swiper-button-next'])[1]");
		this.productTP = page.locator("//a[@id='mz-product-listing-image-37217979-0-4']");
		this.addToCart = page.locator("(//*[@title='Add to Cart'])[5]");
		this.topProducts = page.locator("//*[@id='entry_217978']");
		this.successNoti = page.locator("//*[@id='notification-box-top']");
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
	    // Locate the actual <img> inside the active carousel item
	    Locator carroselImage = page.locator("(//div[starts-with(@class,'carousel-item') and contains(@class, 'active')]//img)[1]");

	    // Wait until the image is visible
	    carroselImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

	    // Extract the image src attribute
	    String carroselimageURL = carroselImage.getAttribute("src");
	    System.out.println("Image URL: " + carroselimageURL);

	    // Ensure the URL is not null
	    Assert.assertNotNull(carroselimageURL, "Image URL is null");

	    // Open a new browser context
//	    BrowserContext newContext = browser.newContext();
//	    Page newPage = newContext.newPage();
//
//	    // Navigate to the extracted image URL
//	    newPage.navigate(carroselimageURL);
	    System.out.println("Opened URL in new tab: " + carroselimageURL);

	    // Go back and verify title
	    carroselImage.click();

	    String actualTitle = page.title();
	    System.out.println("Actual title: " + actualTitle);
	    Assert.assertEquals(actualTitle, "iPhone");

	    page.goBack();
	}


	
	public void BannerImages() {
	    // Locate the <img> tag inside the banner
	    Locator bannerImage = page.locator("//*[@title='Lumix S Series From Panasonic']//img");

	    bannerImage.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

	    String imageURL = bannerImage.getAttribute("src");
	    System.out.println("Image URL: " + imageURL);

	    if (imageURL != null) {
	    	BrowserContext newContext = page.context().browser().newContext();
	        Page newPage = newContext.newPage();
	        newPage.navigate(imageURL);
	        System.out.println("Opened image in new tab: " + newPage.url());
	    } else {
	        System.out.println("Failed to extract image URL.");
	    }
	    
	    String actualTitle = page.title();
	    System.out.println("Actual title: " + actualTitle);
	    Assert.assertEquals(actualTitle, "middle-banner-1-580x234.webp (580×234)");
	    
	    
	}

	public void TopTrendCategory() {
		
		String Topic = topTrendTopic.textContent().trim();
		Assert.assertEquals(Topic, "Top Trending Categories");
		
		swipBtnNext.click();
		swipBtnPrew.click();
		swipBtnNext.click();
		

		 

		 camersLink.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		    String imageURL = camersLink.getAttribute("href");
		    System.out.println("Image URL: " + imageURL);

		    if (imageURL != null) {
		    	BrowserContext newContext = page.context().browser().newContext();
		        Page newPage = newContext.newPage();
		        newPage.navigate(imageURL);
		        System.out.println("Opened image in new tab: " + newPage.url());
		    } else {
		        System.out.println("Failed to extract image URL.");
		    }
		    
		    String actualTitle = page.title();
		    System.out.println("Actual title: " + actualTitle);
		    Assert.assertEquals(actualTitle, "Cameras");
		    
	}

	public void TopProductsSection() {
		
		String TopicTP = topProducts.textContent().trim();
		Assert.assertEquals(TopicTP, "Top Products");
		
		nextArrowTP.click();
		System.out.println("Click right arrow");
		
		productTP.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		
		productTP.hover();
		addToCart.click();
		
		Boolean NotiAvailbility = successNoti.isVisible();
		if(NotiAvailbility) {
			System.out.println("OPen Addto Cart Notification Successfully");
		}else {
			System.out.println("No Notification Opens for click Add To Cart");
		}
		
		Assert.assertTrue(successNoti.isVisible(), "Add to Cart notification should be visible.");

		
		
		productTP.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

	    String imageURL = productTP.getAttribute("href");
	    System.out.println("Image URL: " + imageURL);

	    if (imageURL != null) {
	    	BrowserContext newContext = page.context().browser().newContext();
	        Page newPage = newContext.newPage();
	        newPage.navigate(imageURL);
	        System.out.println("Opened image in new tab: " + newPage.url());
	    } else {
	        System.out.println("Failed to extract image URL.");
	    }
	    
	    String actualTitle = page.title();
	    System.out.println("Actual title: " + actualTitle);
	    Assert.assertEquals(actualTitle, " HTC Touch HD");
	}

}
