package service;

import domain.ContaBancaria;
import exceptions.DepositoInvalidoException;
import exceptions.NumeroDaContaInvalido;
import exceptions.SaldoInsuficienteException;

import java.util.ArrayList;

public class ContaManager {
    private final ArrayList<ContaBancaria> contas = new ArrayList<>();

    public void adicionarNovaConta(ContaBancaria conta) {
        this.contas.add(conta);
    }

    public ContaBancaria conferirNumConta(int num) throws NumeroDaContaInvalido{
        for (ContaBancaria c : contas) {
            if(c.getNumeroDaConta() == num) {
                    return c;
            }
        }
        throw new NumeroDaContaInvalido("Número da conta inválido.");
    }

    public void transferir(int numOrigem, int numDestino, double valor) throws SaldoInsuficienteException, NumeroDaContaInvalido, DepositoInvalidoException {
            ContaBancaria origem = conferirNumConta(numOrigem);
            ContaBancaria destino = conferirNumConta(numDestino);

            origem.saque(valor);
            origem.registrarTransacao("Transferência enviada", valor, "Para: " + destino.getNomeDoTitular());
            destino.deposito(valor);
            destino.registrarTransacao("Transferência recebida", valor, "Para: " + destino.getNomeDoTitular());
    }

}
