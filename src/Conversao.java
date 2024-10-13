import com.google.gson.annotations.SerializedName;

public class Conversao {
    @SerializedName("result")
    private String resultado;

    @SerializedName("error-type")
    private String erro;

    @SerializedName("conversion_rate")
    private double conversaoDeTaxa;

    @SerializedName("conversion_result")
    private double conversaoDoResultado;

    public String getResultado() {
        return resultado;
    }

    public String getErro() {
        return erro;
    }

    public double getConversaoDeTaxa() {
        return conversaoDeTaxa;
    }

    public double getConversaoDoResultado() {
        return conversaoDoResultado;
    }
}