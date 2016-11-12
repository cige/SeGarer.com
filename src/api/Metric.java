package api;

public class Metric {

	private float distance;

	private float duration;

	private String formattedDistance;
	private String formattedDuration;

	public Metric(float dist, float duree) {
		this.distance = dist;
		this.duration = duree;
	}

	public Metric(float dist, float duree, String fd, String fdur) {
		this.distance = dist;
		this.duration = duree;
		this.formattedDistance = fd;
		this.formattedDuration = fdur;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getFormattedDuration() {
		return formattedDuration;
	}

	public String getFormattedDistance() {
		return formattedDistance;
	}

	@Override
	public String toString() {
		return "Distance: " + formattedDistance + ", duration: " + formattedDuration;
	}
}
