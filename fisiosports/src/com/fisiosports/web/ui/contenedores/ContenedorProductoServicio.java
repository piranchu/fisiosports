package com.fisiosports.web.ui.contenedores;

import com.fisiosports.web.ui.componentes.caja.BeanItemProductoServicio;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorProductoServicio extends BeanItemContainer<BeanItemProductoServicio>{

	public ContenedorProductoServicio(Class<BeanItemProductoServicio> type)
			throws IllegalArgumentException {
		super(type);
		addNestedContainerBean("productoServicio");
	}
	
	public static Object[] getVisibleColumns(){
		return new Object[]{
				"productoServicio.nombre", "productoServicio.precio", "botonEliminar"
		};
	}
	
	public static String[] getColumnHeaders(){
		return new String[]{
				"nombre", "precio sugerido", " "
		};
	}

	private static final long serialVersionUID = 1L;

}
