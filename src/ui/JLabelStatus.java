package ui;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Classe permettant de gérer un label avec un text qui disparait au bout d'un certain temps.
 * Utilisé comme status bar.
 */
public class JLabelStatus extends JLabel {

    private Timer timer;
    private final int ms;

    private final ActionListener taskPerformer;

    /**
     * Constructeur
     *
     * @param ms le nombre de millisecondes avant que le texte disparaisse.
     */
    public JLabelStatus(int ms) {
        super();
        this.ms = ms;
        this.taskPerformer = e -> super.setText("");
    }

    /**
     * Constructeur
     * Par défaut, le texte disparait au bout de 2 secondes.
     */
    public JLabelStatus() {
        this(2000);
    }

    /**
     * Change le texte du label et lance le timer.
     *
     * @param text le nouveau texte.
     * @see JLabel#setText(String)
     */
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

    /**
     * Permet d'afficher un message d'erreur.
     * Le texte sera en rouge.
     *
     * @param text le texte à afficher.
     * @see JLabelStatus#setText(String)
     */
    public void setError(String text) {
        this.setForeground(java.awt.Color.RED);
        this.setText(text);
    }

    /**
     * Permet d'afficher un message de succès.
     * Le texte sera en vert.
     *
     * @param text le texte à afficher.
     * @see JLabelStatus#setText(String)
     */
    public void setSuccess(String text) {
        this.setForeground(java.awt.Color.GREEN);
        this.setText(text);
    }
}
