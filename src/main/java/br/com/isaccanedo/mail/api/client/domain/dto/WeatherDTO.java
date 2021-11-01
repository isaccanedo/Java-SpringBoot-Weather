package br.com.isaccanedo.mail.api.client.domain.dto;

import java.util.Objects;

public class WeatherDTO {
	private final Long instant;
	private final Double maximumTemperature;
	private final Double minimumTemperature;
	
	private GeolocalizationDTO geolocalization;
	
	public WeatherDTO(Double maximumTemperature, Double minimumTemperature, Long instant) {
		this.maximumTemperature = maximumTemperature;
		this.minimumTemperature = minimumTemperature;
		this.instant = instant;
	}

	public Double getMaximumTemperature() {
		return maximumTemperature;
	}

	public Double getMinimumTemperature() {
		return minimumTemperature;
	}
	
	public Long getInstant() {
		return this.instant;
	}

	public GeolocalizationDTO getGeolocalization() {
		return geolocalization;
	}

	public void setGeolocalization(GeolocalizationDTO geolocalization) {
		this.geolocalization = geolocalization;
	}

	@Override
	public String toString() {
		return "WeatherDTO [instant=" + instant + ", maximumTemperature=" + maximumTemperature + ", minimumTemperature="
				+ minimumTemperature + ", geolocalization=" + geolocalization + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(geolocalization, instant, maximumTemperature, minimumTemperature);
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
		WeatherDTO other = (WeatherDTO) obj;
		return Objects.equals(geolocalization, other.geolocalization) && Objects.equals(instant, other.instant)
				&& Objects.equals(maximumTemperature, other.maximumTemperature)
				&& Objects.equals(minimumTemperature, other.minimumTemperature);
	}
}
