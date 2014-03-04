package com.coral.vaadin.widget.view;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.BrowserInfo;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VRefresher extends Widget implements Paintable {

	public static final String TAGNAME = "refresher";

	private static final int STOP_THRESHOLD = 0;

	public static final String VARIABLE_REFRESH_EVENT = "r";

	private ApplicationConnection client;
	private final Poller poller;
	private boolean pollerSuspendedDueDetach;

	private int pollingInterval;

	public VRefresher() {
		setElement(Document.get().createDivElement());
		if (BrowserInfo.get().isIE6()) {
			getElement().getStyle().setProperty("overflow", "hidden");
			getElement().getStyle().setProperty("height", "0");
		}
		poller = new Poller();
	}

	public void updateFromUIDL(final UIDL uidl, final ApplicationConnection client) {
		this.client = client;
		final boolean cached = uidl.getBooleanAttribute("cached");
		if (!cached) {
			poller.cancel();
		}
		if (client.updateComponent(this, uidl, true)) {
			return;
		}

		pollingInterval = uidl.getIntAttribute("pollinginterval");
		if (pollingInterval > STOP_THRESHOLD && !uidl.getBooleanAttribute("disabled")) {
			poller.scheduleRepeating(pollingInterval);
		}
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (pollerSuspendedDueDetach) {
			poller.scheduleRepeating(pollingInterval);
		}
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		if (pollingInterval > STOP_THRESHOLD) {
			poller.cancel();
			pollerSuspendedDueDetach = true;
		}
	}

	class Poller extends Timer {
		@Override
		public void run() {
			// Just send something, to trigger the server side event.
			client.updateVariable(client.getPid(getElement()), VARIABLE_REFRESH_EVENT, 0, false);
			client.sendPendingVariableChanges();
		}
	}
}