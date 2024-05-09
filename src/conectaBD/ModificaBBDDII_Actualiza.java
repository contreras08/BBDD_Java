package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ModificaBBDDII_Actualiza {

	public static void main(String[] args) {
		try{
			//1. Crea la conexión
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_virtual", "root", "Daniel3214059327.");
			
			//2. Crear objeto Statement
			Statement miStatement = miConexion.createStatement();
			String instruccionSql = "UPDATE PRODUCTO SET NOMBRE = 'Colchón Cassata' WHERE ID = 16 ";
					 
            miStatement.executeUpdate(instruccionSql);
            
            System.out.println("Datos modificados correctamente!!!");
			
		}catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}

	}

}
