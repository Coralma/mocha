package com.coral.vaadin.widget.layout;

import org.vaadin.jouni.animator.AnimatorProxy;
import org.vaadin.jouni.animator.client.ui.VAnimatorProxy.AnimType;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.BaseTheme;

@SuppressWarnings("serial")
public class Wrap extends VerticalLayout {

    public static final String STYLE = "v-disclosure";
    public static final String STYLE_CAPTION = STYLE + "-caption";
    public static final String STYLE_CAPTION_OPEN = STYLE_CAPTION + "-open";

    protected AnimatorProxy ap = new AnimatorProxy();
    protected Component content;
    protected Button caption = new Button();
    protected boolean open = false;

    public Wrap(String caption) {
        this.caption.setCaption(caption);
        setStyleName(STYLE);
        this.caption.addStyleName(BaseTheme.BUTTON_LINK);
        this.caption.addStyleName(STYLE_CAPTION);
        super.addComponent(ap);
        super.addComponent(this.caption);
        this.caption.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if (open) {
                    close();
                } else {
                    open();
                }
            }
        });
    }

    public Wrap(String caption, Component content) {
        this(caption);
        this.content = content;
    }

    public String getDisclosureCaption() {
        return caption.getCaption();
    }

    public void setDisclosureCaption(String caption) {
        this.caption.setCaption(caption);
    }

    public Wrap setContent(Component newContent) {
        if (content != newContent) {
            if (content != null && content.getParent() != null) {
                removeComponent(content);
            }
            if (open && newContent != null) {
                super.addComponent(newContent);
            }
            content = newContent;
        }
        return this;
    }

    public Component getContent() {
        return content;
    }

    public Wrap open() {
        if (content != null) {
            if (content.getParent() == null || content.getParent() != this) {
                super.addComponent(content);
            }
            ap.animate(content, AnimType.ROLL_DOWN_OPEN_POP);
            caption.addStyleName(STYLE_CAPTION_OPEN);
            open = true;
        }
        return this;
    }

    public boolean isOpen() {
        return open;
    }

    public Wrap close() {
        if (content != null) {
            ap.animate(content, AnimType.ROLL_UP_CLOSE_REMOVE);
            caption.removeStyleName(STYLE_CAPTION_OPEN);
            open = false;
        }
        return this;
    }

    @Override
    public void addComponent(Component c) {
        if (content == null) {
            setContent(c);
        } else {
            throw new UnsupportedOperationException(
                    "You can only add one component to the Disclosure. Use Disclosure.setContent() method instead.");
        }
    }

    @Override
    public void removeAllComponents() {
        setContent(null);
    }
}
