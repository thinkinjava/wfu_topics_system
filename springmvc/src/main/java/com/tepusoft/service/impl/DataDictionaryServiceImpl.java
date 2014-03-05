package com.tepusoft.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.entity.DataDictionary;
import com.tepusoft.service.DataDictionaryServiceI;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryServiceI {

	@Autowired
	private BaseDaoI<DataDictionary> baseDaoI;
	@Override
	public List<DataDictionary> findAllDataDictionary() {
		// TODO Auto-generated method stub
		String hql="from DataDictionary d";
		return baseDaoI.find(hql);
	}
	@Override
	public void saveDataDictionary(DataDictionary dataDictionary) {
		// TODO Auto-generated method stub
		 baseDaoI.save(dataDictionary);
	}
	@Override
	public DataDictionary findDataDicById(String dicId) {
		// TODO Auto-generated method stub
		return baseDaoI.get(DataDictionary.class, dicId);
	}
	@Override
	public void deleteDataDic(DataDictionary dataDictionary) {
		// TODO Auto-generated method stub
		baseDaoI.delete(dataDictionary);
	}
	@Override
	public void updateDataDic(DataDictionary dataDictionary) {
		// TODO Auto-generated method stub
		baseDaoI.update(dataDictionary);
	}
	@Override
	public DataDictionary findByMark(String mark) {
		// TODO Auto-generated method stub
		String hql="from DataDictionary d where d.dicMark=:mark";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("mark", mark);
		return baseDaoI.get(hql, map);
	}
	@Override
	public List<DataDictionary> findByPid(String dicId) {
		// TODO Auto-generated method stub
		String hql="from DataDictionary d where d.dicPid=:dicId";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("dicId", dicId);		
		return baseDaoI.find(hql, map);
	}
	@Override
	public DataDictionary findDataDicByName(String dicName) {
		// TODO Auto-generated method stub
		String hql="from DataDictionary d where d.dicName=:dicName";
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("dicName", dicName);
		return baseDaoI.get(hql, map);
	}

}
