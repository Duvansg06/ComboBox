package ventanas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.PersonaDao;
import principal.Coordinador;
import vo.PersonaVo;

public class VentanaCombo extends JFrame implements ActionListener {
	
	 	private JLabel lblNombre;
	    private JLabel lblDireccion;
	    private JTextField txtNombre;
	    private JTextField txtDireccion;
	    private JLabel lblDocumento;
	    private JLabel lblTelefono;
	    private JTextField txtDoc;
	    private JTextField txtTelefono;
	    private JButton btnRegistrar;
	    private JLabel lblComboPersonas;  
	    private Coordinador miCoordinador;
	    private JLabel lblSeleccion;
	    private JComboBox comboPersonas;
		private JTable tablaNombres;
		private JScrollPane mibarra1;
		private JButton btnActualizar;
		private JButton btnEliminar;
		
	    
	
	    public void setCoordinador(Coordinador miCoordinador) {
	    	this.miCoordinador = miCoordinador;
	    }

	   

	    public VentanaCombo(){
	        setSize(619, 544);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        getContentPane().setLayout(null);
	        setLocationRelativeTo(null);

	        JLabel lblTitulo = new JLabel("Gestionar personas ");
	        lblTitulo.setFont(new Font("Engravers MT", Font.PLAIN, 11));
	        lblTitulo.setBounds(145, 11, 199, 27);
	        getContentPane().add(lblTitulo);

	        iniciarComponenetes();

	    }
	



	private void iniciarComponenetes() {
			
		lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(10, 60, 60, 14);
        getContentPane().add(lblNombre);

        lblDireccion = new JLabel("Direccion");
        lblDireccion.setBounds(10, 100, 60, 14);
        getContentPane().add(lblDireccion);

        txtNombre = new JTextField();
        txtNombre.setBounds(66, 57, 211, 20);
        getContentPane().add(txtNombre);
        txtNombre.setColumns(10);

        txtDireccion = new JTextField();
        txtDireccion.setColumns(10);
        txtDireccion.setBounds(66, 97, 211, 20);
        getContentPane().add(txtDireccion);

        lblDocumento = new JLabel("Documento");
        lblDocumento.setBounds(287, 60, 76, 14);
        getContentPane().add(lblDocumento);

        lblTelefono = new JLabel("Telefono");
        lblTelefono.setBounds(287, 100, 57, 14);
        getContentPane().add(lblTelefono);

        txtDoc = new JTextField();
        txtDoc.setBounds(356, 57, 123, 20);
        getContentPane().add(txtDoc);
        txtDoc.setColumns(10);

        txtTelefono = new JTextField();
        txtTelefono.setColumns(10);
        txtTelefono.setBounds(354, 97, 123, 20);
        getContentPane().add(txtTelefono);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(356, 139, 123, 35);
        getContentPane().add(btnRegistrar);
        btnRegistrar.addActionListener(this);
        
        
        tablaNombres = new JTable();
        mibarra1 = new JScrollPane();
        mibarra1.setBounds(31,287,535,108);
        getContentPane().add(mibarra1);


        lblComboPersonas = new JLabel("Combo de personas");
        lblComboPersonas.setBounds(10, 221,150, 20);
        getContentPane().add(lblComboPersonas);

        comboPersonas = new JComboBox();
        comboPersonas.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
        comboPersonas.setSelectedIndex(0);
        comboPersonas.setBounds(139, 221, 180, 20);
        getContentPane().add(comboPersonas);
        comboPersonas.addActionListener(this);

        lblSeleccion = new JLabel("Seleccion: ");
        lblSeleccion.setBounds(325, 221, 300, 20);
        getContentPane().add(lblSeleccion);
        
        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(202, 139, 117, 35);
        getContentPane().add(btnActualizar);
        btnActualizar.addActionListener(this);
        
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(45, 141, 115, 33);
        getContentPane().add(btnEliminar);
        btnEliminar.addActionListener(this);
    }
			
	
	@Override
	public void actionPerformed(ActionEvent e) {
	     if (e.getSource()==btnRegistrar){
	            registrarUsuario();
	            cargarRegistros();
	        }

	     else if(e.getSource()==comboPersonas){
	 		
	 		if(comboPersonas.getSelectedItem() != null) {
	 			if (comboPersonas.getSelectedIndex()> 0){
	                 lblSeleccion.setText(comboPersonas.getSelectedItem().toString());
   
	                 Object seleccion = comboPersonas.getSelectedItem();
	                 List<PersonaVo> persona = miCoordinador.consultarListaPersonas();
	                 
	                 if (comboPersonas.getSelectedIndex() > 0 && seleccion instanceof PersonaVo) {
	                	 PersonaVo persona1 = (PersonaVo) seleccion;
	                	 if(persona1 != null) {
	                		 cargarPersona(persona1);
	                	 }else {
	                		 System.out.println("esta recibiendo null");
	                	 }	                	 
	                 }
	 		}
	
	             }else {
	                 lblSeleccion.setText("");
	             }
	         }
	 	
	 	if (e.getSource()==btnActualizar) {
	 			ActualizarRegistro();

	 	}
	
	 	if(e.getSource()== btnEliminar) {
	 			EliminarPersona();
	 			cargarRegistros();
	 	}
	 	
	        
	    }

	private void EliminarPersona() {
		
		if(comboPersonas.getSelectedIndex() < 0 || comboPersonas.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Seleccione la persona a eliminar. ");
			return;
		}
			
			try {
				PersonaVo personaObtenida = (PersonaVo) comboPersonas.getSelectedItem();

				boolean eliminado = miCoordinador.eliminarPersona(personaObtenida);
				
				if(eliminado) {
					JOptionPane.showMessageDialog(this, "Usuario eliminado con demasiado exito");
				}else {
					JOptionPane.showMessageDialog(this, "Usuario no eliminado");
				}
				
			} catch (Exception e) {
			    JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage());
			}
		}
		
	



	private void ActualizarRegistro() {
		
		if (comboPersonas.getSelectedIndex() < 0 || comboPersonas.getSelectedItem() == null) {
	        JOptionPane.showMessageDialog(this, "Debes seleccionar una persona del combo para actualizar.");
	        return;
	    }
		
		 try {
		        PersonaVo personaSeleccionada = (PersonaVo) comboPersonas.getSelectedItem();

		        PersonaVo personaActualizada = new PersonaVo();
		        personaActualizada.setDocumento(personaSeleccionada.getDocumento()); 
		        personaActualizada.setNombre(txtNombre.getText());
		        personaActualizada.setDireccion(txtDireccion.getText());
		        personaActualizada.setTelefono(txtTelefono.getText());

		        boolean actualizado = miCoordinador.actualizarPersona(personaActualizada);

		        if (actualizado) {
		            JOptionPane.showMessageDialog(this, "Persona actualizada correctamente.");
		            cargarRegistros();
		        } else {
		            JOptionPane.showMessageDialog(this, "No se pudo actualizar la persona.");
		        }

		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
		    }
		
		
	}
	
	


	private void cargarPersona(PersonaVo persona) {
		txtDoc.setText(persona.getDocumento());
		txtNombre.setText(persona.getNombre());
		txtDireccion.setText(persona.getDireccion());
		txtTelefono.setText(persona.getTelefono());	
	}



	private void registrarUsuario() {
		 PersonaVo persona = new PersonaVo();
	        persona.setDocumento(txtDoc.getText());
	        persona.setNombre(txtNombre.getText());
	        persona.setTelefono(txtTelefono.getText());
	        persona.setDireccion(txtDireccion.getText());

	        miCoordinador.registrarUsuario(persona);
		
	}

	public void cargarRegistros() {
		ArrayList<PersonaVo> listaPersonas = miCoordinador.consultarListaPersonas();
        llenarCombo(listaPersonas);
        llenarTable(listaPersonas);
		
	}

	private void llenarTable(ArrayList<PersonaVo> listaPersonas) {
		String titulos[]= {"Documento", "Nombre", "Direccion", "Telefono"};
		
		String informacion[][] = new String[listaPersonas.size()][4];
		
		for(int x = 0; x < informacion.length;x++) {
			informacion[x][0] = listaPersonas.get(x).getDocumento() + "";
			informacion[x][1] = listaPersonas.get(x).getNombre() + "";
			informacion[x][2] = listaPersonas.get(x).getDireccion() + "";
			informacion[x][3] = listaPersonas.get(x).getTelefono() + "";
	
		}
		
		tablaNombres= new JTable(informacion,titulos);
		
		int[] anchos = {20,100,100,10};
		for (int i = 0; i < tablaNombres.getColumnCount(); i++) {
			tablaNombres.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
		}
		
		mibarra1.setViewportView(tablaNombres);
			
		
	}
	
	

	private void llenarCombo(ArrayList<PersonaVo> listaPersonas) {
		comboPersonas.removeAllItems();

        for (PersonaVo persona : listaPersonas) {
            comboPersonas.addItem(persona);
        }
	}
}
	

