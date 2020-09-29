package com.sitOrderDetail.model;

import java.util.List;

public interface SitODetailDAO_interface {
	
	public void insert(SitODetailVO sitODetailVO);
	public SitODetailVO getByPKFK(String sitOrderNo);
	public List<SitODetailVO> getAll();
}
