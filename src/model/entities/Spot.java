package model.entities;

import java.sql.Timestamp;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Spot {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Embedded
	private Address address;
	
	@Column(name="release_time")
	private Timestamp releaseTime;
	
	
	@Column
	/**
	 * true if the spot is free
	 */
	private boolean status;

	@OneToOne
	@JoinColumn(name="origin_user")
	private User originUser;
	
	@ManyToOne
	@JoinColumn(name="id_horodator")
	private Horodator horodator;
	
	@Column(name="vehicle_size")
	private Integer size;
	
	@OneToMany(mappedBy="aimedSpot")
	private Set<User> interestedUsers;
	
	private Spot(){
		super();
	}
	
	public Spot(Address address,User originUser){
		this.address = address;
		this.releaseTime= new Timestamp(System.currentTimeMillis());
		this.originUser = originUser;
		this.status = true;
		//TODO initialize size and horodator?
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
	
	public int getInterestedUsersNumber(){
		return this.interestedUsers.size();
	}
	
	public JsonObject toJson(){
		JsonObjectBuilder resBuilder = Json.createObjectBuilder();
		
		resBuilder.add("longitude", this.address.getLongitude());
		resBuilder.add("latitude", this.address.getLatitude());
		resBuilder.add("address", this.address.getFormattedAddress());
		
		return resBuilder.build();
	}
}
