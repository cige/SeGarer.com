package model.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String email;

	private String pseudo;

	@Column
	private String password;

	@Column
	private Timestamp creationDate;

	@Column(name = "active")
	private boolean status;

	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "current_vehicle")
	private Vehicle vehicle;

	@ManyToOne
	@JoinColumn(name = "aimed_spot")
	private Spot aimedSpot;

	@Column(name = "freed_spots")
	private Long freedSpots = 0L;

	private User() {
		super();
	}

	public User(String email, String pseudo, String password) {
		this.email = email;
		this.pseudo = pseudo;
		this.password = password;
		this.creationDate = new Timestamp(System.currentTimeMillis());
		this.status = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void incrementFreedSpots() {
		this.freedSpots++;
	}

	public Long getFreedSpots() {
		return this.freedSpots;
	}

	public String getPseudo() {
		return pseudo;
	}

}