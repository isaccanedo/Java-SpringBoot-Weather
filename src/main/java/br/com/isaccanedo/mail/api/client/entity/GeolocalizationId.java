package br.com.isaccanedo.mail.api.client.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GeolocalizationId implements Serializable {
	private static final long serialVersionUID = -2117839216650439183L;

	@Column
	private Double lattitude;
	
	@Column
	private Double longitude;
	
	public GeolocalizationId() {
		super();
	}
	
	public GeolocalizationId(Double lattitude, Double longitude) {
		super();
		this.lattitude = lattitude;
		this.longitude = longitude;
	}

	public Double getLattitude() {
		return lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "GeolocalizationId [lattitude=" + lattitude + ", longitude=" + longitude + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(lattitude, longitude);
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
		GeolocalizationId other = (GeolocalizationId) obj;
		return Objects.equals(lattitude, other.lattitude) && Objects.equals(longitude, other.longitude);
	}
}
