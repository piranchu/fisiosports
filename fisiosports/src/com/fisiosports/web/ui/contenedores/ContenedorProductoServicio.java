package com.fisiosports.web.ui.contenedores;

import com.fisiosports.web.ui.contenedores.beantypes.BeanItemProductoServicio;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorProductoServicio extends BeanItemContainer<BeanItemProductoServicio>{

	public ContenedorProductoServicio(Class<BeanItemProductoServicio> type)
			throws IllegalArgumentException {
		super(type);
		addNestedContainerBean("productoServicio");
		addNestedContainerBean("productoServicio.concepto");
	}
	
	public static Object[] getVisibleColumns(){
		return new Object[]{
				"productoServicio.nombre", "productoServicio.precio", "productoServicio.concepto.nombre", "botonEliminar"
		};
	}
	
	public static String[] getColumnHeaders(){
		return new String[]{
				"nombre", "precio sugerido", "concepto", " "
		};
	}

	private static final long serialVersionUID = 1L;

}
