package com.interview.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.interview.pages.CreditSearchEngin;
import com.interview.pages.NavigationBar;
import com.interview.pages.ResultPage;
import com.interview.utilities.HelperMethods;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KrediTest {
	WebDriver driver;

	@Test
	public void krediTest() throws InterruptedException {
		NavigationBar navBar = new NavigationBar(driver);
		CreditSearchEngin searchEngin = new CreditSearchEngin(driver);
		ResultPage result = new ResultPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Actions actions = new Actions(driver);
		// kredi listesine hover yap
		actions.moveToElement(navBar.krediSection).build().perform();
		//kredi listesinin sub menu lerini kontrol et
		Assert.assertEquals(navBar.getActualCreditList(), navBar.expectedKrediList);
		// ihtiyac kredisine tikla
		navBar.ihtiyacKredisi.click();
		String url = driver.getCurrentUrl();
		// ihtiyac kredisi sayfasina yonlendirildigini kontrol et
		Assert.assertEquals("https://www.hangikredi.com/kredi/ihtiyac-kredisi", url);
		// newtwork status codun 200 (success) oldugunu control et 
		Assert.assertEquals(HelperMethods.checkNetworkStatusCode(driver), 200);
		// 500 ile 100.000 arasinda rastgale numara olustur
		int randomNum = HelperMethods.generateRandomNumber(500, 100.000);
		// olusturulan numarayi kredi tutari field ina gonder
		searchEngin.krediTutariBox.sendKeys(String.valueOf(randomNum));
		// 12 ay kredi vadesini sec
		HelperMethods.selectByValue(searchEngin.krediVadesiBox, "12");
		// hesapla butonu na tikla
		searchEngin.CalculateButton.click();
		// sonuc sayfasinda basligi kontrol et 
		Assert.assertTrue(result.tutarVeVade(randomNum).isDisplayed());
		// newtwork status codun 200 (success) oldugunu control et 
		Assert.assertEquals(HelperMethods.checkNetworkStatusCode(driver), 200);
		// tum sponsor bankalar bulunana kadar beklenmesi gerekir.
		//Herhangi bir wait kullanildiginda ilk sponsor bankayi bulur bulmaz teste devam edicak fakat sonradan yuklenenleri test etmicektir.
		Thread.sleep(3000);
		for (int i = 1; i <= result.sponsorBanks.size(); i++) {
			// Sonuc sayfasinda ilk sponsor bankanin faiz orani, aylik taksit, toplam odeme bilgilerini kaydet
			List<String> expectList = result.getExpectedListForSponsorBankByindex(i);
			// ilk sponsor bankanin detay butonu na tikla
			result.detailButtonLocatorByIndex(i).click();
			// detay sayafasinda ilk sponsor bankanin faiz orani, aylik taksit, toplam odeme bilgilerini kaydet
			List<String> actualList = result.getActualListForSponsorBankByindex(i);
			try {
				// sonuc ve detay sayfasinda ki banka verilerini karsilastir
				Assert.assertEquals(actualList, expectList);
			} catch (AssertionError e) {
				System.out.println("Expect Values " + expectList + " Actual Values " + actualList + " not equal for "
						+ driver.getCurrentUrl());
				throw e;
			}
			driver.navigate().back();
		}
	}

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.hangikredi.com/");
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot tc = (TakesScreenshot) driver;
			File src = tc.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File(result.getName() + ".png"));
		}
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}

}
