package com.homepage.mail.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.homepage.mail.GmailSender;

public class MailQueueConsumer implements Runnable {

	public BlockingQueue<String> mailQueue;

	public MailQueueConsumer() {
		if (mailQueue == null) {
			this.mailQueue=MailQueueProvider.getMailQueue();
		}
	}

	public MailQueueConsumer(BlockingQueue<String> mailQueue) {
		this.mailQueue = mailQueue;
	}

	@Override
	public void run() {
		while (true) {
//			System.out.println(mailQueue.size());
			if (MailQueueProvider.getMailQueue().size() > 0) {
				// GmailSender.send(mailQueue.take());
				try {
					GmailSender.send(MailQueueProvider.getMailQueue().take());
					System.out.println("I've got the mail queue input now :"+mailQueue.take());
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
