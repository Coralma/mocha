package com.mocha.cooperate.restfulservice;

import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.ToDo;
import com.mocha.service.model.DiscussWSElement;
import com.mocha.service.model.ToDoWSElement;

public class UserFeedAdapter extends ServiceDomainAdapter<ToDoWSElement, ToDo> {

	// @Override
	public ToDoWSElement marshalToDo(ToDo v) throws Exception {
		System.out.println("start to marshall the ToDo object");
		ToDoWSElement todows = new ToDoWSElement();
		todows.setCreateTime(v.getCreateTime());
		todows.setName(v.getName());
		return todows;
	}

	@Override
	public Object unmarshal(Object paramValueType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object marshal(Object paramBoundType) throws Exception {
		if (paramBoundType instanceof ToDo) {
			return marshalToDo((ToDo) paramBoundType);
		}
		else if (paramBoundType instanceof Discuss) {
			return marshalDiscuss((Discuss) paramBoundType);
		}
		return null;
	}

	private DiscussWSElement marshalDiscuss(Discuss discuss) {
		System.out.println("start to marshall the Discuss object");
		DiscussWSElement discussWs = new DiscussWSElement();
		// discussWs.setCreator(discuss.getCreator());
		discussWs.setContent("test");
		return discussWs;
	}
}
