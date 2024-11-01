package br.com.projeto.ordenacao.model.algoritmos;

import br.com.projeto.ordenacao.model.CalculadorDeTempoExecucao;

public class BubbleSort implements CalculadorDeTempoExecucao {

    private double[] numeros;

    // Construtor que define o array a ser ordenado
    public BubbleSort(double[] arr) {
        setNumeros(arr);
    }

    // Algoritmo Bubble Sort para ordenação
    private void bubbleSort(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Troca de posições para ordenar
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Calcula o tempo de execução do Bubble Sort
    public long calcularTempoAlgoritmo() {
        long tempoInicial = System.currentTimeMillis();
        bubbleSort(getNumeros());
        return System.currentTimeMillis() - tempoInicial;
    }

    // Getters e Setters
    public void setNumeros(double[] nmr) {
        numeros = nmr;
    }

    public double[] getNumeros() {
        return numeros;
    }
}
