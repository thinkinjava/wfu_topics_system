package com.topic.controller;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tepusoft.entity.DataDictionary;
import com.tepusoft.entity.Major;
import com.tepusoft.service.DataDictionaryServiceI;
import com.tepusoft.service.MajorServiceI;
import com.tepusoft.utils.AcademyUtil;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.Constants;
import com.tepusoft.utils.DataGrid;
import com.tepusoft.utils.DataGridOther;
import com.tepusoft.utils.ExportExcelUtils;
import com.tepusoft.utils.JsonData;
import com.tepusoft.utils.PageUtil;
import com.topic.dto.ChooseTopicDto;
import com.topic.dto.TopicDto;
import com.topic.entity.ChooseTopic;
import com.topic.entity.Topic;
import com.topic.service.AdminTopicServiceI;
import com.topic.service.TeaTopicServiceI;
import com.topic.service.ChooseTopicServiceI;

@Controller
@RequestMapping("/adminTopicController")
public class AdminTopicController extends BaseController {
	@Autowired
	private AdminTopicServiceI adminTopicServiceI;
	@Autowired
	private TeaTopicServiceI teaTopicServiceI;
	@Autowired
	private ChooseTopicServiceI chooseTopicServiceI;
	@Autowired
	private MajorServiceI majorServiceI;
	@Autowired
	private DataDictionaryServiceI dataDictionaryServiceI;

	/**
	 * 功能：根据登陆的管理员院系查找教师发布的题目
	 * 
	 * @author zhaoyekai
	 * @time 2013-12-27
	 * @param pageUtil
	 * @return
	 */

	@ResponseBody
	@RequestMapping("/findAllTopByAdminAdademy")
	public DataGrid findAllTopByAdminAdademy(PageUtil pageUtil,String searchValue,String searchName) {
		DataGrid dataGrid = new DataGrid();
		
		Map<String, Object> map=new HashMap<String, Object>();
		//判断是否是按条件查询
		
		if (null!=searchValue&&!"".equals(searchValue))
		{
			map.put(searchName, Constants.GET_SQL_LIKE+searchValue+Constants.GET_SQL_LIKE);
		   
		}
		// 首先判断管理员的院系
		AcademyUtil academyUtil = this.getUserAcademy();
		String academyId = academyUtil.getAcademyId();
		List<Topic> topicList = new ArrayList<Topic>();
		BigInteger total = null;
		if (!StringUtils.isEmpty(academyId)) {
			// 根据当前登陆管理员的院系去查找所有属于该学院的所有的教师题目
			topicList = adminTopicServiceI.findAllTopicByAcademyId(pageUtil,
					academyId,map);
			total = adminTopicServiceI.findAllTopicByAcademyCount(academyId,map);
		   
		}
		List<TopicDto> topicDtoList = new ArrayList<>();
		for (Topic topic : topicList) {
			TopicDto topicDto = new TopicDto();
			BeanUtils.copyProperties(topic, topicDto,
					new String[] { "topType" });
			topicDto.setTopicPersonName(topic.getTeacherInfo().getTeaName());
			topicDto.setTopicType(topic.getTopType().getDicName());
			//2014-2-23 lxm添加 
			//得到在数据字典中教师对应的职称的dicId
			String dicId = topic.getTeacherInfo().getTeaTitle().getDicId();
			//查
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary = dataDictionaryServiceI.findDataDicById(dicId);
			//往dto里面放
			topicDto.setTeacherTitle(dataDictionary.getDicName());
			//结束
			topicDto.setTopicMajorIds(null);
			
			topicDtoList.add(topicDto);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(topicDtoList);
		return dataGrid;
	}

	/**
	 * 功能：根据登陆的管理员院系查找教师发布的未选题目
	 * 
	 * @author Li Chunming
	 * @time 2013-12-31
	 * @param pageUtil
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findUnTopByAdminAdademy")
	public DataGrid findUnTopByAdminAdademy(PageUtil pageUtil) {
		DataGrid dataGrid = new DataGrid();
		// 首先判断管理员的院系
		AcademyUtil academyUtil = this.getUserAcademy();
		String academyId = academyUtil.getAcademyId();
		List<Topic> topicList = new ArrayList<Topic>();
		BigInteger total = null;
		if (!StringUtils.isEmpty(academyId)) {
			// 根据当前登陆管理员的院系去查找所有属于该学院的所有的学生未选题目
			topicList = adminTopicServiceI.findUnTopicByAcademyId(pageUtil,
					academyId);
			total = adminTopicServiceI.findUnTopicByAcademyCount(academyId);
		}
		List<TopicDto> topicDtoList = new ArrayList<>();
		for (Topic topic : topicList) {
			TopicDto topicDto = new TopicDto();
			BeanUtils.copyProperties(topic, topicDto,
					new String[] { "topType" });
			topicDto.setTopicPersonName(topic.getTeacherInfo().getTeaName());
			topicDto.setTopicType(topic.getTopType().getDicName());
			topicDto.setTopicMajorIds(null);
			topicDtoList.add(topicDto);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(topicDtoList);
		return dataGrid;
	}

	/**
	 * 功能：根据选择的条件查找教师发布的题目
	 * 
	 * @author zhaoyekai
	 * @time 2013-12-27
	 * @param topicDto
	 * @return
	 */

	// 搜索课题
	@ResponseBody
	@RequestMapping("/searchTopic")
	public DataGridOther searchTopic(TopicDto topicDto) {
		DataGridOther dataGrid = new DataGridOther();
		// 全局变量
		List<TopicDto> afterSearchTopicSDtoList = new ArrayList<TopicDto>();
		AcademyUtil academyUtil = this.getUserAcademy();
		List<Topic> afterSearchTopicList = adminTopicServiceI
				.findAllTopicByTopicNameOrTopicTypeOrTopicPersonName(topicDto.getTeacherTitle(),
						topicDto.getTopicName(), topicDto.getTopicType(),
						topicDto.getTopicPersonName(),
						academyUtil.getAcademyId());
		for (Topic topic : afterSearchTopicList) {
			TopicDto topicDto2 = new TopicDto();
			BeanUtils.copyProperties(topic, topicDto2,
					new String[] { "topType"});
			topicDto2.setTopicPersonName(topic.getTeacherInfo().getTeaName());
			topicDto2.setTopicType(topic.getTopType().getDicName());
			topicDto2.setTeacherTitle(topic.getTeacherInfo().getTeaTitle().getDicName());
			topicDto2.setTopicMajorIds(null);
			afterSearchTopicSDtoList.add(topicDto2);
		}
		dataGrid.setRows(afterSearchTopicSDtoList);
		dataGrid.setTotal(afterSearchTopicSDtoList.size());
		return dataGrid;
	}

	/**
	 * 功能：根据选择的条件查找教师发布的未选题目
	 * 
	 * @author LiChunming
	 * @time 2013-12-31
	 * @param topicDto
	 * @return
	 */

	// 搜索未选课题
	@ResponseBody
	@RequestMapping("/searchUnTopic")
	public DataGridOther searchUnTopic(TopicDto topicDto) {
		DataGridOther dataGrid = new DataGridOther();
		// 全局变量
		List<TopicDto> afterSearchTopicSDtoList = new ArrayList<TopicDto>();
		AcademyUtil academyUtil = this.getUserAcademy();
		List<Topic> afterSearchTopicList = adminTopicServiceI
				.findUnTopicByTopicNameOrTopicTypeOrTopicPersonName(
						topicDto.getTopicName(), topicDto.getTopicType(),
						topicDto.getTopicPersonName(),
						academyUtil.getAcademyId());
		for (Topic topic : afterSearchTopicList) {
			TopicDto topicDto2 = new TopicDto();
			BeanUtils.copyProperties(topic, topicDto2,
					new String[] { "topType" });
			topicDto2.setTopicPersonName(topic.getTeacherInfo().getTeaName());
			topicDto2.setTopicType(topic.getTopType().getDicName());
			topicDto2.setTopicMajorIds(null);
			afterSearchTopicSDtoList.add(topicDto2);
		}
		dataGrid.setRows(afterSearchTopicSDtoList);
		dataGrid.setTotal(afterSearchTopicSDtoList.size());
		return dataGrid;
	}

	// 编辑课题
	/**
	 * 功能：管理员修改教师发布的题目
	 * 
	 * @author zhaoyekai
	 * @time 2013-12-27
	 * @param topicDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateTopic")
	public JsonData updateTopic(TopicDto topicDto) {
		JsonData jsonData = new JsonData();
		// 因为只更新课题的几个字段，可以从课题表中找出数据然后修改
		Topic topic = adminTopicServiceI.findByTopicId(topicDto.getTopicId());
		topic.setTopicName(replaceBlank(topicDto.getTopicName()));
		topic.setTopicMajorIds(topicDto.getTopicMajorIds());
		topic.setTopicMajorNames(topicDto.getTopicMajorNames());
		if (!StringUtils.isEmpty(topicDto.getTopicType())) {// 如果课题类型不为空
			DataDictionary topicType = new DataDictionary();
			topicType.setDicId(topicDto.getTopicType());
			topic.setTopType(topicType);
		}
		try {
			adminTopicServiceI.updateTopic(topic);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
		}
		return jsonData;
	}

	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 功能：导出excel
	 * 
	 * @author Lixiangmao
	 * @throws Exception
	 * @create 2014/1/2
	 */
	@ResponseBody
	@RequestMapping("/exportTopic")
	public JsonData exportTopic(HttpServletRequest request) {
		JsonData jsonData = new JsonData();
		try {
			String[] headers = { "毕业设计题目", "课题类别","适应专业","指导教师","指导教师职称/学位","联系电话","邮箱","课题发布时间"};
			ExportExcelUtils eeu = new ExportExcelUtils();
			HSSFWorkbook workbook = new HSSFWorkbook();
			String path = request.getSession().getServletContext().getRealPath("upload")+"\\2014届毕业设计题目.xls";  
			OutputStream out = new FileOutputStream(path);
			//从数据库中取数据
			List<Major> majorList = majorServiceI.findAllMajors();
			List<Topic> topicList = new ArrayList<Topic>();
			List<TopicDto> topicDtoList = new ArrayList<TopicDto>();
			for(int i = 0;i < majorList.size();i++){
				//根据不同的专业Id查找不同数据
				topicList = teaTopicServiceI.findTopicByMajorId(majorList.get(i).getMajorId());
				for(Topic topic : topicList){
					TopicDto topicDto = new TopicDto();
					topicDto.setTopicName(topic.getTopicName());
					if(!StringUtils.isEmpty(topic.getTopType())){
						topicDto.setTopicType(topic.getTopType().getDicName());
					}
					topicDto.setTopicMajorNames(majorList.get(i).getMajorName());
					topicDto.setTopicPersonName(topic.getTeacherName());
					if(!StringUtils.isEmpty(topic.getTeacherInfo())){
						if(!StringUtils.isEmpty(topic.getTeacherInfo().getTeaTitle())){
							topicDto.setTopicPersonTitle(topic.getTeacherInfo().getTeaTitle().getDicName());
						}
						topicDto.setTeacherPhone(topic.getTeacherInfo().getTeaPhone());
						topicDto.setTeacherEmail(topic.getTeacherInfo().getTeaEmail());
						topicDto.setTopicCreateTime(topic.getTopicCreateTime());
					}
					topicDtoList.add(topicDto);
				}
			}
			eeu.exportExcel(workbook, 0, "2014届毕业设计题目", headers, topicDtoList, out);
			workbook.write(out);
			out.close();
			jsonData.setMessage(request.getContextPath()+"/upload/2014届毕业设计题目.xls");
			jsonData.setStatus("true");
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setStatus("false");
		}
		return jsonData;
	}

	// 查看已选课题
	@ResponseBody
	@RequestMapping("/findSelectedByAdminAdademy")
	public DataGrid findSelectedByAdminAdademy(PageUtil pageUtil) {
		DataGrid dataGrid = new DataGrid();
		// 判断管理员的院系，得到院系Id
		AcademyUtil academyUtil = this.getUserAcademy();
		String academyId = academyUtil.getAcademyId();
		List<Topic> topicList = new ArrayList<Topic>();
		BigInteger total = null;
		// 得到所有已选课题的课题Id.
		if (!StringUtils.isEmpty(academyId)) {
			topicList = adminTopicServiceI.findSelectedTopicByAcademyId(
					pageUtil, academyId);
			total = adminTopicServiceI
					.findSelectedTopicByAcademyCount(academyId);
		}
		List<ChooseTopicDto> choosedTopicDtoList = new ArrayList<ChooseTopicDto>();
		for (Topic t : topicList) {
			String topicId = t.getTopicId();
			ChooseTopic chooseTopic = null;
			if (!StringUtils.isEmpty(topicId)) {
				chooseTopic = chooseTopicServiceI.findAllChoosedTopicBytopicId(
						topicId).get(0);
			}
			ChooseTopicDto chooseTopicDto = new ChooseTopicDto();
			chooseTopicDto.setChoosedId(chooseTopic.getChoosedId());
			chooseTopicDto
					.setTopicName(chooseTopic.getTopicId().getTopicName());
			chooseTopicDto.setTopicType(chooseTopic.getTopicId().getTopType()
					.getDicName());
			chooseTopicDto.setTopicMajorNames(chooseTopic.getTopicId()
					.getTopicMajorNames());
			chooseTopicDto.setTopicPersonName(chooseTopic.getTeacherId()
					.getTeaName());
			chooseTopicDto.setChoosedStudentName(chooseTopic.getStudentId()
					.getStuName());
			chooseTopicDto.setStudentPhone(chooseTopic.getStudentId()
					.getStuPhone());
			chooseTopicDto.setStudentEmail(chooseTopic.getStudentId()
					.getStuEmail());
			choosedTopicDtoList.add(chooseTopicDto);

		}
		dataGrid.setRows(choosedTopicDtoList);
		dataGrid.setTotal(total);
		return dataGrid;
	}
}