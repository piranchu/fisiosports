package com.fisiosports.web.ui.componentes.caja;

import java.util.Arrays;
import java.util.Date;
import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Categoria;
import com.fisiosports.modelo.entidades.caja.CuentaFinanciera;
import com.fisiosports.modelo.entidades.caja.Egreso;
import com.fisiosports.modelo.entidades.caja.Ingreso;
import com.fisiosports.modelo.entidades.caja.Moneda;
import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.pacientes.ComponenteSeleccionPaciente;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaAltaMovimiento extends Window{

	private static final long serialVersionUID = 1L;
	
	private ComboBox tipoMovimiento;
	private OptionGroup optionTipoMov;
	
	private BeanItemContainer contenedorTipoMov = new BeanItemContainer(Movimiento.TipoMovimiento.class);
	private ComboBox cuenta;
	private BeanItemContainer contenedorCuenta = new BeanItemContainer(CuentaFinanciera.class);
	private ComboBox categoria;
	private BeanItemContainer contenedorCategoria = new BeanItemContainer(Categoria.class);
	private ComponenteSeleccionPaciente seleccionPaciente;
	private ComboBox moneda;
	private BeanItemContainer contenedorMoneda = new BeanItemContainer(Moneda.class);
	private TextField importe;
	private PopupDateField fecha;
	private TextArea observaciones;
	
	
	
	private FisiosportsUI ui;
	private Observer observer;
	
	public VentanaAltaMovimiento(Observer observer){

		this.observer = observer;
		this.ui = (FisiosportsUI) UI.getCurrent();
		
		this.setCaption("nuevo movimiento de caja");
		this.setModal(true);
		
		
		FormLayout formLayout = new FormLayout();
		formLayout.setSizeUndefined();
		formLayout.setSpacing(true);
		formLayout.setMargin(true);
		
		optionTipoMov = new OptionGroup("tipo de movimiento");
		optionTipoMov.setMultiSelect(false);
		optionTipoMov.setNullSelectionAllowed(false);
		optionTipoMov.addItems(
				Movimiento.TipoMovimiento.INGRESO, 
				Movimiento.TipoMovimiento.EGRESO);
		optionTipoMov.setValue(Movimiento.TipoMovimiento.INGRESO);
		formLayout.addComponent(optionTipoMov);
		
//		tipoMovimiento = new ComboBox("tipo de movimiento");
//		tipoMovimiento.setContainerDataSource(contenedorTipoMov);
////		moneda.setItemCaptionPropertyId("descripcion");
//		contenedorTipoMov.addAll(Arrays.asList(Movimiento.TipoMovimiento.values()));
//		formLayout.addComponent(tipoMovimiento);
		
		cuenta = new ComboBox("cuenta financiera");
		cuenta.setContainerDataSource(contenedorCuenta);
		cuenta.setItemCaptionPropertyId("nombre");
		contenedorCuenta.addAll(ui.getiMovimientos().consultarCuentasFinancieras());
		cuenta.setNullSelectionAllowed(false);
		cuenta.setInvalidAllowed(false);
		formLayout.addComponent(cuenta);
		
		categoria = new ComboBox("categoría");
		categoria.setContainerDataSource(contenedorCategoria);
		categoria.setItemCaptionPropertyId("nombre");
		contenedorCategoria.addAll(ui.getiMovimientos().consultarCategorias());
		formLayout.addComponent(categoria);
		
		seleccionPaciente = new ComponenteSeleccionPaciente(null);
		seleccionPaciente.setCaption("cliente");
		formLayout.addComponent(seleccionPaciente);
		
		importe = new TextField("importe");
		formLayout.addComponent(importe);
		
		moneda = new ComboBox("moneda");
		moneda.setContainerDataSource(contenedorMoneda);
		moneda.setItemCaptionPropertyId("descripcion");
		contenedorMoneda.addAll(Arrays.asList(Moneda.values()));
		moneda.setValue(Moneda.UYU);
		formLayout.addComponent(moneda);
		
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
		
		switch ((Movimiento.TipoMovimiento)this.optionTipoMov.getValue()){
		case EGRESO: 
			movimiento = new Egreso();
			break;
		case INGRESO: 
			movimiento = new Ingreso();
			break;
		default: movimiento = new Ingreso();
		}
		
		movimiento.setCategoria((Categoria)this.categoria.getValue());
		movimiento.setCuentaFinanciera((CuentaFinanciera)this.cuenta.getValue());
		movimiento.setMoneda((Moneda)this.moneda.getValue());
		movimiento.setImporte(Double.parseDouble(this.importe.getValue()));
		movimiento.setFecha(this.fecha.getValue());
		movimiento.setPaciente(this.seleccionPaciente.getPaciente());
		movimiento.setObservaciones(observaciones.getValue());
		
		ui.getiMovimientos().crearMovimiento(movimiento);
		if (observer != null){
			observer.update(null, movimiento);
		}
		close();
		
	}

	
	
}
