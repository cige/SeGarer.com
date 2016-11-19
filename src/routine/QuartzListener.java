package routine;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzListener
implements ServletContextListener{

	private final static boolean BOTS = true;
	private final static boolean PURGE = true;

	Scheduler scheduler = null;

	//Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Context Initialized");


		try {

			JobDetail purgeJob = JobBuilder.newJob(PurgeSpotsJob.class).withIdentity(
					"PurgeJob", "Group").build();

			JobDetail createSpotJob = JobBuilder.newJob(CreateSpotsJob.class).withIdentity(
					"CreateJob", "Group").build();

			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("trigger1", "group1")
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(30)
							.repeatForever())            
							.build();

			Trigger trigger2 = TriggerBuilder.newTrigger()
					.withIdentity("trigger2", "group2")
					.startNow()
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInMilliseconds(200)
							.repeatForever())            
							.build();


			// Setup the Job and Trigger with Scheduler & schedule jobs
			scheduler = new StdSchedulerFactory().getScheduler();
			

			if(PURGE)
				scheduler.scheduleJob(purgeJob, trigger);

			if(BOTS)
				scheduler.scheduleJob(createSpotJob, trigger2);
			
			scheduler.start();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			scheduler.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}