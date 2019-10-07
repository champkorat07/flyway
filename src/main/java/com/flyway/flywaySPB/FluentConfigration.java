package com.flyway.flywaySPB;

import javax.annotation.PostConstruct;

import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FluentConfigration extends FluentConfiguration {

	@Autowired
	private FlywayProperties flywayProperties;

	@PostConstruct
	public void init() {
		this.dataSource(flywayProperties.getUrl(), flywayProperties.getUser(), flywayProperties.getPassword());
		this.locations(flywayProperties.getLocations());
		this.schemas(flywayProperties.getSchemas());
		this.outOfOrder(flywayProperties.isOutOfOrder());

		if (!flywayProperties.getTarget().isEmpty())
			this.target(flywayProperties.getTarget());

		this.installedBy(flywayProperties.getInstalledBy());
		this.connectRetries(flywayProperties.getConnectRetries());
		this.encoding(flywayProperties.getEncoding());
	}

}
