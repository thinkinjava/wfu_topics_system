package com.tepusoft.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tepusoft.dto.MenuModel;
import com.tepusoft.dto.StudentInfoDto;
import com.tepusoft.dto.TeacherInfoDto;
import com.tepusoft.dto.UsersDto;
import com.tepusoft.entity.DataDictionary;
import com.tepusoft.entity.Dept;
import com.tepusoft.entity.Major;
import com.tepusoft.entity.Role;
import com.tepusoft.entity.StudentInfo;
import com.tepusoft.entity.TeacherInfo;
import com.tepusoft.entity.Troom;
import com.tepusoft.entity.User;
import com.tepusoft.entity.UsersRoles;
import com.tepusoft.service.DataDictionaryServiceI;
import com.tepusoft.service.DeptServiceI;
import com.tepusoft.service.MajorServiceI;
import com.tepusoft.service.RoleServiceI;
import com.tepusoft.service.StuInfServiceI;
import com.tepusoft.service.TeaServiceI;
import com.tepusoft.service.TroomServiceI;
import com.tepusoft.service.UserServiceI;
import com.tepusoft.service.UsersRolesServiceI;
import com.tepusoft.shiro.ShiroUser;
import com.tepusoft.utils.AcademyUtil;
import com.tepusoft.utils.BaseController;
import com.tepusoft.utils.Constants;
import com.tepusoft.utils.DataGrid;
import com.tepusoft.utils.JsonData;
import com.tepusoft.utils.PageUtil;


@Controller
@RequestMapping("/userController")
public class UserController extends BaseController {
	
	@Autowired
	private UserServiceI userServiceI;
	@Autowired
	private DeptServiceI deptServiceI;
	@Autowired
	private StuInfServiceI stuInfServiceI;
	@Autowired
	private TeaServiceI teaServiceI;
	@Autowired
	private DataDictionaryServiceI dataDictionaryServiceI;
	@Autowired
	private RoleServiceI roleServiceI;
	@Autowired
	private MajorServiceI majorServiceI;
	@Autowired
	private UsersRolesServiceI usersRolesServiceI;
	@Autowired
	private TroomServiceI troomServiceI;
	/**
	 * 功能:根据用户权限得到所有的菜单
	 * @author Lijinzhao
	 * @param null
	 * @return List<MenuModel> json格式
	 */
	@ResponseBody
	@RequestMapping("/findAllMenu")
	public List<MenuModel> findAllMenuByUser(HttpServletRequest request){
		
		return userServiceI.findMenuListByUser();
	}
	
	/**
	 * 功能得到所有的用户信息
	 * @author Lijinzhao 
	 * (光棍节我还在苦逼的写代码)
	 * @time 2013-11-11 00:06
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllUsers")	
	public DataGrid findAllUsers(PageUtil pageUtil){
		DataGrid dataGrid = new DataGrid();
		BigInteger total=userServiceI.usersCount();
		List<User> userList=userServiceI.findUsersByPageUtil(pageUtil);
		List<UsersDto> userDtoList=new ArrayList<>();
		for(User u:userList){
			UsersDto usersDto=new UsersDto();
			//将用户的角色放入列表（只有管理员使用，这里可以不考虑并发，以后注意啊，会产生十九条查询语句%>_<%）
			/*String roleIds="";
			String roleNames="";
			for(UsersRoles usersRoles:u.getUserRoles()){
				roleIds+=usersRoles.getRoles().getRoleId()+",";
				roleNames+=usersRoles.getRoles().getRoleName()+",";
				usersDto.setRoleIds(roleIds.substring(0,roleIds.length()-1));
				usersDto.setRoleNames(roleNames.substring(0,roleNames.length()-1));
			}*/
			BeanUtils.copyProperties(u, usersDto,new String[]{"dept","major","academy","troom"});
		/*	if(u.getDept()!=null&&!u.getDept().equals("")){
				usersDto.setDeptId(u.getDept().getDeptId());
				usersDto.setDeptName(u.getDept().getDeptName());
			}
			if(!StringUtils.isEmpty(u.getMajor())){
				usersDto.setMajor(u.getMajor().getMajorName());
			}
			if(!StringUtils.isEmpty(u.getAcademy())){
				usersDto.setAcademy(u.getAcademy().getDicName());
			}
			if(!StringUtils.isEmpty(u.getTroom())){
				usersDto.setTroom(u.getTroom().getTroomName());
			}*/
			userDtoList.add(usersDto);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(userDtoList);
		
		return dataGrid;
	}
	
	/**
	 * 功能删除用户
	 * @author "Lijinzhao"
	 */
	@ResponseBody
	@RequestMapping("/deleteUsers")
	public void deleteUser(String ids){
		
		userServiceI.deleteUserBySql(ids);
	}
	/**
	 * 得到所有部门（人员添加时使用）
	 * @author "Lijinzhao"
	 */
	@ResponseBody
	@RequestMapping("/getAllDepts")
	public Object getAllDepts(){
		return deptServiceI.findDeptsOutJson();   //以json形式返回
		}
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateUser",method=RequestMethod.POST)
	public JsonData saveOrUpdateUser(UsersDto usersDto){
		JsonData jsonData = new JsonData();
		/*if(usersDto.getDeptId()!=null&&usersDto.getDeptId()!=""){
			dept = deptServiceI.findDeptByDeptId(usersDto.getDeptId());	
			usersDto.setDept(dept);
		}*/
		//保存用户
		userServiceI.saveOrUpdateUser(usersDto);
		jsonData.setMessage("保存成功");
		jsonData.setStatus("true");
		return jsonData;
	}
	/**
	 * 根据用户名查找用户
	 * @author "Lijinzhao"
	 * @param usersDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/searchUser")
	public DataGrid searchUser(UsersDto usersDto){
		DataGrid dataGrid = new DataGrid();
		BigInteger total=userServiceI.usersCountBySearch(usersDto.getUserName());
		List<User> userList=userServiceI.findUsersByUserName(usersDto.getUserName());
		List<UsersDto> userDtoList=new ArrayList<>();
		for(User u:userList){
			UsersDto users=new UsersDto();
			u.setUserRoles(null);		
			BeanUtils.copyProperties(u, users,new String[]{"dept"});
			/*if(u.getDept()!=null&&!u.getDept().equals("")){
				users.setDeptId(u.getDept().getDeptId());
				users.setDeptName(u.getDept().getDeptName());
			}*/
			userDtoList.add(users);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(userDtoList);
		return dataGrid;
	}
	/**
	 * 功能：用于验证系统中是否存在重复用户名
	 * @author Lijinzhao
	 * @time 2013-12-06
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/isHasRepeatUserName")
	public JsonData isHasRepeatUserName(String userName){
		JsonData jsonData = new JsonData();
		User user = userServiceI.findUserByUserName(userName);
		if(user==null){
			jsonData.setTheBoolean(false);  //不存在重复用户
		}else{
			jsonData.setTheBoolean(true);  //存在重复用户
		}
		return jsonData;
	}
	/**
	 * 功能：修改用户密码
	 * @author Lijinzhao
	 * @time 2013-12-16
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editPassWord")
	public JsonData editPassWord(String userId,String passWord){
		JsonData jsonData = new JsonData();
		//根据id得到用户
		User user=userServiceI.findUserByUserId(userId);
		//修改用户密码
		user.setPassWord(passWord);
		try {
			//保存用户
			userServiceI.updateUser(user);
			jsonData.setStatus("true");
			jsonData.setMessage("密码修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
			jsonData.setMessage("密码修改失败");
		}

		return jsonData;
	}
	/**
	 * 功能：修改教师密码
	 * @author Lixiangmao
	 * @time 2014-01-03
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editTeacherPassWord")
	public JsonData editTeacherPassWord(String userName,String passWord){
		JsonData jsonData = new JsonData();
		//根据id得到教师
		User user = userServiceI.findUserByUserName(userName);
		//修改用户密码
		user.setPassWord(passWord);
		try {
			//保存用户
			userServiceI.updateUser(user);
			jsonData.setStatus("true");
			jsonData.setMessage("密码修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
			jsonData.setMessage("密码修改失败");
		}

		return jsonData;
	}
	/**
	 * 功能：修改学生密码
	 * @author Lixiangmao
	 * @time 2014-01-03
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editStudentPassWord")
	public JsonData editStudentPassWord(String userName,String passWord){
		JsonData jsonData = new JsonData();
		//根据id得到教师
		User user = userServiceI.findUserByUserName(userName);
		//修改用户密码
		user.setPassWord(passWord);
		try {
			//保存用户
			userServiceI.updateUser(user);
			jsonData.setStatus("true");
			jsonData.setMessage("密码修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setStatus("false");
			jsonData.setMessage("密码修改失败");
		}

		return jsonData;
	}
	
	/**
	 * 功能：展示当前登陆人的个人信息
	 * @author Lixiangmao
	 * @create 2013/12/17
	 */
	@ResponseBody
	@RequestMapping("/userInfo")
	public UsersDto userInfo(){
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		UsersDto usersDto = new UsersDto();
		BeanUtils.copyProperties(shiroUser, usersDto);
		return usersDto;
	}
	/**
	 * 功能：当前登陆人个人信息编辑初始化
	 * @author Lixiangmao
	 * @create 2013/12/17
	 */
	@ResponseBody
	@RequestMapping("/userInfoEditInit")
	public UsersDto userInfoEditInit(){
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		String userId = shiroUser.getUserId();
		User user = userServiceI.findUserByUserId(userId);
		UsersDto usersDto = new UsersDto();
		BeanUtils.copyProperties(user, usersDto);
		return usersDto;
	}

	/**
	 * 功能:修改个人密码
	 * @author Lixiangmao
	 * @create 2013/12/17
	 */
	@ResponseBody
	@RequestMapping("/editUserPasswordInit")
	public UsersDto editUserPasswordInit(){
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		String userId = shiroUser.getUserId();
		User user = userServiceI.findUserByUserId(userId);
		UsersDto usersDto = new UsersDto();
		BeanUtils.copyProperties(user, usersDto);
		return usersDto;
	}
	/**
	 * 功能：验证是否是正确的老密码
	 * @author Lixiangmao
	 * @create 2013/12/17
	 */
	@ResponseBody
	@RequestMapping("/isTheTrueOldPassword")
	public JsonData isTheTrueOldPassword(String oldPassWord){
		JsonData jsonData = new JsonData();
		//得到当前用户的密码
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		String userId = shiroUser.getUserId();
		User user = userServiceI.findUserByUserId(userId);
		String passWord = user.getPassWord();
		if(passWord.equals(oldPassWord)){
			jsonData.setTheBoolean(false);
		}else {
			jsonData.setTheBoolean(true);
		}
		return jsonData;
	}
	/**
	 * 功能：更新密码
	 * @author Lixiangmao
	 * @create 2013/12/18
	 */
	@ResponseBody
	@RequestMapping("/editUserPassword")
	public JsonData editUserPassword(String passWord){
		JsonData jsonData = new JsonData();
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		String userId = shiroUser.getUserId();
		User user = userServiceI.findUserByUserId(userId);
		user.setPassWord(passWord);
		try {
			userServiceI.updateUser(user);
			jsonData.setMessage("密码修改成功");
			jsonData.setStatus("true");
		} catch (Exception e) {
			jsonData.setMessage("密码修改失败");
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：查找所有的学生信息
	 * @author Lixiangmao
	 * @time 2013/12/19
	 */
	@ResponseBody
	@RequestMapping("/findAllStuInfs")
	public DataGrid findAllStuInfs(PageUtil pageUtil){
		DataGrid dataGrid = new DataGrid();
		List<StudentInfo> stuInfoList = stuInfServiceI.findAllStuInfs(pageUtil);
		BigInteger total = stuInfServiceI.stuInfsCount();
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
	 * 功能：添加学生信息
	 * @author Lixiangmao
	 * @time 2013/12/19
	 */
	@ResponseBody
	@RequestMapping("/saveStuInfo")
	public JsonData saveStuInfo(StudentInfoDto studentInfoDto){
		String academyId = studentInfoDto.getStuAcademyId();
		String roleIds = studentInfoDto.getRoleIds();
		DataDictionary dataDictionary = null;
		Role role = null;
		//根据academy,roleIds查找院系表，角色表
		if(!StringUtils.isEmpty(academyId)&&!StringUtils.isEmpty(roleIds)){
			dataDictionary = new DataDictionary();
			dataDictionary = dataDictionaryServiceI.findDataDicById(academyId);
			role = new Role();
			role = roleServiceI.findRoleById(roleIds);
		}
		//查找结束
		JsonData jsonData = new JsonData();
		//开始保存学生表
		StudentInfo studentInfo = new StudentInfo();
		BeanUtils.copyProperties(studentInfoDto, studentInfo, new String[]{"stutId","stuAcademy","roleNames","createTime","stuMajor"});
		studentInfo.setStutId(UUID.randomUUID().toString());
		studentInfo.setStuAcademy(dataDictionary);
		studentInfo.setRoleNames(role.getRoleName());
		studentInfo.setStuMajor(majorServiceI.findMajorByMajorId(studentInfoDto.getStuMajor()));
		studentInfo.setCreateTime(new Date());
		//保存到学生表结束
		try {
			if(!StringUtils.isEmpty(studentInfo)){
				userServiceI.saveUserAndUserRoles(studentInfoDto);
				stuInfServiceI.saveStuInfo(studentInfo);
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
	 * 功能：获得所有教师信息
	 * @author Lijinzhao
	 * @time 2013-12-19
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAllTeaInfos")
	public DataGrid findAllTeaInfos(PageUtil pageUtil){
		DataGrid dataGrid = new DataGrid();
		BigInteger total=teaServiceI.teasCount();
		AcademyUtil academyUtil = this.getUserAcademy();
		List<TeacherInfo> teaList = new ArrayList<>();
		if(!StringUtils.isEmpty(academyUtil)){
			teaList=teaServiceI.findAllTeas(pageUtil,academyUtil.getAcademyId());
		}else{
			teaList=teaServiceI.findAllTeas(pageUtil,"");
		}
			
		List<TeacherInfoDto> teaDtoList=new ArrayList<>();
		for(TeacherInfo t : teaList){
			TeacherInfoDto teacherInfoDto = new TeacherInfoDto();
			BeanUtils.copyProperties(t, teacherInfoDto, new String[]{"teaTitle","teaTroom","teaAcademy","teaDept"});
			if(!StringUtils.isEmpty(t.getTeaTitle())){
				teacherInfoDto.setTeaTitle(t.getTeaTitle().getDicName());
			}
			if(!StringUtils.isEmpty(t.getTeaTroom())){
				teacherInfoDto.setTeaTroom(t.getTeaTroom().getTroomName());
			}
			if(!StringUtils.isEmpty(t.getTeaAcademy())){
				teacherInfoDto.setTeaAcademy(t.getTeaAcademy().getDicName());
			}
			if(!StringUtils.isEmpty(t.getTeaDept())){
				teacherInfoDto.setTeaDept(t.getTeaDept().getDeptId());
				teacherInfoDto.setDeptName(t.getTeaDept().getDeptName());
			}
			teaDtoList.add(teacherInfoDto);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(teaDtoList);
		return dataGrid;
	}
	/**
	 * 功能：在添加学生信息的时候，得到当前的教师信息，放到添加Dlg中
	 * @author Lixiangmao
	 * @create 2013/12/20
	 */
	@ResponseBody
	@RequestMapping("/findAcademy")
	public AcademyUtil findAcademy(){
		//得到当前登陆的教师信息
		AcademyUtil academyUtil = this.getUserAcademy();
		return academyUtil;
	}
	
	/**
	 * 功能：得到当前登录的教师信息
	 * @author Lijinzhao
	 * @time 2013-12-20
	 * @return teacherInfoDto
	 */
	@ResponseBody
	@RequestMapping("/getAcademy")
	public AcademyUtil getAcademy(){
		AcademyUtil academyUtil = this.getUserAcademy();
		return academyUtil;
	}
	/**
	 * 功能：保存教师信息
	 * @author Lijinzhao
	 * @time 2013-12-20
	 * @param teacherInfoDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveTeacher")
	public JsonData saveTeacher(TeacherInfoDto teacherInfoDto){
		JsonData jsonData = new JsonData();
		try {
			teaServiceI.saveTeacher(teacherInfoDto);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonData.setStatus("false");
		}
		return jsonData;
	}

	/**
	 * 功能：更新教师表信息
	 * @author Lijinzhao
	 * @time 2013-12-21
	 * @param teacherInfoDto
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateTeacher")
	public JsonData updateTeacher(TeacherInfoDto teacherInfoDto){
		JsonData jsonData = new JsonData();
		try {
			teaServiceI.updateTeacher(teacherInfoDto);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	
	/**
	 * 功能：删除教师
	 * @author Lijinzhao
	 * @time 2013-12-21
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteTeacher")
	public JsonData deleteTeacher(String teaId,String userName){
		JsonData jsonData = new JsonData();
		try {
			teaServiceI.deleteTeacher(teaId,userName);
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	

	/**
	 * 功能：删除学生信息
	 * @author Lixiangmao
	 * @create 2013/12/21
	 */
	@ResponseBody
	@RequestMapping("/deleteStutInfo")
	public JsonData deleteStutInfo(String stutId){
		JsonData jsonData = new JsonData();
		//删除流程：通过stutId找到学生表，找到学号，通过角色表的userId找到中间表，先删除中间表，然后删除用户表，最后删除学生信息表。
		StudentInfo studentInfo = new StudentInfo();
		studentInfo = stuInfServiceI.findStuInfoById(stutId);
		//得到学号
		String userName = studentInfo.getUserName();
		User user = new User();
		user = userServiceI.findUserByUserName(userName);//亲耐的朝哥给我写好了接口 感动 ~~~~(>_<)~~~~ 
		//得到userId
		String userId = user.getUserId();
		UsersRoles usersRoles = new UsersRoles();
		usersRoles = usersRolesServiceI.findUserRolesByUserId(userId);
		try {
			//删除正式开始
			//1.删除中间表
			usersRolesServiceI.deleteUsersRoles(usersRoles);
			//2.删除用户表
			userServiceI.deleteUser(user);
			//3.删除学生信息表
			stuInfServiceI.deleteStuInfo(studentInfo);
			jsonData.setMessage("删除成功");
			jsonData.setStatus("true");
		} catch (Exception e) {
			// TODO: handle exception
			jsonData.setMessage("删除失败");
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：搜索学生信息
	 * @author Lixiangmao
	 * @create 2013/12/21
	 */
	@ResponseBody
	@RequestMapping("/searchStuInfo")
	public DataGrid searchStuInfo(StudentInfoDto studentInfoDto){
		DataGrid dataGrid = new DataGrid();
		BigInteger total = null;
		List<StudentInfo> stuInfoList = null;
		//下面开始判断 
		if(!StringUtils.isEmpty(studentInfoDto.getUserName())&&StringUtils.isEmpty(studentInfoDto.getStuName())){
			//1 第一种情况 学号不为空 姓名为空
			total = stuInfServiceI.stuInfosCountByUserName(studentInfoDto.getUserName());
			stuInfoList = stuInfServiceI.findStuInfosByUserName(studentInfoDto.getUserName());
		}else if(StringUtils.isEmpty(studentInfoDto.getUserName())&&!StringUtils.isEmpty(studentInfoDto.getStuName())){
			//2 第二种情况 学号为空 姓名不为空
			total = stuInfServiceI.stuInfosCountByStuName(studentInfoDto.getStuName());
			stuInfoList = stuInfServiceI.findStuInfosByStuName(studentInfoDto.getStuName());
		}else if(!StringUtils.isEmpty(studentInfoDto.getUserName())&&!StringUtils.isEmpty(studentInfoDto.getStuName())){
			//3 第三种情况 学号和姓名都不为空
			total = stuInfServiceI.stuInfosCountBySearch(studentInfoDto.getUserName(), studentInfoDto.getStuName());
			stuInfoList = stuInfServiceI.findStuInfosByUserNameAndStuName(studentInfoDto.getUserName(), studentInfoDto.getStuName());
		}
		List<StudentInfoDto> stuInfsList = new ArrayList<>();
		for(StudentInfo studentInfo : stuInfoList){
			StudentInfoDto studentInfoDto1 = new StudentInfoDto();
			BeanUtils.copyProperties(studentInfo, studentInfoDto1,new String[]{"stuAcademy","stuMajor"});
			if(!StringUtils.isEmpty(studentInfo.getStuAcademy())){
				studentInfoDto1.setStuAcademy(studentInfo.getStuAcademy().getDicName());
			}
			if(!StringUtils.isEmpty(studentInfo.getStuMajor())){
				studentInfoDto1.setStuMajor(studentInfo.getStuMajor().getMajorName());
			}
			stuInfsList.add(studentInfoDto1);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(stuInfsList);
		return dataGrid;
	}
	/**
	 * 功能:搜素教师信息
	 * @author Lixiangmao
	 * @create 2013/12/23
	 */
	@ResponseBody
	@RequestMapping("/searchTeaInfo")
	public DataGrid searchTeaInfo(TeacherInfoDto teacherInfoDto){
		DataGrid dataGrid = new DataGrid();
		BigInteger total = null;
		List<TeacherInfo> teaInfoList = null;
		//下面开始判断 
		if(!StringUtils.isEmpty(teacherInfoDto.getUserName())&&StringUtils.isEmpty(teacherInfoDto.getTeaName())){
			//1 第一种情况 工号不为空 教师姓名为空
			total = teaServiceI.teaInfosCountByUserName(teacherInfoDto.getUserName());
			teaInfoList = teaServiceI.findTeaInfosByUserName(teacherInfoDto.getUserName());
		}else if(StringUtils.isEmpty(teacherInfoDto.getUserName())&&!StringUtils.isEmpty(teacherInfoDto.getTeaName())){
			//2 第二种情况 工号为空 教师姓名不为空
			total = teaServiceI.teaInfosCountByTeaName(teacherInfoDto.getTeaName());
			teaInfoList = teaServiceI.findTeaInfosByTeaName(teacherInfoDto.getTeaName());
		}else if(!StringUtils.isEmpty(teacherInfoDto.getUserName())&&!StringUtils.isEmpty(teacherInfoDto.getTeaName())){
			//3 第三种情况 工号和教师姓名都不为空
			total = teaServiceI.teaInfosCountByUserNameAndTeaName(teacherInfoDto.getUserName(), teacherInfoDto.getTeaName());
			teaInfoList = teaServiceI.findTeaInfosByUserNameAndTeaName(teacherInfoDto.getUserName(), teacherInfoDto.getTeaName());
		}
		List<TeacherInfoDto> teaInfoDtosList = new ArrayList<>();
		for(TeacherInfo teacherInfo : teaInfoList){
			TeacherInfoDto teacherInfoDto2 = new TeacherInfoDto();
			BeanUtils.copyProperties(teacherInfo, teacherInfoDto2,new String[]{"teaTitle","teaTroom","teaAcademy","teaDept"});
			//处理外键
			if(!StringUtils.isEmpty(teacherInfo.getTeaTitle())){
				teacherInfoDto2.setTeaTitle(teacherInfo.getTeaTitle().getDicName());
			}
			if(!StringUtils.isEmpty(teacherInfo.getTeaTroom())){
				teacherInfoDto2.setTeaTroom(teacherInfo.getTeaTroom().getTroomName());
			}
			if(!StringUtils.isEmpty(teacherInfo.getTeaAcademy())){
				teacherInfoDto2.setTeaAcademy(teacherInfo.getTeaAcademy().getDicName());
			}
			if(!StringUtils.isEmpty(teacherInfo.getTeaDept())){
				teacherInfoDto2.setTeaDept(teacherInfo.getTeaDept().getDeptName());
			}
			teaInfoDtosList.add(teacherInfoDto2);
		}
		dataGrid.setTotal(total);
		dataGrid.setRows(teaInfoDtosList);
		return dataGrid;
	}
	/**
	 * 功能：更新学生表
	 * @author Lixiangmao
	 * @create 2013/12/22
	 */
	@ResponseBody
	@RequestMapping("/updateStuInfo")
	public JsonData updateStuInfo(StudentInfoDto studentInfoDto){
		JsonData jsonData = new JsonData();
		try {
			stuInfServiceI.updateStuInfo(studentInfoDto);
			jsonData.setStatus("true");
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：登陆界面查看个人信息 
	 * 查看个人信息之查看教师信息
	 * @author Lixiangmao
	 * @create 2013/12/22
	 */
	@ResponseBody
	@RequestMapping("/viewTeaInfo")
	public TeacherInfoDto viewTeaInfo(){
		String useId = this.getOwnId();
		User user = userServiceI.findUserByUserId(useId);
		TeacherInfo teacherInfo = new TeacherInfo();
		TeacherInfoDto teacherInfoDto = new TeacherInfoDto();
		teacherInfo = teaServiceI.findTeaByUserName(user.getUserName());
		BeanUtils.copyProperties(teacherInfo, teacherInfoDto,new String[]{"teaAcademy","teaTroom","teaDept","teaTitle"});
		//处理外键
		
		if(!StringUtils.isEmpty(teacherInfo.getTeaTroom())){ //教研室不为空
			String teaTroomId = teacherInfo.getTeaTroom().getTroomId();
			Troom troom = new Troom();
			troom = troomServiceI.findTroomById(teaTroomId);
			teacherInfoDto.setTeaTroom(troom.getTroomName());
		}

		
		if(!StringUtils.isEmpty(teacherInfo.getTeaDept())){   //部门不为空
			String teadeptId = teacherInfo.getTeaDept().getDeptId();
			Dept dept = new Dept();
			dept = deptServiceI.findDeptByDeptId(teadeptId);
			teacherInfoDto.setDeptName(dept.getDeptName());
		}

		if(!StringUtils.isEmpty(teacherInfo.getTeaTitle())){ //职称不为空
			String teaTitleId = teacherInfo.getTeaTitle().getDicId();
			DataDictionary dataDictionary2 = new DataDictionary();
			dataDictionary2 = dataDictionaryServiceI.findDataDicById(teaTitleId);
			teacherInfoDto.setTeaTitle(dataDictionary2.getDicName());
		}
		
		if(!StringUtils.isEmpty(teacherInfo.getTeaAcademy())){ //院系不为空
			String teaAcademyId = teacherInfo.getTeaAcademy().getDicId();
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary = dataDictionaryServiceI.findDataDicById(teaAcademyId);
			teacherInfoDto.setTeaAcademy(dataDictionary.getDicName());
		}
		
		return teacherInfoDto;
	}
	/**
	 * 功能：登陆界面查看个人信息
	 * 查看个人信息之查看学生信息
	 * @author Lixiangmao
	 * @create 2013/12/22
	 */
	@ResponseBody
	@RequestMapping("/viewStuInfo")
	public StudentInfoDto viewStuInfo(){
		String userId = this.getOwnId();
		User user = userServiceI.findUserByUserId(userId);
		StudentInfo studentInfo = new StudentInfo();
		StudentInfoDto studentInfoDto = new StudentInfoDto();
		studentInfo = stuInfServiceI.findStuByUserName(user.getUserName());
		BeanUtils.copyProperties(studentInfo,studentInfoDto,new String[]{"stuMajor","stuAcademy"});
		//处理外键
		if(!StringUtils.isEmpty(studentInfo.getStuMajor())){		
			studentInfoDto.setStuMajor(studentInfo.getStuMajor().getMajorName());
		}
		String stuAcademyId = studentInfo.getStuAcademy().getDicId();
		DataDictionary dataDictionary = new DataDictionary();
		dataDictionary = dataDictionaryServiceI.findDataDicById(stuAcademyId);
		studentInfoDto.setStuAcademy(dataDictionary.getDicName());
		
		return studentInfoDto;
	}
	/**
	 * 功能：当前登陆人个人信息编辑初始化
	 * @author Lixiangmao
	 * @create 2013/12/17
	 */
	@ResponseBody
	@RequestMapping("/editUserInfoInit")
	public UsersDto editUserInfoInit(){
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		String userId = shiroUser.getUserId();
		User user = userServiceI.findUserByUserId(userId);
		//思路：首先判断是教师还是学生，然后根据工号或者学号来查找教师或者学生
		TeacherInfo teacherInfo = null;
		StudentInfo studentInfo = null;
		UsersDto usersDto = null;
		if(user.getUserType().equals("1")){
			//是教师
			teacherInfo = teaServiceI.findTeaByUserName(user.getUserName());
			usersDto = new UsersDto();
			usersDto.setPhone(teacherInfo.getTeaPhone());
			usersDto.setEmail(teacherInfo.getTeaEmail());
			
			usersDto.setUserType("1");
		}else if(user.getUserType().equals("2")){
			//是学生
			studentInfo = stuInfServiceI.findStuByUserName(user.getUserName());
			usersDto = new UsersDto();
			usersDto.setPhone(studentInfo.getStuPhone());
			usersDto.setEmail(studentInfo.getStuEmail());
			usersDto.setUserType("2");
		}
		return usersDto;
	}
	/**
	 * 功能：当前登陆人个人信息编辑
	 * @author Lixiangmao
	 * @create 2013/12/17
	 */
	@ResponseBody
	@RequestMapping("/editUserInfo")
	public JsonData editUserInfo(TeacherInfoDto teacherInfoDto){
		JsonData jsonData = new JsonData();
		Subject subject=SecurityUtils.getSubject();
		ShiroUser shiroUser=(ShiroUser)subject.getSession().getAttribute("shiroUser");
		String userId = shiroUser.getUserId();
		User user = userServiceI.findUserByUserId(userId);
		//判断是修改教师信息还是修改学生信息
		if(shiroUser.getUserType().equals("1")){
			//修改教师信息
			TeacherInfo teacherInfo = new TeacherInfo();
			teacherInfo = teaServiceI.findTeaByUserName(user.getUserName());
			teacherInfo.setTeaPhone(teacherInfoDto.getTeaPhone());
			teacherInfo.setTeaEmail(teacherInfoDto.getTeaEmail());
			Troom teaTroom=new Troom();
			teaTroom.setTroomId(teacherInfoDto.getTeaTroom());
			DataDictionary dataDictionnary= new DataDictionary();
			dataDictionnary.setDicId(teacherInfoDto.getTeaTitle());
			teacherInfo.setTeaTroom(teaTroom);
			teacherInfo.setTeaTitle(dataDictionnary);
			teaServiceI.updateTeaInfo(teacherInfo);
		}else if(shiroUser.getUserType().equals("2")){
			StudentInfo studentInfo = new StudentInfo();
			studentInfo = stuInfServiceI.findStuByUserName(user.getUserName());
			studentInfo.setStuEmail(teacherInfoDto.getStuEmail());
			studentInfo.setStuPhone(teacherInfoDto.getStuPhone());
			Major major=new Major();
			major.setMajorId(teacherInfoDto.getStuMajor());
			studentInfo.setStuMajor(major);
			stuInfServiceI.updateStuInfo2(studentInfo);
		}
		try {
			userServiceI.updateUser(user);
			jsonData.setMessage("修改成功");
			jsonData.setStatus("true");
		} catch (Exception e) {
			e.printStackTrace();
			jsonData.setMessage("修改失败");
			jsonData.setStatus("false");
		}
		return jsonData;
	}
	/**
	 * 功能：批量导入教师信息
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/importTeacherInfos")  
    public JsonData importTeacherInfos(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model)  throws Exception{  
		JsonData jsonData = new JsonData();
		String newFileName = Constants.upload(file, request, model);  //现将文件上传保存
        // poi读取excel 
        //创建要读入的文件的输入流 
        InputStream inp = new FileInputStream(request.getSession().getServletContext().getRealPath("upload")+"/"+newFileName);     
        //根据上述创建的输入流 创建工作簿对象 
        Workbook wb = WorkbookFactory.create(inp); 
        //得到第一页 sheet 
        //页Sheet是从0开始索引的 
        Sheet sheet = wb.getSheetAt(0); 
        //利用foreach循环 遍历sheet中的所有行 
        //遍历row中的所有方格 
        for(int i=1;i<=sheet.getLastRowNum();i++){

        	String userName=toString(sheet.getRow(i).getCell(0));
        	String passWord=toString(sheet.getRow(i).getCell(1));
        	String personName=toString(sheet.getRow(i).getCell(2));
        	String idCard=toString(sheet.getRow(i).getCell(3));
        	String email=toString(sheet.getRow(i).getCell(4));
        	String phone=toString(sheet.getRow(i).getCell(5));
        	String title=toString(sheet.getRow(i).getCell(6));
        	String dept=toString(sheet.getRow(i).getCell(7));
        	String troom=toString(sheet.getRow(i).getCell(8));
        	String academy=toString(sheet.getRow(i).getCell(9));
        	String age=toString(sheet.getRow(i).getCell(10));
        	String sex=toString(sheet.getRow(i).getCell(11));
        	String roles=toString(sheet.getRow(i).getCell(12));
        	String address=toString(sheet.getRow(i).getCell(13));
        	String remark=toString(sheet.getRow(i).getCell(14));

        	//首先插入users表
        	String userId=UUID.randomUUID().toString();
        	String sql1="insert into users(userId,userName,passWord,personName,userType) value('"+userId+"','"+passWord+"','"+userName+"','"+personName+"','1')";
        	String sql2="";
        	String sql3="";
        	String academyId="";
        	String deptId="";
        	String titleId="";
        	String troomId="";
        	//导入角色中间表
        	if(!StringUtils.isEmpty(roles)){
        		String roleNames[]=roles.split(",");
        		for(String roleName:roleNames){
        			Role role=roleServiceI.findRoleByRoleName(roleName);
        			if(role!=null){
        				String urId=UUID.randomUUID().toString();
        				sql2="insert into users_roles(id,userId,roleId) value('"+urId+"','"+userId+"','"+role.getRoleId()+"')";
        			}
        		}
        	}
        	String teaId=UUID.randomUUID().toString();
        	sql3="insert into wfu_teacherinfo(teaId,userName,teaName,teaIdCard,teaEmail,teaPhone,teaAcademy,teaTitle,teaDept,teaTroom,teaAge,teaSex,createTime,roleNames,address,remark) " +
        			"value('"+teaId+"','"+userName+"','"+personName+"','"+idCard+"','"+email+"','"+phone+"',";
        	//导入教师信息表，首先查找教研室，职称，院系，部门信息（根据名称）
        	if(!StringUtils.isEmpty(academy)){
        		DataDictionary academypo = dataDictionaryServiceI.findDataDicByName(academy);
        		if(academypo!=null){
        			academyId=academypo.getDicId();   //获得院系id
        			sql3+="'"+academyId+"',";
        		}else{
        			sql3+=null+",";
        		}
        	}else{
        		sql3+=null+",";
        	}

        	if(!StringUtils.isEmpty(title)){
        		DataDictionary titlepo = dataDictionaryServiceI.findDataDicByName(title);
        		if(titlepo!=null){
        			titleId=titlepo.getDicId();
        			sql3+="'"+titleId+"',";
        		}else{
        			sql3+=null+",";
        		}
        	}else{
        		sql3+=null+",";
        	}
        	if(!StringUtils.isEmpty(dept)){
        		Dept deptpo= deptServiceI.findDeptByDeptName(dept);
        		if(deptpo!=null){
        			deptId=deptpo.getDeptId();
        			sql3+="'"+deptId+"',";
        		}else{
        			sql3+=null+",";
        		}
        	}else{
        		sql3+=null+",";
        	}
        	//查找教研室时需注意，在本院系中查找
        	if(!StringUtils.isEmpty(troom)){
        		Troom troompo=troomServiceI.findTroomByNameAndAcd(troom,academyId);
        		if(troompo!=null){
        			troomId=troompo.getTroomId();
        			sql3+="'"+troomId+"',";
        		}else{
        			sql3+=null+",";
        		}
        	}else{
        		sql3+=null+",";
        	}
        	
        	if(age==""){
        		age="0";
        	}
        	sql3+="'"+age+"','"+sex+"','"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"','"+roles+"','"+address+"','"+remark+"')";
        	
        	try {
        		userServiceI.executesql(sql1);
        		userServiceI.executesql(sql2);
        		userServiceI.executesql(sql3);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }
        //关闭输入流 
        inp.close(); 
        jsonData.setStatus("true");
        return jsonData;  
    }
	public String toString(Object str){
		if(str==null||str==""){
			return "";
		}else{
			return str.toString();
		}
	}
	/**
	 * 功能:批量导入学生信息
	 * @author Lixiangmao
	 * @create 2013/12/25
	 */
	@ResponseBody
	@RequestMapping("/importStudentInfos")
	public JsonData importStudentInfos(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model) throws Exception{
		JsonData jsonData = new JsonData();
		String newFileName = Constants.upload(file, request, model);  //现将文件上传保存
        // poi读取excel 
        //创建要读入的文件的输入流 
        InputStream inp = new FileInputStream(request.getSession().getServletContext().getRealPath("upload")+"/"+newFileName);     
        //根据上述创建的输入流 创建工作簿对象 
        Workbook wb = WorkbookFactory.create(inp); 
        //得到第一页 sheet 
        //页Sheet是从0开始索引的 
        Sheet sheet = wb.getSheetAt(0); 
        //利用foreach循环 遍历sheet中的所有行 详细字段在模板中见
        for(int i = 1;i <= sheet.getLastRowNum();i++){
        	String userName = toString(sheet.getRow(i).getCell(1));
        	String stuName = toString(sheet.getRow(i).getCell(2));
        	String stuSex = toString(sheet.getRow(i).getCell(3));
        	String stuNation = toString(sheet.getRow(i).getCell(4));
        	String stuAcademy = toString(sheet.getRow(i).getCell(5));
        	String stuMajor = toString(sheet.getRow(i).getCell(6));
        	String stuSubject = toString(sheet.getRow(i).getCell(7));
        	String stuGrade = toString(sheet.getRow(i).getCell(8));
        	String stuClass = toString(sheet.getRow(i).getCell(9));
        	String StuPoliticalLandscape = toString(sheet.getRow(i).getCell(10));
        	String stuPost = toString(sheet.getRow(i).getCell(11));
        	String stuPhone = toString(sheet.getRow(i).getCell(12));
        	String stuEmail = toString(sheet.getRow(i).getCell(13));
        	String roleNames = toString(sheet.getRow(i).getCell(14));
        	String stuAge = toString(sheet.getRow(i).getCell(15));
        	//sql1 插入用户表 sql2 插入权限表 sql3 插入学生信息表
        	String sql1 ="";
        	String sql2 ="";
        	String sql3 ="";
        	//开始插入用户表 userId userName passWord personName 由于是学生,所以userType的值为2
        	String userId = UUID.randomUUID().toString();
        	sql1 = "insert into users(userId,userName,passWord,personName,userType) values ('"+userId+"','"+userName+"','"+userName+"','"+stuName+"','2')";
        	//开始插入用户_权限表 id userId roleId
        	if(!StringUtils.isEmpty(roleNames)){
        		String roleNames1[] = roleNames.split(",");
        		for(String roleName : roleNames1){
        			Role role = roleServiceI.findRoleByRoleName(roleName);
        			if(!StringUtils.isEmpty(role)){
        				String urId = UUID.randomUUID().toString();
        				sql2 = "insert into users_roles(id,userId,roleId) values ('"+urId+"','"+userId+"','"+role.getRoleId()+"')";
        			}
        		}
        	}
        	//开始插入学生信息表 stutId userName stuName stuSex stuNation stuSubject
        	//stuGrade stuClass StuPoliticalLandscape stuPost stuPhone stuEmail 
        	//外键： stuMajor stuAcademy
        	String stutId = UUID.randomUUID().toString();
        	if(stuAge==""){
        		stuAge="0";
        	}
        	sql3 = "insert into wfu_studentinfo(stutId,stuAge,roleNames,userName,stuName,stuSex,stuNation,stuSubject,stuGrade,stuClass,StuPoliticalLandscape,stuPost,stuPhone,stuEmail,stuAcademy,stuMajor) "
        			+ "values ('"+stutId+"','"+stuAge+"','"+roleNames+"','"+userName+"','"+stuName+"','"+stuSex+"','"+stuNation+"','"+stuSubject+"','"+2010+"','"+stuClass+"','"+StuPoliticalLandscape+"','"+stuPost+"','"+stuPhone+"','"+stuEmail+"',";
        	String academyId = "";
        	//String majorId  = "";
        	if(!StringUtils.isEmpty(stuAcademy)){
        		DataDictionary dataDictionary = dataDictionaryServiceI.findDataDicByName(stuAcademy);
        		if(!StringUtils.isEmpty(dataDictionary)){
        			academyId = dataDictionary.getDicId();
        			sql3 += "'"+academyId+"',";
        		}else {
					sql3 +=null+",";
				}
        	}
        	if(!StringUtils.isEmpty(stuMajor)){
        		Major major = majorServiceI.findMajorByMajorNameAndAcademyId(stuMajor,academyId);
        		if(!StringUtils.isEmpty(major)){
        			sql3 += "'"+major.getMajorId()+"')";
        		}else {
					sql3 +=null+")";
				}
        	}
        	try {
				userServiceI.executesql(sql1);
				userServiceI.executesql(sql2);
				userServiceI.executesql(sql3);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
      //关闭输入流 
        inp.close(); 
        jsonData.setStatus("true");
        return jsonData;  
	}}
