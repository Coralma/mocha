/**
 * 
 */
package com.mocha.cooperate.help;

import com.vaadin.terminal.ThemeResource;

/**
 * @author Coral
 *
 */
public class FileGuide extends AbstractGuide implements IGuidePanel {

	public FileGuide() {
		super();
	}
	
	public void build() {
		this.addComponent(createTitle("Private File and Company File"));
		this.addComponent(createHelpText("The File function support user to manage and share their files in Mocha Platform. Only admin can access to company file page to maintain the whole company's attached files. The company attache file is available for everyone. "));
		this.addComponent(createHelpImage(new ThemeResource("helps/gs-file.jpg")));
	}
}
