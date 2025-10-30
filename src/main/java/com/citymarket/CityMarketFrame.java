package com.citymarket;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CityMarketFrame extends JFrame {

    JPanel panelPrincipal, panelIzquierdo, panelNorte;
    JLabel imageLabel;

    public CityMarketFrame(){
        this.setSize(1000, 800);
        this.setTitle("Catalogo Productos");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        InitializeComponents();
    }

    public void InitializeComponents(){
        panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(new Color(13, 138, 227));
        panelIzquierdo.setPreferredSize(new Dimension(200, 800));
        this.getContentPane().add(panelIzquierdo, BorderLayout.WEST);

        ImageIcon image = new ImageIcon(getClass().getResource("/icons/logoSupermercado.png"));
        imageLabel = new JLabel(image);
        panelIzquierdo.add(imageLabel);


    }
}
