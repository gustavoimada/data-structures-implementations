public class Main
{
    public static void main(String[] args)
    {
        Arvore arvore = new Arvore();

        String[] palavras = {
                "bear",
                "bell",
                "bid",
                "bull",
                "buy",
                "sell",
                "stock",
                "stop"
        };

        System.out.println("Inserindo palavras...");
        for(int i = 0; i < palavras.length; i++)
        {
            System.out.println("Inserindo: " + palavras[i]);
            arvore.inserir(palavras[i]);
        }

        System.out.println("\nExibir por nivel:");
        arvore.exibirNivel();

        System.out.println("\nExibir todas as palavras:");
        arvore.exibirPalavrasIterativo2();
    }
}
