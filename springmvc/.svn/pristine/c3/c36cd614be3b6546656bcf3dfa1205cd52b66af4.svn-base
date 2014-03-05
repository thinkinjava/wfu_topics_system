package com.topic.service;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.tepusoft.utils.PageUtil;
import com.topic.entity.Topic;

public interface TeaTopicServiceI {
	public List<Topic> findAllTopic();
	public List<Topic> findAllTopicByOwn(String userId, PageUtil pageUtil);
	public BigInteger findAllTopicByOwnCount(String userId);
	public Topic findByTopicId(String topicId);
	public void deletetopic(Topic topic);
	public void saveTopic(Topic topic);
	public void updateTopic(Topic topic);
	public BigInteger getCountByTopicName(String topicName);
	public List<Topic> findAllTopicByAcademyId(PageUtil pageUtil,String academyId,String majorId, Map<String, Object> map);
	public List<Topic> findAllTopicByAcademyId(String academyId);
	public BigInteger findAllTopicByAcademyCount(String academyId,String majorId, Map<String, Object> map);
	public List<Topic> findAllTopicByTopicNameOrTopicTypeOrTopicPersonName(String topicName,String topicType,String topicPersonName,String academyId);
	public List<Topic> findTopicByMajorId(String majorId);
}
