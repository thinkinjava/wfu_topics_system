package com.tepusoft.service;

import java.util.List;

import com.tepusoft.entity.DataDictionary;

public interface DataDictionaryServiceI {

	public List<DataDictionary> findAllDataDictionary();

	public void saveDataDictionary(DataDictionary dataDictionary);

	public DataDictionary findDataDicById(String dicId);

	public void deleteDataDic(DataDictionary dataDictionary);

	public void updateDataDic(DataDictionary dataDictionary);

	public DataDictionary findByMark(String mark);

	public List<DataDictionary> findByPid(String dicId);
	public DataDictionary findDataDicByName(String dicName);

}
