package org.clayman.checkalive.service;

import org.clayman.checkalive.domain.Claim;

public interface MonitoringService {
    boolean addToQueue(Claim claim);
}
