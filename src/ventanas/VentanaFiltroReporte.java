package ventanas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;

import logica.Persona;
import logica.RegistroCivil;

import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class VentanaFiltroReporte extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_anio;
	public static VentanaFiltroReporte estaVentana;
	
	/**
	 * Genera los reportes.
	 * Accesible por Funcionario y Administrador
	 * Vuelve a VentanaFuncionario si es funcionario, VentanaAdministrador si es Administrador
	 * @param registro
	 * @param usuario
	 */
	private VentanaFiltroReporte(RegistroCivil registro, Persona usuario) {
		setFont(UIManager.getFont("InternalFrame.titleFont"));
		setTitle("Reporte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Registro ");
		label.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		label.setAlignment(Label.CENTER);
		label.setBounds(53, 53, 55, 22);
		contentPane.add(label);
		
		JComboBox<String> choice_registro = new JComboBox<String>();
		choice_registro.setBounds(131, 55, 156, 20);
		contentPane.add(choice_registro);
		//choice_registro.add("Registro");
		choice_registro.addItem("Nacimientos");
		choice_registro.addItem("Defunciones");
		choice_registro.addItem("Matrimonios");
		
		Label label_2 = new Label("Regi\u00F3n");
		label_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		label_2.setBounds(53, 97, 55, 22);
		contentPane.add(label_2);
		
		JComboBox<String> choice_region = new JComboBox<String>();
		choice_region.setBounds(131, 99, 156, 20);
		contentPane.add(choice_region);
		//choice_region.add("Regi�n");
		for(int i=0;i<registro.numeroRegiones();i++)
			choice_region.addItem(registro.nombreRegion(i));
		
		Label label_3 = new Label("Sucursal ");
		label_3.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		label_3.setBounds(53, 145, 72, 22);
		contentPane.add(label_3);
		
		JComboBox<String> choice_sucursal = new JComboBox<String>();
		choice_sucursal.setBounds(131, 145, 118, 20);
		contentPane.add(choice_sucursal);
		
		Label label_1 = new Label("A\u00F1o");
		label_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		label_1.setBounds(53, 192, 72, 22);
		contentPane.add(label_1);
		
		textField_anio = new JTextField();
		textField_anio.setBounds(131, 192, 118, 20);
		contentPane.add(textField_anio);
		textField_anio.setColumns(10);
		textField_anio.setText("");
		choice_sucursal.addItem("Sucursal");
		JButton Seleccionar = new JButton("Seleccionar");
		Seleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choice_sucursal.removeAllItems();;
				choice_sucursal.addItem("Sucursal");
				String regionSeleccionada = (String)choice_region.getSelectedItem();
				String[] vector = new String [registro.numeroDeSucursales(regionSeleccionada)];
				vector = registro.nombreSucursales(regionSeleccionada);
				for(int i=0;i<registro.numeroDeSucursales(regionSeleccionada);i++){
					choice_sucursal.addItem(vector[i]);
				}
				   
			}
		});
		Seleccionar.setBounds(316, 97, 96, 23);
		contentPane.add(Seleccionar);
		
		Label texto = new Label("**campos obligatorios");
		texto.setForeground(Color.RED);
		texto.setFont(new Font("Gadugi", Font.PLAIN, 11));
		texto.setBounds(282, 20, 115, 22);
		contentPane.add(texto);
		
		Label label_4 = new Label("**");
		label_4.setForeground(Color.RED);
		label_4.setBounds(33, 53, 22, 22);
		contentPane.add(label_4);
		
		Label label_5 = new Label("**");
		label_5.setForeground(Color.RED);
		label_5.setBounds(33, 97, 22, 22);
		contentPane.add(label_5);
		
		Label label_6 = new Label("**");
		label_6.setForeground(Color.RED);
		label_6.setBounds(33, 145, 22, 22);
		contentPane.add(label_6);
		
		JButton GenerarRegistro = new JButton("Ver Registro");
		GenerarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(((String) choice_registro.getSelectedItem()).compareTo("Registro")==0 || ((String) choice_sucursal.getSelectedItem()).compareTo("Sucursal")==0){
			       registro.error("Debe seleccionar los campos obligatorios");
			    }
				else{
					if(textField_anio.getText().compareTo("")==0){
						VentanaReporte reporteFinal = VentanaReporte.getInstance(registro.reporte((String)choice_registro.getSelectedItem(),(String)choice_region.getSelectedItem(),(String)choice_sucursal.getSelectedItem()),registro,usuario);
						reporteFinal.mostrar();
						ocultar();
					}
					else{
						int anio = Integer.parseInt(textField_anio.getText());
						ArrayList<Persona> reporteParc = new ArrayList<Persona>();
						reporteParc = registro.reporte((String)choice_registro.getSelectedItem(),(String)choice_region.getSelectedItem(),(String)choice_sucursal.getSelectedItem());
						VentanaReporte reporteFinal = VentanaReporte.getInstance(registro.reporte(reporteParc,anio,(String)choice_registro.getSelectedItem()),registro,usuario); 
						reporteFinal.mostrar();
						ocultar();
					}
				}
				
			
			}
		});
		GenerarRegistro.setBounds(282, 229, 130, 22);
		contentPane.add(GenerarRegistro);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver(usuario);
			}
		});
		btnVolver.setBounds(10, 228, 89, 23);
		contentPane.add(btnVolver);
	}
	
	private void volver(Persona usuario) {
		if(usuario.getTipo().compareTo("Funcionario")==0){
			VentanaFuncionario ventana = VentanaFuncionario.getInstance();  
			ventana.mostrar();
			destruir();
		} else {
			VentanaAdministrador ventana = VentanaAdministrador.getInstance();
			ventana.mostrar();
			destruir();
		}
	}
	
	public static VentanaFiltroReporte getInstance(RegistroCivil registro, Persona usuario){
		if(estaVentana==null)
			estaVentana = new VentanaFiltroReporte(registro, usuario);
		return estaVentana;
	}
	public static VentanaFiltroReporte getInstance(){
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
