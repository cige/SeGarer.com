package routine;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import model.dao.DaoFactory;
import model.dao.SpotDao;
import model.entities.Spot;

public class PurgeSpotsJob implements Job {

	private final static int TIME_THRESHOLD = 30;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		SpotDao spotDao = DaoFactory.getInstance().getSpotDao();
		List<Spot> spots = spotDao.findAll(Spot.class);
		spots = spots.stream()
				.filter(p -> p.getReleaseTime().before(new Timestamp(System.currentTimeMillis() - TIME_THRESHOLD*60)))
				.collect(Collectors.toList());
		spotDao.purgeAll(spots);
	}

}
