public class Fila
{
    private NoFila inicio;

    public Fila()
    {
        // inicio jÃ¡ eh nulo por padrÃ£o, o java coloca automatico
    }

    public void enqueue(No info, int nivel)
    {
        NoFila aux;
        if(inicio == null)
            inicio = new NoFila(info, null, nivel);
        else
        {
            aux = inicio;
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
