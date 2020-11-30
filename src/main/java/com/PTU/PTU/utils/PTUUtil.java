package com.PTU.PTU.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.PTU.PTU.exception.PTUException;
import com.PTU.PTU.infra.LogPTU;

@Component
public class PTUUtil {

	@Autowired
	private Environment env;

	public static final TimeZone TIMEZONE = TimeZone.getTimeZone("GMT-03:00");

	private LogPTU logger = new LogPTU(PTUUtil.class.getSimpleName());

	private PTUUtil() {}

	public static String getLastUpdatedSTR(){
        TimeZone.setDefault(TIMEZONE);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		return sdf.format(hora);
	}

	public static Date getLastUpdatedOrCreateDate(){
        TimeZone.setDefault(TIMEZONE);
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	public static Map<String, Object> returnError(String error) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("error", error);
		return map;
	}

	public static Map<String, Object> returnMensagem(String msg) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("mensagem", msg);
		return map;
	}

	public static Map<String, Object> getErroRetorno(PTUException e) {
		Erro erro = new Erro();
		erro.setMensagem(e.getMessage());

		Map<String, Object> retorno = new HashMap<>();
		retorno.put("status", "erro");
		retorno.put("erro", erro);
		return retorno;
	}

	public static Map<String, Object> getErroRetorno(Errors errors) {
		List<Erro> erroList = new ArrayList<>();
		for (ObjectError e : errors.getAllErrors()) {
			Erro erro = new Erro();

			String campo = e.getCodes()[1];
			erro.setCampo(campo.substring(campo.indexOf('.') + 1));
			erro.setMensagem(e.getDefaultMessage());
			erroList.add(erro);
		}

		Map<String, Object> retorno = new HashMap<>();
		retorno.put("status", "erro");
		retorno.put("erros", erroList);
		return retorno;
	}

	/**
	 * Recupera o valor da propriedade no arquivo properties de acordo com a chave informada.
	 *
	 * @param properties
	 * @return
	 */
	public String getValueOfProperties(String properties){
		try {
			return env.getProperty(properties);
		}catch (Exception e){
			logger.error("getValueOfProperties", "NÃ£o foi recuperar a propriedade do arquivo.");
			return "";
		}
	}

	public static boolean isCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") ||
				CPF.equals("11111111111") ||
				CPF.equals("22222222222") || CPF.equals("33333333333") ||
				CPF.equals("44444444444") || CPF.equals("55555555555") ||
				CPF.equals("66666666666") || CPF.equals("77777777777") ||
				CPF.equals("88888888888") || CPF.equals("99999999999") ||
				(CPF.length() != 11))
			return(false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i=0; i<9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int)(CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for(i=0; i<10; i++) {
				num = (int)(CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else dig11 = (char)(r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return(true);
			else return(false);
		} catch (InputMismatchException erro) {
			return(false);
		}
	}
}
