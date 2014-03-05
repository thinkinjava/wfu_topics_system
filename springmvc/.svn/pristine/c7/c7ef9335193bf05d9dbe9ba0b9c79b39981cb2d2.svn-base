package com.topic.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tepusoft.entity.DataDictionary;
import com.tepusoft.entity.TeacherInfo;
import com.tepusoft.service.TeaServiceI;
import com.tepusoft.utils.AcademyUtil;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.DataGrid;
import com.tepusoft.utils.JsonData;
import com.tepusoft.utils.Levenshtein;
import com.tepusoft.utils.PageUtil;
import com.topic.dto.ChooseTopicDto;
import com.topic.dto.TopicDto;
import com.topic.entity.ChooseTopic;
import com.topic.entity.Topic;
import com.topic.service.ChooseTopicServiceI;
import com.topic.service.TeaTopicServiceI;

@Controller
@RequestMapping("/teaTopicController")
public class TeaTopicController extends BaseController{
	
	@Autowired
	private TeaTopicServiceI teaTopicServiceI;
	@Autowired
	private TeaServiceI teaServiceI;
	@Autowired
	private ChooseTopicServiceI chooseTopicServiceI;

	@ResponseBody
	@RequestMapping("/findAllTopByOwn")
	public DataGrid findAllTopByOwn(PageUtil pageUtil){
		DataGrid dataGrid = new DataGrid();
		String userName=this.getOwnUserName();
		TeacherInfo teacherInfo = teaServiceI.findByUserName(userName);
		String teaId="";
		if(teacherInfo!=null){
			teaId=teacherInfo.getTeaId();
		}
		BigInteger count=teaTopicServiceI.findAllTopicByOwnCount(teaId);
		List<Topic> topicList=teaTopicServiceI.findAllTopicByOwn(teaId,pageUtil);
		List<TopicDto> topicDtoList=new ArrayList<>();
		for(Topic topic: topicList){
			TopicDto topicDto = new TopicDto();
			BeanUtils.copyProperties(topic, topicDto,new String[]{"topType","academy","teacherInfo","topicMajorIds"});
			if(topic.getTopType()!=null){
				topicDto.setTopicType(topic.getTopType().getDicName());
			}
			topicDtoList.add(topicDto);
		}
		dataGrid.setTotal(count);
		dataGrid.setRows(topicDtoList);
		return dataGrid;
		
	}
	/**
	 * 功能：教师删除
	 * @author Lijinzhao
	 * @time 2013-12-22
	 * @param topicId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletetopic")
	public JsonData deletetopic(String topicId){
		JsonData jsonData = new JsonData();
		Topic topic= teaTopicServiceI.findByTopicId(topicId);
		try {
			teaTopicServiceI.deletetopic(topic);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：保存课题信息
	 * @author Lijinzhao
	 * @time 2013-12-22
	 * @param topicDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveTopic")
	public JsonData saveTopic(TopicDto topicDto){
		JsonData jsonData = new JsonData();
		Topic topic = new Topic();
		String topicName=replaceBlank(topicDto.getTopicName()); //去除空格制表符等
		topic.setTopicId(UUID.randomUUID().toString());
		topic.setIsSelect("0");
		AcademyUtil academy =this.getUserAcademy();
		if(academy!=null){
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setDicId(academy.getAcademyId());
			topic.setAcademy(dataDictionary);
		}
		TeacherInfo teacherInfo = teaServiceI.findByUserName(this.getOwnUserName());
		topic.setTeacherInfo(teacherInfo);
		topic.setTopicCreateTime(new Date());
		topic.setTopicName(topicName);
		topic.setTopicMajorIds(topicDto.getTopicMajorIds());
		topic.setTopicMajorNames(topicDto.getTopicMajorNames());
		if(!StringUtils.isEmpty(topicDto.getTopicType())){  //如果类型不为空,保存类型
			DataDictionary topicType= new DataDictionary();
			topicType.setDicId(topicDto.getTopicType());
			topic.setTopType(topicType);
		}
		topic.setTeacherName(teacherInfo.getTeaName());
		try {
			teaTopicServiceI.saveTopic(topic);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：更新课题
	 * @author Lijinzhao
	 * @time 2013-12-22
	 * @param topicDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateTopic")
	public JsonData updateTopic(TopicDto topicDto){
		JsonData jsonData = new JsonData();
		//因为只更新课题的几个字段，可以从课题表中找出数据然后修改
		Topic topic = teaTopicServiceI.findByTopicId(topicDto.getTopicId());
		topic.setTopicName(replaceBlank(topicDto.getTopicName()));
		topic.setTopicMajorIds(topicDto.getTopicMajorIds());
		topic.setTopicMajorNames(topicDto.getTopicMajorNames());
		if(!StringUtils.isEmpty(topicDto.getTopicType())){//如果课题类型不为空
			DataDictionary topicType = new DataDictionary();
			topicType.setDicId(topicDto.getTopicType());
			topic.setTopType(topicType);
		}
		try {
			teaTopicServiceI.updateTopic(topic);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	
	/**
	 * 功能：除字符串中的空格、回车、换行符、制表符
	 * @author Lijinzhao
	 * @time 2013-12-22
	 * @param str
	 * @return
	 */
	 public static String replaceBlank(String str) {
		  String dest = "";
		 	if (str!=null) {
		 	      Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		 	      Matcher m = p.matcher(str);
		 	      dest = m.replaceAll("");
		 	        }
		 	        return dest;
	}
	 /**
	  * 功能：验证是否存在该课题
	  * @param topicName
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping("/isEmptyTopicName")
	 public JsonData isEmptyTopicName(String topicName){
		 JsonData jsonData = new JsonData();
		 BigInteger count=teaTopicServiceI.getCountByTopicName(topicName);
		 if(count.toString().equals("0")){ //说明没有重复值
			 jsonData.setStatus("false");
		 }else{
			 jsonData.setStatus("true");
		 }
		 return jsonData;
	 }
	 
	 /**
	  * 功能：验证论文题目的相似度
	  * @author Lijinzhao
	  * @time 2013012025
	  * */
	 @ResponseBody
	 @RequestMapping("/similarityTopicName")
	 public JsonData similarityTopicName(String topicName){
		 JsonData jsonData = new JsonData();
		 AcademyUtil academyId = this.getUserAcademy();
		 List<Topic> topicList=teaTopicServiceI.findAllTopicByAcademyId(academyId.getAcademyId());
		 for(Topic t :topicList){
			 if(Levenshtein.levenshtein(t.getTopicName(), topicName) >= 0.9){
				 jsonData.setStatus("true");
				 break;
			 }
		 }
		 return jsonData;
	 }
	 /**
	  * 功能：教师已选课题
	  * @author Lixiangmao
	  * @create 2013/12/26
	  */
	 @ResponseBody
	 @RequestMapping("/findAllChoosedTopic")
	 public DataGrid findAllChoosedTopic(PageUtil pageUtil){
		 DataGrid dataGrid = new DataGrid();
		 String userName = this.getOwnUserName();
		 TeacherInfo teacherInfo = teaServiceI.findTeaByUserName(userName);
		 String teacherId = teacherInfo.getTeaId();
		 BigInteger total = chooseTopicServiceI.findAllChoosedTopicCountByTeacherId(teacherId);
		 List<ChooseTopic> choosedTopicList = chooseTopicServiceI.findAllChoosedTopicByTeacherId(teacherId,pageUtil);
		 List<ChooseTopicDto> choosedTopicDtoList = new ArrayList<ChooseTopicDto>();
		 for(ChooseTopic chooseTopic : choosedTopicList){
			 ChooseTopicDto chooseTopicDto = new ChooseTopicDto();
			 chooseTopicDto.setChoosedId(chooseTopic.getChoosedId());
			 chooseTopicDto.setTopicName(chooseTopic.getTopicId().getTopicName());
			 chooseTopicDto.setTopicType(chooseTopic.getTopicId().getTopType().getDicName());
			 chooseTopicDto.setChoosedStudentName(chooseTopic.getStudentId().getStuName());
			 chooseTopicDto.setStudentPhone(chooseTopic.getStudentId().getStuPhone());
			 chooseTopicDto.setStudentEmail(chooseTopic.getStudentId().getStuEmail());
			 chooseTopicDto.setStudentMajor(chooseTopic.getStudentId().getStuMajor().getMajorName());
			 choosedTopicDtoList.add(chooseTopicDto);
		 }
		 dataGrid.setRows(choosedTopicDtoList);
		 dataGrid.setTotal(total);
		 return dataGrid;
	 }
	 /**
	  * 功能：退选学生
	  * 退选学生流程：确认退选，先将该条课题信息isSelect字段置为0 
	  * 清选中课题表
	  * @author Lixiangmao
	  * @create 2013/12/27
	  */
	 @ResponseBody
	 @RequestMapping("/topicNoChoosed")
	 public JsonData topicNoChoosed(String choosedId){
		 JsonData jsonData = new JsonData();
		 ChooseTopic chooseTopic = new ChooseTopic();
		 Topic topic = new Topic();
		 String topicId = "";
		 if(!StringUtils.isEmpty(choosedId)){
			 chooseTopic = chooseTopicServiceI.findChoosedTopicByChoosedId(choosedId);
	         topicId = chooseTopic.getTopicId().getTopicId();
		 }
		 if(!StringUtils.isEmpty(topicId)){
			 topic = teaTopicServiceI.findByTopicId(topicId);
		 }
		 //清表 置0
		 try {
			 if(!StringUtils.isEmpty(chooseTopic)&&!StringUtils.isEmpty(topic)){
				 chooseTopicServiceI.deleteChoosedTopic(chooseTopic);
				 topic.setIsSelect("0");
				 teaTopicServiceI.updateTopic(topic);
				 jsonData.setStatus("true");
				 jsonData.setMessage("退选成功");
			 }else {
				jsonData.setStatus("false");
				jsonData.setMessage("退选失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
		
	 }
}
