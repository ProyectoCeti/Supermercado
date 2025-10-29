package com.citymarket.login.view;

import com.citymarket.customers.view.RegisterCustomerFrame;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    JPanel panelPrincipal, panelNorte, panelSur;
    JLabel labelImage, label, label2, lblCopyright;
    JTextField textUser, textPassword;
    JButton buttonRegister, buttonLogin;

    public LoginFrame() {
        this.setSize(600, 700);
        this.setTitle("Mi tienda - Sistema de Acceso");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false); //Deshabilitamos el boton para hacer mas grande o pequeña la interfaz
        InitializeComponents();
    }

    public void InitializeComponents(){

        panelNorte = new JPanel();
        panelNorte.setBackground(new Color(13, 138, 227));
        panelNorte.setPreferredSize(new Dimension(600, 100));
        panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER)); //Ajusta la forma en la que se agregaran los componetes
        this.getContentPane().add(panelNorte, BorderLayout.NORTH); //Ajusta el panel para que este en la parte norte

        ImageIcon image = new ImageIcon(getClass().getResource("/icons/logoSupermercado.png"));
        labelImage = new JLabel(image);
        panelNorte.add(labelImage);

        panelSur = new JPanel();
        panelSur.setBackground(new Color(13, 138, 227));
        panelSur.setPreferredSize(new Dimension(600, 50));
        this.getContentPane().add(panelSur, BorderLayout.SOUTH);

        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.white);
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS)); //Alinea los componentes verticalmente
        this.getContentPane().add(panelPrincipal, BorderLayout.CENTER); //Ajusta el panel automaticamente

        label = new JLabel("Inicio de Sesión");
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Arial", Font.BOLD, 28));
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar los componentes
        panelPrincipal.add(label);

        panelPrincipal.add(Box.createVerticalStrut(15)); // Espacio entre labels

        label2 = new JLabel("Ingrese su usuario y contraseña");
        label2.setForeground(new Color(100, 110, 120));
        label2.setFont(new Font("Arial", 0, 14));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar los componentes
        panelPrincipal.add(label2);

        panelPrincipal.add(Box.createVerticalStrut(35));

        textUser = new JTextField("Ingrese su usuario");
        textUser.setPreferredSize(new Dimension(350, 45));
        textUser.setMaximumSize(new Dimension(350, 45));
        textUser.setFont(new Font("Arial", Font.PLAIN, 14));
        textUser.setForeground(new Color(100, 110, 120));
        textUser.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true), BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        panelPrincipal.add(textUser);

        textUser.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (textUser.getText().equals("Ingrese su usuario")) {
                    textUser.setText("");
                    textUser.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textUser.getText().isEmpty()) {
                    textUser.setText("Ingrese su usuario");
                    textUser.setForeground(Color.GRAY);
                }
            }
        });

        panelPrincipal.add(Box.createVerticalStrut(35));

        textPassword = new  JTextField("Ingrese su contraseña");
        textPassword.setPreferredSize(new Dimension(350, 45));
        textPassword.setMaximumSize(new Dimension(350, 45));
        textPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        textPassword.setForeground(new Color(100, 110, 120));
        textPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true), BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        panelPrincipal.add(textPassword);

        textPassword.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (textPassword.getText().equals("Ingrese su contraseña")) {
                    textPassword.setText("");
                    textPassword.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (textPassword.getText().isEmpty()) {
                    textPassword.setText("Ingrese su contraseña");
                    textPassword.setForeground(Color.GRAY);
                }
            }
        });

        //Boton para registrar un cliente
        buttonRegister = new JButton("Registrarse");
        buttonRegister.setPreferredSize(new Dimension(350, 45));
        buttonRegister.setMaximumSize(new Dimension(350, 45));
        buttonRegister.setBorderPainted(false);
        buttonRegister.setContentAreaFilled(false);
        buttonRegister.setForeground(new Color(100, 110, 120));
        buttonRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonRegister.setFont(new Font("Arial", Font.PLAIN, 14));
        buttonRegister.setAlignmentX(Component.RIGHT_ALIGNMENT);

        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterCustomerFrame reg = new RegisterCustomerFrame();
                reg.setVisible(true);
            }
        });
        panelPrincipal.add(buttonRegister);

        panelPrincipal.add(Box.createVerticalStrut(35));

        //Botono para el incio de sesion
        buttonLogin = new JButton("Inicar Sesion");
        buttonLogin.setPreferredSize(new Dimension(200, 40));
        buttonLogin.setMaximumSize(new Dimension(200, 40));
        buttonLogin.setBackground(new Color(13, 138, 227));
        buttonLogin.setForeground(Color.WHITE);
        buttonLogin.setFont(new Font("Arial", Font.BOLD, 14));
        buttonLogin.setBorderPainted(false);
        buttonLogin.setFocusPainted(false);
        buttonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(buttonLogin);

        lblCopyright = new JLabel("© 2025 CityMarket - Todos los derechos reservados");
        lblCopyright.setForeground(new Color(62, 62, 62));
        lblCopyright.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCopyright.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelSur.add(lblCopyright);
    }
}
