package ventanas;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Registrado;
import logica.RegistroCivil;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistrado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static VentanaRegistrado estaVentana;

	/**
	 * Ventana de los registrados.
	 * @param registro
	 * @param usuario
	 */
	private VentanaRegistrado(RegistroCivil registro, Registrado usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnEmitirCertificados = new JButton("Emitir certificados");
		btnEmitirCertificados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCertificadosRegistrado certificados = VentanaCertificadosRegistrado.getInstance(registro, usuario, usuario);
				certificados.mostrar();
				ocultar();
			}
		});
		btnEmitirCertificados.setBounds(126, 72, 174, 52);
		panel.add(btnEmitirCertificados);
		
		JLabel lblBienvenido = new JLabel();
		lblBienvenido.setBounds(35, 11, 296, 14);
		lblBienvenido.setText("Bienvenido "+usuario.getNombre().toUpperCase());
		panel.add(lblBienvenido);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(35, 28, 192, 14);
		lblNewLabel.setText(usuario.getRut());
		panel.add(lblNewLabel);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registro.alerta("Hasta pronto.");
				destruir();
			}
		});
		btnVolver.setBounds(15, 189, 115, 29);
		panel.add(btnVolver);
	}
	
	public static VentanaRegistrado getInstance(RegistroCivil registro, Registrado usuario){
		if(estaVentana == null)
			estaVentana = new VentanaRegistrado(registro,usuario);
		return estaVentana;
	}
	
	public static VentanaRegistrado getInstance(){
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
