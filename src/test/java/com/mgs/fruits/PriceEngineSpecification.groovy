package com.mgs.fruits

import spock.lang.Unroll


class PriceEngineSpecification extends spock.lang.Specification {
    PriceEngine testObj
    PricingSession pricingSessionMock = Mock()
    Price priceWithDiscountMock = Mock ()
    Price priceWithoutDiscountMock = Mock ()
    Map<String, Price> prices = [
            itemWithDiscount        : priceWithDiscountMock,
            itemWithoutDiscount     : priceWithoutDiscountMock
    ]

    static final ITEM_WITH_DISCOUNT = "itemWithDiscount"
    static final ITEM_WITHOUT_DISCOUNT = "itemWithoutDiscount"

    static final ITEM_WITH_DISCOUNT_PRICE = 35
    static final ITEM_WITHOUT_DISCOUNT_PRICE = 20

    def "setup" () {
        testObj = Spy(PriceEngine, constructorArgs: [prices])

        priceWithDiscountMock.hasDiscount() >> true
        priceWithoutDiscountMock.hasDiscount() >> false

        pricingSessionMock.calculatePrice(ITEM_WITH_DISCOUNT) >> ITEM_WITH_DISCOUNT_PRICE
        pricingSessionMock.calculatePrice(ITEM_WITHOUT_DISCOUNT) >> ITEM_WITHOUT_DISCOUNT_PRICE
    }

    @Unroll ("given an item list: [#shoppingList] should return with total price of [#expectedPrice]")
    def "should return the sum of prices returned from the priceSession" (){
        given:
        testObj.newSession([itemWithDiscount:0]) >> pricingSessionMock

        when:
        def result = testObj.calculatePrice (shoppingList)

        then:
        result == expectedPrice

        where:
        shoppingList                                    |   expectedPrice
        []                                              |   0
        [ITEM_WITH_DISCOUNT]                            |   ITEM_WITH_DISCOUNT_PRICE
        [ITEM_WITHOUT_DISCOUNT]                         |   ITEM_WITHOUT_DISCOUNT_PRICE
        [ITEM_WITH_DISCOUNT, ITEM_WITHOUT_DISCOUNT]     |   ITEM_WITH_DISCOUNT_PRICE + ITEM_WITHOUT_DISCOUNT_PRICE
        [ITEM_WITHOUT_DISCOUNT, ITEM_WITHOUT_DISCOUNT]  |   ITEM_WITHOUT_DISCOUNT_PRICE + ITEM_WITHOUT_DISCOUNT_PRICE
        [ITEM_WITH_DISCOUNT, ITEM_WITH_DISCOUNT]        |   ITEM_WITH_DISCOUNT_PRICE + ITEM_WITH_DISCOUNT_PRICE
    }
}
