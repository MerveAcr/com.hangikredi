package com.interview.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreditSearchEngin {
	WebDriver driver;

	public CreditSearchEngin(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(id = "amount")
	public WebElement krediTutariBox;

	@FindBy(id = "maturity")
	public WebElement krediVadesiBox;

	@FindBy(id = "CalculateCta")
	public WebElement CalculateButton;
}
