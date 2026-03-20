package app;

import domain.ContaBancaria;
import domain.ContaCorrente;
import domain.ContaPoupanca;
import exceptions.DepositoInvalidoException;
import exceptions.NumeroDaContaInvalido;
import exceptions.SaldoInsuficienteException;
import service.ContaManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContaManager manager = new ContaManager();

        int opcao;

        do {

            System.out.println("\nMENU DE OPÇÕES");
            System.out.println("CADASTRAR NOVA CONTA DIGITE 1");
            System.out.println("REALIZAR UM DEPÓSITO DIGITE 2");
            System.out.println("REALIZAR UM SAQUE DIGITE    3");
            System.out.println("TRANSFERIR FUNDOS DIGITE    4");
            System.out.println("VERIFICAR SALDO DIGITE      5");
            System.out.println("VERIFICAR EXTRATO DIGITE    6");
            System.out.println("SAIR DIGITE                 9\n");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:

                    System.out.println("Para criar uma Conta Poupança 1");
                    System.out.println("Para criar uma Conta Corrente 2");
                    int opcao1 = sc.nextInt();
                    sc.nextLine();

                    ContaBancaria conta = null;
                    System.out.print("Digite o nome do titular: ");
                    String contaNome = sc.nextLine();


                    if (opcao1 == 1) {
                        conta = new ContaPoupanca(contaNome);
                    } else if (opcao1 == 2) {
                        conta = new ContaCorrente(contaNome);
                    }

                    if (conta != null) {
                        manager.adicionarNovaConta(conta);
                        System.out.println("Conta Poupança cadastrada com sucesso!");
                        System.out.println("O número da conta é: " + conta.getNumeroDaConta());
                        System.out.print("Digite o valor do primeiro depósito: ");
                        double valorDep = sc.nextDouble();
                        try {
                            conta.deposito(valorDep);
                            System.out.println("Depósito em Conta " + conta.getTipo() + " realizado.");
                            conta.registrarTransacao("Depósito", valorDep, "Saque em dinheiro");
                        } catch (DepositoInvalidoException e) {
                            System.err.println("ALERTA: " + e.getMessage() + "\n");
                        }
                    }
                    break;

                case 2:

                    System.out.print("Digite o número da conta para depósito: ");
                    int numConta = sc.nextInt();
                    sc.nextLine();

                    try {
                        ContaBancaria contaEncontrada = manager.conferirNumConta(numConta);
                        System.out.print("Digite o valor do depósito: ");
                        double valorDep = sc.nextDouble();
                        sc.nextLine();
                        contaEncontrada.deposito(valorDep);
                        System.out.println("Depósito em conta " + contaEncontrada.getTipo() + " realizado.");
                        contaEncontrada.registrarTransacao("Depósito", valorDep, "Saque em dinheiro");
                    } catch (NumeroDaContaInvalido | DepositoInvalidoException e) {
                        System.err.println("ALERTA: " + e.getMessage() + "\n");
                    }
                    break;

                case 3:

                    System.out.print("Digite o número da conta para saque: ");
                    int numConta3 = sc.nextInt();
                    sc.nextLine();

                    try {
                        ContaBancaria contaEncontrada = manager.conferirNumConta(numConta3);
                        System.out.print("Digite o valor do saque: ");
                        double valorSaq = sc.nextDouble();
                        sc.nextLine();

                        try {
                            contaEncontrada.saque(valorSaq);
                            System.out.println("Saque em Conta " + contaEncontrada.getTipo() + " realizado.");
                            contaEncontrada.registrarTransacao("Saque", valorSaq, "Saque em dinheiro");
                        } catch (SaldoInsuficienteException e) {
                            System.err.println("ALERTA: " + e.getMessage() + "\n");
                        }
                    } catch (NumeroDaContaInvalido e) {
                        System.err.println("ALERTA: " + e.getMessage() + "\n");
                    }
                    break;

                case 4:

                    System.out.print("Digite o número da sua conta: ");
                    int numConta4 = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Digite o número da conta que vai receber a transferência: ");
                    int numContaRecebe = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Digite o valor da transferência: ");
                    double valorTransf = sc.nextDouble();
                    sc.nextLine();

                    try {
                        manager.transferir(numConta4, numContaRecebe, valorTransf);
                        System.out.println("Transferência realizada com sucesso.");
                    } catch (SaldoInsuficienteException | NumeroDaContaInvalido | DepositoInvalidoException e) {
                        System.err.println("ALERTA: Número de uma das contas inválido." + "\n");
                    }
                    break;

                case 5:

                    System.out.print("Digite o número da conta para consultar o saldo: ");
                    int numConta5 = sc.nextInt();
                    sc.nextLine();

                    try {
                        ContaBancaria contaEncontrada = manager.conferirNumConta(numConta5);
                        System.out.println();
                        System.out.println("Titular: " + contaEncontrada.getNomeDoTitular());
                        System.out.println("Tipo: " + contaEncontrada.getTipo());
                        System.out.println("Saldo atual: R$" + contaEncontrada.getSaldo());
                    } catch (NumeroDaContaInvalido e) {
                        System.err.println("ALERTA: " + e.getMessage() + "\n");
                    }
                    break;

                case 6:

                    System.out.print("Digite o número da conta para consultar o extrato: ");
                    int numConta6 = sc.nextInt();
                    sc.nextLine();

                    try {
                        ContaBancaria contaEncontrada = manager.conferirNumConta(numConta6);
                        contaEncontrada.exibirExtrato();
                    } catch (NumeroDaContaInvalido e) {
                        System.err.println("ALERTA: " + e.getMessage() + "\n");
                    }

            }

        } while (opcao != 9);
        System.out.println("Saindo.");
        sc.close();
    }
}
