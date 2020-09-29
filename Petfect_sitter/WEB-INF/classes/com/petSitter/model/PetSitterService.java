package com.petSitter.model;

import java.util.List;

public class PetSitterService {
	
	private PetSitterDAO_interface dao;
	
	public PetSitterService() {
		dao = new PetSitterDAO();
	}
	
	public PetSitterVO insert(String memNo, String sitInfo, String srvSTime, String srvETime, String bankCode,
			String bankAcc) {
		
		PetSitterVO petSitterVO = new PetSitterVO();
		petSitterVO.setMemNo(memNo);
		petSitterVO.setSitInfo(sitInfo);
		petSitterVO.setSrvSTime(srvSTime);
		petSitterVO.setSrvETime(srvETime);		
		petSitterVO.setBankCode(bankCode);
		petSitterVO.setBankAcc(bankAcc);
		dao.insert(petSitterVO);
		
		return petSitterVO;
	}
	
	public PetSitterVO update(String sitNo, String memNo, String sitInfo, String srvSTime, String srvETime, String bankCode,
			String bankAcc, Integer sitAccStatus, Integer totalComm, Integer totalCus, Integer repeatCus) {
		
		PetSitterVO petSitterVO = new PetSitterVO();
		petSitterVO.setSitNo(sitNo);
		petSitterVO.setMemNo(memNo);
		petSitterVO.setSitInfo(sitInfo);
		petSitterVO.setSrvSTime(srvSTime);
		petSitterVO.setSrvETime(srvETime);		
		petSitterVO.setBankCode(bankCode);
		petSitterVO.setBankAcc(bankAcc);
		petSitterVO.setSitAccStatus(sitAccStatus);
		petSitterVO.setTotalComm(totalComm);
		petSitterVO.setTotalCus(totalCus);
		petSitterVO.setRepeatCus(repeatCus);
		dao.update(petSitterVO);
		
		return petSitterVO;	
	}
	
	public PetSitterVO getByPK(String sitNo) {
		return dao.getByPK(sitNo);
	}
	
	public List<PetSitterVO> getAll() {
		return dao.getAll();
	}



}
