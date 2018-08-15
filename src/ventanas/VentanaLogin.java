package ventanas;
import logica.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Font;


public class VentanaLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField txtRut;
	private RegistroCivil registro;
	private static VentanaLogin estaVentana;

	/**
	 * 
	 * @param registro
	 */
	private VentanaLogin(RegistroCivil registro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		setTitle("Bienvenido");
		this.registro = registro;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(156, 117, 96, 14);
		panel.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(282, 114, 136, 20);
		panel.add(passwordField);
		
		txtRut = new JTextField();
		txtRut.setToolTipText("rut");
		txtRut.setBounds(282, 75, 136, 20);
		panel.add(txtRut);
		txtRut.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("deprecation")
				Persona persona = registro.login(txtRut.getText(), passwordField.getText());
				if(persona != null){
					if(persona.getTipo().equals("Registrado"))
						loggear((Registrado)persona);
					else if(persona.getTipo().equals("Funcionario"))
						loggear((Funcionario)persona);
					else if(persona.getTipo().equals("Administrador"))
						loggear((Administrador)persona);
				} else {
					registro.error("Usuario o contraseña incorrectos.");
				}
			}
		});
		btnEntrar.setBounds(234, 232, 89, 23);
		panel.add(btnEntrar);
		
		Label lblRut = new Label("RUT");
		lblRut.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRut.setBounds(155, 75, 62, 22);
		panel.add(lblRut);
		
		JLabel lblNewLabel = new JLabel("SISTEMA DE GESTI\u00D3N DE REGISTRO CIVIL");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(113, 16, 330, 14);
		panel.add(lblNewLabel);
	}

	
	private void loggear(Registrado usuario){
		VentanaRegistrado venRegistrado = VentanaRegistrado.getInstance(registro, usuario);
		venRegistrado.mostrar();
		destruir();
	}
	
	private void loggear(Funcionario usuario){
		VentanaFuncionario venFuncionario = VentanaFuncionario.getInstance(registro, usuario);
		venFuncionario.mostrar();
		destruir();
	}
	private void loggear(Administrador usuario){
		VentanaAdministrador venAdministrador = VentanaAdministrador.getInstance(registro, usuario);
		venAdministrador.mostrar();
		destruir();
	}
	
	public static VentanaLogin getInstance(RegistroCivil registro) throws IOException{
		if(estaVentana==null)
			estaVentana = new VentanaLogin(registro);
		return estaVentana;
	}
	
	public static VentanaLogin getInstance() throws IOException{
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

