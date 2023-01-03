package com.interview.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar {
	WebDriver driver;

	public NavigationBar(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//nav[@class='navigation']//a[normalize-space()='Kredi']")
	public WebElement krediSection;

	@FindBy(xpath = "//nav[@class=\"navigation\"]//a[@href=\"/kredi\"]//following-sibling::ul//a")
	public List<WebElement> krediTipi;

	@FindBy(xpath = "//nav[@class=\"navigation\"]//a[normalize-space()=\"Ýhtiyaç Kredisi\"]")
	public WebElement ihtiyacKredisi;

	public List<String> getActualCreditList() {
		List<String> actualCreditSubMenu = new ArrayList();
		for (int i = 1; i < krediTipi.size(); i++) {
			actualCreditSubMenu.add(krediTipi.get(i).getText());
		}
		return actualCreditSubMenu;
	}

	public List<String> expectedKrediList = new ArrayList<String>(Arrays.asList("Ýhtiyaç Kredisi", "Konut Kredisi",
			"Taþýt Kredisi", "KOBÝ Kredisi", "Kredi Hesaplama Araçlarý"));
}
