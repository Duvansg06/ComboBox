package principal;

import conexion.Conexion;

public class Aplicacion {
	
	public static void main (String[] args) {
		Relaciones misRelaciones = new Relaciones();
		misRelaciones.iniciar();
		
		Conexion miConexion = new Conexion();
	}

}
