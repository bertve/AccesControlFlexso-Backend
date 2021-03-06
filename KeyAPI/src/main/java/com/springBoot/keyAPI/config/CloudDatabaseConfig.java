package com.springBoot.keyAPI.config;

import io.pivotal.cfenv.core.CfCredentials;
import io.pivotal.cfenv.jdbc.CfJdbcEnv;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;


@Configuration
@Profile("cloud")
public class CloudDatabaseConfig extends AbstractCloudConfig {

	@Bean
	@Primary
	@Profile("cloud")
	public DataSourceProperties dataSourceProperties() {
		CfJdbcEnv cfJdbcEnv = new CfJdbcEnv();
		DataSourceProperties properties = new DataSourceProperties();
		CfCredentials hanaCredentials = cfJdbcEnv.findCredentialsByTag("hana");

		if (hanaCredentials != null) {

			String uri = hanaCredentials.getUri("hana");
			properties.setUrl(uri);
			properties.setUsername(hanaCredentials.getUsername());
			properties.setPassword(hanaCredentials.getPassword());
		}

		return properties;
	}
	
}
