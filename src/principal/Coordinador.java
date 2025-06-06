package principal;

import java.util.ArrayList;

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
		System.out.println("no ecuientra la perssona");
		return null;
	}

}
