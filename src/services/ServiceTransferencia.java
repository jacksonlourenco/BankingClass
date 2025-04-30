package services;

import java.util.List;
import model.ContaBanco;

public class ServiceTransferencia {

    // Solicitamos os parametros (List, array das contas cadastradas)
    // Uma conta remente
    // O Cpf do destinatário
    // E o valor de transferencia.
    public void transferirDinheiro(List<ContaBanco> contas, String cpfRemetente, String cpfDestino, double valor,
            String senhaRemente) {

        // O valor precisa um numero inteiro, maior do que 0.
        if (valor > 0) {
            // Verificamos se o remetente possui o saldo suficiente
            ContaBanco remetente = null;
            for (ContaBanco c : contas) {
                if (c.getCpf().equals(cpfRemetente)) {
                    remetente = c;
                    break;
                }
            }

            if (remetente != null) {

                if (remetente.getSaldoConta() >= valor) {

                    // Instanciamos a conta do destinatario, junto ao cpfDestino que solicitamos.
                    ContaBanco destinatario = null;

                    for (ContaBanco c : contas) {
                        if (c.getCpf().equals(cpfDestino)) {
                            destinatario = c;
                            break;
                        }
                    }

                    // Caso encontre a conta, começamos as validações para transferência.
                    if (destinatario != null) {
                        if (!destinatario.getCpf().equals(remetente.getCpf())) {
                            if (remetente.getSenha().equals(senhaRemente)) {
                                remetente.setSaldoConta(remetente.getSaldoConta() - valor);
                                destinatario.setSaldoConta(destinatario.getSaldoConta() + valor);
                            }
                        }
                    }

                }
            }

        }
    }
}
