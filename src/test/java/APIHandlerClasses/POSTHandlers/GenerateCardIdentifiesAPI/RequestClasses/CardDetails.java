package APIHandlerClasses.POSTHandlers.GenerateCardIdentifiesAPI.RequestClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardDetails {

@SerializedName("cardholderName")
@Expose
private String cardholderName;
@SerializedName("cardNumber")
@Expose
private String cardNumber;
@SerializedName("expiryDate")
@Expose
private String expiryDate;
@SerializedName("securityCode")
@Expose
private String securityCode;

public String getCardholderName() {
return cardholderName;
}

public void setCardholderName(String cardholderName) {
this.cardholderName = cardholderName;
}

public String getCardNumber() {
return cardNumber;
}

public void setCardNumber(String cardNumber) {
this.cardNumber = cardNumber;
}

public String getExpiryDate() {
return expiryDate;
}

public void setExpiryDate(String expiryDate) {
this.expiryDate = expiryDate;
}

public String getSecurityCode() {
return securityCode;
}

public void setSecurityCode(String securityCode) {
this.securityCode = securityCode;
}

}