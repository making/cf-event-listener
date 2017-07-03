package com.example.demo.event;

import java.time.Instant;

import com.example.demo.syslog.SyslogDrainPayload;

public class BecameHealthyEvent {
	private final SyslogDrainPayload payload;
	private final Instant timestamp;

	public BecameHealthyEvent(SyslogDrainPayload payload) {
		this.payload = payload;
		this.timestamp = Instant.now();
	}

	public SyslogDrainPayload getPayload() {
		return payload;
	}

	public Instant getTimestamp() {
		return timestamp;
	}
}
