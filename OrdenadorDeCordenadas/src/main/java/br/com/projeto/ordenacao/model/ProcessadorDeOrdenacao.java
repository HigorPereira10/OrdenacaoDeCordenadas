package br.com.projeto.ordenacao.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import br.com.projeto.ordenacao.controller.gerador_arquivo.CriaArquivo;
import br.com.projeto.ordenacao.model.algoritmos.HeapSort;
import br.com.projeto.ordenacao.model.algoritmos.MergeSort;
import br.com.projeto.ordenacao.model.algoritmos.BubbleSort;

public class ProcessadorDeOrdenacao {
    private int qtdCoord;
    private String nomeArquivo;
    private String algoritmoEscolhido;
    private String caminhoSalvamento;
    private List<Double> coordenadas;
    private List<String> coordenadasGeradas = new ArrayList<>();
    
    private List<String> coordenadasBubbleSort = new ArrayList<>();
    private List<String> coordenadasMergeSort = new ArrayList<>();
    private List<String> coordenadasHeapSort = new ArrayList<>();

    // Construtor para dados gerados internamente
    public ProcessadorDeOrdenacao(String nomeArquivo, int qtdCoord, String algoritmoEscolhido, String caminhoSalvamento) {
        this.nomeArquivo = nomeArquivo;
        this.qtdCoord = qtdCoord;
        this.algoritmoEscolhido = algoritmoEscolhido;
        this.caminhoSalvamento = caminhoSalvamento;
        gerarCoordenadas(); // Gera coordenadas aleatórias
    }

    // Construtor para dados importados externamente
    public ProcessadorDeOrdenacao(List<Double> coordenadas, String algoritmoEscolhido) {
        this.coordenadas = coordenadas;
        this.algoritmoEscolhido = algoritmoEscolhido;
    }

    // Método para gerar coordenadas aleatórias
    private void gerarCoordenadas() {
    	CriaCordenadas gerador = new CriaCordenadas(qtdCoord);
        gerador.gerarCoordenadas();
        this.coordenadas = gerador.getCoordenadas();
        for (int i = 0; i < coordenadas.size(); i += 2) {
            coordenadasGeradas.add(coordenadas.get(i) + ", " + coordenadas.get(i + 1));
        }
    }

    // Método que executa a ordenação e mede o tempo de cada algoritmo
    public String iniciar() {
        double[] arr = listaParaArray(coordenadas);
        StringBuilder resultados = new StringBuilder();

        // Calcula tempo de execução e armazena coordenadas para BubbleSort
        BubbleSort bubbleSort = new BubbleSort(arr.clone());
        long tempoExecucaoBubble = bubbleSort.calcularTempoAlgoritmo();
        resultados.append("BubbleSort: ").append(tempoExecucaoBubble).append("ms\n");
        coordenadasBubbleSort = formatarCoordenadas(bubbleSort.getNumeros());

        // Calcula tempo de execução e armazena coordenadas para MergeSort
        MergeSort mergeSort = new MergeSort(arr.clone());
        long tempoExecucaoMerge = mergeSort.calcularTempoAlgoritmo();
        resultados.append("MergeSort: ").append(tempoExecucaoMerge).append("ms\n");
        coordenadasMergeSort = formatarCoordenadas(mergeSort.getVetor());

        // Calcula tempo de execução e armazena coordenadas para HeapSort
        HeapSort heapSort = new HeapSort(arr.clone());
        long tempoExecucaoHeap = heapSort.calcularTempoAlgoritmo();
        resultados.append("HeapSort: ").append(tempoExecucaoHeap).append("ms");
        coordenadasHeapSort = formatarCoordenadas(heapSort.getNumeros());

        salvarCoordenadas(arr);
        return resultados.toString();
    }

    // Método auxiliar para converter lista de coordenadas para array
    private static double[] listaParaArray(List<Double> coordenadas) {
        double[] arr = new double[coordenadas.size()];
        for (int i = 0; i < coordenadas.size(); i++) {
            arr[i] = coordenadas.get(i);
        }
        return arr;
    }

    private void salvarCoordenadas(double[] arrOrdenado) {
        File pastaOrdenado = new File(caminhoSalvamento + "Ordenado/");
        pastaOrdenado.mkdirs();
        File pastaDesordenado = new File(caminhoSalvamento + "Desordenado/");
        pastaDesordenado.mkdirs();

        CriaArquivo geradorArquivoDesordenado = new CriaArquivo(pastaDesordenado.getPath() + "/");
        CriaArquivo geradorArquivoOrdenado = new CriaArquivo(pastaOrdenado.getPath() + "/");

        geradorArquivoDesordenado.gerarArquivoCoordenadaDesordenada(coordenadas, nomeArquivo);
        geradorArquivoOrdenado.gerarArquivoOrdenado(arrOrdenado, nomeArquivo + algoritmoEscolhido);
    }

    // Formata coordenadas para exibição
    private List<String> formatarCoordenadas(double[] arr) {
        List<String> listaFormatada = new ArrayList<>();
        for (int i = 0; i < arr.length; i += 2) {
            if (i + 1 < arr.length) {
                listaFormatada.add(arr[i] + ", " + arr[i + 1]);
            }
        }
        return listaFormatada;
    }

    // Retorna as coordenadas geradas pelo algoritmo escolhido
    public List<String> getCoordenadasGeradasAlgoritmo(String algoritmo) {
        switch (algoritmo) {
            case "BubbleSort":
                return coordenadasBubbleSort;
            case "MergeSort":
                return coordenadasMergeSort;
            case "HeapSort":
                return coordenadasHeapSort;
            default:
                return new ArrayList<>();
        }
    }

    public List<String> getCoordenadasGeradas() {
        return coordenadasGeradas;
    }
}