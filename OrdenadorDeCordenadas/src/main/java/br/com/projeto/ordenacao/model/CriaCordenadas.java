package br.com.projeto.ordenacao.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CriaCordenadas {
    private double latitude;
    private double longitude;
    private int quantidade;
    private List<Double> coordenadas = new ArrayList<>();

    private static final int COORD_X = 180; // Limite para longitude
    private static final int COORD_Y = 90;  // Limite para latitude

    // Construtor que define a quantidade de coordenadas a serem geradas
    public CriaCordenadas(int qtd) {
        setQuantidade(qtd);
    }

    // Gera uma coordenada aleatória dentro do limite especificado
    private double gerarCoordenadaAleatoria(int n) {
        double coordenada = Math.random() * (n + n) - n;
        BigDecimal bd = BigDecimal.valueOf(coordenada).setScale(6, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    // Gera a lista de coordenadas (latitude e longitude) aleatórias
    public void gerarCoordenadas() {
        int i = 0;
        while (i <= getQuantidade()) {
            setLatitude(gerarCoordenadaAleatoria(COORD_Y));
            setLongitude(gerarCoordenadaAleatoria(COORD_X));
            setCoordenadas(getLatitude());
            setCoordenadas(getLongitude());
            i++;
        }
    }

    // Formata a coordenada para exibição em string
    public static String formataCoordenadas(double latitude, double longitude) {
        return String.valueOf(latitude + ", " + longitude);
    }

    // Getters e Setters
    public List<Double> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(double coordenadas) {
        this.coordenadas.add(coordenadas);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
