package br.com.senac.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "contat")
public class ContatVO implements Serializable{
	
private static final long serialVersionUID = 8018416086146027602L;
	
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "contat", sequenceName = "sq_contat", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "contat")
	private BigInteger id;
	
	@Basic
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "descri", nullable = false, length = 100)
	private String descri;
	
	
	@Basic
	@Column(name = "datnas", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date datnas;
	
	
	@Basic
	@Size(min = 1, max = 400)
	@Column(name = "observ", nullable = false, length = 400)
	private String observ;
	
	


	public ContatVO() {
		super();
	}




	public ContatVO(@NotNull BigInteger id, @NotNull @Size(min = 1, max = 100) String descri, Date datnas,
			@Size(min = 1, max = 400) String observ) {
		super();
		this.id = id;
		this.descri = descri;
		this.datnas = datnas;
		this.observ = observ;
	}




	public BigInteger getId() {
		return id;
	}




	public void setId(BigInteger id) {
		this.id = id;
	}




	public String getDescri() {
		return descri;
	}




	public void setDescri(String descri) {
		this.descri = descri;
	}




	public Date getDatnas() {
		return datnas;
	}




	public void setDatnas(Date datnas) {
		this.datnas = datnas;
	}




	public String getObserv() {
		return observ;
	}




	public void setObserv(String observ) {
		this.observ = observ;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public int hashCode() {
		return Objects.hash(datnas, descri, id, observ);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContatVO other = (ContatVO) obj;
		return Objects.equals(datnas, other.datnas) && Objects.equals(descri, other.descri)
				&& Objects.equals(id, other.id) && Objects.equals(observ, other.observ);
	}




	@Override
	public String toString() {
		return "ContatVO [id=" + id + ", descri=" + descri + ", datnas=" + datnas + ", observ=" + observ + "]";
	}
	
	

}
