package br.com.isaccanedo.mail.api.client.domain.dto;

import java.util.Objects;

public class GeolocalizationDTO {
	private final String ipAddress;
	private final Double lattitude;
	private final Double longitude;
	private final String subdivisionName;
	private final String continentName;
	private final String countryName;
	private final String cityName;
	
	public GeolocalizationDTO(String ipAddress, Double lattitude, Double longitude, String subdivisionName,
			String continentName, String countryName, String cityName) {
		super();
		this.ipAddress = ipAddress;
		this.lattitude = lattitude;
		this.longitude = longitude;
		this.subdivisionName = subdivisionName;
		this.continentName = continentName;
		this.countryName = countryName;
		this.cityName = cityName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public Double getLattitude() {
		return lattitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getSubdivisionName() {
		return subdivisionName;
	}

	public String getContinentName() {
		return continentName;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getCityName() {
		return cityName;
	}

	@Override
	public String toString() {
		return "GeolocalizationDTO [ipAddress=" + ipAddress + ", lattitude=" + lattitude + ", longitude=" + longitude
				+ ", subdivisionName=" + subdivisionName + ", continentName=" + continentName + ", countryName="
				+ countryName + ", cityName=" + cityName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cityName, continentName, countryName, ipAddress, lattitude, longitude, subdivisionName);
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
		GeolocalizationDTO other = (GeolocalizationDTO) obj;
		return Objects.equals(cityName, other.cityName) && Objects.equals(continentName, other.continentName)
				&& Objects.equals(countryName, other.countryName) && Objects.equals(ipAddress, other.ipAddress)
				&& Objects.equals(lattitude, other.lattitude) && Objects.equals(longitude, other.longitude)
				&& Objects.equals(subdivisionName, other.subdivisionName);
	}
}
