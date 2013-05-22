/**
 * 
 */
package com.mocha.cooperate.page.event;

import java.util.List;
import java.util.Set;

import com.coral.foundation.security.model.BasicUser;
import com.mocha.cooperate.model.Attachment;
import com.mocha.cooperate.model.Discuss;

/**
 * @author Coral
 *
 */
public interface ForumEditorListener {

	public void saveTopic(Discuss discuss, BasicUser creator, Set<BasicUser> notifiedUsers, List<Attachment> attachments);
	public void cancelAndBack();
}
