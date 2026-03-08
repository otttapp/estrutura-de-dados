package UNI1.aula3.ListaEstatica;

import UNI1.aula3.ListaEstatica.ListaEstatica;

public class App3 {
    public static void main(String[] args) {
        ListaEstatica<String> nomes = new ListaEstatica<>();
        nomes.inserir("Vini");
        nomes.inserir("Bumann");
        nomes.inserir("Vito");

        nomes.retirar("Vito");
    }
}
