package com.sitLic.model;

import java.util.List;

public interface SitLicDAO_interface {
	// �������w�q�F���Ʈw����Ʀs����H��k
	void add(SitLicVO sitLic);
	void update(SitLicVO sitLic);
	SitLicVO getOneLicByPK(String licNo);
	List<SitLicVO> getSitAllLic(String sitNo);
	List<SitLicVO> getAllForEmp(Integer licStatus);

}
