// package it.giochisulnostrotavolo.listone.quartz;
// import static org.quartz.JobBuilder.newJob;
// import static org.quartz.TriggerBuilder.newTrigger;

// import org.quartz.JobDetail;
// import org.quartz.JobKey;
// import org.quartz.SimpleScheduleBuilder;
// import org.quartz.Trigger;
// import org.quartz.TriggerKey;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import it.giochisulnostrotavolo.listone.service.ConstantService;
// import it.giochisulnostrotavolo.listone.util.StringUtil;

// @Configuration
// public class UpdateGlobalJobDetailTrigger {

//     Logger logger = LoggerFactory.getLogger(getClass());
    
//     @Autowired
// 	private ConstantService constantService;

//     @Bean("updateGlobalJobDetail")
//     public JobDetail updateGloablJobDetail() {
//         return newJob().ofType(UpdateGlobalJob.class).storeDurably().withIdentity(JobKey.jobKey("UpdateGlobal_Job_Detail")).withDescription("UpdateGlobal Job Detail").build();
//     }

//     @Bean("updateGlobalTrigger")
//     public Trigger updateGlobalTrigger(@Qualifier("updateGlobalJobDetail") JobDetail job) {
    	
//     	String interval = constantService.getConstantValueById("interval_job");
//     	if(StringUtil.isvoid(interval)) interval = "5";
//     	logger.debug("Configuring trigger to fire every {} mintes", interval);

//         return newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey("UpdateGlobal_Trigger")).withDescription("UpdateGlobal trigger")
//         		.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(Integer.valueOf(interval)).repeatForever()).startNow().build();
//     }
// }