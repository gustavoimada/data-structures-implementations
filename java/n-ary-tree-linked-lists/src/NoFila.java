public class NoFila
{
    private int nivel;
    private No info;
    private NoFila prox;

    public NoFila(int nivel, No info, NoFila prox) {
        this.nivel = nivel;
        this.info = info;
        this.prox = prox;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public No getInfo() {
        return info;
    }

    public void setInfo(No info) {
        this.info = info;
    }

    public NoFila getProx() {
        return prox;
    }

    public void setProx(NoFila prox) {
        this.prox = prox;
    }
}
