package com.fisiosports.web.ui.contenedores;

import com.fisiosports.web.ui.contenedores.beantypes.BeanItemCierreCaja;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorCierreCaja extends BeanItemContainer<BeanItemCierreCaja>{

	private static final long serialVersionUID = 1L;

	public ContenedorCierreCaja(Class<BeanItemCierreCaja> type) throws IllegalArgumentException {
		super(type);
		addNestedContainerBean("cierreCaja");
	}
	
	public static Object[] getVisibleColumns(){
		return new Object[]{
			"cierreCaja.fecha", "cierreCaja.saldoInicial", "cierreCaja.saldoFinal", "botonMovimientos"
		};
	}
	
	public static String[] getColumnHeaders(){
		return new String[]{
				"fecha cierre", "saldo inicial", "saldo final", " "
		};
	}


}
