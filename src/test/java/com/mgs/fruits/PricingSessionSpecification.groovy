package com.mgs.fruits

import spock.lang.Specification


class PricingSessionSpecification extends Specification {
    static final String UNDISCOUNTED_ITEM = "undiscountedItem"
    static final String ONE_FOR_FREE_EVERY_THREE = "oneForFreeEveryThree"
    public static final int UNDISCOUNTED_ITEM_PRICE = 10

    PricingSession testObj
    Price undiscountedPriceMock = Mock ()
    Price oneForFreeEveryThreePriceMock = Mock ()
    Map<String, Price> prices = [
            undiscountedItem: undiscountedPriceMock,
            oneForFreeEveryThree : oneForFreeEveryThreePriceMock
    ]
    Map<String, Integer> accumulatedItems = [oneForFreeEveryThree: 0]

    def "setup" (){
        testObj = new PricingSession(prices, accumulatedItems)
        undiscountedPriceMock.hasDiscount() >> false
        undiscountedPriceMock.undiscountedPrice >> UNDISCOUNTED_ITEM_PRICE
        oneForFreeEveryThreePriceMock.undiscountedPrice >> UNDISCOUNTED_ITEM_PRICE
        oneForFreeEveryThreePriceMock.hasDiscount() >> true
        oneForFreeEveryThreePriceMock.oneFreeEvery >> 3
    }

    def "should return always the undiscounted price for an undiscounted item" (){
        when:
        def resultCall1 = testObj.calculatePrice(UNDISCOUNTED_ITEM)
        def resultCall2 = testObj.calculatePrice(UNDISCOUNTED_ITEM)
        def resultCall3 = testObj.calculatePrice(UNDISCOUNTED_ITEM)
        def resultCall4 = testObj.calculatePrice(UNDISCOUNTED_ITEM)
        def resultCall5 = testObj.calculatePrice(UNDISCOUNTED_ITEM)
        def resultCall6 = testObj.calculatePrice(UNDISCOUNTED_ITEM)

        then:
        resultCall1 == UNDISCOUNTED_ITEM_PRICE
        resultCall2 == UNDISCOUNTED_ITEM_PRICE
        resultCall3 == UNDISCOUNTED_ITEM_PRICE
        resultCall4 == UNDISCOUNTED_ITEM_PRICE
        resultCall5 == UNDISCOUNTED_ITEM_PRICE
        resultCall6 == UNDISCOUNTED_ITEM_PRICE
    }

    def "should give item for free every x purchases according to discount" (){
        when:
        def resultCall1 = testObj.calculatePrice(ONE_FOR_FREE_EVERY_THREE)
        def resultCall2 = testObj.calculatePrice(ONE_FOR_FREE_EVERY_THREE)
        def resultCall3 = testObj.calculatePrice(ONE_FOR_FREE_EVERY_THREE)
        def resultCall4 = testObj.calculatePrice(ONE_FOR_FREE_EVERY_THREE)
        def resultCall5 = testObj.calculatePrice(ONE_FOR_FREE_EVERY_THREE)
        def resultCall6 = testObj.calculatePrice(ONE_FOR_FREE_EVERY_THREE)

        then:
        resultCall1 == UNDISCOUNTED_ITEM_PRICE
        resultCall2 == UNDISCOUNTED_ITEM_PRICE
        resultCall3 == 0
        resultCall4 == UNDISCOUNTED_ITEM_PRICE
        resultCall5 == UNDISCOUNTED_ITEM_PRICE
        resultCall6 == 0

    }
}
