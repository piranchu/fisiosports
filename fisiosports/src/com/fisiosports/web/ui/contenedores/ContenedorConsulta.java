package com.fisiosports.web.ui.contenedores;

import java.util.Collection;

import com.fisiosports.web.ui.contenedores.beantypes.ConsultaDT;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorConsulta extends BeanItemContainer<ConsultaDT> {

	public static Object[] getColumnasVisibles(){
		return new Object[]{"consulta.descripcion", "consulta.observacion", "consulta.fecha", "deleteButton"};
	}

	public static String[] getNonbresColumnas(){
		return new String[]{"consulta/sesión", "observaciones", "fecha", ""};
	}

	public ContenedorConsulta(Class<? super ConsultaDT> type,
			Collection<? extends ConsultaDT> collection)
			throws IllegalArgumentException {
		super(type, collection);
		this.addNestedContainerBean("consulta");
	}

	private static final long serialVersionUID = 1L;


}
