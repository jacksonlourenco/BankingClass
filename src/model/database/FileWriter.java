package model.database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.ContaBanco;

public class FileWriter {

    public void salvarContasEmTxt(List<ContaBanco> contas, String caminhoArquivo) throws IOException {
        FileWriter writer = new FileWriter(caminhoArquivo);

        for (ContaBanco conta : contas) {
            // Escreve nome, cpf, senha e saldo separados por vírgulas
            writer.write(conta.getNome() + "," +
                         conta.getCpf() + "," +
                         conta.getSenha() + "," +
                         conta.getSaldoConta() + "\n");
        }

        writer.close();
    }
    
}
