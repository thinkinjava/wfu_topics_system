package com.topic.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tepusoft.entity.DataDictionary;
import com.tepusoft.entity.StudentInfo;
import com.tepusoft.entity.TeacherInfo;

@Entity
@Table(name = "wfu_choosetopic")
public class ChooseTopic implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7684559988780895955L;
	private String choosedId;
	private Topic topicId;
	private TeacherInfo teacherId;
	private StudentInfo studentId;
	private DataDictionary academyId;
	private Date createTime;

	@Id
	public String getChoosedId() {
		return choosedId;
	}

	public void setChoosedId(String choosedId) {
		this.choosedId = choosedId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="topicId")
	public Topic getTopicId() {
		return topicId;
	}

	public void setTopicId(Topic topicId) {
		this.topicId = topicId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="teacherId")
	public TeacherInfo getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(TeacherInfo teacherId) {
		this.teacherId = teacherId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="studentId")
	public StudentInfo getStudentId() {
		return studentId;
	}

	public void setStudentId(StudentInfo studentId) {
		this.studentId = studentId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="academyId")
	public DataDictionary getAcademyId() {
		return academyId;
	}

	public void setAcademyId(DataDictionary academyId) {
		this.academyId = academyId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
