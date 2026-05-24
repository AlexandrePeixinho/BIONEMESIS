package pckJogo.Fases;

import pckEntidade.Jogador.Jogador;
import pckSistema.Input;
import pckSistema.Texto;
import pckSistema.Combate.GerenciadorCombate;

//(Classe)
//(Implementação de Interface)
//Classe que representa a fase da prisao.
public class Prisao implements Fase {

    //(Encapsulamento)
    //Atributo que armazena o jogador da fase.
    private Jogador jogador;

    //(Método Construtor)
    //Inicializa a fase com o jogador atual.
    public Prisao(Jogador jogador){

        this.jogador = jogador;
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Implementa a fase definida pela interface Fase.
    public boolean iniciar(){
        Texto.narrativa("""
                Você pega a chave do General e caminha até a escada do outro lado do salão, por sorte os soldados não escutaram a sua batalha na sala ao lado devido ao barulho da maquinaria e conversas.
                A chave velha quebra ao destrancar o portão, abrindo caminho a uma escadaria escura, iluminada por algumas tochas.
                Ao descer, um guarda que cuidava do local lhe diz
                Vigia: O chefe te enviou, certo? Já que não está com ele, responda a pergunta, ele deve ter te dito a resposta de acesso.

                Quem irá libertar-nos?
                1- Thaedus
                2- Kregg
                3- Por que alguém ajudaria vocês?

                """);

        boolean vigiaMorto = false;

        int escolha = Input.lerOpcao(1, 3);
            switch(escolha) {
                case 1:
                    Texto.narrativa("Um soldado apoiador desse destruidor de planetas!? Quem é você?");
                    vigiaMorto = true;
                    if (!GerenciadorCombate.iniciar(jogador,"Vigia", 160, 30, 9, 3)) {
                        return false;
                    }
                    break;
                case 2:
                    Texto.narrativa("Certo, pode ir! Não vamos deixar a ideologia exploradora de Thaedus dominar nosso novo lar também!");
                    break;
                case 3:
                    Texto.narrativa("");
                    jogador.setMoral(5);
                    vigiaMorto = true;
                    if (!GerenciadorCombate.iniciar(jogador,"Vigia", 160, 30, 9, 3)) {
                        return false;
                    }
                    break;
        }

        Texto.narrativa("""

                Você anda com cautela em um corredor silencioso repleto de celas, em sua maioria vazias. De repente alguém lhe chama, um homem na casa dos 50 anos, magro e com cabelo branco, acompanhado de uma garotinha, lhe diz
                Ivhan: Ei, me solte, por favor, sei que você não é como algum deles! Você está aqui para nos ajudar, né?
                %s": Senhor, o que você fez para estar aqui?
                Ivhan: Eles tinham pego minha pequena Mei, vim aqui para salvá-la, mas me pegaram também, agora a Annya está sozinha lá fora...
                %s: Minha chave está quebrada, mas acredito que sei quem pode consertá-la. Por acaso você viu uma garota? Tem olhos castanhos e cabelos dourados.
                Ivhan: Não me lembro, mas se ela está aqui, deve estar na Ala de Prisioneiros, depois daquela porta. Aparentemente eles só mantém aqui quem será usado como mais um soldado do exército deles, igual todos aqueles lá fora.
                Você vai até a porta, mas certamente não consegue abrir, pelo nível de detalhes daquela fechadura, apenas uma chave especial conseguiria destrancá-la.
                """.formatted(jogador.getNome(), jogador.getNome()));

        if (vigiaMorto == true) {
            Texto.narrativa("Você corre para a escada, porém um guarda, parado em frente a sua passagem lhe diz\n\nGuarda: O que você fez com o General e com o vigia!? Sua sorte é que os outros já estão em missão, mas eu irei vingá-los!");
            if (!GerenciadorCombate.iniciar(jogador, "Guarda", 110, 16, 6, 3)) {
                return false;
            }
        }
        else if (vigiaMorto == false) {
            Texto.narrativa("Você corre para a escada, porém o vigia está acompanhado de outro guarda, que grita\n\nGuarda: O que você fez com o General!? Você irá sofrer as consequências!");
            if (!GerenciadorCombate.iniciar(jogador, "Guarda", 110, 16, 6, 3)) {
                return false;
            }
            Texto.narrativa("Vigia: Você é forte, mas infelizmente não poderei deixá-lo passar daqui, não precisarei nem chamar os outros de sua missão.");
            if (!GerenciadorCombate.iniciar(jogador,"Vigia", 160, 30, 9, 3)) {
                return false;
            }
        }

        return true;
    }

}
