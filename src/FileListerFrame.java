import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.DirectoryStream;

public class FileListerFrame extends JFrame {
    private JLabel title = new JLabel("File Lister");
    private JPanel titlePanel = new JPanel();
    private JTextArea textArea = new JTextArea(50,70);;
    private JScrollPane scroller = new JScrollPane(textArea);;
    private JPanel txtPanel = new JPanel();
    private JButton selectDirButton = new JButton("Select Directory");;
    private JButton quitButton = new JButton("Quit");;
    private JPanel topPanel = new JPanel();
    private JPanel main = new JPanel(new BorderLayout());

    private File selectedFile;

    public FileListerFrame()
    {
        setTitle("File Lister");
        setSize(800,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        selectDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (fileChooser.showDialog(main, "Select a Directory") == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();

                    textArea.setText("Directory Selected: " + selectedFile.getName());
                    recListFiles(selectedFile);
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        title.setFont(new Font("Monospaced", Font.BOLD, 48));
        title.setForeground(new Color(85, 3, 3));
        title.setHorizontalAlignment(JLabel.CENTER);

        title.setHorizontalTextPosition(JLabel.CENTER);
        topPanel.setLayout(new GridLayout(2,1));
        topPanel.add(title);
        topPanel.add(selectDirButton);


        main.add(topPanel, BorderLayout.NORTH);
        txtPanel.add(scroller);
        main.add(txtPanel, BorderLayout.CENTER);
        main.add(quitButton, BorderLayout.SOUTH);
        add(main);
    }


    public void recListFiles(File j)
    {
        if (j.isDirectory())
        {
            textArea.append("\n\n" + j.getName());
            for (File m:j.listFiles())
            {
                recListFiles(m);
            }
        }
        else
        {
            textArea.append("\n" + j.getName());
        }
    }
}
