package com.sitRep.model;

import java.util.List;

public class SitRepService {
	
	private SitRepDAO_interface dao;
	
	public SitRepService() {
		dao = new SitRepDAO();
	}
	
	public SitRepVO insert(String sitOrderNo, String memNo, String sitRep, String empNo, Integer sitRepStatus,
			Integer sitRepResult) {
		
		SitRepVO sitRepVO = new SitRepVO();
		sitRepVO.setSitOrderNo(sitOrderNo);
		sitRepVO.setMemNo(memNo);
		sitRepVO.setEmpNo(sitRep);
		sitRepVO.setSitRep(sitRep);
		sitRepVO.setSitRepStatus(sitRepStatus);
		sitRepVO.setSitRepResult(sitRepStatus);
		dao.insert(sitRepVO);
		
		return sitRepVO;
	}
	
	public SitRepVO update(String sitRepNo, String sitOrderNo, String memNo, String sitRep, String empNo, Integer sitRepStatus,
			Integer sitRepResult) {
		
		SitRepVO sitRepVO = new SitRepVO();
		sitRepVO.setSitRepNo(sitRepNo);
		sitRepVO.setSitOrderNo(sitOrderNo);
		sitRepVO.setMemNo(memNo);
		sitRepVO.setEmpNo(sitRep);
		sitRepVO.setSitRep(sitRep);
		sitRepVO.setSitRepStatus(sitRepStatus);
		sitRepVO.setSitRepResult(sitRepStatus);
		dao.update(sitRepVO);
		
		return sitRepVO;
	}
	
	public void delete(String sitRepNo) {
		dao.delete(sitRepNo);
	}
	
	public SitRepVO getByPK(String sitOrderNo) {
		return dao.getByPK(sitOrderNo);
	}
	
	public SitRepVO getByFK(String sitOrderNo) {
		return dao.getByFK(sitOrderNo);
	}
	
	public List<SitRepVO> getAll() {
		return dao.getAll();
	}




 
	
}
