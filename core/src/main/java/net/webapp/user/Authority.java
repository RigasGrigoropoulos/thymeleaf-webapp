package net.webapp.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Authorities")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(optional = false)
	private User user;

	@Enumerated(EnumType.STRING)
	private SecurityRole authority;
	
	public Authority() { }

	public Authority(User user, SecurityRole authority) {
		this.user = user;
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SecurityRole getAuthority() {
		return authority;
	}

	public void setAuthority(SecurityRole authority) {
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

}
