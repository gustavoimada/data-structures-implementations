public class Main
{
    public static void main(String[] args)
    {
        TriePatricia patricia = new TriePatricia();

        String[] palavras = {"galo", "sola", "gel", "solo", "sol", "galope", "gelatina", "gema", "soltar",
                "soldado", "apple", "apricot", "april", "bathroom", "bandana", "bandit", "connect", "context",
                "cooperate", "drama", "dream", "drastic", "elephant", "elegant", "finance", "final", "finger",
                "ground", "great", "group", "house", "home", "hour", "hotel", "host", "hope", "horizon",
                "camera", "campus", "campo", "caminho", "casa", "casaco", "casamento", "cavalo", "caverna",
                "porta", "portao", "porto", "porco", "poema", "poeta", "programa", "projeto", "prova",
                "arvore", "arquivo", "arma", "artigo", "azul", "banana", "banco", "barco", "bateria",
                "dados", "data", "dado", "dedo", "delta", "festa", "ferro", "filtro", "folha", "forca",
                "livro", "linha", "lista", "lima", "limite", "rede", "regra", "relatorio", "roda", "roupa",
                "mesa", "mesario", "metodo", "metal", "menta", "mercado", "mouse", "motor", "modelo",
                "monte", "mundo", "nave", "navio", "nariz", "natural", "natureza", "noite", "nome", "nota",
                "noticia", "numero", "pedra", "pedreiro", "pedal", "pessoa", "peste", "peixe", "piano",
                "pimenta", "plantar", "plano", "praia", "preco", "pressa", "primo", "quarto", "quase",
                "queda", "queijo", "questao", "quieto", "sala", "salada", "salario", "sapato", "sapo",
                "sombra", "sonho", "tempo", "templo", "tenda", "teste", "texto", "terra", "tigre", "tinta",
                "trabalho", "trator", "treino", "uva", "urso", "urgente", "valor", "vento", "verbo", "verde",
                "viagem", "video", "vila", "violao", "xadrez", "xerox", "zebra", "zero"};

        for(int i = 0; i < palavras.length; i++)
            patricia.inserir(palavras[i]);

        System.out.println("Palavras inseridas com sucesso!\n");

        System.out.println("Printando todas palavras da arvore:");
        patricia.exibirPalavras();
        System.out.println();

        String[] buscas = {"sol", "geleia", "porta", "carro", "sola", "bananaaa", "galo", "portao",
                "gal", "gel", "programador", "final", "porto", "solado", "banana", "dado", "contexto",
                "dados", "tempo", "tempestade", "templo", "violao", "carrinho", "zero", "gelado",
                "projetos", "arvore", "arvores", "sapato", "sapatos", "texto", "textos", "mesa",
                "mesas", "xyz", "livro", "livros"};

        System.out.println("=== Busca na arvore ===");
        for(int i = 0; i < buscas.length; i++)
        {
            if(patricia.buscar(buscas[i]))
                System.out.println(buscas[i] + ": Achou");
            else
                System.out.println(buscas[i] + ": Nao achou");
        }
        System.out.println();

        System.out.println("=== Exibindo por nivel ===");
        patricia.exibirPorNivel();
        System.out.println();

        System.out.println("========================================");
        System.out.println("Indo para outro teste: teste do enunciado");
        System.out.println("========================================");

        TriePatricia testeEnunciado = new TriePatricia();
        testeEnunciado.inserir("galo");
        testeEnunciado.inserir("sola");
        testeEnunciado.inserir("gel");
        testeEnunciado.inserir("solo");
        testeEnunciado.inserir("sol");

        System.out.println("Palavras do teste do enunciado inseridas!\n");

        System.out.println("Printando as palavras do teste do enunciado");
        testeEnunciado.exibirPalavras();

        System.out.println("=== Teste do enunciado por nivel ===");
        testeEnunciado.exibirPorNivel();
    }
}
