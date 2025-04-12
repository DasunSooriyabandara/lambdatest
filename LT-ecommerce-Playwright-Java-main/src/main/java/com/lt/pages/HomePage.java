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
	private Locator topic1;
	private Locator rightArrow;
	

	public HomePage(Page page) {
		this.page = page;

		// Ensure the page is fully loaded
		page.waitForLoadState(LoadState.NETWORKIDLE);

		// Wait for the topic1 element to appear
//		page.waitForSelector("#entry_212427", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));

		initLocators(); // Initialize all locators after ensuring page load
	}

	private void initLocators() {
		this.topic1 = page.locator("#mz-article-listing-76210960 > div.mz-tab-listing-header.d-flex");

		this.rightArrow = page.locator(
				"#mz-article-listing-76210960 > div.mz-tab-listing-header.d-flex > div > div > a.mz-swiper-nav-next");
		this.leftArrow = page.locator(
				"#mz-article-listing-76210960 > div.mz-tab-listing-header.d-flex > div > div > a.mz-swiper-nav-prev");

		this.topic2 = page.locator("#mz-article-listing-77210961 > div.mz-tab-listing-header.d-flex");
		

	}

	public void SearcFucntion() {
		// Assert product title
		String sectionTitle1 = topic1.textContent().trim();
		System.out.println("Section Title: " + sectionTitle1);
		Assert.assertEquals(sectionTitle1, "Latest Articles");

		rightArrow.click();
		page.waitForTimeout(3000);
		rightArrow.click();
		System.out.println("Successfully Scrool the page to fight");
		page.waitForTimeout(3000);
		leftArrow.click();
		System.out.println("Successfully Scrool the page to left");

		Locator articleNames = page.locator(".swiper-slide .title a");

		// Wait for at least one item to appear
		articleNames.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		// Get the total count of listed items
		int counAName = articleNames.count();
		System.out.println("Total Items Found: " + counAName);

		// Print the item titles (or fewer if less than 4 exist)
		for (int M = 0; M < Math.min(10, counAName); M++) {
			System.out.println("Item " + (M + 1) + " Title: " + articleNames.nth(M).textContent().trim());

		
	}

	
		
		
	}
}
