package com.homefirst.Library.manager

import com.homefirst.Library.entity.Creds
import com.homefirst.Library.repository.CredsRepository
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


enum class EnCredType(val value: String) {
    PRODUCTION("PRODUCTION"),
    UAT("UAT"),
    PRE_PROD("PRE_PROD"),
    FULL_COPY("FULL_COPY");

}

enum class EnPartnerName(val value: String) {
    HOMEFIRST("homefirst"),
    GUPSHUP("Gupshup"),
    GOOGLE_MAPS("Google_Maps"),
    SALESFORCE("Salesforce"),
    TEAL_V2("Teal-V2"),
    AMAZON("AWS-RABIT"),
    AMAZON_STAGE("AWS-STAGE"),
    GOOGLE_DNR("Google_DNR"),
    KALEYRA_SMS("KaleyraSMS"),
    KALEYRA_IO("KaleyraIO"),
    KALEYRA("Kaleyra"),
    DIGITAP("Digitap"),
    MSG91("MSG91"),
    BILLDESK("BillDesk")
}

@Component()
class CredsManager(
    @Autowired private val credsRepository: CredsRepository,
    @Autowired private val entityManager: EntityManager
) {

    fun fetchCredentials(
        partnerName: EnPartnerName,
        credType: EnCredType
    ): Creds? {

        val cred = credsRepository.findByPartnerNameAndCredType(
            partnerName.value,
            credType.value
        )?.apply {
            entityManager.detach(this)
        }

        return cred

    }
}