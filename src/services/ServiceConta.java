package services;

import java.util.List;
import model.ContaBanco;

public class ServiceConta {

    public void alterarSenha(ContaBanco conta, String novaSenha) {

        String regexSenha = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (!novaSenha.matches(regexSenha)) {
            throw new IllegalArgumentException(
                    "Senha fraca! Ela deve conter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial.");
        } else if (novaSenha.equals(conta.getSenha())) {
            throw new IllegalArgumentException("As senhas são iguais.");
        } else {
            conta.setSenha(novaSenha);
        }
    }

    public void buscarPorCpf(ContaBanco conta, String cpf) {
        String regexCpf = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";

        if (cpf.matches(regexCpf)) {
            System.out.println("Nome: " + conta.getNome() +
                    "\nSaldo: " + conta.getSaldoConta());
        } else {
            throw new IllegalArgumentException("CPF inválido ou não encontrado.");
        }
    }

    public boolean cpfJaCadastrado(List<ContaBanco> contas, String cpf) {
        return contas.stream().anyMatch(c -> c.getCpf().equals(cpf));
    }

}
