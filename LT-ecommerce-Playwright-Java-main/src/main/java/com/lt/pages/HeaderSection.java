package com.lt.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HeaderSection {

	private Page page = null;

	public HeaderSection(Page page) {
		this.page = page;
	}

	private Locator getMyAccount() {
		return this.page.locator("//a[contains(.,'My account')]").last();
	}

	private Locator AddOns() {
		return this.page.locator("#widget-navbar-217834 > ul > li:nth-child(5) > a > div > span");
	}

	private Locator AppleMobile() {
		return this.page.locator(
				"#widget-navbar-217834 > ul > li.nav-item.dropdown.dropdown-hoverable.mega-menu.position-static > a > div > span");
	}

	// -----------------------------------------------------------------------------------------------------------------------------------

	public void clickLogin() {
		this.getMyAccount().hover();
		this.page.locator("//span[text()[normalize-space()='Login']]").click();
	}

	public void clickRegister() {
		this.getMyAccount().hover();
		this.page.locator("//span[text()[normalize-space()='Register']]").click();
	}

	public void clickModules() {
		this.AddOns().hover();
		this.page.locator("#widget-navbar-217834 > ul > li:nth-child(5) > ul > li:nth-child(1) > a > div > span")
				.click();
	}

	public void clickMegaMenu() {
		this.AppleMobile().hover();
		this.page.locator("//*[@id=\"entry281_216477\"]/div/div/ul/li[1]/a").click();
	}

	public void clickBlog() {
		this.page.locator("//*[@id=\"widget-navbar-217834\"]/ul/li[3]").click();
	}

	public void clickHome() {
		this.page.locator("//*[@id=\"widget-navbar-217834\"]/ul/li[1]").click();
	}

}
