package pckEntidade.Jogador;

import pckSistema.Texto;

//(Classe)
//(Herança)
//Classe jogavel que herda comportamentos de Jogador.
public class Guerreiro extends Jogador {

    //(Método Construtor)
    //Inicializa o jogador guerreiro com seus atributos base.
    public Guerreiro(String nome) {
        super(nome, 140, 22, 12);
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Implementa o ataque especial do guerreiro.
    @Override
    public int ataqueEspecial(int distancia) {

        if (distancia > 1) {
           Texto.narrativa("Muito longe para ataque pesado!");
            return 0;
        }

        int dano = ataque * 2;
        Texto.narrativa("Golpe pesado!");

        return dano;
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Implementa o ataque comum do guerreiro.
    public int atacar(int distancia) {
        int dano = ataque;

        dano += random.nextInt(5);

        if (distancia >= 2) { 
            dano /= 2; 
            Texto.narrativa("Você atacou de longe."); 
        }

        if (random.nextInt(100) < 20) {
            Texto.narrativa("Crítico!");
            dano *= 2;
        }

        return dano;
    }
    
    //(Sobrescrita)
    //(Polimorfismo)
    //Retorna a arma usada pelo guerreiro.
    @Override
    public String getArma(){
        return "Espada";
    }
}
