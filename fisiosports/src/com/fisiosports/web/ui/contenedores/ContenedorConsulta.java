package com.fisiosports.web.ui.contenedores;

import java.util.Collection;
import java.util.Map;

import com.fisiosports.modelo.entidades.Consulta;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.VaadinPropertyDescriptor;

public class ContenedorConsulta extends BeanItemContainer<Consulta> {

	public Object[] getColumnasVisibles(){
		return new Object[]{"descripcion"};
	}

	public String[] getNonbresColumnas(){
		return new String[]{"consulta/sesión"};
	}

	public ContenedorConsulta(Class<? super Consulta> type,
			Collection<? extends Consulta> collection)
			throws IllegalArgumentException {
		super(type, collection);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;


}
