package com.fisiosports.web.ui.contenedores;

import java.util.Collection;

import com.fisiosports.web.ui.contenedores.beantypes.SesionDT;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorSesionRehabilitacion extends BeanItemContainer<SesionDT> {
	
	private static final long serialVersionUID = 1L;

	public ContenedorSesionRehabilitacion(Class<? super SesionDT> type,
			Collection<? extends SesionDT> collection)
			throws IllegalArgumentException {
		super(type, collection);
		// TODO Auto-generated constructor stub
	}

}
