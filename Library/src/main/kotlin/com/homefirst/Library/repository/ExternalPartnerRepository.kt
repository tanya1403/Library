package com.homefirst.Library.repository

import com.homefirst.Library.entity.Creds
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Component
class PartnerMasterRepository(
)



@Repository
interface CredsRepository : JpaRepository<Creds, String> {

    @Transactional(readOnly = true)
    @Query("from Creds where partnerName = :partnerName and credType = :credType and isValid = true")
    fun findByPartnerNameAndCredType(partnerName: String, credType: String): Creds?

}


