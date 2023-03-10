package com.interview.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interview.utilities.Driver;

public class CreditSearchEngin {

	public CreditSearchEngin() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(id = "amount")
	public WebElement krediTutariBox;

	@FindBy(id = "maturity")
	public WebElement krediVadesiBox;

	@FindBy(id = "CalculateCta")
	public WebElement CalculateButton;
}
