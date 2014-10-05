package com.mgs.fruits;


import java.util.Map;

public class PricingSession {
	private final Map<String, Price> prices;
	private final Map<String, Integer> accumulatedItems;

	PricingSession(Map<String, Price> prices, Map<String, Integer> accumulatedItems) {
		this.prices = prices;
		this.accumulatedItems = accumulatedItems;
	}

	public int calculatePrice(String item) {
		Price price = prices.get(item);
		if (price.hasDiscount()) {
			return calculateDiscountedPrice(item, price);
		} else {
			return price.getUndiscountedPrice();
		}
	}

	private int calculateDiscountedPrice(String item, Price price) {
		int itemsBoughtWithNoDiscount = get(item);
		if (itemsBoughtWithNoDiscount == (price.getOneFreeEvery() - 1)){
			reset(item);
			return 0;
		} else {
			addOne (item);
			return price.getUndiscountedPrice();
		}
	}

	private int get(String item) {
		return accumulatedItems.get(item);
	}

	private void reset(String item) {
		accumulatedItems.put(item, 0);
	}

	private void addOne(String item) {
		accumulatedItems.put(item, get(item) + 1);
	}
}