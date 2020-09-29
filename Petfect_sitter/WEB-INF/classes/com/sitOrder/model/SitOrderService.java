package com.sitOrder.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class SitOrderService {
	
	private SitOrderDAO_interface dao;
	
	public SitOrderService() {
		dao = new SitOrderDAO();
	}
	
	public SitOrderVO insert(String memNo, String sitNo, Date sitSDate, Date sitEDate, Integer totalPrice,
			Integer orderStatus, Integer refund, Integer coupon, Integer commStar, String sitComm, String pickupFrom,
			String pickupTo) {
		
		SitOrderVO sitOrderVO = new SitOrderVO();
		sitOrderVO.setMemNo(memNo);
		sitOrderVO.setSitNo(sitNo);
		sitOrderVO.setSitSDate(sitSDate);
		sitOrderVO.setSitEDate(sitEDate);
		sitOrderVO.setTotalPrice(totalPrice);
		sitOrderVO.setOrderStatus(orderStatus);
		sitOrderVO.setRefund(refund);
		sitOrderVO.setCoupon(coupon);
		sitOrderVO.setCommStar(commStar);
		sitOrderVO.setSitComm(sitComm);
		sitOrderVO.setPickupFrom(pickupFrom);
		sitOrderVO.setPickupTo(pickupFrom);		
		dao.insert(sitOrderVO);
		
		return sitOrderVO;
	}
	public SitOrderVO update(String sitOrderNo, String memNo, String sitNo, Date sitSDate, Date sitEDate, Integer totalPrice,
			Integer orderStatus, Integer refund, Integer coupon, Integer commStar, String sitComm, String pickupFrom,
			String pickupTo) {
		
		SitOrderVO sitOrderVO = new SitOrderVO();
		sitOrderVO.setMemNo(sitOrderNo);
		sitOrderVO.setMemNo(memNo);
		sitOrderVO.setSitNo(sitNo);
		sitOrderVO.setSitSDate(sitSDate);
		sitOrderVO.setSitEDate(sitEDate);
		sitOrderVO.setTotalPrice(totalPrice);
		sitOrderVO.setOrderStatus(orderStatus);
		sitOrderVO.setRefund(refund);
		sitOrderVO.setCoupon(coupon);
		sitOrderVO.setCommStar(commStar);
		sitOrderVO.setSitComm(sitComm);
		sitOrderVO.setPickupFrom(pickupFrom);
		sitOrderVO.setPickupTo(pickupFrom);					
		dao.update(sitOrderVO);
		
		return sitOrderVO;
	}
	
	public SitOrderVO getByPK(String sitOrderNo) {
		return dao.getByPK(sitOrderNo);
	}
	
	public Set<SitOrderVO> getByFK_memNo(String memNo) {
		return dao.getByFK_memNo(memNo);
	}
	
	public Set<SitOrderVO> getByFK_sitNo(String sitNo) {
		return dao.getByFK_sitNo(sitNo);
	}

	public List<SitOrderVO> getAll() {
		return dao.getAll();
	}

	
	

}
