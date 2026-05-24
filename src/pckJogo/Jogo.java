package pckJogo;

import pckEntidade.Jogador.*;
import pckJogo.Fases.Introducao;
import pckJogo.Fases.Acampamento;
import pckJogo.Fases.Fase;
import pckJogo.Fases.Fortaleza;
import pckJogo.Fases.Prisao;
import pckJogo.Fases.Ferreiro;
import pckJogo.Fases.Final;
import pckSistema.Input;
import pckSistema.Texto;
import pckSistema.Ascii;

//(Classe)
//Classe responsavel por controlar o fluxo principal do jogo.
public class Jogo {

    //(Encapsulamento)
    //Atributo privado que armazena o jogador atual.
    private Jogador jogador;

    //Método que retorna tela de Game Over.
    public boolean telaGameOver() {

        Ascii.derrota();
        Texto.narrativa("1- Jogar novamente\n2- Sair do jogo\n");
        int escolha = Input.lerOpcao(1, 2);

        return escolha == 1;

    }

    //Método que inicia a fase e, caso o jogador morra e a fase retorne False, imprime a tela de Game Over.
    private boolean iniciarFase(Fase fase) {

    if (!fase.iniciar()) {
        boolean reiniciar = telaGameOver();
        if (reiniciar) {
            iniciar();
        }
        return false;
    }
    return true;
}

    public void iniciar() {

        Ascii.bionemesis();
        criarJogador();

        Texto.narrativa("""

            Pular introdução?
            1-Sim
            2-Não

                """);

        int escolha = Input.lerOpcao(1, 2);

        if (escolha == 2) {
            if (!iniciarFase(new Introducao(jogador))) {
                return;
            }
        }

        if (!iniciarFase(new Acampamento(jogador))) {
            return;
        }

        if (!iniciarFase(new Fortaleza(jogador))) {
            return;
        }

        if (!iniciarFase(new Prisao(jogador))) {
            return;
        }

        if(!iniciarFase(new Ferreiro(jogador))) {
            return;
        }

        if (!iniciarFase(new Final(jogador))) {
            return;
        }
    }

    //Instancia a classe do jogador dependendo de sua escolha.
    private void criarJogador() {

        Texto.narrativa("Digite seu nome:");

        String nome = Input.sc.nextLine();

        Texto.narrativa("""

                Escolha sua classe:
                1 - Guerreiro
                2 - Arqueiro
                3 - Alquimista
                
                """);

        int escolha = Input.lerOpcao(1, 3);

        switch (escolha) {

            case 1:
                jogador = new Guerreiro(nome);
                break;

            case 2:
                jogador = new Arqueiro(nome);
                break;

            case 3:
                jogador = new Alquimista(nome);
                break;
        }

        Texto.narrativa("Bem-vindo, " + jogador.getNome() + "!\n");
    }
}
