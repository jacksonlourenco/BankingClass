package services;

import model.ContaBanco;

public class ServiceConta {

    public void alterarSenha(ContaBanco conta, String novaSenha) {

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (!novaSenha.matches(regex)) {
            throw new IllegalArgumentException(
                    "Senha fraca! Ela deve conter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial.");
        } else if (novaSenha.equals(conta.getSenha())) {
            throw new IllegalArgumentException("As senhas são iguais.");
        } else {
            conta.setSenha(novaSenha);
        }
    }

}
