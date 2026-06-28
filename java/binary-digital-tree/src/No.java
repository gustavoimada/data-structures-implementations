public class No
{
    private No esq;
    private No dir;
    private String chave;
    int rotulo;

    public No()
    {
        // esq e dir ja recebem null por padrao
        chave = "";
        rotulo = 1;
    }

    public void setEsq(No esq)
    {
        this.esq = esq;
    }

    public No getEsq()
    {
        return esq;
    }

    public void setDir(No dir)
    {
        this.dir = dir;
    }

    public No getDir()
    {
        return dir;
    }

    public String getChave()
    {
        return chave;
    }

    public void setChave(String chave)
    {
        this.chave = chave;
    }

    public void setRotulo(int rotulo)
    {
        this.rotulo = rotulo;
    }

    public int getRotulo()
    {
        return rotulo;
    }
}
