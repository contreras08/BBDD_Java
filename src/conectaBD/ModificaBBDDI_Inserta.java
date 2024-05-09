package conectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModificaBBDDI_Inserta {

	public static void main(String[] args) {
		try{
			//1. Crea la conexión
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_virtual", "root", "Daniel3214059327.");
			
			//2. Crear objeto Statement
			Statement miStatement = miConexion.createStatement();
			String instruccionSql = "INSERT INTO PRODUCTO (nombre, descripcion, precio, stock, categoria_id) VALUES('Colchón cassata','Colchón de excelente calidad indeformable', 1040000.00, 12, 8) ";
					 
            miStatement.executeUpdate(instruccionSql);
            
            System.out.println("Datos insertados correctamente!!!");
			
		}catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}

	}

}
