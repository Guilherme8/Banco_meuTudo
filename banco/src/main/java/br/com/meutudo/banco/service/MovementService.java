package br.com.meutudo.banco.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.meutudo.banco.enums.TipoMovimentacaoEnum;
import br.com.meutudo.banco.enums.TipoTransacaoEnum;
import br.com.meutudo.banco.exception.ExceptionCustom;
import br.com.meutudo.banco.model.Conta;
import br.com.meutudo.banco.model.Movement;
import br.com.meutudo.banco.model.Transaction;
import br.com.meutudo.banco.repository.MovementRepository;

public class MovementService {

	 @Autowired
	 private MovementRepository movementRepository;

    // Soma movimentações da conta
    @Transactional
    public double getSomatorioByConta(long idConta) {
        return movementRepository.getSomatorioByConta(idConta);
    }

    // cria movimentação de despesa
    @Transactional
    public void criarDespesa(double valor, Conta conta, TipoTransacaoEnum tipoTransacao) {
        criar(-valor, conta, TipoMovimentacaoEnum.DESPESA, tipoTransacao, null);
    }

    // cria movimentação de receita
    @Transactional
    public void criarReceita(double valor, Conta conta, TipoTransacaoEnum tipoTransacao) {
        criar(valor, conta, TipoMovimentacaoEnum.RECEITA, tipoTransacao, null);
    }

    // cria movimentação de despesa
    @Transactional
    public void criarDespesa(double valor, Conta conta, TipoTransacaoEnum tipoTransacao, Transaction transaction) {
        criar(-valor, conta, TipoMovimentacaoEnum.DESPESA, tipoTransacao, transaction);
    }

    // cria movimentação de receita
    @Transactional
    public void criarReceita(double valor, Conta conta, TipoTransacaoEnum tipoTransacao, Transaction transaction) {
        criar(valor, conta, TipoMovimentacaoEnum.RECEITA, tipoTransacao, transaction);
    }

    // cria movimentação
    private void criar(double valor, Conta conta, TipoMovimentacaoEnum tipo, TipoTransacaoEnum tipoTransacao, Transaction transaction) {

        if ((tipoTransacao == TipoTransacaoEnum.TRANSFERENCIA || tipoTransacao == TipoTransacaoEnum.ESTORNO) && transaction == null)
            throw new ExceptionCustom("Transaferência não informada.");

        Movement movement = new Movement();
        movement.setValor(valor);
        movement.setConta(conta);
        movement.setTipo(tipo.getCodigo());
        movement.setTipoTransacao(tipoTransacao.getCodigo());
        movement.setTransaction(transaction);
        movementRepository.save(movement);
    }

}
