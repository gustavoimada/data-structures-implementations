public class NoPilha
{
    private No atual;
    private No pai;
    private int nivel;
    private NoPilha prox;

    public NoPilha(No atual, No pai, int nivel, NoPilha prox)
    {
        this.atual = atual;
        this.pai = pai;
        this.nivel = nivel;
        this.prox = prox;
    }

    public No getAtual()
    {
        return atual;
    }

    public void setAtual(No atual) {
        this.atual = atual;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public NoPilha getProx()
    {
        return prox;
    }

    public void setProx(NoPilha prox)
    {
        this.prox = prox;
    }
}
