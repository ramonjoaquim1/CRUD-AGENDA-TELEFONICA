package br.com.senac.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Classe de persistência que representa a tabela de clientes
 *  
 * @author rogerio.cortina
 * @since 24/10/2023
 *
 */
@Entity
@Table(name = "siclient")
public class ClienteVO implements Serializable {
	
	private static final long serialVersionUID = 8628720230067281243L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "sq_siclient", sequenceName = "sq_siclient",
			allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, 
		generator = "sq_siclient")
	private BigInteger id;
	
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "descri", nullable = false, length = 100)
	private String descri;
	
	public ClienteVO() {
		super();
	}
	
	public ClienteVO(BigInteger id) {
		super();
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteVO other = (ClienteVO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "ClienteVO [id=" + id + "]";
	}
	
	

}
