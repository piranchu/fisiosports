package com.fisiosports.web.ui.componentes.pacientes;

import com.fisiosports.modelo.entidades.Paciente;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorPacientes extends BeanItemContainer<Paciente>{

	//public final String id = "3";
	
	static public Object[] columnasVisibles(){
		return new Object[]{"documento","nombre", "apellido", "telefono", "correoElectronico"};
	}
	
	static public Object[] columnasVisiblesReducidas(){
		return new Object[]{"documento","nombre", "apellido"};
	}

	static public String[] nombresColumnas(){
		return new String[]{"documento","nombre", "apellido", "teléfono", "correo electrónico"};
	}
	
	static public String[] nombresColumnasReducidas(){
		return new String[]{"documento","nombre", "apellido"};
	}

	//	public Object[] columnasVisibles(){
//		return new Object[]{};
//	}
	
	public ContenedorPacientes(Class<? super Paciente> type)
			throws IllegalArgumentException {
		super(type);
	}

	private static final long serialVersionUID = 1L;

}
