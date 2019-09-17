package com.flyway.flywaySPB;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FlywayProperties {
	@Value("${spring.flyway.schemas}")
	private String[] schemas;
	@Value("${spring.flyway.url}")
	private String url;
	@Value("${spring.flyway.user}")
	private String user;
	@Value("${spring.flyway.password}")
	private String password;
	@Value("${spring.flyway.locations}")
	private String[] locations;
	@Value("${spring.flyway.outoforder}")
	private boolean outOfOrder;
	@Value("${spring.flyway.target}")
	private String target;
	@Value("${spring.flyway.installedby}")
	private String installedBy;
	@Value("${spring.flyway.connectretries}")
	private int connectRetries;
	@Value("${spring.flyway.encoding}")
	private Charset encoding = StandardCharsets.UTF_8;
	public String[] getSchemas() {
		return this.schemas;
	}

	public String[] getLocations() {
		return locations;
	}

	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	public void setSchemas(String[] schemas) {
		this.schemas = schemas;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return (this.password != null) ? this.password : "";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isOutOfOrder() {
		return outOfOrder;
	}

	public void setOutOfOrder(boolean outOfOrder) {
		this.outOfOrder = outOfOrder;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {

		this.target = target;
	}

	public String getInstalledBy() {
		return installedBy;
	}

	public void setInstalledBy(String installedBy) {
		this.installedBy = installedBy;
	}

	public int getConnectRetries() {
		return connectRetries;
	}

	public void setConnectRetries(int connectRetries) {
		this.connectRetries = connectRetries;
	}

	public Charset getEncoding() {
		return encoding;
	}

	public void setEncoding(Charset encoding) {
		this.encoding = encoding;
	}

}
