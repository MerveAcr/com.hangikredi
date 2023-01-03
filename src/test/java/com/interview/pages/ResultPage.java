package com.interview.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
	WebDriver driver;

	public ResultPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(css = ".card--sponsored")
	public List<WebElement> sponsorBanks;

	public WebElement tutarVeVade(int randomNum) {
		WebElement interestRate = driver
				.findElement(By.xpath("//h2[contains(text(),'" + randomNum + " TL 12 Ay Vadeli Ýhtiyaç Kredileri')]"));
		return interestRate;
	};

	public WebElement detailButtonLocatorByIndex(int index) {
		return driver.findElement(
				By.xpath("(//*[contains(@class, \"card--sponsored\")])[" + index + "]//*[text()=\"Detay\"]"));
	};

	public WebElement getInterestRateLocatorByIndex(int index) {
		WebElement interestRate = driver.findElement(By.xpath("(//*[contains(@class, \"card--sponsored\")])[" + index
				+ "]//*[@class=\"card__content\"]//*[contains(@class, \"interest-rate\")]//*[contains(@class, \"rates\")]"));
		return interestRate;
	};

	public WebElement getMonthlyInstallmentLocatorByIndex(int index) {
		WebElement monthlyInstallment = driver.findElement(By.xpath("(//*[contains(@class, \"card--sponsored\")])["
				+ index
				+ "]//*[@class=\"card__content\"]//*[contains(@class, \"monthly-installment\")]//*[contains(@class, \"rates\")]"));
		return monthlyInstallment;
	};

	public WebElement getTotalPaymentLocatorByIndex(int index) {
		WebElement totalPayment = driver.findElement(By.xpath("(//*[contains(@class, \"card--sponsored\")])[" + index
				+ "]//*[@class=\"card__content\"]//*[contains(@class, \"total-payment\")]//*[contains(@class, \"rates\")]"));
		return totalPayment;
	};

	public List<String> getExpectedListForSponsorBankByindex(int index) {
		List<String> expectedList = new ArrayList();
		expectedList.add(getInterestRateLocatorByIndex(index).getText());
		expectedList.add(getMonthlyInstallmentLocatorByIndex(index).getText());
		expectedList.add(getTotalPaymentLocatorByIndex(index).getText());
		return expectedList;
	}

	public List<String> getActualListForSponsorBankByindex(int index) {
		CreditDetails krediDetay = new CreditDetails(driver);
		List<String> actualList = new ArrayList();
		actualList.add(krediDetay.interestRate.getText());
		actualList.add(krediDetay.monthlyInstallment.getText());
		actualList.add(krediDetay.totalPayment.getText());
		return actualList;
	}
}
