package system;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TimeThread extends Thread {
    private JTextField timeField;

    public TimeThread(JTextField timeField) {
        this.timeField = timeField;
    }

    public void startUpdating(Locale locale) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateTime(locale);
            }
        }, 0, 1000); // обновлять каждую минуту
    }

    private void updateTime(Locale locale) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);
        String formattedTime = now.format(formatter);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                timeField.setText(formattedTime);
            }
        });
    }
}