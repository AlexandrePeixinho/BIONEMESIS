package pckEntidade.Jogador;

import pckSistema.Texto;

//(Classe)
//(Herança)
//Classe jogavel que herda comportamentos de Jogador.
public class Arqueiro extends Jogador {

    //(Método Construtor)
    //Inicializa o jogador arqueiro com seus atributos base.
    public Arqueiro(String nome) {
        super(nome, 100, 30, 6);
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Implementa o ataque comum do arqueiro.
    @Override
    public int atacar(int distancia) {

        int dano = ataque;

        if (distancia >= 2) {
            dano += 5;
        }

        if (random.nextInt(100) < 35) {
            Texto.narrativa("Crítico do arqueiro!");
            dano *= 2;
        }

        return dano;
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Implementa o ataque especial do arqueiro.
    @Override
    public int ataqueEspecial(int distancia) {

        int dano = ataque;

        if (distancia < 2) {
            Texto.narrativa("Muito perto!");
            return dano;
        }

        dano = dano * 2 + 5;
        Texto.narrativa("Disparo preciso!");

        return dano;
    }
    
    //(Sobrescrita)
    //(Polimorfismo)
    //Retorna a arma usada pelo arqueiro.
    @Override
    public String getArma(){
        return "Flecha e Arco";
    }
}
