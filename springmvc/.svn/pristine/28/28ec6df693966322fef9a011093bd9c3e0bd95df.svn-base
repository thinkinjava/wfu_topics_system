package com.topic.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.utils.PageUtil;
import com.topic.entity.ChooseTopic;
import com.topic.service.ChooseTopicServiceI;
@Service
public class ChooseTopicServiceImpl implements ChooseTopicServiceI {
	@Autowired
	private BaseDaoI<ChooseTopic> baseDaoI;

	@Override
	public List<ChooseTopic> findChoosedTopicByTopicId(String topicId) {
		String hql = "from ChooseTopic c where c.topicId=:topicId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("topicId",topicId);
		return baseDaoI.find(hql, map);
	}

	@Override
	public void saveChoosedTopic(ChooseTopic chooseTopic) {
		baseDaoI.save(chooseTopic);
	}

	@Override
	public List<ChooseTopic> findAllChoosedTopic(PageUtil pageUtil,String studentId) {
		String hql = "from ChooseTopic c where c.studentId.stutId=:studentId";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("studentId", studentId);
		return baseDaoI.find(hql,map,pageUtil.getPage(),pageUtil.getRows());
	}

	@Override
	public BigInteger findAllChoosedTopicCount() {
		String sql = "select count(*) from wfu_choosetopic w where 1=1";
		return baseDaoI.countBySql(sql);
	}

	@Override
	public BigInteger getTopicCount(String topicId) {
		// TODO Auto-generated method stub
		String sql="select count(*) from wfu_choosetopic w where w.topicId=:topicId";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("topicId", topicId);
		return baseDaoI.countBySql(sql, map);
	}

	@Override
	public BigInteger getOwnHasTopicCount(String stuId) {
		// TODO Auto-generated method stub
		String sql="select count(*) from wfu_choosetopic w where w.studentId=:stuId";
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("stuId", stuId);
		return baseDaoI.countBySql(sql, map);

	}

	@Override
	public BigInteger findAllChoosedTopicCountByTeacherId(String teacherId) {
		String sql = "select count(*) from wfu_choosetopic w where w.teacherId=:teacherId";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("teacherId", teacherId);
		return baseDaoI.countBySql(sql, map);
	}

	@Override
	public List<ChooseTopic> findAllChoosedTopicByTeacherId(String teacherId,PageUtil pageUtil) {
		String hql = "from ChooseTopic c where c.teacherId.teaId=:teacherId";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("teacherId", teacherId);
		return baseDaoI.find(hql, map,pageUtil.getPage(),pageUtil.getRows());

	}

	@Override
	public int updateTopicStatus(String status,String teaId) {
		// TODO Auto-generated method stub
		String hql="update Topic t set t.isSelect=:status where t.teacherInfo.teaId=:teaId and t.isSelect = '0'";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("status", status);
		map.put("teaId", teaId);
		return baseDaoI.executeHql(hql, map);
	}

	@Override
	public ChooseTopic findChoosedTopicByChoosedId(String choosedId) {
		return baseDaoI.get(ChooseTopic.class,choosedId);
		
	}

	@Override
	public void deleteChoosedTopic(ChooseTopic chooseTopic) {
		baseDaoI.delete(chooseTopic);
	}

	@Override
	public List<ChooseTopic> findAllChoosedTopicBytopicId(String topicId) {
		// TODO Auto-generated method stub
		String hql="from ChooseTopic c where c.topicId.topicId=:topicId";
		Map<String,Object>map=new HashMap<String ,Object>();
		map.put("topicId", topicId);
		return baseDaoI.find(hql, map);
		
	} 
	

}
