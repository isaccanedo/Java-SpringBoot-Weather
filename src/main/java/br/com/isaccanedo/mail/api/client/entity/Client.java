package br.com.isaccanedo.mail.api.client.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Client domain entity
 */
@Entity
public class Client implements Serializable {
	private static final long serialVersionUID = 567634661639289326L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private int age;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@OneToOne
	private Weather weatherWhenCreated;

	public Client() {
		super();
	}
	
	public Client(Long id, String name, String email, int age, Weather weatherWhenCreated) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.creationDate = new Date();
		this.weatherWhenCreated = weatherWhenCreated;
	}

	public Client(Long id, String name, String email, int age, Date creationDate, Weather weatherWhenCreated) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
		this.creationDate = creationDate;
		this.weatherWhenCreated = weatherWhenCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Weather getWeatherWhenCreated() {
		return weatherWhenCreated;
	}

	public void setWeatherWhenCreated(Weather weatherWhenCreated) {
		this.weatherWhenCreated = weatherWhenCreated;
	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", creationDate=" + creationDate + ", weatherWhenCreated=" + weatherWhenCreated + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}
}