package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val encryptionService: EncryptionService,
    private val hashService: HashService
) {

    @PostMapping("/ownercheck")
    fun validateRequest(@RequestBody requestData: RequestData): ResponseEntity<String> {
        // 필수 필드 검증
        if (requestData.hdlInfo.isBlank() || requestData.mchtId.isBlank() || requestData.mchtCustId.isBlank()) {
            return ResponseEntity.badRequest().body("Missing required fields")
        }

//        // 해시값 검증
//        val calculatedHash = hashService.generateHash(requestData)
//        if (calculatedHash != requestData.pktHash) {
//            return ResponseEntity.status(401).body("Invalid hash value")
//        }

        return ResponseEntity.ok("Validation Successful")
    }
}
