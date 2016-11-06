package model.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name="formatted_address")
	private String formattedAddress;
	
	@Column
	private Float longitude;
	
	@Column
	private Float latitude;
	
	private Address(){
		super();
	}
	
	public Address(Float longitude, Float latitude, String formattedAddress){
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

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

}
