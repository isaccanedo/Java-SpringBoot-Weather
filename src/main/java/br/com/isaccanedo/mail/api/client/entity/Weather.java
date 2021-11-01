package br.com.isaccanedo.mail.api.client.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Weather implements Serializable {
	private static final long serialVersionUID = -7744982170906714088L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date instant;
	
	@Column
	private Double minimumTemperature;
	
	@Column
	private Double maximumTemperature;
	
	@ManyToOne
	private Geolocalization geolocalization;
	
	public Weather( ) {
		super();
	}
	
	public Weather(Long id, Date instant, Double minimumTemperature, Double maximumTemperature, Geolocalization geolocalization) {
		this.id = id;
		this.instant = instant;
		this.minimumTemperature = minimumTemperature;
		this.maximumTemperature = maximumTemperature;
		this.geolocalization = geolocalization;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Double getMinimumTemperature() {
		return minimumTemperature;
	}

	public void setMinimumTemperature(Double minimumTemperature) {
		this.minimumTemperature = minimumTemperature;
	}

	public Double getMaximumTemperature() {
		return maximumTemperature;
	}

	public void setMaximumTemperature(Double maximumTemperature) {
		this.maximumTemperature = maximumTemperature;
	}

	public Geolocalization getGeolocalization() {
		return geolocalization;
	}

	public void setGeolocalization(Geolocalization geolocalization) {
		this.geolocalization = geolocalization;
	}

	@Override
	public String toString() {
		return "Weather [id=" + id + ", instant=" + instant + ", minimumTemperature=" + minimumTemperature
				+ ", maximumTemperature=" + maximumTemperature + ", geolocalization=" + geolocalization + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(geolocalization, id, instant, maximumTemperature, minimumTemperature);
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
		Weather other = (Weather) obj;
		return Objects.equals(geolocalization, other.geolocalization) && Objects.equals(id, other.id)
				&& Objects.equals(instant, other.instant)
				&& Objects.equals(maximumTemperature, other.maximumTemperature)
				&& Objects.equals(minimumTemperature, other.minimumTemperature);
	}
}
