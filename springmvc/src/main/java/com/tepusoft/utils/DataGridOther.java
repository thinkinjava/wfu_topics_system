package com.tepusoft.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * EasyUI DataGrid模型
 * 
 * @author Lijinzhao
 * 
 */
public class DataGridOther implements java.io.Serializable {

	private int total;
	private List rows = new ArrayList();



	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


}
