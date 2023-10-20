package ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JLabelStatus extends JLabel {

    private Timer timer;
    private int ms;

    private final ActionListener taskPerformer;

    public JLabelStatus(int ms){
        super();
        this.ms = ms;
        this.taskPerformer = e -> super.setText("");
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        if (this.timer != null) {
            this.timer.stop();
        }
        this.timer = new Timer(this.ms, this.taskPerformer);
        this.timer.setRepeats(false);
        this.timer.start();
    }

    public void setError(String text) {
        this.setForeground(java.awt.Color.RED);
        this.setText(text);
    }

    public void setSuccess(String text) {
        this.setForeground(java.awt.Color.GREEN);
        this.setText(text);
    }

    public JLabelStatus() {
        this(2000);
    }
}
