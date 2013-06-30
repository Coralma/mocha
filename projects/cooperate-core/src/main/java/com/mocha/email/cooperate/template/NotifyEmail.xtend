package com.mocha.email.cooperate.template

import com.coral.foundation.security.model.BasicUser
import com.mocha.cooperate.model.TimeLine

class NotifyEmail {
	
	BasicUser sender
	TimeLine timeLine
	BasicUser receiver
	
	def init(BasicUser sender, TimeLine timeLine, BasicUser receiver) {
		this.sender = sender;
		this.timeLine = timeLine;
		this.receiver = receiver; 
	}
	
	def getSubject() '''
		«IF timeLine.status != null»
			«sender.realName» create a new Status notify you
		«ELSEIF timeLine.discuss != null»
			«sender.realName» create a new Topic notify you
		«ELSEIF timeLine.todo != null»
			«sender.realName» create a new Todo notify you
		«ENDIF»
	'''
	
	def getEmailContent() '''
		<div style="font-size: 12px; font-family: Arial; color: #52555A">
		Dear «receiver.realName»<br/><br/>
		«getSubject()»
		<br/>
		Please login in your Mocha account and check your notification <a href="www.mocha-platform.com/cooperate/">www.mocha-platform.com/cooperate</a><br/>

		Best Regards,<br/>
		Mocha Platform team.<br/>
		<br/>
		<div style="font-size: 10px; color: #A4A4A4">
		Please do not reply to this email. This mailbox is not monitored and you will not receive a response.
		</div>
		</div>
	'''
}