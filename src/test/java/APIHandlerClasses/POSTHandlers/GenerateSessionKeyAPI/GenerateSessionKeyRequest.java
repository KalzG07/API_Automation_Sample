package APIHandlerClasses.POSTHandlers.GenerateSessionKeyAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenerateSessionKeyRequest {

@SerializedName("vendorName")
@Expose
private String vendorName;

public String getVendorName() {
return vendorName;
}

public void setVendorName(String vendorName) {
this.vendorName = vendorName;
}

}