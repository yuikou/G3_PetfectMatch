package com.sitPhoto.model;

import java.util.List;

public class SitPhotoService {
	
	private SitPhotoDAO_interface dao;
	
	public SitPhotoService() {
		dao = new SitPhotoDAO();
	}
	
	public SitPhotoVO add(String sitNo, byte[] sitPhoto) {
		
		SitPhotoVO sitPhotoVO = new SitPhotoVO();
		sitPhotoVO.setSitNo(sitNo);
		sitPhotoVO.setSitPhoto(sitPhoto);
		dao.add(sitPhotoVO);
		
		return sitPhotoVO;
		
	}
	
	public void delete(String sitPNo) {
		dao.delete(sitPNo);
	}
	
	public List<SitPhotoVO> getAll() {
		return dao.getAll();
	}

}
