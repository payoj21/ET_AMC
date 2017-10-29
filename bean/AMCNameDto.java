package com.til.bean;

import java.sql.Timestamp;

public class AMCNameDto {
	Integer id;
	Double aum;
	String fname;
	Double change;
	Double changeper;
	Timestamp Date;
	String amcName;
	int num_of_scheme, num_of_scheme_beating_benchmark1w,num_of_scheme_beating_benchmark1m;
	int num_of_scheme_beating_benchmark3m,num_of_scheme_beating_benchmark6m,num_of_scheme_beating_benchmark1y;
	int num_of_scheme_beating_benchmark3y,num_of_scheme_beating_benchmark5y,num_of_scheme_beating_benchmarkytd;
	Double per_scheme1w,per_scheme1m,per_scheme3m,per_scheme6m,per_scheme1y,per_scheme3y,per_scheme5y,per_schemeytd;
	public String getAmcName() {
		return amcName;
	}
	public void setAmcName(String amcName) {
		this.amcName = amcName;
	}
	public Double getChange() {
		return change;
	}
	public void setChange(Double change) {
		this.change = change;
	}
	public Double getChangeper() {
		return changeper;
	}
	public void setChangeper(Double changeper) {
		this.changeper = changeper;
	}
	public int getNum_of_scheme() {
		return num_of_scheme;
	}
	public void setNum_of_scheme(int num_of_scheme) {
		this.num_of_scheme = num_of_scheme;
	}
	public int getNum_of_scheme_beating_benchmark1w() {
		return num_of_scheme_beating_benchmark1w;
	}
	public void setNum_of_scheme_beating_benchmark1w(
			int num_of_scheme_beating_benchmark1w) {
		this.num_of_scheme_beating_benchmark1w = num_of_scheme_beating_benchmark1w;
	}
	public int getNum_of_scheme_beating_benchmark1m() {
		return num_of_scheme_beating_benchmark1m;
	}
	public void setNum_of_scheme_beating_benchmark1m(
			int num_of_scheme_beating_benchmark1m) {
		this.num_of_scheme_beating_benchmark1m = num_of_scheme_beating_benchmark1m;
	}
	public int getNum_of_scheme_beating_benchmark3m() {
		return num_of_scheme_beating_benchmark3m;
	}
	public void setNum_of_scheme_beating_benchmark3m(
			int num_of_scheme_beating_benchmark3m) {
		this.num_of_scheme_beating_benchmark3m = num_of_scheme_beating_benchmark3m;
	}
	public int getNum_of_scheme_beating_benchmark6m() {
		return num_of_scheme_beating_benchmark6m;
	}
	public void setNum_of_scheme_beating_benchmark6m(
			int num_of_scheme_beating_benchmark6m) {
		this.num_of_scheme_beating_benchmark6m = num_of_scheme_beating_benchmark6m;
	}
	public int getNum_of_scheme_beating_benchmark1y() {
		return num_of_scheme_beating_benchmark1y;
	}
	public void setNum_of_scheme_beating_benchmark1y(
			int num_of_scheme_beating_benchmark1y) {
		this.num_of_scheme_beating_benchmark1y = num_of_scheme_beating_benchmark1y;
	}
	public int getNum_of_scheme_beating_benchmark3y() {
		return num_of_scheme_beating_benchmark3y;
	}
	public void setNum_of_scheme_beating_benchmark3y(int num_of_scheme_beating_benchmark3y) {
		this.num_of_scheme_beating_benchmark3y = num_of_scheme_beating_benchmark3y;
	}
	public int getNum_of_scheme_beating_benchmark5y() {
		return num_of_scheme_beating_benchmark5y;
	}
	public void setNum_of_scheme_beating_benchmark5y(
			int num_of_scheme_beating_benchmark5y) {
		this.num_of_scheme_beating_benchmark5y = num_of_scheme_beating_benchmark5y;
	}
	public int getNum_of_scheme_beating_benchmarkytd() {
		return num_of_scheme_beating_benchmarkytd;
	}
	public void setNum_of_scheme_beating_benchmarkytd(
			int num_of_scheme_beating_benchmarkytd) {
		this.num_of_scheme_beating_benchmarkytd = num_of_scheme_beating_benchmarkytd;
	}
	public Double getPer_scheme1w() {
		return per_scheme1w;
	}
	public void setPer_scheme1w(Double per_scheme1w) {
		this.per_scheme1w = per_scheme1w;
	}
	public Double getPer_scheme1m() {
		return per_scheme1m;
	}
	public void setPer_scheme1m(Double per_scheme1m) {
		this.per_scheme1m = per_scheme1m;
	}
	public Double getPer_scheme3m() {
		return per_scheme3m;
	}
	public void setPer_scheme3m(Double per_scheme3m) {
		this.per_scheme3m = per_scheme3m;
	}
	public Double getPer_scheme6m() {
		return per_scheme6m;
	}
	public void setPer_scheme6m(Double per_scheme6m) {
		this.per_scheme6m = per_scheme6m;
	}
	public Double getPer_scheme1y() {
		return per_scheme1y;
	}
	public void setPer_scheme1y(Double per_scheme1y) {
		this.per_scheme1y = per_scheme1y;
	}
	public Double getPer_scheme3y() {
		return per_scheme3y;
	}
	public void setPer_scheme3y(Double per_scheme3y) {
		this.per_scheme3y = per_scheme3y;
	}
	public Double getPer_scheme5y() {
		return per_scheme5y;
	}
	public void setPer_scheme5y(Double per_scheme5y) {
		this.per_scheme5y = per_scheme5y;
	}
	public Double getPer_schemeytd() {
		return per_schemeytd;
	}
	public void setPer_schemeytd(Double per_schemeytd) {
		this.per_schemeytd = per_schemeytd;
	}
	
	public Timestamp getDate() {
		return Date;
	}
	public void setDate(Timestamp date) {
		Date = date;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getAum() {
		return aum;
	}
	public void setAum(Double aum) {
		this.aum = aum;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	@Override
	public String toString() {
		return "AMCNameDto [id=" + id + ", aum=" + aum + ", fname=" + fname
				+ ", change=" + change + ", changeper=" + changeper + ", Date="
				+ Date + ", amcName=" + amcName + ", num_of_scheme="
				+ num_of_scheme + ", num_of_scheme_beating_benchmark1w="
				+ num_of_scheme_beating_benchmark1w
				+ ", num_of_scheme_beating_benchmark1m="
				+ num_of_scheme_beating_benchmark1m
				+ ", num_of_scheme_beating_benchmark3m="
				+ num_of_scheme_beating_benchmark3m
				+ ", num_of_scheme_beating_benchmark6m="
				+ num_of_scheme_beating_benchmark6m
				+ ", num_of_scheme_beating_benchmark1y="
				+ num_of_scheme_beating_benchmark1y
				+ ", num_of_scheme_beating_benchmark3y="
				+ num_of_scheme_beating_benchmark3y
				+ ", num_of_scheme_beating_benchmark5y="
				+ num_of_scheme_beating_benchmark5y
				+ ", num_of_scheme_beating_benchmarkytd="
				+ num_of_scheme_beating_benchmarkytd + ", per_scheme1w="
				+ per_scheme1w + ", per_scheme1m=" + per_scheme1m
				+ ", per_scheme3m=" + per_scheme3m + ", per_scheme6m="
				+ per_scheme6m + ", per_scheme1y=" + per_scheme1y
				+ ", per_scheme3y=" + per_scheme3y + ", per_scheme5y="
				+ per_scheme5y + ", per_schemeytd=" + per_schemeytd + "]";
	}
	
	
	
}
