package com.PTU.PTU.utils;

import java.io.Serializable;

public class Erro implements Serializable {
	private static final long serialVersionUID = -359658709585342837L;

	private String campo;
	private String mensagem;

	public String getCampo() {
		return campo;
	}
	public String getMensagem() {
		return mensagem;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String toString() {
		return "[" + campo + "] " + mensagem;
	}
}
