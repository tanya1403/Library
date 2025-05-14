//package com.homefirst.Library.entity
//
//import com.homefirst.Library.utils.DateTimeUtils.getCurrentDateTimeInIST
//import org.hibernate.annotations.ColumnDefault
//import jakarta.persistence.*
//
//@Entity
//@Table(name = "`Creds`")
//class Creds {
//
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @Column(updatable = false, nullable = false)
//    var id: String? = null
//
//    @Column(nullable = false)
//    var partnerName: String? = null
//    var credType: String? = null
//    var username: String? = null
//    var password: String? = null
//    var memberId: String? = null
//    var memberPasscode: String? = null
//    var salt: String? = null
//    var apiKey: String? = null
//    var apiUrl: String? = null
//
//    @ColumnDefault("1")
//    var isValid = true
//
//    @ColumnDefault("0")
//    var isEncrypted = false
//
//    @Column(columnDefinition = "DATETIME", updatable = false, nullable = false)
//    var createDatetime = getCurrentDateTimeInIST()
//
//    @Column(columnDefinition = "DATETIME")
//    var updateDatetime = getCurrentDateTimeInIST()
//
//}