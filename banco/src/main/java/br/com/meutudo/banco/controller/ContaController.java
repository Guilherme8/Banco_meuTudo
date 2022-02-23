package br.com.meutudo.banco.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.meutudo.banco.controller.dto.TransactionFutureReturnDto;
import br.com.meutudo.banco.controller.dto.TransactionReturnDto;
import br.com.meutudo.banco.service.ContaService;

@RestController
@RequestMapping("/accounts")
public class ContaController {
	
	@Autowired
    private ContaService contaService;

    // List transferencia
    @GetMapping("/{id}/transferencias")
    public ResponseEntity<List<TransactionReturnDto>> getTransferenciasById(@PathVariable long id) {
    	return new ResponseEntity<>(contaService.getTransferenciasById(id), HttpStatus.OK);
    }

    // List transferencias futuras
    @GetMapping("/{id}/transferencias/futuras")
    public ResponseEntity<List<TransactionFutureReturnDto>> getTransferenciasFuturasById(@PathVariable long id) {
        return new ResponseEntity<>(contaService.getTransferenciasFuturasById(id), HttpStatus.OK);
    }
}
