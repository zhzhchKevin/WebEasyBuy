package com.ithwua.ServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.ithwua.DaoImpl.CartDaoImpl;
import com.ithwua.DaoImpl.OrderDaoImpl;
import com.ithwua.DaoImpl.OrderDetailDaoImpl;
import com.ithwua.DaoImpl.ProductDaoImpl;
import com.ithwua.IDao.ICartDao;
import com.ithwua.IDao.IOrderDao;
import com.ithwua.IDao.IOrderDetialDao;
import com.ithwua.IDao.IProductDao;
import com.ithwua.IService.IBuyService;
import com.ithwua.bean.Cart;
import com.ithwua.bean.User;
import com.ithwua.util.ConnectionFactory;
import com.ithwua.util.DBUtils;

public class BuyServiceImpl implements IBuyService {
	IOrderDao orderDao=new OrderDaoImpl();
	ICartDao cartDao=new CartDaoImpl();
	IProductDao productDao=new ProductDaoImpl();
	IOrderDetialDao orderDetailDao=new OrderDetailDaoImpl();

	@Override
	public boolean doBuy(String[] cartIds,User userBack) {
		Connection conn=ConnectionFactory.getConnection();

		try {
			conn.setAutoCommit(false);
			int flag=1;
			List<Cart> carts=new ArrayList<Cart>();
			double sum=0;
			long orderSeq=0;
			
			//先生成一个order,如果创建失败，就返回false 
			if(orderDao.createOrder(conn,userBack)==1){
				flag=1;
				System.out.println("生成order成功！");
			}else{
				flag=0;
			}
				
			//根据userName获得倒数第一条订单的id
			if(flag==1){
				orderSeq=orderDao.queryOrderSeq(conn,userBack);
			}
			
			//再根据cartId查找所有的cart集合，并且装入java类中
			
			if(flag==1){
				
				for(String cartId:cartIds){
					//查询cart 
					Cart cart=cartDao.queryCartsByCartId(conn, cartId);
					carts.add(cart);
					
				    // 根据cartId删除对应的CART
					int rows=cartDao.deleteCartById(conn,cartId);
					if(rows==0){
						//有一条cart删除失败
						System.out.println("有一条cart删除失败");
						flag=0;
					}
					
					System.out.println("测试输出"+cart.toString());
				}
					
				
		     //生成多个detailorder
				if(flag==1){
					for(Cart cart:carts){
						double cost=cart.getProductPrice()*cart.getQuantity();
						//计算加总所有的cost
						sum +=cost;
						//将ho_id应用order的id,
						int result=orderDetailDao.createOrderDetail(conn,cart,cost,orderSeq);
						if(result==0){
							//如果创建detail失败，就让flag为=0
							flag=0;
							break;
						}
					}
				}
				
		    //把计算获得的总金额赋给Order
				if(flag==1){
					if(orderDao.updateOrder(conn,orderSeq,sum)==0){
						System.out.println("修改order金额失败，返回0");
						flag=0; 
					}
				}
			
				
				if(flag==1){
					System.out.println("订单修改成功");
				}else {
					System.out.println("订单修改失败");
					//操作失败，回滚
					throw new Exception("下单操作失败");
				}
			}
			conn.commit();
			System.out.println("下单操作成功");
			return true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}finally{
			DBUtils.close(conn);
		}
	}
}
