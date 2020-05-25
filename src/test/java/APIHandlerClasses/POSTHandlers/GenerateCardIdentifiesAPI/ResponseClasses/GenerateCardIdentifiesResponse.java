package APIHandlerClasses.POSTHandlers.GenerateCardIdentifiesAPI.ResponseClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateCardIdentifiesResponse {

@SerializedName("cardIdentifier")
@Expose
private String cardIdentifier;
@SerializedName("expiry")
@Expose
private String expiry;
@SerializedName("cardType")
@Expose
private String cardType;

public String getCardIdentifier() {
return cardIdentifier;
}

public void setCardIdentifier(String cardIdentifier) {
this.cardIdentifier = cardIdentifier;
}

public String getExpiry() {
return expiry;
}

public void setExpiry(String expiry) {
this.expiry = expiry;
}

public String getCardType() {
return cardType;
}

public void setCardType(String cardType) {
this.cardType = cardType;
}

}