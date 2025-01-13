package com.example.demo

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.lang.Integer.sum


class CalBehaviorSpec : BehaviorSpec({

    Given("calculator") {
        // before Each 라고 생각 하기
        val expression = "1 + 2"

        When("1 + 2 결과 와 같은 String 입력시 동일한 결과가 나온다") {
            val result = sum(1,2)
            Then("해당 하는 결과값이 반환된다") {
                result shouldBe 3
            }
        }
    }


})