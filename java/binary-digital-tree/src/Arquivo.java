import java.io.IOException;
import java.io.RandomAccessFile;

public class Arquivo
{
    private RandomAccessFile arquivo;

    public Arquivo(String nomearquivo)
    {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        }
        catch (IOException e)
        {

        }
    }

    // semelhante ao feof() do C , verifica se o ponteiro esta no <EOF> do arquivo
    public boolean eof()
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        }
        catch (IOException e)
        {

        }
        return (retorno);
    }

    public Registro lerRegistro()
    {
        Registro registro = new Registro();
        registro.leDoArq(arquivo);
        return registro;
    }

    public void seekArq(int pos)
    {
        try
        {
            arquivo.seek(pos * Registro.length());
        }
        catch (IOException e)
        {

        }
    }
}
