package com.tepusoft.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tepusoft.dto.MajorDto;
import com.tepusoft.entity.DataDictionary;
import com.tepusoft.entity.Major;
import com.tepusoft.service.MajorServiceI;
import com.tepusoft.utils.AcademyUtil;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.DataGrid;
import com.tepusoft.utils.JsonData;
import com.tepusoft.utils.PageUtil;


@Controller
@RequestMapping("/majorController")
public class MajorController extends BaseController {


	@Autowired
	private MajorServiceI majorServiceI;


	/**
	 * 功能：添加学生专业
	 * 
	 * @author Lixiangmao
	 * @create 2013/12/18
	 */
	@ResponseBody
	@RequestMapping("/findAllMajors")
	public DataGrid findAllMajors(PageUtil pageUtil) {
		DataGrid dataGrid = new DataGrid();
		// 得到当前登陆人 并且得到当前登陆人的院系
		AcademyUtil academyUtil = this.getUserAcademy();
		String academyId="";
		if(!StringUtils.isEmpty(academyUtil)){
			academyId=academyUtil.getAcademyId();
		}
		
		List<Major> majorList = majorServiceI
				.findAllMajors(academyId, pageUtil);
		BigInteger total = majorServiceI.majorsCount();
		List<MajorDto> majorDtoList = new ArrayList<>();
		for (Major major : majorList) {
			MajorDto majorDto = new MajorDto();
			BeanUtils.copyProperties(major, majorDto,
					new String[] { "academy" });
			if (!StringUtils.isEmpty(major.getAcademy()))
				majorDto.setAcademy(major.getAcademy().getDicName());
			majorDtoList.add(majorDto);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(majorDtoList);
		return dataGrid;
	}

	/**
	 * 功能：添加专业
	 * 
	 * @author Lixiangmao
	 * @create 2013/12/18
	 */
	@ResponseBody
	@RequestMapping("/addMajor")
	public JsonData addMajor(String majorName,String academy){
		JsonData jsonData = new JsonData();
		Major major = new Major();
		major.setMajorId(UUID.randomUUID().toString());
		major.setMajorName(majorName);
		major.setCreateTime(new Date());//得到当前时间

		AcademyUtil academyUtil = this.getUserAcademy();
		DataDictionary dataDictionary = new DataDictionary();
		dataDictionary.setDicId(academyUtil.getAcademyId());
		major.setAcademy(dataDictionary);
		
		try {
			majorServiceI.saveMajor(major);
			jsonData.setMessage("保存成功");
			jsonData.setStatus("true");
		} catch (Exception e) {
			jsonData.setMessage("保存失败");
			jsonData.setStatus("false");
		}
		return jsonData;
	}

	/**
	 * 功能:编辑专业
	 * 
	 * @author Lixiangmao
	 * @create 2013/12/18
	 */
	@ResponseBody
	@RequestMapping("/editMajorInfo")
	public JsonData editMajorInfo(MajorDto majorDto) {
		JsonData jsonData = new JsonData();
		Major major = new Major();
		if (majorDto.getMajorId() != null && !majorDto.getMajorId().equals("")) {
			major = majorServiceI.findMajorByMajorId(majorDto.getMajorId());
			major.setMajorName(majorDto.getMajorName());
		}
		try {
			majorServiceI.updateMajor(major);
			jsonData.setMessage("编辑成功");
			jsonData.setStatus("true");
		} catch (Exception e) {
			jsonData.setMessage("编辑失败");
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：删除专业
	 * @author Lixiangmao
	 * @create 2013/12/18
	 */
	@ResponseBody
	@RequestMapping("/deleteMajor")
	public JsonData deleteMajor(String majorId){
		JsonData jsonData = new JsonData();
		Major major = majorServiceI.findMajorByMajorId(majorId);
		
		try {
			if(major!=null){
				majorServiceI.deleteMajor(major);
			}
			jsonData.setMessage("删除成功");
			jsonData.setStatus("true");
		} catch (Exception e) {
			jsonData.setMessage("删除失败");
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：根据登陆教师所在的隶属学院，找到隶属于该学院的所有专业
	 * @author Lixiangmao
	 * @create 2013/12/20
	 */
	@ResponseBody
	@RequestMapping("/findMajorsByAcademy")
	public List<Major> findMajorsByAcademy(){
 		String academyId = this.getUserAcademy().getAcademyId();
		List<Major> majorList = majorServiceI.findAllMajorsByAcademy(academyId);
		for(Major major : majorList){
			major.setAcademy(null);
		}
		return majorList;
	}
}
