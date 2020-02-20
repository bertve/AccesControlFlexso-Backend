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
	@NamedQuery(name="Office.getAll",
			query="SELECT * FROM Office")

})
public class Office implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Embedded
	private Address address;
	@ManyToOne(optional = false,fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private Company company;
	@ManyToMany
	@JoinTable(name="officeAuthorizedPerson",
	joinColumns=@JoinColumn(name="id"),
	inverseJoinColumns=@JoinColumn(name="id"))
	@JoinColumn()
	private Set<AuthorizedPerson> authorizedPersons;	
}
