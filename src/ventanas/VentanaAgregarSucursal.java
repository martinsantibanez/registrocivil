package ventanas;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Administrador;
import logica.LugarNoExisteException;
import logica.Region;
import logica.RegistroCivil;
import logica.Sucursal;

import java.awt.Panel;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaAgregarSucursal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputNombre;
	private JTextField inputDireccion;
	private JTextField inputTelefono;
	private JTextField inputHorario;
	public static VentanaAgregarSucursal estaVentana;
	/**
	 * Create the frame.
	 */
	private VentanaAgregarSucursal(RegistroCivil registro, Administrador usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel_1 = new Panel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		panel_1.setBackground(UIManager.getColor("Button.background"));
		
		JLabel label = new JLabel("Nombre");
		label.setBounds(12, 40, 55, 16);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Direccion");
		label_1.setBounds(12, 64, 55, 16);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Telefono");
		label_2.setBounds(12, 88, 55, 16);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Horario");
		label_3.setBounds(12, 111, 55, 16);
		panel_1.add(label_3);
		
		inputNombre = new JTextField();
		inputNombre.setText((String) null);
		inputNombre.setColumns(10);
		inputNombre.setBounds(111, 38, 169, 20);
		panel_1.add(inputNombre);
		
		inputDireccion = new JTextField();
		inputDireccion.setText((String) null);
		inputDireccion.setColumns(10);
		inputDireccion.setBounds(111, 62, 169, 20);
		panel_1.add(inputDireccion);
		
		inputTelefono = new JTextField();
		inputTelefono.setText((String) null);
		inputTelefono.setColumns(10);
		inputTelefono.setBounds(111, 86, 169, 20);
		panel_1.add(inputTelefono);
		
		inputHorario = new JTextField();
		inputHorario.setText((String) null);
		inputHorario.setColumns(10);
		inputHorario.setBounds(111, 109, 169, 20);
		panel_1.add(inputHorario);
		
		JComboBox<String> regionCombo = new JComboBox<String>();
		regionCombo.setBounds(111, 12, 169, 20);
		panel_1.add(regionCombo);
		for(int i=0;i<registro.numeroRegiones();i++)
			regionCombo.addItem(registro.nombreRegion(i));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(0, 198, 98, 26);
		panel_1.add(btnVolver);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreRegion = (String) regionCombo.getSelectedItem();
				Region regIns = null;
				try {
					regIns = registro.buscarRegion(nombreRegion);
				} catch (LugarNoExisteException e1) {
					registro.error("La region no existe.");
					volver();
					e1.printStackTrace();
				}
				String nombre = inputNombre.getText();
				String direccion = inputDireccion.getText();
				String horario = inputHorario.getText();
				String telefono = inputTelefono.getText();
				if(nombre.compareTo("")!=0 && direccion.compareTo("")!=0 && horario.compareTo("")!=0 && telefono.compareTo("")!=0){
					Sucursal sucIns = new Sucursal();
					sucIns.setNombre(inputNombre.getText());
					sucIns.setDireccion(inputDireccion.getText());
					sucIns.setHorario(inputHorario.getText());
					sucIns.setTelefono(inputTelefono.getText());
					//TODO SET TRAMITES
					if(regIns.addSucursal(sucIns)){
						registro.alerta("Sucursal agregada correctamente.");
						inputNombre.setText("");
						inputDireccion.setText("");
						inputHorario.setText("");
						inputTelefono.setText("");
					} else {
						registro.error("Error: ya hay una sucursal con ese nombre.");
					}
				} else {
					registro.error("Error: todos los campos son obligatorios.");
				}
			}
		});
		btnAgregar.setBounds(321, 198, 98, 26);
		panel_1.add(btnAgregar);
		
		JLabel lblRegin = new JLabel("Regi\u00F3n");
		lblRegin.setBounds(12, 13, 55, 16);
		panel_1.add(lblRegin);
	}
	private void volver(){
		VentanaAdministrador ventana= VentanaAdministrador.getInstance();
		ventana.mostrar();
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
	public static VentanaAgregarSucursal getInstance(RegistroCivil registro, Administrador usuario) {
		if(estaVentana==null)
			estaVentana = new VentanaAgregarSucursal(registro, usuario);
		return estaVentana;
	}
}
