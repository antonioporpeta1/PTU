package com.PTU.PTU.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PTU.PTU.model.WsControleA1100;
import com.PTU.PTU.model.repository.WsControleA1100Repository;
import com.PTU.PTU.utils.PTUUtil;

@Service
public class WsControleA1100Service {

    @Autowired
    WsControleA1100Repository wsControleA1100Repository;
	
    public List<WsControleA1100> findAll() {
        return wsControleA1100Repository.findAll();
    }

    public ResponseEntity getWsControleA1100(Long id) {
        Map<String, Object> retornaMap = new HashMap<>();
        Optional<WsControleA1100> wsControleA1100 = wsControleA1100Repository.findById(id);
        retornaMap.put("WsControleA1100", wsControleA1100);
        return new ResponseEntity(retornaMap, HttpStatus.OK);
    }

    public Optional<WsControleA1100> findById(Long id) {
        Optional<WsControleA1100> wsControleA1100 = wsControleA1100Repository.findById(id);
        return wsControleA1100;
    }

    public WsControleA1100 save(WsControleA1100 wsControleA1100) {
    	wsControleA1100.setDtInclusao(PTUUtil.getLastUpdatedOrCreateDate());
        return wsControleA1100Repository.save(wsControleA1100);
    }
    
    
    public ResponseEntity saveWsControleA1100(WsControleA1100 wsControleA1100) {
        Map<String, Object> retornaMap;
        try {
            if (wsControleA1100.getDtInclusao() == null){
                retornaMap = PTUUtil.returnError("Data de inclusão não informada!");
                return new ResponseEntity(retornaMap, HttpStatus.BAD_REQUEST);
            }
            if (wsControleA1100.getDtDiaSolicitado() == null){
                retornaMap = PTUUtil.returnError("Data de referência a movimentação não informada!");
                return new ResponseEntity(retornaMap, HttpStatus.BAD_REQUEST);
            }
            if (wsControleA1100.getDsTipoCliente() == null){
                retornaMap = PTUUtil.returnError("Tipo do cliente. Fixo: UNIMED não informado!");
                return new ResponseEntity(retornaMap, HttpStatus.BAD_REQUEST);
            }
            if (wsControleA1100.getStatus() == null){
                retornaMap = PTUUtil.returnError("Status não informado!");
                return new ResponseEntity(retornaMap, HttpStatus.BAD_REQUEST);
            }

            wsControleA1100 = this.save(wsControleA1100);
            retornaMap = PTUUtil.returnMensagem("WsControleA1100,'"+ wsControleA1100.getIdWsControleA1100() +"' cadastrado com sucesso");
            return new ResponseEntity(retornaMap, HttpStatus.OK);
        }catch (Exception e) {
            retornaMap = PTUUtil.returnError(e.getMessage());
            return new ResponseEntity(retornaMap ,HttpStatus.BAD_REQUEST);
        }
    }
	
}
