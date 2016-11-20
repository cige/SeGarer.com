package model.entities;

import java.sql.Timestamp;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import api.Metric;

@Entity
public class Spot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Embedded
	private Address address;

	@Column(name = "release_time")
	private Timestamp releaseTime;

	@Column
	/**
	 * true if the spot is free
	 */
	private boolean isFree;

	@OneToOne
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name = "origin_user")
	private User originUser;

	@ManyToOne
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name = "id_horodator")
	private Horodator horodator;

	@Column(name = "vehicle_size")
	private Integer size;

	@Column(name = "interested_nb")
	private int interestedUsers = 0;

	private Spot() {
	}

	public Spot(Address address, User originUser) {
		this.address = address;
		this.releaseTime = new Timestamp(System.currentTimeMillis());
		this.originUser = originUser;
		this.isFree = true;
		// TODO initialize size and horodator?
	}

	public Spot(Address address) {
		this.address = address;
		this.releaseTime = new Timestamp(System.currentTimeMillis());
		this.isFree = true;
		// TODO initialize size and horodator?
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Timestamp getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Timestamp releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Horodator getHorodator() {
		return horodator;
	}

	public void setHorodator(Horodator horodator) {
		this.horodator = horodator;
	}

	public User getOriginUser() {
		return originUser;
	}

	public void setOriginUser(User originUser) {
		this.originUser = originUser;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public int incremenInterestUsers() {
		return this.interestedUsers++;
	}

	public int getInterestedUsers() {
		return interestedUsers;
	}

	public void setInterestedUsers(int interestedUsers) {
		this.interestedUsers = interestedUsers;
	}

	public JsonObject toJson(Metric metric, float purcentage) {
		
		JsonObjectBuilder resBuilder = Json.createObjectBuilder();

		resBuilder.add("longitude", this.address.getLongitude());
		resBuilder.add("latitude", this.address.getLatitude());
		resBuilder.add("address", this.address.getFormattedAddress());

		resBuilder.add("intersted", interestedUsers);
		resBuilder.add("purcentage", purcentage);
		resBuilder.add("id", this.id.longValue());
		resBuilder.add("distance", metric.getFormattedDistance());
		resBuilder.add("duration", metric.getFormattedDuration());
		return resBuilder.build();
	}
}
