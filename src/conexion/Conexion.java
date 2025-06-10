package conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
	
//	private static final String nombreBD="usuarios";
//	private static final String usuario="root";
//	private static final String password="1234";
//	private static final String url= "jdbc:mysql://localhost:3306/"+nombreBD+"?useUnicode=true&use"
//            + "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
//            + "serverTimezone=UTC";
//	
	
	private static Connection conn=null;
	private static Properties properties = new Properties();
	
	public Conexion() {
		
	}
	
	
	public  static Connection getConnection() {
		
		 
			try {
				
				InputStream input = Conexion.class.getClassLoader().getSystemResourceAsStream("properties/confing.properties");
//				// obtener el driver
//				Class.forName("com.mysql.cj.jdbc.Driver");	
//				
//				// obtener la conexion
//				conn=DriverManager.getConnection(url,usuario,password);
				if(input==null) {
				System.err.println("Archivo de config.properties no encontrado");
				return null;
				}
				
				
				properties.load(input);
				
				String nombreBD = properties.getProperty("db.name");
                String usuario = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");
                String host = properties.getProperty("db.host");
                String port = properties.getProperty("db.port");
                
                String url = "jdbc:mysql://" + host + ":" + port + "/" + nombreBD +
                        "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
				
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                conn = DriverManager.getConnection(url,usuario, password);
                
                if (conn == null) {
                    System.err.println("Conexión fallida a la BD: " + nombreBD);
                }
				
				}catch (IOException e) {
	                System.err.println("Error al leer config.properties: " + e.getMessage());
				} catch (ClassNotFoundException e) {
	                System.err.println("Error al cargar el driver: " + e.getMessage());
				}catch (SQLException e) {
	                System.err.println("Error de conexión: " + e.getMessage());
				}
				
				return conn;
			}
			
	
	public static  void desconectar() {
	conn=null;
	
	}

}
