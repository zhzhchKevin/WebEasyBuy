/**
 * 
 */
package com.ithwua.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ��������ռλ����ֵ��ͨ������
 * @author Administrator
 *
 */
public interface PreparedStatementSetter {
      public void setValues(PreparedStatement pstmt) throws SQLException;
}
