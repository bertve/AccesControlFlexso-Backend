package domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Address implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String street,houseNumber,postalCode,town,country;
	
}
