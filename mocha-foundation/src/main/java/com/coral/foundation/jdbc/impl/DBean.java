/**
 * 
 */
package com.coral.foundation.jdbc.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

import com.coral.foundation.jdbc.IDBean;

/**
 * @author coral.ma
 * 
 */
public class DBean implements IDBean {

	private Connection conn;
	private String sqlite = "sqlite";
	private String mysql = "mysql";
	private String connectType = sqlite;

	@PersistenceContext
	protected static EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coral.foundation.jdbc.impl.IBean#getConnection()
	 */
	public Connection getConnection() throws Exception {
		if (connectType.equals(sqlite)) {
			return getSQLiteConnection();
		} else if (connectType.equals(mysql)) {
			return getMySQLConnection();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coral.foundation.jdbc.impl.IBean#getMySQLConnection()
	 */
	public Connection getMySQLConnection() throws Exception {
		 Class.forName("com.mysql.jdbc.Driver");
		 // Setup the connection with the DB
		 Connection conn = DriverManager
		 .getConnection("jdbc:mysql://localhost/cstore?user=coral&password=coralpw");
		 return conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coral.foundation.jdbc.impl.IBean#getSQLiteConnection()
	 */
	public Connection getSQLiteConnection() throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:cstore.db");
		return conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coral.foundation.jdbc.impl.IBean#close()
	 */
	public void close() {
		if (conn == null) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coral.foundation.jdbc.impl.IBean#closeAll(java.sql.ResultSet,
	 * java.sql.Statement, com.coral.foundation.jdbc.impl.IBean)
	 */
	public void closeAll(ResultSet rs, Statement stmt, IDBean dBean) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (dBean != null) {
			dBean.close();
		}
	}

}
