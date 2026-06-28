public class No {
    // M eh a ordem da arvore, ou seja, o maximo de ligacoes que um no pode ter
    // por isso o maximo de infos dentro do no eh N - 1
    public static int N = 5;

    private int[]  vInfo;
    private No[]   vLig;
    private No     ant;
    private No     prox;
    private int    TL;

    public No() {
        vInfo = new int[N];
        vLig  = new No[N + 1];
        ant   = null;
        prox  = null;
        TL    = 0;
    }

    public No(int info) {
        this();
        vInfo[0] = info;
        TL = 1;
    }

    public int procurarPosicao(int info) {
        int pos = 0;
        while (pos < TL && info >= vInfo[pos]) pos++;
        return pos;
    }

    public void remanejar(int pos) {
        vLig[TL + 1] = vLig[TL];
        for (int i = TL; i > pos; i--) {
            vInfo[i] = vInfo[i - 1];
            vLig[i]  = vLig[i - 1];
        }
    }

    public void remanejarExc(int pos) {
        for (int i = pos; i < TL - 1; i++) {
            vInfo[i] = vInfo[i + 1];
            vLig[i]  = vLig[i + 1];
        }
        vLig[TL - 1] = vLig[TL];
    }

    public int  getvInfo(int p)          { return vInfo[p]; }
    public void setvInfo(int p, int v)   { vInfo[p] = v; }
    public No   getvLig(int p)           { return vLig[p]; }
    public void setvLig(int p, No v)     { vLig[p] = v; }
    public No   getAnt()                 { return ant; }
    public void setAnt(No a)             { ant = a; }
    public No   getProx()                { return prox; }
    public void setProx(No p)            { prox = p; }
    public int  getTL()                  { return TL; }
    public void setTL(int t)             { TL = t; }
}
