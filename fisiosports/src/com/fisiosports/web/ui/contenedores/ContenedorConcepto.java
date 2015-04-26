package com.fisiosports.web.ui.contenedores;

import com.fisiosports.web.ui.contenedores.beantypes.BeanItemConcepto;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorConcepto extends BeanItemContainer<BeanItemConcepto>{

	private static final long serialVersionUID = 1L;

	public ContenedorConcepto(Class<BeanItemConcepto> type) throws IllegalArgumentException {
		super(type);
		addNestedContainerBean("concepto");
	}
	
	public static Object[] getVisibleColumns(){
		return new Object[]{
				"concepto.nombre", "botonEliminar"
		};
	}
	
	public static String[] getColumnHeaders(){
		return new String[]{
				"nombre", " "
		};
	}


}
