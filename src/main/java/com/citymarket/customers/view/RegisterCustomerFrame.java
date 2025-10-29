package com.citymarket.customers.view;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterCustomerFrame extends JFrame {

    JPanel panelPrincipal;
    JLabel labelName, labelEmail, labelPassword, labelAddress;
    JTextField fieldName, fieldEmail, fieldPassword, fieldAddress;
    JButton cancelar, continuar;

    public RegisterCustomerFrame(){
        this.setSize(800,600);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitializeComponents();
    }

    public void InitializeComponents(){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS)); //Alinea los componentes Verticalmente
        panelPrincipal.setBackground(Color.white);
        this.getContentPane().add(panelPrincipal,  BorderLayout.CENTER); //Ajusta el panel automaticamente

        panelPrincipal.add(Box.createVerticalStrut(50));

        panelPrincipal.add(Box.createHorizontalStrut(30));

        labelName = new JLabel("Digite su nombre completo");
        labelName.setPreferredSize(new Dimension(250,50));
        labelName.setForeground(Color.BLACK);
        labelName.setFont(new Font("Arial", Font.BOLD, 18));
        panelPrincipal.add(labelName);

        panelPrincipal.add(Box.createVerticalStrut(50));

        fieldName = new JTextField();
        fieldName.setPreferredSize(new Dimension(250,30));
        fieldName.setMaximumSize(new Dimension(250,30));
        fieldName.setForeground(Color.BLACK);
        fieldName.setFont(new Font("Arial", 0, 14));
        fieldName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true), BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        panelPrincipal.add(fieldName);

        labelEmail = new JLabel("Digite un correo");

        labelPassword = new JLabel("Cree una contraseña");

        labelAddress = new JLabel("Digite su direccion");
    }
}
