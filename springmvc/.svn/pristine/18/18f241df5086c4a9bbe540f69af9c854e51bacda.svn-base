package com.tepusoft.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tepusoft.dto.DataDictionaryDto;
import com.tepusoft.entity.DataDictionary;
import com.tepusoft.service.DataDictionaryServiceI;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.JsonData;

@Controller
@RequestMapping("/dataDictionaryController")
public class DataDictionaryController extends BaseController{
	
	@Autowired
	private DataDictionaryServiceI dataDictionaryServiceI;
	/**
	 * 功能：得到所有的数据字典
	 * @author Lijinzhao
	 * @time 2013-12-17
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllDataDictionary")
	public List<DataDictionary> findAllDataDictionary(){
		List<DataDictionary> dataList=dataDictionaryServiceI.findAllDataDictionary();
		return dataList;
	}
	/**
	 * 功能保存数据字典
	 * @author Lijinzhao
	 * @time2013-12-17
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveDataDictionary")
	public JsonData saveDataDictionary(DataDictionaryDto dataDictionaryDto){
		JsonData jsonData = new JsonData();
		DataDictionary dataDictionary = new DataDictionary();
		BeanUtils.copyProperties(dataDictionaryDto, dataDictionary,new String[]{"dicId"});
		dataDictionary.setDicId(UUID.randomUUID().toString());
		try {
			dataDictionaryServiceI.saveDataDictionary(dataDictionary);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：删除一个节点
	 * @author Lijinzhao
	 * @time 2013-12-17
	 * @param dicId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteDataDictionary")
	public JsonData deleteDataDictionary(String dicId){
		JsonData jsonData = new JsonData();
		DataDictionary dataDictionary = dataDictionaryServiceI.findDataDicById(dicId);
		try {
			dataDictionaryServiceI.deleteDataDic(dataDictionary);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能编辑子节点
	 * @author Lijinzhao
	 * @time 2013-12-17
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateDataDictionary")
	public JsonData updateDataDictionary(DataDictionaryDto dataDictionaryDto){
		JsonData jsonData = new JsonData();
		//查找DataDictionary
		DataDictionary dataDictionary=dataDictionaryServiceI.findDataDicById(dataDictionaryDto.getDicId());
		BeanUtils.copyProperties(dataDictionaryDto, dataDictionary);
		try {
			dataDictionaryServiceI.updateDataDic(dataDictionary);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：通过传过来的标示查询数据字典
	 * @author Lijinzhao
	 * @time 2013-12-19
	 * @param mark
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByMark")
	public List<DataDictionary> findByMark(String mark){ //通过标示查询数据字典
		DataDictionary dataDictionary=dataDictionaryServiceI.findByMark(mark);
		List<DataDictionary> dataDicList =dataDictionaryServiceI.findByPid(dataDictionary.getDicId());
		return dataDicList;
	}
	
}
