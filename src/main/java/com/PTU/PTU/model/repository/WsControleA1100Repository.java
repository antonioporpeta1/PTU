package com.PTU.PTU.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PTU.PTU.model.WsControleA1100;

public interface WsControleA1100Repository extends JpaRepository<WsControleA1100,Long> {

    @Query(value = " select * from ws_controle_a1100 ", nativeQuery = true)
    List<WsControleA1100> findAllByWsControleA1100();
	
    @Query(value = " select * from ws_controle_a1100 " +
            " where trunc(dt_inclusao) = to_date(:dt_inclusao,'dd/mm/yyyy') " +
            " order by dt_inclusao desc ", nativeQuery = true)
    List<WsControleA1100> findAllByDtInclusao(@Param("dt_inclusao") String dtInclusao);
    
}
