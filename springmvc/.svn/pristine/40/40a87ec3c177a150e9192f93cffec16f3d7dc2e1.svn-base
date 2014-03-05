package com.topic.controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.tepusoft.utils.ExportExcelUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tepusoft.dto.StudentInfoDto;
import com.tepusoft.entity.Major;
import com.tepusoft.entity.StudentInfo;
import com.tepusoft.service.MajorServiceI;
import com.tepusoft.service.StuInfServiceI;
import com.tepusoft.utils.AcademyUtil;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.Constants;
import com.tepusoft.utils.DataGrid;
import com.tepusoft.utils.DataGridOther;
import com.tepusoft.utils.JsonData;
import com.tepusoft.utils.PageUtil;
import com.topic.dto.ChooseTopicDto;
import com.topic.dto.TopicDto;
import com.topic.entity.ChooseTopic;
import com.topic.entity.Topic;
import com.topic.service.ChooseTopicServiceI;
import com.topic.service.TeaTopicServiceI;

@Controller
@RequestMapping("/studentTopicController")
public class StudentTopicController extends BaseController {
	@Autowired
	private TeaTopicServiceI teaTopicServiceI;
	@Autowired
	private StuInfServiceI stuInfServiceI;
	@Autowired
	private ChooseTopicServiceI chooseTopicServiceI;
	@Autowired
	private MajorServiceI majorServiceI;
	/**
	 * 功能：在页面上显示登陆学生所属院系所有的教师题目
	 * 
	 * @author Lixiangmao
	 * @create 2013/12/24 午觉没睡好啊T-T
	 */
	@ResponseBody
	@RequestMapping("/findAllTopByStuAdademy")
	public DataGrid findAllTopByStuAdademy(PageUtil pageUtil,String searchValue,String searchName) {
		DataGrid dataGrid = new DataGrid();
		Map<String, Object> map=new HashMap<String, Object>();
		if (null!=searchValue&&!"".equals(searchValue))
		{
			map.put(searchName, Constants.GET_SQL_LIKE+searchValue+Constants.GET_SQL_LIKE);
		}
		// 首先得到当前登陆学生的院系
		AcademyUtil academyUtil = this.getUserAcademy();
		String academyId = academyUtil.getAcademyId();
		List<Topic> topicList = new ArrayList<Topic>();
		BigInteger total = null;
		String userName = this.getOwnUserName();
		StudentInfo studentInfo = stuInfServiceI.findStuByUserName(userName);
		String majorId = "";
		if(!StringUtils.isEmpty(studentInfo)){
			majorId=studentInfo.getStuMajor().getMajorId();
		}
		// 判断是否为空
		if (!StringUtils.isEmpty(academyId)&&!StringUtils.isEmpty(majorId)) {
			// 根据当前登陆学生的院系去查找所有属于该学院的教师题目
			topicList = teaTopicServiceI.findAllTopicByAcademyId(pageUtil,
					academyId,majorId,map);
			total = teaTopicServiceI.findAllTopicByAcademyCount(academyId,majorId,map);
		}
		List<TopicDto> topicDtoList = new ArrayList<>();
		for (Topic topic : topicList) {
			TopicDto topicDto = new TopicDto();
			BeanUtils.copyProperties(topic, topicDto,
					new String[] { "topType" });
			topicDto.setTopicPersonName(topic.getTeacherInfo().getTeaName());
			topicDto.setTopicType(topic.getTopType().getDicName());
			topicDto.setTopicPersonId(topic.getTeacherInfo().getTeaId());
			topicDto.setTopicPersonTitle(topic.getTeacherInfo().getTeaTitle().getDicName());
			topicDtoList.add(topicDto);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(topicDtoList);
		return dataGrid;
	}

	/**
	 * 功能：选题正式开始 
	 * 1 首先判断选题表中是否已存在该课题，如果存在就提示该课题已经被选择.
	 * 2 更新教师课题表的数据.
	 * @author Lijinzhao
	 * @time 2013-12-26
	 */
	@ResponseBody
	@RequestMapping("/saveChooseTopic")
	public JsonData saveChooseTopic(TopicDto topicDto) {
		JsonData jsonData = new JsonData();
		String topicId = topicDto.getTopicId();
		String userName=this.getOwnUserName();
		StudentInfo studentInfo = stuInfServiceI.findStuByUserName(userName);
		if(isOwnHasTopic(studentInfo.getStutId())){//判断该学生是否已经选题
			jsonData.setStatus("false");
			jsonData.setMessage("对不起，您已经选择课题不能选择其他课题！！！");
		}else{
			if(isHasTheTopic(topicId)){    //这个课题是不是已经被选中
				jsonData.setStatus("false");
				jsonData.setMessage("对不起，该课题已经被他人选中请选择其他课题！！！");
			}else{
				if(isOkChooseByTeaId(topicDto.getTopicPersonId(),topicDto.getTopicPersonTitle())){//如果教师所带学生满足要求
					//开示保存
					Topic topic = teaTopicServiceI.findByTopicId(topicId);
					ChooseTopic chooseTopic = new ChooseTopic();
					chooseTopic.setChoosedId(UUID.randomUUID().toString());
					chooseTopic.setTopicId(topic);
					chooseTopic.setTeacherId(topic.getTeacherInfo());
					chooseTopic.setStudentId(studentInfo);
					chooseTopic.setAcademyId(topic.getTeacherInfo().getTeaAcademy());
					chooseTopic.setCreateTime(new Date());
					try {
						chooseTopicServiceI.saveChoosedTopic(chooseTopic);
						topic.setIsSelect("1");
						teaTopicServiceI.updateTopic(topic);
						jsonData.setStatus("true");
						jsonData.setMessage("保存成功");
					} catch (Exception e) {
						// TODO: handle exception
						jsonData.setStatus("flase");
						jsonData.setMessage("对不起，该课题已经被他人选中请选择其他课题！！！");
					}
				}else{ //如果不满足，把该教师的其他课题设置成不可见
					try {
						chooseTopicServiceI.updateTopicStatus("2",topicDto.getTopicPersonId());
						jsonData.setStatus("flase");
						jsonData.setMessage("该教师所带课题已超过上限，请选择其他教师课题");
					} catch (Exception e) {
						// TODO: handle exception
						jsonData.setStatus("flase");
						jsonData.setMessage("选题异常，请重新选择");
					}
					
				}
			}
		}

		
		return jsonData;
	}
	/**
	 * 功能：判断该课题是不是已经被选中
	 * @author Lijinzhao
	 * @time 2013-12-26
	 * @param topicId
	 * @return
	 */
	public boolean isHasTheTopic(String topicId){
		BigInteger a=chooseTopicServiceI.getTopicCount(topicId);
		if(a.toString().equals("0")){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 功能：判断自己是否已选择题目
	 * @author Lijinzhao
	 * @time 2013-12-26
	 * @param stuId
	 * @return
	 */
	public boolean isOwnHasTopic(String stuId){
		BigInteger a=chooseTopicServiceI.getOwnHasTopicCount(stuId);
		if(a.toString().equals("0")){
			return false;
		}else{
			return true;
		}
	}
	/***
	 * 功能：判断教师选题是否满足要求
	 * 讲师，硕士只能带四个课题
	 * 博士，副教授只能带六个课题
	 * 教授能带八个课题
	 */
	public boolean isOkChooseByTeaId(String teaId,String titleName){
		BigInteger a;
		switch (titleName) {
			case "讲师":
				a= chooseTopicServiceI.findAllChoosedTopicCountByTeacherId(teaId);
				if(a.intValue()>=4){
					return false;
				}else{
					return true;
				}
			case "硕士":
				a= chooseTopicServiceI.findAllChoosedTopicCountByTeacherId(teaId);
				if(a.intValue()>=4){
					return false;
				}else{
					return true;
				}
			case "博士":
				a= chooseTopicServiceI.findAllChoosedTopicCountByTeacherId(teaId);
				if(a.intValue()>=6){
					return false;
				}else{
					return true;
				}
			case "副教授":
				a= chooseTopicServiceI.findAllChoosedTopicCountByTeacherId(teaId);
				if(a.intValue()>=6){
					return false;
				}else{
					return true;
				}
			case "教授":
				a= chooseTopicServiceI.findAllChoosedTopicCountByTeacherId(teaId);
				if(a.intValue()>=8){
					return false;
				}else{
					return true;
				}
			default:
				return false;
			}
	}
	
	
	/**
	 * 功能：搜素课题
	 * 
	 * @author Lixiangmao
	 * @create 2013/12/25 
	 */
	@ResponseBody
	@RequestMapping("/searchTopic")
	public DataGridOther searchTopic(TopicDto topicDto){
		DataGridOther dataGrid = new DataGridOther();
		//全局变量
		List<TopicDto> afterSearchTopicSDtoList = new ArrayList<TopicDto>(); 
		AcademyUtil academyUtil = this.getUserAcademy();
		List<Topic> afterSearchTopicList = teaTopicServiceI.
				findAllTopicByTopicNameOrTopicTypeOrTopicPersonName(topicDto.getTopicName(),topicDto.getTopicType(),topicDto.getTopicPersonName(),academyUtil.getAcademyId());
		for(Topic topic : afterSearchTopicList){
			TopicDto topicDto2 = new TopicDto();
			BeanUtils.copyProperties(topic, topicDto2,
					new String[] { "topType" });
			topicDto2.setTopicPersonName(topic.getTeacherInfo().getTeaName());
			topicDto2.setTopicType(topic.getTopType().getDicName());
			topicDto2.setTopicPersonTitle(topic.getTeacherInfo().getTeaTitle().getDicName());
			afterSearchTopicSDtoList.add(topicDto2);
		}
		dataGrid.setRows(afterSearchTopicSDtoList);
		dataGrid.setTotal(afterSearchTopicSDtoList.size());
		return dataGrid;
	}
	/**
	 * 功能 ：学生已选课题
	 * @author Lixiangmao
	 * @create 2013/12/25
	 */
	@ResponseBody
	@RequestMapping("/findAllChoosedTopic")
	public DataGrid findAllChoosedTopic(PageUtil pageUtil){
		DataGrid dataGrid = new DataGrid();
		List<ChooseTopic> allChoosedTopicList = new ArrayList<ChooseTopic>();
		List<ChooseTopicDto> allChoosedTopicDtoList = new ArrayList<ChooseTopicDto>();
		String userName = this.getOwnUserName();
		StudentInfo studentInfo = stuInfServiceI.findStuByUserName(userName);
		String stuId="";
		if(studentInfo!=null){
			stuId=studentInfo.getStutId();
		}
		allChoosedTopicList = chooseTopicServiceI.findAllChoosedTopic(pageUtil,stuId);
		BigInteger total = chooseTopicServiceI.findAllChoosedTopicCount();
		for(ChooseTopic chooseTopic : allChoosedTopicList){
			ChooseTopicDto chooseTopicDto = new ChooseTopicDto();
			chooseTopicDto.setTopicName(chooseTopic.getTopicId().getTopicName());
			chooseTopicDto.setTopicType(chooseTopic.getTopicId().getTopType().getDicName());
			chooseTopicDto.setTopicPersonName(chooseTopic.getTeacherId().getTeaName());
			chooseTopicDto.setTeaPhone(chooseTopic.getTeacherId().getTeaPhone());
			chooseTopicDto.setTeaEmail(chooseTopic.getTeacherId().getTeaEmail());
			allChoosedTopicDtoList.add(chooseTopicDto);
		}
		dataGrid.setRows(allChoosedTopicDtoList);
		dataGrid.setTotal(total);
		return dataGrid;
 	}
	/**
	 * 功能 ：所有未选课题的学生
	 * @author LiChunming
	 * @create 2014-01-02 
	 */
	@ResponseBody
	@RequestMapping("/findUnTopicStuInfs")
	public DataGrid findUnTopicStuInfs(PageUtil pageUtil){
		DataGrid dataGrid = new DataGrid();
		List<StudentInfo> stuInfoList = stuInfServiceI.findUnTopicStuInfs(pageUtil);
		BigInteger total = stuInfServiceI.stuUnTopCount();
		List<StudentInfoDto> stuInfsList = new ArrayList<>();
		for(StudentInfo studentInfo : stuInfoList){
			StudentInfoDto studentInfoDto = new StudentInfoDto();
			BeanUtils.copyProperties(studentInfo, studentInfoDto,new String[]{"stuAcademy","stuMajor"});
			if(!StringUtils.isEmpty(studentInfo.getStuAcademy())){
				studentInfoDto.setStuAcademy(studentInfo.getStuAcademy().getDicName());
			}
			if(!StringUtils.isEmpty(studentInfo.getStuMajor())){
				studentInfoDto.setStuMajor(studentInfo.getStuMajor().getMajorName());
			}
			stuInfsList.add(studentInfoDto);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(stuInfsList);
		return dataGrid;
	}
	/**
	 * 功能 ：导出所有未选课题的学生
	 * @author LiChunming
	 * @create 2014-01-03 
	 */
	@ResponseBody
	@RequestMapping("/exportStudents")
	public JsonData exportStudents(HttpServletRequest request) {	
		JsonData jsonData = new JsonData();
		try {
			String[] headers = { "学号", "学生姓名","性别","院系","专业","本专科","入学年级","班级","手机号"};
			ExportExcelUtils eeu = new ExportExcelUtils();
			HSSFWorkbook workbook = new HSSFWorkbook();
			String path = request.getSession().getServletContext().getRealPath("upload")+"\\2014UnStudents.xls";  
			OutputStream out = new FileOutputStream(path);
			//从数据库中取数据
			List<Major> majorList = majorServiceI.findAllMajors();
			List<StudentInfo> studList = new ArrayList<StudentInfo>();
			for(int i = 0;i < majorList.size();i++){
				List<StudentInfoDto> studDtoList = new ArrayList<StudentInfoDto>();
				//根据不同的专业Id查找不同数据
				studList = stuInfServiceI.findUnTopicStuInfs(majorList.get(i).getMajorId());
				for(StudentInfo stud : studList){
					StudentInfoDto studDto = new StudentInfoDto();
					studDto.setUserName(stud.getUserName());
					studDto.setStuName(stud.getStuName());
					studDto.setStuSex(stud.getStuSex());
					studDto.setStuAcademy(stud.getStuAcademy().getDicName());
					studDto.setStuMajor(majorList.get(i).getMajorName());
					studDto.setStuSubject(stud.getStuSubject());
					studDto.setStuGrade(stud.getStuGrade());
					studDto.setStuClass(stud.getStuClass());
					studDto.setStuPhone(stud.getStuPhone());
					studDtoList.add(studDto);
					
				}
				eeu.exportExcel1(workbook, i, majorList.get(i).getMajorName(), headers, studDtoList, out);
			}
			workbook.write(out);
			out.close();
			jsonData.setMessage(request.getContextPath()+"/upload/2014UnStudents.xls");
			jsonData.setStatus("true");
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setStatus("false");
		}
		return jsonData;
	}
}

