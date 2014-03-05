package com.topic.service;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.tepusoft.utils.PageUtil;
import com.topic.entity.Topic;

public interface AdminTopicServiceI {
	
	public List<Topic> findAllTopicByAcademyId(PageUtil pageUtil,String academyId,Map<String,Object> map);
	public List<Topic> findUnTopicByAcademyId(PageUtil pageUtil,String academyId);
	public BigInteger findAllTopicByAcademyCount(String academyId,Map<String,Object> map);

	public BigInteger findSelectedTopicByAcademyCount(String academyId);

	public BigInteger findUnTopicByAcademyCount(String academyId);
	//2014-2-23 lxm 添加搜索教师职称
	public List<Topic> findAllTopicByTopicNameOrTopicTypeOrTopicPersonName(String teacherTitle,String topicName,String topicType,String topicPersonName,String academyId);
	public List<Topic> findUnTopicByTopicNameOrTopicTypeOrTopicPersonName(String topicName,String topicType,String topicPersonName,String academyId);
	public Topic findByTopicId(String topicId);
	public void updateTopic(Topic topic);
	public List<Topic> findSelectedTopicByAcademyId(PageUtil pageUtil,String academyId);
	
}
