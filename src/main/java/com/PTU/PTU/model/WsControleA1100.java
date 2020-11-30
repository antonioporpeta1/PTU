package com.PTU.PTU.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Ws_Controle_A1100")
public class WsControleA1100 implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_ws_controle_a1100")
    @SequenceGenerator(name = "sq_ws_controle_a1100", sequenceName = "sq_ws_controle_a1100", allocationSize = 1)
    @Column(name = "id_ws_controle_a1100")
    private Long idWsControleA1100;
	
    @Column(name = "dt_inclusao")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dtInclusao;
	 
    @Column(name = "dt_dia_solicitado")
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dtDiaSolicitado;

    @Column(name = "ds_tipo_cliente")
    private String dsTipoCliente;

    @Column(name = "status")
    private String status;

	public Long getIdWsControleA1100() {
		return idWsControleA1100;
	}

	public void setIdWsControleA1100(Long idWsControleA1100) {
		this.idWsControleA1100 = idWsControleA1100;
	}

	public Date getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(Date dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public Date getDtDiaSolicitado() {
		return dtDiaSolicitado;
	}

	public void setDtDiaSolicitado(Date dtDiaSolicitado) {
		this.dtDiaSolicitado = dtDiaSolicitado;
	}

	public String getDsTipoCliente() {
		return dsTipoCliente;
	}

	public void setDsTipoCliente(String dsTipoCliente) {
		this.dsTipoCliente = dsTipoCliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
