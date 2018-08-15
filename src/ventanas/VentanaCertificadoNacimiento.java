package ventanas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.LugarNoExisteException;
import logica.Persona;
import logica.Registrado;
import logica.RegistroCivil;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaCertificadoNacimiento extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static VentanaCertificadoNacimiento estaVentana;
	
	/**
	 * Muestra certificado de nacimiento
	 * @param persona
	 */
	private VentanaCertificadoNacimiento(RegistroCivil registro, Persona persona) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 549, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblRegistroCivil = new JLabel("SERVICIO DE REGISTRO\r\n");
		lblRegistroCivil.setFont(new Font("Arial", Font.BOLD, 12));
		lblRegistroCivil.setBounds(186, 4, 138, 23);
		panel.add(lblRegistroCivil);
		
		JLabel lblCivilEIdentificacin = new JLabel("CIVIL E IDENTIFICACI\u00D3N");
		lblCivilEIdentificacin.setFont(new Font("Arial", Font.BOLD, 12));
		lblCivilEIdentificacin.setBounds(186, 22, 131, 23);
		panel.add(lblCivilEIdentificacin);
		
		JLabel lblCertificadoDeNacimiento = new JLabel("CERTIFICADO DE NACIMIENTO");
		lblCertificadoDeNacimiento.setForeground(new Color(184, 134, 11));
		lblCertificadoDeNacimiento.setBounds(140, 58, 240, 16);
		panel.add(lblCertificadoDeNacimiento);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 43, 324, 2);
		panel.add(separator);
		
		JLabel lblCircunscripcin = new JLabel("CIRCUNSCRIPCI\u00D3N");
		lblCircunscripcin.setBounds(12, 85, 148, 16);
		panel.add(lblCircunscripcin);
		
		JLabel circ = new JLabel(); 
		//circ.setText(registro.nombreSucursalRegistradoRut(persona.getRut()));
		try {
			circ.setText(registro.nombreSucursalPersonaRut(persona.getRut()));
		} catch (LugarNoExisteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		circ.setBounds(191, 85, 189, 16);
		panel.add(circ);
		
		JButton btnGuardarEnPdf = new JButton("Guardar en PDF");
		btnGuardarEnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					registro.EscrituraPersona(persona);
					registro.alerta("Guardado en PDF correctamente.");
				}catch (Exception e1){
		            System.out.println(e1);
		        }
			}
		});
		btnGuardarEnPdf.setBounds(333, 321, 148, 26);
		panel.add(btnGuardarEnPdf);
		
		JLabel lblFechaDeRegistro = new JLabel("FECHA DE REGISTRO");
		lblFechaDeRegistro.setBounds(12, 107, 148, 16);
		panel.add(lblFechaDeRegistro);
		
		JLabel fechaRegistro = new JLabel();
		fechaRegistro.setText(persona.getFechaDeInscripcion());
		fechaRegistro.setBounds(191, 107, 213, 16);
		panel.add(fechaRegistro);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(12, 129, 148, 16);
		panel.add(lblNombre);
		
		JLabel lblRut = new JLabel("RUT");
		lblRut.setBounds(12, 151, 148, 16);
		panel.add(lblRut);
		
		JLabel nombre = new JLabel();
		nombre.setText(persona.getNombre());
		nombre.setBounds(191, 129, 261, 16);
		panel.add(nombre);
		
		JLabel rut = new JLabel();
		rut.setText(persona.getRut());
		rut.setBounds(191, 151, 240, 16);
		panel.add(rut);
		
		JLabel lblFechaDeNacimieneto = new JLabel("FECHA DE NACIMIENTO");
		lblFechaDeNacimieneto.setBounds(12, 173, 148, 16);
		panel.add(lblFechaDeNacimieneto);
		
		JLabel fechaNacimiento = new JLabel();
		fechaNacimiento.setText(persona.getFechaSlash());
		fechaNacimiento.setBounds(191, 173, 261, 16);
		panel.add(fechaNacimiento);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destruir();
			}
		});
		btnVolver.setBounds(42, 321, 98, 26);
		panel.add(btnVolver);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(204, 43, 324, 2);
		panel.add(separator_1);
		if(persona.getTipo().equals("Registrado")){
			JLabel lblPrecio = new JLabel("Precio: $"+registro.calcularDescuento((Registrado)persona));
			lblPrecio.setBounds(122, 261, 154, 20);
			panel.add(lblPrecio);
		}
	}
	
	public static VentanaCertificadoNacimiento getInstance(){
		return estaVentana;
	}
	
	public static VentanaCertificadoNacimiento getInstance(RegistroCivil registro, Persona persona){
		if(estaVentana == null)
			estaVentana= new VentanaCertificadoNacimiento(registro, persona);
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
