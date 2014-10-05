package com.mgs.fruits;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;

public class Main {

	public static void main (String... args){
		System.out.println(calculatePrice(args));
	}

	public static int calculatePrice(String... strings) {
		return new PriceEngine(
				new ImmutableMap.Builder<String, Price>()
						.put("Apple", new Price(35, 0))
						.put("Banana", new Price(20, 0))
						.put("Melon", new Price(50, 2))
						.put("Lime", new Price(15, 3))
						.build()
		).calculatePrice(Arrays.asList(strings));
	}
}
