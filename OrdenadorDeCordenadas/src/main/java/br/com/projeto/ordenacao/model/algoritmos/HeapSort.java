package br.com.projeto.ordenacao.model.algoritmos;

import br.com.projeto.ordenacao.model.CalculadorDeTempoExecucao;

public class HeapSort implements CalculadorDeTempoExecucao {
    private double[] numeros;

    // Construtor para definir o array a ser ordenado
    public HeapSort(double[] arr) {
        setNumeros(arr);
    }

    // Algoritmo Heap Sort para ordenar o array
    public void heapSort(double[] arr) {
        int tamanho = arr.length;

        // Cria o heap max
        for (int i = tamanho / 2 - 1; i >= 0; i--)
            heapify(arr, tamanho, i);

        // Remove elementos do heap um a um
        for (int i = tamanho - 1; i >= 0; i--) {
            double x = arr[0];
            arr[0] = arr[i];
            arr[i] = x;

            heapify(arr, i, 0);
        }
    }

    // Reestrutura o heap para manter a propriedade do heap max
    void heapify(double[] arr, int heapSize, int i) {
        int maior = i;
        int noEsquerda = 2 * i + 1;
        int noDireira = 2 * i + 2;

        if (noEsquerda < heapSize && arr[noEsquerda] > arr[maior])
            maior = noEsquerda;

        if (noDireira < heapSize && arr[noDireira] > arr[maior])
            maior = noDireira;

        if (maior != i) {
            double troca = arr[i];
            arr[i] = arr[maior];
            arr[maior] = troca;

            heapify(arr, heapSize, maior);
        }
    }

    // Calcula o tempo de execução do Heap Sort
    public long calcularTempoAlgoritmo() {
        long tempoInicial = System.currentTimeMillis();
        heapSort(getNumeros());
        return System.currentTimeMillis() - tempoInicial;
    }

    // Getters e Setters
    private void setNumeros(double[] arr) {
        numeros = arr;
    }

    public double[] getNumeros() {
        return numeros;
    }
}
