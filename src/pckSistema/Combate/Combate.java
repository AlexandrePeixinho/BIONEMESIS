package pckSistema.Combate;

import java.util.*;
import pckEntidade.Inimigo.Inimigo;
import pckEntidade.Jogador.*;
import pckSistema.Input;
import pckSistema.Texto;

//(Classe)
//Classe responsavel por controlar uma batalha entre jogador e inimigo.
public class Combate {

    //(Encapsulamento)
    //Atributos privados que armazenam o estado do combate.
    private Jogador jogador;
    private Inimigo inimigo;
    private int distancia = 1;
    private int bonusdefesa;
    private int ibonusdefesa;
    private int rodadaDebuff;
    private int debuffAtaqueInimigo;

    //(Encapsulamento)
    //(Atributo Estático)
    //(Atributo Final)
    //Gerador de numeros aleatorios usado no combate.
    private static final Random random = new Random();

    //(Método Construtor)
    //Inicializa o combate com o jogador e o inimigo.
    public Combate(Jogador jogador, Inimigo inimigo) {
        this.jogador = jogador;
        this.inimigo = inimigo;
    }

    //Método da batalha do jogo que retorna um valor booleano para a fase, parando a fase caso jogador morra.
    public boolean iniciarBatalha() {

        int rodada = 0;
        int rodadaPocao = 0;
        int rodadaEspecial = 0;

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            
            rodada ++;

            Texto.narrativa("""

                %s VIDA: %d
                %s VIDA: %d
                DISTÂNCIA: 
                
                    """.formatted(jogador.getNome(), jogador.getVida(), inimigo.getNome(), inimigo.getVida(), distancia));
            int acoes = 2;

            //Ciclo de turnos do jogador e inimigo até um dos dois serem derrotados.
            while (acoes > 0) {

                Texto.narrativa("\nAções restantes: " + acoes+"\n");

                Texto.narrativa("""

                        1 - Atacar (1)
                        2 - Defender (1)
                        3 - Aproximar (1)
                        4 - Distanciar (1)
                        5 - Tomar Poção (1)
                        6 - Habilidade Especial (2)
                        
                        """);

                int escolha = Input.lerOpcao(1, 6);

                switch (escolha) {

                    case 1:

                        int dano = jogador.atacar(distancia) - ((inimigo.getDefesa() + ibonusdefesa)/3);
                        if(dano < 0) {
                            dano = 0;
                        }
                        int danoRecebido = inimigo.receberDano(dano);
                        Texto.narrativa("\nVocê causou " + danoRecebido + " de dano.");
                        acoes --;
                        break;

                    case 2:

                        bonusdefesa = jogador.defender();
                        acoes --;
                        break;

                    case 3:
                    
                        if (jogador.aproximar(distancia)){
                            acoes --;
                            distancia --;
                        }
                        break;
                        
                    case 4:

                        
                        if (jogador.distanciar(distancia)){
                             acoes --;
                             distancia ++;
                        }
                        break;

                    case 5:

                        if(rodada <= rodadaPocao) {
                            Texto.narrativa("\nPoção disponível em "+(rodadaPocao - rodada)+" rodadas.");
                            break;
                        }

                        else {
                            jogador.usarPocao();
                            Texto.narrativa("\nVocê possui "+jogador.getPocoes()+" poções de cura.");
                            rodadaPocao = rodada + 2;
                            acoes --;
                            break;
                        }

                    case 6:

                       if (acoes < 2) {
                            Texto.narrativa("\nAções insuficientes!");
                            break;
                        }

                        if (rodada <= rodadaEspecial) {
                            Texto.narrativa("\nHabilidade disponível em "+ (rodadaEspecial - rodada)+ " rodadas.");
                            break;
                        }

                        if(jogador instanceof Alquimista) {
                            debuffAtaqueInimigo = inimigo.getAtaque() / 2;
                            rodadaDebuff = 2;
                            Texto.narrativa("\nO ataque do inimigo foi reduzido por 2 rodadas!");
                        }

                        else {
                            int ult = jogador.ataqueEspecial(distancia) - ((inimigo.getDefesa() + ibonusdefesa)/3);

                        if (ult < 0) {
                            ult = 0;
                        }

                        int danoRecebidoUlt = inimigo.receberDano(ult);
                        Texto.narrativa("\nVocê causou "+danoRecebidoUlt+ " de dano com a habilidade especial.");
                        }

                        rodadaEspecial = rodada + 3;
                        acoes -= 2;
                        break;
                }

                if (!inimigo.estaVivo()) {
                    break;
                }
            }

            if (!inimigo.estaVivo()) {
                break;
            }

            ibonusdefesa = 0;
            turnoInimigo();
            bonusdefesa = 0;

            if (rodadaDebuff > 0) {
                rodadaDebuff --;
            
                if (rodadaDebuff == 0) {
                    debuffAtaqueInimigo = 0;
                    Texto.narrativa("\n"+inimigo.getNome()+" recuperou suas forças.");
                }
            }   
        }
         if (jogador.estaVivo()) {
            jogador.recuperarVida();
            jogador.ganharPocao(inimigo.getDropPocao());
            Texto.narrativa("\nVocê venceu a batalha e ganhou "+inimigo.getDropPocao()+" poções!\n");
        } 
        else {
            Texto.titulo("\nVocê foi derrotado.");
        }

        return jogador.estaVivo();
    }

    //Condições para o inimigo realizar suas ações durante seu turno.
    private void turnoInimigo() {

        Texto.narrativa("\nTurno do inimigo.\n");

        int iacoes = 2;

        while(iacoes > 0) {

            if (inimigo.getVida() <= 30 && random.nextInt(100) < 20 && iacoes > 0) {
                inimigo.usarPocao();
                Texto.narrativa("\n"+inimigo.getNome()+" usou uma poção!");
                iacoes = 0;
        }
             
            if (distancia > 1 && random.nextInt(100) <= 33 && iacoes > 0){
                Texto.narrativa("\n"+inimigo.getNome()+" se aproximou de você.");
                distancia--;
                iacoes --;
        }
            if (iacoes > 0) {
                int dano = (inimigo.atacar(distancia) - debuffAtaqueInimigo) - ((jogador.getDefesa() + bonusdefesa)/3);
                if(dano < 0) {
                    dano = 0;
                }
                jogador.receberDano(dano);
                Texto.narrativa("\n"+inimigo.getNome()+" causou "+dano+" de dano.");
                iacoes --;
            }

            if(iacoes > 0) {
                int chance = random.nextInt(100);
                if (chance < 30){
                    int dano = (inimigo.atacar(distancia) - debuffAtaqueInimigo) - ((jogador.getDefesa() + bonusdefesa)/3);
                    if(dano < 0) {
                        dano = 0;
                    }   
                    jogador.receberDano(dano);
                    Texto.narrativa("\n"+inimigo.getNome() +" atacou novamente, causando "+dano+" de dano.");
                    iacoes --;
                }
                else if (chance > 30 && chance < 60 ) {
                    ibonusdefesa = inimigo.defender();
                    iacoes--;
                }
                else{
                    iacoes--;
                }
            }
            }
    }
}
