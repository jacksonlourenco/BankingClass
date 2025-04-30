package services;

import model.ContaBanco;

public class ServiceSaque {

    public void sacarDinheiro(ContaBanco conta, double valorSaque) {
        double juros = 1.05;
        double valorComJuros = valorSaque * juros;

        if (conta.getSaldoConta() >= valorComJuros) {

            conta.setSaldoConta(conta.getSaldoConta() - valorComJuros);

            System.out.println("Saque de: R$ " + valorSaque + ", realizado com sucesso!");
            System.out.println("Saldo atual: R$ " + conta.getSaldoConta());
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }
}
