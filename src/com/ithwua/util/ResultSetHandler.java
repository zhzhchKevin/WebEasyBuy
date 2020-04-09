package com.ithwua.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * 结果集的处理
 */
public interface ResultSetHandler {
	public void handleRS(ResultSet rs) throws SQLException;
}
