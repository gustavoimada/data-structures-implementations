public class BPlusTree {

    private No raiz;

    // No.M e definido pelo usuario antes de usar (ex: No.M = 5)
    public BPlusTree() {
        raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No r) {
        raiz = r;
    }

    private int min() {
        return (No.N - 1) / 2;
    }

    public No navegarAteFolha(int info) {// desce na arvore ate chegar na folha onde a info deveria estar
        No aux = raiz;
        while (aux.getvLig(0) != null) {// desce na arvore ate chegar na folha onde a info deveria estar
            aux = aux.getvLig(aux.procurarPosicao(info));// escolhe a ligacao certa para descer
        }
        return aux;
    }

    public No localizarPai(No filho) { // localiza o pai de um no, usado principalmente no split e na exclusao
        No resultado;
        if (filho == raiz) { // se o filho eh a raiz, ele nao tem pai de verdade
            resultado = raiz;
        } else {
            No r = buscarPai(raiz, filho); // se o filho Ã© a raiz, ele nao tem pai de verdade
            if (r != null) {
                resultado = r;
            } else {
                resultado = raiz;
            }
        }
        return resultado;
    }

    // quando o primeiro valor de uma folha muda, os separadores internos podem mudar tambem
    private void atualizarSeparadores(int antigo, int novo) {
        atualizarSeparadores(raiz, antigo, novo);
    }

    // percorre os nos internos trocando o separador antigo pelo novo
    private void atualizarSeparadores(No no, int antigo, int novo) {
        if (no != null && no.getvLig(0) != null) { // so no interno tem separador
            for (int i = 0; i < no.getTL(); i++) {
                if (no.getvInfo(i) == antigo) { // achou a chave antiga que apontava para a folha
                    no.setvInfo(i, novo);
                }
            }
            for (int i = 0; i <= no.getTL(); i++) {
                atualizarSeparadores(no.getvLig(i), antigo, novo); // continua procurando nos filhos
            }
        }
    }

    // busca recursiva auxiliar para achar quem aponta para o filho
    private No buscarPai(No atual, No filho) {
        No resultado = null;
        if (atual != null && atual.getvLig(0) != null) { // so no interno pode ser pai
            boolean encontrou = false;
            for (int i = 0; i <= atual.getTL() && !encontrou; i++) {
                if (atual.getvLig(i) == filho) { // achou uma ligacao apontando para o filho
                    resultado = atual;
                    encontrou = true;
                }
            }
            if (!encontrou) {
                for (int i = 0; i <= atual.getTL() && resultado == null; i++) {
                    resultado = buscarPai(atual.getvLig(i), filho); // desce nos filhos procurando
                }
            }
        }
        return resultado;
    }

    // descobre em qual posicao do pai esta a ligacao para o filho
    private int posFilhoNoPai(No pai, No filho) {
        int pos = -1;
        for (int i = 0; i <= pai.getTL() && pos == -1; i++) {
            if (pai.getvLig(i) == filho) { // compara por referencia, pq precisa ser o mesmo no
                pos = i;
            }
        }
        return pos;
    }

    // remove uma chave do pai e tambem remove uma ligacao ligada a essa chave
    private void removeDoPai(No pai, int posChave, int posLig) {
        for (int i = posChave; i < pai.getTL() - 1; i++) {
            pai.setvInfo(i, pai.getvInfo(i + 1)); // puxa as chaves para a esquerda
        }
        for (int i = posLig; i < pai.getTL(); i++) {
            pai.setvLig(i, pai.getvLig(i + 1)); // puxa as ligacoes para a esquerda
        }
        pai.setTL(pai.getTL() - 1); // agora o pai tem uma chave a menos
    }

    // divide um no que estourou e joga a chave separadora para o pai
    private void split(No folha, No pai) {
        No cx1 = new No(); // primeira metade depois do split
        No cx2 = new No(); // segunda metade depois do split
        int aux, meio;

        No proxSalvo = null;
        No antSalvo = null;
        if (folha.getvLig(0) == null) { // se for folha, precisa guardar a lista encadeada
            proxSalvo = folha.getProx();
            antSalvo = folha.getAnt();
        }

        if (folha.getvLig(0) == null) { // split de folha
            aux = No.N / 2; // primeira metade fica na cx1
            for (int i = 0; i < aux; i++) {
                cx1.setvInfo(i, folha.getvInfo(i));
                cx1.setTL(cx1.getTL() + 1);
            }
            meio = aux; // menor valor da segunda folha sobe como separador
            for (int i = aux; i < No.N; i++) {
                cx2.setvInfo(i - aux, folha.getvInfo(i));
                cx2.setTL(cx2.getTL() + 1);
            }
        } else { // split de no interno
            aux = (No.N - 1) / 2; // primeira metade fica antes da chave do meio
            for (int i = 0; i < aux; i++) {
                cx1.setvInfo(i, folha.getvInfo(i));
                cx1.setvLig(i, folha.getvLig(i));
                cx1.setTL(cx1.getTL() + 1);
            }
            cx1.setvLig(aux, folha.getvLig(aux)); // ultima ligacao da primeira metade
            meio = aux; // chave do meio sobe para o pai
            int ini = aux + 1; // segunda metade comeca depois da chave que subiu
            for (int i = ini; i < No.N; i++) {
                cx2.setvInfo(i - ini, folha.getvInfo(i));
                cx2.setvLig(i - ini, folha.getvLig(i));
                cx2.setTL(cx2.getTL() + 1);
            }
            cx2.setvLig(No.N - ini, folha.getvLig(No.N)); // ultima ligacao da segunda metade
        }

        if (folha == pai) { // estourou a raiz, entao cria uma raiz nova usando o proprio no
            folha.setvInfo(0, folha.getvInfo(meio));
            folha.setvLig(0, cx1);
            folha.setvLig(1, cx2);
            folha.setTL(1);
            if (cx1.getvLig(0) == null) { // se dividiu folha, precisa religar as folhas
                cx1.setProx(cx2);
                cx2.setAnt(cx1);
                cx1.setAnt(antSalvo);
                cx2.setProx(proxSalvo);
                if (antSalvo != null) {
                    antSalvo.setProx(cx1);
                }
                if (proxSalvo != null) {
                    proxSalvo.setAnt(cx2);
                }
            }
        } else {
            int info = folha.getvInfo(meio); // chave que vai subir para o pai
            int pos = pai.procurarPosicao(info); // acha onde essa chave entra no pai
            pai.remanejar(pos); // abre espaco no pai
            pai.setTL(pai.getTL() + 1); // pai ganhou uma chave
            pai.setvInfo(pos, info);
            pai.setvLig(pos, cx1);
            pai.setvLig(pos + 1, cx2);

            if (pai.getvLig(0).getvLig(0) == null) { // pai aponta direto para folhas
                for (int j = 0; j < pai.getTL(); j++) {
                    pai.getvLig(j).setProx(pai.getvLig(j + 1));
                    pai.getvLig(j + 1).setAnt(pai.getvLig(j));
                }
                if (pos + 1 == pai.getTL()) { // split aconteceu no final da lista
                    cx2.setProx(proxSalvo);
                    if (proxSalvo != null) {
                        proxSalvo.setAnt(cx2);
                    }
                }
                if (pos == 0) { // split aconteceu no comeco da lista
                    cx1.setAnt(antSalvo);
                    if (antSalvo != null) {
                        antSalvo.setProx(cx1);
                    }
                }
            }

            if (pai.getTL() > No.N - 1) { // se o pai tambem estourou, sobe o split
                split(pai, localizarPai(pai));
            }
        }
    }

    // insere uma informacao sempre na folha correta
    public void inserir(int info) {
        if (raiz == null) { // primeira insercao cria a raiz como folha
            raiz = new No(info);
        } else {
            No folha = navegarAteFolha(info); // acha a folha onde o valor deve entrar
            int pos = folha.procurarPosicao(info); // acha a posicao dentro da folha
            folha.remanejar(pos); // abre espaco
            folha.setvInfo(pos, info);
            folha.setTL(folha.getTL() + 1);
            if (folha.getTL() > No.N - 1) { // passou do maximo, tem que fazer split
                split(folha, localizarPai(folha));
            }
        }
    }

    // exclui uma informacao procurando primeiro na folha
    public void excluir(int info) {
        if (raiz != null) {
            No aux = navegarAteFolha(info); // B+ remove o dado na folha
            int pos = aux.procurarPosicao(info); // procura onde o valor estaria
            boolean encontrou = (pos > 0 && aux.getvInfo(pos - 1) == info); // confirma se existe
            if (encontrou) {
                pos--; // procurarPosicao volta depois do valor, entao precisa voltar uma casa
                boolean removeuPrimeiro = (pos == 0); // se removeu o primeiro, talvez mude separador
                aux.remanejarExc(pos); // tira o valor da folha
                aux.setTL(aux.getTL() - 1);

                if (aux != raiz && aux.getTL() < min()) { // ficou com menos que o minimo
                    redistribuicao_concatenacao(aux); // tenta resolver com irma ou concatenando
                }

                if (removeuPrimeiro && aux != raiz && aux.getTL() > 0) { // atualiza separadores que ficaram velhos
                    atualizarSeparadores(info, aux.getvInfo(0));
                }

                if (aux == raiz && aux.getTL() == 0 && aux.getvLig(0) != null) { // raiz vazia desce um nivel
                    raiz = aux.getvLig(0);
                }
            }
        }
    }

    public void redistribuicao_concatenacao(No no) { // resolve underflow tentando redistribuir primeiro e concatenar se nao der
        No pai = localizarPai(no);// pega o pai para achar as irmas
        if (pai != null && pai != no) {
            int pPos = posFilhoNoPai(pai, no); // posicao do no dentro das ligacoes do pai
            if (pPos != -1) {
                No irmaE = null;
                if (pPos > 0) // se nao for o primeiro filho, tem irma esquerda
                    irmaE = pai.getvLig(pPos - 1);
                No irmaD = null;
                if (pPos < pai.getTL()) // se nao for o ultimo filho, tem irma direita
                    irmaD = pai.getvLig(pPos + 1);
                int mn = min(); //funcao de receber o min
                boolean isFolha = (no.getvLig(0) == null); // folha e interno tratam diferente
                boolean concatenou = false; // marca se removeu ligacao do pai

                if (isFolha) {
                    if (irmaE != null && irmaE.getTL() > mn) { // redistribui pegando da esquerda
                        no.remanejar(0); // abre espaco no comeco
                        no.setvInfo(0, irmaE.getvInfo(irmaE.getTL() - 1));
                        no.setTL(no.getTL() + 1);
                        irmaE.setTL(irmaE.getTL() - 1); // irma perdeu o ultimo valor
                        pai.setvInfo(pPos - 1, no.getvInfo(0)); // separador vira o primeiro do no

                    } else if (irmaD != null && irmaD.getTL() > mn) { // redistribui pegando da direita
                        no.setvInfo(no.getTL(), irmaD.getvInfo(0));
                        no.setTL(no.getTL() + 1);
                        for (int i = 0; i < irmaD.getTL() - 1; i++)
                            irmaD.setvInfo(i, irmaD.getvInfo(i + 1)); // puxa a irma direita para esquerda
                        irmaD.setTL(irmaD.getTL() - 1);
                        if (pPos > 0) // se o no nao eh o primeiro filho, atualiza separador anterior
                            pai.setvInfo(pPos - 1, no.getvInfo(0));
                        pai.setvInfo(pPos, irmaD.getvInfo(0)); // separador da direita muda para o novo primeiro dela
                    } else if (irmaE != null) { // nao deu para "emprestar", concatena com a esquerda
                        for (int i = 0; i < no.getTL(); i++) {
                            irmaE.setvInfo(irmaE.getTL() + i, no.getvInfo(i));
                        }
                        irmaE.setTL(irmaE.getTL() + no.getTL());
                        irmaE.setProx(no.getProx()); // pula o no que foi concatenado
                        if (no.getProx() != null) {
                            no.getProx().setAnt(irmaE);
                        }
                        removeDoPai(pai, pPos - 1, pPos); // remove separador e ligacao do pai
                        concatenou = true;

                    } else if (irmaD != null) { // nao tem esquerda, concatena com a direita
                        for (int i = 0; i < irmaD.getTL(); i++) {
                            no.setvInfo(no.getTL() + i, irmaD.getvInfo(i));
                        }
                        no.setTL(no.getTL() + irmaD.getTL());
                        no.setProx(irmaD.getProx());
                        if (irmaD.getProx() != null) {
                            irmaD.getProx().setAnt(no);
                        }
                        removeDoPai(pai, pPos, pPos + 1); // remove a chave que separava os dois
                        concatenou = true;
                    }

                } else {
                    if (irmaE != null && irmaE.getTL() > mn) { // no interno pega emprestado da esquerda
                        no.remanejar(0);
                        no.setvInfo(0, pai.getvInfo(pPos - 1));
                        no.setvLig(0, irmaE.getvLig(irmaE.getTL()));
                        no.setTL(no.getTL() + 1);
                        pai.setvInfo(pPos - 1, irmaE.getvInfo(irmaE.getTL() - 1)); // pai recebe ultimo da esquerda
                        irmaE.setTL(irmaE.getTL() - 1);

                    } else if (irmaD != null && irmaD.getTL() > mn) { // no interno pega emprestado da direita
                        no.setvInfo(no.getTL(), pai.getvInfo(pPos));
                        no.setvLig(no.getTL() + 1, irmaD.getvLig(0));
                        no.setTL(no.getTL() + 1);
                        pai.setvInfo(pPos, irmaD.getvInfo(0)); // primeiro da direita sobe para o pai
                        for (int i = 0; i < irmaD.getTL() - 1; i++) {
                            irmaD.setvInfo(i, irmaD.getvInfo(i + 1));
                            irmaD.setvLig(i, irmaD.getvLig(i + 1));
                        }
                        irmaD.setvLig(irmaD.getTL() - 1, irmaD.getvLig(irmaD.getTL()));
                        irmaD.setTL(irmaD.getTL() - 1);

                    } else if (irmaE != null) { // concatena no interno com a esquerda
                        int p = irmaE.getTL(); // comeca no final da irma esquerda
                        irmaE.setvInfo(p, pai.getvInfo(pPos - 1));
                        irmaE.setvLig(p + 1, no.getvLig(0));
                        irmaE.setTL(p + 1);
                        for (int i = 0; i < no.getTL(); i++) {
                            irmaE.setvInfo(irmaE.getTL() + i, no.getvInfo(i));
                            irmaE.setvLig(irmaE.getTL() + i + 1, no.getvLig(i + 1));
                        }
                        irmaE.setTL(irmaE.getTL() + no.getTL());
                        removeDoPai(pai, pPos - 1, pPos); // remove a chave que desceu do pai
                        concatenou = true;

                    } else if (irmaD != null) { // concatena no interno com a direita
                        int q = no.getTL(); // comeca no final do no atual
                        no.setvInfo(q, pai.getvInfo(pPos));
                        no.setvLig(q + 1, irmaD.getvLig(0));
                        no.setTL(q + 1);
                        for (int i = 0; i < irmaD.getTL(); i++) {
                            no.setvInfo(no.getTL() + i, irmaD.getvInfo(i));
                            no.setvLig(no.getTL() + i + 1, irmaD.getvLig(i + 1));
                        }
                        no.setTL(no.getTL() + irmaD.getTL());
                        removeDoPai(pai, pPos, pPos + 1); // remove a ligacao da irma direita
                        concatenou = true;
                    }
                }

                if (concatenou) { // se concatenou, o pai perdeu uma chave
                    if (pai == raiz && pai.getTL() == 0) { // se a raiz ficou vazia, a arvore diminui altura
                        raiz = pai.getvLig(0);
                    } else if (pai != raiz && pai.getTL() < mn) { // se o pai tambem ficou abaixo do minimo
                        redistribuicao_concatenacao(pai); // sobe o tratamento do underflow
                    }
                }
            }
        }
    }

    // exibe apenas as folhas, descendo pela ligacao 0 e depois seguindo pelo prox
    public void exibir() {
        if (raiz == null) {
            System.out.println("[arvore vazia]//");
        } else {
            No aux = raiz;
            while (aux.getvLig(0) != null) { // vai sempre pela esquerda ate a primeira folha
                aux = aux.getvLig(0);
            }
            while (aux != null) { // percorre a lista encadeada de folhas
                for (int i = 0; i < aux.getTL(); i++) {
                    System.out.print(aux.getvInfo(i) + "--");
                }
                aux = aux.getProx();
            }
            System.out.println("//");
        }
    }

    public void in_ordem() {
        in_ordem(raiz);
        System.out.println();
    }

    public void in_ordem(No raiz) {
        if (raiz != null) {
            for (int i = 0; i < raiz.getTL(); i++) {
                in_ordem(raiz.getvLig(i));
                System.out.print(raiz.getvInfo(i) + " ");
            }
            in_ordem(raiz.getvLig(raiz.getTL()));
        }
    }

    public void in_ordem_iterativo() {
        Pilha pilha = new Pilha();
        No atual = raiz;
        int pos = 0;

        while (atual != null || !pilha.isEmpty()) {
            while (atual != null) {
                pilha.push(atual, pos);
                atual = atual.getvLig(pos);
                pos = 0;
            }

            NoPilha caixa = pilha.pop();
            atual = caixa.getNo();
            pos = caixa.getPos();

            if (pos < atual.getTL()) {
                System.out.print(atual.getvInfo(pos) + " ");

                pos++;
                pilha.push(atual, pos);
                atual = atual.getvLig(pos);
                pos = 0;
            } else {
                atual = null;
                pos = 0;
            }
        }

        System.out.println();
    }




}
