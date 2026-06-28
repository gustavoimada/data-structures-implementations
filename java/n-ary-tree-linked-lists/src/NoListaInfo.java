public class NoListaInfo
{
    int info;
    NoListaInfo prox;

    public NoListaInfo(int info, NoListaInfo prox)
    {
        this.info = info;
        this.prox = prox;
    }

    public int getInfo()
    {
        return info;
    }

    public void setInfo(int info)
    {
        this.info = info;
    }

    public NoListaInfo getProx()
    {
        return prox;
    }

    public void setProx(NoListaInfo prox)
    {
        this.prox = prox;
    }
}
