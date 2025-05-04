package model;

public final class ContaBanco {

    private String nome;
    private double saldoConta;
    private String senha;
    private String cpf;

    public ContaBanco(String nome, String cpf, double saldoConta, String senha) {
        setNome(nome);
        setCpf(cpf);
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
        String regexSenha = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (!senha.matches(regexSenha)) {
            throw new IllegalArgumentException(
                    "Senha fraca! Ela deve conter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial.");
        }

        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        // Aceita CPF formatado ou somente números
        String regexValidaCpf = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$";

        if (!cpf.matches(regexValidaCpf)) {
            throw new IllegalArgumentException("CPF inválido. Use 11 números ou no formato xxx.xxx.xxx-xx.");
        }

        // Remove qualquer caractere que não seja número
        String cpfNumerico = cpf.replaceAll("\\D", "");

        this.cpf = cpfNumerico;
    }

}
