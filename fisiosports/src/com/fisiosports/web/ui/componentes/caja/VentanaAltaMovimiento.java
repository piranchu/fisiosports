package com.fisiosports.web.ui.componentes.caja;

import java.util.Date;
import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Concepto;
import com.fisiosports.modelo.entidades.caja.Egreso;
import com.fisiosports.modelo.entidades.caja.Ingreso;
import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.modelo.entidades.caja.ProductoServicio;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.pacientes.ComponenteSeleccionPaciente;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaAltaMovimiento extends Window{

	private static final long serialVersionUID = 1L;
	
//	private ComboBox tipoMovimiento;
//	private OptionGroup optionTipoMov;
	
//	private BeanItemContainer<Movimiento.TipoMovimiento> contenedorTipoMov = new BeanItemContainer<TipoMovimiento>(Movimiento.TipoMovimiento.class);
	private ComboBox comboProductoServicio;
	private BeanItemContainer<ProductoServicio> contenedorProductoServicio = new BeanItemContainer<ProductoServicio>(ProductoServicio.class);
	private ComboBox comboConcepto;
	private BeanItemContainer<Concepto> contenedorConcepto = new BeanItemContainer<Concepto>(Concepto.class);
	private ComponenteSeleccionPaciente seleccionPaciente;
	private TextField importe;
	private PopupDateField fecha;
	private TextArea observaciones;
	
	private FisiosportsUI ui;
	private Observer observer;
	private Movimiento.TipoMovimiento tipoMovimiento;
	
	public VentanaAltaMovimiento(Observer observer, Movimiento.TipoMovimiento tipoMovimiento){
		
		this.observer = observer;
		this.tipoMovimiento = tipoMovimiento;
		this.ui = (FisiosportsUI) UI.getCurrent();
		
		this.setCaption("nuevo "+tipoMovimiento+" de caja");
		this.setModal(true);
		
		FormLayout formLayout = new FormLayout();
		formLayout.setSizeUndefined();
		formLayout.setSpacing(true);
		formLayout.setMargin(true);
		
//		optionTipoMov = new OptionGroup("tipo de movimiento");
//		optionTipoMov.setMultiSelect(false);
//		optionTipoMov.setNullSelectionAllowed(false);
//		optionTipoMov.addItems(
//				Movimiento.TipoMovimiento.INGRESO, 
//				Movimiento.TipoMovimiento.EGRESO);
//		optionTipoMov.setValue(Movimiento.TipoMovimiento.INGRESO);
//		formLayout.addComponent(optionTipoMov);
		
//		tipoMovimiento = new ComboBox("tipo de movimiento");
//		tipoMovimiento.setContainerDataSource(contenedorTipoMov);
////		moneda.setItemCaptionPropertyId("descripcion");
//		contenedorTipoMov.addAll(Arrays.asList(Movimiento.TipoMovimiento.values()));
//		formLayout.addComponent(tipoMovimiento);
		
		comboConcepto = new ComboBox("concepto");
		comboConcepto.setContainerDataSource(contenedorConcepto);
		comboConcepto.setItemCaptionPropertyId("nombre");
		comboConcepto.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				cargarProductosServicios((Concepto)comboConcepto.getValue());
				
			}
		});
		contenedorConcepto.addAll(ui.getiCaja().consultarConceptos(tipoMovimiento));
		formLayout.addComponent(comboConcepto);

		comboProductoServicio = new ComboBox("producto/servicio");
		comboProductoServicio.setContainerDataSource(contenedorProductoServicio);
		comboProductoServicio.setItemCaptionPropertyId("nombre");
		cargarProductosServicios(null);
		
		formLayout.addComponent(comboProductoServicio);
		
		seleccionPaciente = new ComponenteSeleccionPaciente(null);
		seleccionPaciente.setCaption("cliente");
		formLayout.addComponent(seleccionPaciente);
		
		importe = new TextField("importe");
		formLayout.addComponent(importe);
		
//		importe.addValidator(new Validator(){
//
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void validate(Object value) throws InvalidValueException {
//				System.out.println("[VentanaAltaMovimiento.validate] value:"+value);
//				if (value == null){
//					throw new InvalidValueException("debe ingresar importe");
//				}
//				try{
//					Double.parseDouble(value.toString());
//				}catch (Exception e){
//					throw new InvalidValueException("el importe debe ser un número");
//				}
//			}
//			
//		});
		fecha = new PopupDateField("fecha");
		fecha.setValue(new Date());
		formLayout.addComponent(fecha);
		
		observaciones = new TextArea("Observaciones");
		formLayout.addComponent(observaciones);
		
		formLayout.addComponent(this.crearBotonAlta());
		
		this.setContent(formLayout);
	}
	
	private void cargarProductosServicios(Concepto concepto){
		
		contenedorProductoServicio.removeAllItems();
		contenedorProductoServicio.addAll(ui.getiCaja().consultarProductosServicios(concepto));
		comboProductoServicio.setNullSelectionAllowed(false);
		comboProductoServicio.setInvalidAllowed(false);
		comboProductoServicio.addValueChangeListener(new ValueChangeListener(){

			private static final long serialVersionUID = 1L;
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null){
					ProductoServicio ps = (ProductoServicio) event.getProperty().getValue();
					Double precioSugerido = ps.getPrecio();
					if (precioSugerido != null && precioSugerido != 0){
						VentanaAltaMovimiento.this.importe.setValue(ps.getPrecio().toString());
					}
				}
			}
			
		});
	}
	
	private Button crearBotonAlta(){
		Button botonAlta = new Button("", FontAwesome.SAVE);
		botonAlta.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		botonAlta.setDescription("guardar datos");
		botonAlta.addClickListener(new Button.ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				alta();
			}
		});
		return botonAlta;
		
	}
	
	private void alta(){
		Movimiento movimiento = null;
		
		switch (tipoMovimiento){
		case EGRESO: 
			movimiento = new Egreso();
			break;
		case INGRESO: 
			movimiento = new Ingreso();
			break;
		default: movimiento = new Ingreso();
		}
		
//		if (comboConcepto.getValue() == null){
//			Notification.show("debe seleccionar concepto", Type.WARNING_MESSAGE);
//			return;
//		}
		
		if (importe.getValue() == null || importe.getValue().trim().isEmpty()){
			Notification.show("debe ingresar importe", Type.WARNING_MESSAGE);
			return;
		}

		try{
			movimiento.setImporte(Double.parseDouble(this.importe.getValue()));			
		}catch(Exception e){
			Notification.show("importe incorrecto", Type.WARNING_MESSAGE);
			return;
		}
		
		movimiento.setConcepto((Concepto)this.comboConcepto.getValue());
		movimiento.setProductoServicio((ProductoServicio)this.comboProductoServicio.getValue());
		movimiento.setFecha(this.fecha.getValue());
		movimiento.setPaciente(this.seleccionPaciente.getPaciente());
		movimiento.setObservaciones(observaciones.getValue());
		
		ui.getiCaja().crearMovimiento(ui.getiCaja().obtenerCaja(), movimiento);
		if (observer != null){
			observer.update(null, movimiento);
		}
		close();
		
	}	
}
