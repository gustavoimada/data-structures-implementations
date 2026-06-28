public class NoPilha {
    private No no;
    private int pos;
    private NoPilha prox;

    public NoPilha(No no, int pos) {
        this.no = no;
        this.pos = pos;
        this.prox = null;
    }

    public No getNo() {
        return no;
    }

    public void setNo(No no) {
        this.no = no;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public NoPilha getProx() {
        return prox;
    }

    public void setProx(NoPilha prox) {
        this.prox = prox;
    }
}
