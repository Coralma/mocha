package com.mocha.cooperate.mobile.phone.widgets.cards;

import com.coral.foundation.persistence.BaseEntity;

public interface PhoneCardListener {

	public void cardClick(BaseEntity entity);
	
	public void createBasic(String category);
}
