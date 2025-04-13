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

		this.optionOne = page.locator("(//ul[@class='dropdown-menu autocomplete w-100'])[1]/li[1]");
		this.comparetBtn = page.locator("//*[@id='entry_217823']");
		this.comparetBtnInsidePage = page.locator("//*[@id='entry_216844']//child::button");
		this.productCompareBtn = page.locator("#notification-box-top > div > div.toast-body > a");
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
		Assert.assertEquals(actualTitle, "Your Store]");

		page.goBack();

		searchCategoryBtn.click();
		page.waitForTimeout(3000);
		selectOption.click();

		searchField.type("HTC");
		optionOne.click();

		String actualTitlesearch = page.title();
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

		Locator SuccessNotificstions = page.locator("#product-compare > div.alert.alert-success.alert-dismissible");
		

		// Wait for the notification to appear (optional if it takes time)
		SuccessNotificstions.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("-----------" + SuccessNotificstions.textContent() + "---------------");
		// Assert that the notification is visible
		Assert.assertTrue(SuccessNotificstions.isVisible(), "Notification did not appear!");

		System.out.println("Successfully disply notification Compair this product");

	}
}
