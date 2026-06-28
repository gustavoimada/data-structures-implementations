public class Fila
{
    private NoFila inicio;

    public Fila()
    {

    }

    public void enqueue(No info, int nivel)
    {
        NoFila aux = inicio;
        if(aux == null)
        {
            inicio = new NoFila(info, null, nivel);
        }
        else
        {
            while(aux.getProx() != null)
                aux = aux.getProx();
            aux.setProx(new NoFila(info, null, nivel));
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
