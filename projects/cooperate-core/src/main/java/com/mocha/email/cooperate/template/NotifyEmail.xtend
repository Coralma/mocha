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
			«sender.realName» post a new Status notify you
		«ELSEIF timeLine.discuss != null»
			«sender.realName» post a new Topic notify you
		«ELSEIF timeLine.todo != null»
			«sender.realName» post a new Todo notify you
		«ENDIF»
	'''
	
	def getEmailContent() '''
		<div style="font-size: 12px; font-family: Arial; color: #52555A">
		Dear «receiver.realName»<br/>
		«IF timeLine.status != null»
			«sender.realName» post a new Status notify you. The status content is:<br/>
			<div style="word-wrap: break-word;word-break: normal;white-space : normal; font-weight: bold;">
			«timeLine.status.content»
			</div>
		«ELSEIF timeLine.discuss != null»
			«sender.realName» post a new Topic notify you. The topic content is:<br/>
			<div style="font-size: 14px; font-family: Arial; color: #52555A; font-weight: bold;">
			«timeLine.discuss.title»
			</div>
			<div style="word-wrap: break-word;word-break: normal;white-space : normal; font-size: 12px; font-family: Arial; color: #52555A">
			«timeLine.discuss.content»
			</div>
		«ELSEIF timeLine.todo != null»
			«sender.realName» post a new Todo notify you. The task list is:<br/>
			<div style="font-size: 14px; font-family: Arial; color: #000000; font-weight: bold;word-wrap: break-word;word-break: normal;">
			«timeLine.todo.name» - «timeLine.todo.assginedUser.realName»
			</div>
			<div style="font-size: 12px; font-family: Arial; color: #52555A; font-weight: bold; word-wrap: break-word;word-break: normal;">
			«FOR task : timeLine.todo.subToDoItems»
				«task.content» - «task.assginedUser.realName»<br/>
			«ENDFOR»
			</div>
		«ENDIF»
		<br/>
		Please login in your Mocha account and check your notification <a href="www.mocha-platform.com/cooperate/">www.mocha-platform.com/cooperate</a><br/>
		Best Regards,<br/>
		Mocha Platform Team.<br/>
		<br/>
		<div style="font-size: 10px; color: #A4A4A4">
		Please do not reply to this email. This mailbox is not monitored and you will not receive a response.
		</div>
		</div>
	'''
}