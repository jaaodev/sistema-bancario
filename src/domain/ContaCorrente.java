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
        double valorFinal = getSaldo() - valor - taxa;
        double limiteChequeEspecial = -500.00;
        if(valor > 0 && valorFinal >= limiteChequeEspecial) {
            setSaldo(valorFinal);
            System.out.println("Taxa de saque aplicada: R$" + taxa);
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente. Limite de cheque especial excedido.");
        }
    }
}
