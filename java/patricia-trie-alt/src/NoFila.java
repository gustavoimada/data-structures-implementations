public class NoFila
{
    private No info;
    private int nivel;
    private NoFila prox;

    public NoFila(No info, NoFila prox, int nivel)
    {
        this.info = info;
        this.prox = prox;
        this.nivel = nivel;
    }

    public No getInfo()
    {
        return info;
    }

    public int getNivel()
    {
        return nivel;
    }

    public NoFila getProx()
    {
        return prox;
    }

    public void setProx(NoFila prox)
    {
        this.prox = prox;
    }
}
