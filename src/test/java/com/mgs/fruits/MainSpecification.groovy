package com.mgs.fruits

import spock.lang.Specification
import spock.lang.Unroll

import static com.mgs.fruits.Main.calculatePrice


class MainSpecification extends Specification {
    @Unroll ("For shopping list [#shoppingList] the expected price is [#expectedValue]")
    def "should return correct prices" (){
        when:
        def result = calculatePrice(shoppingList.toArray() as String[])

        then:
        result == expectedValue

        where:
        shoppingList                                    |       expectedValue
        ["Apple"]                                       |       35
        ["Banana"]                                      |       20
        ["Melon"]                                       |       50
        ["Melon", "Melon"]                              |       50
        ["Lime"]                                        |       15
        ["Lime", "Lime", "Lime"]                        |       30
        ["Lime", "Melon", "Lime", "Melon", "Lime"]      |       80
    }
}
