package com.ithwua.DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ithwua.IDao.ICategoryDao;
import com.ithwua.bean.Category;
import com.ithwua.util.JDBCTemplate;
import com.ithwua.util.PreparedStatementSetter;
import com.ithwua.util.ResultSetHandler;

public class CategoryDaoImpl extends JDBCTemplate implements ICategoryDao {
   //获得大类的ID
	@Override
	public List<Long> queryALLParentCategorys() {
		final List<Long> categoryIds=new ArrayList<Long>();
		String sql="select hpc_parent_id from HWUA_PRODUCT_CATEGORY group by HPC_PARENT_ID having count(hpc_id)>1";
		this.query(sql,null,new ResultSetHandler() {
			
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					categoryIds.add(rs.getLong(1));
				}
				
			}
		});
		return categoryIds;
	}

	@Override
	public List<Category> queryALLCategorys() {
		final List<Category> categorys=new ArrayList<Category>();
		String sql="select * from HWUA_PRODUCT_CATEGORY";
		this.query(sql,null,new ResultSetHandler() {
			
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					Category category=new Category();
					category.setId(rs.getLong(1));
					category.setName(rs.getString(2));
					category.setParentId(rs.getLong(3));				
					categorys.add(category);
				}
				
			}
		});
		
		return categorys;
	}

	@Override
	public Category queryCategorysById(final Long id) {
		final List<Category> categorys=new ArrayList<Category>();
		String sql="select * from HWUA_PRODUCT_CATEGORY where hpc_id=?";
		this.query(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1, id);
				
			}
		},new ResultSetHandler() {
			
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				if(rs.next()){
					Category category=new Category();
					category.setId(rs.getLong(1));
					category.setName(rs.getString(2));
					category.setParentId(rs.getLong(3));				
					categorys.add(category);
				}
				
			}
		});
		
		return categorys.get(0);
	}

}
