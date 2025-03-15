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
	private Locator PriceRangeFrom;
	private Locator Search;
	private Locator plusbutton;
	private Locator CheckBox1;
	private Locator CheckBox1Values;
	private Locator CheckBox2Values;
	private Locator CheckBox3Values;
	private Locator addtocart;
	private Locator ViewCart;

	private Locator addtoWishlist;
	private Locator quicView;
	private Locator quicViewHeading;
	private Locator compareProduct;
	private Locator desktopLink;

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
		this.PriceRangeFrom = page
				.locator("#mz-filter-panel-0-0 > div > div.d-flex.align-items-center > input:nth-child(1)");
		this.Search = page.locator("#mz-filter-panel-0-1 > div > input");
		this.CheckBox1 = page.locator("input[type='checkbox'][id='mz-fss-0--1']");
		this.CheckBox1Values = page.locator("#mz-filter-panel-0-3 > div > div:nth-child(1)");
		this.CheckBox2Values = page.locator("#mz-filter-panel-0-3 > div > div:nth-child(2)");
		this.CheckBox3Values = page.locator("#mz-filter-panel-0-3 > div > div:nth-child(3)");
		this.addtocart = page.locator("//button[@class='btn btn-cart cart-34']//span[contains(text(),'Add to Cart')]");
		this.ViewCart = page.locator("#notification-box-top > div > div.toast-body > div.form-row > div:nth-child(1)");
		this.addtoWishlist = page.locator(
				"#entry_212439 > div > div:nth-child(1) > div > div.caption > div.product-action > button.btn.btn-wishlist.wishlist-34");
		this.quicView = page.locator(
				"#entry_212439 > div > div:nth-child(1) > div > div.caption > div.product-action > button.btn.btn-quick-view.quick-view-34");
		this.quicViewHeading = page.locator("#entry_212948"); 
		this.compareProduct = page.locator(
				"#entry_212439 > div > div:nth-child(1) > div > div.caption > div.product-action > button.btn.btn-compare.compare-34");

		//----Desktop Page---------
		this.desktopLink.locator("#entry_212443 > div > a:nth-child(1)");
		
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

		// ------------------------------------Sortby------------------------------------------------------
		page.goBack();

		System.out.println("Successfully Come back to Iphone Page");

		page.locator("#input-sort-212434").selectOption(new SelectOption().setIndex(1));

		String ItemOneName = SortItemName1.textContent().trim();
		Assert.assertEquals(ItemOneName, "iPod Touch");

		System.out.println("Successfully select the sort by Best Seller");

	}
		 public void FilterSectionPriceNSearch() {
		// ----------------------------FIlters----------------------

		PriceRangeFrom.clear();
		PriceRangeFrom.fill("100");

		page.waitForTimeout(5000);

		Search.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		Search.fill("iPod Shuffle");

		// Define the locator
		Locator searchItemTitleLocator = page.locator("//*[@id=\"entry_212439\"]/div/div[1]/div/div[2]/h4");

		// Wait for the element to be visible
		searchItemTitleLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		// Get the text content
		String searchItemTitle = searchItemTitleLocator.textContent().trim();

		// Assert that the notification is visible
		Assert.assertEquals(searchItemTitle, "iPod Touch");

		System.out.println("Successfully searched item displayed.");
		
		 }
		 
		 
		 public void FilterSectionItemAvailability() {
		 
		// --------Filters- Availability -----------In Stock ------------

		Locator label = page.locator("label[for='mz-fss-0--1']");
		label.click();
//		CheckBox1.check();
		String CheckboxValueCount1 = CheckBox1Values.textContent().trim();
		System.out.println("Availability Value In Stock: " + CheckboxValueCount1);

		String enteredNumber1 = "42";

		String retrievedText1 = page.locator("#entry_212440 > div > div.col-sm-6.text-right").textContent().trim();
		System.out.println("Total items In Stock:" + retrievedText1);

		// Check if the entered number is included in the retrieved text
		if (retrievedText1.contains(enteredNumber1)) {
			System.out.println("✅ The entered number " + enteredNumber1 + " is found in the text: " + retrievedText1);
		} else {
			System.out
					.println("❌ The entered number " + enteredNumber1 + " is NOT found in the text: " + retrievedText1);
		}

		// --------Filters- Availability -----------Out of Stock ------------

		Locator label2 = page.locator("label[for='mz-fss-0-5']");
		label2.click();
//				CheckBox1.check();
		String CheckboxValueCount2 = CheckBox2Values.textContent().trim();
		System.out.println("Availability Value In Stock: " + CheckboxValueCount2);

		String enteredNumber2 = "41";

		String retrievedText2 = page.locator("#entry_212440 > div > div.col-sm-6.text-right").textContent().trim();
		System.out.println("Total items InStock+Out of Stock:" + retrievedText2);

		// Check if the entered number is included in the retrieved text
		if (retrievedText2.contains(enteredNumber2)) {
			System.out.println("✅ The entered number " + enteredNumber2 + " is found in the text: " + retrievedText2);
		} else {
			System.out
					.println("❌ The entered number " + enteredNumber2 + " is NOT found in the text: " + retrievedText2);
		}

		// --------Filters- Availability -----------2-3 days ------------

		Locator label3 = page.locator("label[for='mz-fss-0-6']");
		label3.click();
//				CheckBox1.check();
		String CheckboxValueCount3 = CheckBox3Values.textContent().trim();
		System.out.println("Availability Value In Stock: " + CheckboxValueCount3);

		String enteredNumber3 = "42";

		String retrievedText3 = page.locator("#entry_212440 > div > div.col-sm-6.text-right").textContent().trim();
		System.out.println("Total items InStock+Out of Stock:" + retrievedText3);

		// Check if the entered number is included in the retrieved text
		if (retrievedText3.contains(enteredNumber3)) {
			System.out.println("✅ The entered number " + enteredNumber3 + " is found in the text: " + retrievedText3);
		} else {
			System.out
					.println("❌ The entered number " + enteredNumber3 + " is NOT found in the text: " + retrievedText3);
		}

		 }
		 
		 
		 
		 public void FourActioButtons() {
		// ------------------------------ 1 st item actions- add to cart----------------

		addtocart.click();
		System.out.println("Click add to cart button");

		Locator tonotification = page.locator("#notification-box-top");

		// Wait for the notification to appear (optional if it takes time)
		tonotification.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("-----------" + tonotification.textContent() + "---------------");
		// Assert that the notification is visible
		Assert.assertTrue(tonotification.isVisible(), "Notification did not appear!");

		System.out.println("Successfully disply notification Add to Cart");

		ViewCart.click();
		System.out.println("Successfully Click View Cart Butoon on add to cart Notification");
		
		String actualTitleViewCartPage = page.title();
		System.out.println("Actual title: " + actualTitleViewCartPage);
		Assert.assertEquals(actualTitleViewCartPage, "Shopping Cart");
		
		page.goBack();
		

//------------------------------ 1 st item actions- add to wish list----------------

		addtoWishlist.click();
		System.out.println("Click add to ish List button");

		Locator tonotification2 = page.locator("#notification-box-top");

		// Wait for the notification to appear (optional if it takes time)
		tonotification2.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("-----------" + tonotification2.textContent() + "---------------");
		// Assert that the notification is visible
		Assert.assertTrue(tonotification2.isVisible(), "Notification did not appear!");

		System.out.println("Successfully disply notification Add to Wish List");

//------------------------------ 1 st item actions- add to Quic View----------------

		Locator ItemOneHeading = page.locator("#entry_212439 > div > div:nth-child(1) > div > div.caption > h4");
		String ItemOneHeading1 = ItemOneHeading.textContent().trim();
		
		quicView.click();
		System.out.println("Click Quic View button");

		Locator tonotification3 = page.locator("#notification-box-top");

		// Wait for the notification to appear (optional if it takes time)
		tonotification3.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("-----------" + tonotification3.textContent() + "---------------");
		// Assert that the notification is visible
		Assert.assertTrue(tonotification2.isVisible(), "Notification did not appear!");

		System.out.println("Successfully disply notification Quic View");

		

		String QuicViewHeadg1 = quicViewHeading.textContent().trim();
		System.out.println("Quic view Section Title: " + QuicViewHeadg1);
		Assert.assertEquals(QuicViewHeadg1, ItemOneHeading1);

		System.out.println("Succesfully Open the Quic View");

		Locator CloseQuicView = page.locator("#quick-view > div > div > button");
		CloseQuicView.click();
		System.out.println("Succesfully Close the Quic View");

//------------------------------ 1 st item actions- compare product----------------

		compareProduct.click();
		System.out.println("Click Compare product button");

		Locator tonotification4 = page.locator("#notification-box-top");

		// Wait for the notification to appear (optional if it takes time)
		tonotification4.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("-----------" + tonotification2.textContent() + "---------------");
		// Assert that the notification is visible
		Assert.assertTrue(tonotification4.isVisible(), "Notification did not appear!");

		System.out.println("Successfully disply notification Compare product");

	}
		 
		 public void DesktopPage() {
			 
			 desktopLink.click();
			
			 String actualTitleDesktopsPage = page.title();
				System.out.println("Actual title: " + actualTitleDesktopsPage);
				Assert.assertEquals(actualTitleDesktopsPage, "Shopping Cart");
				System.out.println("Successfully open Desktops Page");
		 }

}
