package com.homepage.model;

import javax.persistence.NoResultException;
import javax.persistence.Query;

public class OrderDao extends abstractDao {

	private Order order;

	public OrderDao(Order order) {
		super();
	}

	public OrderDao() {
		super();
	}

	public long createOrderWithAccountAndUser(Order order, User user) {
		entityMfg.getTransaction().begin();
		if (order.getOrderOID() == null) {
			System.out.println("");
			return -1;
		}
		UserDao userDao = new UserDao();
		User nUser = userDao.findUserByEmail(user.getEmailAddress());
		nUser.getOrders().add(order);
		entityMfg.merge(nUser);
		order.setAccount(nUser.getAccount());
		order.setUser(nUser);
		entityMfg.merge(order);
		entityMfg.getTransaction().commit();
		return order.getId();
	}

	public void updateOrderByOrderOID(String orderOID,
			OrderStatusType statusType) {
		entityMfg.getTransaction().begin();
		Query updateQuery = entityMfg.createNamedQuery("updateOrderByOrderOID");
		updateQuery.setParameter("orderOID", orderOID);
		updateQuery.setParameter("orderStatus", statusType.getOrderStatus());
		updateQuery.executeUpdate();
		entityMfg.getTransaction().commit();
	}

	public Order findOrderByOrderOID(String orderOID) {
		try {
			entityMfg.getTransaction().begin();

			Query findOrderQuery = entityMfg
					.createNamedQuery("findOrderByOrderOID");

			findOrderQuery.setParameter("orderOID", orderOID);

			Order order = (Order) findOrderQuery.getSingleResult();

			entityMfg.getTransaction().commit();

			return order;
		} catch (NoResultException e) {
			System.out.println("Can't find the orderOID with OID: " + orderOID);
			return null;
		}
	}
}
