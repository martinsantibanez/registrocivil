 package ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Persona;
import logica.RegistroCivil;
import logica.FormatoInvalidoException;
import logica.Sucursal;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEditarPersona extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField rut;
	private JTextField anioNacimiento;
	private JTextField estadoCivil;
	private JTextField conyuge;
	private JTextField diaNac;
	private JTextField mesNac;
	public static VentanaEditarPersona estaVentana;
	/**
	 * Edita a "persona". Accesible por Funcionario y Administrador
	 * @param registro
	 * @param usuario
	 * @param persona
	 */
	private VentanaEditarPersona(RegistroCivil registro, Persona usuario, Persona persona) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		estadoCivil = new JTextField(persona.getEstadoCivil());
		estadoCivil.setEditable(false);
		estadoCivil.setColumns(10);
		estadoCivil.setBounds(166, 136, 114, 20);
		contentPane.add(estadoCivil);
		
		JLabel label = new JLabel("Nombre");
		label.setBounds(30, 33, 84, 14);
		contentPane.add(label);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(166, 30, 114, 20);
		nombre.setText(persona.getNombre());
		contentPane.add(nombre);
		
		rut = new JTextField();
		rut.setColumns(10);
		rut.setBounds(166, 55, 114, 20);
		rut.setText(persona.getRut());
		contentPane.add(rut);
		
		JLabel label_1 = new JLabel("RUT");
		label_1.setBounds(30, 58, 84, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Fecha de nacimiento");
		label_2.setBounds(30, 83, 126, 16);
		contentPane.add(label_2);
		
		anioNacimiento = new JTextField();
		anioNacimiento.setColumns(10);
		anioNacimiento.setBounds(225, 81, 55, 20);
		anioNacimiento.setText(Integer.toString(persona.getAnioNacimiento()));
		contentPane.add(anioNacimiento);
		
		JLabel lblddMmAaaa = new JLabel("(dd mm aaaa)");
		lblddMmAaaa.setBounds(299, 84, 89, 14);
		contentPane.add(lblddMmAaaa);
		
		JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
		rdbtnFemenino.setBounds(285, 106, 121, 24);
		contentPane.add(rdbtnFemenino);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		rdbtnMasculino.setBounds(166, 106, 121, 24);
		contentPane.add(rdbtnMasculino);
		if(persona.getSexo()=="Masculino")
			rdbtnMasculino.setSelected(true);
		else
			rdbtnFemenino.setSelected(true);
		
		ButtonGroup grupoSexo = new ButtonGroup();
	    grupoSexo.add(rdbtnFemenino);
	    grupoSexo.add(rdbtnMasculino);
		
		JLabel label_4 = new JLabel("Sexo");
		label_4.setBounds(30, 110, 55, 16);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Estado Civil");
		label_5.setBounds(30, 138, 98, 16);
		contentPane.add(label_5);
		
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					registro.validarRut(rut.getText());
					if(nombre.getText().compareTo("")!=0){
						if( (rut.getText().equals(persona.getRut()) ) || ( registro.buscarPersona(rut.getText())==null ) ){
							String fechaNacimiento = diaNac.getText() + "," + mesNac.getText() + "," + anioNacimiento.getText();
							registro.validarFecha(fechaNacimiento);
							Sucursal sucReg = registro.buscarSucursalPersonaRut(persona.getRut());
							if(sucReg!=null){
								persona.setNombre(nombre.getText());
								sucReg.cambiaRutPersona(persona.getRut(), rut.getText());
								persona.setFechaNacimiento(fechaNacimiento);
								if(rdbtnMasculino.isSelected())
									persona.setSexo("Masculino");
								else
									persona.setSexo("Femenino");
								registro.alerta("Datos actualizados.");
								volver();
							} else registro.error("ERROR");
						} else registro.error("El rut especificado ya est� registrado.");
					} else registro.error("Todos los campos son obligatorios.");
				} catch (FormatoInvalidoException exc) {
					 registro.error("Formato del rut o fecha inv�lido");
				}
				//ACTUALIZAR LOS LABEL DE ACUERDO A LO EDITADO (VAR);
			}
		});
		btnActualizar.setBounds(299, 227, 107, 23);
		contentPane.add(btnActualizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(30, 227, 84, 23);
		contentPane.add(btnVolver);
		
		diaNac = new JTextField();
		diaNac.setText(Integer.toString(persona.getDiaNacimiento()));
		diaNac.setColumns(10);
		diaNac.setBounds(166, 81, 27, 20);
		contentPane.add(diaNac);
		
		mesNac = new JTextField();
		mesNac.setText(Integer.toString(persona.getMesNacimiento()));
		mesNac.setColumns(10);
		mesNac.setBounds(195, 81, 27, 20);
		contentPane.add(mesNac);
		
		if(persona.getEstadoCivil().compareTo("Casado")==0){
			JLabel lblCon = new JLabel("con");
			lblCon.setBounds(285, 138, 21, 16);
			contentPane.add(lblCon);
			String nombreConyuge = registro.nombrePersonaRut(persona.getRutConyuge());
			if(nombreConyuge!=null)
				conyuge = new JTextField(nombreConyuge);
			else
				conyuge = new JTextField("ERROR");
			conyuge.setEditable(false);
			conyuge.setColumns(10);
			conyuge.setBounds(315, 137, 109, 20);
			contentPane.add(conyuge);
			JButton btnDivorciar = new JButton("Divorciar");
			btnDivorciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(registro.divorciar(persona.getRut(), persona.getRutConyuge()))
						volver();
				}
			});
			btnDivorciar.setBounds(335, 167, 89, 23);
			contentPane.add(btnDivorciar);
		}
		
	}
	
	private void volver(){
		VentanaPanelRegistrados venF = VentanaPanelRegistrados.getInstance();
		venF.mostrar();
		destruir();
	}
	
	public void destruir(){
		estaVentana = null;
		dispose();
	}
	
	public void ocultar(){
		setVisible(false);
	}
	
	public void mostrar(){
		setVisible(true);
	}

	public static VentanaEditarPersona getInstance(RegistroCivil registro, Persona usuario, Persona persona) {
		if(estaVentana==null)
			estaVentana = new VentanaEditarPersona(registro, usuario, persona);
		return estaVentana;
	}
	
	public static VentanaEditarPersona getInstance(){
		return estaVentana;
	}
	
}
