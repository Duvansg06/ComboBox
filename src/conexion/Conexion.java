package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	private static final String nombreBD="usuarios";
	private static final String usuario="root";
	private static final String password="1234";
	private static final String url= "jdbc:mysql://localhost:3306/"+nombreBD+"?useUnicode=true&use"
            + "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC";
	
	
	private static Connection conn=null;
	// constructor de la clase
	
	public  static Connection getConnection() {
		
		 
			try {
				// obtener el driver
				Class.forName("com.mysql.cj.jdbc.Driver");	
				
				// obtener la conexion
				conn=DriverManager.getConnection(url,usuario,password);
				if(conn==null) {
				System.err.println("Conexion Fallida a la BD: "+nombreBD);
				}
				
				}catch (ClassNotFoundException e) {
					System.err.println("ocurre una ClassNotFoundException: "+e.getMessage());
				} catch (SQLException e) {
					System.err.println("ocurre una SQLException: "+e.getMessage());
					}
				
				return conn;
			}
			
	
	public static  void desconectar() {
	conn=null;
	
	}

}
