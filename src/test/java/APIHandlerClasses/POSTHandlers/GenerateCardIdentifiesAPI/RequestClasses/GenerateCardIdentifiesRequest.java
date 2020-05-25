package APIHandlerClasses.POSTHandlers.GenerateCardIdentifiesAPI.RequestClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateCardIdentifiesRequest {

@SerializedName("cardDetails")
@Expose
private CardDetails cardDetails;

public CardDetails getCardDetails() {
return cardDetails;
}

public void setCardDetails(CardDetails cardDetails) {
this.cardDetails = cardDetails;
}

}