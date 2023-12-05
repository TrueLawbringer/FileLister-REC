import javax.swing.*;

public class FileListerRunner {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            FileListerFrame gui = new FileListerFrame();
            gui.setVisible(true);

        });
}
}
