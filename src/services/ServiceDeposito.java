package services;

import model.ContaBanco;

public class ServiceDeposito {

    public void depositarDinheiro(ContaBanco conta, double deposito) {
        double saldo = conta.getSaldoConta();

        if (deposito > 0) {
            double novoSaldo = saldo + deposito;

            conta.setSaldoConta(novoSaldo);
        } else {
            throw new IllegalArgumentException("O valor mínimo deve ser R$ 1.");
        }
    }
}
