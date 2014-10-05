package com.mgs.fruits;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceEngine {
	private final Map<String, Price> prices;

	public PriceEngine(Map<String, Price> prices) {
		this.prices = prices;
	}

	public int calculatePrice(List<String> items) {
		PricingSession pricingSession = newSession(initializeDiscountsAccumulator());
		int total = 0;
		for (String item : items) {
			total += pricingSession.calculatePrice(item);
		}
		return total;
	}

	private Map<String, Integer> initializeDiscountsAccumulator() {
		Map<String,Integer> discountsAccumulator = new HashMap<String, Integer>();
		for (Map.Entry<String, Price> priceEntry : prices.entrySet()) {
			if (priceEntry.getValue().hasDiscount()){
				discountsAccumulator.put(priceEntry.getKey(), 0);
			}
		}
		return discountsAccumulator;
	}

	PricingSession newSession(Map<String, Integer> discountsAccumulator) {
		return new PricingSession(prices, discountsAccumulator);
	}
}