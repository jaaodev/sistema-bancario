package domain;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Transacao {
    LocalDateTime data;
    String tipo;
    double valor;
    double saldoApos;
    String detalhes;

    public Transacao(String tipo, double valor, double saldoApos, String detalhes) {
        this.data = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
        this.saldoApos = saldoApos;
        this.detalhes = detalhes;
    }

    public String getDataFormatada() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return this.data.format(formatador);
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public double getSaldoApos() {
        return saldoApos;
    }

}
