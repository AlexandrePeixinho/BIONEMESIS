package pckJogo.Fases;

import pckEntidade.Jogador.Jogador;
import pckSistema.Input;
import pckSistema.Texto;
import pckSistema.Combate.GerenciadorCombate;
import pckSistema.Ascii;

//(Classe)
//(Implementação de Interface)
//Classe que representa a fase final do jogo.
public class Final implements Fase {

    //(Encapsulamento)
    //Atributo que armazena o jogador da fase.
    private Jogador jogador;

    //(Método Construtor)
    //Inicializa a fase com o jogador atual.
    public Final(Jogador jogador){
        this.jogador = jogador;
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Implementa a fase definida pela interface Fase.
    public boolean iniciar(){
        Texto.narrativa("""
            Kregg: Nosso planeta não morreu em uma guerra, morreu lentamente. Arranha-céus substituíram montanhas, fumaça substituiu nuvens. E quando percebemos... não havia mais ar puro para respirar, apenas uma vasta planície cinza e sem vida.
            Thaedus: Natureza não constrói civilizações. Infelizmente sacrifícios são necessários para evolução, olha onde nós chegamos, há 300 anos atrás quem diria que seríamos capazes de alcançar outros sistemas planetários!? Você prefere árvores... ou milhões de vidas salvas?
            Kregg: Isso que fazemos é estender nosso fim, e agora de outro povo! EU não permitirei outro mundo morto. 

            Você:
            1- Defende Kregg
            2- Defende Thaedus
            3- Se opõe a ambos

                    """);

        int ending = 0;

            int escolha = Input.lerOpcao(1, 3);
            switch (escolha) {
                case 1:
                    jogador.setMoral(5);
                    ending = 1;
                    Texto.narrativa("Thaedus: Não sei quem é você garoto, mas não devia ter se metido.");
                    if (!GerenciadorCombate.iniciar(jogador, "Grande Thaedus", 240, 40, 10, 5)) {
                        return false;
                    }
                    break;
                case 2:
                    jogador.setMoral(-5);
                    ending = 2;
                    Texto.narrativa("Kregg: Um nativo defendendo as ideias dele!? Não posso deixar isso assim.");
                    if (!GerenciadorCombate.iniciar(jogador, "Tenente Kregg", 210, 40, 8, 5)) {
                        return false;
                    }
                    break;
                case 3:
                    jogador.setMoral(5);
                    ending = 3;
                    Texto.narrativa("Thaedus: Kregg, acabe com ele, depois nós terminamos...");
                    if (!GerenciadorCombate.iniciar(jogador, "Tenente Kregg", 210, 40, 8, 5)) {
                        return false;
                    }
                    Texto.narrativa("Você é realmente forte, mas não posso te deixar impune!");
                    if (!GerenciadorCombate.iniciar(jogador, "Grande Thaedus", 240, 40, 10, 5)) {
                        return false;
                    }
                    break;
            }
        
        //Condicionais que definem qual final o jogador fez baseado em sua escolha e sua moral final
        if (ending == 1) {
            Texto.narrativa("""
                Kregg: Você fez o certo garoto, as ideias dele já estavam corrompidas por riqueza e poder. Graças a você, nós teremos uma oportunidade de nos redimir.
                Você pega a chave de Thaedus e vai para o calabouço procurar Mipha.
                        """);
        }
        else if (ending == 2) {
             Texto.narrativa("""
                Thaedus: Muito obrigado garoto, ele não entendia, para salvar bilhões no futuro com a tecnologia que busco trazer, alguns sacrifícios no presente são necessários.
                Você pega a chave de Kregg e vai para o calabouço procurar Mipha.
                """);
        }
        else if (ending == 3) {
            Texto.narrativa("Você pega a chave de Thaedus e vai em direção ao calabouço, sem nem olhar para trás.");
        }

        Texto.narrativa("""
                Ao chegar no calabouço, a ânsia de cumprir sua missão e salvar sua amiga fazem a caminhada parecer infinita, você está quase lá, quando escuta as vozes de Ivhan
                Ivhan: Ei garoto, me diga que conseguiu consertar a chave para nos tirar daqui, por favor!
                """);
        if (jogador.getChave() == true) {
            Texto.narrativa("""
                %s :Claro! Não deixaria vocês aqui.
                Ivhan e Mei saem da sela, emocionados, te agradecem e correm para fora da Fortaleza, indo para o esperado encontro com Annya.
                """.formatted(jogador.getNome()));
                jogador.setMoral(15);
        }
         else if (jogador.getChave() == false) {
            Texto.narrativa("""
                %s: Desculpe senhor, mas não consegui fazer nada sobre... Me desculpe mesmo.
                Ivhan: Tudo bem, não sei por que criei tanta expectativa.
                """.formatted(jogador.getNome()));
            jogador.setMoral(-5);
        }
        Texto.narrativa("""
            Você olha para a chave de joia lapidada de Thaedus, destranca a porta e se depara com várias pessoas sendo mantidas lá, entre elas, estava Mipha, que ao te ver, corre em sua direção e te abraça.
            Mipha: Você veio me salvar!? Não acredito, você está bem?
                """);
       
        if (jogador.getMoral() < 20 && ending == 1) {
            Texto.narrativa("Com o fim do reinado de Thaedus, os Estrangeiros entram em um acordo com seu povo e abandonam sua terra. As máquinas são desligadas, o céu retoma sua cor vívida lentamente. O futuro de seu povo está a salvo!");
        }
        else if (jogador.getMoral() >= 20 && ending == 1) {
            Texto.narrativa("Sob nova direção, os Estrangeiros desligam suas máquinas, a fumaça desaparece lentamente do céu, todas as suas forças são direcionadas para ajudar os nativos ao redor do planeta, uma nova história entre os dois povos se inicia.");
        }
        else if (ending == 2) {
            Texto.narrativa("Com o fim da oposição do reinado, Thaedus intensifica seu avanço, paisagens verdes são substituídas por asfalto e concreto. A sociedade vive o ápice da tecnologia, às custas da cultura, da história de seu povo, que se desvai com o tempo.");
        }
        else if (jogador.getMoral() >= 20 && ending == 3) {
            Texto.narrativa("Sem um líder, os Estrangeiros estão sem rumo. Apesar de todo o mal causado, vingança só tornaria vocês iguais a eles. Seu povo aceita eles em suas terras, contanto que a atividade prejudicial acabe.");
        }
        else if (jogador.getMoral() < 20 && ending == 3) {
            Texto.narrativa("Sem um líder, os Estrangeiros estão sem rumo. Seu povo expulsam-os de suas terras, todo o mal causado não poderia passar impune.");
        }
        Ascii.fim();

        return true;
    
    }

}
