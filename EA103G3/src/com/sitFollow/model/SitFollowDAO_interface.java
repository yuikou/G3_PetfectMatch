package com.sitFollow.model;

import java.util.*;

public interface SitFollowDAO_interface {
	// �������w�q�F���Ʈw����Ʀs����H��k
	Boolean add(String memNo, String sitNo);
	Boolean del(String memNo, String sitNo);
	List<String> getAllByMemNo(String memNo);

}
