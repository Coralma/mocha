package com.mocha.cooperate.restfulservice;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.mocha.cooperate.model.ToDo;
import com.mocha.service.model.ToDoWSElement;

public class ToDoAdapter extends XmlAdapter<ToDoWSElement, ToDo> {

	@Override
	public ToDoWSElement marshal(ToDo v) throws Exception {
		System.out.println("start to marshall the ToDo object");
		ToDoWSElement todows = new ToDoWSElement();
		todows.setCreateTime(v.getCreateTime());
		todows.setName(v.getName());
		return todows;
	}

	@Override
	public ToDo unmarshal(ToDoWSElement v) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
