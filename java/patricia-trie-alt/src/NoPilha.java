public class NoPilha
{
    private No info;
    private NoPilha prox;

    public NoPilha(No info, NoPilha prox)
    {
        this.info = info;
        this.prox = prox;
    }

    public No getInfo()
    {
        return info;
    }

    public NoPilha getProx()
    {
        return prox;
    }

}
