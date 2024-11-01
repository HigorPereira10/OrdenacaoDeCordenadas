package br.com.projeto.ordenacao.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import br.com.projeto.ordenacao.model.ProcessadorDeOrdenacao;

public class PainelOrdenacao {
    private JPanel basePane;
    private String chosenAlgorithm = "BubbleSort";
    private String savePath = "C:\\Temp\\Coordenadas\\";
    private JTable table;
    private List<Double> importedData = new ArrayList<>();

    public PainelOrdenacao(JPanel basePane) {
        this.basePane = basePane;
        createWindow();
    }

    // Cria a interface do painel de ordenação
    private void createWindow() {
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(220, 220, 220));
        contentPane.setLayout(null);

        JLabel lbSaudacao = new JLabel("Saudações!");
        lbSaudacao.setBounds(160, 10, 200, 30);
        lbSaudacao.setForeground(Color.darkGray);
        lbSaudacao.setFont(new Font("Arial", Font.BOLD, 28));
        contentPane.add(lbSaudacao);

        JButton btnSelectPath = new JButton("Selecionar Caminho de Salvamento");
        btnSelectPath.setBounds(45, 50, 400, 30);
        btnSelectPath.addActionListener(e -> selectSavePath());
        contentPane.add(btnSelectPath);

        JLabel lbNomeArquivo = new JLabel("Informe o nome do arquivo:");
        lbNomeArquivo.setBounds(45, 90, 350, 30);
        lbNomeArquivo.setForeground(Color.black);
        contentPane.add(lbNomeArquivo);

        final JTextField campoNomeArquivo = new JTextField(20);
        campoNomeArquivo.setBounds(45, 120, 400, 30);
        campoNomeArquivo.setForeground(Color.black);
        campoNomeArquivo.setBackground(new Color(240, 240, 240));
        campoNomeArquivo.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPane.add(campoNomeArquivo);

        JLabel lbQtdCoord = new JLabel("Informe a quantidade de coordenadas:");
        lbQtdCoord.setBounds(45, 160, 350, 30);
        lbQtdCoord.setForeground(Color.black);
        contentPane.add(lbQtdCoord);

        final JTextField campoQtdCoord = new JTextField(20);
        campoQtdCoord.setBounds(45, 190, 400, 30);
        campoQtdCoord.setForeground(Color.black);
        campoQtdCoord.setBackground(new Color(240, 240, 240));
        campoQtdCoord.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPane.add(campoQtdCoord);

        JLabel lbAlgoritmo = new JLabel("Escolha o Algoritmo de Ordenação:");
        lbAlgoritmo.setBounds(45, 230, 350, 30);
        lbAlgoritmo.setForeground(Color.black);
        contentPane.add(lbAlgoritmo);

        JRadioButton rbBubbleSort = new JRadioButton("BubbleSort", true);
        rbBubbleSort.setBounds(45, 260, 100, 30);
        JRadioButton rbMergeSort = new JRadioButton("MergeSort");
        rbMergeSort.setBounds(150, 260, 100, 30);
        JRadioButton rbHeapSort = new JRadioButton("HeapSort");
        rbHeapSort.setBounds(255, 260, 100, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbBubbleSort);
        bg.add(rbMergeSort);
        bg.add(rbHeapSort);

        ActionListener radioListener = e -> chosenAlgorithm = ((JRadioButton) e.getSource()).getText();
        rbBubbleSort.addActionListener(radioListener);
        rbMergeSort.addActionListener(radioListener);
        rbHeapSort.addActionListener(radioListener);

        contentPane.add(rbBubbleSort);
        contentPane.add(rbMergeSort);
        contentPane.add(rbHeapSort);

        JButton btnGerar = new JButton("Iniciar Ordenação");
        btnGerar.setBounds(45, 300, 180, 35);
        btnGerar.setBackground(new Color(0, 153, 51));
        btnGerar.setForeground(Color.white);
        btnGerar.setFont(new Font("Arial", Font.BOLD, 15));
        btnGerar.addActionListener(e -> startSorting(campoNomeArquivo.getText(), campoQtdCoord.getText()));
        contentPane.add(btnGerar);

        JButton btnLimpar = new JButton("Resetar Campos");
        btnLimpar.setBounds(265, 300, 180, 35);
        btnLimpar.setBackground(new Color(0, 102, 204));
        btnLimpar.setForeground(Color.white);
        btnLimpar.setFont(new Font("Arial", Font.BOLD, 15));
        btnLimpar.addActionListener(e -> resetFields(campoNomeArquivo, campoQtdCoord));
        contentPane.add(btnLimpar);

        JLabel lbResultado = new JLabel("Visualização das Coordenadas:");
        lbResultado.setBounds(45, 340, 200, 30);
        contentPane.add(lbResultado);

        // Tabela para exibir coordenadas
        table = new JTable(new DefaultTableModel(new Object[]{"Coordenadas"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(45, 370, 400, 150);
        contentPane.add(scrollPane);

        JButton btnImportarArquivo = new JButton("Importar Dados Externos");
        btnImportarArquivo.setBounds(45, 540, 180, 35);
        btnImportarArquivo.addActionListener(this::importarDadosExternos);
        contentPane.add(btnImportarArquivo);

        setNewPane(contentPane, basePane);
    }

    // Reseta os campos de texto
    private void resetFields(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }

    // Inicia a ordenação com os dados informados ou importados
    private void startSorting(String nomeArquivo, String qtdCoord) {
        ProcessadorDeOrdenacao processo;

        if (!importedData.isEmpty()) {
            processo = new ProcessadorDeOrdenacao(importedData, chosenAlgorithm);
        } else {
            try {
                int quantidade = Integer.parseInt(qtdCoord);
                processo = new ProcessadorDeOrdenacao(nomeArquivo, quantidade, chosenAlgorithm, savePath);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para a quantidade de coordenadas.");
                return;
            }
        }

        String resultado = processo.iniciar();
        updateTable(processo.getCoordenadasGeradasAlgoritmo(chosenAlgorithm));
        JOptionPane.showMessageDialog(null, "Ordenação concluída em:\n" + resultado);
    }

    // Atualiza a tabela com as coordenadas
    private void updateTable(List<String> coordenadas) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (String coord : coordenadas) {
            model.addRow(new Object[]{coord});
        }
    }

    // Seleciona o caminho para salvar os arquivos
    private void selectSavePath() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showSaveDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            savePath = selectedFile.getAbsolutePath() + File.separator;
            JOptionPane.showMessageDialog(null, "Caminho selecionado: " + savePath);
        }
    }

    // Importa dados externos para a ordenação
    private void importarDadosExternos(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                importedData.clear();
                while ((line = br.readLine()) != null) {
                    importedData.add(Double.parseDouble(line.trim()));
                }
                JOptionPane.showMessageDialog(null, "Dados importados com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao importar dados: " + ex.getMessage());
            }
        }
    }

    // Configura e exibe um novo painel de conteúdo
    private void setNewPane(JPanel contentPane, JPanel basePane) {
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        contentPane.setBounds(0, 0, 600, 600);
        basePane.add(contentPane);
    }
}
