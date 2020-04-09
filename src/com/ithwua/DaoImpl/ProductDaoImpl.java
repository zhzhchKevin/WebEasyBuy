package com.ithwua.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ithwua.IDao.IProductDao;
import com.ithwua.bean.Product;
import com.ithwua.util.ConnectionFactory;
import com.ithwua.util.DBUtils;
import com.ithwua.util.JDBCTemplate;
import com.ithwua.util.Page_Util;
import com.ithwua.util.PreparedStatementSetter;
import com.ithwua.util.ResultSetHandler;


public class ProductDaoImpl extends JDBCTemplate implements IProductDao{
    private Connection conn;
    private ResultSet rs;
    PreparedStatement pst;
	@Override
	public List<Product> queryAllProducts() {
		List<Product> products=new ArrayList<>();
		conn=ConnectionFactory.getConnection();
		
		String sql="select * from HWUA_PRODUCT";
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				Product productHere=new Product();
				products.add(productHere);
				productHere.setId(rs.getLong(1));
				productHere.setName(rs.getString(2));
				productHere.setDescription(rs.getString(3));
				productHere.setPrice(rs.getDouble(4));
				productHere.setStock(rs.getLong(5));
				productHere.setCategoryId(rs.getLong(6));
				productHere.setChildCategoryId(rs.getLong(7));
				productHere.setFileName(rs.getString(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtils.close(rs,pst,conn);
		}
		
		return products;
	}
	

	/**
	 * 根据当前的页数 查询  需要显示的数据
	 * */
	@Override
	public List<Product> queryProducts(final int nowPage){
		final List<Product> list=new ArrayList<Product>();
		String sql="SELECT * FROM "+
		"(SELECT t.*, ROWNUM RN  FROM "+ 
        "(SELECT * FROM hwua_product) "+ 
        "t WHERE ROWNUM<=?) "+
         "WHERE RN >=?";
		
		this.query(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1,nowPage*Page_Util.PAGE_SIZE);
				pstmt.setInt(2,(nowPage-1)*Page_Util.PAGE_SIZE+1);
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					Product productHere=new Product();
					productHere.setId(rs.getLong(1));
					productHere.setName(rs.getString(2));
					productHere.setDescription(rs.getString(3));
					productHere.setPrice(rs.getDouble(4));
					productHere.setStock(rs.getLong(5));
					productHere.setCategoryId(rs.getLong(6));
					productHere.setChildCategoryId(rs.getLong(7));
					productHere.setFileName(rs.getString(8));
					list.add(productHere);
				}
			}
		});
		return list;
	}
	/**
	 * 统计需要显示的数据总条数
	 * */
	int productCount=0;
	@Override
	public int queryProductCount(){
		System.out.println("dao");
		String sql="select count(*) from hwua_product";
		this.query(sql,null,new ResultSetHandler() {
			
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				if(rs.next()){
					productCount=rs.getInt(1);
				}
			}
		});
		return productCount;
	}

	@Override
	public Product queryProductById(final String productId) {
		String sql="select * from hwua_product where hp_id=?";
		final Product productHere=new Product();
		this.query(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,productId);
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				if(rs.next()){
					productHere.setId(rs.getLong(1));
					productHere.setName(rs.getString(2));
					productHere.setDescription(rs.getString(3));
					productHere.setPrice(rs.getDouble(4));
					productHere.setStock(rs.getLong(5));
					productHere.setCategoryId(rs.getLong(6));
					productHere.setChildCategoryId(rs.getLong(7));
					productHere.setFileName(rs.getString(8));
				}
			}
		});
		return productHere;
	}

//查询浏览过的商品
	@Override
	public List<Product> queryProductsByIds(String sql,final String[] productIds) {
		final List<Product> products=new ArrayList<Product>();
		final Product productHere=new Product();
		this.query(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				for(int i=1;i<=productIds.length;i++){
					pstmt.setString(i,productIds[i-1]);
				}
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					Product productHere=new Product();
					productHere.setId(rs.getLong(1));
					productHere.setName(rs.getString(2));
					productHere.setDescription(rs.getString(3));
					productHere.setPrice(rs.getDouble(4));
					productHere.setStock(rs.getLong(5));
					productHere.setCategoryId(rs.getLong(6));
					productHere.setChildCategoryId(rs.getLong(7));
					productHere.setFileName(rs.getString(8));
					products.add(productHere);
				}
			}
		});
		return products;

	}


	@Override
	public List<Product> queryProductsByCateId(String sql, final String categoryId) {
		final List<Product> products=new ArrayList<Product>();
		this.query(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,categoryId);
			}
		},new ResultSetHandler() {
			
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
				while(rs.next()){
					Product productHere=new Product();
					productHere.setId(rs.getLong(1));
					productHere.setName(rs.getString(2));
					productHere.setDescription(rs.getString(3));
					productHere.setPrice(rs.getDouble(4));
					productHere.setStock(rs.getLong(5));
					productHere.setCategoryId(rs.getLong(6));
					productHere.setChildCategoryId(rs.getLong(7));
					productHere.setFileName(rs.getString(8));
					products.add(productHere);
				}
				
			}
		});
		return products;
	}

}
