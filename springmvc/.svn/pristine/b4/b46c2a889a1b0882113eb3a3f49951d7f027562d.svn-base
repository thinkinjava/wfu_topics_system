package com.tepusoft.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.entity.Troom;
import com.tepusoft.service.TroomServiceI;
import com.tepusoft.utils.PageUtil;

@Service
public class TroomServiceImpl implements TroomServiceI {
	@Autowired
	private BaseDaoI<Troom> baseDaoI;

	@Override
	public List<Troom> findAllTrooms(String academyId,PageUtil pageUtil) {
		String hql = "from Troom t where t.academy.dicId=:academyId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("academyId", academyId);
		return baseDaoI.find(hql,map,pageUtil.getPage(),pageUtil.getRows());
	}

	@Override
	public BigInteger troomsCount() {
		String sql = "select count(*) from wfu_troom";
		return baseDaoI.countBySql(sql);
	}

	@Override
	public Troom findTroomById(String troomId) {
		return baseDaoI.get(Troom.class, troomId);
	}

	@Override
	public void deleteTroom(Troom troom) {
		baseDaoI.delete(troom);
	}

	@Override
	public void saveTroom(Troom troom) {
		baseDaoI.save(troom);
	}

	@Override
	public void updateTroom(Troom troom) {
		baseDaoI.update(troom);
	}

	@Override
	public List<Troom> findAllTroomByAcademy(String dicId) {
		// TODO Auto-generated method stub
		String hql="from Troom t where t.academy.dicId=:dicId";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("dicId", dicId);
		return baseDaoI.find(hql, map);
	}

	@Override
	public Troom findTroomByNameAndAcd(String troom, String academyId) {
		// TODO Auto-generated method stub
		String hql="from Troom t where t.troomName=:troom and t.academy.dicId=:academyId";
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("troom", troom);
		map.put("academyId", academyId);
		return baseDaoI.get(hql, map);
	}
}
