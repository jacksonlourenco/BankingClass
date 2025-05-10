package services;

import java.util.List;
import model.ContaBanco;

public class ServiceDeposito {

    public void depositarDinheiro(List<ContaBanco> contas, double deposito, String cpf) {

        ContaBanco conta = null;

        for (ContaBanco c : contas) {

            if (c.getCpf().equals(cpf)) {
                conta = c;
                break;
            }
        }

        if (conta != null) {

            if (deposito > 0) {
                
                conta.setSaldoConta(conta.getSaldoConta() + deposito);
            } else {
                throw new IllegalArgumentException("O valor mínimo deve ser R$ 1.");
            }

        } else {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

    }
}
