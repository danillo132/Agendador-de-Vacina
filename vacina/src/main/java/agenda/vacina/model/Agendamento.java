package agenda.vacina.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Agendamento implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String cidade;
	
	
	private String ubs;
	
	private String endereco;
	
	private int numero;
	
	private String bairro;
	
	
	private String telefone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date dataaplicacao;
	
	private String horario;
	
	private String status;
	
	private String dose;
	
	private String protocolo;
	
	
	
	@ForeignKey(name = "usuario_id")
	@ManyToOne
	private Usuario usuario;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	

	public String getCidade() {
		return cidade;
	}


	
	







	public String getProtocolo() {
		return protocolo;
	}


	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}


	public String getUbs() {
		return ubs;
	}
	



	public void setUbs(String ubs) {
		this.ubs = ubs;
	}


public void setEndereco(String endereco) {
	this.endereco = endereco;
}

public String getEndereco() {
	return endereco;
}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getBairro() {
		return bairro;
	}

	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public Date getDataaplicacao() {
		return dataaplicacao;
	}


	public void setDataaplicacao(Date dataaplicacao) {
		this.dataaplicacao = dataaplicacao;
	}
	
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getDose() {
		return dose;
	}


	public String getHorario() {
		return horario;
	}


	public void setHorario(String horario) {
		this.horario = horario;
	}


	public String getStatus() {
		return status;
	}
	



	public void setStatus(String status) {
		this.status = status;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	

}
