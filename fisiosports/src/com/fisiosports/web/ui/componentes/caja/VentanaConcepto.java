package com.fisiosports.web.ui.componentes.caja;

import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Concepto;
import com.fisiosports.modelo.entidades.caja.Movimiento.TipoMovimiento;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.contenedores.ContenedorConcepto;
import com.fisiosports.web.ui.contenedores.beantypes.BeanItemConcepto;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaConcepto extends Window{

	private static final long serialVersionUID = 1L;
	private TextField textConcepto;
	private CheckBox ingreso;
	private CheckBox egreso;
	private Button newButton;
	private Table table;
	private Observer observer;
	private FisiosportsUI ui;
	private VerticalLayout layout = new VerticalLayout();
	private ContenedorConcepto container = new ContenedorConcepto(BeanItemConcepto.class);
	
	public VentanaConcepto(Observer observer){
		
		this.setCaption("Conceptos");
		this.setModal(true);
		
		this.observer = observer;
		this.ui = (FisiosportsUI) UI.getCurrent();
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSpacing(true);
		ingreso = new CheckBox("Ingreso");
		egreso = new CheckBox("Egreso");
		hl.addComponent(ingreso);
		hl.addComponent(egreso);
		
		textConcepto = new TextField();
		textConcepto.setInputPrompt("nuevo concepto para ingresos/egresos de caja");
		textConcepto.setWidth(25, Unit.EM);

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
		
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(hl);
		layout.addComponent(textConcepto);
		layout.addComponent(newButton);
		layout.addComponent(cargarTabla());
		
		this.setContent(layout);

	}
	
	private Table cargarTabla(){
		if (table != null){
			layout.removeComponent(table);
		}
		table = new Table();
		table.setWidth(25, Unit.EM);
		table.setContainerDataSource(container);
		table.setVisibleColumns(ContenedorConcepto.getVisibleColumns());
		table.setColumnHeaders(ContenedorConcepto.getColumnHeaders());
//		table.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		
		table.getContainerDataSource().removeAllItems();
		for (Concepto concepto:this.ui.getiCaja().consultarConceptos(null)){
			table.getContainerDataSource().addItem(new BeanItemConcepto(concepto, this));
		}
		if (table.getContainerDataSource().size()>10){
			table.setPageLength(10);
		}else{
			table.setPageLength(table.size());
		}
		
		if (table.getContainerDataSource().size() == 0){
			table.setWidth(100, Unit.PERCENTAGE);
		}else{
			table.markAsDirty();
			table.refreshRowCache();
			table.setSizeUndefined();
		}
		layout.addComponent(table);
		this.markAsDirtyRecursive();
		
		return table;
	}
	
	private void agregar() {
		
		if (this.textConcepto.getValue() == null || this.textConcepto.getValue().trim().isEmpty()){
			Notification.show("Debe indicar nombre de categoría", Type.ERROR_MESSAGE);
			return;
		}
		
		if (!ingreso.getValue() && !egreso.getValue()){
			Notification.show("Debe indicar si es concepto de ingreso, egreso, o ambos", Type.ERROR_MESSAGE);
			return;
		}
		
		Concepto concepto = new Concepto();
		concepto.setNombre(this.textConcepto.getValue());
		if (ingreso.getValue()){
			concepto.getTiposMovimiento().add(TipoMovimiento.INGRESO);
		}
		if (egreso.getValue()){
			concepto.getTiposMovimiento().add(TipoMovimiento.EGRESO);
		}
		
		ui.getiCaja().crearConcepto(concepto);
		this.cargarTabla();
		textConcepto.setValue("");
		ingreso.setValue(false);
		egreso.setValue(false);
		if (observer != null){
			observer.update(null, null);
		}
	}
	
	public void borrarConcepto(Concepto concepto){
		try{
			this.ui.getiCaja().borrarConcepto(concepto);
		}catch(Exception e){
			Notification.show("No se pudo eliminar la categoría. "
					+ "Pueden haber movimientos asociados a la misma", 
					Type.ERROR_MESSAGE);
		}
		this.cargarTabla();
	}
	
}
