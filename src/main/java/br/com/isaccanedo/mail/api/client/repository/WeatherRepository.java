package br.com.isaccanedo.mail.api.client.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.isaccanedo.mail.api.client.entity.Weather;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {}
