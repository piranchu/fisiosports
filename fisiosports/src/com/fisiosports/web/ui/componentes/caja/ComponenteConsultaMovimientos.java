package com.fisiosports.web.ui.componentes.caja;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Concepto;
import com.fisiosports.modelo.entidades.caja.Egreso;
import com.fisiosports.modelo.entidades.caja.Ingreso;
import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.modelo.entidades.caja.Movimiento.TipoMovimiento;
import com.fisiosports.modelo.entidades.caja.ProductoServicio;
import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.pacientes.ComponenteSeleccionPaciente;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteConsultaMovimientos extends CustomComponent implements Observer{
	
	private static final long serialVersionUID = 1L;

	private ComponenteCaja componenteCaja;
	private BeanItemContainer<TipoMovimiento> contenedorTipoMovimiento = new BeanItemContainer<TipoMovimiento>(TipoMovimiento.class);
	private ComboBox comboTipoMovimiento;
	private ComboBox comboConcepto;
	private BeanItemContainer<Concepto> contenedorConcepto = new BeanItemContainer<Concepto>(Concepto.class);
	private ComboBox comboProductoServicio;
	private BeanItemContainer<ProductoServicio> contenedorProductoServicio = new BeanItemContainer<ProductoServicio>(ProductoServicio.class);
	private ComponenteSeleccionPaciente seleccionPaciente;
	private PopupDateField fechaInicial;
	private PopupDateField fechaFinal;
	
	private Table tabla = new Table();

	private FisiosportsUI ui;
	
	private VerticalLayout mainLayout = new VerticalLayout();
	
	
	public ComponenteConsultaMovimientos(ComponenteCaja componenteCaja){
		ui = (FisiosportsUI) UI.getCurrent();
		
		this.componenteCaja = componenteCaja;
		
		FisiosportsUI ui = (FisiosportsUI) UI.getCurrent();
		
		mainLayout.setSpacing(true);
		
		mainLayout.addComponent(this.obtenerBotonVolver());
		mainLayout.addComponent(this.obtenerBuscadores());
		
		this.setCompositionRoot(mainLayout);
	}
	
	public Button obtenerBotonVolver(){
		Button button = new Button();
		button.setDescription("volver");
		button.setIcon(FontAwesome.HAND_O_LEFT);
		button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		button.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				ComponenteConsultaMovimientos.this.componenteCaja.setComponentMovimientosActuales();
			}
		});
		return button;
	}
	
	public Button obtenerBotonBuscar(){
		Button button = new Button();
		button.setDescription("buscar movimientos");
		button.setIcon(FontAwesome.SEARCH);
		button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		button.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				cargarMovimientos();
			}
		});
		return button;
	}

	private void cargarMovimientos() {
		mainLayout.removeComponent(tabla);
		Class<? extends Movimiento> tipoMovimiento;
		if (comboTipoMovimiento.getValue()==null){
			tipoMovimiento = null;
		}else if (comboTipoMovimiento.getValue().equals(TipoMovimiento.INGRESO)){
			tipoMovimiento = Ingreso.class;
		}else{
			tipoMovimiento = Egreso.class;
		}
		
		List<? extends Movimiento> movimientos = ui.getiCaja().obtenerMovimientos(
				tipoMovimiento, 
				(Concepto)comboConcepto.getValue(), 
				(ProductoServicio)comboProductoServicio.getValue(), 
				seleccionPaciente.getPaciente(), 
				fechaInicial.getValue(), 
				fechaFinal.getValue());
		tabla = new TablaMovimientos(movimientos);
		mainLayout.addComponent(tabla);

	}

	public VerticalLayout obtenerBuscadores(){
		VerticalLayout vLayout = new VerticalLayout();
		
		HorizontalLayout layout = new HorizontalLayout();
		
		comboTipoMovimiento = new ComboBox();
		comboTipoMovimiento.setInputPrompt("tipo movimiento");
		comboTipoMovimiento.setContainerDataSource(contenedorTipoMovimiento);
		contenedorTipoMovimiento.addAll(Arrays.asList(TipoMovimiento.values()));
		comboTipoMovimiento.setItemIcon(TipoMovimiento.INGRESO, FontAwesome.ARROW_CIRCLE_DOWN);
		comboTipoMovimiento.addStyleName("redicon");
		comboTipoMovimiento.setItemIcon(TipoMovimiento.EGRESO, FontAwesome.ARROW_CIRCLE_UP);
		
		comboConcepto = new ComboBox();
		comboConcepto.setInputPrompt("concepto");
		comboConcepto.setContainerDataSource(contenedorConcepto);
		comboConcepto.setItemCaptionPropertyId("nombre");
		contenedorConcepto.addAll(ui.getiCaja().consultarConceptos());
		
		comboProductoServicio = new ComboBox();
		comboProductoServicio.setInputPrompt("producto/servicio");
		comboProductoServicio.setContainerDataSource(contenedorProductoServicio);
		comboProductoServicio.setItemCaptionPropertyId("nombre");
		contenedorProductoServicio.addAll(ui.getiCaja().consultarProductosServicios());

		seleccionPaciente = new ComponenteSeleccionPaciente(this);
		fechaInicial = new PopupDateField();
		fechaInicial.setInputPrompt("fecha inicial");
		fechaFinal = new PopupDateField();
		fechaFinal.setInputPrompt("fecha final");
		
		layout.addComponent(comboTipoMovimiento);
		layout.addComponent(comboConcepto);
		layout.addComponent(comboProductoServicio);
		layout.addComponent(seleccionPaciente);
		layout.addComponent(fechaInicial);
		layout.addComponent(fechaFinal);

		vLayout.addComponent(layout);
		vLayout.addComponent(this.obtenerBotonBuscar());
		
		return vLayout;		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Paciente){
			
		}
		
	}
	
	
	

}
