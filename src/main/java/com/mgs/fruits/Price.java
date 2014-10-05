package com.mgs.fruits;

public class Price {
	private final int undiscountedPrice;
	private final int oneFreeEvery;

	public Price(int undiscountedPrice, int oneFreeEvery) {
		this.undiscountedPrice = undiscountedPrice;
		this.oneFreeEvery = oneFreeEvery;
	}

	public int getUndiscountedPrice() {
		return undiscountedPrice;
	}

	public boolean hasDiscount() {
		return oneFreeEvery > 0;
	}

	public int getOneFreeEvery() {
		return oneFreeEvery;
	}
}
