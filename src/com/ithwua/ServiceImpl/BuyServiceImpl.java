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
			
			//������һ��order,�������ʧ�ܣ��ͷ���false 
			if(orderDao.createOrder(conn,userBack)==1){
				flag=1;
				System.out.println("����order�ɹ���");
			}else{
				flag=0;
			}
				
			//����userName��õ�����һ��������id
			if(flag==1){
				orderSeq=orderDao.queryOrderSeq(conn,userBack);
			}
			
			//�ٸ���cartId�������е�cart���ϣ�����װ��java����
			
			if(flag==1){
				
				for(String cartId:cartIds){
					//��ѯcart 
					Cart cart=cartDao.queryCartsByCartId(conn, cartId);
					carts.add(cart);
					
				    // ����cartIdɾ����Ӧ��CART
					int rows=cartDao.deleteCartById(conn,cartId);
					if(rows==0){
						//��һ��cartɾ��ʧ��
						System.out.println("��һ��cartɾ��ʧ��");
						flag=0;
					}
					
					System.out.println("�������"+cart.toString());
				}
					
				
		     //���ɶ��detailorder
				if(flag==1){
					for(Cart cart:carts){
						double cost=cart.getProductPrice()*cart.getQuantity();
						//����������е�cost
						sum +=cost;
						//��ho_idӦ��order��id,
						int result=orderDetailDao.createOrderDetail(conn,cart,cost,orderSeq);
						if(result==0){
							//�������detailʧ�ܣ�����flagΪ=0
							flag=0;
							break;
						}
					}
				}
				
		    //�Ѽ����õ��ܽ���Order
				if(flag==1){
					if(orderDao.updateOrder(conn,orderSeq,sum)==0){
						System.out.println("�޸�order���ʧ�ܣ�����0");
						flag=0; 
					}
				}
			
				
				if(flag==1){
					System.out.println("�����޸ĳɹ�");
				}else {
					System.out.println("�����޸�ʧ��");
					//����ʧ�ܣ��ع�
					throw new Exception("�µ�����ʧ��");
				}
			}
			conn.commit();
			System.out.println("�µ������ɹ�");
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
