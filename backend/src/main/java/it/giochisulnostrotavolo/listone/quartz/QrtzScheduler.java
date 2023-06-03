// package it.giochisulnostrotavolo.listone.quartz;
// import java.io.IOException;
// import java.util.Properties;

// import javax.annotation.Resource;
// import javax.sql.DataSource;

// import org.quartz.JobDetail;
// import org.quartz.Scheduler;
// import org.quartz.SchedulerException;
// import org.quartz.Trigger;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.scheduling.quartz.SchedulerFactoryBean;
// import org.springframework.scheduling.quartz.SpringBeanJobFactory;

// import it.giochisulnostrotavolo.listone.configuration.AutoWiringSpringBeanJobFactory;

// @Configuration
// public class QrtzScheduler {

//     Logger logger = LoggerFactory.getLogger(getClass());

//     @Autowired
//     private ApplicationContext applicationContext;
    
//     @Autowired
//     private QuartzProperties quartzProperties;
    
//     @Autowired
//     private DataSource dataSource;
    
//     @Resource
//     Trigger updateGridTrigger;
    
//     @Resource
//     JobDetail updateGridJobDetail;
    
//     @Value("${app.enableQuartz}")
//     Boolean enableQuartz;

//     @Bean
//     public SpringBeanJobFactory springBeanJobFactory() {
//         AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
//         logger.debug("Configuring Job factory");

//         jobFactory.setApplicationContext(applicationContext);
//         return jobFactory;
//     }

//     @Bean
//     public Scheduler scheduler(SchedulerFactoryBean factory) throws SchedulerException {
//         logger.debug("Getting a handle to the Scheduler");
//         Scheduler scheduler = factory.getScheduler();
//         scheduler.clear();
//         //se si vuole aggiungere un job, aggiungere una riga come le seguenti
//         if (enableQuartz) {
// 	        scheduler.scheduleJob(updateGridJobDetail, updateGridTrigger);
//         }

//         logger.debug("Starting Scheduler threads");
//         scheduler.start();
//         return scheduler;
//     }

//     @Bean
//     public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
//         SchedulerFactoryBean factory = new SchedulerFactoryBean();
//         factory.setJobFactory(springBeanJobFactory());
//         Properties properties = new Properties();
//         properties.putAll(quartzProperties.getProperties());
//         factory.setQuartzProperties(properties);
//         factory.setDataSource(dataSource);
//         return factory;
//     }

// }