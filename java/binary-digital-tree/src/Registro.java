import java.io.IOException;
import java.io.RandomAccessFile;

public class Registro
{
    private static final int TF_STRING = 20;
    private static final int TF_CODIGO_BINARIO = 50;

    private String string;
    private String codigoBinario;

    public Registro()
    {
        this("", "");
    }

    public Registro(String string, String codigoBinario)
    {
        this.string = string;
        this.codigoBinario = codigoBinario;
    }

    public void gravaNoArq(RandomAccessFile arquivo)
    {
        try
        {
            gravaString(arquivo, string, TF_STRING);
            gravaString(arquivo, codigoBinario, TF_CODIGO_BINARIO);
        }
        catch(IOException e)
        {

        }
    }

    public void leDoArq(RandomAccessFile arquivo)
    {
        try
        {
            string = leString(arquivo, TF_STRING);
            codigoBinario = leString(arquivo, TF_CODIGO_BINARIO);
        }
        catch(IOException e)
        {

        }
    }

    static int length()
    {
        // Cada char gravado com writeChar ocupa 2 bytes.
        return (TF_STRING + TF_CODIGO_BINARIO) * 2;
    }

    public String getString() {
        return string.trim();
    }

    public String getCodigoBinario() {
        return codigoBinario.trim();
    }

    private static void gravaString(RandomAccessFile arquivo, String valor, int tamanho) throws IOException
    {
        for(int i = 0 ; i < tamanho ; i++)
        {
            if(valor != null && i < valor.length())
                arquivo.writeChar(valor.charAt(i));
            else
                arquivo.writeChar(' ');
        }
    }

    private static String leString(RandomAccessFile arquivo, int tamanho) throws IOException
    {
        String valor = "";
        for(int i = 0 ; i < tamanho ; i++)
            valor += arquivo.readChar();
        return valor;
    }
}
