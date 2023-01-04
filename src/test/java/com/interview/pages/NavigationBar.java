package com.interview.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interview.utilities.Driver;

public class NavigationBar {

	public NavigationBar() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "//nav[@class='navigation']//a[normalize-space()='Kredi']")
	public WebElement krediSection;

	@FindBy(xpath = "//nav[@class=\"navigation\"]//a[@href=\"/kredi\"]//following-sibling::ul//a")
	public List<WebElement> krediTipi;

	@FindBy(xpath = "//nav[@class=\"navigation\"]//a[normalize-space()=\"Ýhtiyaç Kredisi\"]")
	public WebElement ihtiyacKredisi;

	public List<String> getActualCreditList() {
		List<String> actualCreditSubMenu = new ArrayList<>();
		for (int i = 1; i < krediTipi.size(); i++) {
			actualCreditSubMenu.add(krediTipi.get(i).getText());
		}
		return actualCreditSubMenu;
	}
}
