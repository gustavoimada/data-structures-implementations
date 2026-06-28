public class Arvore
{
    private No raiz;

    public Arvore()
    {
        raiz = new No();
    }

    public void inserir(String palavra)
    {
        No pai = raiz, filho, novo, meio;
        int i = 0, j, pos, posFilhoAntigo, posNovo;
        String palavraFilho;
        boolean continua = true;

        palavra = palavra.toLowerCase();
        while (i < palavra.length() && continua)
        {
            pos = palavra.charAt(i) - 'a';
            filho = pai.getvLig(pos);

            if (filho == null) // Cria uma caixa e coloca palavra (inteira ou o resto)
            {
                novo = new No();
                novo.setPalavra(palavra.substring(i));
                novo.setFlag(true);
                pai.setvLig(pos, novo);
                continua = false;
            }
            else // ja existe uma caixa que comeca com essa letra, comparar as palavras
            {
                palavraFilho = filho.getPalavra();
                j = 0;
                while(j < palavraFilho.length() && i + j < palavra.length() && palavraFilho.charAt(j) == palavra.charAt(i + j))
                    j++;

                if (j == palavraFilho.length()) // o filho inteiro bateu entÃ£o desce na arvore e volta na repetiÃ§Ã£o
                {
                    i = i+j;
                    pai = filho;
                    if (i == palavra.length()) // palavra terminou, Ã© um prefixo
                    {
                        pai.setFlag(true);
                        continua = false;
                    }
                }
                else // a palavra nova e a palavra do filho tem partes em comum
                {
                    meio = new No();
                    meio.setPalavra(palavraFilho.substring(0, j));
                    meio.setFlag(i + j == palavra.length());
                    pai.setvLig(pos, meio);

                    filho.setPalavra(palavraFilho.substring(j));
                    posFilhoAntigo = filho.getPalavra().charAt(0) - 'a';
                    meio.setvLig(posFilhoAntigo, filho);

                    if (i + j < palavra.length()) // ainda sobrou parte da palavra nova
                    {
                        novo = new No();
                        novo.setPalavra(palavra.substring(i + j));
                        novo.setFlag(true);

                        posNovo = novo.getPalavra().charAt(0) - 'a';
                        meio.setvLig(posNovo, novo);
                    }
                    continua = false;
                }
            }
        }
    }

    public void excluir(String palavra)
    {

    }

    public void exibirNivel()
    {
        Fila f = new Fila();
        No aux = raiz;
        NoFila auxNoFila;
        int nivel = -1;

        f.enqueue(aux, 0);
        while(!f.isEmpty())
        {
            auxNoFila = f.dequeue();
            aux = auxNoFila.getInfo();

            if(nivel != auxNoFila.getNivel())
            {
                nivel = auxNoFila.getNivel();
                System.out.println("\nNivel: "+auxNoFila.getNivel()+" ");
            }

            System.out.println(aux.getPalavra());
            for(int i = 0; i < 26; i++)
            {
                if(aux.getvLig(i) != null)
                    f.enqueue(aux.getvLig(i), auxNoFila.getNivel()+1);
            }
        }
    }

    public void exibirPalavrasIterativo2()
    {
        Pilha p = new Pilha();
        NoPilha caixa;
        No auxN; String auxS;

        p.push(raiz, "");
        while(!p.isEmpty())
        {
            caixa = p.pop();
            auxN = caixa.getInfo();
            auxS = caixa.getString();

            if(auxN.getFlag())
                System.out.println(auxS);

            for (int i = 25; i >= 0; i--)
            {
                if(auxN.getvLig(i) != null)
                    p.push(auxN.getvLig(i),   auxS+ auxN.getvLig(i).getPalavra());

            }
        }
    }
}
