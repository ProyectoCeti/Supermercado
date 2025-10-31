package com.citymarket;

import com.citymarket.products.controller.ProductsController;
import com.citymarket.products.dto.ProductsDTO;
import com.citymarket.products.service.ProductsService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.List;

public class CityMarketFrame extends JFrame {

    JPanel panelPrincipal, panelIzquierdo, panelNorte;
    JLabel imageLabel, bienvenidaLabel, labelUser;
    JTextField fieldBuscador;
    JButton btnBuscar, btnComprar;
    JTable tablaProductos;
    DefaultTableModel modelo;
    ProductsService productsService;
    ProductsController productsController;

    public CityMarketFrame(String email){
        this.productsService = new ProductsService();
        this.productsController = new ProductsController();
        this.setSize(1000, 800);
        this.setTitle("Catalogo Productos");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        InitializeComponents(email);
        cargarProductos(); // Carga todos los productos al iniciar
    }

    public void InitializeComponents(String email){

        panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new  Color(255, 255, 255));
        this.getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;

        panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setBackground(new  Color(13, 138, 227));
        panelIzquierdo.setPreferredSize(new Dimension(200, 800));
        this.getContentPane().add(panelIzquierdo, BorderLayout.WEST);

        gbc.insets = (new Insets(-370,0,0,-130));
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        ImageIcon image = new ImageIcon(getClass().getResource("/icons/logoSupermercado.png"));
        imageLabel = new JLabel(image);
        panelIzquierdo.add(imageLabel, gbc);

        gbc.insets = (new Insets(-230,-10,0,0));
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = 1;
        gbc.gridy = 1;

        bienvenidaLabel = new JLabel("Bienvenido");
        bienvenidaLabel.setForeground(new Color(255, 255, 255));
        bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bienvenidaLabel.setPreferredSize(new Dimension(100, 30));
        panelIzquierdo.add(bienvenidaLabel, gbc);

        gbc.insets = (new Insets(-170,12,0,0));
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = 1;
        gbc.gridy = 1;

        labelUser = new JLabel(email);
        labelUser.setForeground(new Color(255, 255, 255));
        labelUser.setFont(new Font("Arial", Font.BOLD, 14));
        labelUser.setPreferredSize(new Dimension(200, 30));
        panelIzquierdo.add(labelUser, gbc);

        gbc.insets = (new Insets(20,20,10,10));
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        fieldBuscador = new JTextField("Ingrese el producto a buscar");
        fieldBuscador.setPreferredSize(new Dimension(250, 30));
        fieldBuscador.setMaximumSize(new Dimension(250, 30));
        fieldBuscador.setForeground(new Color(110, 110, 120));
        fieldBuscador.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldBuscador.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(62, 62, 62), 1, true), BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        panelPrincipal.add(fieldBuscador, gbc);

        fieldBuscador.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                if(fieldBuscador.getText().equals("Ingrese el producto a buscar")){
                    fieldBuscador.setText("");
                    fieldBuscador.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e){
                if(fieldBuscador.getText().isEmpty()){
                    fieldBuscador.setText("Ingrese el producto a buscar");
                    fieldBuscador.setForeground(new Color(110, 110, 120));
                }
            }
        });

        // ActionListener para buscar al presionar Enter en el campo
        fieldBuscador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductos();
            }
        });

        gbc.insets = (new Insets(20,320,10,0));
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.gridx = 0;
        gbc.gridy = 0;

        btnBuscar = new JButton("Buscar");
        btnBuscar.setForeground(new Color(110, 110, 120));
        btnBuscar.setPreferredSize(new Dimension(100, 30));
        btnBuscar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnBuscar.setBackground(new Color(255, 255, 255));
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);

        // ActionListener para buscar productos al hacer clic
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductos();
            }
        });

        panelPrincipal.add(btnBuscar, gbc);

        // Boton de comprar
        gbc.insets = (new Insets(20,520,10,0));
        gbc.gridx = 0;
        gbc.gridy = 0;

        btnComprar = new JButton("Comprar");
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setPreferredSize(new Dimension(100, 30));
        btnComprar.setFont(new Font("Arial", Font.BOLD, 14));
        btnComprar.setBackground(new Color(13, 138, 227));
        btnComprar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnComprar.setFocusPainted(false);
        btnComprar.setBorderPainted(false);

        // ActionListener para procesar la compra
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comprarProducto();
            }
        });

        panelPrincipal.add(btnComprar, gbc);

        String[] columnas ={"nombre", "cantidad", "descripcion", "precio"};

        modelo = new DefaultTableModel(columnas,0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        gbc.insets = (new Insets(80,20,20,20));
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;

        tablaProductos = new JTable(modelo);
        tablaProductos.setRowHeight(35);
        tablaProductos.setFont(new Font("Arial", Font.BOLD, 14));
        tablaProductos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tablaProductos.getTableHeader().setBackground(new Color(0, 79, 156));
        tablaProductos.getTableHeader().setForeground(new Color(255, 255, 255));

        tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(150);
        tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(80);
        tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(60);

        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        panelPrincipal.add(scrollPane, gbc);
    }

    // Metodo para cargar todos los productos en la tabla
    private void cargarProductos(){
        try {
            List<ProductsDTO> productos = productsController.obtenerTodosProductos();
            actualizarTabla(productos);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar productos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para buscar productos por nombre
    private void buscarProductos(){
        String termino = fieldBuscador.getText().trim();

        if(termino.isEmpty() || termino.equals("Ingrese el producto a buscar")){
            cargarProductos();
            return;
        }

        try {
            List<ProductsDTO> productos = productsController.buscarProductos(termino);

            if(productos.isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "No se encontraron productos",
                        "Sin resultados",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarProductos();
            } else {
                actualizarTabla(productos);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al buscar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para actualizar la tabla con una lista de productos
    private void actualizarTabla(List<ProductsDTO> productos){
        modelo.setRowCount(0);

        for(ProductsDTO producto : productos){
            Object[] fila = {
                    producto.getNameProduct(),
                    producto.getCantidad(),
                    producto.getDescription(),
                    formatearPrecio(producto.getPrice())
            };
            modelo.addRow(fila);
        }
    }

    // Metodo para formatear el precio con simbolo de peso
    private String formatearPrecio(BigDecimal precio){
        return String.format("$%.2f", precio);
    }

    // Metodo para procesar la compra de un producto
    private void comprarProducto(){
        // Solicita el nombre del producto usando JOptionPane
        String nombreProducto = JOptionPane.showInputDialog(this,
                "Ingrese el nombre del producto:",
                "Comprar Producto",
                JOptionPane.QUESTION_MESSAGE);

        if(nombreProducto == null || nombreProducto.trim().isEmpty()){
            return;
        }

        // Solicita la cantidad a comprar
        String cantidadStr = JOptionPane.showInputDialog(this,
                "Ingrese la cantidad a comprar:",
                "Cantidad",
                JOptionPane.QUESTION_MESSAGE);

        if(cantidadStr == null || cantidadStr.trim().isEmpty()){
            return;
        }

        try {
            int cantidadSolicitada = Integer.parseInt(cantidadStr);

            if(cantidadSolicitada <= 0){
                JOptionPane.showMessageDialog(this,
                        "La cantidad debe ser mayor a 0",
                        "Cantidad invalida",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Busca el producto en la lista actual
            List<ProductsDTO> productos = productsController.buscarProductos(nombreProducto);

            if(productos.isEmpty()){
                JOptionPane.showMessageDialog(this,
                        "Producto no encontrado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            ProductsDTO producto = productos.get(0);

            // Verifica si hay suficiente stock
            if(producto.getCantidad() < cantidadSolicitada){
                JOptionPane.showMessageDialog(this,
                        "Stock insuficiente. Solo hay " + producto.getCantidad() + " unidades disponibles",
                        "Stock insuficiente",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Calcula el total de la compra
            BigDecimal total = producto.getPrice().multiply(new BigDecimal(cantidadSolicitada));

            // Confirma la compra
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "Producto: " + producto.getNameProduct() + "\n" +
                            "Cantidad: " + cantidadSolicitada + "\n" +
                            "Precio unitario: " + formatearPrecio(producto.getPrice()) + "\n" +
                            "Total: " + formatearPrecio(total) + "\n\n" +
                            "¿Confirmar compra?",
                    "Confirmar Compra",
                    JOptionPane.YES_NO_OPTION);

            if(confirmacion == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(this,
                        "Compra realizada exitosamente\n" +
                                "Total: " + formatearPrecio(total),
                        "Compra Exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                // Recarga los productos para actualizar el stock
                cargarProductos();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Cantidad invalida. Ingrese un numero",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al procesar compra: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}