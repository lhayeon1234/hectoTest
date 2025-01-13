package com.example.demo

data class RequestData(
    val hdlInfo: String, // 전문정보 코드
    val mchtId: String, // 상점아이디
    val mchtTrdNo: String?, // 상점주문번호 (Optional)
    val mchtCustId: String, // 고객아이디 (AES 암호화)
    val reqDt: String, // 요청일자 (yyyyMMdd)
    val reqTm: String, // 요청시간 (HH24MISS)
    val bankCd: String, // 은행코드
    val custAcntNo: String, // 계좌번호 (AES 암호화)
    val mchtCustNm: String, // 예금주명 (AES 암호화)
    val acctNoChkYn: String?, // 등록불가계좌 확인여부 (Optional)
    val custIp: String?, // 고객 IP 주소 (Optional)
    val pktHash: String // SHA-256 해시값
)
