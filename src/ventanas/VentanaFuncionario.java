package ventanas;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.EscrituraExcel;
import logica.Funcionario;
import logica.LugarNoExisteException;
import logica.Persona;
import logica.RegistroCivil;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
public class VentanaFuncionario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField rut;
	public static VentanaFuncionario estaVentana;
	
	
	/**
	 * Ventana principal del funcionario.
	 * @param registro
	 * @param usuario
	 */
	private VentanaFuncionario(RegistroCivil registro, Funcionario usuario) {
		setResizable(false);
		setBackground(new Color(255, 0, 0));
		setForeground(new Color(124, 252, 0)); //
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(60, 179, 113));
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		setTitle("Funcionario");
		JLabel lblSucursal = new JLabel("Sucursal ");
		lblSucursal.setBounds(24, 22, 250, 20);
		try {
			lblSucursal.setText(registro.buscarSucursalFuncionarioRut(usuario.getRut()).getNombre());
		} catch (LugarNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel.add(lblSucursal);
		
		JButton btnReporte = new JButton("Entregar Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaFiltroReporte reporte = VentanaFiltroReporte.getInstance(registro, (Funcionario)usuario);
				reporte.mostrar();
				ocultar();
			}
		});
		btnReporte.setBounds(127, 158, 140, 23);
		panel.add(btnReporte);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro ventana = VentanaRegistro.getInstance(registro, usuario);
				ventana.mostrar();
				ocultar();
			}
		});
		btnRegistrar.setBounds(127, 193, 140, 23);
		panel.add(btnRegistrar);
		
		JLabel lblPanelRegistrados = new JLabel("Panel Registrados (Editar, certificados, etc)");
		lblPanelRegistrados.setBounds(34, 65, 279, 16);
		panel.add(lblPanelRegistrados);
		
		rut = new JTextField();
		rut.setBounds(143, 104, 124, 20);
		panel.add(rut);
		rut.setColumns(10);
		
		JLabel lblRut = new JLabel("RUT");
		lblRut.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRut.setBounds(76, 107, 65, 16);
		panel.add(lblRut);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Persona persona = registro.buscarPersona(rut.getText());
				if(persona!=null){
					VentanaPanelRegistrados panel = VentanaPanelRegistrados.getInstance(registro, (Persona) usuario, persona);
					panel.mostrar();
					ocultar();
				} else {
					registro.error("RUT Invalido.");
				}
			}
		});
		btnContinuar.setBounds(297, 100, 98, 26);
		panel.add(btnContinuar);
		
		JLabel lblBienvenido = new JLabel("Bienvenido "+ usuario.getNombre());
		lblBienvenido.setBounds(24, 0, 250, 20);
		panel.add(lblBienvenido);
		
		JButton FinSesion = new JButton("Finalizar Sesion");
		FinSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EscrituraExcel esc = new EscrituraExcel();
				try {
					if (esc.EscribirDatos(registro)){
						registro.alerta("Guardado Correctamente");
						dispose();
					}
					else registro.alerta("No fue guardado correctamente, intentelo de nuevo");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		FinSesion.setBounds(269, 228, 140, 23);
		panel.add(FinSesion);
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
	public static VentanaFuncionario getInstance(){
		return estaVentana;
	}
	public static VentanaFuncionario getInstance(RegistroCivil registro, Funcionario usuario){
		if(estaVentana==null)
			estaVentana = new VentanaFuncionario(registro, usuario);
		return estaVentana;
	}
}
