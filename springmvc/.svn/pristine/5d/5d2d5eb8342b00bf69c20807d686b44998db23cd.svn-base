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

import com.tepusoft.dto.TroomDto;
import com.tepusoft.entity.DataDictionary;
import com.tepusoft.entity.Troom;
import com.tepusoft.service.TroomServiceI;
import com.tepusoft.utils.AcademyUtil;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.DataGrid;
import com.tepusoft.utils.JsonData;
import com.tepusoft.utils.PageUtil;

@Controller
@RequestMapping("/troomController")
public class TroomController extends BaseController {
	@Autowired
	private TroomServiceI troomServiceI;

	/**
	 * 功能：找到所有符合的教研室信息
	 * 
	 * @author Lixiangmao
	 * @create 2013/12/19
	 */
	@ResponseBody
	@RequestMapping("/findAlltrooms")
	public DataGrid findAlltrooms(PageUtil pageUtil) {
		DataGrid dataGrid = new DataGrid();
		// 得到当前登陆人 并且获得当前登陆人的院系信息
		AcademyUtil academyUtil = this.getUserAcademy();
		String academyId ="";
		if(!StringUtils.isEmpty(academyUtil)){
			academyId=academyUtil.getAcademyId();
		}
		List<Troom> troomList = troomServiceI
				.findAllTrooms(academyId, pageUtil);
		BigInteger total = troomServiceI.troomsCount();
		List<TroomDto> troomDtoList = new ArrayList<>();
		for (Troom troom : troomList) {
			TroomDto troomDto = new TroomDto();
			BeanUtils.copyProperties(troom, troomDto,
					new String[] { "academy" });
			if (!StringUtils.isEmpty(troom.getAcademy())) {
				troomDto.setAcademy(troom.getAcademy().getDicName());
			}
			troomDtoList.add(troomDto);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(troomDtoList);
		return dataGrid;
	}

	/**
	 * 功能：删除教研室信息
	 * 
	 * @author Lixiangmao
	 * @create 2013/12/19
	 */
	@ResponseBody
	@RequestMapping("/deleteTroom")
	public JsonData deleteTroom(String troomId) {
		JsonData jsonData = new JsonData();
		Troom troom = troomServiceI.findTroomById(troomId);
		try {
			if (troom != null) {
				troomServiceI.deleteTroom(troom);
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
	 * 功能：添加教研室信息
	 * 
	 * @author Lixiangmao
	 * @create 2013/12/19
	 */
	@ResponseBody
	@RequestMapping("/addTroom")
	public JsonData addTroom(String troomName) {
		JsonData jsonData = new JsonData();
		Troom troom = new Troom();
		troom.setTroomId(UUID.randomUUID().toString());
		troom.setTroomName(troomName);
		troom.setCreateTime(new Date());
		AcademyUtil academyUtil = this.getUserAcademy();
		DataDictionary dataDictionary = new DataDictionary();
		dataDictionary.setDicId(academyUtil.getAcademyId());
		troom.setAcademy(dataDictionary);
		try {
			if (troom != null) {
				troomServiceI.saveTroom(troom);
			}
			jsonData.setMessage("添加成功");
			jsonData.setStatus("true");
		} catch (Exception e) {
			jsonData.setMessage("添加失败");
			jsonData.setStatus("false");
		}
		return jsonData;
	}

	/**
	 * 功能：编辑教研室信息
	 * 
	 * @author Lixiangmao
	 * @create 2013/12/19
	 */
	@ResponseBody
	@RequestMapping("/editTroomInfo")
	public JsonData editTroomInfo(TroomDto troomDto) {
		JsonData jsonData = new JsonData();
		String troomId = troomDto.getTroomId();
		Troom troom = new Troom();
		if (!StringUtils.isEmpty(troomId)) {
			troom = troomServiceI.findTroomById(troomId);
			troom.setTroomName(troomDto.getTroomName());
		}
		try {
			if(!StringUtils.isEmpty(troom)){
				troomServiceI.updateTroom(troom);
			}
			jsonData.setMessage("修改成功");
			jsonData.setStatus("true");
		} catch (Exception e) {
			jsonData.setMessage("修改失败");
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：通过当前登录人员得到人员所在院系，根据院系得到教研室信息
	 * @author Lijinzhao
	 * @time 2013-12-20
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findTroomsByAcademy")
	public List<Troom> findTroomsByAcademy(){
		String academyId = this.getUserAcademy().getAcademyId();
		List<Troom> troomList = troomServiceI.findAllTroomByAcademy(academyId);
		for (Troom t: troomList) {
			t.setAcademy(null);
		}
		return troomList;
	}
}
