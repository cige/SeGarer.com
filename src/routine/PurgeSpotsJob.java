package routine;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;

public class PurgeSpotsJob implements Job {
	
	private final static int TIME_THRESHOLD = 15;
	
	private Scheduler scheduler = null;

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("Purge des places automatique!");

	}

}
