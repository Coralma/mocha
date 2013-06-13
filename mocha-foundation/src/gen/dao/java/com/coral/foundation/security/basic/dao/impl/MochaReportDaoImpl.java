package com.coral.foundation.security.basic.dao.impl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.coral.foundation.security.basic.dao.*;
import com.coral.foundation.security.model.*;
import com.coral.foundation.jpa.impl.JpaDao;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * MochaReportDaoImpl is a auto Generated class. Please don't modify it.
 * 
 * @author Coral
 */
public class MochaReportDaoImpl extends JpaDao<MochaReport>
		implements
			MochaReportDao {

	Logger log = LoggerFactory.getLogger(MochaReportDaoImpl.class);
	public MochaReportDaoImpl() {
		super();
		log.debug("" + MochaReportDaoImpl.class);
	}

	@Override
	public void saveMochaReport(MochaReport mochaReport) {
		entityManager.persist(mochaReport);
	}

	@Override
	@Transactional
	public ArrayList executeReport(MochaReport mochaReport) {
		// CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		// final Query query = entityManager.createNativeQuery(mochaReport
		// .getReportPureQuery());

		// entityManager.getTransaction().begin();
		// java.sql.Connection cnn =
		// entityManager.unwrap(java.sql.Connection.class);

		Session hibernateSession = entityManager.unwrap(Session.class);
		Connection conn = hibernateSession.connection();
		ResultSet rs = null;
		Statement st;
		ArrayList reportRowDataReslt = new ArrayList();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(mochaReport.getReportPureQuery());

			ResultSetMetaData rsmd;
			try {
				rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				// output the column name
				String[] columnNames = new String[columnCount];
				for (int i = 1; i <= columnCount; i++) {

					System.out.print(rsmd.getColumnName(i));
					columnNames[i-1] = rsmd.getColumnName(i);
					System.out.print("(" + rsmd.getColumnTypeName(i) + ")");
					System.out.print(" | ");
				}
				reportRowDataReslt.add(columnNames);

				// output the row data
				while (rs.next()) {
					String[] columnValues = new String[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						System.out.print(rs.getString(i) + " | ");
						columnValues[i-1] = rs.getString(i);
					}
					reportRowDataReslt.add(columnValues);
					System.out.println();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return reportRowDataReslt;
	}
}