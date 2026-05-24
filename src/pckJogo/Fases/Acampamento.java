package pckJogo.Fases;

import pckEntidade.Jogador.Jogador;
import pckSistema.Input;
import pckSistema.Texto;
import pckSistema.Combate.GerenciadorCombate;

//(Classe)
//(Implementação de Interface)
//Classe que representa a fase do acampamento.
public class Acampamento implements Fase {

    //(Encapsulamento)
    //Atributo privado que armazena o jogador da fase.
    private Jogador jogador;

    //(Método Construtor)
    //Inicializa a fase com o jogador atual.
    public Acampamento(Jogador jogador) {
        this.jogador = jogador;
    }

        //(Sobrescrita)
        //(Polimorfismo)
        //Implementa a fase definida pela interface Fase.
        public boolean iniciar() {
        
            jogador.ganharPocao(2);
            Texto.narrativa("""
                
            Você recebeu 2 Poções de Cura!

            Você parte em uma jornada de 5 dias de caminhada árdua até a Fortaleza. Ao chegar, o que você vê é apenas desesperança e raiva. Diversas barracas e guerreiros que foram ameaçados e persuadidos a fazer parte do exército explorador dos estrangeiros.
                    """);

            
            boolean opcao = false;
            boolean falarSoldado = false;
            while (!opcao) {
                Texto.narrativa("""

                    1-Conversar com soldado
                    2-Ir para o portão
                    3-Explorar a área

                        """);
        
                int escolha = Input.lerOpcao(1, 3);
                switch(escolha) {
                    case 1:
                        Texto.narrativa("""
                            Soldado: Você ainda é jovem... não deve se lembrar de como era antes deles chegarem

                            1- Acho que não quero descobrir.
                            2- Imagino que devia ser bem melhor. 

                                """);
                        escolha = Input.lerOpcao(1,2);
                            switch(escolha){
                                case 1:
                                    Texto.narrativa("\nSoldado: ...");
                                    break;
                                case 2:
                                    Texto.narrativa("""
                                        Soldado: No começo, quando eu ainda conseguia viver de pesca, prometeram progresso. Máquinas, energia, comida em abundância...
                                        Soldado: Mas toda floresta virou fumaça. Os rios secaram para alimentar aquelas máquinas.
                                        Soldado: Hoje sobrevivemos cercados de metal.
                                        
                                                """);
                                    if (!falarSoldado) {
                                        jogador.setMoral(5);
                                        falarSoldado = true;
                                    }
                                    break;
                            }
                        break;
                    case 2:
                        Texto.narrativa("""
                            Você encontra um soldado Estrangeiro guardando o portão
                            Guarda: Ei muleque! Se tentar passar por aqui de novo isso não vai acabar bem pra você!  
                                    """);
                        break;
                    case 3:
                        opcao = true;
                        break;

                }
            }
            Texto.narrativa("""
                
                Enquanto você caminha nos arredores da Fortaleza, alguém te puxa para dentro de uma barraca.
                %s: Ei, me solta! O que você pensa que está fazendo?
                Annya: Calma, só quero conversar! Prazer, meu nome é Annya. Por que você quer entrar na Fortaleza?
                %s: Estou a procura de uma garota, acho que ela está lá dentro.
                Annya: Entendi, meu pai e minha irmã estão lá também... Depois que minha irmãzinha foi capturada, meu pai fez de tudo para salvar ela, mas aquele homem no portão pegou ele também.
                Annya: Se você derrotar aquele cara, a passagem para dentro da Fortaleza está limpa, por favor, pegue esta %s e salve minha família, é a única coisa que consigo fazer por eles na minha situação atual.
                
                1- Não ajudar
                2- Prometer ajuda

                """.formatted(jogador.getNome(), jogador.getNome(), jogador.getArma()));
            
                int escolha = Input.lerOpcao(1, 2);
                switch(escolha){
                case 1:
                    Texto.narrativa("\n"+jogador.getNome()+": Já tenho problemas demais, mas aceito a "+ jogador.getArma()+"\n");
                    opcao = true;
                    break;
                case 2:
                    Texto.narrativa("\n"+jogador.getNome()+": Tudo bem! Prometo que vou ajudá-los.\n");
                jogador.setMoral(5);
                    opcao = true;
                    break;
                }

            Texto.narrativa("""

                    Você Recebeu uma %s!

                    Você anda em direção ao portão, confiante das suas habilidades, acreditando que o treinamento na vila para combater a opressão dos invasores o prepararam para este momento.
                    Guarda: Ei garoto! Já disse, quem ousar enfrentar o Grande Guardião do Portão irá sofrer as consequências!

                    """.formatted(jogador.getArma()));
            
            if (!GerenciadorCombate.iniciar(jogador, "Guardião", 120, 19, 6, 2)) {
                return false;
            }

            return true;
    }

}
