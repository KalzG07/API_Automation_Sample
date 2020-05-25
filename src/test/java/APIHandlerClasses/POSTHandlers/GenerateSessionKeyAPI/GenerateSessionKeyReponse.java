package APIHandlerClasses.POSTHandlers.GenerateSessionKeyAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateSessionKeyReponse {

@SerializedName("expiry")
@Expose
private String expiry;
@SerializedName("merchantSessionKey")
@Expose
private String merchantSessionKey;

public String getExpiry() {
return expiry;
}

public void setExpiry(String expiry) {
this.expiry = expiry;
}

public String getMerchantSessionKey() {
return merchantSessionKey;
}

public void setMerchantSessionKey(String merchantSessionKey) {
this.merchantSessionKey = merchantSessionKey;
}

}