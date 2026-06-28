public class TriePatricia
{
    private No raiz;

    public TriePatricia()
    {
        // raiz ja eh nula por padrao o java bota automaticamente
    }

    // a) auxiliar pro inserir
    private int calcularPosDiferenca(String palavra, String palavraAntiga)
    {
        int tamPalavra = palavra.length(), tamPalavraAntiga = palavraAntiga.length(), posDiferenca = 1;

        while(posDiferenca <= tamPalavra && posDiferenca <= tamPalavraAntiga && palavra.charAt(posDiferenca - 1) == palavraAntiga.charAt(posDiferenca - 1))
            posDiferenca++;

        return posDiferenca;
    }

    // a) Inserir na arvore
    public void inserir(String palavra)
    {
        No pai = raiz, atual = raiz, novo, meio, aux;
        String palavraAntiga;
        int tamPalavra, tamPalavraAntiga, posPai = 0, posVet = 0, posDiferenca, posNova, posAntiga, menorTamPalavras;
        char letraNova, letraAntiga, letraPai;
        boolean inseriu = false, criouMeio;

        // transforma em minusculo
        palavra = palavra.toLowerCase();
        tamPalavra = palavra.length();

        if(raiz == null) // primeira palavra inserida vira a raiz
        {
            raiz = new No();
            raiz.setPalavra(palavra);
            raiz.setPosicao(tamPalavra);
        }
        else
        {
            // percorre a arvore ate encontrar o ponto certo para inserir ou dividir
            while(!inseriu)
            {
                meio = atual;
                criouMeio = false;
                palavraAntiga = atual.getPalavra();

                if(atual.getQtdLig() != 0 && palavraAntiga.equals(""))
                {
                    aux = atual;
                    while(palavraAntiga.equals(""))
                    {
                        posVet = 0;
                        while(aux.getvLig(posVet) == null)
                            posVet++;

                        aux = aux.getvLig(posVet);
                        palavraAntiga = aux.getPalavra();
                    }
                }

                if(palavraAntiga.equals(palavra)) // palavra repetida, nao insere de novo
                    inseriu = true;
                else
                {
                    tamPalavraAntiga = palavraAntiga.length();
                    menorTamPalavras = tamPalavra;
                    if(tamPalavraAntiga < menorTamPalavras) // pega o menor tamanho para tratar prefixo
                        menorTamPalavras = tamPalavraAntiga;

                    posDiferenca = calcularPosDiferenca(palavra, palavraAntiga);

                    if(atual.getQtdLig() == 0 && posDiferenca > menorTamPalavras) // chegou em folha e uma palavra eh prefixo da outra
                    {
                        meio = new No();
                        criouMeio = true;

                        if(tamPalavra < tamPalavraAntiga) // palavra nova eh prefixo da palavra antiga
                        {
                            meio.setPosicao(tamPalavra + 1);
                            meio.setPalavra(palavra);
                            letraAntiga = palavraAntiga.charAt(tamPalavra);
                            posAntiga = letraAntiga - 'a';

                            meio.setvLetra(posAntiga, letraAntiga);
                            meio.setvLig(posAntiga, atual);
                            meio.setQtdLig(1);
                        }
                        else // palavra antiga eh prefixo da palavra nova
                        {
                            meio.setPosicao(tamPalavraAntiga + 1);
                            meio.setPalavra(palavraAntiga);
                            letraNova = palavra.charAt(tamPalavraAntiga);
                            posNova = letraNova - 'a';

                            novo = new No();
                            novo.setPalavra(palavra);
                            novo.setPosicao(tamPalavra);

                            meio.setvLetra(posNova, letraNova);
                            meio.setvLig(posNova, novo);
                            meio.setQtdLig(1);
                        }

                        inseriu = true;
                    }
                    else
                    {
                        if(posDiferenca > menorTamPalavras && tamPalavra < atual.getPosicao()) // palavra nova eh prefixo do caminho atual
                        {
                            meio = new No();
                            meio.setPosicao(tamPalavra);
                            if(atual.getPalavra().equals(""))
                                atual.setPalavra(palavra);
                            else
                                meio.setPalavra(palavra);
                            posVet = palavra.charAt(tamPalavra - 1) - 'a';

                            meio.setvLetra(posVet, palavra.charAt(tamPalavra - 1));
                            meio.setvLig(posVet, atual);
                            meio.setQtdLig(1);

                            criouMeio = true;
                            inseriu = true;
                        }
                        else
                        {
                            if(atual.getQtdLig() == 0 || posDiferenca < atual.getPosicao()) // divide folha ou caminho antes do no atual
                            {
                                meio = new No();
                                meio.setPosicao(posDiferenca);
                                letraNova = palavra.charAt(posDiferenca - 1);
                                letraAntiga = palavraAntiga.charAt(posDiferenca - 1);
                                posNova = letraNova - 'a';
                                posAntiga = letraAntiga - 'a';

                                novo = new No();
                                novo.setPalavra(palavra);
                                novo.setPosicao(tamPalavra);

                                meio.setvLetra(posNova, letraNova);
                                meio.setvLig(posNova, novo);
                                meio.setvLetra(posAntiga, letraAntiga);
                                meio.setvLig(posAntiga, atual);
                                meio.setQtdLig(2);

                                criouMeio = true;
                                inseriu = true;
                            }
                            else
                            {
                                // usa a posicao do no atual para escolher a entrada certa do vetor
                                posVet = palavra.charAt(atual.getPosicao() - 1) - 'a';

                                if(atual.getvLig(posVet) == null) // achou uma posicao livre para ligar a palavra nova
                                {
                                    novo = new No();
                                    novo.setPalavra(palavra);
                                    novo.setPosicao(tamPalavra);
                                    atual.setvLetra(posVet, palavra.charAt(atual.getPosicao() - 1));
                                    atual.setvLig(posVet, novo);
                                    atual.setQtdLig(atual.getQtdLig() + 1);
                                    inseriu = true;
                                }
                                else
                                {
                                    // ja existe caminho nessa letra, entao desce para continuar comparando
                                    pai = atual;
                                    posPai = posVet;
                                    atual = atual.getvLig(posVet);
                                }
                            }
                        }
                    }

                    if(criouMeio) // religa a raiz ou o pai no novo no criado no meio
                    {
                        if(atual == raiz)
                            raiz = meio;
                        else
                        {
                            letraPai = palavra.charAt(pai.getPosicao() - 1);
                            pai.setvLetra(posPai, letraPai);
                            pai.setvLig(posPai, meio);
                        }
                    }
                }
            }
        }
    }

    // b) Exibir todas palavras da arvore, usa pilha (nao precsisa de um campo para ir concatenando pq essa forma de implementacao
    // ja tem a string da palavra inteira guardada
    public void exibirPalavras()
    {
        Pilha pilha = new Pilha();
        NoPilha caixa;
        No atual;

        pilha.push(raiz);
        while(!pilha.isEmpty())
        {
            caixa = pilha.pop();
            atual = caixa.getInfo();

            if(!atual.getPalavra().equals("")) // se tiver a palavra printa
                System.out.println(atual.getPalavra());

            for(int i = 25; i >= 0; i--) // tem que ser de "tras pra frente" pq eh pilha
                if(atual.getvLig(i) != null) // tem q verificar se tem ou nao esse filho
                    pilha.push(atual.getvLig(i));
        }
    }

    // c) Buscar uma palavra na arvore
    public boolean buscar(String palavra)
    {
        No atual = raiz;
        int pos;
        boolean achou = false, terminou = false;

        palavra = palavra.toLowerCase();
        while(atual != null && !terminou)
        {
            if(atual.getPalavra().equals(palavra))
            {
                achou = true;
                terminou = true;
            }
            else
            {
                if(palavra.length() < atual.getPosicao())
                    terminou = true;
                else
                {
                    pos = palavra.charAt(atual.getPosicao() - 1) - 'a';
                    atual = atual.getvLig(pos);
                }
            }
        }

        return achou;
    }

    // d) Exibir as informacoes nivel a nivel
    public void exibirPorNivel()
    {
        Fila fila = new Fila();
        NoFila caixa;
        No atual;
        String tipo, palavra, letras, posicao;
        int nivelAtual = -1, nivel;

        fila.enqueue(raiz, 0);
        while(!fila.isEmpty())
        {
            caixa = fila.dequeue();
            atual = caixa.getInfo();
            nivel = caixa.getNivel();

            if(nivel != nivelAtual) // se o nivel for diferente printa
            {
                nivelAtual = nivel;
                System.out.println();
                System.out.println("Nivel " + nivel + ":");
            }

            letras = ""; // para poder exibir todas as informacoes

            // nao precisa ser de tras para frente igual na pilha
            // aqui eh fila, entao segue a ordem colocada no enqueue
            for(int i = 0; i < 26; i++)
            {
                // para poder exibir todas as informacoes
                if(atual.getvLetra(i) != '\0')
                    letras = letras + atual.getvLetra(i);

                // logica para percorrer em nivel
                if(atual.getvLig(i) != null)
                    fila.enqueue(atual.getvLig(i), nivel + 1);
            }

            // para poder exibir todas as informacoes corretamente

            if(atual == raiz)
                tipo = "RAIZ";
            else
            {
                if(atual.getQtdLig() > 0)
                    tipo = "INTERNO";
                else
                    tipo = "FOLHA";
            }

            if(atual.getPalavra().equals(""))
                palavra = "-";
            else
                palavra = atual.getPalavra();

            if(letras.equals(""))
                letras = "-";

            if(tipo.equals("FOLHA"))
                posicao = "-";
            else
                posicao = "" + atual.getPosicao();

            System.out.println("  " + tipo
                    + " -> vLetra: " + letras
                    + " | posicao: " + posicao
                    + " | palavra: " + palavra);
        }
    }
}
