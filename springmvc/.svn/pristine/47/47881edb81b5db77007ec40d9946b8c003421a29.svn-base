package com.topic.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.entity.DataDictionary;
import com.tepusoft.service.DataDictionaryServiceI;
import com.tepusoft.utils.Constants;
import com.tepusoft.utils.PageUtil;
import com.topic.entity.Topic;
import com.topic.service.AdminTopicServiceI;

@Service
public class AdminTopicServiceImpl implements AdminTopicServiceI {

	@Autowired
	private BaseDaoI<Topic> baseDaoI;
	@Autowired
	private DataDictionaryServiceI dataDictionaryServiceI;

	@Override
	public List<Topic> findAllTopicByAcademyId(PageUtil pageUtil,
			String academyId,Map<String,Object> map) {
		String hql = "from Topic t where t.academy.dicId='"+academyId+"' ";
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("academyId", academyId);
		hql+=Constants.getSearchConditionsHQL("t", map);
		hql+=" order by t.topicName";
		
		return baseDaoI.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public BigInteger findAllTopicByAcademyCount(String academyId,Map<String,Object> map) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from wfu_topic w where w.topicAcademyId='"+academyId+"'";
		//Map<String, Object> map = new HashMap<String, Object>();
		//map.put("academyId", academyId);
		sql+=Constants.getSearchConditionsHQL("w", map);
		return baseDaoI.countBySql(sql, map);

	}
	
	@Override
	public List<Topic> findAllTopicByTopicNameOrTopicTypeOrTopicPersonName(String teacherTitle,
			String topicName, String topicType, String topicPersonName,
			String academyId) {
		// TODO Auto-generated method stub
		String hql = "from Topic t where t.academy.dicId = '" + academyId + "'";
		if (!StringUtils.isEmpty(teacherTitle)) {
			hql += " and t.teacherInfo.teaTitle = '" + teacherTitle
					+"'" ;
		}
		if (!StringUtils.isEmpty(topicName)) {
			hql += " and t.topicName like '%" + topicName + "%'";
		}
		if (!StringUtils.isEmpty(topicType)) {
			hql += " and t.topType.dicId = '" + topicType + "'";
		}
		if (!StringUtils.isEmpty(topicPersonName)) {
			hql += " and t.teacherInfo.teaName like '%" + topicPersonName
					+ "%'";
		}
		return baseDaoI.find(hql);
	}

	@Override
	public Topic findByTopicId(String topicId) {
		// TODO Auto-generated method stub
		return baseDaoI.get(Topic.class, topicId);

	}

	@Override
	public void updateTopic(Topic topic) {
		// TODO Auto-generated method stub
		baseDaoI.update(topic);
	}


	@Override
	public List<Topic> findSelectedTopicByAcademyId(PageUtil pageUtil,
			String academyId) {
		// TODO Auto-generated method stub
		String hql="from Topic t where t.isSelect='1' order by t.teacherName" ;
		return baseDaoI.find(hql);
	}

	@Override
	public BigInteger findSelectedTopicByAcademyCount(String academyId) {
		// TODO Auto-generated method stub
		String sql = "select count(*) from wfu_topic w where w.topicAcademyId=:academyId and w.isSelect='1' ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("academyId", academyId);
		return baseDaoI.countBySql(sql, map);
		
	}
	@Override
	public List<Topic> findUnTopicByAcademyId(PageUtil pageUtil,
			String academyId) {
		String hql = "from Topic t where t.academy.dicId=:academyId and t.isSelect="+"0"+" order by t.topicName";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("academyId", academyId);
		return baseDaoI.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public BigInteger findUnTopicByAcademyCount(String academyId) {
		String sql = "select count(*) from wfu_topic w where w.topicAcademyId=:academyId and w.isSelect="+"0";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("academyId", academyId);
		return baseDaoI.countBySql(sql, map);
	}

	@Override
	public List<Topic> findUnTopicByTopicNameOrTopicTypeOrTopicPersonName(
			String topicName, String topicType, String topicPersonName,
			String academyId) {
		// TODO Auto-generated method stub
		String hql = "from Topic t where t.academy.dicId = '" + academyId + "' and t.isSelect= "+"0";
		if (!StringUtils.isEmpty(topicName)) {
			hql += " and t.topicName like '%" + topicName + "%'";
		}
		if (!StringUtils.isEmpty(topicType)) {
			hql += " and t.topType.dicId = '" + topicType + "'";
		}
		if (!StringUtils.isEmpty(topicPersonName)) {
			hql += " and t.teacherInfo.teaName like '%" + topicPersonName
					+ "%'";
		}
		return baseDaoI.find(hql);
	}


}
