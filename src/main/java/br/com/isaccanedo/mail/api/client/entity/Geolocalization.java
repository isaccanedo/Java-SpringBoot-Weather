package br.com.isaccanedo.mail.api.client.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Geolocalization implements Serializable {
	private static final long serialVersionUID = 7061126952717743097L;
	
	@EmbeddedId
	private GeolocalizationId coordinates;
	
	@Column
	private String ipv4Address;
	
	@Column
	private String subdivisionName;
	
	@Column
	private String continentName;
	
	@Column
	private String countryName;
	
	@Column
	private String cityName;

	public Geolocalization() {
		super();
	}
	
	public Geolocalization(GeolocalizationId coordinates, String ipv4Address, String subdivisionName, String continentName, String countryName, String cityName) {
		super();
		this.coordinates = coordinates;
		this.ipv4Address = ipv4Address;
		this.subdivisionName = subdivisionName;
		this.continentName = continentName;
		this.countryName = countryName;
		this.cityName = cityName;
	}

	public GeolocalizationId getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(GeolocalizationId coordinates) {
		this.coordinates = coordinates;
	}

	public String getIpv4Address() {
		return ipv4Address;
	}

	public void setIpv4Address(String ipv4Address) {
		this.ipv4Address = ipv4Address;
	}

	public String getSubdivisionName() {
		return subdivisionName;
	}

	public void setSubdivisionName(String subdivisionName) {
		this.subdivisionName = subdivisionName;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String toString() {
		return "Geolocalization [coordinates=" + coordinates + ", ipv4Address=" + ipv4Address + ", subdivisionName="
				+ subdivisionName + ", continentName=" + continentName + ", countryName=" + countryName + ", cityName="
				+ cityName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordinates);
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
		Geolocalization other = (Geolocalization) obj;
		return Objects.equals(coordinates, other.coordinates);
	}
}
