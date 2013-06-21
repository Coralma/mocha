package com.mocha.mobile.ui;
import com.coral.foundation.md.model.MobileMenu;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;
import com.vaadin.ui.Window.ResizeEvent;
import com.vaadin.ui.Window.ResizeListener;

public class PadApplication extends HorizontalLayout implements PageView, ResizeListener, ClickListener {

    private static final long serialVersionUID = 1L;
    private NavigateMenuManager controlMenuView = new NavigateMenuManager();
    private MobileViewManager functionView = new MobileViewManager();
    private Button showControllerButton;
    private boolean lastOrientationHorizontal;

    public PadApplication() {
        setSizeFull();
        addStyleName("tablet");
    }

    @Override
    public void attach() {
        super.attach();
        setOrientation();
        getWindow().addListener(this);
    }

    private void setOrientation() {
        removeAllComponents();
        if (isHorizontal()) {
            if (controlMenuView.getParent() != null) {
                Component parent2 = controlMenuView.getParent();
                if (parent2 instanceof Window) {
                    Window window = (Window) parent2;
                    window.setContent(null);
                    if (window.getParent() != null) {
                        window.getParent().removeWindow(window);
                    }
                }
            }
            addComponent(controlMenuView);
            addComponent(functionView);
            setExpandRatio(functionView, 1);
        } else {
            showControllerButton = new Button();
            showControllerButton.addListener(this);
            addComponent(functionView);
            showControllerButton.setCaption(controlMenuView
                    .getCurrentComponent().getCaption());
            HorizontalLayout hLayout = new HorizontalLayout();
            hLayout.setSpacing(true);
            hLayout.setHeight("30px");
            hLayout.addComponent(showControllerButton);
//            functionView.setLeftComponent(hLayout);
        }

        lastOrientationHorizontal = isHorizontal();
    }

    private boolean isHorizontal() {
        return getApplication().getMainWindow().getWidth() > getApplication()
                .getMainWindow().getHeight();
    }

    public void windowResized(ResizeEvent e) {
        if (getApplication() != null) {
            if (isHorizontal() != lastOrientationHorizontal) {
                setOrientation();
            }
        }
    }

    public void buttonClick(ClickEvent event) {
        if (event.getButton() == showControllerButton) {
            Popover popover = new Popover();
            Component parent2 = controlMenuView.getParent();
            if (parent2 != null && parent2 instanceof Popover) {
                ((Popover) parent2).setContent(null);
            }
            popover.setContent(controlMenuView);
            popover.setClosable(true);
            popover.showRelativeTo(showControllerButton);
            popover.setHeight(getParent().getHeight() - 100, UNITS_PIXELS);
            popover.addListener(new CloseListener() {
                private static final long serialVersionUID = 1L;

                public void windowClose(CloseEvent e) {
                    setEnabled(true);
                }
            });
        }

    }

    @Override
    public void navigatePage(MobileMenu message) {
        functionView.navigatePage(message);
    }
}
