package com.ithwua.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ithwua.IDao.ICartDao;
import com.ithwua.IDao.IUserDao;
import com.ithwua.IService.ICartService;
import com.ithwua.bean.Cart;
import com.ithwua.util.JDBCTemplate;
import com.ithwua.util.PreparedStatementSetter;
import com.ithwua.util.ResultSetHandler;

public class CartDaoImpl extends JDBCTemplate implements ICartDao {
	IUserDao userDao=new UserDaoImpl();

	/**
	 * 根据用户名称查询包含所有的Cart的集合
	 */
	@Override
	public List<Cart> queryCartsByName(final String userName) {
		
		//根据用户名称查询用户的ID
		final long userId=userDao.queryUserId(userName);

		String sql="select c.id,p.hp_id,p.hp_name,c.quantity,p.hp_price,p.hp_file_Name from hwua_product p,hwua_cart c where p.hp_id=c.pid and c.userid=?";
		final ArrayList<Cart> carts=new ArrayList<Cart>(); 
		this.query(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1,userId);
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
			     while(rs.next()){
			    	 Cart cart=new Cart();
						cart.setId(rs.getLong(1));
				        cart.setProductId(rs.getLong(2));
		                cart.setProductName(rs.getString(3));
		                cart.setQuantity(rs.getLong(4));
		                cart.setProductPrice(rs.getDouble(5));
		                cart.setFileName(rs.getString(6));
		                carts.add(cart);
				}
			}
		});
		return carts;
	}

	
	/**
	 * 根据CARTID查询CART
	 */
	@Override
	public Cart queryCartsByCartId(Connection conn,final String cartId) {
		
		String sql="select c.id,p.hp_id,p.hp_name,c.quantity,p.hp_price,p.hp_file_Name from hwua_product p,hwua_cart c where p.hp_id=c.pid and c.id=?";
		final ArrayList<Cart> carts=new ArrayList<Cart>(); 
		this.queryByConn(conn,sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,cartId);
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
			     while(rs.next()){
					Cart cart=new Cart();
					cart.setId(rs.getLong(1));
			        cart.setProductId(rs.getLong(2));
	                cart.setProductName(rs.getString(3));
	                cart.setQuantity(rs.getLong(4));
	                cart.setProductPrice(rs.getDouble(5));
	                cart.setFileName(rs.getString(6));
	                carts.add(cart);
				}
			}
		});
		return carts.get(0);
	}


	/**
	 * 修改购物车对应的商品数量
	 */
	@Override
	public int updateCarts(final String cartId, final String quantity) {
		String sql="update hwua_cart set quantity=? where Id=?";
		 int rows=this.update(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,quantity);
				pstmt.setString(2, cartId);
			}
		});
		 return rows;
	}
//删除cart
	@Override
	public int deleteCartById(final String cartId) {
		String sql="delete hwua_cart where Id=?";
		int rows=this.update(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, cartId);
			}
		});
		 return rows;
	}
	//事务中的删除
		@Override
		public int deleteCartById(Connection conn, final String cartId) {
			String sql="delete hwua_cart where Id=?";
			int rows=this.updateByConn(conn,sql,new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setString(1, cartId);
				}
			});
			 return rows;
		}

	
	@Override
	public int addCart(final String productId, final String userName) {
		final int[] flag=new int[1];
		final int[] cartId=new int[1];
		final int[] quantity=new int[1];
			
		//根据用户名字查询用户的ID
		final long userId=userDao.queryUserId(userName);
		
		//查询该用户的购物车中是否已经有指定的商品
		String sql4="select c.id,c.quantity from hwua_cart c where c.userid=? and c.pid=?";
		this.query(sql4,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setLong(1,userId);
				pstmt.setString(2,productId);
			}
		}, new ResultSetHandler() {
			@Override
			public void handleRS(ResultSet rs) throws SQLException {
			     if(rs.next()){
                     flag[0]=1;
                     cartId[0]=rs.getInt(1);
                     quantity[0]=rs.getInt(2);
				}
			}
		});
		
		
		//如果购物车中已经有该商品，则对应记录修改数量
		if(flag[0]==1){
			System.out.println("购物车中已经有该商品");
			final int quantityNew=quantity[0]+1;
			String sql1="update hwua_cart set quantity=? where id=? ";
			return this.update(sql1,new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1,quantityNew);
					pstmt.setInt(2, cartId[0]);
				}
			});
        //如果购物车中还没有该商品，则插入新的购物车记录
		}else{
			System.out.println("购物车中还没有该商品，添加新记录");
			String sql2="insert into hwua_cart values(seq_cart.nextval,?,1,?)";
			return this.update(sql2, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement pstmt) throws SQLException {
					pstmt.setString(1,productId);
					pstmt.setLong(2, userId);
				}
			});	
		}

	}

   
}
