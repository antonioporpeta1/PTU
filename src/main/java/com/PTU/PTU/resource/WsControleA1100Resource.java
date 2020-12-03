package com.PTU.PTU.resource;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import com.PTU.PTU.model.WsControleA1100;
import com.PTU.PTU.service.WsControleA1100Service;
import com.PTU.PTU.utils.PTUUtil;

@Api(value = "WsControleA1100")
@RestController
@RequestMapping(value="/api/wscontrolea1100")
public class WsControleA1100Resource {

    @Autowired
    WsControleA1100Service wsControleA1100Service;
   
    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        return wsControleA1100Service.getWsControleA1100(id);
    }
    
    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WsControleA1100> saveWsControleA1100(@RequestBody WsControleA1100 wsControleA1100) {
        return wsControleA1100Service.saveWsControleA1100(wsControleA1100);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<WsControleA1100>> findAll() {
        Collection<WsControleA1100> wsControleA1100List = wsControleA1100Service.findAll();
        return new ResponseEntity<>(wsControleA1100List, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/dtInclusao/{dtInclusao}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<WsControleA1100>> findAllByDtInclusao(@PathVariable String dtInclusao) {
        Collection<WsControleA1100> wsControleA1100List = wsControleA1100Service.findAllByDtInclusao(dtInclusao);
        return new ResponseEntity<>(wsControleA1100List, HttpStatus.OK);
    }
    
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<WsControleA1100> updateWsControleA1100(@RequestBody WsControleA1100 wsControleA1100Details){

        Optional<WsControleA1100> wsControleA1100Atual = wsControleA1100Service.findById(wsControleA1100Details.getIdWsControleA1100());
        if (!wsControleA1100Atual.isPresent()) {
            return new ResponseEntity(PTUUtil.returnError("WsControleA1100 " + wsControleA1100Details.getIdWsControleA1100() + " não encontrado"), HttpStatus.NOT_FOUND);
        }

        wsControleA1100Service.saveWsControleA1100(wsControleA1100Details);
        return new ResponseEntity(PTUUtil.returnMensagem("WsControleA1100 " + wsControleA1100Details.getIdWsControleA1100() + " alterado com sucesso"), HttpStatus.OK);
    }
}
