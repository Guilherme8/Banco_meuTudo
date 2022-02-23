package br.com.meutudo.banco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meutudo.banco.controller.dto.TransactionDto;
import br.com.meutudo.banco.controller.dto.TransactionFutureDto;
import br.com.meutudo.banco.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
    private TransactionService transactionService;

    // Cadastra transferência p/ conta
    @PostMapping
    public ResponseEntity criar(@RequestBody @Validated TransactionDto transactionDto) {
    	transactionService.criar(transactionDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // Estorna transferência
    @PostMapping("/{id}/reverse")
    public ResponseEntity estornar(@PathVariable long id) {
    	transactionService.estornar(id);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    // Cria transferências futuras parcelando o valor
    @PostMapping("/future")
    public ResponseEntity criarFutura(@RequestBody @Validated TransactionFutureDto transactionFutureDto) {
    	transactionService.criarFutura(transactionFutureDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
