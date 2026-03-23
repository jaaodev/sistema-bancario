package domain;

import exceptions.DepositoInvalidoException;
import exceptions.SaldoInsuficienteException;

import java.util.ArrayList;
import java.util.List;

public abstract class ContaBancaria {
    private static int contadorDeContas = 1000;
    private int numeroDaConta;
    private String tipo;
    private String nomeDoTitular;
    private double saldo;
    private List<Transacao> historico = new ArrayList<>();

    public ContaBancaria(String nomeDoTitular) {
        this.nomeDoTitular = nomeDoTitular;
        this.saldo = 0.0;
        contadorDeContas++;
        this.numeroDaConta = contadorDeContas;
    }

    public void deposito(double valor) throws DepositoInvalidoException {
        if(valor > 0) {
            this.saldo += valor;
        } else {
            throw new DepositoInvalidoException("Deposite um valor maior que R$0.00");
        }
    }

    public void saque(double valor) throws SaldoInsuficienteException {
        if(valor > 0 && valor <= saldo) {
            this.saldo-=valor;
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente ou valor de saque inválido.");
        }
    }

    public void registrarTransacao(String tipo, double valor, String detalhes) {
        this.historico.add(new Transacao(tipo, valor, this.saldo, detalhes));
    }

    public void exibirExtrato() {
        System.out.println("\n ----- Extrato Conta " +
                this.getTipo() +
                " " +
                this.nomeDoTitular + " -----");
        for (Transacao t : historico) {
            System.out.println(t.getDataFormatada() + " | " +
                    t.getTipo() + " | " +
                    ": R$" + t.getValor() + " | " +
                    "Saldo: R$" + t.getSaldoApos());
        }
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public String getNomeDoTitular() {
        return nomeDoTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return this.tipo;
    }
}
