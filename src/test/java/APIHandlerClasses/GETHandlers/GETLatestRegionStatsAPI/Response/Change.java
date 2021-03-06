package APIHandlerClasses.GETHandlers.GETLatestRegionStatsAPI.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Change {

@SerializedName("total_cases")
@Expose
private Integer totalCases;
@SerializedName("active_cases")
@Expose
private Integer activeCases;
@SerializedName("deaths")
@Expose
private Integer deaths;
@SerializedName("recovered")
@Expose
private Integer recovered;
@SerializedName("critical")
@Expose
private Integer critical;
@SerializedName("tested")
@Expose
private Integer tested;
@SerializedName("death_ratio")
@Expose
private Integer deathRatio;
@SerializedName("recovery_ratio")
@Expose
private Float recoveryRatio;

public Integer getTotalCases() {
return totalCases;
}

public void setTotalCases(Integer totalCases) {
this.totalCases = totalCases;
}

public Integer getActiveCases() {
return activeCases;
}

public void setActiveCases(Integer activeCases) {
this.activeCases = activeCases;
}

public Integer getDeaths() {
return deaths;
}

public void setDeaths(Integer deaths) {
this.deaths = deaths;
}

public Integer getRecovered() {
return recovered;
}

public void setRecovered(Integer recovered) {
this.recovered = recovered;
}

public Integer getCritical() {
return critical;
}

public void setCritical(Integer critical) {
this.critical = critical;
}

public Integer getTested() {
return tested;
}

public void setTested(Integer tested) {
this.tested = tested;
}

public Integer getDeathRatio() {
return deathRatio;
}

public void setDeathRatio(Integer deathRatio) {
this.deathRatio = deathRatio;
}

public Float getRecoveryRatio() {
return recoveryRatio;
}

public void setRecoveryRatio(Float recoveryRatio) {
this.recoveryRatio = recoveryRatio;
}

}