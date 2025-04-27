package services;

import model.ContaBanco;

public class ServiceSaque {

    public void sacar(ContaBanco conta, double valorSaque) {
        double juros = 1.01;
        double valorComJuros = valorSaque * juros;

        if (conta.getSaldoConta() >= valorComJuros) {
            conta.setSaldoConta(conta.getSaldoConta() - valorComJuros);
            System.out.println("Saque realizado com sucesso! Novo saldo: " + conta.getSaldoConta());
        } else {
            System.out.println("Saldo insuficiente para saque com juros.");
        }
    }
}
