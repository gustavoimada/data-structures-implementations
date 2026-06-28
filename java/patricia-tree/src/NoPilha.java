public class NoPilha
{
    private No info;
    private String string;
    private NoPilha prox;

    public NoPilha(No info, String string, NoPilha prox)
    {
        this.info = info;
        this.prox = prox;
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public NoPilha getProx() {
        return prox;
    }

    public void setProx(NoPilha prox) {
        this.prox = prox;
    }

    public No getInfo() {
        return info;
    }

    public void setInfo(No info) {
        this.info = info;
    }
}
