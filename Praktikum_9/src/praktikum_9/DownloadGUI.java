package praktikum_9;

import javax.swing.*;
import java.awt.*;

public class DownloadGUI extends JFrame {

    private JProgressBar bar1, bar2, bar3;
    private JButton downloadBtn;

    public DownloadGUI() {
        setTitle("Download Manager App");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

      
        JLabel title = new JLabel("Download Manager App", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        add(title, BorderLayout.NORTH);

       
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        bar1 = new JProgressBar(0, 100);
        bar2 = new JProgressBar(0, 100);
        bar3 = new JProgressBar(0, 100);

        bar1.setPreferredSize(new Dimension(250, 20));
        bar2.setPreferredSize(new Dimension(250, 20));
        bar3.setPreferredSize(new Dimension(250, 20));

        bar1.setStringPainted(false);
        bar2.setStringPainted(false);
        bar3.setStringPainted(false);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("File 1"), gbc);
        gbc.gridx = 1;
        panel.add(bar1, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("File 2"), gbc);
        gbc.gridx = 1;
        panel.add(bar2, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("File 3"), gbc);
        gbc.gridx = 1;
        panel.add(bar3, gbc);

        add(panel, BorderLayout.CENTER);

        downloadBtn = new JButton("Downloading");
        downloadBtn.setFocusable(false);
        downloadBtn.addActionListener(e -> startDownload());

        JPanel btnPanel = new JPanel();
        btnPanel.add(downloadBtn);

        add(btnPanel, BorderLayout.SOUTH);
    }

    private void startDownload() {

        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <= 100; i += 10) {
                int val = i;
                SwingUtilities.invokeLater(() -> bar1.setValue(val));
                try { Thread.sleep(300); } catch (Exception ignored) {}
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i <= 100; i += 10) {
                int val = i;
                SwingUtilities.invokeLater(() -> bar2.setValue(val));
                try { Thread.sleep(350); } catch (Exception ignored) {}
            }
        });

         Thread t3 = new Thread(() -> {
            for (int i = 0; i <= 100; i += 10) {
                int val = i;
                SwingUtilities.invokeLater(() -> bar3.setValue(val));
                try { Thread.sleep(450); } catch (Exception ignored) {}
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DownloadGUI().setVisible(true));
    }
}
