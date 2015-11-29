package org.clayman.checkalive.service;

import org.clayman.checkalive.domain.Claim;

import java.util.List;

public interface ClaimService {
    Claim addClaim(Claim claim);

    Claim getClaimResponseIfReady(String host, int port, String path);

    List<Claim> getClaims();

    void updateClaim(Claim claim);
}
