package controller;

import java.util.ArrayList;
import java.util.Scanner;
import model.ContaBanco;
import services.ServiceConta;
import services.ServiceDeposito;
import services.ServiceSaque;
import services.ServiceTransferencia;

public class ControllerContaBanco {

    private Scanner sc = new Scanner(System.in);
    private ArrayList<ContaBanco> contas = new ArrayList<>();
    private ServiceConta serviceConta = new ServiceConta();
    private ServiceSaque serviceSaque = new ServiceSaque();
    private ServiceDeposito serviceDeposito = new ServiceDeposito();
    private ServiceTransferencia serviceTransferencia = new ServiceTransferencia();

    public void iniciarSistema() {
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Criar Conta");
            System.out.println("2. Sacar");
            System.out.println("3. Depositar");
            System.out.println("4. Transferir");
            System.out.println("5. Alterar Senha");
            System.out.println("6. Mostrar conta");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine(); // Consumir o ENTER
            System.out.println("=====================\n");

            switch (opcao) {
                case 1:
                    criarConta();
                    break;

                case 2:
                    sacar();
                    break;

                case 3:
                    depositar();
                    break;

                case 4:
                    transferir();
                    break;

                case 5:
                    alterarSenha();
                    break;

                case 6:
                    buscarCpf();
                    break;

                case 7:
                    rodando = false;
                    System.out.println("Sistema encerrado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }

    public void criarConta() {

        System.out.println("Para criar sua conta, insira: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Insira o  seu CPF: ");
        String cpf = sc.nextLine();

        // Criamos um Objeto para conseguir adicioná-lo ao ArrayList.
        if (serviceConta.cpfJaCadastrado(contas, cpf)) {
            System.out.println("CPF já cadastrado.");
        } else {
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            System.out.print("Saldo em conta inicial: ");
            double saldoConta = sc.nextDouble();
            sc.nextLine();
            ContaBanco novaConta = new ContaBanco(nome, cpf, saldoConta, senha);

            // Aqui adicionamos no ArrayList.
            contas.add(novaConta);

            System.out.println("Conta criada com sucesso!");
        }

    }

    public void alterarSenha() {
        System.out.print("Digite o seu CPF (Somente números): ");
        String cpf = sc.nextLine();

        System.out.print("Digite a nova senha: ");
        String novaSenha = sc.nextLine();

        try {
            serviceConta.alterarSenha(contas, novaSenha, cpf);
            System.out.println("Senha alterada com sucesso!");

            // Caso não dê certo, disparamos a mensagem descrita no service.
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao alterar senha: " + e.getMessage());
        }

    }

    public void depositar() {
        System.out.print("Digite o CPF (somente números) da conta que deseja adicionar dinheiro: ");
        String cpf = sc.nextLine();

        System.out.println("Insira o valor que deseja depositar: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        try {
            serviceDeposito.depositarDinheiro(contas, valor, cpf);
            System.out.println("Valor adicionado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao realizar depósito: " + e.getMessage());
        }

    }

    public void sacar() {
        System.out.print("Digite o seu CPF(apenas números) para sacar o dinheiro: ");
        String cpf = sc.next();
        sc.nextLine();

        System.out.print("Insira o valor que deseja sacar: R$ ");
        double valor = sc.nextDouble();
        sc.nextLine();

        System.out.print("Insira a senha da conta: ");
        String senha = sc.next();
        sc.nextLine();

        try {
            serviceSaque.sacarDinheiro(contas, valor, cpf, senha);
            System.out.println("Valor sacado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao realizar saque: " + e.getMessage());
        }
    }

    public void buscarCpf() {
        System.out.print("Insira o seu CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Insira a senha: ");
        String senha = sc.nextLine();
        try {
            serviceConta.buscarPorCpf(contas, cpf, senha);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao buscar informações: " + e.getMessage());
        }
    }

    public void transferir() {
        System.out.print("Insira o seu CPF: ");
        String cpfRemetente = sc.nextLine();
        System.out.print("Insira a conta para qual deseja transferir: ");
        String cpfDestinatario = sc.nextLine();
        System.out.print("Insira o valor: R$ ");
        double valor = sc.nextDouble();
        sc.nextLine();
        System.out.print("Insira sua senha: ");
        String senha = sc.nextLine();

        serviceTransferencia.transferirDinheiro(contas, cpfRemetente, cpfDestinatario, valor, senha);
    }
}
