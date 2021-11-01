package br.com.isaccanedo.mail.api.client.dao.rest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.isaccanedo.mail.api.client.dao.WeatherDAO;
import br.com.isaccanedo.mail.api.client.domain.dto.GeolocalizationDTO;
import br.com.isaccanedo.mail.api.client.domain.dto.WeatherDTO;

/**
 * DAO class to retrieve Weather data from the Meta Weather API
 * {@link https://metaweather.com/api/}
 */
@Component
public class MetaWeatherDAO implements WeatherDAO {
	private static final Logger logger = LoggerFactory.getLogger(MetaWeatherDAO.class);
	
	@Value("${weather.api.url}")
	private String baseUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public MetaWeatherDAO(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	private String getSearchUrl(Double lattitude, Double longitude) {
		String templateUrl = this.baseUrl + "/api/location/?lattlong=%.2f,%.2f";
		return String.format(templateUrl, lattitude, longitude);
	}
	
	private String getLocationUrl(Long woeid) {
		String templateUrl = this.baseUrl + "/api/location/%s/";
		return String.format(templateUrl, woeid);
	}
	
	@Override
	public WeatherDTO getWeatherByGeolocation(GeolocalizationDTO geolocation) {
		Double lattitude = geolocation.getLattitude();
		Double longitude = geolocation.getLongitude();
		String searchUrl = this.getSearchUrl(lattitude, longitude);
		ResponseEntity<String> response = this.restTemplate.getForEntity(searchUrl, String.class);
		if(!response.getStatusCode().is2xxSuccessful()) {
			logger.error(response.getBody());
			throw new IllegalStateException("Failed to retrieve Weather data");
		}
		String body = response.getBody();
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(body).getAsJsonObject();
		
		Long woeid = json.get("woeid").getAsLong();
		String locationUrl = this.getLocationUrl(woeid);
		response = this.restTemplate.getForEntity(locationUrl, String.class);
		if(!response.getStatusCode().is2xxSuccessful()) {
			logger.error(response.getBody());
			throw new IllegalStateException("Failed to retrieve Weather data");
		}
		body = response.getBody();
		json = parser.parse(body).getAsJsonObject();
		JsonObject consolidatedWeather = json.get("consolidated_weather").getAsJsonArray().get(0).getAsJsonObject();
		Double maximumTemperature = consolidatedWeather.get("min_temp").getAsDouble();
		Double minimumTemperature = consolidatedWeather.get("max_temp").getAsDouble();
		Date now = new Date();
		return new WeatherDTO(maximumTemperature, minimumTemperature, now.getTime());
	}
}
