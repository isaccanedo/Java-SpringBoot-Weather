package br.com.isaccanedo.mail.api.client.service;

import org.springframework.stereotype.Service;

import br.com.isaccanedo.mail.api.client.domain.dto.GeolocalizationDTO;
import br.com.isaccanedo.mail.api.client.domain.dto.WeatherDTO;
import br.com.isaccanedo.mail.api.client.entity.Geolocalization;
import br.com.isaccanedo.mail.api.client.entity.Weather;

/**
 * Service interface with signature methods for Weather data business rules
 *
 * @see Weather
 * @see WeatherDTO
 */
@Service
public interface WeatherService {
	/**
	 * Finds the Weather data based on a given Geolocalization
	 * 
	 * @see Weather
	 * @see WeatherDTO
	 * @see Geolocalization
	 * @see GeolocalizationDTO
	 * 
	 * @param geolocalization
	 * 
	 * @return the Weather data
	 */
	WeatherDTO findByGeolocalization(GeolocalizationDTO geolocalization);
	
	/**
	 * Saves the Weather data by geolocalization
	 * 
	 * @see Weather
	 * @see WeatherDTO
	 * @see Geolocalization
	 * @see GeolocalizationDTO
	 * 
	 * @param weatherDTO the weather data to be saved
	 * @param geolocalization the geolocalization related to the weather
	 * 
	 * @return Weather data after the operation
	 */
	Weather saveWeatherByGeolocalization(WeatherDTO weatherDTO, Geolocalization geolocalization);
}
