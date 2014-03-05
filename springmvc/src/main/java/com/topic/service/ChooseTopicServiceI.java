package com.topic.service;

import java.math.BigInteger;
import java.util.List;

import com.tepusoft.utils.PageUtil;
import com.topic.entity.ChooseTopic;

public interface ChooseTopicServiceI {

	List<ChooseTopic> findChoosedTopicByTopicId(String topicId);

	void saveChoosedTopic(ChooseTopic chooseTopic);

	List<ChooseTopic> findAllChoosedTopic(PageUtil pageUtil,String studentId);

	BigInteger findAllChoosedTopicCount();

	BigInteger getTopicCount(String topicId);

	BigInteger getOwnHasTopicCount(String stuId);

	BigInteger findAllChoosedTopicCountByTeacherId(String teacherId);

	List<ChooseTopic> findAllChoosedTopicByTeacherId(String teacherId,PageUtil pageUtil);

	int updateTopicStatus(String string,String teaId);
	ChooseTopic findChoosedTopicByChoosedId(String choosedId);

	void deleteChoosedTopic(ChooseTopic chooseTopic);
	public List<ChooseTopic> findAllChoosedTopicBytopicId(String  topicId);
}
