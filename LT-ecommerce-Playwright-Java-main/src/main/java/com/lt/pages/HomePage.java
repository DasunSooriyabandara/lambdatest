package com.lt.pages;

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

	public HomePage(Page page) {
		this.page = page;
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
		
		
		this.carroselArrow = page.locator("(//span[@class='carousel-control-next-icon'])[1]");
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
		
		carroselArrow.click();
		
		
		// Extract the URL from the button using JavaScript
		String targetUrl = (String) page.evaluate("() => document.querySelector('button#yourButtonId').getAttribute('onclick')");
		// OR if the button triggers a link behind the scenes, get that URL instead

		// Open a new page (tab)
		Page newTab = page.context().newPage();
		newTab.navigate(targetUrl);

		// Now interact with the new tab
		System.out.println("Opened URL in new tab: " + newTab.url());

		
		carroselImage.click();
		
		
	}
}
