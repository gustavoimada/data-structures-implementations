public class ListaLig
{
    private NoListaLig inicio;

    public ListaLig()
    {
        inserirFim(null);
        inserirFim(null);
    }

    public void inserirFim(No novo)
    {
        if(inicio == null)
            inicio = new NoListaLig(novo, null);
        else
        {
            NoListaLig aux = inicio;
            while(aux.getProx() != null)
                aux = aux.getProx();

            aux.setProx(new NoListaLig(novo, null));
        }
    }

    public No andarPos(int pos)
    {
        NoListaLig aux = inicio;
        while(pos>0)
        {
            aux = aux.getProx();
            pos--;
        }
        return aux.getInfo();
    }

    public void insereNaPos(int pos, No novo)
    {
        NoListaLig aux = inicio;
        while(pos>0)
        {
            aux = aux.getProx();
            pos--;
        }
        aux.setInfo(novo);
    }
}
