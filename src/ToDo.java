import javax.swing.*;
import java.awt.*;

public class ToDo extends JFrame {
    public ToDo(){
        setTitle("Lista de Tarefas");
        setSize(400, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        JLabel headLabel = new JLabel("Lista de Tarefas");
        headLabel.setBounds(100, 10, 200, 20);
        headLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(headLabel);

        JTextField taskField = new JTextField();
        taskField.setBounds(20, 400, 270, 50);
        taskField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(taskField);

        JButton addButton = new JButton("Adicionar");
        addButton.setBounds(300, 400, 80, 50);
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        add(addButton);
    }
}