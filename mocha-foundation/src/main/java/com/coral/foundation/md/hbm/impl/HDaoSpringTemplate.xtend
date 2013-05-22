package com.coral.foundation.md.hbm.impl

import java.util.List
import com.coral.foundation.md.model.Mocha

class HDaoSpringTemplate {
	
	def generate(List<Mocha> coralList) '''
		<?xml version="1.0" encoding="ISO-8859-1"?>

		<beans xmlns="http://www.springframework.org/schema/beans"
		  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		  xmlns:context="http://www.springframework.org/schema/context"
		  xmlns:p="http://www.springframework.org/schema/p"
		  xsi:schemaLocation="
		   http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd
		   http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-2.5.xsd">
		«FOR coral : coralList»
			«val daoPkg = coral.getDaoImplPackage»
			«FOR entity : coral.getEntityList»
			«val daoName = entity.getEntityName+"DAO"»
			<bean id="«daoName»" class="«daoPkg».«daoName»"/>
			«ENDFOR»
		«ENDFOR»
		</beans>
	'''
	
}