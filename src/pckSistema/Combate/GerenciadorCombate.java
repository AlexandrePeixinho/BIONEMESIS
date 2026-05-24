package pckSistema.Combate;

import pckEntidade.Jogador.*;
import pckEntidade.Inimigo.*;

//(Classe)
//Classe responsavel por criar inimigos e iniciar combates.
public class GerenciadorCombate {

    public static boolean iniciar(Jogador jogador, String nome, int vida, int dano, int defesa, int dropPocao) {

        Inimigo inimigo = new Inimigo(nome, vida, dano, defesa, dropPocao);

        Combate combate = new Combate(jogador, inimigo);

        return combate.iniciarBatalha();

    }
}
