package org.clayman.checkalive.service;

import org.clayman.checkalive.domain.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@Service
public class MonitoringServiceSimpleImpl implements MonitoringService {
    @Value("${monitoring.configuration.max-size}")
    private int maxSize;

    private Queue<Claim> claims;
    private ClaimService claimService;

    @PostConstruct
    public void bootstrapQueue() throws Exception {
        claims = new ArrayBlockingQueue<>(maxSize);
        claims.addAll(claimService.getClaims());

        int cores = Runtime.getRuntime().availableProcessors();
        int numberOfThreads;

        if (cores > 2) {
            numberOfThreads = cores - 1;
        } else {
            numberOfThreads = 1;
        }

        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new ClaimExecuteRunnable(claims, claimService)).start();
        }
    }

    @Override
    public boolean addToQueue(Claim claim) {
        try {
            claims.add(claim);
        } catch (IllegalStateException ise) {
            return false;
        }
        return true;
    }

    @Autowired
    public void setClaimService(ClaimService claimService) {
        this.claimService = claimService;
    }
}