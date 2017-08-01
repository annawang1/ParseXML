package com.learning.annawang.parsexml;

import static com.learning.annawang.parsexml.CardInfoModel.EOL;

/**
 * Monitor transaction.
 */

public class TransactionManagerStatusModel extends BaseModel {
    private String id;

    public TransactionManagerStatusModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        super.toString();
        return TransactionManagerStatusModel.class.getSimpleName() + "{" + EOL
                + "transactionManagerStatus: " + transactionManagerStatus + "," + EOL
                + "reasonCodeStatus: " + reasonCodeStatus + "," + EOL
                + "reasonCodeStatusData: " + reasonCodeStatusData + "," + EOL
                + "id: " + id + EOL + "}";
    }
}