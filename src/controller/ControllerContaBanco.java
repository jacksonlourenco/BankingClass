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
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine(); // Consumir o ENTER

            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                    /* 
                case 2:
                    sacar();
                    break;
                case 3:
                    depositar();
                    break;
                case 4:
                    transferir();
                    break;
                    */
                case 5:
                    alterarSenha();
                    break;
                    
                case 6:
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
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Saldo em conta inicial: ");
        double saldoConta = sc.nextDouble();
        sc.nextLine();

        //Criamos um Objeto para conseguir adicioná-lo ao ArrayList.
        ContaBanco novaConta = new ContaBanco(nome, saldoConta, senha);

        //Aqui adicionamos no ArrayList.
        contas.add(novaConta);

        System.out.println("Conta criada com sucesso!");

    }

    public void alterarSenha() {
        System.out.print("Digite o nome da conta que deseja alterar a senha: ");
        String nome = sc.nextLine();

        //Instanciamos uma conta como Nula.
        ContaBanco conta = null;
        for(ContaBanco c : contas) {
            //Procuramos no ArrayList se existe a conta, e caso sim, salvamos o nome.
            if (c.getNome().equalsIgnoreCase(nome)) {
                conta = c;
                break;
            }
        }

        if (conta != null) {
            System.out.print("Digite a nova senha: ");
            String novaSenha = sc.nextLine();

            try {
                serviceConta.alterarSenha(conta, novaSenha);
                System.out.println("Senha alterada com sucesso!");
                
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao alterar senha: " + e.getMessage());
            }
        } else {
            System.out.println("Conta não encontrada!");
        }

    }
}
