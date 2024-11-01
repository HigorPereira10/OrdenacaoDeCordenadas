package br.com.projeto.ordenacao.controller.gerador_arquivo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import br.com.projeto.ordenacao.model.CriaCordenadas;

public class CriaArquivo {

	private String path;

	public CriaArquivo(String path) {
		setPath(path);
	}

	public void gerarArquivoCoordenadaDesordenada(List<Double> coordenadasGeradas, String nomeArquivo) {
		Iterator<Double> itr = coordenadasGeradas.iterator();
		while (itr.hasNext()) {
			double latitude = (double) itr.next();
			double longitude = (double) itr.next();
			String coordenadas = CriaCordenadas.formataCoordenadas(latitude, longitude);
			gravarTexto(nomeArquivo, coordenadas);
		}

	}

	public void gerarArquivoOrdenado(double[] arrQuick, String nomeArquivo) {
		try {
			StringBuilder coordenas = new StringBuilder();
			int contador = 1;
			for (double arr : arrQuick) {
				coordenas.append(" "+arr);
				if (contador == 10) {
					gravarTexto(nomeArquivo, coordenas.toString());
					coordenas.setLength(0);
					contador = 1;
				} else if (arrQuick[arrQuick.length - 1] == arr) {
					gravarTexto(nomeArquivo, coordenas.toString());
					coordenas.setLength(0);
					contador = 1;
				}
				else {
					coordenas.append(",");					
					contador++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gravarTexto(String nomeArquivo, String texto) {
		try (Writer writer = new BufferedWriter(new FileWriter(getPath() + nomeArquivo, true));) {
			writer.append(texto + "\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
