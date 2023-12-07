package br.com.senac.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "contel")
public class ContelVO implements Serializable{


	private static final long serialVersionUID = 8018416086146027602L;

	@Id
	@Basic(optional = false) // Not null está "marcado" no banco
	@NotNull
	@Column(name = "id", nullable = false)
	@SequenceGenerator(name = "contel", sequenceName = "sq_contel", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "contel")
	private BigInteger id;
	
	@Basic(optional = true) // Not Null esta "desmarcado" no banco
	@Column(name = "numero", length = 10)
	private String numero;
	
	@Basic(optional = true) // Not Null esta desmarcado no banco
	@Column(name = "dddnum", length = 2)
	private String dddnum;
	
	@Basic(optional = true) 
	@Column(name = "emails", length = 250)
	private String emails;
	
	//CHAVE ESTRANGEIRA
	@NotNull
	@JoinColumn(name = "contat", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private ContatVO contat;

	


	public ContelVO() {
		super();
	}




	public ContelVO(@NotNull BigInteger id, String numero, String dddnum, String emails, @NotNull ContatVO contat) {
		super();
		this.id = id;
		this.numero = numero;
		this.dddnum = dddnum;
		this.emails = emails;
		this.contat = contat;
	}




	public BigInteger getId() {
		return id;
	}




	public void setId(BigInteger id) {
		this.id = id;
	}




	public String getNumero() {
		return numero;
	}




	public void setNumero(String numero) {
		this.numero = numero;
	}




	public String getDddnum() {
		return dddnum;
	}




	public void setDddnum(String dddnum) {
		this.dddnum = dddnum;
	}




	public String getEmails() {
		return emails;
	}




	public void setEmails(String emails) {
		this.emails = emails;
	}




	public ContatVO getContat() {
		return contat;
	}




	public void setContat(ContatVO contat) {
		this.contat = contat;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public int hashCode() {
		return Objects.hash(contat, dddnum, emails, id, numero);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContelVO other = (ContelVO) obj;
		return Objects.equals(contat, other.contat) && Objects.equals(dddnum, other.dddnum)
				&& Objects.equals(emails, other.emails) && Objects.equals(id, other.id)
				&& Objects.equals(numero, other.numero);
	}




	@Override
	public String toString() {
		return "ContelVO [id=" + id + ", numero=" + numero + ", dddnum=" + dddnum + ", emails=" + emails + ", contat="
				+ contat + "]";
	}
	
	
	
	
	
	

	
}
