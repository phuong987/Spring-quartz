package com.phuongo.config;

import java.util.Properties;

import javax.sql.DataSource;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {

	private final DataSource dataSource;

	private final ApplicationContext applicationContext;

	private final QuartzProperties quartzProperties;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {

		SchedulerJobFactory jobFactory = new SchedulerJobFactory();
		jobFactory.setApplicationContext(applicationContext);


		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setOverwriteExistingJobs(true);
		factory.setDataSource(dataSource);
		Properties properties = new Properties();
		properties.putAll(quartzProperties.getProperties());

		factory.setQuartzProperties(properties);
		factory.setJobFactory(jobFactory);
		return factory;
	}
}
