package com.tepusoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tepusoft.dao.BaseDaoI;
import com.tepusoft.entity.Log;
import com.tepusoft.service.LogServiceI;
@Service
public class LogServiceImpl implements LogServiceI{
	
	@Autowired
	private BaseDaoI<Log> baseDaoI;
	
	@Override
	public void saveLog(Log log) {
		// TODO Auto-generated method stub
		baseDaoI.save(log);
	}
	
}
