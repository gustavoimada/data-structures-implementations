public class Main
{
    public static void main(String[] args)
    {
        Arvore arvore = new Arvore();

        arvore.inserir(30);
        arvore.inserir(40);
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(35);
        arvore.inserir(38);
        arvore.inserir(45);

        System.out.println("EXIBINDO EM NIVEL");
        arvore.ExibirEmNivel();
    }
}
