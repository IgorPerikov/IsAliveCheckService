package org.clayman.checkalive.repository;

import org.clayman.checkalive.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    @Modifying
    @Query("update Claim c set c.checkDate = ?2, c.responseStatusCode = ?3, c.responseText = ?4, c.reason = ?5, c.claimStatus = 1  where c.id = ?1")
    int updateClaim(long claimId, Date date, int responseStatusCode, String responseText, String reason);

    Claim findByHostAndPortAndPathAndClaimStatus(String host, int port, String path, byte claimStatus);

    List<Claim> findByClaimStatus(byte claimStatus);
}