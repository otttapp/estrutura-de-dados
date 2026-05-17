package UNI3.aula2;

/**
 * Classe Veiculo — representa um veículo com placa e proprietário.
 * 
 * Utilizada como tipo T nos testes do mapa de dispersão genérico,
 * sendo a placa (String) usada como chave K.
 */
public class Veiculo {

    private String placa;
    private String proprietario;

    /**
     * Construtor — cria um veículo com placa e proprietário.
     * 
     * @param placa        placa do veículo (ex: "AXQ-3041")
     * @param proprietario nome do proprietário (ex: "Ana")
     */
    public Veiculo(String placa, String proprietario) {
        this.placa = placa;
        this.proprietario = proprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Veiculo{placa='" + placa + "', proprietario='" + proprietario + "'}";
    }
}
