// package it.giochisulnostrotavolo.listone.quartz;

// import org.quartz.Job;
// import org.quartz.JobExecutionContext;
// import org.quartz.JobExecutionException;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;

// import it.giochisulnostrotavolo.listone.service.ToolsService;

// @Component
// public class UpdateGlobalJob implements Job {

// 	Logger logger = LoggerFactory.getLogger(getClass());

// 	@Autowired
// 	ToolsService toolsService;



// 	public void execute(JobExecutionContext context) throws JobExecutionException {
// 		logger.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
		
// 		toolsService.checkOrders();
		
// 		toolsService.checkReservations();
		
// 		logger.info("Next job scheduled @ {}", context.getNextFireTime());
// 	}
// }