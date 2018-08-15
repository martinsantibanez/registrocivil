package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Administrador;
import logica.Funcionario;
import logica.Persona;
import logica.RegistroCivil;
import javax.swing.SwingConstants;

public class VentanaReporte extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Persona> rep = null;
	private TextArea pantalla;
	private JButton btnPantalla;
	private JButton btnVolver;
	public static VentanaReporte estaVentana;
	
	private VentanaReporte(ArrayList<Persona> reporte, RegistroCivil registro, Persona usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(reporte!=null){
			rep = reporte;
		}
		
		setTitle("Reporte");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel repPer = new JLabel("Reporte Personas");
		repPer.setFont(new Font("MS PGothic", Font.ITALIC, 17));
		repPer.setHorizontalAlignment(SwingConstants.CENTER);
		repPer.setBounds(10, 11, 414, 42);
		contentPane.add(repPer);
		
		pantalla = new TextArea();
		pantalla.setForeground(Color.BLACK);
		pantalla.setFont(new Font("MS PGothic", Font.ITALIC, 13));
		pantalla.setBackground(SystemColor.menu);
		pantalla.setBounds(20, 59, 380, 145);
		contentPane.add(pantalla);
		pantalla.setEditable(false);
		
		btnPantalla = new JButton("Mostrar");
		btnPantalla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				pantalla.setEditable(true);
				obtenerDatos(pantalla);
				pantalla.setEditable(false);
			}
		});
		btnPantalla.setBounds(165, 225, 112, 23);
		contentPane.add(btnPantalla);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
		});
		btnVolver.setBounds(10, 237, 79, 23);
		contentPane.add(btnVolver);		
		JButton btnNewButton = new JButton("Guardar en PDF");//guarda el documento con reporte_ Nombre del usuario
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(usuario.getTipo().equals("Funcionario"))
						((Funcionario)usuario).escribirReporte(reporte, usuario);
					else{
					((Administrador)usuario).escribirReporte(reporte, usuario);
					}
					JOptionPane.showMessageDialog(null, "Guardado en PDF correctamente");
				}catch (Exception e1){
		            System.out.println(e1);
		        }
				
			}
		});
		btnNewButton.setBounds(302, 235, 122, 26);
		contentPane.add(btnNewButton);
	}
	
	public void obtenerDatos(TextArea pant){
		if(rep!=null){
			for (int i = 0; i<rep.size();i++){
				pant.append("--------------------------------");
				pant.append("\nNombre: "+rep.get(i).getNombre());
				pant.append("\nFecha Nacimiento: "+rep.get(i).getFechaSlash());
				pant.append("\nRut: "+rep.get(i).getRut());
				pant.append("\nEstado Civil: "+rep.get(i).getEstadoCivil());
				pant.append("\nSexo: "+rep.get(i).getSexo());
				pant.append("\n");
			}
		}
	}
	
	public void volver(){
		VentanaFiltroReporte venFilRep = VentanaFiltroReporte.getInstance();
		venFilRep.mostrar();
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

	public static VentanaReporte getInstance(){
		return estaVentana;
	}
	
	public static VentanaReporte getInstance(ArrayList<Persona> reporte, RegistroCivil registro, Persona usuario) {
		if(estaVentana==null)
			estaVentana = new VentanaReporte(reporte, registro, usuario);
		return estaVentana;
	}
}
