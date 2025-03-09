package com.lt.pages;

import com.microsoft.playwright.Locator;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ProductOverviewPage {
	private Page page;
	private Locator topic1;
	private Locator prodCode;
	private Locator details;
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

	public ProductOverviewPage(Page page) {
		this.page = page;
		initLocators(); // Ensure locators are initialized in the constructor
	}

	private void initLocators() {
		this.topic1 = page.locator("//*[@id=\"entry_216816\"]/h1");
//        this.prodCode = page.locator("#entry_216819 > span"); // Corrected locator
		this.details = page.locator("\"[data-id='216826']\"");
		this.price = page.locator("#entry_216831 > div > div > h3");
		this.priceDetails = page.locator("//*[@id=\"entry_216831\"]/div/div/span/i");
		this.priceDsection = page.locator("#popover260040 > div.popover-body");
		this.minusbutton = page.locator("//*[@id=\"entry_216841\"]/div/div[1]/button");
		this.newvalue1 = page.locator("#entry_216841 > div > input");
		this.plusbutton = page.locator("//*[@id=\"entry_216841\"]/div/div[1]/button");
		this.addtocart = page.locator("#entry_216842 > button");
		this.compairProduct = page.locator("//button[normalize-space()='Compare this Product']");
		this.sizechart = page.locator("#entry_216848 > a");
		this.sizeCCloseBtn = page.locator("#mz-component-78181048 > div > div > button");
		this.popup = page.locator("#entry_216849 > a");
		this.popupClose = page.locator("#mz-component-983507417 > div > div > button");
		this.askQuestion = page.locator("#entry_216850 > a");
//		this.reviewStarts = page.locator("//*[@id=\"form-review\"]/div[1]/span/label[3]");
		this.reviewSubmitBtn = page.locator("#form-review > div.buttons.clearfix > div");
		this.reviewWarning = page.locator("#entry_216850 > a");
//		this.contactusMessage = page.locator("#entry_216850 > a");
//--------------item discription section -----------------------		
		this.addtofavourite = page.locator("#image-gallery-216811 > div.image-thumb.d-flex > button");
		this.descriptionsection = page.locator("#entry_216814 > div.clearfix > div > ul > li:nth-child(1)");
		this.showLessBtn = page.locator("#mz-design-tab-216814-0 > div > div > div > a");
		this.reviewBtn = page.locator("#entry_216814 > div.clearfix > div > ul > li:nth-child(2)");
		this.customBtn = page.locator("#entry_216814 > div.clearfix > div > ul > li:nth-child(3) > a");
	}

	public void productOverviewPage1() {
		// Assert product title
		String sectionTitle1 = topic1.textContent().trim();
		System.out.println("Section Title: " + sectionTitle1);
		Assert.assertEquals(sectionTitle1, "HTC Touch HD");

//        // Assert product code
//        String productCode = prodCode.textContent().trim();
//        System.out.println("Product Code: " + productCode);
//        Assert.assertEquals(productCode, "Product 1");

//        // Assert product details
//        String prodDetails = details.textContent();
//        System.out.println("Product Details: " + prodDetails);
//        Assert.assertTrue(prodDetails.contains("Brand:") && prodDetails.contains("Reward Points"),
//                "Product details do not contain expected values!");
//        
		// -------------------------

		Locator listItems = page.locator("[data-id='216826']"); // Select all list items

		// Get the count of list items
		int itemCount = listItems.count();
		System.out.println("Total items in the list: " + itemCount);

		// Iterate through each item and print its text
		for (int i = 0; i < itemCount; i++) {
			String text = listItems.nth(i).textContent().trim();
			System.out.println("Item " + (i + 1) + ": " + text);
		}

		// ----------------------------------

		// Assert product price
		String price1 = price.textContent().trim();
		System.out.println("Product Price: " + price1);
		Assert.assertEquals(price1, "$146.00");

		// Click price details and assert visibility
		priceDetails.click();
		System.out.println("Price details icon click successfulll");

//		Locator topic1 = page.locator("#popover422218 > div.popover-body > ul > li:nth-child(1)");
//		String priceDetail1 = topic1.textContent().trim();
//		System.out.println("detail 1 : " + priceDetail1);
//		Assert.assertEquals(priceDetail1, "Ex Tax: $120.00");
//
//		Locator topic2 = page.locator("#popover422218 > div.popover-body > ul > li:nth-child(2)");
//		String priceDetail2 = topic2.textContent().trim();
//		System.out.println("detail 2 : " + priceDetail2);
//		Assert.assertEquals(priceDetail2, "Ex Tax: $120.00");

		System.out.println("current value : 1");
		minusbutton.click();

		String newvalminus = newvalue1.textContent().trim();
		System.out.println("Value after click minues: " + newvalminus);

		page.waitForTimeout(2000);

		plusbutton.click();
		String newvalplus = newvalue1.textContent().trim();
		System.out.println("Value after click minues: " + newvalplus);

		// --------------------------------------------------------------------
		addtocart.click();
		System.out.println("Click add to cart button");

		Locator tonotification = page.locator("#notification-box-top");

		// Wait for the notification to appear (optional if it takes time)
		tonotification.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		System.out.println("-----------" + tonotification.textContent() + "---------------");
		// Assert that the notification is visible
		Assert.assertTrue(tonotification.isVisible(), "Notification did not appear!");

		System.out.println("Successfully disply notification Add to Cart");
		// ------------------------------------------------------------------------------

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
