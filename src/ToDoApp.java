import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ToDoApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private List<String> tarefas;
    private JPanel tarefasPanel;
    private DefaultListModel<String> tarefasListModel;
    private JTextField txtEditNome;
    private int tarefaEditIndex;

    public ToDoApp() {
        setTitle("Lista de Tarefas");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Inicializar a lista de tarefas
        tarefas = new ArrayList<>();
        tarefasListModel = new DefaultListModel<>();

        // Layout principal
        setLayout(new BorderLayout());

        // Criar a barra lateral
        JPanel sidePanel = createSidebarPanel();

        // Criar painel principal com CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Criar os paineis de conteudo
        tarefasPanel = createTarefasPanel();
        JPanel addTarefaPanel = createAddTarefaPanel();
        JPanel editTarefaPanel = createEditTarefaPanel();

        // Adicionar os paineis ao CardLayout
        mainPanel.add(tarefasPanel, "Tarefas");
        mainPanel.add(addTarefaPanel, "Adicionar");
        mainPanel.add(editTarefaPanel, "Editar");

        // Adicionar componentes à janela principal
        add(sidePanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createSidebarPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setPreferredSize(new Dimension(150, 400));

        JButton btnTarefas = new JButton("Lista de Tarefas");
        JButton btnAddTarefa = new JButton("Adicionar Tarefa");

        // Estilizar botões com cores RGB
        btnTarefas.setBackground(new Color(70, 130, 180)); // SteelBlue
        btnTarefas.setForeground(Color.WHITE);
        btnAddTarefa.setBackground(new Color(60, 179, 113)); // MediumSeaGreen
        btnAddTarefa.setForeground(Color.WHITE);

        // Ações dos botões
        btnTarefas.addActionListener(e -> cardLayout.show(mainPanel, "Tarefas"));
        btnAddTarefa.addActionListener(e -> cardLayout.show(mainPanel, "Adicionar"));

        panel.add(btnTarefas);
        panel.add(btnAddTarefa);

        return panel;
    }

    private JPanel createTarefasPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adicionar margens
        panel.setLayout(new BorderLayout());

        JList<String> tarefasList = new JList<>(tarefasListModel);
        tarefasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tarefasList), BorderLayout.CENTER);

        JButton btnEditar = new JButton("Editar Tarefa");
        btnEditar.setBackground(new Color(255, 165, 0)); // Orange
        btnEditar.setForeground(Color.WHITE);
        btnEditar.addActionListener(e -> {
            int selectedIndex = tarefasList.getSelectedIndex();
            if (selectedIndex != -1) {
                tarefaEditIndex = selectedIndex;
                txtEditNome.setText(tarefas.get(selectedIndex));
                cardLayout.show(mainPanel, "Editar");
            }
        });
        panel.add(btnEditar, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAddTarefaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adicionar margens
        
        JLabel lblNome = new JLabel("Nome da Tarefa:");
        JTextField txtNome = new JTextField(20);
        txtNome.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNome.setPreferredSize(new Dimension(200, 20));
        
        JButton btnSalvar = new JButton("Salvar Tarefa");
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalvar.setPreferredSize(new Dimension(50, 15));

        // Estilizar botão com cores RGB
        btnSalvar.setBackground(new Color(60, 179, 113)); // MediumSeaGreen
        btnSalvar.setForeground(Color.WHITE);

        // Adicionar evento ao botão salvar
        btnSalvar.addActionListener(e -> {
            String tarefa = txtNome.getText();
            if (!tarefa.isEmpty()) {
                tarefas.add(tarefa);
                tarefasListModel.addElement(tarefa);
                txtNome.setText("");
                cardLayout.show(mainPanel, "Tarefas");
            }
        });

        panel.add(lblNome);
        panel.add(txtNome);
        panel.add(btnSalvar);

        return panel;
    }

    private JPanel createEditTarefaPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adicionar margens
        
        JLabel lblEditNome = new JLabel("Editar Nome da Tarefa:");
        txtEditNome = new JTextField(20);
        txtEditNome.setFont(new Font("Arial", Font.PLAIN, 14));
        txtEditNome.setPreferredSize(new Dimension(200, 20));
        
        JButton btnAtualizar = new JButton("Atualizar Tarefa");
        btnAtualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAtualizar.setPreferredSize(new Dimension(50, 15));

        // Estilizar botão com cores RGB
        btnAtualizar.setBackground(new Color(60, 179, 113)); // MediumSeaGreen
        btnAtualizar.setForeground(Color.WHITE);

        // Adicionar evento ao botão atualizar
        btnAtualizar.addActionListener(e -> {
            String tarefa = txtEditNome.getText();
            if (!tarefa.isEmpty()) {
                tarefas.set(tarefaEditIndex, tarefa);
                tarefasListModel.set(tarefaEditIndex, tarefa);
                txtEditNome.setText("");
                cardLayout.show(mainPanel, "Tarefas");
            }
        });

        panel.add(lblEditNome);
        panel.add(txtEditNome);
        panel.add(btnAtualizar);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoApp app = new ToDoApp();
            app.setVisible(true);
        });
    }
}
