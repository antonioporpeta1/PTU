package com.PTU.PTU.infra;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogPTU {

	private static final String TAG_TAG = "TAG: ";
	private static final String DETALHES_TAG = " Erro: ";
	private static final String ESPACO_TAG = " ";

	private Logger logger;
	private String classe;

	public LogPTU(String classe) {
		super();
		this.classe = classe;
		this.logger = LogManager.getLogger(classe);
	}

	public void error(String metodo, String mensagemErro) {
		StringBuilder error = new StringBuilder();
		error.append(TAG_TAG).append(classe).append(".").append(metodo);
		error.append(DETALHES_TAG).append(mensagemErro);
		logger.error(error.toString());
	}

	public void warn(String metodo, String mensagemWarn) {
		StringBuilder msg = new StringBuilder();
		msg.append(TAG_TAG).append(classe).append(".").append(metodo);
		msg.append(ESPACO_TAG).append(mensagemWarn);
		logger.warn(msg.toString());
	}

	public void info(String metodo, String mensagemInfo) {
		StringBuilder msg = new StringBuilder();
		msg.append(TAG_TAG).append(classe).append(".").append(metodo);
		msg.append(ESPACO_TAG).append(mensagemInfo);
		logger.info(msg.toString());
	}

	public void debug(String metodo, String mensagemDebug) {
		StringBuilder msg = new StringBuilder();
		msg.append(TAG_TAG).append(classe).append(".").append(metodo);
		msg.append(ESPACO_TAG).append(mensagemDebug);
		logger.debug(msg.toString());
	}

}
