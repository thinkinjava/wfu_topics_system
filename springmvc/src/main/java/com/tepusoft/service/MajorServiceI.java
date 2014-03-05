package com.tepusoft.service;

import java.math.BigInteger;
import java.util.List;

import com.tepusoft.entity.Major;
import com.tepusoft.utils.PageUtil;


public interface MajorServiceI {
public List<Major> findAllMajors(String academy,PageUtil pageUtil);

public BigInteger majorsCount();

public void saveMajor(Major major);

public Major findMajorByMajorId(String majorId);

public void updateMajor(Major major);

public void deleteMajor(Major major);

public List<Major> findAllMajorsByAcademy(String dicId);

public Major findMajorByMajorNameAndAcademyId(String majorName,String academyId);

public List<Major> findAllMajors();


}
