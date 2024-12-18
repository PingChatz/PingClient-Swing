package view;

import interface_adapter.login.LoginController;
import interface_adapter.signup.SignupController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageView extends JPanel {

    public HomePageView(LoginController loginController, SignupController signupController) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // set gradient background
        setBackground(new Color(0, 0, 0, 0)); // Transparent for gradient effect
        setOpaque(false);

        JLabel titleLabel = new JLabel("PingChatz");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

        // create buttons
        JButton loginButton = createButton("Login");
        JButton signupButton = createButton("Signup");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signupController.switchToLoginView();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginController.switchToSignUpView();
            }
        });

        // add components
        add(titleLabel);
        add(Box.createVerticalGlue());
        add(loginButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(signupButton);
        add(Box.createVerticalGlue());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint gradient
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(131, 58, 180),
                getWidth(), getHeight(), new Color(253, 29, 29)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // add button gradient
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(255, 255, 255),
                        getWidth(), getHeight(), new Color(240, 240, 240));
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                // give buttons a border
                g2.setColor(new Color(131, 58, 180));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setForeground(new Color(131, 58, 180));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(253, 29, 29)); // change text color on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(new Color(131, 58, 180)); // reset text color after hover
            }
        });

        return button;
    }
}
