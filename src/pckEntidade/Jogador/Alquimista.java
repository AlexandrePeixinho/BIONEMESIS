package pckEntidade.Jogador;

import pckSistema.Texto;

//(Classe)
//(Herança)
//Classe jogavel que herda comportamentos de Jogador.
public class Alquimista extends Jogador {

    //(Método Construtor)
    //Inicializa o jogador alquimista com seus atributos base.
    public Alquimista(String nome) {
        super(nome, 110, 18, 8);
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Implementa o ataque especial do alquimista.
    @Override
    public int ataqueEspecial(int distancia) {

       Texto.narrativa("Você lançou um projétil corrosivo!");

       int dano = ataque / 2;

        return dano;
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Implementa o ataque comum do alquimista.
    @Override
    public int atacar(int distancia) {
        int dano = ataque;

        dano += random.nextInt(5);

        if (random.nextInt(100) < 20) {
            Texto.narrativa("Crítico!");
            dano *= 2;
        }

        return dano;
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Retorna a arma usada pelo alquimista.
    @Override
    public String getArma(){
        return "Manopla Alquímica";
    }
}
