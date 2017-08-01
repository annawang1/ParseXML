package com.learning.annawang.parsexml;

/**
 * Monitor transaction.
 */

public class BaseModel {

    protected String transactionManagerStatus;
    protected String reasonCodeStatus;
    protected String reasonCodeStatusData;

    public BaseModel() {
    }

    public String getTransactionManagerStatus() {
        return transactionManagerStatus;
    }

    public void setTransactionManagerStatus(String transactionManagerStatus) {
        this.transactionManagerStatus = transactionManagerStatus;
    }

    public String getReasonCodeStatus() {
        return reasonCodeStatus;
    }

    public void setReasonCodeStatus(String reasonCodeStatus) {
        this.reasonCodeStatus = reasonCodeStatus;
    }

    public String getReasonCodeStatusData() {
        return reasonCodeStatusData;
    }

    public void setReasonCodeStatusData(String reasonCodeStatusData) {
        this.reasonCodeStatusData = reasonCodeStatusData;
    }
}
