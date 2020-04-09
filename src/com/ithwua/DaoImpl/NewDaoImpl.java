package com.ithwua.DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ithwua.IDao.INewDao;
import com.ithwua.bean.New;
import com.ithwua.util.JDBCTemplate;
import com.ithwua.util.PreparedStatementSetter;
import com.ithwua.util.ResultSetHandler;

public class NewDaoImpl extends JDBCTemplate implements INewDao {

	@Override
	public List<New> queryAllNews() {
		final List<New> news=new ArrayList<New>();
		String sql="select * from hwua_news";
		this.query(sql,null,new ResultSetHandler() {
			
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					New newHere=new New();
					newHere.setId(rs.getLong(1));
					newHere.setTitle(rs.getString(2));
					newHere.setContent(rs.getString(3));
					newHere.setCreateTime(rs.getString(4));
					news.add(newHere);
				}
				
			}
		});
		return news;
	}

}
