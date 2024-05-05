package conectaBD;
import java.sql.*;

public class Conecta_Pruebas {

	public static void main(String[] args) {
		try{
			//1. Crea la conexión
			Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_virtual", "root", "Daniel3214059327.");
			
			//2. Crear objeto Statement
			Statement miStatement = miConexion.createStatement();
			
			//3. Ejecutar SQL
			ResultSet miResultset = miStatement.executeQuery("SELECT * FROM PRODUCTO");
			
			//4. Recorrer el resultset
			while(miResultset.next()) {
				System.out.println(miResultset.getString("id") + " " + miResultset.getString("nombre") + " " + miResultset.getString("descripcion") + " " + miResultset.getString("precio") + " " + miResultset.getString("stock") + " " + miResultset.getString("categoria_id"));
				
			}
			
		}catch(Exception e) {
			System.out.println("No conecta");
			e.printStackTrace();
		}

	}

}
