package com.til.bean;

import java.sql.Timestamp;

public class AUMChangeDto {
	Double assetundermanagement;
	Double change, changepercent;
	int assetmanagementcompanyid;
	Timestamp date;
	public Double getAssetundermanagement() {
		return assetundermanagement;
	}
	public void setAssetundermanagement(Double assetundermanagement) {
		this.assetundermanagement = assetundermanagement;
	}
	public Double getChange() {
		return change;
	}
	public void setChange(Double change) {
		this.change = change;
	}
	public Double getChangepercent() {
		return changepercent;
	}
	public void setChangepercent(Double changepercent) {
		this.changepercent = changepercent;
	}
	public int getAssetmanagementcompanyid() {
		return assetmanagementcompanyid;
	}
	public void setAssetmanagementcompanyid(int assetmanagementcompanyid) {
		this.assetmanagementcompanyid = assetmanagementcompanyid;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
}
