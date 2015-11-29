package org.clayman.checkalive.domain;

import java.util.Date;

public class ClaimBodyResponse {
    private int responseStatusCode;
    private String reason;
    private String responseText;
    private Date checkDate;

    public ClaimBodyResponse(Claim claim) {
        responseStatusCode = claim.getResponseStatusCode();
        reason = claim.getReason();
        responseText = claim.getResponseText();
        checkDate = claim.getCheckDate();
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(int responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
