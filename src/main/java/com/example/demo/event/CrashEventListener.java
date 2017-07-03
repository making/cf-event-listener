package com.example.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.demo.syslog.SyslogDrainPayload;

@Component
public class CrashEventListener {
	@EventListener
	public void handleCrashEvent(CrashEvent event) {
		SyslogDrainPayload payload = event.getPayload();
		System.err.println(Thread.currentThread().getName() + " Crashed!! ("
				+ payload.getOrgSpaceApp() + ") " + event.getTimestamp() + " "
				+ payload.getMessage().trim());
	}
}
