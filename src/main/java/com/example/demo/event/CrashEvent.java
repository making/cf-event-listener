package com.example.demo.event;

import com.example.demo.syslog.SyslogDrainPayload;

import java.time.Instant;

public class CrashEvent {
	private final SyslogDrainPayload payload;
	private final Instant timestamp;

	public CrashEvent(SyslogDrainPayload payload) {
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
