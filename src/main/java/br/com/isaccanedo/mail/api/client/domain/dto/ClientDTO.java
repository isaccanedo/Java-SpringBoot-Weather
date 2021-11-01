package br.com.isaccanedo.mail.api.client.domain.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.isaccanedo.mail.api.client.entity.Client;

/**
 * Immutable DTO object representation of the Client entity
 * @see Client
 */
public class ClientDTO {
	private Long id;
	
	@NotEmpty(message = "Name must not be empty")
	@NotNull(message = "Name must not be null")
	private final String name;
	
	@NotEmpty(message = "E-mail must not be empty")
	@NotNull(message = "E-mail must not be null")
	@Email(message = "Invalid e-mail address")
	private final String email;
	
	@Positive(message = "Age should be greater than zero")
	private final Integer age;
	
	private String ipAddress;
	
	private WeatherDTO wheaterWhenCreated;
	
	public ClientDTO(String name, String email, Integer age) {
		super();
		this.name = name;
		this.email = email;
		this.age = age;
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

	public String getEmail() {
		return email;
	}

	public Integer getAge() {
		return age;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public WeatherDTO getWheaterWhenCreated() {
		return wheaterWhenCreated;
	}

	public void setWheaterWhenCreated(WeatherDTO wheaterWhenCreated) {
		this.wheaterWhenCreated = wheaterWhenCreated;
	}

	@Override
	public String toString() {
		return "ClientDTO [id=" + id + ", name=" + name + ", email=" + email + ", age=" + age + ", ipAddress="
				+ ipAddress + ", wheaterWhenCreated=" + wheaterWhenCreated + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, email, id, ipAddress, name, wheaterWhenCreated);
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
		ClientDTO other = (ClientDTO) obj;
		return Objects.equals(age, other.age) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(ipAddress, other.ipAddress) && Objects.equals(name, other.name)
				&& Objects.equals(wheaterWhenCreated, other.wheaterWhenCreated);
	}
}
