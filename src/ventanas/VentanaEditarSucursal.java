package ventanas;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Administrador;
import logica.Region;
import logica.RegistroCivil;
import logica.Sucursal;

import java.awt.Panel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class VentanaEditarSucursal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreEditar;
	private JTextField direccionEditar;
	private JTextField telefonoEditar;
	private JTextField horarioEditar;
	public static VentanaEditarSucursal estaVentana;
	/**
	 * Create the frame.
	 */
	private VentanaEditarSucursal(RegistroCivil registro, Sucursal suc, Administrador usuario, Region region) {
		setTitle("Sucursal -"+suc.getNombre());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		Panel panel_2 = new Panel();
		panel_2.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Info", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(80, 24, 254, 139);
		panel_2.add(textPane);
		textPane.setText("Nombre: "+suc.getNombre()+"\n"+"Direccion: "+suc.getDireccion()+"\n"+/*"Region: "+ suc.getRegion()+*/"\n"+"Horario: "+ suc.getHorario()+"\n"+"Telefono: "+ suc.getTelefono());
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				volver();
			}
		});
		btnVolver.setBounds(0, 198, 107, 26);
		panel_2.add(btnVolver);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog (null, "CONFIRMAR ELIMINAR SUCURSAL:\n" + suc.getNombre(),"Confirmar", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					if(registro.eliminarSucursal(suc)){
						registro.alerta("Sucursal eliminada correctamente.");
						volver();
					} else {
						registro.error("Error al eliminar.");
					}
				}
			}
		});
		btnEliminar.setBounds(321, 198, 98, 26);
		panel_2.add(btnEliminar);
		
		Panel panel = new Panel();
		panel.setBackground(UIManager.getColor("Button.background"));
		tabbedPane.addTab("Editar", null, panel, null);
		tabbedPane.setBackgroundAt(1, UIManager.getColor("Button.disabledToolBarBorderBackground"));
		panel.setLayout(null);
		
		JLabel nombre = new JLabel("Nombre");
		nombre.setBounds(12, 11, 55, 16);
		panel.add(nombre);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(12, 35, 55, 16);
		panel.add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(12, 59, 55, 16);
		panel.add(lblTelefono);
		
		JLabel lblHorario = new JLabel("Horario");
		lblHorario.setBounds(12, 82, 55, 16);
		panel.add(lblHorario);
		
		nombreEditar = new JTextField();
		nombreEditar.setBounds(111, 9, 169, 20);
		panel.add(nombreEditar);
		nombreEditar.setColumns(10);
		nombreEditar.setText(suc.getNombre());
		
		direccionEditar = new JTextField();
		direccionEditar.setBounds(111, 33, 169, 20);
		panel.add(direccionEditar);
		direccionEditar.setColumns(10);
		direccionEditar.setText(suc.getDireccion());
		
		telefonoEditar = new JTextField();
		telefonoEditar.setBounds(111, 57, 169, 20);
		panel.add(telefonoEditar);
		telefonoEditar.setColumns(10);
		telefonoEditar.setText(suc.getTelefono());
		
		horarioEditar = new JTextField();
		horarioEditar.setBounds(111, 80, 169, 20);
		panel.add(horarioEditar);
		horarioEditar.setColumns(10);
		horarioEditar.setText(suc.getHorario());
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnNewButton.setBounds(0, 198, 98, 26);
		panel.add(btnNewButton);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nuevoNombre = nombreEditar.getText();
				String nuevaDireccion = direccionEditar.getText();
				String nuevoHorario = horarioEditar.getText();
				String nuevoTelefono = telefonoEditar.getText();
                if( nuevoNombre.compareTo("") != 0 && nuevaDireccion.compareTo("") != 0 && nuevoHorario.compareTo("") != 0 && nuevoTelefono.compareTo("") != 0 ){
    				if((nuevoNombre.equals(suc.getNombre())) || (region.renombrarSucursal(suc, nombreEditar.getText()))){
    					suc.setNombre(nuevoNombre);
    					suc.setDireccion(nuevaDireccion);
    					suc.setHorario(nuevoHorario);
    					suc.setTelefono(nuevoTelefono);
    					registro.alerta("Sucursal editada correctamente.");
    					volver();
    				} else {
    					registro.error("Ya existe una sucursal con ese nombre.");
    				}
                } else {
                    registro.error("Todos los campos son obligatorios.");
                }
			}
		});
		btnEditar.setBounds(321, 198, 98, 26);
		panel.add(btnEditar);
	}
	private void volver(){
		VentanaAdministrador ventana = VentanaAdministrador.getInstance();
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
	public static VentanaEditarSucursal getInstance(RegistroCivil registro, Sucursal suc, Administrador usuario,
			Region region) {
		if(estaVentana==null)
			estaVentana = new VentanaEditarSucursal(registro, suc, usuario, region);
		return estaVentana;
	}
}

