package ventanas;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.GeneradorCertificados;
import logica.Persona;
import logica.Registrado;
import logica.RegistroCivil;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaCertificadosRegistrado extends JFrame {

	/**
	 * El valor de cada certificado es de $2500
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaCertificadosRegistrado estaVentana;

	/**
	 * Accesible por Registrado, Funcionario y Administrador.
	 * Los parametros son iguales si es un Registrado.
	 * Emite los distintos tipos de certificado.
	 * @param usuario
	 * @param persona
	 */
	private VentanaCertificadosRegistrado(RegistroCivil registro, Persona usuario, Persona cliente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnCertificadoDeNacimiento = new JButton("NACIMIENTO");
		btnCertificadoDeNacimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cliente.getTipo().equals("Registrado"))
					((Registrado)cliente).aumentarNumTramites(); //al realizar un nuevo tramite aumenta la cantidad de realizados, solo para registrados
					@SuppressWarnings("unused")
					GeneradorCertificados generador = new GeneradorCertificados(registro, cliente);
			}
		});
		btnCertificadoDeNacimiento.setBounds(67, 47, 121, 26);
		panel.add(btnCertificadoDeNacimiento);
		
		JButton btnMatrimonio = new JButton("MATRIMONIO");
		btnMatrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cliente.getEstadoCivil().equals("Casado")){
					if(cliente.getTipo().equals("Registrado"))
						((Registrado)cliente).aumentarNumTramites(); //al realizar un nuevo tramite aumenta la cantidad de realizados, solo para registrados
					 @SuppressWarnings("unused")
					GeneradorCertificados generador = new GeneradorCertificados(cliente, registro);
				}
				else{
					registro.error("No existe un registro de matrimonio asociado a este rut");
				} 
					
			}
		});
		btnMatrimonio.setBounds(67, 83, 121, 26);
		panel.add(btnMatrimonio);
		
		JLabel lblTipoDeCertificado = new JLabel("Tipo de Certificado");
		lblTipoDeCertificado.setBounds(67, 19, 177, 16);
		panel.add(lblTipoDeCertificado);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver(usuario);
			}
		});
		btnVolver.setBounds(12, 213, 98, 26);
		panel.add(btnVolver);
	}
	/**
	 * Vuelve a la ventana anterior.
	 * Si es Registrado lo lleva a VentanaRegistrado
	 * Si es Funcionario o Administrador lo lleva a VentanaPanelRegistrados
	 * @param registro
	 * @param usuario
	 */
	private void volver(Persona usuario) {
		if(usuario.getTipo().equals("Registrado")){
			VentanaRegistrado venRegistrado = VentanaRegistrado.getInstance();
			venRegistrado.mostrar();
			destruir();
		} else {
			VentanaPanelRegistrados venPanel = VentanaPanelRegistrados.getInstance();
			venPanel.mostrar();
			destruir();
		}
	}
	
	public static VentanaCertificadosRegistrado getInstance(RegistroCivil registro, Persona emisor, Persona cliente){
		if(estaVentana == null)
			estaVentana= new VentanaCertificadosRegistrado(registro, emisor, cliente);
		return estaVentana;
	}
	
	public static VentanaCertificadosRegistrado getInstance(){
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
