package br.com.isaccanedo.mail.api.client.domain.dto;

import java.util.Objects;

public class ApiErrorDTO {
	private final String error;
	private final String message;
	private final Integer status;
	private final Long timestamp;
	
	public ApiErrorDTO(String error, String message, Integer status, Long timestamp) {
		super();
		this.error = error;
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ApiErrorDTO [error=" + error + ", message=" + message + ", status=" + status + ", timestamp="
				+ timestamp + "]";
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public Integer getStatus() {
		return status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(error, message, status, timestamp);
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
		ApiErrorDTO other = (ApiErrorDTO) obj;
		return Objects.equals(error, other.error) && Objects.equals(message, other.message)
				&& Objects.equals(status, other.status) && Objects.equals(timestamp, other.timestamp);
	}
}
