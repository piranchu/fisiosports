package com.fisiosports.web.ui.contenedores;

import java.util.Collection;

import com.fisiosports.web.ui.contenedores.beantypes.EvaluacionDT;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorEvaluacion extends BeanItemContainer<EvaluacionDT>{

	private static final long serialVersionUID = 1L;

	public ContenedorEvaluacion(Class<? super EvaluacionDT> type,
			Collection<? extends EvaluacionDT> collection)
			throws IllegalArgumentException {
		super(type, collection);
		this.addNestedContainerBean("evaluacion");
	}
	
	public static String[] cabeceraColumnas(){
		return new String[]{"diagnóstico", "fecha", ""};
	}

	public static Object[] columnasVisibles(){
		return new String[]{"evaluacion.diagnostico", "evaluacion.fecha", "deleteButton"};
	}

}
