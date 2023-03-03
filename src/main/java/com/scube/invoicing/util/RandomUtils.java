package com.scube.invoicing.util;

import java.util.Random;

public class RandomUtils {
	
	public static int generateRandomNumber() {
		Random randomNumber = new Random();
		int number = randomNumber.nextInt(1000);
		return number;
	}
	
}
