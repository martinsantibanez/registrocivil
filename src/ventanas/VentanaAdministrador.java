package ventanas;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Administrador;
import logica.EscrituraExcel;
import logica.LugarNoExisteException;
import logica.Persona;
import logica.Region;
import logica.RegistroCivil;
import logica.Sucursal;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class VentanaAdministrador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField rut;
	public static VentanaAdministrador estaVentana;

	private VentanaAdministrador(RegistroCivil registro, Administrador usuario) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("logo.jpg"));
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido "+ usuario.getNombre());
		lblBienvenido.setBounds(10, 0, 382, 16);
		panel.add(lblBienvenido);
		
		JLabel lblPanelSucursal = new JLabel("Panel Sucursal (editar, eliminar,agregar)");
		lblPanelSucursal.setBounds(10, 27, 339, 16);
		panel.add(lblPanelSucursal);
		
		JLabel lblNewLabel = new JLabel("Nombre Sucursal");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel.setBounds(40, 85, 105, 16);
		panel.add(lblNewLabel);
		
		
		
		JLabel lblPanelRegistrado = new JLabel("Panel Registrado (editar, ceritificados, etc)");
		lblPanelRegistrado.setBounds(12, 177, 258, 16);
		panel.add(lblPanelRegistrado);
		
		rut = new JTextField();
		rut.setBounds(156, 206, 114, 20);
		panel.add(rut);
		rut.setColumns(10);
		
		JLabel lblRut = new JLabel("RUT");
		lblRut.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRut.setBounds(76, 208, 55, 16);
		panel.add(lblRut);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				Persona persona = registro.buscarPersona(rut.getText());
				if(persona!=null){
					VentanaPanelRegistrados panel = VentanaPanelRegistrados.getInstance(registro, (Persona)usuario,persona);
					panel.mostrar();
					ocultar();
				} else {
					registro.error("RUT Invalido.");
				}
			}
		});
		btnContinuar.setBounds(309, 203, 105, 26);
		panel.add(btnContinuar);
		
		JButton btnNewButton = new JButton("Entregar Reporte");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaFiltroReporte reporte = VentanaFiltroReporte.getInstance(registro, usuario);
				reporte.mostrar();
				ocultar();
			}
		});
		btnNewButton.setBounds(139, 239, 143, 26);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro ventana = VentanaRegistro.getInstance(registro, usuario);
				ventana.mostrar();
				ocultar();
			}
		});
		btnNewButton_1.setBounds(139, 272, 143, 26);
		panel.add(btnNewButton_1);
		
		JLabel lblRegion = new JLabel("Region");
		lblRegion.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblRegion.setBounds(40, 56, 55, 16);
		panel.add(lblRegion);
		
		JComboBox<String> choice_region = new JComboBox<String>();
		choice_region.setBounds(152, 50, 125, 22);
		panel.add(choice_region);
		for(int i=0;i<registro.numeroRegiones();i++)
			choice_region.addItem(registro.nombreRegion(i));
		
		JComboBox<String> choice_sucursal = new JComboBox<String>();
		choice_sucursal.setBounds(151, 79, 126, 22);
		panel.add(choice_sucursal);
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choice_sucursal.removeAllItems();
				String regSel = (String)choice_region.getSelectedItem();
				String[] vector = new String [registro.numeroDeSucursales(regSel)];
				vector = registro.nombreSucursales(regSel);
				for(int i=0;i<registro.numeroDeSucursales(regSel);i++){
					choice_sucursal.addItem(vector[i]);
				}
			}	
		});
		btnSeleccionar.setBounds(307, 46, 105, 26);
		panel.add(btnSeleccionar);
		
		JButton btnBuscar = new JButton("Continuar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String regSel = (String)choice_region.getSelectedItem();
				Region regionSeleccionada;
				Sucursal suc;
				try {
					regionSeleccionada = registro.buscarRegion(regSel);
					suc = regionSeleccionada.buscarSucursal((String)choice_sucursal.getSelectedItem());
					VentanaEditarSucursal ventana = VentanaEditarSucursal.getInstance(registro, suc, (Administrador)usuario, regionSeleccionada);
					ventana.mostrar();
					ocultar();
				} catch (LugarNoExisteException e1) {
					registro.error("Sucursal o region incorrectos.");
				} 
			}
		});
		btnBuscar.setBounds(307, 80, 105, 26);
		panel.add(btnBuscar);
		
		JButton btnAgregarNuevaSucursal = new JButton("Agregar Nueva Sucursal");
		btnAgregarNuevaSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAgregarSucursal ventana = VentanaAgregarSucursal.getInstance(registro, usuario);
				ventana.mostrar();
				ocultar();
			}
		});
		btnAgregarNuevaSucursal.setBounds(238, 118, 177, 26);
		panel.add(btnAgregarNuevaSucursal);
		
		JButton FinSesion = new JButton("Finalizar Sesion");//cierra el programa y lo guarda
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
		FinSesion.setBounds(309, 342, 177, 23);
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

	public static VentanaAdministrador getInstance(RegistroCivil registro, Administrador usuario) {
		if(estaVentana==null)
			estaVentana = new VentanaAdministrador(registro, usuario);
		return estaVentana;
	}
	public static VentanaAdministrador getInstance(){
		return estaVentana;
	}
}

