/**
 * 
 */
package com.coral.vaadin.widget.validate;

import java.util.List;

import com.coral.foundation.hibernate.impl.GenericDAO;
import com.coral.foundation.hibernate.impl.GenericHibernateDAO;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.Field;

/**
 * @author Coral
 *
 */
public class ReferenceValidation extends AbstractValidator implements Validator {

	public String hql;
	
	public ReferenceValidation(Field field, String entityName, String property) {
		super(field);
		this.hql = "from " + entityName + " where " + property;
	}

	public boolean validate(String status) {
//		String input = field.getText();
//		if(!StrUtils.isEmpty(input)) {
//			GenericDAO dao = new GenericHibernateDAO();
//			String query = hql + "='"+ input +"'";
//			List rs = dao.find(query);
//			if(rs.size() == 0) {
//				MessageCenter.getMessageCenter().alertMessage("系统中没有您输入的\""+field.getLabel()+"\"信息,请查询已存在数据.").open();
//				field.setError(true);
//				return false;
//			} else {
//				field.setError(false);
//				return true;
//			}
//		}
		return true;
	}

	
}
