package com.fisiosports.web.ui.contenedores;

import com.fisiosports.web.ui.contenedores.beantypes.BeanItemMovimiento;
import com.vaadin.data.util.BeanItemContainer;

public class ContenedorMovimientosCaja extends BeanItemContainer<BeanItemMovimiento>{

	private static final long serialVersionUID = 1L;

	public ContenedorMovimientosCaja(Class<? super BeanItemMovimiento> type/*,
			Collection<? extends BeanItemMovimiento> collection*/)
			throws IllegalArgumentException {
//		super(type, collection);
		super(type);
		this.addNestedContainerBean("movimiento");
		this.addNestedContainerBean("movimiento.concepto");
		this.addNestedContainerBean("movimiento.productoServicio");
		this.addNestedContainerBean("movimiento.paciente");
	}

	public static Object[] getColumnasVisibles(){
		return new Object[]{
				"icon",
				"movimiento.concepto.nombre", 
				"movimiento.productoServicio.nombre", 
				"movimiento.importe", 
				"movimiento.fecha", 
				"movimiento.observaciones", 
				"movimiento.paciente.documento", 
				"movimiento.paciente.nombre", 
				"movimiento.paciente.apellido", 
				"deleteButton"};
	}

	public static Object[] getColumnasVisiblesConsulta(){
		return new Object[]{
				"icon",
				"movimiento.concepto.nombre", 
				"movimiento.productoServicio.nombre", 
				"movimiento.importe", 
				"movimiento.fecha", 
				"movimiento.observaciones", 
				"movimiento.paciente.documento", 
				"movimiento.paciente.nombre", 
				"movimiento.paciente.apellido"};
	}

	public static String[] getNonbresColumnas(){
		return new String[]{
				"",
				"concepto", 
				"producto/servicio", 
				"importe",
				"fecha", 
				"observaciones", 
				"documento", 
				"nombre", 
				"apellido", 
				""};
	}
	
	public static String[] getNonbresColumnasConsulta(){
		return new String[]{
				"",
				"concepto", 
				"producto/servicio", 
				"importe",
				"fecha", 
				"observaciones", 
				"documento", 
				"nombre", 
				"apellido"};
	}
	
	
}
