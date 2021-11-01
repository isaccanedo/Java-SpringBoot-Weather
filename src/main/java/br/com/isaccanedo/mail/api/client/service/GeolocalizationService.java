package br.com.isaccanedo.mail.api.client.service;

import org.springframework.stereotype.Service;

import br.com.isaccanedo.mail.api.client.domain.dto.GeolocalizationDTO;
import br.com.isaccanedo.mail.api.client.entity.Geolocalization;

/**
 * Service interface with definition of Geolocalization business rules
 * @author helpdesk
 *
 */
@Service
public interface GeolocalizationService {
	/**
	 * Finds the geolocalization based on an IP Address
	 * 
	 * @param ipAddress
	 * 
	 * @return Geolocalization data related to the IP Address
	 */
	GeolocalizationDTO getByIpAddress(String ipAddress);
	
	/**
	 * Saves the geolocalization data
	 * 
	 * @param geolocalizationDTO geolocalization data to be saved
	 * 
	 * @return the saved geolocalization data after the operation
	 */
	Geolocalization saveGeolocalization(GeolocalizationDTO geolocalizationDTO);
}
