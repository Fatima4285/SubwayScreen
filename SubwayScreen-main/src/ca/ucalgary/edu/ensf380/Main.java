package ca.ucalgary.edu.ensf380;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JTextArea outputTextArea;
    private JButton stopButton;
    private Process process;
    private SimpleDateFormat dateFormat;
    private TrainInfo trainMapPanel;
    private ExecutorService executorService;
    private static String enteredTrain;

    public Main() {
        super("Subway Simulator Screen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dateFormat = new SimpleDateFormat("HH:mm:ss");
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        trainMapPanel = new TrainInfo(enteredTrain);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(stopButton, BorderLayout.SOUTH);
        contentPane.add(trainMapPanel, BorderLayout.EAST);
        setContentPane(contentPane);

        setSize(1200, 600);
        setVisible(true);

        executorService = Executors.newSingleThreadExecutor();

        try {
            String[] command = {
                "java", "-jar",
                "C:\\Users\\hoode\\Downloads\\ensf380fall\\SubwayScreen\\SubwayScreen-main\\exe\\SubwaySimulator.jar",
                "--in", "C:\\Users\\hoode\\Downloads\\ensf380fall\\SubwayScreen\\SubwayScreen-main\\data\\subway.csv",
                "--out", "C:\\Users\\hoode\\Downloads\\ensf380fall\\SubwayScreen\\SubwayScreen-main\\out"
            };
            process = new ProcessBuilder(command).start();
            executorService.submit(() -> {
                try (InputStream inputStream = process.getInputStream();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        final String finalLine = line;
                        final String formattedLine = formatOutput(finalLine);
                        SwingUtilities.invokeLater(() -> {
                            outputTextArea.append(formattedLine + "\n");
                            outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength());
                            updateMapData(finalLine);
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (process != null) {
                    process.destroy();
                }
                dispose();
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (process != null) {
                process.destroy();
            }
            System.exit(0);
        }));
    }

    private String formatOutput(String line) {
        String timestamp = dateFormat.format(new Date());
        return String.format("[%s] %s", timestamp, line);
    }

    private void updateMapData(String line) {
        if (line.startsWith("Train positions:")) {
            line = line.replace("Train positions:", "").trim();
            String[] routes = line.split("\n");
            Map<String, String[]> trainRoutes = new HashMap<>();

            for (String route : routes) {
                String[] parts = route.split(":");
                if (parts.length > 1) {
                    String trainId = parts[0].trim();
                    String[] stations = parts[1].split(",");
                    trainRoutes.put(trainId, stations);
                }
            }
            trainMapPanel.setTrainRoutes(trainRoutes);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == stopButton) {
            if (process != null) {
                process.destroy();
            }
            stopButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            enteredTrain = args[0];
        }
        SwingUtilities.invokeLater(() -> new Main());
    }
}