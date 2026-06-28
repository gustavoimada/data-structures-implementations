public class NoListaLig
{
    No info;
    NoListaLig prox;

    public NoListaLig(No info, NoListaLig prox)
    {
        this.info = info;
        this.prox = prox;
    }

    public No getInfo() {
        return info;
    }

    public void setInfo(No info) {
        this.info = info;
    }

    public NoListaLig getProx() {
        return prox;
    }

    public void setProx(NoListaLig prox) {
        this.prox = prox;
    }
}
