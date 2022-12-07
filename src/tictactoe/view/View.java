package tictactoe.view;

import tictactoe.controller.Controller;
import tictactoe.controller.Messages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Initiualizes the JFrame, the JPanel where the game gets displayed and the JLabel that is indicating the state of the game.
 * Sends user actions to the <code>Controller</code>.
 */
public class View {
    private Controller controller;
    private JFrame frame;
    private JPanel panel;
    private JLabel[] labels;
    private JLabel statusLabel;
    private Messages messages;
    private Color colorHuman;
    private Color colorCPU;
    private String stringCPU;
    private String stringHuman;
    private String stringNone;

    /**
     * Calls methods to create the JFrame, the JPanel for the game with its JLabels and a JLabel to display the status of the game.
     * Sets the used Strings and Colors for the human and CPU player.
     *
     * @param controller the controller object
     */
    public View(Controller controller) {
        this.controller = controller;
        stringHuman = "X";
        stringCPU = "O";
        stringNone = "";
        colorHuman = Color.BLUE;
        colorCPU = Color.RED;

        frame = createFrame();
        panel = createPanel();
        statusLabel = createStatusLabel();
        labels = createSpaceLabels();
        frame.pack();
    }

    /**
     * Creates the JFrame.
     *
     * @return the created JFrame
     */
    private JFrame createFrame() {
        JFrame f = new JFrame();

        f.setLayout(new BorderLayout(0, 0));
        f.setTitle("TicTacToe");
        f.setSize(500, 500);
        f.setMinimumSize(new Dimension(500, 500));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                for (int i = 0; i < 9; i++)
                    labels[i].setFont(new Font("Arial", Font.PLAIN, Math.min(
                            labels[i].getWidth(), labels[i].getHeight()
                    )));
            }
        });

        return f;
    }

    /**
     * Creates the JPanel that contains the JLabels that containt the game board.
     *
     * @return the created JPanel
     */
    private JPanel createPanel() {
        JPanel p = new JPanel();

        p.setLayout(new GridLayout(3, 3, 3, 3));
        p.setBackground(Color.DARK_GRAY);

        frame.add(p, BorderLayout.CENTER);

        return p;
    }

    /**
     * Creates the JLabel that displays the status of the game.
     *
     * @return the created JLabel
     */
    private JLabel createStatusLabel() {
        JLabel sl = new JLabel();

        sl.setBackground(Color.DARK_GRAY);
        sl.setForeground(Color.WHITE);
        sl.setHorizontalAlignment(SwingConstants.CENTER);
        sl.setFont(new Font("Arial", Font.PLAIN, 30));
        sl.setOpaque(true);
        sl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onMouseClicked(e);
            }
        });

        frame.add(sl, BorderLayout.SOUTH);

        return sl;
    }

    /**
     * Creates the JLabels used for the game.
     *
     * @return Array of the created JLabels
     */
    private JLabel[] createSpaceLabels() {
        JLabel[] ls = new JLabel[9];

        for (int i = 0; i < 9; i++) {
            ls[i] = new JLabel("");
            ls[i].setBackground(Color.WHITE);
            ls[i].setOpaque(true);
            ls[i].setHorizontalAlignment(SwingConstants.CENTER);
            ls[i].setVerticalAlignment(SwingConstants.CENTER);
            ls[i].setFocusable(false);
            ls[i].addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) {
                    onMouseClicked(e);
                }
            });
            ls[i].setFont(new Font("Arial", Font.PLAIN, 40));

            panel.add(ls[i]);
        }

    return ls;
    }

    /**
     * Displays a message box with given texts.
     *
     * @param messageText the text of the message
     * @param titleText  the text of the button
     */
    public void displayMessage(String messageText, String titleText) {
        JOptionPane.showMessageDialog(null, messageText, titleText, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * When mouse is clicked, determine where and tell the <code>Controller</code>.
     *
     * @param e the MouseEvent
     */
    private void onMouseClicked(MouseEvent e) {
        if (e.getSource() == statusLabel)
            controller.statusLabelClicked();
        else {
            for (int i = 0; i < 9; i++)
                if (e.getSource() == labels[i]) {
                    controller.checkBox(i);
                    break;
                }
        }
    }

    /**
     * Sets the text of the status label.
     *
     * @param status string that will be used for the status label
     */
    public void setStatusText(String status) {
        statusLabel.setText(status);
    }

    /**
     * Sets a specific space to human.
     *
     * @param i tbe space to set
     */
    public void setSpaceToHuman(int i) {
        labels[i].setText(stringHuman);
        labels[i].setForeground(colorHuman);
    }

    /**
     * Sets a specific space to CPU.
     *
     * @param i the space to set
     */
    public void setSpaceToCPU(int i) {
        labels[i].setText(stringCPU);
        labels[i].setForeground(colorCPU);
    }

    /**
     * Sets a specific space to empty/free.
     *
     * @param i the space to set
     */
    public void setSpaceToFree(int i) {
        labels[i].setText(stringNone);
    }

    /**
     * Sets the JFrame to visible.
     */
    public void show() {
        frame.setVisible(true);
    }
}
