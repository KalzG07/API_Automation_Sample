package APIHandlerClasses.GETHandlers.GETLatestRegionStatsAPI.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("summary")
@Expose
private Summary summary;
@SerializedName("change")
@Expose
private Change change;

public Summary getSummary() {
return summary;
}

public void setSummary(Summary summary) {
this.summary = summary;
}

public Change getChange() {
return change;
}

public void setChange(Change change) {
this.change = change;
}

}