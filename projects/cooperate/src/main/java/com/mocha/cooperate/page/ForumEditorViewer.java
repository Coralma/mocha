/**
 * 
 */
package com.mocha.cooperate.page;

import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.component.Toolbar;
import com.coral.vaadin.widget.view.CommonViewer;
import com.mocha.cooperate.SystemProperty;
import com.mocha.cooperate.page.event.ForumEditorListener;
import com.mocha.cooperate.widget.ForumEditor;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 */
public class ForumEditorViewer extends CommonViewer implements Viewer, ClickListener {
	
	private ForumEditorListener listener;
	private ForumEditor forumEditor;
	private String topicCategory;
	private String categoryTitle;
	
	public ForumEditorViewer(String topicCategory) {
		this.topicCategory = topicCategory;
		this.categoryTitle = SystemProperty.getForumCategoryTitle(topicCategory);
//		super.changeTitle("New" + categoryTitle);
		
	}

	public void attach() {
		super.attach();
		Toolbar toolbar = new Toolbar(message);
		toolbar.setWidth("768px");
		toolbar.addLeftLink("cooperate.forum.backTopics", "back", this);
		this.addComponent(toolbar);
		
		this.setWidth(SystemProperty.content_widget_width);
		forumEditor = new ForumEditor(topicCategory, categoryTitle);
		this.addComponent(forumEditor);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		listener.cancelAndBack();
	}

	@Override
	public String getViewerTitle() {
//		return "cooperate.forum.NewPost";
		return "cooperate.forum.New" + categoryTitle;
	}

	/**
	 * @return the listener
	 */
	public ForumEditorListener getListener() {
		return listener;
	}

	/**
	 * @param listener the listener to set
	 */
	public void setListener(ForumEditorListener listener) {
		this.listener = listener;
		forumEditor.setListener(listener);
	}
}
