package com.interview.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.interview.pages.CreditSearchEngin;
import com.interview.pages.NavigationBar;
import com.interview.pages.ResultPage;
import com.interview.test.data.NavigationBarTestData;
import com.interview.utilities.Config;
import com.interview.utilities.Driver;
import com.interview.utilities.HelperMethods;

public class KrediTest {

	@Test
	public void krediTest() throws InterruptedException {
		NavigationBar navBar = new NavigationBar();
		CreditSearchEngin searchEngin = new CreditSearchEngin();
		ResultPage result = new ResultPage();
		Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Actions actions = new Actions(Driver.getDriver());
		// kredi listesine hover yap
		actions.moveToElement(navBar.krediSection).build().perform();
		//kredi listesinin sub menu lerini kontrol et
		Assert.assertEquals(navBar.getActualCreditList(), NavigationBarTestData.expectedKrediList);
		// ihtiyac kredisine tikla
		navBar.ihtiyacKredisi.click();
		String url = Driver.getDriver().getCurrentUrl();
		// ihtiyac kredisi sayfasina yonlendirildigini kontrol et
		Assert.assertEquals("https://www.hangikredi.com/kredi/ihtiyac-kredisi", url);
		// newtwork status codun 200 (success) oldugunu control et 
		Assert.assertEquals(HelperMethods.checkNetworkStatusCode(), 200);
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
		Assert.assertEquals(HelperMethods.checkNetworkStatusCode(), 200);
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
						+ Driver.getDriver().getCurrentUrl());
				throw e;
			}
			Driver.getDriver().navigate().back();
		}
	}

	@BeforeTest
	public void beforeTest() {
		Driver.getDriver().get(Config.getProperty("url"));
		Driver.getDriver().manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		HelperMethods.takeScreenshot(result);
	}

	@AfterTest
	public void afterTest() {
		Driver.closeDriver();
	}

}
