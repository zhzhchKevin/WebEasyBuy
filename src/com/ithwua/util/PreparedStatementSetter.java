/**
 * 
 */
package com.ithwua.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 用来设置占位符的值的通用类型
 * @author Administrator
 *
 */
public interface PreparedStatementSetter {
      public void setValues(PreparedStatement pstmt) throws SQLException;
}
