package br.com.projeto.ordenacao.model.algoritmos;

import br.com.projeto.ordenacao.model.CalculadorDeTempoExecucao;

public class MergeSort implements CalculadorDeTempoExecucao {

    private double[] v;
    private double[] w;

    // Construtor para definir o array a ser ordenado
    public MergeSort(double[] vet) {
        setVetor(vet);
    }

    // Método de ordenação Merge Sort
    public void mergeSort(double[] v, double[] w, int ini, int fim) {
        if (ini < fim) {
            int meio = (ini + fim) / 2;
            mergeSort(v, w, ini, meio);
            mergeSort(v, w, meio + 1, fim);
            intercalar(v, w, ini, meio, fim);
        }
    }

    // Intercala as duas metades ordenadas
    public void intercalar(double[] v, double[] w, int ini, int meio, int fim) {
        for (int k = ini; k <= fim; k++) {
            w[k] = v[k];
        }

        int i = ini;
        int j = meio + 1;

        for (int k = ini; k <= fim; k++) {
            if (i > meio) v[k] = w[j++];
            else if (j > fim) v[k] = w[i++];
            else if (w[i] < w[j]) v[k] = w[i++];
            else v[k] = w[j++];
        }
    }

    // Calcula o tempo de execução do Merge Sort
    public long calcularTempoAlgoritmo() {
        long tempoInicial = System.currentTimeMillis();
        mergeSort(v, w, 0, v.length - 1);

        return System.currentTimeMillis() - tempoInicial;
    }

    // Getters e Setters
    public void setVetor(double[] vet) {
        v = vet;
        w = new double[v.length];
    }

    public double[] getVetor() {
        return v;
    }
}
