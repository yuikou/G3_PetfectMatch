package com.sitOrder.model;

import java.util.List;
import java.util.Set;

public interface SitOrderDAO_interface {
	
	public void insert(SitOrderVO sitOrderVO);
	public void update(SitOrderVO sitOrderNo);
	public SitOrderVO getByPK(String sitOrderNo);
	public Set<SitOrderVO> getByFK_memNo(String memNo);
	public Set<SitOrderVO> getByFK_sitNo(String sitNo);
	public List<SitOrderVO> getAll();
}
