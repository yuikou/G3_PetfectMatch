package com.petSitter.model;

import java.util.List;

public interface PetSitterDAO_interface {
	
	public void insert(PetSitterVO petSitterVO);
	public void update(PetSitterVO petSitterVO);
	public PetSitterVO getByPK(String sitNo);
	public List<PetSitterVO> getAll();
}
