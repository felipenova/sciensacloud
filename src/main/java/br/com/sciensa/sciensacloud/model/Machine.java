package br.com.sciensa.sciensacloud.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.sciensa.sciensacloud.helper.JsonStdDateDeserializer;
import br.com.sciensa.sciensacloud.helper.JsonStdDateSerializer;

@Entity
@Table(name="Machine")
@NamedQueries(value={
		@NamedQuery(name="search.by.client.hash", 
				query="select m from Machine m join m.client cli where upper(cli.hash) = :clientHash"),
		@NamedQuery(name="search.by.client.hash.and.machine.id", 
		query="select m from Machine m join m.client cli where upper(cli.hash) = :clientHash and m.id = :machineId")
		
})
public class Machine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="url",nullable=false)
	private String url;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created",nullable=false)
	@JsonSerialize(using = JsonStdDateSerializer.class)
    @JsonDeserialize(using = JsonStdDateDeserializer.class)
	private Date created;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER) 
	@JoinTable(name="Machine_Cartridge", 
	joinColumns={@JoinColumn(name="machine_id", referencedColumnName="id")}
	, inverseJoinColumns={@JoinColumn(name="cartridge_id", referencedColumnName="id")}) 
	private List<Cartridge> cartridges;
	
	@ManyToOne
	@JoinColumn(name="client_id",nullable=false)
	private Client client;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public List<Cartridge> getCartridges() {
		return cartridges;
	}
	public void setCartridges(List<Cartridge> cartridges) {
		this.cartridges = cartridges;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Machine other = (Machine) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	
	
	
}
