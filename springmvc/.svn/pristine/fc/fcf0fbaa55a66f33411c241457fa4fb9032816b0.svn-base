package com.tepusoft.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.entity.Major;
import com.tepusoft.service.MajorServiceI;
import com.tepusoft.utils.PageUtil;


@Service
public class MajorServiceImpl implements MajorServiceI {
	@Autowired
	private BaseDaoI<Major> baseDaoI;

	@Override
	public List<Major> findAllMajors(String academyId, PageUtil pageUtil) {
		String hql = "from Major m where m.academy.dicId=:academyId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("academyId", academyId);
		return baseDaoI.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public BigInteger majorsCount() {
		String sql = "select count(*) from wfu_major";
		return baseDaoI.countBySql(sql);
	}

	@Override
	public void saveMajor(Major major) {
		baseDaoI.save(major);
	}

	@Override
	public Major findMajorByMajorId(String majorId) {
		return baseDaoI.get(Major.class,majorId);
	}

	@Override
	public void updateMajor(Major major) {
		baseDaoI.update(major);
	}

	@Override
	public void deleteMajor(Major major) {
		baseDaoI.delete(major);
		
	}

	@Override
	public List<Major> findAllMajorsByAcademy(String dicId) {
		String hql = "from Major m where m.academy.dicId=:dicId";
		Map<String, Object> map = new HashMap<>();
		map.put("dicId", dicId);
		return baseDaoI.find(hql, map);
	}

	@Override
	public Major findMajorByMajorNameAndAcademyId(String majorName,
			String academyId) {
		String hql = "from Major m where m.academy.dicId=:academyId and m.majorName=:majorName";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("academyId", academyId);
		map.put("majorName", majorName);
		return baseDaoI.get(hql, map);
	}

	@Override
	public List<Major> findAllMajors() {
		String hql = "from Major m where 1=1";
		return baseDaoI.find(hql);
	}

}
