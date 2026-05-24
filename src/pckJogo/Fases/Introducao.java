package pckJogo.Fases;

import pckSistema.Input;
import pckSistema.Texto;
import pckEntidade.Jogador.Jogador;

//(Classe)
//(Implementação de Interface)
//Classe que representa a fase de introducao.
public class Introducao implements Fase {

    //(Encapsulamento)
    //Atributo privado que armazena o jogador da fase.
    private Jogador jogador;

    //(Método Construtor)
    //Inicializa a fase com o jogador atual.
    public Introducao(Jogador jogador){
        this.jogador = jogador;
    }
        
     //(Sobrescrita)
     //(Polimorfismo)
     //Implementa a fase definida pela interface Fase.
     public boolean iniciar() {   
        Texto.narrativa("""
                Chefe de Bordo: Senhor, a equipe do planeta Centaury XII retornou a nave mãe. Os testes realizados pelos cientistas resultaram em 96%% de compatibilidade com ALPHA-13, o melhor candidato até o momento para presenciar a sua chegada, aparentemente o planeta possui reservas de metal incrivelmente alta em seu subterrâneo!
                Thaedus: Interessante, podemos fortalecer nosso exército de uma forma que jamais imaginamos com esse metal. Envie a unidade principal a esta terra, não temos tempo a perder
                Kregg: Mas Senhor, como Conselheiro devo lhe alertar que ainda não estamos completamente preparados! Uma nova terra contém perigos para Vossa Majestade, devemos preparar...
                Thaedus: Já chega! Há 4 anos saímos de casa em busca de um novo lar a nosso povo, há 4 anos estou esperando vocês fazerem seu trabalho, e já cansei de esperar, agora, vamos em direção ao novo mundo!
            
                *CENTAURY XII*

                Crianças correm, alegres e saltitantes, em meio ao pôr do sol em uma paisagem verde vívida, repleta de animais, os quais, enquanto se alimentam do pasto denso, parecem não se importar com a presença delas. Uma sombra surge, interrompendo a brincadeira e atraindo olhares ao horizonte. Uma nave colossal se aproxima cada vez mais do solo, cessando uma ventania que espanta as criaturas do ambiente e amedronta as crianças, que correm em direção à sua vila ao leste.
            
                16 ANOS DEPOIS

                %s: Vamos logo, Mipha! Hoje a nossa tarefa é encontrar a comida para o centro, com esse tanto de crianças novas lá, precisaremos encontrar bastante.
                Mipha: Mas ainda é tão cedo...
                Vocês andam pela floresta que cerca sua vila, conversando e aproveitando um dos únicos momentos em que os dois amigos conseguem ficar a sós. A floresta é um ambiente tão belo e tranquilo, naquele dia, tranquilo até demais...
                Mipha: Que estranho, não vi nenhum animal até agora.
                %s: Devem estar fugindo da onda de poluição do oeste, acho que deveríamos entrar mais na floresta, talvez eles estejam lá.
                Mipha: Não sei, o Ancião foi bem claro quando disse para evitar aquelas áreas, os Estrangeiros estão cada vez mais perto da vila.
                Você insiste e convence Mipha, ao patrulhar a área, consequências da exploração dos Estrangeiros são notáveis, algumas árvores murchas, o ar mais pesado que o normal.
                Vocês decidem retornar, mas não percebiam que já era tarde de mais, um Esquadrão de Patrulha de Estrangeiros se aproxima, vocês correm, mas Mipha é capturada por um deles.
                ...
                Você retorna à vila
                %s: Senhora Abby, Mipha foi capturada... e foi tudo culpa minha! Preciso ir ajudá-la!
                Abby: Não garoto, é muito perigoso! Infelizmente tem coisas que não podemos evitar, mas agora temos que cuidar de quem está aqui. Não podemos perder você também.

                Você
                1- Concorda com a Sra Abby
                2- Insiste em ajudá-la

                """.formatted(jogador.getNome(), jogador.getNome(), jogador.getNome()));

        int escolha = Input.lerOpcao(1, 2);
        switch(escolha){
            case 1:
                return false;
            case 2: 
                Texto.narrativa("Abby: Já que não vou conseguir te convencer, pelo menos leve isso! E tome cuidado garoto.");
                break;
        }
        
    return true;

    }

}
