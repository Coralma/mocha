package com.coral.foundation.jpa.gen

import java.util.List
import com.coral.foundation.md.model.helper.VGenHelper
import com.coral.foundation.md.model.Mocha

class JPADaoSpringContextTemplate {
	
	List<Mocha> corals
	
	def init(List<Mocha> corals) {
		this.corals = corals;
	}
	
	def generateSpringContextXml()'''
		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:task="http://www.springframework.org/schema/task"
			xmlns:context="http://www.springframework.org/schema/context" xmlns:p="http://www.springframework.org/schema/p"
			xmlns:tx="http://www.springframework.org/schema/tx" xmlns:jaxws="http://cxf.apache.org/jaxws"
			xsi:schemaLocation="
		        http://www.springframework.org/schema/beans
		        http://www.springframework.org/schema/beans/spring-beans.xsd
		        http://www.springframework.org/schema/tx 
		        http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
		        http://www.springframework.org/schema/task
		        http://www.springframework.org/schema/task/spring-task-3.0.xsd
		        http://www.springframework.org/schema/context
		        http://www.springframework.org/schema/context/spring-context-3.0.xsd">
		
«««				<bean id="DaoFactory" class="«VGenHelper::DAO_FACTORY_PKG».GeneratedDaoFactory"></bean>
			«FOR coral : corals»
				«FOR entity : coral.getEntityList»
					«val entityName = entity.entityName»
					«val daoImplClassName = VGenHelper::genDao(entityName)»
					«val daoImplPackage = coral.getDaoImplPackage»
						<bean id="«daoImplClassName»" class="«daoImplPackage».«daoImplClassName»"></bean>
				«ENDFOR»
			«ENDFOR»
		</beans>
		
	'''
}