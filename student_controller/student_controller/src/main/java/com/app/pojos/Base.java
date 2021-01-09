package com.app.pojos;

import javax.persistence.*;

@MappedSuperclass
public class Base {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@Version
	long version;
	
	
	public Base() {
		super();
	}

	public Base(long id, long version) {
		this.id = id;
		this.version = version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Base [id=" + id + ", version=" + version + "]";
	}
	
}
