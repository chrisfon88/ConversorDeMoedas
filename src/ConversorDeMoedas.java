import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;

public class ConversorDeMoedas {
    private final HttpClient httpClient;
    private final String[] moedasSuportadas = {"USD", "EUR", "BRL", "JPY", "GBP", "AUD"};

    public ConversorDeMoedas() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public Conversao obterConversao(String moedaOrigem, String moedaDestino, double valor) throws IOException, InterruptedException {
        String apiKey = "sua-chave-de-api"; // Substitua pela sua chave de API
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + moedaOrigem + "/" + moedaDestino + "/" + valor;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Resposta completa da API: " + response.body());

        if (response.statusCode() != 200) {
            System.out.println("Erro: Solicitação falhou com código HTTP: " + response.statusCode());
            return null;
        }

        Gson gson = new Gson();
        Conversao conversao = gson.fromJson(response.body(), Conversao.class);

        if (conversao.getResultado().equals("success")) {
            return conversao;
        } else {
            System.out.println("Erro na conversão: " + conversao.getErro());
            return null;
        }
    }

    private boolean moedaValida(String moeda) {
        for (String m : moedasSuportadas) {
            if (m.equals(moeda)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ConversorDeMoedas conversor = new ConversorDeMoedas();
        Scanner scanner = new Scanner(System.in);

        try {
            boolean continuar = true;
            while (continuar) {
                String moedaOrigem, moedaDestino;
                double valor = -1;

                do {
                    System.out.println("Escolha a moeda de origem e digite somente a sigla: (" +
                            "USD - Dólar dos Estados Unidos, " +
                            "EUR - Euro, " +
                            "BRL - Real, " +
                            "JPY - Iene, " +
                            "GBP - Libra Esterlina, " +
                            "AUD - Dólar Australiano)");
                    moedaOrigem = scanner.next().toUpperCase();
                    if (!conversor.moedaValida(moedaOrigem)) {
                        System.out.println("Moeda inválida. Por favor, tente novamente.");
                    }
                } while (!conversor.moedaValida(moedaOrigem));

                do {
                    System.out.println("Escolha a moeda de destino e digite somente a sigla: (" +
                            "USD - Dólar dos Estados Unidos, " +
                            "EUR - Euro, " +
                            "BRL - Real, " +
                            "JPY - Iene, " +
                            "GBP - Libra Esterlina, " +
                            "AUD - Dólar Australiano)");
                    moedaDestino = scanner.next().toUpperCase();
                    if (!conversor.moedaValida(moedaDestino)) {
                        System.out.println("Moeda inválida. Por favor, tente novamente.");
                    }
                } while (!conversor.moedaValida(moedaDestino));

                do {
                    System.out.print("Digite o valor a ser convertido (positivo): ");
                    if (scanner.hasNextDouble()) {
                        valor = scanner.nextDouble();
                        if (valor <= 0) {
                            System.out.println("Valor inválido. Digite um número positivo.");
                        }
                    } else {
                        System.out.println("Entrada inválida. Digite um número válido.");
                        scanner.next();
                    }
                } while (valor <= 0);

                try {
                    Conversao conversao = conversor.obterConversao(moedaOrigem, moedaDestino, valor);
                    if (conversao != null && "success".equals(conversao.getResultado())) {
                        System.out.printf("Taxa de conversão: %.4f%n", conversao.getConversaoDeTaxa());
                        System.out.printf("Resultado da conversão: %.2f %s%n", conversao.getConversaoDoResultado(), moedaDestino);
                    } else {
                        System.out.println("Não foi possível realizar a conversão. Por favor, verifique as informações e tente novamente.");
                    }
                } catch (IOException | InterruptedException e) {
                    System.out.println("Erro ao se comunicar com a API. Tente novamente mais tarde.");
                }

                System.out.print("Deseja realizar outra conversão? (sim/não): ");
                String resposta = scanner.next().toLowerCase();
                if (!resposta.equals("sim")) {
                    continuar = false;
                }
            }
        } finally {
            scanner.close();
        }
    }
}

