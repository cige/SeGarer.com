package model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(name = "formatted_address")
	private String formattedAddress;

	@Column
	private Double longitude;

	@Column
	private Double latitude;

	private Address() {

	}

	public Address(Double longitude, Double latitude, String formattedAddress) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.formattedAddress = formattedAddress;
	}

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Override
	public String toString() {
		return formattedAddress + "(" + longitude + ", " + latitude + ")";
	}
}
