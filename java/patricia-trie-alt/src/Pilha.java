public class Pilha
{
    private NoPilha topo;

    public Pilha()
    {
        // topo jÃ¡ Ã© nulo por padrÃ£o, o java coloca automÃ¡tico
    }

    public void push(No info)
    {
        topo = new NoPilha(info, topo); // conecta com o outro passando o topo no construtor do NoPilha
    }

    public NoPilha pop()
    {
        NoPilha aux = topo;
        topo = topo.getProx();
        return aux; // parecido com o da fila msm
    }

    public boolean isEmpty()
    {
        return topo == null;
    }
}
