package pckEntidade.Jogador;

import java.util.*;
import pckSistema.Texto;

//(Classe Abstrata)
//Classe base para todas as classes jogaveis do sistema.
public abstract class Jogador {

    //(Encapsulamento)
    //Atributos protegidos que armazenam o estado do jogador.
    protected String nome;
    protected int vida;
    protected int vidaMax;
    protected int ataque;
    protected int defesa;
    protected int pocoes = 0;
    protected int moral = 0;
    protected boolean chave;

    //(Encapsulamento)
    //(Atributo Estático)
    //(Atributo Final)
    //Gerador de numeros aleatorios compartilhado pela classe.
    protected static final Random random = new Random();

    //(Método Construtor)
    //Inicializa os atributos principais do jogador.
    public Jogador(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.vidaMax = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    //(Encapsulamento - Setters)

    //Altera a moral do jogador.
    public void setMoral(int valor){
        moral += valor;
    }

    //Diz se o jogador possui a chave.
    public void setChave(boolean valor){
        chave = valor;
    }

    //(Encapsulamento - Getters)

    //Retorna a vida atual do jogador.
    public int getVida() {
        return vida;
    }

    //Retorna a quantidade de pocoes do jogador.
    public int getPocoes() {
        return pocoes;
    }

    //Retorna o estado da chave do jogador.
    public boolean getChave(){
        return chave;
    }

    //Retorna a moral do jogador.
    public int getMoral(){
        return moral;
    }

    //Retorna o nome do jogador.
    public String getNome() {
        return nome;
    }

    //Retorna a defesa do jogador.
    public int getDefesa(){
        return defesa;
    }

    //(Métodos Abstratos)

    //Define a arma conforme a classe concreta.
    public abstract String getArma();

    //Define o ataque especial conforme a classe concreta.
    public abstract int ataqueEspecial(int distancia);

    //Define o ataque conforme a classe concreta.
    public abstract int atacar(int distancia);


    public boolean estaVivo() {
        return vida > 0;
    }

    public int recuperarVida() {
        return vida = vidaMax;
    }

    public int defender() {

        int chance = random.nextInt(100);

        int bonusdefesa;

        if(chance > 80) {
            bonusdefesa = 8;
        }

        else if(chance > 10) {
            bonusdefesa = 5;
        }

        else {
            bonusdefesa = -2;
        }

        Texto.narrativa("Você está em estado de defesa.");
        return bonusdefesa;

    }

    public boolean aproximar(int distancia) {

        if(distancia > 1) {
            Texto.narrativa("Você se aproximou.");
            return true;
        }

        else {
            Texto.narrativa("Você já está muito perto do inimigo!");
            return false;
        }

    }

    public boolean distanciar(int distancia) {

        if(distancia < 5) {
            Texto.narrativa("Você se distanciou.");
            return true;
        }

        else {
            Texto.narrativa("Você já está muito longe do inimigo!");
            return false;
        }
    }

    public void receberDano(int dano) {

        if (dano < 0)
            dano = 0;

        vida -= dano;
    }

    public void usarPocao() {

        if (pocoes <= 0) {
            Texto.narrativa("Sem poções!");
            return;
        }

        if (vida == vidaMax) {
            Texto.narrativa("Vida cheia!");
            return;
        }

        int cura = 20;

        vida += cura;

        if (vida > vidaMax)
            vida = vidaMax;

        pocoes--;

        Texto.narrativa("Curou " + cura + " de vida!");
    }

    public void ganharPocao(int qtd) {
        pocoes += qtd;
    }

    public void melhorarAtaque(int valor) {
        ataque += valor;
        Texto.narrativa("Ataque aumentado!\n");
    }

    public void melhorarDefesa(int valor) {
        defesa += valor;
        Texto.narrativa("Defesa aumentada!\n");
    }

}
