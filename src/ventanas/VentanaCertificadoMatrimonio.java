package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import logica.Persona;
import logica.RegistroCivil;
import java.awt.Toolkit;

public class VentanaCertificadoMatrimonio extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static VentanaCertificadoMatrimonio estaVentana;
	
	private VentanaCertificadoMatrimonio(RegistroCivil registro, Persona persona, Persona conyuge) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 334);
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
		lblRegistroCivil.setBounds(0, 0, 138, 23);
		panel.add(lblRegistroCivil);
		
		JLabel lblCivilEIdentificacin = new JLabel("CIVIL E IDENTIFICACI\u00D3N");
		lblCivilEIdentificacin.setFont(new Font("Arial", Font.BOLD, 12));
		lblCivilEIdentificacin.setBounds(0, 18, 131, 23);
		panel.add(lblCivilEIdentificacin);
		
		JLabel lblCertificadoDeNacimiento = new JLabel("CERTIFICADO DE MATRIMONIO");
		lblCertificadoDeNacimiento.setForeground(new Color(184, 134, 11));
		lblCertificadoDeNacimiento.setBounds(79, 53, 245, 16);
		panel.add(lblCertificadoDeNacimiento);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 43, 414, 2);
		panel.add(separator);
		
		JLabel lblFechaDeRegistro = new JLabel("FECHA DE MATRIMONIO");
		lblFechaDeRegistro.setBounds(12, 208, 148, 16);
		panel.add(lblFechaDeRegistro);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(12, 91, 148, 16);
		panel.add(lblNombre);
		
		JLabel lblRut = new JLabel("RUT");
		lblRut.setBounds(12, 106, 148, 16);
		panel.add(lblRut);
		
		JLabel lblFechaDeNacimiento = new JLabel("FECHA DE NACIMIENTO");
		lblFechaDeNacimiento.setBounds(12, 175, 148, 16);
		panel.add(lblFechaDeNacimiento);
		
		JLabel lblFechaDeNacimientoc = new JLabel("FECHA DE NACIMIENTO");
		lblFechaDeNacimientoc.setBounds(12, 119, 148, 16);
		panel.add(lblFechaDeNacimientoc);
		
		JLabel lblN = new JLabel("NOMBRE CONYUGE");
		lblN.setBounds(12, 142, 131, 16);
		panel.add(lblN);
		
		JLabel lblRut_1 = new JLabel("RUT");
		lblRut_1.setBounds(12, 159, 55, 16);
		panel.add(lblRut_1);

		JLabel fechaRegistro = new JLabel();
		fechaRegistro.setText("");
		fechaRegistro.setBounds(164, 208, 146, 16);
		panel.add(fechaRegistro);
		
		JLabel nombre = new JLabel();
		nombre.setText(persona.getNombre());
		nombre.setBounds(191, 91, 119, 16);
		panel.add(nombre);
		
		JLabel rut = new JLabel();
		rut.setText(persona.getRut());
		rut.setBounds(191, 106, 119, 16);
		panel.add(rut);
		
		JLabel fechaNacimiento = new JLabel();
		fechaNacimiento.setText(persona.getFechaSlash());
		fechaNacimiento.setBounds(191, 119, 119, 16);
		panel.add(fechaNacimiento);
		
		JLabel nombreconyuge = new JLabel();
		nombreconyuge.setText(conyuge.getNombre());
		nombreconyuge.setBounds(191, 142, 119, 16);
		panel.add(nombreconyuge);
		
		JLabel rutconyuge = new JLabel();
		rutconyuge.setText(conyuge.getRut());
		rutconyuge.setBounds(191, 159, 119, 16);
		panel.add(rutconyuge);
		
		JButton btnGuardarEnPdf = new JButton("Guardar en PDF");
		btnGuardarEnPdf.setBounds(164, 259, 148, 26);
		btnGuardarEnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					registro.EscrituraPersona(persona);
					JOptionPane.showMessageDialog(null, "guardado en pdf correctamente");
				}catch (Exception e1){
					System.out.println(e1);
		        }
				
			}
		});
		
		panel.add(btnGuardarEnPdf);
		
		
		JLabel fechaconyuge = new JLabel();
		fechaconyuge.setText(conyuge.getFechaSlash());
		fechaconyuge.setBounds(191, 175, 121, 16);
		panel.add(fechaconyuge);

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				destruir();
			}
		});
		btnVolver.setBounds(0, 259, 98, 26);
		panel.add(btnVolver);
		
		JLabel fechamatrimonio = new JLabel();
		fechamatrimonio.setText(persona.getFechaMatrimonio());
		fechamatrimonio.setBounds(201, 209, 46, 14);
		panel.add(fechamatrimonio);
	}
	
	public static VentanaCertificadoMatrimonio getInstance(RegistroCivil registro, Persona persona, Persona conyuge){
		if(estaVentana==null)
			estaVentana = new VentanaCertificadoMatrimonio(registro, persona, conyuge);
		return estaVentana;
	}
	
	public static VentanaCertificadoMatrimonio getInstance(){
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


