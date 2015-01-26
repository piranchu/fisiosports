package com.fisiosports.web.ui.contenedores;

import com.fisiosports.web.ui.contenedores.beantypes.PacienteDT;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorPacientes extends BeanItemContainer<PacienteDT>{

	static public Object[] columnasVisibles(){
		return new Object[]{"paciente.documento","paciente.nombre", "paciente.apellido", 
				"paciente.telefono", "paciente.correoElectronico", "evalButton", "modifyButton", "deleteButton"};
//		return new Object[]{"modifyButton", "evalButton"};
	}
	
	static public Object[] columnasVisiblesReducidas(){
		return new Object[]{"paciente.documento","paciente.nombre", "paciente.apellido"};
	}

	static public String[] nombresColumnas(){
		return new String[]{"documento","nombre", "apellido", "teléfono", "correo electrónico", 
				"", "", ""};
//		return new String[]{"", ""};
	}
	
	static public String[] nombresColumnasReducidas(){
		return new String[]{"documento","nombre", "apellido"};
	}

	public ContenedorPacientes(Class<? super PacienteDT> type)
			throws IllegalArgumentException {
		super(type);
		addNestedContainerBean("paciente");
//		addNestedContainerProperty("paciente.documento");
//		addNestedContainerProperty("paciente.nombre");
//		addNestedContainerProperty("paciente.apellido");
//		addNestedContainerProperty("paciente.correoElectronico");
//		addNestedContainerProperty("paciente.telefono");
		
	}

	private static final long serialVersionUID = 1L;

}