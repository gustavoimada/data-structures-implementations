public class No
{
    private String palavra;
    private No vLig[];
    boolean flag;

    public No()
    {
        palavra = "";
        vLig = new No[26];
        flag = false;
    }

    public String getPalavra()
    {
        return palavra;
    }

    public void setPalavra(String palavra)
    {
        this.palavra = palavra;
    }

    public No getvLig(int pos)
    {
        return vLig[pos];
    }

    public void setvLig(int pos, No vLig)
    {
        this.vLig[pos] = vLig;
    }

    public boolean getFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
}
