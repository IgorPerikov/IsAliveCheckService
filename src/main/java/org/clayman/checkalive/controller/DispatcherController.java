package org.clayman.checkalive.controller;

import org.clayman.checkalive.domain.Claim;
import org.clayman.checkalive.domain.ClaimBodyRequest;
import org.clayman.checkalive.domain.ClaimBodyResponse;
import org.clayman.checkalive.service.ClaimService;
import org.clayman.checkalive.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/api")
public class DispatcherController {
    private ClaimService claimService;
    private MonitoringService monitoringService;

    @Autowired
    public void setClaimService(ClaimService claimService) {
        this.claimService = claimService;
    }

    @Autowired
    public void setMonitoringService(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @RequestMapping(value = "/application-check", method = RequestMethod.POST)
    private ResponseEntity postClaim(@RequestBody ClaimBodyRequest claimBodyRequest) {
        Claim claim = new Claim(claimBodyRequest);
        Claim recentlyAddedClaim = claimService.addClaim(claim);
        if (monitoringService.addToQueue(recentlyAddedClaim)) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @RequestMapping(value = "/application-status", method = RequestMethod.GET)
    private ResponseEntity<ClaimBodyResponse> getStatusOfClaim(@RequestParam String host,
                                                               @RequestParam int port,
                                                               @RequestParam String path) {
        Claim claim = claimService.getClaimResponseIfReady(host, port, path);
        if (claim == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body(new ClaimBodyResponse(claim));
        }
    }
}