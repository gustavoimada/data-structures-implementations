public class Pilha {
    private NoPilha topo;

    public Pilha() {
        topo = null;
    }

    public boolean isEmpty() {
        return topo == null;
    }

    public void push(No no, int pos) {
        NoPilha novo = new NoPilha(no, pos);
        novo.setProx(topo);
        topo = novo;
    }

    public NoPilha pop() {
        NoPilha removido = topo;
        topo = topo.getProx();
        return removido;
    }

    public NoPilha top() {
        return topo;
    }
}
