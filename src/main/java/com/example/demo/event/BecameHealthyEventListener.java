package com.example.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BecameHealthyEventListener {
	@EventListener
	public void handleCrashEvent(BecameHealthyEvent event) {
		System.err.println(Thread.currentThread().getName() + " Became healthy ("
				+ event.getPayload().getOrgSpaceApp() + ") " + event.getTimestamp());
	}
}
