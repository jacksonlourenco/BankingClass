package services;

import java.util.List;
import model.ContaBanco;

public class ServiceSaque {

    public void sacarDinheiro(List<ContaBanco> contas, double valorSaque, String cpf, String senha) {
        double juros = 1.05;
        double valorComJuros = valorSaque * juros;

        ContaBanco conta = null;

        for (ContaBanco c : contas) {
            if (c.getCpf().equals(cpf)) {
                conta = c;
                break;
            }
        }

        if (conta != null) {

            if (conta.getSenha().equals(senha)) {

                if (conta.getSaldoConta() >= valorComJuros) {

                    conta.setSaldoConta(conta.getSaldoConta() - valorComJuros);

                    System.out.println("Saque de: R$ " + valorSaque + ", realizado com sucesso!");
                    System.out.println("Saldo atual: R$ " + conta.getSaldoConta());
                } else {
                    throw new IllegalArgumentException("Saldo infuciente para saque..");
                }
            } else {
                throw new IllegalArgumentException("Senha incorreta.");
            }

        } else {
            throw new IllegalArgumentException("Conta não encontrada.");

        }

    }
}
