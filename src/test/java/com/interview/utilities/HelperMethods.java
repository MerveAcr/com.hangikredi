package com.interview.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HelperMethods {

	public static int generateRandomNumber(int min, double max) {
		int random = (int) (Math.random() * ((min - max) + 1)) + min;
		return random;
	}

	public static int checkNetworkStatusCode() {
		RestAssured.baseURI = Driver.getDriver().getCurrentUrl();
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		return response.getStatusCode();
	}

	public static void selectByValue(WebElement element, String value) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
	}

	public static void takeScreenshot(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot tc = (TakesScreenshot) Driver.getDriver();
			File src = tc.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File(result.getName() + ".png"));
		}
	}
}
