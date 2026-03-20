package domain;

import exceptions.DepositoInvalidoException;

public class ContaPoupanca extends ContaBancaria {
    public ContaPoupanca(String nomeDoTitular) {
        super(nomeDoTitular);
    }

    @Override
    public String getTipo() {
        return "Poupança";
    }

    @Override
    public void deposito(double valor) throws DepositoInvalidoException {
        double juros = valor * 0.003;
        super.deposito(valor + juros);
        System.out.printf("Você recebeu um rendimento de R$%.2f%n",juros);
    }
}
