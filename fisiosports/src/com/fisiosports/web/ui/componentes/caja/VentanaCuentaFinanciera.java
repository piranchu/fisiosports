package com.fisiosports.web.ui.componentes.caja;

import java.util.Arrays;
import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.CuentaFinanciera;
import com.fisiosports.modelo.entidades.caja.Moneda;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaCuentaFinanciera extends Window{

	private static final long serialVersionUID = 1L;
	private TextField textNombre;
	private TextArea textDescripcion;
	private ComboBox comboMoneda;
	private Button newButton;
	private Table tabla;
	private Observer observer;
	private FisiosportsUI ui;
	private VerticalLayout layout = new VerticalLayout();
	private BeanItemContainer<Moneda> containerMoneda = new BeanItemContainer<>(Moneda.class);
	private BeanItemContainer<BeanItemCuentaFinanciera> container = new BeanItemContainer<>(BeanItemCuentaFinanciera.class);
	
	public VentanaCuentaFinanciera(Observer observer){
		
		this.setCaption("Mantenimiento de cuentas financieras");
		this.setModal(true);
		
		this.observer = observer;
		this.ui = (FisiosportsUI) UI.getCurrent();
		
		textNombre = new TextField();
		textNombre.setInputPrompt("nombre");
		textNombre.setWidth(25, Unit.EM);

		textDescripcion = new TextArea();
		textDescripcion.setInputPrompt("descripción");
		textDescripcion.setWidth(25, Unit.EM);
		
		comboMoneda = new ComboBox();
		comboMoneda.setInputPrompt("moneda");
		comboMoneda.setNullSelectionAllowed(false);
		comboMoneda.setContainerDataSource(containerMoneda);
		comboMoneda.setItemCaptionPropertyId("descripcion");
		containerMoneda.addAll(Arrays.asList(Moneda.values()));
		comboMoneda.setValue(Moneda.UYU);
		
		newButton = new Button("", FontAwesome.PLUS_CIRCLE);
		newButton.setDescription("agregar");
		newButton.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		newButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				agregar();
			}
		});
		
		container.addNestedContainerBean("cuentaFinanciera");

		layout.setMargin(true);
		layout.setSpacing(true);
		
		layout.addComponent(textNombre);
		layout.addComponent(textDescripcion);
		layout.addComponent(comboMoneda);
		layout.addComponent(newButton);
		layout.addComponent(cargarTabla());
		
		this.setContent(layout);

	}
	
	private Table cargarTabla(){
		if (tabla != null){
			layout.removeComponent(tabla);
		}
		tabla = new Table();
		tabla.setContainerDataSource(container);
		tabla.setVisibleColumns(new Object[]{
				"cuentaFinanciera.nombre", "cuentaFinanciera.descripcion", 
				"cuentaFinanciera.moneda", "cuentaFinanciera.saldo", "botonEliminar"
		});
		tabla.setColumnHeaders(new String[]{
				"nombre", "descripcion", "moneda", "saldo", ""
		});
//		tabla.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		
		tabla.getContainerDataSource().removeAllItems();
		for (CuentaFinanciera cuenta:this.ui.getiMovimientos().consultarCuentasFinancieras()){
			tabla.getContainerDataSource().addItem(new BeanItemCuentaFinanciera(cuenta, this));
		}
		if (tabla.getContainerDataSource().size()>10){
			tabla.setPageLength(10);
		}else{
			tabla.setPageLength(tabla.size());
		}
		
		if (tabla.getContainerDataSource().size() == 0){
			tabla.setWidth(100, Unit.PERCENTAGE);
		}else{
			tabla.markAsDirty();
			tabla.refreshRowCache();
			tabla.setSizeUndefined();
		}
		layout.addComponent(tabla);
		this.markAsDirtyRecursive();
		
		return tabla;
	}
	
	private void agregar() {
		
		if (this.textNombre.getValue() == null || this.textNombre.getValue().trim().isEmpty()){
			Notification.show("Debe indicar nombre de la cuenta", Type.ERROR_MESSAGE);
			return;
		}
		CuentaFinanciera cuenta = new CuentaFinanciera();
		cuenta.setNombre(this.textNombre.getValue());
		cuenta.setDescripcion(textDescripcion.getValue());
		cuenta.setMoneda((Moneda) this.comboMoneda.getValue());
		ui.getiMovimientos().crearCuentaFinanciera(cuenta);
		this.cargarTabla();
		textNombre.setValue("");
		textDescripcion.setValue("");
		if (observer != null){
			observer.update(null, null);
		}
	}
	
	public void borrar(CuentaFinanciera cuenta){
		try{
			this.ui.getiMovimientos().borrarCuentaFinanciera(cuenta);
		}catch(Exception e){
			Notification.show("No se pudo eliminar la cuenta. "
					+ "Pueden haber movimientos asociados a la misma", 
					Type.ERROR_MESSAGE);
		}
		this.cargarTabla();
	}
	
}
