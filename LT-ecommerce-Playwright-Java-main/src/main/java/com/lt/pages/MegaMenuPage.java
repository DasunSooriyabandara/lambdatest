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
	private Locator priceDetails;
	private Locator priceDsection;
	private Locator listItems;
	private Locator minusbutton;
	private Locator plusbutton;
	private Locator newvalue1;
	private Locator newvalue2;
	private Locator addtocart;
	private Locator compairProduct;
	private Locator sizechart;
	private Locator sizeCCloseBtn;
	private Locator popup;
	private Locator popupClose;
	private Locator askQuestion;
	private Locator reviewStarts;
	private Locator reviewSubmitBtn;
	private Locator reviewWarning;
	private Locator contactusMessage;
	private Locator addtofavourite;
	private Locator descriptionsection;
	private Locator showLessBtn;
	private Locator reviewBtn;
	private Locator customBtn;
	private Locator ItemsForPage;

	public MegaMenuPage(Page page) {
		this.page = page;
		initLocators(); // Ensure locators are initialized in the constructor
	}

	private void initLocators() {
		this.topic1 = page.locator("#entry_212427 > h1");
//        this.prodCode = page.locator("#entry_216819 > span"); // Corrected locator
		this.listView = page.locator("#list-view > i");
		this.price = page.locator("#entry_216831 > div > div > h3");
		this.ShowItemCount = page.locator("#input-limit-212433");

	}

	public void productOverviewPage() {
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

		Locator items = (Locator) ShowItemCount.selectOption(new SelectOption().setLabel("15"));
		items.click();
		System.out.println("Successfullly select the items shown in a single page");

		this.ItemsForPage = page.locator("#entry_212440 > div > div.col-sm-6.text-right");
		System.out.println(ItemsForPage);

	}

	
	
	
	
	
	
	public void SizechatPopUpAskQ() {
		compairProduct.click();
		System.out.println("Click compair product button");

		Locator tonotification1 = page.locator("//*[@id=\"notification-box-top\"]");

		// Wait for the notification to appear (optional if it takes time)
		tonotification1.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("-----------" + tonotification1.textContent() + "---------------");
		// Assert that the notification is visible
		Assert.assertTrue(tonotification1.isVisible(), "Notification did not appear!");

		System.out.println("Successfully disply notification Compair this product");

		// ------------------------------------------------------------------------------------------

		sizechart.click();

		Locator sizechartavailable = page.locator("#entry_216864");

		// Wait for the notification to appear (optional if it takes time)
		sizechartavailable.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		// Assert that the notification is visible
		Assert.assertTrue(sizechartavailable.isVisible(), "sizechart did not appear!");

		sizeCCloseBtn.click();

		// ---------------------------------------------------------------------------------------------------

		popup.click();

		Locator popupavailable = page.locator("#entry_216870");

		// Wait for the notification to appear (optional if it takes time)
		popupavailable.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		// Assert that the notification is visible
		Assert.assertTrue(popupavailable.isVisible(), "popup did not appear!");

		popupClose.click();

		// ------------------------------------------------------------------------

	}

	public void ContactUsForm(String name, String password, String subject, String message) {
		askQuestion.click();

		Locator askquestion = page.locator("#mz-component-752738972 > div > div");

		// Wait for the popup to appear
		askquestion.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		// Assert that the popup is visible
		Assert.assertTrue(askquestion.isVisible(), "Popup did not appear!");
		System.out.println("Click Ask Question successfully");

		// Fill in the form fields
		page.getByLabel("Your name").nth(0).fill(name);
		page.getByLabel("Your email").fill(password);
		page.getByLabel("Subject").fill(subject);
		page.getByLabel("Message").fill(message);

		// Click the submit button
		page.locator("#entry_216873 > div > form > button").click();
		System.out.println("Successfully click the submit button");

		Locator popup = page.locator(" body > div.alert.alert-success.alert-notification.w-50.alert-dismissible");

		popup.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("Submit successfull message :" + popup);

		// Assert that the notification is visible
		Assert.assertTrue(popup.isVisible(), "toaste message did not appear!");

		// Wait for popup close button to be visible before clicking

	}

	// ----------------------Problem in this
	// section--------------------------------------------------------------------------------------------
	public void reviewSection(String name, String review) {

//			boolean isSelected = page.locator("label[for='rating-5-216860']").isChecked();
//			Assert.assertTrue(isSelected, "Radio button is not selected!");
//
//			System.out.println("Successfully select the  review stars");

		// Set a flag to track whether the alert appears
		AtomicBoolean alertAppeared = new AtomicBoolean(false);

		// Listen for the alert
		page.onceDialog(dialog -> {
			alertAppeared.set(true); // Mark that the alert appeared
			String alertText = dialog.message();
			Assert.assertEquals(alertText, "Warning: Review Text must be between 25 and 1000 characters!",
					"Warning message does not match!");
//			    dialog.accept(); // Click 'OK' on the alert
		});

		// Click the submit button (Replace locator with the actual one)

		reviewSubmitBtn.click();

		// Verify that the alert actually appeared
		Assert.assertTrue(alertAppeared.get(), "Warning alert did not appear!");

		page.getByLabel("Your Name").nth(0).fill(name);
		page.getByLabel("Your Review").fill(review);

		reviewSubmitBtn.click();

	}

	public void itemDescription() {

		// Accessing Review Section

		page.waitForSelector("#loader", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));

		page.waitForLoadState(LoadState.NETWORKIDLE);

		reviewBtn.click();

		Locator reviewSection = page.locator("#mz-design-tab-216814-2");
		reviewSection.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		Assert.assertEquals(reviewSection.textContent().trim(), "There are no reviews for this product.");
		System.out.println("✅ Review section accessed successfully");
		// Clicking on the description section
		descriptionsection.click();
		Locator descriptionSec = page.locator("#entry_216814 > div.tab-content");
		descriptionSec.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		Assert.assertTrue(descriptionSec.isVisible(), "Description section not available");
		System.out.println("✅ Item description section is available");

		// Clicking on Show Less Button
		showLessBtn.click();
		Assert.assertTrue(showLessBtn.isVisible(), "Show less button is NOT Clicked!");
		System.out.println("✅ Successfully clicked the show button");

		page.waitForTimeout(2000); // Wait for UI changes

		showLessBtn.click();
		Assert.assertTrue(showLessBtn.isVisible(), "Show Hide button is NOT Clicked!");
		System.out.println("✅ Successfully clicked the Hide button");

		// Accessing Custom Section
		customBtn.click();
		Locator customSec = page.locator("#mz-design-tab-216814-3");
		customSec.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		Assert.assertEquals(customSec.textContent().trim(),
				"Create unlimited custom tabs and add any product detail, module, widget, design or HTML. Also possible to create custom tab layout using layout builder");
		System.out.println("✅ Custom section accessed successfully");
	}

	public void FAQSection() {
		// Verifying FAQ Title
		Locator faqTitle = page.locator("#entry_216863 > h3");
		Assert.assertEquals(faqTitle.textContent().trim(), "FAQ (Frequently Asked Questions)");
		System.out.println("✅ Successfully retrieved the FAQ title");

		// Loop through FAQ questions and verify buttons
		for (int i = 0; i <= 6; i++) {
			String question = page.locator("#mz-faq-label-216863-" + i + " > span").textContent().trim();
			System.out.println("Question " + (i + 1) + ": " + question);

			Locator faqButton = page.locator("#mz-faq-label-216863-" + i + " > i");
			faqButton.click();
			Assert.assertTrue(faqButton.isVisible(), "FAQ button " + (i + 1) + " is NOT Clicked!");
			System.out.println("✅ Successfully clicked FAQ button " + (i + 1));
		}

		page.waitForTimeout(2000);

		// Back to Top Button Verification
		Locator backToTop = page.locator("#back-to-top");
		backToTop.click();
		Assert.assertTrue(backToTop.isVisible(), "Back to top button is NOT Clicked!");
		System.out.println("✅ Successfully clicked the Back to top button");
	}

}
