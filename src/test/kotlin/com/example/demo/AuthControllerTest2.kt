package com.example.demo

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.Test
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthControllerTest2 : BehaviorSpec({

    Given("두 숫자가 있을 때") {
        val a = 1
        val b = 2

        When("두 숫자를 더하면") {
            val result = a + b

            Then("결과는 3이어야 한다") {
                result shouldBe 3
            }
        }
    }
})
