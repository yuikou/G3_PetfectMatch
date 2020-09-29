package com.sitOffDay.model;

import java.util.*;

//�������w�q�F���Ʈw����Ʀs����H��k
public interface SitOffDayDAO_interface {

	Boolean add(SitOffDayVO sod);
	Boolean update(SitOffDayVO sod);
	Boolean del(String SODNo);
	
	// �d�ߤw�]�w�����i�A�Ȥ���A�i���˵��B�ק�B�R��
	SitOffDayVO getByPK(String offDayNo);
	
	// �j�M�Y�A�ȶ��ءi���j���ѪA�Ȫ��O�i(�@��h)(�^�� Set)--�|���j�M�B�w����--
	Set<String> getSitByDate(String sitSrvNo, String start_date, String end_date, String time);
	
	
}
