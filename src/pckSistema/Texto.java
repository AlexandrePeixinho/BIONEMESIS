package pckSistema;

//(Classe)
//Classe responsavel por exibir textos com efeito visual.
public class Texto {

    public  static void narrativa(String texto) {

        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
             Thread.sleep(10);
            } 
            catch (InterruptedException e) {
             e.printStackTrace();
            }
        }
    }

    public  static void titulo(String texto) {

        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
             Thread.sleep(60);
            } 
            catch (InterruptedException e) {
             e.printStackTrace();
            }
        }
    }

}
