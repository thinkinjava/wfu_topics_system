package com.tepusoft.service;

import java.math.BigInteger;
import java.util.List;

import com.tepusoft.entity.Troom;
import com.tepusoft.utils.PageUtil;

public interface TroomServiceI {

	List<Troom> findAllTrooms(String academyId,PageUtil pageUtil);

	BigInteger troomsCount();

	Troom findTroomById(String troomId);

	void deleteTroom(Troom troom);

	void saveTroom(Troom troom);

	void updateTroom(Troom troom);

	List<Troom> findAllTroomByAcademy(String dicId);

	Troom findTroomByNameAndAcd(String troom, String academyId);

}
