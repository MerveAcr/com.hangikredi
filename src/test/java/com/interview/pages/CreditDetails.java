package com.interview.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreditDetails {
	WebDriver driver;

	public CreditDetails(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "(//dt[normalize-space()='Faiz Oran�']//following-sibling::dd)[1]")
	WebElement interestRate;

	@FindBy(xpath = "(//dt[normalize-space()='Ayl�k Taksit']//following-sibling::dd)[1]")
	WebElement monthlyInstallment;

	@FindBy(xpath = "(//dt[normalize-space()='Toplam �deme']//following-sibling::dd)[1]")
	WebElement totalPayment;
}
