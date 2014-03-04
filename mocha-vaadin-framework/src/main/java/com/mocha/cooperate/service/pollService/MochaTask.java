package com.mocha.cooperate.service.pollService;

import com.coral.foundation.security.model.BasicUser;

public class MochaTask implements Runnable {

	private BasicUser bu;

	private int num = 0;

	public MochaTask(BasicUser bu) {
		this.setBu(bu);
	}

	@Override
	public void run() {
		System.out.println("Sample Run " + num++);
	}

	public BasicUser getBu() {
		return bu;
	}

	public void setBu(BasicUser bu) {
		this.bu = bu;
	}

}
