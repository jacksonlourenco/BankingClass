package model;

public final class ContaBanco {

    private String nome;
    private double saldoConta;
    private String senha;

    public ContaBanco(String nome, double saldoConta, String senha) {
        setNome(nome);
        setSaldoConta(saldoConta);
        setSenha(senha);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(double saldoConta) {
        if (saldoConta > 0) {
            this.saldoConta = saldoConta;
        } else {
            throw new IllegalArgumentException("Saldo em conta deve ser positivo.");
        }
    }

    public void setSenha(String senha) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (!senha.matches(regex)) {
            throw new IllegalArgumentException("Senha fraca! Ela deve conter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial.");
        }

        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

}
