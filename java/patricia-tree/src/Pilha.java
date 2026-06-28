public class Pilha
{
    private NoPilha topo;

    public Pilha()
    {

    }

    public void push(No info, String string)
    {
        topo = new NoPilha(info, string, topo);
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
