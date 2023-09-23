package payment;

/**
 * @author Bhavik Patel
 * @date 12-06-2023
 * @version 1.0
 */
public class Payment {

private String cardName;
private String cardNum;
private String expiryDate;
private String securityCode;
private String postalCode;

/**
 * Constructs Payment object.
 * @param cardName Card name.
 * @param cardNum Card number.
 * @param expiryDate Expiry date.
 * @param securityCode Security code.
 * @param postalCode Postal code.
 */
public Payment(String cardName, String cardNum, String expiryDate, String securityCode, String postalCode) {
    this.cardName = cardName;
    this.cardNum = cardNum;
    this.expiryDate = expiryDate;
    this.securityCode = securityCode;
    this.postalCode = postalCode;
}

/**
 * Retrieves card name.
 * @return Card name.
 */
public String getCardName() {
    return cardName;
}

/**
 * Assigns card name.
 * @param cardName Card name.
 */
public void setCardName(String cardName) {
    this.cardName = cardName;
}

/**
 * Retrieves card number.
 * @return Card number.
 */
public String getCardNum() {
    return cardNum;
}

/**
 * Assigns card number.
 * @param cardNum Card number.
 */
public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
}

/**
 * Retrieves expiry date.
 * @return Expiry date.
 */
public String getExpiryDate() {
    return expiryDate;
}

/**
 * Assigns expiry date.
 * @param expiryDate Expiry date.
 */
public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
}

/**
 * Retrieve security code.
 * @return Security code.
 */
public String getSecurityCode() {
    return securityCode;
}

/**
 * Assign security code.
 * @param securityCode Security code.
 */
public void setSecurityCode(String securityCode) {
    this.securityCode = securityCode;
}

/**
 * Retrieves postal code.
 * @return Postal code.
 */
public String getPostalCode() {
    return postalCode;
}

/**
 * Assigns postal code.
 * @param postalCode Postal code.
 */
public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
}

@Override
public String toString() {
    return "Payment [cardName=" + cardName + ", cardNum=" + cardNum + ", expiryDate=" + expiryDate + ", securityCode="
            + securityCode + ", postalCode=" + postalCode + "]";
}

    
}
