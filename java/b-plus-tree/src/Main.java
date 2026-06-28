public class Main {

    public static void main(String[] args) {

        // ===================================================
        // Teste 1: n = 5 (200 insercoes e 150 exclusoes)
        // ===================================================
        No.N = 5; // define a ordem antes de criar a arvore

        System.out.println("===========================================");

        BPlusTree a5 = new BPlusTree(); // arvore vazia para testar n = 5

        for (int i = 1; i <= 100; i++) {
            a5.inserir(i); // insere 1 ate 100 em ordem crescente
        }

        for (int i = 200; i >= 101; i--) {
            a5.inserir(i); // insere 200 ate 101 em ordem decrescente
        }

        System.out.println("\nFolhas apos 200 insercoes (n = 5):");
        a5.exibir(); // metodo pedido no item a, percorre so as folhas

        System.out.println("\nIn-ordem iterativo apos 200 insercoes (n = 5):");
        a5.in_ordem_iterativo(); // metodo pedido no item b, agora usando pilha

        for (int i = 1; i <= 75; i++) {
            a5.excluir(i); // remove varios do comeco, causando underflow em folhas
        }

        for (int i = 126; i <= 200; i++) {
            a5.excluir(i); // remove varios do fim, testando redistribuicao/concatenacao
        }

        System.out.println("\nFolhas apos 150 exclusoes (n = 5):");
        a5.exibir(); // confere se as folhas continuam encadeadas

        System.out.println("\nIn-ordem iterativo apos 150 exclusoes (n = 5):");
        a5.in_ordem_iterativo(); // confere se a ordem continuou correta

        // ===================================================
        // Teste 2: n = 4 (300 insercoes e 201 exclusoes)
        // ===================================================
        No.N = 4; // muda a ordem antes de criar outra arvore

        System.out.println("===========================================");

        BPlusTree a4 = new BPlusTree(); // arvore vazia para testar n = 4

        for (int i = 0; i < 150; i++) {
            a4.inserir(i * 2); // insere primeiro todos os pares de 0 ate 298
        }

        for (int i = 149; i >= 0; i--) {
            a4.inserir(i * 2 + 1); // depois insere os impares de 299 ate 1
        }

        System.out.println("\nFolhas apos 300 insercoes (n = 4):");
        a4.exibir(); // mostra todos os valores pelas folhas

        System.out.println("\nIn-ordem iterativo apos 300 insercoes (n = 4):");
        a4.in_ordem_iterativo(); // mostra em ordem usando a pilha

        for (int i = 0; i <= 100; i++) {
            a4.excluir(i); // exclui 101 valores do inicio
        }

        for (int i = 200; i <= 299; i++) {
            a4.excluir(i); // exclui 100 valores do final
        }

        System.out.println("\nFolhas apos 201 exclusoes (n = 4):");
        a4.exibir(); // confere o encadeamento das folhas depois das exclusoes

        System.out.println("\nIn-ordem iterativo apos 201 exclusoes (n = 4):");
        a4.in_ordem_iterativo(); // confere a ordem depois das exclusoes

        // ===================================================
        // Teste 3: exemplo pequeno do PDF (n = 4)
        // ===================================================
        System.out.println("===========================================");

        BPlusTree caso = new BPlusTree(); // arvore pequena para acompanhar melhor

        caso.inserir(6);
        caso.inserir(9);
        caso.inserir(2);
        caso.inserir(7);
        caso.inserir(4);
        caso.inserir(8);
        caso.inserir(5);
        caso.inserir(1);
        caso.inserir(3);

        System.out.println("\nFolhas apos insercoes do caso pequeno:");
        caso.exibir(); // deve estar tudo nas folhas em ordem
        System.out.println("\nEstrutura apos exclusoes do caso pequeno:");
        caso.excluir(4);
        caso.excluir(7);
        caso.exibir();
        caso.in_ordem_iterativo();
    }
}
