package com.citymarket.customers.view;

import com.citymarket.customers.controller.CustomerController;
import com.citymarket.customers.service.CustomerService;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterCustomerFrame extends JFrame {

    JPanel panelPrincipal;
    JLabel labelName, labelEmail, labelPassword, labelAddress, labelRegister;
    JTextField fieldName, fieldEmail, fieldPassword, fieldAddress;
    JButton cancelar, continuar;
    CustomerService customerService;

    public RegisterCustomerFrame(){
        this.customerService = new CustomerService();
        this.setSize(800,600);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InitializeComponents();
    }

    public void InitializeComponents(){
        panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(Color.white);
        this.getContentPane().add(panelPrincipal,  BorderLayout.CENTER); //Ajusta el panel automaticamente

        GridBagConstraints gbc = new GridBagConstraints(); //Crea el layout donde se guardan todas las instrucciones

        gbc.anchor = GridBagConstraints.WEST; //Configura donde se va a colocar el componente

        //Configuramos los espacios de separacion Arriba, Izquierda, Abajo, Derecha
        gbc.insets = new Insets(-500,20,0,0);
        gbc.weightx = 0.0; //Configura si el componente ocupa espacio extra
        gbc.gridx = 0; //Columnas
        gbc.gridy = 0; //Filas

        labelRegister = new JLabel("Registro");
        labelRegister.setPreferredSize(new Dimension(100,30));
        labelRegister.setForeground(Color.BLACK);
        labelRegister.setFont(new Font("Arial", Font.BOLD, 24));
        panelPrincipal.add(labelRegister, gbc);

        gbc.insets = new Insets(-380,-420,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 1;
        gbc.gridy = 1;

        labelName = new JLabel("Digite su nombre completo");
        labelName.setPreferredSize(new Dimension(250,30));
        labelName.setForeground(Color.BLACK);
        labelName.setFont(new Font("Arial", Font.BOLD, 18));
        panelPrincipal.add(labelName, gbc);

        gbc.insets = new Insets(-310, -420,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 1;
        gbc.gridy = 1;

        fieldName = new JTextField();
        fieldName.setPreferredSize(new Dimension(250,30));
        fieldName.setMaximumSize(new Dimension(250,30));
        fieldName.setForeground(Color.BLACK);
        fieldName.setFont(new Font("Arial", 0, 14));
        fieldName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true), BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        panelPrincipal.add(fieldName, gbc);

        gbc.insets = new Insets(-220,-420,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 2;
        gbc.gridy = 2;

        labelEmail = new JLabel("Digite su correo");
        labelEmail.setPreferredSize(new Dimension(250,30));
        labelEmail.setForeground(Color.BLACK);
        labelEmail.setFont(new Font("Arial", Font.BOLD, 18));
        panelPrincipal.add(labelEmail, gbc);

        gbc.insets = new Insets(-150,-420,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 2;
        gbc.gridy = 2;

        fieldEmail = new JTextField();
        fieldEmail.setPreferredSize(new Dimension(250,30));
        fieldEmail.setMaximumSize(new Dimension(250,30));
        fieldEmail.setForeground(Color.BLACK);
        fieldEmail.setFont(new Font("Arial", 0, 14));
        fieldEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true), BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        panelPrincipal.add(fieldEmail, gbc);

        gbc.insets = new Insets(-80,-420,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 3;
        gbc.gridy = 3;

        labelAddress = new JLabel("Digite su direccion");
        labelAddress.setPreferredSize(new Dimension(250,30));
        labelAddress.setForeground(Color.BLACK);
        labelAddress.setFont(new Font("Arial", Font.BOLD, 18));
        panelPrincipal.add(labelAddress, gbc);

        gbc.insets = new Insets(-10,-420,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 3;
        gbc.gridy = 3;

        fieldAddress = new JTextField();
        fieldAddress.setPreferredSize(new Dimension(250,30));
        fieldAddress.setMaximumSize(new Dimension(250,30));
        fieldAddress.setForeground(Color.BLACK);
        fieldAddress.setFont(new Font("Arial", 0, 14));
        fieldAddress.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true), BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        panelPrincipal.add(fieldAddress, gbc);

        gbc.insets = new Insets(-30,-420,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 4;
        gbc.gridy = 4;

        labelPassword = new JLabel("Cree una contraseña");
        labelPassword.setPreferredSize(new Dimension(250,30));
        labelPassword.setForeground(Color.BLACK);
        labelPassword.setFont(new Font("Arial", Font.BOLD, 18));
        panelPrincipal.add(labelPassword, gbc);

        gbc.insets = new Insets(40,-420,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 4;
        gbc.gridy = 4;

        fieldPassword = new JTextField();
        fieldPassword.setPreferredSize(new Dimension(250,30));
        fieldPassword.setMaximumSize(new Dimension(250,30));
        fieldPassword.setForeground(Color.BLACK);
        fieldPassword.setFont(new Font("Arial", 0, 14));
        fieldPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true), BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        panelPrincipal.add(fieldPassword, gbc);

        gbc.insets = new Insets(30,-420,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 5;
        gbc.gridy = 5;

        cancelar = new JButton("Cancelar");
        cancelar.setForeground(Color.WHITE);
        cancelar.setFont(new Font("Arial", Font.BOLD, 14));
        cancelar.setPreferredSize(new Dimension(120,30));
        cancelar.setBackground(new Color(246, 7, 7));
        cancelar.setBorderPainted(false);
        cancelar.setFocusPainted(false);
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Cerramos la ventana
                dispose();
            }
        });
        panelPrincipal.add(cancelar, gbc);

        gbc.insets = new Insets(-30,-280,0,0);
        gbc.weightx = 0.0;
        gbc.gridx = 6;
        gbc.gridy = 6;

        continuar = new JButton("Continuar");
        continuar.setForeground(Color.WHITE);
        continuar.setFont(new Font("Arial", Font.BOLD, 14));
        continuar.setPreferredSize(new Dimension(120,30));
        continuar.setBackground(new Color(20, 243, 4));
        continuar.setBorderPainted(false);
        continuar.setFocusPainted(false);
        continuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String name = fieldName.getText();
                    String email = fieldEmail.getText();
                    String address = fieldAddress.getText();
                String password = fieldPassword.getText();

                    if(name.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos");
                        return;
                    }
                    else {
                        CustomerController c = new CustomerController(customerService);
                        c.registrar(name, email, address, password);
                        JOptionPane.showMessageDialog(null,"Usuario Registrado Exitosamente.");
                    }
            }
        });
        panelPrincipal.add(continuar, gbc);
    }
}
