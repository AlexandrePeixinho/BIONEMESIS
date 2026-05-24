package pckJogo.Fases;

import pckEntidade.Jogador.Jogador;
import pckSistema.Input;
import pckSistema.Texto;
import pckSistema.Combate.GerenciadorCombate;

//(Classe)
//(Implementação de Interface)
//Classe que representa a fase da fortaleza.
public class Fortaleza implements Fase {

    //(Encapsulamento)
    //Atributo privado que armazena o jogador da fase.
    private Jogador jogador;

    //(Método Construtor)
    //Inicializa a fase com o jogador atual.
    public Fortaleza(Jogador jogador) {
        this.jogador = jogador;
    }

        //(Sobrescrita)
        //(Polimorfismo)
        //Implementa a fase definida pela interface Fase.
        public boolean iniciar() {
        Texto.narrativa("""
            Agora você está dentro da Fortaleza, um ambiente quente, várias engrenagens em funcionamento por todo lado, o barulho de máquinas em funcionamento ecoam pelos corredores, parece até um portal para outro mundo.
            Você anda em direção ao barulho, procurando por pistas, até encontrar um salão completamente vago. A sua esquerda, uma escadaria sendo protegida por um guarda adormecido em um degrau, à frente, uma porta estranha, da onde vinha sons de várias pessoas conversando em uma língua que desconhecia, e à direita, um portão aberto que abria passagem para o som de maquinarias e um vapor quente.
            
            Para onde você deseja ir?
            1- Escadaria
            2- Porta estranha
            3- Portão aberto

            """);

        boolean opcao = false;
       
        while(!opcao){
            int escolha = Input.lerOpcao(1, 3);
            switch(escolha) {
                case 1:
                    Texto.narrativa(jogador.getNome()+": Este cara parece forte, melhor não mexer com ele.\n");
                    break;
                case 2:
                    Texto.narrativa(jogador.getNome()+": Parece que os soldados estrangeiros ficam aqui, melhor não entrar aqui.\n");
                    break;
                case 3:
                    Texto.narrativa(jogador.getNome()+": Quantas máquinas, parece que é aqui que eles extraem o nosso metal...\n");
                    opcao = true;
                    break;

            }
        }

        Texto.narrativa("""
                Ao entrar na sala, uma linha de produção refina o metal, fumaça escura é despejada pela tubulação. Um dos operários te fala.
                Operário: Quanto mais metal produzimos... mais rápido aquela coisa consome a floresta.

                1- Então por que continuam?
                2- Infelizmente produção é necessária, só tivemos o azar de sermos os prejudicados.

                """);

        int escolha = Input.lerOpcao(1, 2);
        switch(escolha){
            case 1:
                Texto.narrativa("Operário: Por que fome mata mais rápido que fumaça.");
                jogador.setMoral(5);
                break;
            case 2: 
                Texto.narrativa("Operário: Sempre tem outro jeito.");
        }
        Texto.narrativa("""

                Uma parte do metal é direcionado para uma sala trancada, parecendo até uma cela. Você se esgueira sobre uma pequena passagem para entrar, encontrando um ferreiro.
                Ferreiro: O que você está fazendo aqui garoto! Quem é você?
                %s: Digo o mesmo, por que você está trancado em uma sala quente como essa?
                Ferreiro: Você acha que estou aqui por que quero? Aqueles caras me pegaram e me mantém aqui há uns 5 anos, aparentemente minha fama como melhor ferreiro da história não me serviu para muito...
                %s: Por acaso ouviu falar de alguma prisão por aqui?
                Ferreiro: Ouvi falar sobre celas na ala dos soldados, talvez possa descobrir algo lá, pegue esta armadura e se infiltre, talvez descubra algo, mas cuidado com Thaedus, aquele homem é maquiavélico... Ah, quase me esqueço, no seu capacete possui um tradutor, assim será possível se comunicar na língua deles, é assim que eles nos entendem.
                %s: Muito obrigado!
                %s: Mais uma coisa, hoje cedo peguei esses pedaços de metal com eles, acha que te pode ser útil?
                Ferreiro: Claro! Com isso posso melhorar sua %s e armadura, caso ache mais, traga para mim que faço ainda mais melhorias.

                Melhorar
                1- %s
                2- Armadura

                """.formatted(jogador.getNome(), jogador.getNome(), jogador.getNome(), jogador.getNome(), jogador.getArma(), jogador.getArma()));
  
        Texto.narrativa("");

            escolha = Input.lerOpcao(1, 2);
            switch (escolha) {
                case 1:
                    jogador.melhorarAtaque(5);
                    break;
                case 2:
                    jogador.melhorarDefesa(5);
                    break;
            }
        
        Texto.narrativa("""

            Ferreiro: Mais uma coisa garoto.
            Ferreiro: Todo dia em que mexo com esse metal tenho mais certeza de que ele é algo mais. Quanto mais eles extraem... Mais a terra morre. Lembro no começo, primeiro os animais começaram a desaparecer, depois os rios, o céu começa a perder a cor, como se expressase sua tristeza.
            Ferreiro: Alguns deles dizem que é um progresso, mas para mim, para nós, isso é uma sentença. Já sou velho, não posso mais ajudar, mas você pode, salve nossa terra!
            Você sai da área de 'trabalho' do ferreiro, enquanto um guarda te olha confuso
            Guarda: Sabia que você pode destrancar a porta, né?
            
            Você
            1- Ataca o guarda
            2- Distrai o guarda  

            """);
        
        Texto.narrativa("");
        
            escolha = Input.lerOpcao(1, 2);
            switch (escolha) {
                case 1:
                    Texto.narrativa("Você saca a sua "+jogador.getArma()+", iniciando uma batalha.");
                    if (!GerenciadorCombate.iniciar(jogador, "Guarda", 120, 17, 5, 2)){
                        return false;
                    }
                    break;
                case 2:
                    Texto.narrativa("Acabei perdendo a minha chave, e precisava desse velhote para consertar minha armadura.");
                    jogador.setMoral(5);
                    break;
            }
        
        Texto.narrativa("""
                Você entra na Ala dos Guardas, um salão repleto de Estrangeiros armados até os dentes, a última coisa que quer agora é arranjar mais uma briga. Você se aproxima de um grupo e diz
                %s: O chefe me disse para interrogar um prisioneiro, um Nativo que dizia que nós pegamos a filha dele, algo assim...
                Estrangeiro: Fale com o chefe, só ele tem a chave do portão da Ala dos Prisioneiros, ele está na Sala de Treinamento. Só ele para estar treinando essa hora.
                Ao entrar na sala, um homem alto, forte, inferindo socos em um saco de pancada que parecem até o golpe de uma marreta, vira e lhe diz
                General: O que foi agora? Não vê que está me atrapalhando?
                Você saca sua %s e diz
                %s: Vou pedir apenas uma vez, me dê a chave da Ala dos Prisioneiros
                Ele pega sua espada encostada no canto e anda em sua direção
                General: Quem você acha que é para falar assim com seu superior, acho que o último não serviu de recado
                Uma batalha se inicia!
                """.formatted(jogador.getNome(), jogador.getArma(), jogador.getNome()));
        if (!GerenciadorCombate.iniciar(jogador, "General", 165, 26, 12, 3)){
            return false;
        }
    
        return true;
    }

}
