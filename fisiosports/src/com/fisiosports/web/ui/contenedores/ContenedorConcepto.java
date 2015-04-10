package com.fisiosports.web.ui.contenedores;

import com.fisiosports.web.ui.componentes.caja.BeanItemConcepto;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorConcepto extends BeanItemContainer<BeanItemConcepto>{

	private static final long serialVersionUID = 1L;

	public ContenedorConcepto(Class<BeanItemConcepto> type) throws IllegalArgumentException {
		super(type);
		addNestedContainerBean("concepto");
	}

}
