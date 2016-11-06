package routine;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;

public class PurgeTooOldSpotJob implements Job {
	
	private Scheduler scheduler = null;

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("Purge des places automatique!");

	}

}
