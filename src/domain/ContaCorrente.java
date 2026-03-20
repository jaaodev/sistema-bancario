package domain;

import exceptions.SaldoInsuficienteException;

public class ContaCorrente extends ContaBancaria {
    public ContaCorrente(String nomeDoTitular) {
        super(nomeDoTitular);
    }

    @Override
    public String getTipo() {
        return "Corrente";
    }

    @Override
    public void saque(double valor) throws SaldoInsuficienteException {
        double taxa = 2.50;
        super.saque(valor + taxa);
        System.out.println("Taxa de saque aplicada: R$" + taxa);
    }
}
