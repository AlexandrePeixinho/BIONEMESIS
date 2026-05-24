package pckSistema;

import java.util.Scanner;

//(Classe)
//Classe responsavel pela leitura de entradas do usuario.
public class Input {

    //(Atributo Estático)
    //Scanner compartilhado para leitura de dados.
    public static Scanner sc = new Scanner(System.in);

    //Realiza a validação da opção digitada pelo usuário.
    public static int lerInt() {
        
        while (!sc.hasNextInt()) {
            System.out.println("Digite apenas números!");
            sc.next();
        }

        return sc.nextInt();
    }

    //Verifica se o usuário escolheu uma das opções;
    public static int lerOpcao(int min, int max) {

        int opcao;

        while (true) {

            opcao = lerInt();

            if (opcao >= min && opcao <= max) {
                return opcao;
            }

            System.out.println("Escolha inválida!");
        }
    }
}
