package pckJogo.Fases;

import pckEntidade.Jogador.Jogador;
import pckSistema.Input;
import pckSistema.Texto;
import pckSistema.Combate.GerenciadorCombate;

//(Classe)
//(Implementação de Interface)
//Classe que representa a fase do ferreiro.
public class Ferreiro implements Fase {

    //(Encapsulamento)
    //Atributo que armazena o jogador da fase.
    private Jogador jogador;

    //(Método Construtor)
    //Inicializa a fase com o jogador atual.
    public Ferreiro(Jogador jogador){
        this.jogador = jogador;
    }

    //(Sobrescrita)
    //(Polimorfismo)
    //Implementa a fase definida pela interface Fase.
    public boolean iniciar() {
        Texto.narrativa("""
                Com o caminho livre, você volta para falar com o ferreiro.
                %s: Estou de volta!, consegui alguns metais com uns guardas no caminho, você poderia consertar essa chave para mim, por favor?
                Ferreiro: Posso sim, com essa quantidade de metal, poderia fazer 3 mellhorias no seu equipamento, ou consertar a chave e fazer apenas 1 melhoria.
                
                Você melhora:
                1- Chave e Equipamento
                2- Apenas equipamento

                """.formatted(jogador.getNome()));

            int escolha = Input.lerOpcao(1, 2);

            switch(escolha) {
                case 1:
                    jogador.setChave(true);
                    Texto.narrativa("""
                            Qual equipamento melhorar?
                            1-%s
                            2- Armadura
                            """.formatted(jogador.getArma()));
                    escolha = Input.lerOpcao(1, 2);
                    switch(escolha) {
                        case 1:
                            jogador.melhorarAtaque(5);
                            break;
                        case 2:
                            jogador.melhorarDefesa(5);
                            break;
                    }
                    break;

                case 2:
                    jogador.setChave(false);
                    Texto.narrativa("""

                            Quantas melhorias deseja fazer na %s? (O resto irá para a armadura)
                            
                            """.formatted(jogador.getArma()));
                    escolha = Input.lerOpcao(0, 3);
                    switch (escolha) {
                        case 3:
                            jogador.melhorarAtaque(23);
                            break;
                    
                        case 2:
                            jogador.melhorarAtaque(16);
                            jogador.melhorarDefesa(7);
                            break;
                        
                        case 1:
                            jogador.melhorarAtaque(8);
                            jogador.melhorarDefesa(15);
                            break;
                        
                        case 0:
                            jogador.melhorarDefesa(23);
                            break;
                    }
                    break;
            }
        Texto.narrativa("Você agradece o ferreiro e parte em direção a escadaria, afinal, quem possuia aquela chave só poderia estar lá. O Guarda havia despertado, o único jeito de subir, era derrotando-o. Você saca sua "+jogador.getArma()+" e começa uma batalha!");
        
        if (!GerenciadorCombate.iniciar(jogador, "Guarda", 150, 28, 7, 3)) {
            return false;
        }

        Texto.narrativa("Você sobe aquela longa escadaria, se deparando com uma porta imensa, que guardava uma sala de reuniões. Ao abrir a porta, vê dois homens, com roupas diferentes dos outros e exuberantes, com certeza eram importantes.\n");
        
        return true;
    }

}
