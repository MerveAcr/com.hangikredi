package com.interview.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.interview.utilities.Driver;

public class CreditDetails {

	public CreditDetails() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "(//dt[normalize-space()='Faiz Oraný']//following-sibling::dd)[1]")
	WebElement interestRate;

	@FindBy(xpath = "(//dt[normalize-space()='Aylýk Taksit']//following-sibling::dd)[1]")
	WebElement monthlyInstallment;

	@FindBy(xpath = "(//dt[normalize-space()='Toplam Ödeme']//following-sibling::dd)[1]")
	WebElement totalPayment;
}
