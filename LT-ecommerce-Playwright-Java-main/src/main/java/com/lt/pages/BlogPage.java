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

public class BlogPage {
	private Page page;
	private Locator topic1;
	private Locator rightArrow;
	private Locator leftArrow;
	private Locator topic2;
	private Locator rightArrow2;
	private Locator leftArrow2;
	private Locator BusinessLink;
	private Locator topic3;
	private Locator BusinessArticledetails;
	private Locator readMore;
	private Locator article2heading;
	private Locator BusinessArticledetailsMetadata;
	private Locator BusinessArticledetailsTitle;
	private Locator BusinessArticledetailsMetadataRM;
	private Locator BusinessArticledetailsTitleRM;
	private Locator postCommentBtn;
	private Locator plpostCommentBtnusbuttonh;
	private Locator plpostCommentBhtnusbutton;
	private Locator plpostCommedhntBtnusbutton;
	private Locator plpostCommehntBtnusbutton;
	private Locator plpostCommenhtBtnusbutton;

	public BlogPage(Page page) {
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
		this.rightArrow2 = page.locator(
				"#mz-article-listing-77210961 > div.mz-tab-listing-header.d-flex > div > div > a.mz-swiper-nav-next");
		this.leftArrow2 = page.locator(
				"#mz-article-listing-77210961 > div.mz-tab-listing-header.d-flex > div > div > a.mz-swiper-nav-prev");
		this.BusinessLink = page.locator("#entry_210963 > div > a:nth-child(1)");
		this.topic3 = page.locator("#entry_210943");
		this.BusinessArticledetailsMetadata = page
				.locator("#entry_210951 > div > div:nth-child(2) > div > div.caption > div");
		this.BusinessArticledetailsTitle = page
				.locator("#entry_210951 > div > div:nth-child(2) > div > div.caption > h4");

		this.readMore = page.locator("#entry_210951 > div > div:nth-child(2) > div > div.caption > a");
		this.article2heading = page.locator("#entry_210903");
		this.BusinessArticledetailsMetadataRM = page
				.locator("#entry_210903");
		this.BusinessArticledetailsTitleRM = page
				.locator("#entry_210904");
		this.postCommentBtn= page.locator("#entry_210904");
//		this.Search = page.locator("#mz-filter-panel-0-1 > div > input");

	}

	public void LatestArticle() {
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

	public void MostViewedArticle() {
		// Assert product title
		String sectionTitle2 = topic2.textContent().trim();
		System.out.println("Section Title: " + sectionTitle2);
		Assert.assertEquals(sectionTitle2, "Most viewed");

		rightArrow2.click();
		page.waitForTimeout(3000);
		rightArrow2.click();
		System.out.println("Successfully Scrool the page to fight");
		page.waitForTimeout(3000);
		leftArrow2.click();
		System.out.println("Successfully Scrool the page to left");

		Locator articleNames2 = page.locator("#mz-article-listing-77210961 > div.mz-tab-listing-content.clearfix");

		// Wait for at least one item to appear
		articleNames2.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		// Get the total count of listed items
		int counAName = articleNames2.count();
		System.out.println("Total Items Found: " + counAName);

		// Print the item titles (or fewer if less than 4 exist)
		for (int M = 0; M < Math.min(10, counAName); M++) {
			System.out.println("Item " + (M + 1) + " Title: " + articleNames2.nth(M).textContent().trim());

		}

	}

	public void BusinessArticle() {

		BusinessLink.click();
		String sectionTitle3 = topic3.textContent().trim();
		System.out.println("Section Title: " + sectionTitle3);
		Assert.assertEquals(sectionTitle3, "Business");

		Locator ReadMore = page.locator("#entry_210945 > div > div");
		ReadMore.click();
		System.out.println("Click Read MOre Button Successfully");

		String article2Details = BusinessArticledetailsMetadata.textContent().trim();
		String article2Details1 = BusinessArticledetailsTitle.textContent().trim();
		System.out.println("MetaData list view :" + article2Details);
		System.out.println("Title list view :" + article2Details1);

		readMore.click();
		String BsectionTitle1 = article2heading.textContent().trim();
		System.out.println("Section Title: " + BsectionTitle1);
		Assert.assertEquals(BsectionTitle1, "eget nunc lobortis mattis aliquam faucibus purus in massa tempor");

		
		String article2DetailsRM = BusinessArticledetailsMetadataRM.textContent().trim();
		String article2Details1RM = BusinessArticledetailsTitleRM.textContent().trim();
		System.out.println("MetaData Read More view :" + article2DetailsRM);
		System.out.println("Title Read More view :" + article2Details1RM);

		Assert.assertEquals(article2Details, article2DetailsRM);
		System.out.println("Meta Data Equals");
		Assert.assertEquals(article2Details1, article2Details1RM);
		System.out.println("Title Equals");
		
		System.out.println("Successfully open read more right article");
	}
	
	public void WriteComment() {
		
		postCommentBtn.click();

		boolean warning1 = page.locator("#form-comment > div:nth-child(1) > div").isVisible();
		boolean warning2 = page.locator("#form-comment > div:nth-child(3) > div").isVisible();

		if (warning1 && warning2) {  // Corrected condition using &&
			System.out.println("passed" + "Warning is visible");
		} else {
			System.out.println("failed" +  "Warning is not visible");
		}

	}
}
