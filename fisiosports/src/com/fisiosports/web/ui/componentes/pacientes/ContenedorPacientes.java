package com.fisiosports.web.ui.componentes.pacientes;

import com.fisiosports.modelo.entidades.Paciente;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorPacientes extends BeanItemContainer<Paciente>{

	//public final String id = "3";
	
	public Object[] nombresColumnas(){
		return new Object[]{"documento","nombre", "apellido", "teléfono", "correo electrónico"};
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
