//@codewithcurious.com
// Enhanced Digital Clock with additional features

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.SwingUtilities;   // <-- Missing import added

public class Digital_clock_enhanced extends JFrame {

    private JLabel timeLabel;
    private JLabel dateLabel;
    private JLabel secondsLabel;
    private JLabel timezoneLabel;
    private JComboBox<String> timezoneCombo;
    private Timer timer;
    private boolean showSeconds = true;
    private boolean is24HourFormat = true;

    public Digital_clock_enhanced() {
        setTitle("Enhanced Digital Clock");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.BLACK);

        // Time display panel
        JPanel timePanel = new JPanel(new BorderLayout());
        timePanel.setBackground(Color.BLACK);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Digital-7", Font.PLAIN, 90));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setForeground(new Color(0, 255, 0));
        timePanel.add(timeLabel, BorderLayout.CENTER);

        // Date panel
        JPanel datePanel = new JPanel();
        datePanel.setBackground(Color.BLACK);
        
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        dateLabel.setForeground(Color.CYAN);
        datePanel.add(dateLabel);

        // Control panel
        JPanel controlPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        controlPanel.setBackground(Color.BLACK);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Timezone selector
        String[] timezones = {"Local", "GMT", "EST", "PST", "IST", "JST"};
        timezoneCombo = new JComboBox<>(timezones);
        timezoneCombo.setBackground(Color.DARK_GRAY);
        timezoneCombo.setForeground(Color.WHITE);
        timezoneCombo.addActionListener(e -> updateTimeAndDate());

        // Format toggle button
        JButton formatButton = new JButton("12/24 Hour");
        formatButton.setBackground(Color.DARK_GRAY);
        formatButton.setForeground(Color.WHITE);
        formatButton.addActionListener(e -> {
            is24HourFormat = !is24HourFormat;
            updateTimeAndDate();
        });

        // Seconds toggle button
        JButton secondsButton = new JButton("Toggle Seconds");
        secondsButton.setBackground(Color.DARK_GRAY);
        secondsButton.setForeground(Color.WHITE);
        secondsButton.addActionListener(e -> {
            showSeconds = !showSeconds;
            updateFont();
        });

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> System.exit(0));

        controlPanel.add(timezoneCombo);
        controlPanel.add(formatButton);
        controlPanel.add(secondsButton);
        controlPanel.add(exitButton);

        // Add all panels
        mainPanel.add(timePanel, BorderLayout.CENTER);
        mainPanel.add(datePanel, BorderLayout.NORTH);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Start timer
        timer = new Timer(1000, e -> updateTimeAndDate());
        timer.start();
        
        updateTimeAndDate();
    }

    private void updateFont() {
        if (showSeconds) {
            timeLabel.setFont(new Font("Digital-7", Font.PLAIN, 90));
        } else {
            timeLabel.setFont(new Font("Digital-7", Font.PLAIN, 110));
        }
    }

    private void updateTimeAndDate() {
        Calendar calendar = Calendar.getInstance();
        
        // Apply timezone
        String selectedTZ = (String) timezoneCombo.getSelectedItem();
        switch (selectedTZ) {
            case "GMT":
                calendar.setTimeZone(TimeZone.getTimeZone("GMT"));
                break;
            case "EST":
                calendar.setTimeZone(TimeZone.getTimeZone("America/New_York"));
                break;
            case "PST":
                calendar.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                break;
            case "IST":
                calendar.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
                break;
            case "JST":
                calendar.setTimeZone(TimeZone.getTimeZone("Japan"));
                break;
        }

        // Format time
        String timeFormat;
        if (is24HourFormat) {
            timeFormat = showSeconds ? "HH:mm:ss" : "HH:mm";
        } else {
            timeFormat = showSeconds ? "hh:mm:ss a" : "hh:mm a";
        }
        
        SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
        if (!selectedTZ.equals("Local")) {
            timeFormatter.setTimeZone(calendar.getTimeZone());
        }
        String time = timeFormatter.format(calendar.getTime());

        // Format date
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        if (!selectedTZ.equals("Local")) {
            dateFormatter.setTimeZone(calendar.getTimeZone());
        }
        String date = dateFormatter.format(calendar.getTime());

        // Update labels
        timeLabel.setText(time);
        dateLabel.setText(date);
        
        // Update title with timezone
        setTitle("Digital Clock - " + selectedTZ);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Digital_clock_enhanced().setVisible(true);
        });
    }
}
