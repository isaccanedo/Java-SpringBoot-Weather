package br.com.isaccanedo.mail.api.client.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.isaccanedo.mail.api.client.dao.WeatherDAO;
import br.com.isaccanedo.mail.api.client.domain.dto.GeolocalizationDTO;
import br.com.isaccanedo.mail.api.client.domain.dto.WeatherDTO;
import br.com.isaccanedo.mail.api.client.entity.Geolocalization;
import br.com.isaccanedo.mail.api.client.entity.Weather;
import br.com.isaccanedo.mail.api.client.repository.WeatherRepository;

@Service
public class WeatherServiceImpl implements WeatherService {
	private WeatherDAO weatherDAO;
	private WeatherRepository weatherRepository;
	
	@Autowired
	public WeatherServiceImpl(WeatherDAO weatherDAO, WeatherRepository weatherRepository) {
		this.weatherDAO = weatherDAO;
		this.weatherRepository = weatherRepository;
	}

	@Override
	public WeatherDTO findByGeolocalization(GeolocalizationDTO geolocalization) {
		return this.weatherDAO.getWeatherByGeolocation(geolocalization);
	}
	
	@Override
	public Weather saveWeatherByGeolocalization(WeatherDTO weatherDTO, Geolocalization geolocalization) {
		Weather weather = new Weather();
		weather.setInstant(new Date(weatherDTO.getInstant()));
		weather.setMinimumTemperature(weatherDTO.getMinimumTemperature());
		weather.setMaximumTemperature(weatherDTO.getMaximumTemperature());
		weather.setGeolocalization(geolocalization);
		return this.weatherRepository.save(weather);
	}
}
