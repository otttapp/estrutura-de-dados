package UNI1.aula2.exercicio1;

import UNI1.aula1.Aluno;

public class main {
    public static void main(String[] args) {
        ListaEstatica<Aluno> alunos = new ListaEstatica();

        alunos.inserir(new Aluno(500, "Vinicius"));
        alunos.inserir(new Aluno(100, "Vito"));
        alunos.inserir(new Aluno(600, "Buumann"));

        Aluno p1 = new Aluno(333, "arthur");
        alunos.inserir(p1);

        for (int i = 0; i < alunos.getTamanho(); i++) {
            Aluno umAluno = alunos.obterElemento(i);
            System.out.println("Aluno " + ((Aluno) umAluno).getNome());
        }


        ListaEstatica frutas = new ListaEstatica<>();
        frutas.inserir("manga");

        frutas.buscar(System.in);
        
    }
}
