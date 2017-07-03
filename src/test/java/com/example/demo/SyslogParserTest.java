package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Map;

import org.junit.Test;
import org.springframework.integration.syslog.RFC5424SyslogParser;
import org.springframework.integration.syslog.SyslogHeaders;

public class SyslogParserTest {
	RFC5424SyslogParser parser = new RFC5424SyslogParser();

	@Test
	public void testParse() throws Exception {
		String log = "<14>1 2017-04-17T15:52:02.863373+00:00 pivot-tmaki.blog.blog-ui-dev 019e1851-8026-4b27-91d0-a304b267105c [RTR] - - blog-ui-dev.cfapps.pez.pivotal.io - [2017-04-17T15:52:02.845+0000] \"GET /favicon-96x96.png HTTP/1.1\" 200 0 9155 \"https://blog-ui-dev.cfapps.pez.pivotal.io/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36\" \"192.168.8.1:12448\" \"192.168.9.3:60006\" x_forwarded_for:\"202.241.169.198\" x_forwarded_proto:\"https\" vcap_request_id:\"2000749e-7661-4644-6481-c7455559cf52\" response_time:0.017560491 app_id:\"019e1851-8026-4b27-91d0-a304b267105c\" app_index:\"0\" x_b3_traceid:\"a485c9ddb4c7ed20\" x_b3_spanid:\"a485c9ddb4c7ed20\" x_b3_parentspanid:\"-\"";
		Map<String, ?> message = parser.parse(log, 0, false);
		assertThat(message.get(SyslogHeaders.FACILITY)).isEqualTo(1);
		assertThat(message.get(SyslogHeaders.SEVERITY)).isEqualTo(6);
		assertThat(message.get(SyslogHeaders.SEVERITY_TEXT)).isEqualTo("INFO");
		assertThat(message.get(SyslogHeaders.TIMESTAMP))
				.isEqualTo("2017-04-17T15:52:02.863373+00:00");
		assertThat(message.get(SyslogHeaders.HOST))
				.isEqualTo("pivot-tmaki.blog.blog-ui-dev");
		assertThat(message.get(SyslogHeaders.APP_NAME))
				.isEqualTo("019e1851-8026-4b27-91d0-a304b267105c");
		assertThat(message.get(SyslogHeaders.PROCID)).isEqualTo("[RTR]");
		assertThat(message.get(SyslogHeaders.MSGID)).isEqualTo("-");
		assertThat(message.get(SyslogHeaders.VERSION)).isEqualTo(1);
		assertThat(message.get(SyslogHeaders.MESSAGE)).isEqualTo(
				"blog-ui-dev.cfapps.pez.pivotal.io - [2017-04-17T15:52:02.845+0000] \"GET /favicon-96x96.png HTTP/1.1\" 200 0 9155 \"https://blog-ui-dev.cfapps.pez.pivotal.io/\" \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36\" \"192.168.8.1:12448\" \"192.168.9.3:60006\" x_forwarded_for:\"202.241.169.198\" x_forwarded_proto:\"https\" vcap_request_id:\"2000749e-7661-4644-6481-c7455559cf52\" response_time:0.017560491 app_id:\"019e1851-8026-4b27-91d0-a304b267105c\" app_index:\"0\" x_b3_traceid:\"a485c9ddb4c7ed20\" x_b3_spanid:\"a485c9ddb4c7ed20\" x_b3_parentspanid:\"-\"");
		assertThat(message.get(SyslogHeaders.DECODE_ERRORS)).isEqualTo("false");

		System.out.println(
				OffsetDateTime.parse(message.get(SyslogHeaders.TIMESTAMP).toString())
						.atZoneSameInstant(ZoneId.of("Asia/Tokyo")).toLocalDateTime());
	}
}
