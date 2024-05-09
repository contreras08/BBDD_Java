package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaPreparada {

	public static void main(String[] args) {
		
		
		try {
			//1. Crear la conexión
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_virtual", "root", "Daniel3214059327.");
			
			//2. Preparar la consulta
			PreparedStatement miSentencia = miConexion.prepareStatement("SELECT nombre, descripcion, stock FROM PRODUCTO WHERE nombre=? ");
			
			//3. Establecer parametros de consulta
			miSentencia.setString(1, "colchón cassata");
			
			//4. Ejecutar y recorrer la consulta
			ResultSet rs = miSentencia.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
				
			}
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
	}

}
