package com.tepusoft.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能：定时备份数据库数据
 * @author Lijinzhao
 * time:2013-11-07
 *
 */
public class Task {

	public void testCron(){
		//String format = "yyyy-MM-dd HH:mm:ss";
		//SimpleDateFormat sdf = new SimpleDateFormat(format);
		//System.out.println("定时执行:"+sdf.format(new Date()));
		//System.out.println(backupSql());

	}

	public String backupSql() {
		// TODO Auto-generated method stub
		//生成临时备份文件
		SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmss");
		String fineName="dbBackUp-"+sd.format(new Date());
		String sqlName=fineName+".sql";
		String pathSql="d:"+File.separator+"dbBackUp";
		try {
			File filePathSql = new File(pathSql);
			if(!filePathSql.exists()){
				filePathSql.mkdir();
			}
			StringBuffer sbs = new StringBuffer();//mysqldump -uroot -p123456 --databases XSCJmysql>D:/FILE/data.sql
			sbs.append("mysqldump ");
			sbs.append("-h localhost ");
			sbs.append("--user=root");
			sbs.append(" --password=wfu1234");
			sbs.append(" --lock-all-tables=true ");
			sbs.append("--result-file="+pathSql+File.separator);
			sbs.append(sqlName+" ");
			sbs.append(" --default-character-set=utf8 ");
			sbs.append("springmvc");
			//System.out.println(sbs.toString());
	        Runtime runtime = Runtime.getRuntime();
	        Process child = runtime.exec(sbs.toString());
	        //Runtime.getRuntime().exec(sbs.toString());
	        //读取备份数据并生成临时文件
	        InputStream in = child.getInputStream();
	        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(pathSql), "utf8");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf8"));
	        String line=reader.readLine();
	        while (line != null) {
	                writer.write(line+"\n");
	                line=reader.readLine();
	         }
	         writer.flush();
		} catch (Exception e) {
			
		}
		return sqlName;
	}
	
}
