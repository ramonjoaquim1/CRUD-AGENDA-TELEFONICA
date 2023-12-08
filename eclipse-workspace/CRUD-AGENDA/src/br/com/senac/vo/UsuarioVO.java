package br.com.senac.vo;

import java.io.Serializable;
import java.math.BigInteger;

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

@Entity
@Table(name = "SIUSUARI")
public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 5115882895930635985L;

	@Id
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "SQ_SIUSUARI", sequenceName = "SQ_SIUSUARI", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SQ_SIUSUARI")
	private BigInteger id;
	
	//Login - 30 caracteres
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "LOGUSU", nullable = false, length = 30)
	private String logusu;
	
	//Senha - 100 caracteres
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "SENUSU", nullable = false, length = 45)
	private String senusu;

	public UsuarioVO() {
		super();	
	}

	public UsuarioVO(BigInteger id, String logusu, String senusu) {
		super();
		this.id = id;
		this.logusu = logusu;
		this.senusu = senusu;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getLogusu() {
		return logusu;
	}

	public void setLogusu(String logusu) {
		this.logusu = logusu;
	}

	public String getSenusu() {
		return senusu;
	}

	public void setSenusu(String senusu) {
		this.senusu = senusu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UsuarioVO other = (UsuarioVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioVO [id=" + id + ", logusu=" + logusu + ", senusu=" + senusu + "]";
	}

}
