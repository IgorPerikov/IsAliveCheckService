package org.clayman.checkalive.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Claim extends AbstractEntity {
    public Claim(ClaimBodyRequest claimBodyRequest) {
        claimStatus = 0;
        host = claimBodyRequest.getHost();
        port = claimBodyRequest.getPort();
        path = claimBodyRequest.getPath();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Claim claim = (Claim) o;

        if (port != claim.port) return false;
        if (!host.equals(claim.host)) return false;
        return path.equals(claim.path);
    }

    @Override
    public int hashCode() {
        int result = host.hashCode();
        result = 31 * result + path.hashCode();
        result = 31 * result + port;
        return result;
    }

    public Claim() {
    }

    @Column(length = 100)
    private String host;
    @Column(length = 100)
    private String path;
    private int port;

    private byte claimStatus;

    private int responseStatusCode;
    @Column(length = 1000)
    private String responseText;
    @Column(length = 100)
    private String reason;

    private Date checkDate;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(byte claimStatus) {
        this.claimStatus = claimStatus;
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(int responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
