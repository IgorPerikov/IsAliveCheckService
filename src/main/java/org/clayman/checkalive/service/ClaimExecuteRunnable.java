package org.clayman.checkalive.service;

import org.clayman.checkalive.domain.Claim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Queue;

public class ClaimExecuteRunnable implements Runnable {
    private Queue<Claim> claims;
    private ClaimService claimService;

    public ClaimExecuteRunnable(Queue<Claim> claims, ClaimService claimService) {
        this.claims = claims;
        this.claimService = claimService;
    }

    @Override
    public void run() {
        Claim claim;
        while (true) {
            try {
                claim = claims.remove();
                handleClaim(claim);
            } catch (NoSuchElementException nsee) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }

    private void handleClaim(Claim claim) {
        URL obj;
        String url = "http://" + claim.getHost();
        int port = claim.getPort();
        String path = claim.getPath();

        try {
            obj = new URL(url + ":" + port + path);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            claim.setReason(con.getResponseMessage());
            claim.setResponseStatusCode(con.getResponseCode());
            if (response.length() >= 1000) {
                claim.setResponseText(response.toString().substring(0, 990));
            } else {
                claim.setResponseText(response.toString());
            }
        } catch (IOException ioe) {
            claim.setReason("");
            claim.setResponseText("service is not available or input data is incorrect");
        }
        claim.setClaimStatus((byte) 1);
        claim.setCheckDate(new Date());

        claimService.updateClaim(claim);
    }
}
