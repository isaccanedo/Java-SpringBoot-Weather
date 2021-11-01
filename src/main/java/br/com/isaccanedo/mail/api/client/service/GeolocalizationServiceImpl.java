package br.com.isaccanedo.mail.api.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.isaccanedo.mail.api.client.dao.GeolocalizationDAO;
import br.com.isaccanedo.mail.api.client.domain.dto.GeolocalizationDTO;
import br.com.isaccanedo.mail.api.client.entity.Geolocalization;
import br.com.isaccanedo.mail.api.client.entity.GeolocalizationId;
import br.com.isaccanedo.mail.api.client.repository.GeolocalizationRepository;

@Service
public class GeolocalizationServiceImpl implements GeolocalizationService {
	private GeolocalizationDAO geolocalizationDAO;
	private GeolocalizationRepository geolocalizationRepository; 
	
	@Autowired
	public GeolocalizationServiceImpl(GeolocalizationDAO geolocalizationDAO, GeolocalizationRepository geolocalizationRepository) {
		this.geolocalizationDAO = geolocalizationDAO;
		this.geolocalizationRepository = geolocalizationRepository;
	}

	@Override
	public GeolocalizationDTO getByIpAddress(String ipAddress) {
		return this.geolocalizationDAO.findByIpAddress(ipAddress);
	}
	
	@Override
	public Geolocalization saveGeolocalization(GeolocalizationDTO geolocalizationDTO) {
		Geolocalization geolocalization = new Geolocalization();
		
		geolocalization.setCityName(geolocalizationDTO.getCityName());
		geolocalization.setContinentName(geolocalizationDTO.getContinentName());
		geolocalization.setCountryName(geolocalizationDTO.getCountryName());
		geolocalization.setIpv4Address(geolocalizationDTO.getIpAddress());
		geolocalization.setSubdivisionName(geolocalizationDTO.getSubdivisionName());
		
		GeolocalizationId coordinates = new GeolocalizationId();
		coordinates.setLattitude(geolocalizationDTO.getLattitude());
		coordinates.setLongitude(geolocalizationDTO.getLongitude());
		geolocalization.setCoordinates(coordinates);
		
		return this.geolocalizationRepository.save(geolocalization);
	}
}
