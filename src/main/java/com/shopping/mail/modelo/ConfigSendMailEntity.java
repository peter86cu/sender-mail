package com.shopping.mail.modelo;


import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "config_send_mail")
public class ConfigSendMailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String codigo;
	private String clave;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	

}
