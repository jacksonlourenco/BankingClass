package services;

import java.util.List;
import model.ContaBanco;

public class ServiceConta {

    public void alterarSenha(List<ContaBanco> contas, String novaSenha, String cpf) {
        String regexSenha = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        ContaBanco conta = contas.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Conta não existe."));

        if (!novaSenha.matches(regexSenha)) {
            throw new IllegalArgumentException(
                    "Senha fraca! Ela deve conter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial.");
        }

        if (novaSenha.equals(conta.getSenha())) {
            throw new IllegalArgumentException("A nova senha não pode ser igual à atual.");
        }

        conta.setSenha(novaSenha);
    }

    public void buscarPorCpf(List<ContaBanco> contas, String cpf, String senha) { 

        ContaBanco conta = null;

        for (ContaBanco c : contas) {
            if (c.getCpf().equals(cpf)) {
                conta = c;
                break;
            }
        }

        if (conta != null) {
            if (conta.getSenha().equals(senha)) {
                System.out.println("Nome: " + conta.getNome()
                        + "\nSaldo: R$ " + conta.getSaldoConta());
            } else {
                throw new IllegalArgumentException("Senha inválida.");
            }
        } else {
            throw new IllegalArgumentException("Conta não encontrada.");
        }

    }

    public boolean cpfJaCadastrado(List<ContaBanco> contas, String cpf) {
        return contas.stream().anyMatch(c -> c.getCpf().equals(cpf));
    }

}
