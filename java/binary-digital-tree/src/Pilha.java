public class Pilha
{
    private NoPilha topo;

    public Pilha()
    {
    }

    public void push(No atual, No pai, int nivel)
    {
        topo = new NoPilha(atual, pai, nivel, topo);
    }

    public NoPilha pop()
    {
        NoPilha aux = topo;
        topo = topo.getProx();
        return aux;
    }

    public boolean isEmpty()
    {
        return topo == null;
    }
}
