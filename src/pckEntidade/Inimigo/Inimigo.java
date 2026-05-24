package pckEntidade.Inimigo;

import java.util.Random;
import pckSistema.Texto;

//(Classe)
//Representa um inimigo enfrentado em combate.
public class Inimigo {

    //(Encapsulamento)
    //Atributos privados que armazenam o estado do inimigo.
    private String nome;
    private int vida;
    private int ataque;
    private int defesa;
    private int dropPocao;

    //(Atributo Estático)
    //(Atributo Final)
    //Gerador de numeros aleatorios usado ao decorrer da classe.
    private static final Random random = new Random();


    //(Método Construtor)
    //Inicializa os atributos principais do inimigo.
    public Inimigo(String nome, int vida, int ataque, int defesa, int dropPocao) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.dropPocao = dropPocao;
    }
    //(Encapsulamento - Getters)

    //Retorna a defesa do inimigo.
    public int getDefesa() {
        return defesa;
    }

    //Retorna o ataque do inimigo.
    public int getAtaque() {
        return ataque;
    }

    //Retorna o nome do inimigo.
    public String getNome() {
        return nome;
    }

    //Retorna a vida atual do inimigo.
    public int getVida(){
        return vida;
    }

    public int usarPocao(){
        int cura = vida / 5;
        vida += cura;
        return vida;
    }
    
    public int atacar(int distancia) {

        int dano = ataque;

        if (distancia >= 2) { 
            dano -= 5; 
            Texto.narrativa("\n"+getNome() + " atacou de longe"); 
        }

        dano += random.nextInt(5);

        if (random.nextInt(100) <= 15) {
            Texto.narrativa("Crítico do inimigo!");
            dano *= 2;
        }

        return dano;
    }

    public int defender() {

        int chance = random.nextInt(100);

        if(chance > 80) {
            Texto.narrativa("\n"+getNome()+" defendeu em cheio!\n");
            return 8;
        }

        else if(chance > 10) {
            Texto.narrativa("\n"+getNome()+" defendeu.");
            return 5;
        }

        else {
            Texto.narrativa("\n"+getNome()+" errou a defesa.\n");
            return -2;
        }
    }

    public int receberDano(int dano) {

        if (dano < 0){
            dano = 0;
        }
        vida -= dano;
        return dano;
    }

    //Analisa se o inimigo esta vivo
    public boolean estaVivo() {
        return vida > 0;
    }

    public int getDropPocao() {
        return dropPocao;
    }
}
