package com.fisiosports.web.ui.contenedores;

import java.util.Collection;

import com.fisiosports.modelo.entidades.Consulta;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorConsulta extends BeanItemContainer<Consulta> {

	public static Object[] getColumnasVisibles(){
		return new Object[]{"descripcion", "observacion", "fecha"};
	}

	public static String[] getNonbresColumnas(){
		return new String[]{"consulta/sesión", "observaciones", "fecha"};
	}

	public ContenedorConsulta(Class<? super Consulta> type,
			Collection<? extends Consulta> collection)
			throws IllegalArgumentException {
		super(type, collection);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;


}
