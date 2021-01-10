package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	@Column(name="addr_1")
	private String addr;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="landmark")
	private String landmark;
	@Column(name="zipcode")
	private String zipcode;
	
	public Address() {}

	public Address(String addr, String city, String state, String zipcode, String landmark) {
		super();
		this.addr = addr;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.landmark = landmark;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getAddressString() {
		String addr ;
		addr = this.addr+", "+this.city+", "+this.landmark+","+this.state+", "+this.zipcode;
		return addr;
	}
	@Override
	public String toString() {
		return "Address [addr=" + addr + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + "]";
	}
	
}
