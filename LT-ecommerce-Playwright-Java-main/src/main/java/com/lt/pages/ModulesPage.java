package com.lt.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;

public class ModulesPage {
    private Page page;
    private Locator topic1;
    private Locator arrow;
    private Locator image;
    private Locator ATC;
    private Locator imaged;
    
    
    public ModulesPage(Page page) {
        this.page = page;
        initLocators(); // Ensure locators are initialized in the constructor
    }

    private void initLocators() {
        this.topic1 = page.locator("#entry_212970 > h2");
        this.arrow = page.locator("#mz-product-tab-37212971-0 > div > div.swiper-pager.custom-pager > a.swiper-button-next > div > svg > use");
        this.image = page.locator("#mz-product-listing-image-37212971-0-4 > div > div.carousel-item.active");
        this.ATC = page.locator("#swiper-wrapper-f9e4ced657a345af > div:nth-child(5) > div > div.product-thumb-top > div.product-action > button.btn.btn-cart.cart-103 > i");
        this.imaged = page.locator ("#mz-product-listing-image-37212971-0-4 > div > div.carousel-item.active"); 
        
//       private Locator AddtoCart() {
//            return page.locator("//span[normalize-space()='Add']");
//        }
        
    }

    public void productListingSection() {
        // Get and print the text of topic1
        String sectionTitle = topic1.textContent();
        System.out.println("Section Title: " + sectionTitle);

        // Assert the section title
        Assert.assertEquals(sectionTitle.trim(), "Product Listing");

        // Click the arrow and assert it is visible
        arrow.click();
        System.out.println("Arrow click Successful");
        PlaywrightAssertions.assertThat(arrow).isVisible();

        // Hover over the image and assert visibility
        image.hover();
        PlaywrightAssertions.assertThat(image).isVisible();
        System.out.println("Image hover Successful");
        
        imaged.click();
        
        System.out.println("SUccessfully open a item Page");
        
    }
    
    public void productListingHoverActionATC() {
    	ATC.click();
    	System.out.println("Click AddtoCart Button Successful");
    	
    }
}
