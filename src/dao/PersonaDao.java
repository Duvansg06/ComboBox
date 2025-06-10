package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import vo.PersonaVo;

public class PersonaDao {
	
	private Connection conexion;
	
	
	public PersonaDao() {
		conexion = Conexion.getConnection();
		
//		Conexion cone = new Conexion();
//		PreparedStatement preStatement = null;
//		System.out.println(conexion);
		}

		String resultado = "";
		
		
		
	public void registrarUsuario(PersonaVo miPersonaVo) {
		String consulta = "INSERT INTO persona(documento, nombre, direccion, telefono)" + "VALUES (?,?,?,?)";
		
		try {
			PreparedStatement pre = conexion.prepareStatement(consulta);
			pre.setString(1, miPersonaVo.getDocumento());
			pre.setString(2, miPersonaVo.getNombre());
			pre.setString(3, miPersonaVo.getDireccion());
			pre.setString(4, miPersonaVo.getTelefono());
			pre.executeUpdate();
			
			
			
		}catch(SQLException e) {
			System.err.println("No se pudo registrar el dato: " + e.getMessage());
			resultado = "No se pudo registrar el dato" + e.getMessage();
		}
		
		
	}
	
	public ArrayList<PersonaVo> consultarListaPersonas(){
		ArrayList<PersonaVo> listaPersona = new ArrayList<PersonaVo>();
		String consulta = "SELECT * FROM persona";
		
		
		try {
			PreparedStatement stm = conexion.prepareStatement(consulta);
			ResultSet rs = stm.executeQuery(consulta);
			
			while(rs.next()) {
				PersonaVo miPersona = new PersonaVo();
				
				miPersona.setDocumento(rs.getString("documento"));
				miPersona.setNombre(rs.getString("nombre"));
				miPersona.setDireccion(rs.getString("direccion"));
				miPersona.setTelefono(rs.getString("telefono"));
				
				listaPersona.add(miPersona);
			}
	
		}catch(SQLException e) {
			System.err.println("Error en la consulta del usuario"+ e.getMessage());
		}
		return listaPersona;
	}
	
	
	
	public boolean actualizarPersona(PersonaVo persona) {
		
		try {
	        Connection con = Conexion.getConnection();
	        String sql = "UPDATE persona SET nombre = ?, direccion = ?, telefono = ? WHERE documento = ?";
	        java.sql.PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, persona.getNombre());
	        ps.setString(2, persona.getDireccion());
	        ps.setString(3, persona.getTelefono());
	        ps.setString(4, persona.getDocumento());

	        int filas = ps.executeUpdate();
	        ps.close();
	        con.close();
	        return filas > 0;
	    } catch (Exception e) {
	        System.out.println("Error al actualizar persona: " + e.getMessage());
	        return false;
	    }
	}

	public boolean eliminarPersona(PersonaVo personaEliminada) {
		
		try {
			Connection conectar = Conexion.getConnection();
			String sql = "DELETE FROM persona WHERE documento = ?";
			java.sql.PreparedStatement ps = conectar.prepareStatement(sql);
			
		      ps.setString(1, personaEliminada.getDocumento());
		      
		    int fila = ps.executeUpdate();
		    ps.close();
		    conectar.close();
		    return fila > 0;
		
		}catch(Exception e) {
		     System.out.println("Error al eliminar persona: " + e.getMessage());
		     return false;
		}
		
		
	}
	
}
