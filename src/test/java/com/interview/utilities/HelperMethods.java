package com.interview.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HelperMethods {

	public static int generateRandomNumber(int min, double max) {
		int random = (int) (Math.random() * ((min - max) + 1)) + min;
		return random;
	}

	public static int checkNetworkStatusCode(WebDriver driver) {
		RestAssured.baseURI = driver.getCurrentUrl();
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		return response.getStatusCode();
	}

	public static void selectByValue(WebElement element, String value) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
	}
}
