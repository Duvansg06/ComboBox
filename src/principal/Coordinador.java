package principal;

import java.sql.Connection;
import java.util.ArrayList;

import conexion.Conexion;
import dao.PersonaDao;
import vo.PersonaVo;

public class Coordinador {

	private PersonaDao miPersonaDao;
	
	public Coordinador () {
		miPersonaDao= new PersonaDao();
	}

	public void registrarUsuario(PersonaVo persona) {
		miPersonaDao.registrarUsuario(persona);
		
	}


	public ArrayList<PersonaVo> consultarListaPersonas() {
		
		return miPersonaDao.consultarListaPersonas();
	}

	public PersonaVo consultarListaPersonasPorDocumento(String documento) {
		ArrayList<PersonaVo> personas = miPersonaDao.consultarListaPersonas();
		for (PersonaVo persona: personas) {
			
			if(persona.getDocumento().equals(documento)) {
				return persona;
			}
		}
		System.out.println("no encuentra la persona");
		return null;
	}
	

	public boolean actualizarPersona(PersonaVo personaActualizada) {
		
		return miPersonaDao.actualizarPersona(personaActualizada);
	}

	public boolean eliminarPersona(PersonaVo personaEliminada) {
		return miPersonaDao.eliminarPersona(personaEliminada);
	}


}
