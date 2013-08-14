package com.coral.foundation.jpa.gen

import com.coral.foundation.md.model.Entity
import com.coral.foundation.utils.StrUtils
import com.coral.foundation.md.model.Mocha

class JPAEntityTemplate {
	
	Mocha coral
	Entity entity
	String packageName
	String entityName
	String entityIdField
	
	def init(Mocha coral,Entity entity) {
		this.coral = coral;
		this.entity = entity;
  		this.packageName = coral.getEntityPackage;
  		this.entityName = entity.entityName; 
  		this.entityIdField = StrUtils::lowCaseFirstLetter(entityName) + "Id";
  	}
  	
	def generate() '''
«««		«val packageName = coral.getEntityPackage»
«««		«val entityName = entity.entityName»
		package «packageName»;
		import java.util.*;
		import java.math.BigDecimal;
		import javax.persistence.*;
		import com.coral.foundation.persistence.*;
		
		/**
		  * <p>Title: «packageName».«entityName» + "</p>
		  * <p>Description: The «entityName» entity </p>
		  */
		«getEntityAnnotation(entityName,entity.getTableName)»
		public class «entityName» extends JPABaseEntity {
			
«««			generate entity ID
«««			«val entityIdField = StrUtils::lowCaseFirstLetter(entityName) + "Id"»
			«getIdAnnotation(entityIdField)»
			private Long «entityIdField»;
			
«««		generate entity veriable
			«generatePropertyAnnotation(entity)»
		
«««		generate getter & setter
			«generateNormalGetterSetter("Long", entityIdField)»
			«generateEntityMethod(entity)»
		
			public Long getID() {
				return «StrUtils::getter(entityIdField)»();
			}
		}
	'''
	def generatePropertyAnnotation(Entity entity) '''
		«FOR m : entity.properties»
			«val type = m.getType»
			«val length = m.length»
			«val ref = m.getRef»
			«val refer = m.isRefer»
			«val mappedBy = m.getMappedBy»
			«val orm = m.getOrm»
			«val columnName = m.getColumnName»
			«val propertyName = m.getPropertyName»
			«IF type.equals("Date")»
				«getDateAnnotation(propertyName, columnName)»
				private «type» «propertyName»;
«««					private «type» «propertyName» = new Date();
			«ELSEIF "one-to-many".equals(orm)»
				«getOneToManyAnnotation(ref)»
				«IF type.equals("List")»
					private List<«ref»> «propertyName» = new ArrayList<«ref»>();
				«ELSE»
					private «type»<«ref»> «propertyName»;
				«ENDIF»
			«ELSEIF "one-to-one".equals(orm)»
				«getOneToOneAnnotation(ref,mappedBy,refer)»
				private «ref» «propertyName»;
			«ELSEIF "many-to-one".equals(orm)»
				«getManyToOneAnnotation(ref,propertyName)»
				private «ref» «propertyName»;
			«ELSEIF m.isIgnore»
				«getTransientAnnotation»
«««				private «type» «propertyName»;
				«getVariableDefinition(type, propertyName, m.getDefaultValue)»
			«ELSE»
				«getCommonAnnotation(propertyName, columnName, length)»
«««				private «type» «propertyName»;
				«getVariableDefinition(type, propertyName, m.getDefaultValue)»
			«ENDIF»
			
		«ENDFOR»
	'''
	def generateEntityMethod(Entity entity) '''
		 «FOR m : entity.getProperties»
			«val type = m.getType»
			«val ref = m.getRef»
			«val propertyName = m.getPropertyName»
			«IF type.equals("List")»
					«generateListGetterSetter(type,ref,propertyName)»
			«ELSE»
					«generateNormalGetterSetter(type,propertyName)»
			«ENDIF»
		«ENDFOR»
	'''
	def generateNormalGetterSetter(String type, String propertyName) '''
		public void «StrUtils::setter(propertyName)» («type» «propertyName») {
			this.«propertyName» = «propertyName»;
		} 
		public «type» «StrUtils::getter(propertyName)» () {
			return «propertyName»;
		}
	'''
	def generateListGetterSetter(String type, String ref, String propertyName)'''
		public void «StrUtils::setter(propertyName)» («type»<«ref»> «propertyName») {
			this.«propertyName» = «propertyName»;
		} 
		public «type»<«ref»> «StrUtils::getter(propertyName)» () {
			return «propertyName»;
		}
	'''
	def getEntityAnnotation(String entityName, String tableName)'''
		@Entity(name = "«entityName»")
		«IF tableName != null»
			@Table(name = "«tableName»")
		«ELSE»
			@Table(name = "T_«StrUtils::genDBName(entityName)»")
		«ENDIF»
	'''
	def getIdAnnotation(String entityIdField)'''
		@Id()
		@Column (name = "«StrUtils::genDBName(entityIdField)»")
		«IF entity.isSpecifySeq»
			«val seqName = entityIdField.toUpperCase+ "_SEQ"»
			@GeneratedValue(generator="«seqName»")
			@TableGenerator(name="«seqName»", table="SEQUENCE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", allocationSize=1)
		«ELSE»
			@GeneratedValue(strategy=GenerationType.IDENTITY)
		«ENDIF»
	'''
	def getDateAnnotation(String fieldName,String columnName) '''
«««		@Column(name = "«StrUtils::genDBName(fieldName)»" )
		«getColumnAnnotation(fieldName,columnName, null)»
		@Temporal(TemporalType.DATE)
	'''
	def getCommonAnnotation(String fieldName,String columnName, String length) '''
		«getColumnAnnotation(fieldName,columnName,length)»
	'''
	def getColumnAnnotation(String fieldName, String columnName,String length) '''
		«IF columnName != null»
			@Column(name = "«columnName»" «getLengthParameter(length)»)
		«ELSE»
			@Column(name = "«StrUtils::genDBName(fieldName)»" «getLengthParameter(length)»)
		«ENDIF»
	'''
	def getLengthParameter(String length) {
		if(length == null) {
			return ""
		} else {
			return ",length = " + length;
		}
	}
	def getOneToOneAnnotation(String ref, String mappedBy, boolean refer)'''
		«IF refer»
			@OneToOne(targetEntity = «ref».class)
		«ELSE»
			@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = «ref».class)
		«ENDIF»
«««		«IF refer»
«««			«IF StrUtils::isEmpty(mappedBy)»
«««				@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, targetEntity = «ref».class, fetch=FetchType.EAGER)
«««			«ELSE»
«««				@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, mappedBy = "«mappedBy»", targetEntity = «ref».class, fetch=FetchType.EAGER)
«««			«ENDIF»
«««		«ELSE»
«««			«IF StrUtils::isEmpty(mappedBy)»
«««				@OneToOne(cascade = { CascadeType.ALL }, targetEntity = «ref».class, fetch=FetchType.EAGER)
«««			«ELSE»
«««				@OneToOne(cascade = { CascadeType.ALL }, mappedBy = "«mappedBy»", targetEntity = «ref».class, fetch=FetchType.EAGER)
«««			«ENDIF»
«««		«ENDIF»
«««		@Fetch(FetchMode.JOIN)
	'''
	def getOneToManyAnnotation(String ref)'''
		@OneToMany(targetEntity=«ref».class, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
		@JoinColumn(name="«StrUtils::genDBName(entityIdField)»")
	'''
	def getManyToOneAnnotation(String ref, String fieldName)'''
		@ManyToOne
		@JoinColumn(name="«fieldName»")
	'''
	def getTransientAnnotation()'''
		@Transient
	'''
	def getVariableDefinition(String type, String propertyName, String defaultValue)'''
		«IF defaultValue == null»
			private «type» «propertyName»;
		«ELSE»
			«IF "String".equals(type)»
				private «type» «propertyName» = "«defaultValue»";
			«ELSEIF "Long".equals(type)»
				private «type» «propertyName» = new Long(«defaultValue»);
			«ELSEIF "BigDecimal".equals(type)»
				private «type» «propertyName» = new BigDecimal("«defaultValue»");
			«ENDIF»
		«ENDIF»
		
	'''
}