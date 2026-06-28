public class No
{
    private char vLetra[];
    private int posicao;
    private No vLig[];
    private String palavra;
    private int qtdLig; // eh claro que no enunciado nÃ£o tinha esse atributo
    // mas ele sÃ³ guarda a quantidade de ligaÃ§Ãµes desse no, para facilitar na hora de inserir
    // se nÃ£o teria que fazer e verificar cada ligaÃ§Ã£o individual para saber se esse nÃ³ tem filho
    // ou nÃ£o

    public No()
    {
        vLetra = new char[26];
        vLig = new No[26];
        posicao = 1; //  se nao a posicao ia ser 0 por padrao
        palavra = ""; // o java inicia string como null entao temq botar "" aq
    }

    public char getvLetra(int pos)
    {
        return vLetra[pos];
    }

    public void setvLetra(int pos, char letra)
    {
        vLetra[pos] = letra;
    }

    public int getPosicao()
    {
        return posicao;
    }

    public void setPosicao(int posicao)
    {
        this.posicao = posicao;
    }

    public No getvLig(int pos)
    {
        return vLig[pos];
    }

    public void setvLig(int pos, No vLig)
    {
        this.vLig[pos] = vLig;
    }

    public String getPalavra()
    {
        return palavra;
    }

    public void setPalavra(String palavra)
    {
        this.palavra = palavra;
    }

    public int getQtdLig()
    {
        return qtdLig;
    }

    public void setQtdLig(int qtdLig)
    {
        this.qtdLig = qtdLig;
    }
}
