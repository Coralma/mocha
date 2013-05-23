package com.homepage.mail.task;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MailQueueProvider {
	private static BlockingQueue<String> mailQueue = new LinkedBlockingQueue<String>();

	public MailQueueProvider() {

	}
	
	public static MailQueueProvider getInstance(){
		return new MailQueueProvider();
	}

	public static BlockingQueue<String> getMailQueue() {
		return mailQueue;
	}

	public static void setMailQueue(BlockingQueue<String> mailQueue) {
		MailQueueProvider.mailQueue = mailQueue;
	}

}
