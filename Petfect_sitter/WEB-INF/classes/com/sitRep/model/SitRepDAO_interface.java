  package com.sitRep.model;

import java.util.List;

public interface SitRepDAO_interface {
	
	public void insert(SitRepVO sitRepVO);
	public void update(SitRepVO sitRepVO);
	public void delete(String sitRepNo);
	public SitRepVO getByPK(String sitOrderNo);
	public SitRepVO getByFK(String sitRepNo);
	public List<SitRepVO> getAll(); 
	
}
