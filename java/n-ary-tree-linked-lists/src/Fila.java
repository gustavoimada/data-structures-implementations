public class Fila
{
    private NoFila inicio;

    public Fila()
    {
    }

    public void enqueue(No info, int n)
    {
        if(inicio == null)
            inicio = new NoFila(n, info, null);
        else
        {
            NoFila aux = inicio;
            while(aux.getProx() != null)
                aux = aux.getProx();
            aux.setProx(new NoFila(n, info, null));
        }
    }

    public NoFila dequeue()
    {
        NoFila aux = inicio;
        inicio = inicio.getProx();
        return aux;
    }

    public boolean isEmpty()
    {
        return inicio == null;
    }
}
