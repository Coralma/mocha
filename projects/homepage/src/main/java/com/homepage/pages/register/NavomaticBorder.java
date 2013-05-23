package com.homepage.pages.register;

import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BoxBorder;

public class NavomaticBorder extends Border {

	public NavomaticBorder(final String componentName) {
        super(componentName);

        addToBorder(new BoxBorder("navigationBorder"));
        addToBorder(new BoxBorder("bodyBorder"));
    }
}
