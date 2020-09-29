package com.sitRep.model;

import java.io.Serializable;

public class SitRepVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String sitRepNo;
	private String sitOrderNo;
	private String memNo;
	private String sitRep;
	private String empNo;
	private Integer sitRepStatus;
	private Integer sitRepResult;

	public SitRepVO() {
		super();
	}
	
	public SitRepVO(String sitRepNo, String sitOrderNo, String memNo, String sitRep, String empNo, Integer sitRepStatus,
			Integer sitRepResult) {
		super();
		this.sitRepNo = sitRepNo;
		this.sitOrderNo = sitOrderNo;
		this.memNo = memNo;
		this.sitRep = sitRep;
		this.empNo = empNo;
		this.sitRepStatus = sitRepStatus;
		this.sitRepResult = sitRepResult;
	}

	public String getSitRepNo() {
		return sitRepNo;
	}

	public void setSitRepNo(String sitRepNo) {
		this.sitRepNo = sitRepNo;
	}

	public String getSitOrderNo() {
		return sitOrderNo;
	}

	public void setSitOrderNo(String sitOrderNo) {
		this.sitOrderNo = sitOrderNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getSitRep() {
		return sitRep;
	}

	public void setSitRep(String sitRep) {
		this.sitRep = sitRep;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public Integer getSitRepStatus() {
		return sitRepStatus;
	}

	public void setSitRepStatus(Integer sitRepStatus) {
		this.sitRepStatus = sitRepStatus;
	}

	public Integer getSitRepResult() {
		return sitRepResult;
	}

	public void setSitRepResult(Integer sitRepResult) {
		this.sitRepResult = sitRepResult;
	}

}
