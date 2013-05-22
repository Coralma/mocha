package com.coral.foundation.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IDBean {

	public abstract Connection getConnection() throws Exception;

	public abstract Connection getMySQLConnection() throws Exception;

	public abstract Connection getSQLiteConnection() throws Exception;

	public abstract void close();

	public abstract void closeAll(ResultSet rs, Statement stmt, IDBean dBean);

}