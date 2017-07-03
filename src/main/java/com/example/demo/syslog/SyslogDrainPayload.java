package com.example.demo.syslog;

import java.util.Map;

import org.springframework.integration.syslog.RFC5424SyslogParser;
import org.springframework.integration.syslog.SyslogHeaders;

public class SyslogDrainPayload {
	private final RFC5424SyslogParser parser = new RFC5424SyslogParser();
	final Map<String, ?> payload;

	public SyslogDrainPayload(String log) {
		this.payload = parser.parse(log, 0, false);
	}

	public boolean isCrashed() {
		return isApi() && getMessage().startsWith("App instance exited");
	}

	public boolean isGotHealthy() {
		return isCell() && getMessage().startsWith("Container became healthy");
	}

	public String getOrgSpaceApp() {
		return (String) this.payload.get(SyslogHeaders.HOST);
	}

	public String getMessage() {
		return (String) this.payload.get(SyslogHeaders.MESSAGE);
	}

	public boolean isApi() {
		return this.determineProcId("[API]");
	}

	public boolean isCell() {
		return this.determineProcId("[CELL]");
	}

	public boolean isRouter() {
		return this.determineProcId("[RTR]");
	}

	public boolean isWebApp() {
		return this.determineProcId("[APP/PROC/WEB]");
	}

	public boolean isSsh() {
		return this.determineProcId("[SSH]");
	}

	private boolean determineProcId(String procId) {
		return procId.equals(payload.get(SyslogHeaders.PROCID));
	}
}
