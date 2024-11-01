package br.com.projeto.ordenacao.model.algoritmos;

import br.com.projeto.ordenacao.model.CalculadorDeTempoExecucao;

public class BubbleSort implements CalculadorDeTempoExecucao {

    private double[] numeros;

    public BubbleSort(double[] arr) {
        setNumeros(arr);
    }

    // Implementação do Bubble Sort
    private void bubbleSort(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Troca arr[j] e arr[j+1]
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public long calcularTempoAlgoritmo() {
        long tempoInicial = System.currentTimeMillis();
        bubbleSort(getNumeros());
        return System.currentTimeMillis() - tempoInicial;
    }

    public void setNumeros(double[] nmr) {
        numeros = nmr;
    }

    public double[] getNumeros() {
        return numeros;
    }
}