public class Arvore
{
    private No raiz;

    public void inserir(int info)
    {
        No aux = raiz, filho;
        boolean flag = false;
        int pos;

        if(raiz == null)
            raiz = new No(info);
        else
        {
            while(!flag)
            {
                if(aux.getListaI().getQntd() < No.n-1) // tem espaÃ§o nesse nÃ³
                {
                    aux.getListaI().inserir(info);
                    aux.getListaL().inserirFim(null);
                    flag = true;
                }
                else // nao tem espaÃ§o no nÃ³
                {
                    pos = aux.getListaI().melhorPos(info);
                    filho = aux.getListaL().andarPos(pos);

                    if(filho == null) // Ã© folha e nÃ£o tem espaÃ§o nele, entÃ£o cria um nÃ³ para baixo na ligaÃ§Ã£o certa
                    {
                        aux.getListaL().insereNaPos(pos, new No(info));
                        flag = true;
                    }
                    else // nÃ£o tem espaÃ§o mas nÃ£o Ã© folha, entao anda na ligaÃ§Ã£o
                        aux = filho;
                }
            }
        }
    }

    public void ExibirEmNivel()
    {
        NoFila NoFilaAux;
        No NoAux = raiz;
        int nivel = -1;
        Fila f = new Fila();

        f.enqueue(NoAux, 0);
        while(!f.isEmpty())
        {
            NoFilaAux = f.dequeue();
            NoAux = NoFilaAux.getInfo();

            if(nivel != NoFilaAux.getNivel())
            {
                nivel = NoFilaAux.getNivel();
                System.out.println("\nNÃ­vel:"+nivel);
            }

            for(int i = 0; i < NoAux.getListaI().getQntd(); i++)
            {
                System.out.print(" "+NoAux.getListaI().exibirPos(i));

                if(NoAux.getListaL().andarPos(i) != null)
                    f.enqueue(NoAux.getListaL().andarPos(i), nivel+1);
            }
            if(NoAux.getListaL().andarPos(NoAux.getListaI().getQntd()) != null) // pq n anda nela
                f.enqueue(NoAux.getListaL().andarPos(NoAux.getListaI().getQntd()), nivel+1);
        }
    }
}
