package com.example.demo.syslog;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.event.BecameHealthyEvent;
import com.example.demo.event.CrashEvent;

@RestController
public class SyslogDrainController {
	private final ApplicationEventPublisher publisher;

	public SyslogDrainController(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@PostMapping(path = "/logs")
	public void logs(@RequestBody String log) {
		SyslogDrainPayload payload = new SyslogDrainPayload(log);
		if (payload.isCrashed()) {
			publisher.publishEvent(new CrashEvent(payload));
		}
		if (payload.isGotHealthy()) {
			publisher.publishEvent(new BecameHealthyEvent(payload));
		}
	}

}
