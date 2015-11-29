package org.clayman.checkalive.service;

import org.clayman.checkalive.domain.Claim;
import org.clayman.checkalive.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {
    private ClaimRepository claimRepository;

    @Autowired
    public void setClaimRepository(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public Claim addClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public Claim getClaimResponseIfReady(String host, int port, String path) {
        return claimRepository.findByHostAndPortAndPathAndClaimStatus(host, port, path, (byte) 1);
    }

    @Override
    public List<Claim> getClaims() {
        return claimRepository.findByClaimStatus((byte) 0);
    }

    @Override
    public void updateClaim(Claim claim) {
        claimRepository.updateClaim(claim.getId(), claim.getCheckDate(), claim.getResponseStatusCode(), claim.getResponseText(), claim.getReason());
    }
}