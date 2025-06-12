package principal;

import java.sql.Connection;

import conexion.Conexion;

public class Aplicacion {
	
	public static void main (String[] args) {
		Relaciones misRelaciones = new Relaciones();
		misRelaciones.iniciar();
		
		Connection miConexion = Conexion.getInstancia().getConnection();
	}

}
