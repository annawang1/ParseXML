package com.learning.annawang.parsexml;

/**
 * Monitor transaction:
 * One of the first updates that should be noted is the CardIdentified stage.
 * This is where the axept® Terminal has identified the card which has been presented.
 * At this stage TransactionManager returns the card information to the POS as well as the recurring
 * token ID for the card.
 */

public class CardInfoModel extends BaseModel {

    public static final String EOL = "\r\n";


    // Masked PAN for the card - e.g. ************9909
    private String starredPrimaryAccountNumber;
    // Card Scheme Id for the card presented
    private String cardSchemeId;
    // Name of the Card Scheme of the card presented
    private String cardSchemeName;
    // Token Id for the card presented
    private String merchantTokenId;
    // Masked PAN presented in an alternative format (not currently used)
    private String alternateStarredPrimaryAccountNumberField;
    // Name of the cardholder (not currently used)
    private String cardholderName;
    // Expiry month for the card presented
    private String expiryDateMM;
    // Expiry year for the card presented
    private String expiryDateYY;
    /**
     * Method in which the card was captured
     * 1 Integrated Circuit Card (ICC)
     * 2 Integrated Circuit Card (ICC) - Fallback to Swipe
     * 3 Swipe
     * 4 Keyed
     * 5 Contactless EMV
     * 6 Contactless Magnetic Stripe Data
     */
    private String captureMethod;


    public CardInfoModel(String starredPrimaryAccountNumber, String cardSchemeId, String cardSchemeName, String merchantTokenId, String alternateStarredPrimaryAccountNumberField, String cardholderName, String expiryDateMM, String expiryDateYY, String captureMethod) {
        this.starredPrimaryAccountNumber = starredPrimaryAccountNumber;
        this.cardSchemeId = cardSchemeId;
        this.cardSchemeName = cardSchemeName;
        this.merchantTokenId = merchantTokenId;
        this.alternateStarredPrimaryAccountNumberField = alternateStarredPrimaryAccountNumberField;
        this.cardholderName = cardholderName;
        this.expiryDateMM = expiryDateMM;
        this.expiryDateYY = expiryDateYY;
        this.captureMethod = captureMethod;
    }

    public String getStarredPrimaryAccountNumber() {
        return starredPrimaryAccountNumber;
    }

    public void setStarredPrimaryAccountNumber(String starredPrimaryAccountNumber) {
        this.starredPrimaryAccountNumber = starredPrimaryAccountNumber;
    }

    public String getCardSchemeId() {
        return cardSchemeId;
    }

    public void setCardSchemeId(String cardSchemeId) {
        this.cardSchemeId = cardSchemeId;
    }

    public String getCardSchemeName() {
        return cardSchemeName;
    }

    public void setCardSchemeName(String cardSchemeName) {
        this.cardSchemeName = cardSchemeName;
    }

    public String getMerchantTokenId() {
        return merchantTokenId;
    }

    public void setMerchantTokenId(String merchantTokenId) {
        this.merchantTokenId = merchantTokenId;
    }

    public String getAlternateStarredPrimaryAccountNumberField() {
        return alternateStarredPrimaryAccountNumberField;
    }

    public void setAlternateStarredPrimaryAccountNumberField(String alternateStarredPrimaryAccountNumberField) {
        this.alternateStarredPrimaryAccountNumberField = alternateStarredPrimaryAccountNumberField;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getExpiryDateMM() {
        return expiryDateMM;
    }

    public void setExpiryDateMM(String expiryDateMM) {
        this.expiryDateMM = expiryDateMM;
    }

    public String getExpiryDateYY() {
        return expiryDateYY;
    }

    public void setExpiryDateYY(String expiryDateYY) {
        this.expiryDateYY = expiryDateYY;
    }

    public String getCaptureMethod() {
        return captureMethod;
    }

    public void setCaptureMethod(String captureMethod) {
        this.captureMethod = captureMethod;
    }

    @Override
    public String toString() {
        super.toString();
        return CardInfoModel.class.getSimpleName() + "{" + EOL
                + "transactionManagerStatus: " + transactionManagerStatus + "," + EOL
                + "reasonCodeStatus: " + reasonCodeStatus + "," + EOL
                + "reasonCodeStatusData: " + reasonCodeStatusData + "," + EOL
                + "starredPrimaryAccountNumber: " + starredPrimaryAccountNumber + "," + EOL
                + "cardSchemeId: " + cardSchemeId + "," + EOL
                + "cardSchemeName: " + cardSchemeName + "," + EOL
                + "merchantTokenId: " + merchantTokenId + "," + EOL
                + "alternateStarredPrimaryAccountNumberField: " + alternateStarredPrimaryAccountNumberField + "," + EOL
                + "cardholderName: " + cardholderName + "," + EOL
                + "expiryDateMM: " + expiryDateMM + "," + EOL
                + "expiryDateYY: " + expiryDateYY + EOL + "}";
    }
}
