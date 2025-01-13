package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootTest
class AuthControllerTest(
    val encryptionService: EncryptionService,// 암호화 서비스
    val hashService : HashService,// 해시 생성 서비스

): BehaviorSpec({
    val restTemplate = RestTemplate()

    Given("RequestData with dynamically generated reqDt and reqTm") {

        // 현재 날짜와 시간 생성
        val currentDateTime = LocalDateTime.now()
        val reqDt = currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        val reqTm = currentDateTime.format(DateTimeFormatter.ofPattern("HHmmss"))

        val mchtId = "M2513021"
        val mchtCustId = "hong11123213"
        val custAcntNo = "976453711551"

        val hashKey = "ST1009281328226982205"


        val concatenatedData = "${mchtId}${mchtCustId}${reqDt}${reqTm}${custAcntNo}${hashKey}"

        val pktHash = hashService.generateHash(data=concatenatedData )
        val requestData = RequestData(
            hdlInfo = "SPAY_NA00_1.0",
            mchtId = mchtId,
            mchtTrdNo = "",
            mchtCustId = encryptionService.encrypt(mchtCustId),
            reqDt = reqDt,
            reqTm = reqTm,
            bankCd = "004",
            custAcntNo = encryptionService.encrypt(custAcntNo),
            mchtCustNm = encryptionService.encrypt("홍길동"),
            acctNoChkYn = "N",
            custIp = "127.0.0.1",
            pktHash = pktHash
        )

        val targetUrl = "https://tbnpay.settlebank.co.kr/v1/api/auth/acnt/ownercheck1"

        val om = ObjectMapper()
        val json = om.writeValueAsString(requestData)
        println("Serialized JSON: $json")


       When("sending POST request to $targetUrl") {

//            val headers = HttpHeaders().apply {
//                contentType = MediaType.APPLICATION_JSON
//            }
           val headers = HttpHeaders().apply {
               contentType = MediaType.parseMediaType("application/json;charset=UTF-8")
           }

            // 요청 데이터 설정
            val httpEntity = HttpEntity(json, headers)


            val response = restTemplate.postForEntity(
                targetUrl,
                httpEntity,
                String::class.java
            )

            Then("should send the request with current reqDt and reqTm and receive success response") {
                println("Response Status Code: ${response.statusCode.value()}")
                println("Response Body: ${response.body}")

                // 예상대로 응답 처리 로직을 추가
                response.statusCode.is2xxSuccessful shouldBe true
                response.body?.isNotEmpty() shouldBe true

                println(httpEntity.body)
                println(httpEntity.headers)
            }
        }
    }
})
