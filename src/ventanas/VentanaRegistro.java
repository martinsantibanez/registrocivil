package ventanas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Empresa;
import logica.Extranjero;
import logica.FormatoInvalidoException;
import logica.Funcionario;
import logica.LugarNoExisteException;
import logica.Persona;
import logica.Region;
import logica.Registrado;
import logica.RegistroCivil;
import logica.Sucursal;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.TextArea;

public class VentanaRegistro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField rut;
	private JTextField rut1;
	private JTextField rut2;
	private JTextField rutDifunto;
	private JTextField password;
	private JTextField dia;
	private JTextField mes;
	private JTextField anio;
	public static VentanaRegistro estaVentana;
	private JTextField paisOrigenExtranjero;
	/**
	 * Ventana para registrar usuarios. Accesible por Administrador y Funcionario.
	 * @param registro
	 * @param usuario
	 */
	private VentanaRegistro(RegistroCivil registro, Persona usuario) {
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 424, 286);
		contentPane.add(tabbedPane);
		
		//NACIMIENTO:
			JPanel panelNacimiento = new JPanel();
			panelNacimiento.setToolTipText("");
			tabbedPane.addTab("Nacimiento", null, panelNacimiento, null);
			panelNacimiento.setLayout(null);
			
			JLabel lblNombre = new JLabel("Nombre");
			lblNombre.setBounds(10, 11, 84, 14);
			panelNacimiento.add(lblNombre);
			
			nombre = new JTextField();
			nombre.setBounds(146, 8, 114, 20);
			panelNacimiento.add(nombre);
			nombre.setColumns(10);
			
			JLabel lblRut = new JLabel("RUT");
			lblRut.setBounds(10, 36, 84, 14);
			panelNacimiento.add(lblRut);
			
			rut = new JTextField();
			rut.setBounds(146, 33, 114, 20);
			panelNacimiento.add(rut);
			rut.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Fecha de nacimiento");
			lblNewLabel.setBounds(10, 61, 126, 16);
			panelNacimiento.add(lblNewLabel);
			
			JLabel lblSexo = new JLabel("Sexo");
			lblSexo.setBounds(10, 88, 55, 16);
			panelNacimiento.add(lblSexo);
			
			JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
			rdbtnFemenino.setBounds(265, 84, 121, 24);
			panelNacimiento.add(rdbtnFemenino);
			
			JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
			rdbtnMasculino.setBounds(146, 84, 121, 24);
			panelNacimiento.add(rdbtnMasculino);
			rdbtnMasculino.setSelected(true); //Por defecto
			
			ButtonGroup grupoSexo = new ButtonGroup();
		    grupoSexo.add(rdbtnFemenino);
		    grupoSexo.add(rdbtnMasculino);

			JLabel lblEj = new JLabel("Ej: 12345678-9");
			lblEj.setBounds(278, 35, 89, 14);
			panelNacimiento.add(lblEj);
			
			JLabel lblContrasea = new JLabel("Contrase\u00F1a");
			lblContrasea.setBounds(10, 112, 84, 14);
			panelNacimiento.add(lblContrasea);
			
			password = new JTextField();
			password.setBounds(146, 109, 114, 20);
			panelNacimiento.add(password);
			password.setColumns(10);
			
			dia = new JTextField();
			dia.setColumns(10);
			dia.setBounds(146, 57, 27, 20);
			panelNacimiento.add(dia);
			
			mes = new JTextField();
			mes.setColumns(10);
			mes.setBounds(175, 57, 27, 20);
			panelNacimiento.add(mes);
			
			anio = new JTextField();
			anio.setColumns(10);
			anio.setBounds(205, 57, 55, 20);
			panelNacimiento.add(anio);
			
			JLabel label = new JLabel("(dd mm aaaa)");
			label.setBounds(279, 60, 89, 14);
			panelNacimiento.add(label);
			JComboBox<String> region = new JComboBox<String>();
			JComboBox<String> sucursal = new JComboBox<String>();
			JComboBox<String> tipoUsuario = new JComboBox<String>();
			if(usuario.getTipo().compareTo("Administrador")==0){
				
				region.setBounds(122, 152, 125, 22);
				panelNacimiento.add(region);
				for(int i=0;i<registro.numeroRegiones();i++)
					region.addItem(registro.nombreRegion(i));
				
				JLabel lblRegion = new JLabel("Region");
				lblRegion.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblRegion.setBounds(10, 158, 55, 16);
				panelNacimiento.add(lblRegion);
				
				JLabel lblSucursalNac = new JLabel("Nombre Sucursal");
				lblSucursalNac.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblSucursalNac.setBounds(10, 187, 105, 16);
				panelNacimiento.add(lblSucursalNac);
				
				
				sucursal.setBounds(121, 181, 126, 22);
				panelNacimiento.add(sucursal);
				JButton seleccionar = new JButton("Seleccionar");
				seleccionar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						sucursal.removeAllItems();
						String regSel = (String)region.getSelectedItem();
						String[] vector = new String [registro.numeroDeSucursales(regSel)];
						vector = registro.nombreSucursales(regSel);
						for(int i=0;i<registro.numeroDeSucursales(regSel);i++){
							sucursal.addItem(vector[i]);
						}
					}
				});
				seleccionar.setBounds(277, 148, 105, 26);
				panelNacimiento.add(seleccionar);
				
				JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario");
				lblTipoDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblTipoDeUsuario.setBounds(10, 210, 105, 16);
				panelNacimiento.add(lblTipoDeUsuario);
				
				tipoUsuario.setBounds(121, 208, 126, 22);
				tipoUsuario.addItem("Registrado");
				tipoUsuario.addItem("Funcionario");
				panelNacimiento.add(tipoUsuario);
				
			}
			JButton btnRegistrar = new JButton("Registrar");
			
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Persona usuarioRegistrar;
					String tipo = (String)tipoUsuario.getSelectedItem();
					//Crear usuario y setearle los datos
					if(tipo.compareTo("Registrado")==0)
						usuarioRegistrar = new Registrado();
					else
						usuarioRegistrar = new Funcionario();
					if(nombre.getText().compareTo("")!=0 && password.getText().compareTo("")!=0){
						usuarioRegistrar.setNombre(nombre.getText());
						usuarioRegistrar.setPassword(password.getText());
						Sucursal sucursalInsertar = null;
						try{
							if(usuario.getTipo().compareTo("Funcionario")==0){
							//Buscar sucursal correspondiente al funcionario actual.
								sucursalInsertar = registro.buscarSucursalFuncionarioRut(usuario.getRut());
							} else { //Si es administrador
								Region regionInsertar = registro.buscarRegion((String)region.getSelectedItem());
								sucursalInsertar = regionInsertar.buscarSucursal((String)sucursal.getSelectedItem());
							}
						} catch(LugarNoExisteException ex){
							registro.error("Error encontrando sucursal.");
							volver(usuario);
						}
						usuarioRegistrar.setEstadoCivil("Soltero");
						//Establecer sexo
						if(rdbtnFemenino.isSelected())
							usuarioRegistrar.setSexo("Femenino");
						else
							usuarioRegistrar.setSexo("Masculino");
						try{
							registro.validarRut(rut.getText());
							if(registro.buscarPersona(rut.getText())==null){ //Revisar que no est� registrado el rut
								usuarioRegistrar.setRut(rut.getText());
								String fecha = dia.getText() + "," + mes.getText() + "," + anio.getText();
								registro.validarFecha(fecha);
								usuarioRegistrar.setFechaNacimiento(fecha);
								boolean respuesta;
								if(usuarioRegistrar.getTipo().compareTo("Registrado")==0)
									respuesta = sucursalInsertar.registrar((Registrado)usuarioRegistrar);
								else
									respuesta = sucursalInsertar.registrar((Funcionario)usuarioRegistrar);
								if(respuesta){ //Revisar que se realiza la insercion
									registro.alerta("Persona agregada correctamente.");
									volver(usuario);
								} else registro.error("Ocurri� un error al registrar.");
							} else registro.error("El rut especificado ya est� registrado.");	
						} catch(FormatoInvalidoException ex) {
							registro.error("Formato del rut o fecha inv�lido");
						}
					} else {
						registro.error("Todos los campos son obligatorios.");
					}
					
				}
			});
			btnRegistrar.setBounds(283, 218, 98, 26);
			panelNacimiento.add(btnRegistrar);
		
		//MATRIMONIO:
	
			JPanel panelMatrimonio = new JPanel();
			tabbedPane.addTab("Matrimonio", null, panelMatrimonio, null);
			panelMatrimonio.setLayout(null);
			
			rut1 = new JTextField();
			rut1.setBounds(52, 137, 114, 20);
			panelMatrimonio.add(rut1);
			rut1.setColumns(10);
			
			JLabel lblRutConyuge = new JLabel("Rut ");
			lblRutConyuge.setBounds(96, 112, 70, 14);
			panelMatrimonio.add(lblRutConyuge);
			
			JLabel lblDatosDelConyuge = new JLabel("Datos de los Conyuges");
			lblDatosDelConyuge.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblDatosDelConyuge.setBounds(113, 78, 211, 23);
			panelMatrimonio.add(lblDatosDelConyuge);
			
			JLabel lblFuncionarioACargo = new JLabel("Funcionario a cargo: "+usuario.getNombre());
			lblFuncionarioACargo.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblFuncionarioACargo.setBounds(29, 49, 378, 16);
			panelMatrimonio.add(lblFuncionarioACargo);
			
			//obtiene la fecha actual para generar el registro
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			JLabel lblFecha = new JLabel("Fecha:"+ dateFormat.format(date));
			lblFecha.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblFecha.setBounds(29, 37, 152, 14);
			panelMatrimonio.add(lblFecha);
			
			JButton btnRegistrar_1 = new JButton("Registrar");
			btnRegistrar_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(registro.generarMatrimonio(rut1.getText(), rut2.getText(), usuario, dateFormat.format(date)))
						volver(usuario);
				}	
			});
			btnRegistrar_1.setBounds(156, 170, 89, 23);
			panelMatrimonio.add(btnRegistrar_1);
			
			JLabel label_1 = new JLabel("Rut ");
			label_1.setBounds(275, 112, 70, 14);
			panelMatrimonio.add(label_1);
			
			rut2 = new JTextField();
			rut2.setBounds(231, 137, 104, 20);
			panelMatrimonio.add(rut2);
			rut2.setColumns(10);
			
			JPanel panelDefuncion = new JPanel();
		//DEFUNCION:
			tabbedPane.addTab("Defuncion", null, panelDefuncion, null);
			panelDefuncion.setLayout(null);
			
			JLabel lblRegistroDeDefunciones = new JLabel("Registro de Defunciones");
			lblRegistroDeDefunciones.setBounds(118, 0, 163, 16);
			lblRegistroDeDefunciones.setFont(new Font("Tahoma", Font.BOLD, 13));
			panelDefuncion.add(lblRegistroDeDefunciones);
			
			JLabel lblIngreseRut = new JLabel("Ingrese rut :");
			lblIngreseRut.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblIngreseRut.setBounds(23, 29, 81, 16);
			panelDefuncion.add(lblIngreseRut);
			
			rutDifunto = new JTextField();
			rutDifunto.setBounds(102, 27, 99, 20);
			panelDefuncion.add(rutDifunto);
			rutDifunto.setColumns(10);
			
			TextArea pantalla = new TextArea();
			pantalla.setBounds(23, 57, 372, 121);
			panelDefuncion.add(pantalla);
			
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pantalla.setText(" ");
					Persona difunto = registro.buscarPersona(rutDifunto.getText());
					if(difunto!=null){
						pantalla.append("--------------------------------------------------------------------------------------------");
						pantalla.append("\nNombre: "+difunto.getNombre());
						pantalla.append("\nRut: "+difunto.getRut());
						pantalla.append("\nFecha de Nacimiento: "+difunto.getFechaSlash());
						pantalla.append("\nFecha de Defunci�n: "+ dateFormat.format(date));
						pantalla.append("\nFuncionario a cargo: "+usuario.getNombre());
					}
					else{
						pantalla.append("NO EXISTE REGISTRO ASOCIADO AL RUT INGRESADO");
					}
				} 
			});
			btnBuscar.setBounds(252, 27, 89, 23);
			panelDefuncion.add(btnBuscar);
			
			JButton btnRegistrar_2 = new JButton("Registrar");
			btnRegistrar_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Persona difunto = registro.buscarPersona(rutDifunto.getText());
					if(usuario.equals(difunto)){
						registro.error("No es posible realizar esta operaci�n");
					}
					else{
						if(difunto!=null){
							if(difunto.getEstadoDeVida().equals("0")){
								registro.error("Ya existe un registro de defuncion asociado a este rut");
							}
							else{
								difunto.setEstadoDeVida("0");
								difunto.setFechaDefuncion(dateFormat.format(date));
								if(difunto.getEstadoCivil().compareTo("Casado")==0){
									Persona conyuge = registro.buscarPersona(difunto.getRutConyuge());
									conyuge.setEstadoCivil("Viudo");
								}
								registro.alerta("El registro fue guardado correctamente");
							}
						}
						else{
							registro.error("Ingrese rut V�lido"); 
						}
					}
				}
			});
			btnRegistrar_2.setBounds(150, 184, 106, 23);
			panelDefuncion.add(btnRegistrar_2);
		
		//EXTRANJERO:
			JPanel panelExtranjero = new JPanel();
			panelExtranjero.setLayout(null);
			tabbedPane.addTab("Extranjero", null, panelExtranjero, null);
			JLabel lblNombreExtranjero = new JLabel("Nombre");
			lblNombreExtranjero.setBounds(10, 11, 84, 14);
			panelExtranjero.add(lblNombreExtranjero);
			
			JTextField nombreExtranjero = new JTextField();
			nombreExtranjero.setBounds(146, 8, 114, 20);
			panelExtranjero.add(nombreExtranjero);
			nombreExtranjero.setColumns(10);
			
			JLabel lblRutExtranjero = new JLabel("RUT");
			lblRutExtranjero.setBounds(10, 36, 84, 14);
			panelExtranjero.add(lblRutExtranjero);
			
			JTextField rutExtranjero = new JTextField();
			rutExtranjero.setBounds(146, 33, 114, 20);
			panelExtranjero.add(rutExtranjero);
			rutExtranjero.setColumns(10);
			
			JLabel lblNewLabelExtranjero = new JLabel("Fecha de nacimiento");
			lblNewLabelExtranjero.setBounds(10, 61, 126, 16);
			panelExtranjero.add(lblNewLabelExtranjero);
			
			JLabel lblSexoExtranjero = new JLabel("Sexo");
			lblSexoExtranjero.setBounds(10, 88, 55, 16);
			panelExtranjero.add(lblSexoExtranjero);
			
			JRadioButton rdbtnFemeninoExtranjero = new JRadioButton("Femenino");
			rdbtnFemeninoExtranjero.setBounds(265, 84, 121, 24);
			panelExtranjero.add(rdbtnFemeninoExtranjero);
			
			JRadioButton rdbtnMasculinoExtranjero = new JRadioButton("Masculino");
			rdbtnMasculinoExtranjero.setBounds(146, 84, 121, 24);
			panelExtranjero.add(rdbtnMasculinoExtranjero);
			rdbtnMasculinoExtranjero.setSelected(true); //Por defecto
			
			ButtonGroup grupoSexoExtranjero = new ButtonGroup();
		    grupoSexoExtranjero.add(rdbtnFemeninoExtranjero);
		    grupoSexoExtranjero.add(rdbtnMasculinoExtranjero);

			JLabel lblEjExtranjero = new JLabel("Ej: 12345678-9");
			lblEjExtranjero.setBounds(278, 35, 89, 14);
			panelExtranjero.add(lblEjExtranjero);
			
			JLabel lblContraseaExtranjero = new JLabel("Contrase\u00F1a");
			lblContraseaExtranjero.setBounds(10, 112, 84, 14);
			panelExtranjero.add(lblContraseaExtranjero);
			
			JTextField passwordExtranjero = new JTextField();
			passwordExtranjero.setBounds(146, 109, 114, 20);
			panelExtranjero.add(passwordExtranjero);
			passwordExtranjero.setColumns(10);
			
			JTextField diaExtranjero = new JTextField();
			diaExtranjero.setColumns(10);
			diaExtranjero.setBounds(146, 57, 27, 20);
			panelExtranjero.add(diaExtranjero);
			
			JTextField mesExtranjero = new JTextField();
			mesExtranjero.setColumns(10);
			mesExtranjero.setBounds(175, 57, 27, 20);
			panelExtranjero.add(mesExtranjero);
			
			JTextField anioExtranjero = new JTextField();
			anioExtranjero.setColumns(10);
			anioExtranjero.setBounds(205, 57, 55, 20);
			panelExtranjero.add(anioExtranjero);
			
			JLabel labelExtranjero = new JLabel("(dd mm aaaa)");
			labelExtranjero.setBounds(279, 60, 89, 14);
			panelExtranjero.add(labelExtranjero);
			
			JLabel lblPasDeOrigen = new JLabel("Pa\u00EDs de origen");
			lblPasDeOrigen.setBounds(10, 141, 101, 14);
			panelExtranjero.add(lblPasDeOrigen);
			
			paisOrigenExtranjero = new JTextField();
			paisOrigenExtranjero.setColumns(10);
			paisOrigenExtranjero.setBounds(146, 138, 114, 20);
			panelExtranjero.add(paisOrigenExtranjero);
			
			JLabel lblTipoDeVisa = new JLabel("Tipo de visa");
			lblTipoDeVisa.setBounds(10, 170, 84, 14);
			panelExtranjero.add(lblTipoDeVisa);
			
			JComboBox<String> tipoVisaExtranjero = new JComboBox<String>();
			tipoVisaExtranjero.setBounds(146, 165, 114, 25);
			tipoVisaExtranjero.addItem("Estudios");
			tipoVisaExtranjero.addItem("Trabajo");
			tipoVisaExtranjero.addItem("Negocios");
			tipoVisaExtranjero.addItem("Otros");
			panelExtranjero.add(tipoVisaExtranjero);
			
			JButton btnRegistrar_4 = new JButton("Registrar");
			btnRegistrar_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if( nombreExtranjero.getText().compareTo("")!=0 && rutExtranjero.getText().compareTo("")!=0 && passwordExtranjero.getText().compareTo("")!=0 && diaExtranjero.getText().compareTo("")!=0 && mesExtranjero.getText().compareTo("")!=0 && anioExtranjero.getText().compareTo("")!=0 && paisOrigenExtranjero.getText().compareTo("")!=0){
						try{
							registro.validarRut(rutExtranjero.getText());
							if(registro.buscarPersona(rutExtranjero.getText())==null){ //Revisar que no est� registrado el rut
								String fecha = diaExtranjero.getText() + "," + mesExtranjero.getText() + "," + anioExtranjero.getText();
								registro.validarFecha(fecha);
								Extranjero extranjeroRegistrar = new Extranjero();
								extranjeroRegistrar.setNombre(nombreExtranjero.getText());
								extranjeroRegistrar.setRut(rutExtranjero.getText());
								extranjeroRegistrar.setFechaNacimiento(fecha);
								if(rdbtnFemenino.isSelected())
									extranjeroRegistrar.setSexo("Femenino");
								else
									extranjeroRegistrar.setSexo("Masculino");
								extranjeroRegistrar.setPassword(passwordExtranjero.getText());
								extranjeroRegistrar.setPaisOrigen(paisOrigenExtranjero.getText());
								extranjeroRegistrar.setTipoVisa((String)tipoVisaExtranjero.getSelectedItem());
								
								if(registro.agregarExtranjero(extranjeroRegistrar)){ //Revisar que se realiza la insercion
									registro.alerta("Extranjero agregado correctamente.");
									volver(usuario);
								} else registro.error("Ocurri� un error al registrar.");
							} else registro.error("El rut especificado ya est� registrado.");	
						} catch(FormatoInvalidoException ex) {
							registro.error("Formato del rut o fecha inv�lido");
						}
					} else {
						registro.error("Todos los campos son obligatorios.");
					}
				}
			});
			btnRegistrar_4.setBounds(288, 220, 98, 26);
			panelExtranjero.add(btnRegistrar_4);
		
		
		//EMPRESA:
			JPanel panelEmpresa = new JPanel();
			panelEmpresa.setLayout(null);
			tabbedPane.addTab("Empresa", null, panelEmpresa, null);
			
			JLabel lblNombreEmpresa = new JLabel("Nombre");
			lblNombreEmpresa.setBounds(10, 11, 84, 14);
			panelEmpresa.add(lblNombreEmpresa);
			
			JTextField nombreEmpresa = new JTextField();
			nombreEmpresa.setBounds(146, 8, 114, 20);
			panelEmpresa.add(nombreEmpresa);
			nombreEmpresa.setColumns(10);
			
			JLabel lblRutEmpresa = new JLabel("RUT");
			lblRutEmpresa.setBounds(10, 36, 84, 14);
			panelEmpresa.add(lblRutEmpresa);
			
			JTextField rutEmpresa = new JTextField();
			rutEmpresa.setBounds(146, 33, 114, 20);
			panelEmpresa.add(rutEmpresa);
			rutEmpresa.setColumns(10);
			
			JLabel lblNewLabelEmpresa = new JLabel("Fecha de registro");
			lblNewLabelEmpresa.setBounds(10, 61, 126, 16);
			panelEmpresa.add(lblNewLabelEmpresa);
			
			JLabel lblEjEmpresa = new JLabel("Ej: 12345678-9");
			lblEjEmpresa.setBounds(278, 35, 89, 14);
			panelEmpresa.add(lblEjEmpresa);
			
			JLabel lblContraseaEmpresa = new JLabel("Contrase\u00F1a");
			lblContraseaEmpresa.setBounds(10, 112, 84, 14);
			panelEmpresa.add(lblContraseaEmpresa);
			
			JTextField passwordEmpresa = new JTextField();
			passwordEmpresa.setBounds(146, 109, 114, 20);
			panelEmpresa.add(passwordEmpresa);
			passwordEmpresa.setColumns(10);
			
			Calendar cal = Calendar.getInstance();
			int diaHoy = cal.get(Calendar.DAY_OF_MONTH);
			int mesHoy = cal.get(Calendar.MONTH)+1;
			int anioHoy = cal.get(Calendar.YEAR);
			
			JTextField diaEmpresa = new JTextField(Integer.toString(diaHoy));
			diaEmpresa.setEnabled(false);
			diaEmpresa.setEditable(false);
			diaEmpresa.setColumns(10);
			diaEmpresa.setBounds(146, 57, 27, 20);
			panelEmpresa.add(diaEmpresa);
			
			JTextField mesEmpresa = new JTextField(Integer.toString(mesHoy));
			mesEmpresa.setEnabled(false);
			mesEmpresa.setEditable(false);
			mesEmpresa.setColumns(10);
			mesEmpresa.setBounds(175, 57, 27, 20);
			panelEmpresa.add(mesEmpresa);
			
			JTextField anioEmpresa = new JTextField(Integer.toString(anioHoy));
			anioEmpresa.setEnabled(false);
			anioEmpresa.setEditable(false);
			anioEmpresa.setColumns(10);
			anioEmpresa.setBounds(205, 57, 55, 20);
			panelEmpresa.add(anioEmpresa);
			
			JLabel labelEmpresa = new JLabel("(dd mm aaaa)");
			labelEmpresa.setBounds(279, 60, 89, 14);
			panelEmpresa.add(labelEmpresa);
			
			JLabel lblRepresentanteLegal = new JLabel("Representante legal");
			lblRepresentanteLegal.setBounds(10, 88, 126, 14);
			panelEmpresa.add(lblRepresentanteLegal);
			
			JTextField representante = new JTextField();
			representante.setColumns(10);
			representante.setBounds(146, 85, 114, 20);
			panelEmpresa.add(representante);
			
			JLabel lblCapital = new JLabel("Capital");
			lblCapital.setBounds(10, 140, 126, 14);
			panelEmpresa.add(lblCapital);
			
			JTextField capital = new JTextField();
			capital.setColumns(10);
			capital.setBounds(146, 137, 114, 20);
			panelEmpresa.add(capital);
			
			JLabel lblRubro = new JLabel("Rubro");
			lblRubro.setBounds(10, 168, 126, 14);
			panelEmpresa.add(lblRubro);
			
			JTextField rubro = new JTextField();
			rubro.setColumns(10);
			rubro.setBounds(146, 165, 114, 20);
			panelEmpresa.add(rubro);
			
			JLabel lblDireccin = new JLabel("Direcci\u00F3n");
			lblDireccin.setBounds(10, 193, 126, 14);
			panelEmpresa.add(lblDireccin);
			
			JTextField direccion = new JTextField();
			direccion.setColumns(10);
			direccion.setBounds(146, 190, 114, 20);
			panelEmpresa.add(direccion);
			
			JComboBox<String> regionEmpresa = new JComboBox<String>();

			regionEmpresa.setBounds(143, 219, 125, 22);
			panelEmpresa.add(regionEmpresa);
			for(int i=0;i<registro.numeroRegiones();i++)
				regionEmpresa.addItem(registro.nombreRegion(i));
			
			JLabel lblRegionEmpresa = new JLabel("Regi\u00F3n");
			lblRegionEmpresa.setFont(new Font("Dialog", Font.BOLD, 12));
			lblRegionEmpresa.setBounds(11, 222, 55, 16);
			panelEmpresa.add(lblRegionEmpresa);
			JButton btnRegistrar_3 = new JButton("Registrar");
			btnRegistrar_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(nombreEmpresa.getText().compareTo("")!=0 && rutEmpresa.getText().compareTo("")!=0 && passwordEmpresa.getText().compareTo("")!=0 && representante.getText().compareTo("")!=0 && capital.getText().compareTo("")!=0 && rubro.getText().compareTo("")!=0 && direccion.getText().compareTo("")!=0 && esNumero(capital.getText())){
						Region regionInsertar = null;
						try{
							regionInsertar = registro.buscarRegion((String) regionEmpresa.getSelectedItem());
						} catch(LugarNoExisteException ex){
							registro.error("Error encontrando región.");
							volver(usuario);
						}
						try{
							registro.validarRut(rutEmpresa.getText());
							
							if(registro.buscarPersona(rutEmpresa.getText())==null){ //Revisar que no est� registrado el rut
								String fecha = diaEmpresa.getText() + "," + mesEmpresa.getText() + "," + anioEmpresa.getText();
								registro.validarFecha(fecha);
								Empresa empresaRegistrar = new Empresa();
								empresaRegistrar.setNombre(nombreEmpresa.getText());
								empresaRegistrar.setRut(rutEmpresa.getText());
								empresaRegistrar.setRegion(regionInsertar.getNombre());
								empresaRegistrar.setFechaNacimiento(fecha);
								empresaRegistrar.setNomRepresentante(representante.getText());
								empresaRegistrar.setCapital(Integer.parseInt(capital.getText()));
								empresaRegistrar.setRubro(rubro.getText());
								empresaRegistrar.setDireccion(direccion.getText());
								if(registro.agregarEmpresa(empresaRegistrar)){ //Revisar que se realiza la insercion
									registro.alerta("Empresa agregada correctamente.");
									volver(usuario);
								} else registro.error("Ocurri� un error al registrar.");
							} else registro.error("El rut especificado ya est� registrado.");	
						} catch(FormatoInvalidoException ex) {
							registro.error("Formato del rut o fecha inv�lido");
						}
					} else {
						registro.error("Todos los campos son obligatorios.");
					}
				}
			});
			btnRegistrar_3.setBounds(278, 224, 89, 23);
			panelEmpresa.add(btnRegistrar_3);
		
		//VOLVER:
		
			
			JPanel panel_3 = new JPanel();
			panel_3.setBounds(14, 293, 417, 48);
			contentPane.add(panel_3);
			panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
			
			JButton volver = new JButton("Volver");
			volver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					volver(usuario);
				}
			});
			panel_3.add(volver);
	}
	/**
	 * Vuelve a la ventana correspondiente. 
	 * @param registro
	 * @param usuario
	 */
	private void volver(Persona usuario) {
		if(usuario.getTipo().equals("Funcionario")){
			VentanaFuncionario ventana = VentanaFuncionario.getInstance();  
			ventana.mostrar();
			destruir();
		} else {
			VentanaAdministrador ventana = VentanaAdministrador.getInstance();
			ventana.mostrar();
			destruir();
		}
		
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
	public static VentanaRegistro getInstance(RegistroCivil registro, Persona usuario) {
		if(estaVentana==null)
			estaVentana = new VentanaRegistro(registro, usuario);
		return estaVentana;
	}
	private static boolean esNumero(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
}

