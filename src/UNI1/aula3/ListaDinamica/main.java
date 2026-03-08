package UNI1.aula3.ListaDinamica;

public class main {
    public static void main(String[] args) {
        Aluno umAluno;
        Aluno outroAluno;
        umAluno = new Aluno(1234567, "VInizao");
        outroAluno =  new Aluno(89010, "Maria");

        umAluno.setProximo(outroAluno);

        outroAluno = new Aluno(3, "Juan");
        outroAluno.setProximo(umAluno);
        
        outroAluno = umAluno;

        while (outroAluno != null) {
            System.out.println(outroAluno);
            outroAluno = outroAluno.getProximo();
        }
    }
}
