package com.topic.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.utils.Constants;
import com.tepusoft.utils.PageUtil;
import com.topic.entity.Topic;
import com.topic.service.TeaTopicServiceI;

@Service
public class TeaTopicServiceImpl implements TeaTopicServiceI {

	@Autowired
	private BaseDaoI<Topic> baseDaoI;

	@Override
	public List<Topic> findAllTopicByOwn(String userId, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "from Topic t where t.teacherInfo.teaId=:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return baseDaoI.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public BigInteger findAllTopicByOwnCount(String userId) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from wfu_topic w where w.topicPersonId=:userId";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return baseDaoI.countBySql(sql, map);
	}

	@Override
	public Topic findByTopicId(String topicId) {
		// TODO Auto-generated method stub

		return baseDaoI.get(Topic.class, topicId);
	}

	@Override
	public void deletetopic(Topic topic) {
		// TODO Auto-generated method stub
		baseDaoI.delete(topic);
	}

	@Override
	public void saveTopic(Topic topic) {
		// TODO Auto-generated method stub
		baseDaoI.save(topic);
	}

	@Override
	public void updateTopic(Topic topic) {
		// TODO Auto-generated method stub
		baseDaoI.update(topic);
	}

	@Override
	public BigInteger getCountByTopicName(String topicName) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from wfu_topic w where w.topicName=:topicName";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("topicName", topicName);
		return baseDaoI.countBySql(sql, map);
	}

	@Override
	public List<Topic> findAllTopicByAcademyId(PageUtil pageUtil,
			String academyId,String majorId,Map<String, Object> map) {
		String hql = "from Topic t where t.academy.dicId='"+academyId+"' and t.isSelect='0' and t.topicMajorIds like '%"+majorId+"%'";
		//Map<String, Object> map = new HashMap<String,Object>();
		hql+=Constants.getSearchConditionsHQL("t", map);
		//hql+=Constants.getGradeSearchConditionsHQL("t", pageUtil);
		
		return baseDaoI.find(hql,map,pageUtil.getPage(),pageUtil.getRows());
	}

	@Override
	public BigInteger findAllTopicByAcademyCount(String academyId,String majorId,Map<String, Object> map) {
		String sql = "select count(*) from wfu_topic w where w.topicAcademyId='"+academyId+"' and w.isSelect='0' and w.topicMajorIds like '%"+majorId+"%' ";
		//Map<String, Object> map = new HashMap<String,Object>();
		//map.put("academyId", academyId);
		sql+=Constants.getSearchConditionsHQL("w", map);
		
		return baseDaoI.countBySql(sql,map);
	}

	@Override
	public List<Topic> findAllTopicByTopicNameOrTopicTypeOrTopicPersonName(String topicName, String topicType,
			String topicPersonName,String academyId) {
		String hql = "from Topic t where t.academy.dicId = '"+academyId+"'";
		if(!StringUtils.isEmpty(topicName)){
			hql+=" and t.topicName like '%"+topicName+"%'";
		}
		if(!StringUtils.isEmpty(topicType)){
			hql+=" and t.topType.dicId = '"+topicType+"'";
		}
		if(!StringUtils.isEmpty(topicPersonName)){
			hql+=" and t.teacherInfo.teaName like '%"+topicPersonName+"%'";	
		}
		return baseDaoI.find(hql);
	}


	@Override
	public List<Topic> findAllTopicByAcademyId(String academyId) {
		// TODO Auto-generated method stub
		String hql="from Topic t where t.academy.dicId=:academyId";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("academyId", academyId);
		return baseDaoI.find(hql, map);
	}

	@Override
	public List<Topic> findAllTopic() {
		String hql = "from Topic t where 1=1";
		return baseDaoI.find(hql);
	}

	@Override
	public List<Topic> findTopicByMajorId(String majorId) {
		String hql = "from Topic t where t.topicMajorIds like '%"+majorId+"%'";
		return baseDaoI.find(hql);
	}
	

}