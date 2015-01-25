package com.fisiosports.web.ui.contenedores;

import java.util.Collection;

import com.fisiosports.modelo.entidades.Evaluacion;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorEvaluacion extends BeanItemContainer<Evaluacion>{

	private static final long serialVersionUID = 1L;

	public ContenedorEvaluacion(Class<? super Evaluacion> type,
			Collection<? extends Evaluacion> collection)
			throws IllegalArgumentException {
		super(type, collection);
	}
	
	public static String[] cabeceraColumnas(){
		return new String[]{"diagnóstico", "fecha"};
	}

	public static Object[] columnasVisibles(){
		return new String[]{"diagnostico", "fecha"};
	}

}
