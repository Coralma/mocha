package com.coral.foundation.md.hbm.impl

import java.util.Properties
import java.util.List
import com.coral.foundation.utils.StrUtils
import com.coral.foundation.md.model.Mocha

class HbmTemplate {
	def generateCfg(Properties p)'''
«««		«val driverClass="com.mysql.jdbc.Driver"»
«««		«val url ="jdbc:mysql://localhost:3306/user_role"»
«««		«val userName = "user"»
«««		«val password = "Password1"»
«««		«val poolSize = "1"»
«««		«val dialect = "org.hibernate.dialect.MySQLDialect"»
«««		«val showSql="true"»
«««		«val formatSql="true"»
«««		«val hbm2ddlAuto="update"»
		«val driverClass = p.get("driverClass")»
		«val url=p.get("url")»
		«val userName=p.get("userName")»
		«val password=p.get("password")»
		«val poolSize=p.get("poolSize")»
		«val dialect=p.get("dialect")»
		«val showSql=p.get("showSql")»
		«val formatSql=p.get("formatSql")»
		«val hbm2ddlAuto=p.get("hbm2ddlAuto")»
		«val hbmfile=p.get("hbmfile")»
		<?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
		<hibernate-configuration>
		    <session-factory>
		        <property name="hibernate.connection.driver_class">«driverClass»</property>
		        <property name="hibernate.connection.url">«url»</property>
		        <property name="hibernate.connection.username">«userName»</property>
		        <property name="connection.password">«password»</property>
		        <property name="connection.useUnicode">true</property>
        		<property name="connection.characterEncoding">UTF-8</property>
		        <property name="connection.pool_size">«poolSize»</property>
		        <property name="hibernate.dialect">«dialect»</property>
		        <property name="show_sql">«showSql»</property>
		        <property name="format_sql">«formatSql»</property>
		        <property name="hbm2ddl.auto">«hbm2ddlAuto»</property>
		        <property name="hibernate.connection.useUnicode">true</property>
		        <property name="hibernate.connection.characterEncoding">UTF-8</property>
		        <!-- Enable Hibernate's automatic session context management -->
		        <property name="current_session_context_class">thread</property>
		        <property name="javax.persistence.validation.mode">none</property>
		        <property name="connection.autocommit">true</property>
		        <mapping resource="«hbmfile»"/>
		    </session-factory>
		</hibernate-configuration>
	'''
	
	def generateHbm(List<Mocha> coralList)'''
		<?xml version="1.0" encoding="UTF-8"?>
		<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
		<hibernate-mapping>
			«FOR coral : coralList»
				«val entityPackage = coral.getEntityPackage»
				«FOR e : coral.getEntityList»
				«val entityName = e.getEntityName»
				«val fullEntityName = entityPackage +"."+ entityName»
				<class name="«entityPackage».«entityName»" table="«StrUtils::genDBName(entityName)»" lazy="false">
					<id name="id" column="ID" type="java.lang.Long">
						<generator class="native"/>
					</id>
					«FOR p : e.getProperties»
						«val pn = p.getPropertyName»
						«val cn = StrUtils::genDBName(pn)»
						«val type = p.getType»
						«val ref = p.getRef»
						«val orm = p.getOrm»
						«IF orm == null»
							«IF "String".equals(type)»
								<property name="«pn»" column="«cn»" not-null="«p.required»" type="java.lang.String"/>
							«ELSEIF "Integer".equals(type)»
								<property name="«pn»" column="«cn»" not-null="«p.required»" type="java.lang.Integer"/>
							«ELSEIF "Long".equals(type)»
								<property name="«pn»" column="«cn»" not-null="«p.required»" type="java.lang.Long"/>
							«ELSEIF "Date".equals(type)»
								<property name="«pn»" column="«cn»" not-null="«p.required»" type="timestamp"/>
							«ELSEIF "BigDecimal".equals(type)»
								<property name="«pn»" column="«cn»" not-null="«p.required»" type="java.math.BigDecimal"/>
							«ENDIF»
						«ELSE»
«««								«val relate = p.relate»
								«IF orm.equals("one-to-many")»
									«IF type.equals("Set")»
										<set name="«pn»" cascade="all" inverse="false" lazy="false">
											<key column="«entityName»__ID"/>
											<one-to-many class="«getRefEntityClass(coralList,ref)»"/>
										</set>
									«ELSEIF type.equals("List")»
										<list name="«pn»"  cascade="all" inverse="false" lazy="false">
											<key column="«ref»__ID" not-null="true"/>
											<list-index column="«pn»Idx" />
											<one-to-many class="«getRefEntityClass(coralList,ref)»"/>
										</list>
									«ENDIF»
								«ELSEIF orm.equals("many-to-one")»
									<many-to-one name="«pn»" class="«fullEntityName»" 
										cascade="save-update" not-null="false" column="«ref»__ID" />
								«ELSEIF orm.equals("one-to-one")»
«««									«IF relate.equals("contain")»
«««										<one-to-one name="«pn»" property-ref="«ref»" class="«getRefEntityClass(coralList,ref)»" cascade="save-update"/>
«««									«ELSEIF relate.equals("refer")»
«««										<one-to-one name="«pn»" class="«getRefEntityClass(coralList,ref)»"/>
«««									«ENDIF»
								«ENDIF»
							«ENDIF»
					«ENDFOR»
					<property name="abstractData" column="ABSTRACT_DATA" not-null="false" type="java.lang.String"/>
					<property name="createDate" column="CREATE_TIME" not-null="false" type="timestamp"/>
					<property name="lastUpdateDate" column="LAST_MODIFIED_TIME" not-null="false" type="timestamp"/>
				</class>
		        «ENDFOR»
	        «ENDFOR»
		</hibernate-mapping>
	'''
	
	def String getRefEntityClass(List<Mocha> coralList, String ref) {
		for(coral : coralList) {
			val entityPackage = coral.getEntityPackage;
			for(e : coral.getEntityList) {
				if(ref.equals(e.getEntityName)) {
					return entityPackage + "." + ref;  
				}
			}
		}
		return "";
	}
}