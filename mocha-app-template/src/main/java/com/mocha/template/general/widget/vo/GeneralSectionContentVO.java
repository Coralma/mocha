package com.mocha.template.general.widget.vo;

import java.util.List;

import com.google.common.collect.Lists;

public class GeneralSectionContentVO {

	public String sectionLabel;
	public List<GeneralSectionMessageVO> messages = Lists.newArrayList();

	/** the type for section content. Now the 1 means link message content. */
	public int type = 1; 

	/**
	 * @return the sectionLabel
	 */
	public String getSectionLabel() {
		return sectionLabel;
	}
	/**
	 * @param sectionLabel the sectionLabel to set
	 */
	public void setSectionLabel(String sectionLabel) {
		this.sectionLabel = sectionLabel;
	}
	/**
	 * @return the messages
	 */
	public List<GeneralSectionMessageVO> getMessages() {
		return messages;
	}
	/**
	 * @param messages the messages to set
	 */
	public void setMessages(List<GeneralSectionMessageVO> messages) {
		this.messages = messages;
	}
	
	public void addMessage(GeneralSectionMessageVO message) {
		messages.add(message);
	}
	
}
