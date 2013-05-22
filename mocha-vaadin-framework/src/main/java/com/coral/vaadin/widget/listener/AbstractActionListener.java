/**
 * 
 */
package com.coral.vaadin.widget.listener;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button.ClickEvent;

/**
 * @author Administrator
 *
 */
public class AbstractActionListener {
	
	public Map<String, Object> data = new HashMap<String, Object>();

	public void buttonClick(ClickEvent event) {
		String methodName = (String)event.getButton().getData();
		invoke(methodName);
		System.out.println("buttonClick " + event + " in entity " + this.getClass());
	}

	public void valueChange(ValueChangeEvent event) {
//		String methodName = (String)event.getButton().getData();
//		invoke(methodName);
		System.out.println("Value " + event + " in entity " + this.getClass());		
	}
	
	public void invoke(String methodName) {
		try {
			Method method = this.getClass().getDeclaredMethod(methodName);
			method.invoke(this);
		} catch (Exception e) {
			System.out.println("[Error!!!] Invoke " + methodName + " error in entity " + this.getClass());
			e.printStackTrace();
		}
	}
	
	public void put(String key, Object value) {
		data.put(key, value);
	}
	
	public Object get(String key) {
		return data.get(key);
	}
}
