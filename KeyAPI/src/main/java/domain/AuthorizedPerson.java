package domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
@NamedQueries({
	@NamedQuery(name="AuthorizedPerson.getAll",
			query="SELECT * FROM AuthorizedPerson"),
	@NamedQuery(name="AuthorizedPerson.getByEmail",
				query="SELECT a FROM AuthorizedPerson a WHERE a.email = :email")
})
public class AuthorizedPerson implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private String firstName, lastName,email;
	@ManyToMany(mappedBy="authorizedPersons")
	public Set<Office> offices;

}
