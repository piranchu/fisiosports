package com.fisiosports.web.ui.componentes.caja;

import java.util.List;
import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Concepto;
import com.fisiosports.modelo.entidades.caja.ProductoServicio;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.contenedores.ContenedorProductoServicio;
import com.fisiosports.web.ui.contenedores.beantypes.BeanItemProductoServicio;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaProductoServicio extends Window{

	private static final long serialVersionUID = 1L;
	private ComboBox comboConceptos;
	private TextField textNombre;
	private TextField textPrecio;
	private Button newButton;
	private Table tabla;
	private Observer observer;
	private FisiosportsUI ui;
	private VerticalLayout layout = new VerticalLayout();
	private ContenedorProductoServicio container = new ContenedorProductoServicio(BeanItemProductoServicio.class);
	
	public VentanaProductoServicio(Observer observer){
		
		this.setCaption("Productos y servicios");
		this.setModal(true);
		
		this.observer = observer;
		this.ui = (FisiosportsUI) UI.getCurrent();
		
		comboConceptos = new ComboBox();
		comboConceptos.setInputPrompt("concepto");
		cargarConceptos();
		
		textNombre = new TextField();
		textNombre.setInputPrompt("nombre");
		textNombre.setWidth(25, Unit.EM);

		textPrecio = new TextField();
		textPrecio.setInputPrompt("precio sugerido");
				
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
		
		layout.addComponent(comboConceptos);
		layout.addComponent(textNombre);
		layout.addComponent(textPrecio);
		layout.addComponent(newButton);
		layout.addComponent(cargarTabla());
		
		this.setContent(layout);

	}
	
	private void cargarConceptos(){
		List<Concepto> conceptos = ui.getiCaja().consultarConceptos(null);
		BeanItemContainer<Concepto> container = new BeanItemContainer<Concepto>(Concepto.class);
		container.addAll(conceptos);
		comboConceptos.setContainerDataSource(container);
		comboConceptos.setItemCaptionPropertyId("nombre");
		
	}
	
	private Table cargarTabla(){
		if (tabla != null){
			layout.removeComponent(tabla);
		}
		tabla = new Table();
		tabla.setContainerDataSource(container);
		
		tabla.setVisibleColumns(ContenedorProductoServicio.getVisibleColumns());
		tabla.setColumnHeaders(ContenedorProductoServicio.getColumnHeaders());
//		tabla.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		
		tabla.getContainerDataSource().removeAllItems();
		for (ProductoServicio ps:this.ui.getiCaja().consultarProductosServicios(null)){
			tabla.getContainerDataSource().addItem(new BeanItemProductoServicio(ps, this));
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
		ProductoServicio ps = new ProductoServicio();
		ps.setNombre(this.textNombre.getValue());
		Double precio = 0.0;
		try{
			if (textPrecio.getValue() != null && !textPrecio.getValue().trim().isEmpty()){
				precio = Double.parseDouble(textPrecio.getValue());
			}
		}catch(Exception e){
			Notification.show("El precio sugerido es incorrecto", Type.ERROR_MESSAGE);
			return;
		}
		ps.setPrecio(precio);
		if (comboConceptos.getValue() != null){
			ps.setConcepto((Concepto)comboConceptos.getValue());
		}
		ui.getiCaja().crearProductoServicio(ps);
		this.cargarTabla();
		textNombre.setValue("");
		textPrecio.setValue("");
		comboConceptos.select(null);
		if (observer != null){
			observer.update(null, null);
		}
	}
	
	public void borrar(ProductoServicio ps){
		try{
			this.ui.getiCaja().borrarProductoServicio(ps);
		}catch(Exception e){
			Notification.show("No se pudo eliminar el producto/servicio. "
					+ "Pueden haber movimientos asociados a los mismos", 
					Type.ERROR_MESSAGE);
		}
		this.cargarTabla();
	}
	
}
