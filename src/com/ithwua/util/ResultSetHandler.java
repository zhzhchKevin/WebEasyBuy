package com.ithwua.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * ������Ĵ���
 */
public interface ResultSetHandler {
	public void handleRS(ResultSet rs) throws SQLException;
}
