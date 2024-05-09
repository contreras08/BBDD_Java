package conectaBD;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import javax.swing.*;


public class Marco_Aplicacion extends JFrame {
	
	private Connection miConexion;
	private JComboBox nombre;
	private JComboBox stock;
	private JTextArea resultado;
	private PreparedStatement enviaConsultaNombre;
	private final String consultaNombre = "SELECT id, nombre, descripcion, precio, stock, categoria FROM PRODUCTO WHERE nombre = ?"; 
	private final String consultaStock = "SELECT id, nombre, dscripcion, precio, stock, categoria FROM PRODUCTO WHERE stock = ?";
	private PreparedStatement enviaConsultaStock;
	private PreparedStatement enviaConsultaTodos;
	private final String consultaTodos = "SELECT id, nombre, descripcion, precio, stock, categoria FROM PRODUCTO WHERE nombre = ? AND"
			+ "stock = ?";
	
	public Marco_Aplicacion() {
		setTitle("Consulta BBDD");
		setBounds(500, 300, 400, 400);
		setLayout(new BorderLayout());
		JPanel menus = new JPanel();
		menus.setLayout(new FlowLayout());
		nombre = new JComboBox();
		nombre.setEditable(false);
		nombre.addItem("Todos");
		
		stock = new JComboBox();
		stock.setEditable(false);
		stock.addItem("Todos");
		resultado = new JTextArea(4, 5);
		resultado.setEditable(false);
		add(resultado);
		menus.add(nombre);
		menus.add(stock);
		add(menus, BorderLayout.CENTER);
		JButton botonConsulta = new JButton("Consulta");
		botonConsulta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ejecutaConsulta();
				
			}
			
		});
		add(botonConsulta, BorderLayout.SOUTH);
		
		
		//-----------------------Conexión con la base de datos------------------------------
		try{
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_virtual", "root", "Daniel3214059327.");
			
			Statement sentencia = miConexion.createStatement();
			
			//-----------------Carga JComboBox nombre---------------			
			String consulta = "SELECT DISTINCTROW NOMBRE FROM PRODUCTO";
			
			ResultSet rs = sentencia.executeQuery(consulta);
			
			while(rs.next()) {
				nombre.addItem(rs.getString(1));
			}
			rs.close();
			
			//-----------------Carga JComboBox stock---------------
			consulta = "SELECT DISTINCTROW STOCK FROM PRODUCTO";
			
			rs = sentencia.executeQuery(consulta);
			
			while(rs.next()) {
				stock.addItem(rs.getString(1));
			}
			rs.close();
			
			
		}catch(Exception e){
			
		}
		
		
	}
	private void ejecutaConsulta() {
		ResultSet rs = null;
		
		try {
			
			resultado.setText("");
			
			String nom = (String)nombre.getSelectedItem();
			String sto = (String)stock.getSelectedItem();
			if(!nombre.equals("Todos") && stock.equals("Todos")) {
				enviaConsultaNombre = miConexion.prepareStatement(consultaNombre);
				enviaConsultaNombre.setString(1, nom);
				rs = enviaConsultaNombre.executeQuery();
			}else if(nombre.equals("Todos") && !stock.equals("Todos")){
				enviaConsultaStock = miConexion.prepareStatement(consultaStock);
				enviaConsultaStock.setString(1, nom);
				rs = enviaConsultaNombre.executeQuery();
			}else if(!nombre.equals("Todos") && !stock.equals("Todos")) {
				enviaConsultaTodos = miConexion.prepareStatement(consultaTodos);
				enviaConsultaTodos.setString(1, nom);
				enviaConsultaTodos.setString(2, sto);
				rs = enviaConsultaNombre.executeQuery();
			}
			while(rs.next()) {
				resultado.append(rs.getString(1));
				resultado.append(", ");
				resultado.append(rs.getString(2));
				resultado.append(", ");
				resultado.append(rs.getString(3));
				resultado.append(", ");
				resultado.append(rs.getString(4));
				resultado.append(", ");
				resultado.append(rs.getString(5));
				resultado.append(", ");
				resultado.append(rs.getString(6));
				resultado.append("\n");
			}
			
			
		}catch(Exception e){
			
		}
		

	}

}
