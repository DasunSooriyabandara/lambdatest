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

public class MegaMenuPage {
	private Page page;
	private Locator topic1;
	private Locator listView;
	private Locator ShowItemCount;
	private Locator price;
	private Locator ProductCompare;
	private Locator SortItemName1;
	private Locator listItems;
	private Locator minusbutton;
	private Locator plusbutton;

	public MegaMenuPage(Page page) {
		this.page = page;

		// Ensure the page is fully loaded
		page.waitForLoadState(LoadState.NETWORKIDLE);

		// Wait for the topic1 element to appear
//		page.waitForSelector("#entry_212427", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));

		initLocators(); // Initialize all locators after ensuring page load
	}

	private void initLocators() {
		this.topic1 = page.locator("[data-id=\"212427\"]");

//        this.prodCode = page.locator("#entry_216819 > span"); // Corrected locator
		this.listView = page.locator("#list-view > i");
		this.price = page.locator("#entry_216831 > div > div > h3");
		this.ShowItemCount = page.locator("#entry_212440 > div");
		this.ProductCompare = page.locator("#entry_212431");
		this.SortItemName1 = page.locator("#entry_212439 > div > div:nth-child(1) > div > div.caption > h4");

	}

	public void productOverview() {
		// Assert product title
		String sectionTitle1 = topic1.textContent().trim();
		System.out.println("Section Title: " + sectionTitle1);
		Assert.assertEquals(sectionTitle1, "Apple");

		listView.click();

		Locator ListViewSectionAvailable = page
				.locator("#entry_212439 > div > div:nth-child(1) > div > div.caption > p");

		// Wait for the notification to appear (optional if it takes time)
		ListViewSectionAvailable.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		// Assert that the notification is visible
		Assert.assertTrue(ListViewSectionAvailable.isVisible(), "popup did not appear!");

		System.out.println("successfully shift to List View");

		// ------------------------------------------------------------------------------

		page.locator("#input-limit-212433").selectOption(new SelectOption().setValue(
				"https://ecommerce-playground.lambdatest.io/index.php?route=product/manufacturer/info&manufacturer_id=8&limit=25"));

		System.out.println("ShowItemCount");

		System.out.println("Successfullly select the items shown For a single page");

//---------------------------------------ProductCompareLink ------------------------------------------
		ProductCompare.click();
		System.out.println("Click product compair link");

		String actualTitle = page.title();
		System.out.println("Actual title: " + actualTitle);
		Assert.assertEquals(actualTitle, "Product Comparison");

		System.out.println("Successfully load product compair Page");

		// ------------------------------------Sort
		// by------------------------------------------------------
		page.goBack();

		System.out.println("Successfully Come back to Iphone Page");
		
		page.locator("#input-sort-212434").selectOption(new SelectOption().setValue(
				"https://ecommerce-playground.lambdatest.io/index.php?route=product/manufacturer/info&manufacturer_id=8&sort=order_quantity&order=DESC&limit=50"));

		String ItemOneName = SortItemName1.textContent().trim();
		Assert.assertEquals(ItemOneName, "Apple Cinema 30");

		System.out.println("Successfully select the sort by section");

	}

}
