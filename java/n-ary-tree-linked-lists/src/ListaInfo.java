public class ListaInfo
{
    private NoListaInfo inicio;
    private int qntd;

    public ListaInfo(int info)
    {
        inserir(info);
    }

    public int getQntd() {
        return qntd;
    }

    public void inserir(int info)
    {
        NoListaInfo aux = inicio;
        if(aux == null)
            inicio = new NoListaInfo(info, null);
        else
        {
            if(info < aux.getInfo())
                inicio = new NoListaInfo(info, aux);
            else
            {
                while(aux.getProx() != null && info > aux.getProx().getInfo())
                    aux = aux.getProx();

                aux.setProx(new NoListaInfo(info, aux.getProx()));
            }
        }
        qntd++;
    }

    public int melhorPos(int info)
    {
        NoListaInfo no = inicio;
        int cont = 0;
        while(no != null && info > no.getInfo())
        {
            no = no.getProx();
            cont++;
        }
        return cont;
    }

    public int exibirPos(int pos)
    {
        NoListaInfo aux = inicio;
        while(pos > 0)
        {
            aux = aux.getProx();
            pos--;
        }

        return aux.getInfo();
    }
}
