package ventanas;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Persona;
import logica.RegistroCivil;
import logica.Sucursal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class VentanaPanelRegistrados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fechaNac;
	private JTextField rut;
	private JTextField nombre;
	private JTextField sexo;
	private JTextField estadoCivil;
	public static VentanaPanelRegistrados estaVentana;
	
	/**
	 * Panel para realizar operaciones sobre los registrados. Accesible solo por administradores y funcionarios.
	 * @param registro
	 * @param usuario
	 * @param persona
	 */
	private VentanaPanelRegistrados(RegistroCivil registro, Persona usuario, Persona persona) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Panel de Registrados");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(114, 0, 209, 49);
		panel.add(lblNewLabel);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 60, 84, 14);
		panel.add(lblNombre);
		
		JLabel lblRut = new JLabel("RUT");
		lblRut.setBounds(12, 85, 84, 14);
		panel.add(lblRut);
		
		JLabel lblFechaNac = new JLabel("Fecha de nacimiento");
		lblFechaNac.setBounds(12, 110, 126, 16);
		panel.add(lblFechaNac);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(12, 137, 55, 16);
		panel.add(lblSexo);
		
		JLabel lblEstado = new JLabel("Estado Civil");
		lblEstado.setBounds(12, 165, 98, 16);
		panel.add(lblEstado);

		nombre = new JTextField(persona.getNombre());
		nombre.setEditable(false);
		nombre.setColumns(10);
		nombre.setBounds(148, 57, 114, 20);
		panel.add(nombre);
		
		rut = new JTextField(persona.getRut());
		rut.setEditable(false);
		rut.setColumns(10);
		rut.setBounds(148, 82, 114, 20);
		panel.add(rut);

		fechaNac = new JTextField(persona.getFechaSlash());
		fechaNac.setEditable(false);
		fechaNac.setColumns(10);
		fechaNac.setBounds(148, 108, 114, 20);
		panel.add(fechaNac);
		
		sexo = new JTextField(persona.getSexo());
		sexo.setEditable(false);
		sexo.setColumns(10);
		sexo.setBounds(148, 135, 114, 20);
		panel.add(sexo);
		
		estadoCivil = new JTextField(persona.getEstadoCivil());
		estadoCivil.setEditable(false);
		estadoCivil.setColumns(10);
		estadoCivil.setBounds(148, 163, 114, 20);
		panel.add(estadoCivil);
		JLabel lblOpciones = new JLabel("OPCIONES");
		lblOpciones.setBounds(304, 123, 81, 16);
		panel.add(lblOpciones);
		
		JButton btnCertificado = new JButton("Emitir Certificado");
		btnCertificado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCertificadosRegistrado certs = VentanaCertificadosRegistrado.getInstance(registro, usuario, persona);
				certs.mostrar();
				ocultar();
			}
		});
		btnCertificado.setBounds(267, 145, 141, 26);
		panel.add(btnCertificado);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(persona.getTipo().equals("Registrado")){
					VentanaEditarPersona edita = VentanaEditarPersona.getInstance(registro, usuario, persona);
					edita.mostrar();
					ocultar();
				} else if (persona.getTipo().equals("Funcionario")) {
					if(usuario.getTipo().equals("Administrador")){
						VentanaEditarPersona edita = VentanaEditarPersona.getInstance(registro, usuario, persona);
						edita.mostrar();
						ocultar();
					} else
						registro.error("No posee los permisos suficientes para realizar esta edición.");
				} else 
					registro.error("No posee los permisos suficientes para realizar esta edición.");
			}
		});
		btnEditar.setBounds(267, 177, 141, 26);
		panel.add(btnEditar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver(usuario);
			}
		});
		btnVolver.setBounds(12, 213, 81, 26);
		panel.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(255, 0, 0));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(persona.getTipo().equals("Registrado")||(persona.getTipo().equals("Funcionario") && usuario.getTipo().equals("Administrador"))){
					int respuesta = JOptionPane.showConfirmDialog(null, "Seguro que quieres eliminarlo?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
					if(respuesta == JOptionPane.YES_OPTION){
						Sucursal sucReg = registro.buscarSucursalPersonaRut(persona.getRut());
						sucReg.eliminarPersona(persona.getRut());
						volver(usuario);
					}
				}
				else
					registro.error("No posee los permisos suficientes para realizar esta eliminación.");
			}
		});
		btnEliminar.setBounds(267, 213, 141, 26);
		panel.add(btnEliminar);
	}
	/**
	 * Vuelve a la ventana correspondiente, si es funcionario o administrador.
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
	
	public static VentanaPanelRegistrados getInstance(){
		return estaVentana;
	}
	
	public static VentanaPanelRegistrados getInstance(RegistroCivil registro, Persona usuario, Persona persona){
		if(estaVentana == null)
			estaVentana = new VentanaPanelRegistrados(registro, usuario, persona);
		return estaVentana;
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
	
}
