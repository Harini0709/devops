import javax.swing.*;
import java.awt.*;

public class TodoList extends JFrame {

    private JTextField taskInput;
    private DefaultListModel<String> taskModel;
    private JList<String> taskList;

    public TodoList() {
        setTitle("My Todo List");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel("MY TODO LIST", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setForeground(Color.BLUE);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        taskInput = new JTextField();
        taskInput.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Task");
        JButton completeButton = new JButton("Complete Task");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);

        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));

        add(heading, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.PAGE_START);
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            String task = taskInput.getText().trim();
            if (!task.isEmpty()) {
                taskModel.addElement("[ ] " + task);
                taskInput.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a task!");
            }
        });

        deleteButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                taskModel.remove(selected);
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to delete!");
            }
        });

        completeButton.addActionListener(e -> {
            int selected = taskList.getSelectedIndex();
            if (selected != -1) {
                String task = taskModel.get(selected);
                if (task.startsWith("[ ] ")) {
                    taskModel.set(selected, "[DONE] " + task.substring(4));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to complete!");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoList::new);
    }
}
