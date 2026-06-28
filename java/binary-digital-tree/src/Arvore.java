public class Arvore
{
    private No raiz;

    public Arvore()
    {
        raiz = new No();
    }

    public void montarArvBinaria(Arquivo arquivo)
    {
        String codB;
        No aux = raiz;
        Registro reg = new Registro();

        arquivo.seekArq(0);
        while(!arquivo.eof())
        {
            reg = arquivo.lerRegistro();
            codB = reg.getCodigoBinario();

            for(int i = 0; i < codB.length(); i++)
            {
                if(codB.charAt(i) == '0')
                {
                    if(aux.getEsq() == null)
                        aux.setEsq(new No());
                    aux = aux.getEsq();
                }
                else
                {
                    if(aux.getDir() == null)
                        aux.setDir(new No());
                    aux = aux.getDir();
                }
            }
            aux.setChave(reg.getString());
            aux = raiz;
        }
    }

    public void compactar()
    {
        Pilha p = new Pilha();
        NoPilha noPilhaAtual;
        No atual, pai;
        int n;

        p.push(raiz, raiz, 1);
        while(!p.isEmpty())
        {
            noPilhaAtual = p.pop();
            atual = noPilhaAtual.getAtual();
            n = noPilhaAtual.getNivel();

            if(atual.getEsq() != null && atual.getDir() != null) // tem 2 filhos
            {
                atual.setRotulo(n);

                p.push(atual.getDir(), atual, n+1);
                p.push(atual.getEsq(), atual, n+1);
            }
            else
            {   // ja sabe que nao tem 2 filhos
                pai = noPilhaAtual.getPai();
                if(atual.getEsq() != null) // tem sÃ³ o filho da esquerda
                {
                    if(pai.getEsq() == atual)
                    {
                        pai.setEsq(atual.getEsq());
                        pai.getEsq().setRotulo(n+1);
                        p.push(pai.getEsq(), pai, n+1);
                    }
                    else
                    {
                        pai.setDir(atual.getEsq());
                        pai.getDir().setRotulo(n+1);
                        p.push(pai.getDir(), pai, n+1);
                    }
                }
                // nao posso simplemsente botar num else pq tem o caso folha
                if(atual.getDir() != null) // tem sÃ³ o filho da direita
                {
                    if(pai.getDir() == atual)
                    {
                        pai.setDir(atual.getDir());
                        pai.getDir().setRotulo(n+1);
                        p.push(pai.getDir(), pai, n+1);
                    }
                    else
                    {
                        pai.setEsq(atual.getDir());
                        pai.getEsq().setRotulo(n+1);
                        p.push(pai.getEsq(), pai, n+1);
                    }
                }
                // se nÃ£o entrou em nenhum dos else, Ã© folha, faz nada
            }
        }
    }

    // CHUTES DE EXERCICIOS

    public void decodifica(String sequencia)
    {
        No aux = raiz;
        int ultimoTamanho=0;

        for (int i = 0; i < sequencia.length(); i++)
        {
            i = aux.getRotulo() + ultimoTamanho;
            if(sequencia.charAt(i-1) == '0') // tem q colocar -1 porque o rotulo comeÃ§a de 1 e o charAt divide a sequencia em um vetor que comeÃ§a de 0
                aux = aux.getEsq();
            else
                aux = aux.getDir();

            if(!aux.getChave().equals(""))
            {
                System.out.print(aux.getChave()); // ou poderia guardar numa pilha sla
                ultimoTamanho = ultimoTamanho + aux.getRotulo();
                aux = raiz;
                // essa Ãºltima linha ta considerando que o rotulo de uma folha, ou seja
                // de um nÃ³ que contem a chave, tambÃ©m tem o rotulo que carrega o tamanho
                // inteiro desse cÃ³digo, mas para fazer isso tem que fazer 2 alteraÃ§Ãµes:
                // aux.setRotulo(codB.length()) no montarArvBinaria junto com aux.setChave
                // adicionar um if no compactar, sÃ³ seta o rotulo se o nÃ³ nao for folha com chave
                // literal sÃ³ adicionar um if
            }
        }
    }
}
