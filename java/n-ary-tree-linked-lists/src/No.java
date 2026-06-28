public class No
{
    public static final int n = 3;

    private ListaInfo listaI;
    private ListaLig listaL;

    public No(int info)
    {
        listaI = new ListaInfo(info);
        listaL = new ListaLig();
    }

    public ListaInfo getListaI() {
        return listaI;
    }

    public ListaLig getListaL() {
        return listaL;
    }
}
