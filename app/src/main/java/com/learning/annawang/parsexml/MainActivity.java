package com.learning.annawang.parsexml;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        Button mBtnGo = (Button) this.findViewById(R.id.btn_go);
        mBtnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    File file = Environment.getExternalStorageDirectory();
                    String path = file.getAbsolutePath() + "/in_2.txt";
                    InputStream inputStream = new FileInputStream(path);
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String xmlString;
                    StringBuilder stringBuilder = null;
                    while (null != (xmlString = bufferedReader.readLine())) {
                        if (xmlString.contains("<SOAP-ENV:Envelope")) {
                            Log.d(TAG, "START the message");
                            stringBuilder = new StringBuilder();
                        }

                        if (null == stringBuilder) return;

                        stringBuilder.append(xmlString);
                        Log.d(TAG, xmlString);

                        if (xmlString.contains("</SOAP-ENV:Envelope>")) {
                            parseResponse(stringBuilder.toString());
                            Log.d(TAG, "END the message");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private static final String TAG_STATUS_DATA = "<Integration:StatusData xsi:type=\"";
    private static final String TAG_TRANSACTION_MANAGER_STATUS = "<Integration:TransactionManagerStatus>";
    private static final String TAG_REASON_CODE = "<Integration:ReasonCode>";
    private static final String TAG_TRANSACTION_REASON_CODE = "<TransactionManagerStatusData:ReasonCode>";
    private static final String TAG_STARRED_PRIMARY_ACCOUNT_NUMBER = "<TransactionManagerStatusData:StarredPrimaryAccountNumber>";
    private static final String TAG_CARD_SCHEME_ID = "<TransactionManagerStatusData:CardSchemeId>";
    private static final String TAG_CARD_SCHEME_NAME = "<TransactionManagerStatusData:CardSchemeName>";
    private static final String TAG_MERCHANT_TOKEN_ID = "<TransactionManagerStatusData:MerchantTokenId>";
    private static final String TAG_CARDHOLDER_NAME = "<TransactionManagerStatusData:CardholderName xsi:nil=\"true\">";
    private static final String TAG_ID = "<Integration:Id>";

    private void parseResponse(String xmlString) {
        int typeStartIndex = xmlString.lastIndexOf(TAG_STATUS_DATA) + TAG_STATUS_DATA.length();
        int typeEndIndex = xmlString.indexOf("\">", typeStartIndex);
        String statusDataType = parseValue(xmlString, typeStartIndex, typeEndIndex);

        int managerStartIndex = xmlString.lastIndexOf(TAG_TRANSACTION_MANAGER_STATUS) + TAG_TRANSACTION_MANAGER_STATUS.length();
        int managerEndIndex = xmlString.indexOf("</Integration:TransactionManagerStatus>", managerStartIndex);
        String transactionManagerStatus = parseValue(xmlString, managerStartIndex, managerEndIndex);

        int codeStartIndex = xmlString.lastIndexOf(TAG_REASON_CODE) + TAG_REASON_CODE.length();
        int codeEndIndex = xmlString.indexOf("</Integration:ReasonCode>", codeStartIndex);
        String reasonCodeStatus = parseValue(xmlString, codeStartIndex, codeEndIndex);

        int reasonCodeStatusDataStartIndex = xmlString.lastIndexOf(TAG_TRANSACTION_REASON_CODE) + TAG_TRANSACTION_REASON_CODE.length();
        int reasonCodeStatusDataEndIndex = xmlString.indexOf("</TransactionManagerStatusData:ReasonCode>", reasonCodeStatusDataStartIndex);
        String reasonCodeStatusData = parseValue(xmlString, reasonCodeStatusDataStartIndex, reasonCodeStatusDataEndIndex);

        BaseModel baseModel = null;

        if (statusDataType.toLowerCase().contains("CardIdentified".toLowerCase())) {
            // card info
            int cardNumStartIndex = xmlString.lastIndexOf(TAG_STARRED_PRIMARY_ACCOUNT_NUMBER) + TAG_STARRED_PRIMARY_ACCOUNT_NUMBER.length();
            int cardNumEndIndex = xmlString.indexOf("</TransactionManagerStatusData:StarredPrimaryAccountNumber>");
            String cardNum = parseValue(xmlString, cardNumStartIndex, cardNumEndIndex);

            int schemeIdStartIndex = xmlString.lastIndexOf(TAG_CARD_SCHEME_ID) + TAG_CARD_SCHEME_ID.length();
            int schemeIdEndIndex = xmlString.indexOf("</TransactionManagerStatusData:CardSchemeId>", schemeIdStartIndex);
            String schemeId = parseValue(xmlString, schemeIdStartIndex, schemeIdEndIndex);

            int shcemeNameStartIndex = xmlString.lastIndexOf(TAG_CARD_SCHEME_NAME) + TAG_CARD_SCHEME_NAME.length();
            int shcemeNameEndIndex = xmlString.indexOf("</TransactionManagerStatusData:CardSchemeName>", shcemeNameStartIndex);
            String schemeName = parseValue(xmlString, shcemeNameStartIndex, shcemeNameEndIndex);

            int tokenIdStartIndex = xmlString.lastIndexOf(TAG_MERCHANT_TOKEN_ID) + TAG_MERCHANT_TOKEN_ID.length();
            int tokenIdEndIndex = xmlString.indexOf("</TransactionManagerStatusData:MerchantTokenId>", tokenIdStartIndex);
            String tokenId = parseValue(xmlString, tokenIdStartIndex, tokenIdEndIndex);

            String numberField = "";

            int holderNameStartIndex = xmlString.lastIndexOf(TAG_CARDHOLDER_NAME) + TAG_CARDHOLDER_NAME.length();
            int holderNameEndIndex = xmlString.indexOf("</TransactionManagerStatusData:CardholderName>", holderNameStartIndex);
            String holderName = parseValue(xmlString, holderNameStartIndex, holderNameEndIndex);

            String dateM = "";
            String dateY = "";
            String capMehod = "";
            baseModel = new CardInfoModel(cardNum
                    , schemeId
                    , schemeName
                    , tokenId
                    , numberField
                    , holderName
                    , dateM
                    , dateY
                    , capMehod);

        } else if (statusDataType.toLowerCase().contains("PaymentManagerProcessing".toLowerCase())) {
            // process payment
            int idStartIndex = xmlString.lastIndexOf(TAG_ID) + TAG_ID.length();
            int idEndIndex = xmlString.indexOf("</Integration:Id>", idStartIndex);
            String id = parseValue(xmlString, idStartIndex, idEndIndex);

            baseModel = new TransactionManagerStatusModel(id);

        } else {
            baseModel = new BaseModel();
        }

        baseModel.setTransactionManagerStatus(transactionManagerStatus);
        baseModel.setReasonCodeStatus(reasonCodeStatus);
        baseModel.setReasonCodeStatusData(reasonCodeStatusData);

        Log.d(TAG, "Parse the messages: \r\n" + baseModel.toString());
    }

    public static final int CODE_FAULT = -1;

    private String parseValue(String str, int startIndex, int endIndex) {
        String value = String.valueOf(CODE_FAULT);
        try {
            value = str.substring(startIndex + 1, endIndex);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return value;
        }
    }
}
