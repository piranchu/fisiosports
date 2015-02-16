package com.fisiosports.web.ui.componentes.caja;

import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Categoria;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaCategoria extends Window{

	private static final long serialVersionUID = 1L;
	private TextField textCategoria;
	private Button newButton;
	private Table tablaCategoria;
	private Observer observer;
	private FisiosportsUI ui;
	private VerticalLayout layout = new VerticalLayout();
	private BeanItemContainer<BeanItemCategoria> container = new BeanItemContainer<>(BeanItemCategoria.class);
	
	public VentanaCategoria(Observer observer){
		
		this.setCaption("Mantenimiento de categorías");
		this.setModal(true);
		
		this.observer = observer;
		this.ui = (FisiosportsUI) UI.getCurrent();
		
		textCategoria = new TextField();
		textCategoria.setInputPrompt("nueva categoría");
		textCategoria.setWidth(25, Unit.EM);

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
		
		container.addNestedContainerBean("categoria");

		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(textCategoria);
		layout.addComponent(newButton);
		layout.addComponent(cargarTabla());
		
		this.setContent(layout);

	}
	
	private Table cargarTabla(){
		if (tablaCategoria != null){
			layout.removeComponent(tablaCategoria);
		}
		tablaCategoria = new Table();
		tablaCategoria.setContainerDataSource(container);
		tablaCategoria.setVisibleColumns(new Object[]{
				"categoria.nombre", "botonEliminar"
		});
		tablaCategoria.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		
		tablaCategoria.getContainerDataSource().removeAllItems();
		for (Categoria categoria:this.ui.getiMovimientos().consultarCategorias()){
			tablaCategoria.getContainerDataSource().addItem(new BeanItemCategoria(categoria, this));
		}
		if (tablaCategoria.getContainerDataSource().size()>10){
			tablaCategoria.setPageLength(10);
		}else{
			tablaCategoria.setPageLength(tablaCategoria.size());
		}
		
		if (tablaCategoria.getContainerDataSource().size() == 0){
			tablaCategoria.setWidth(100, Unit.PERCENTAGE);
		}else{
			tablaCategoria.markAsDirty();
			tablaCategoria.refreshRowCache();
			tablaCategoria.setSizeUndefined();
		}
		layout.addComponent(tablaCategoria);
		this.markAsDirtyRecursive();
		
		return tablaCategoria;
	}
	
	private void agregar() {
		
		if (this.textCategoria.getValue() == null || this.textCategoria.getValue().trim().isEmpty()){
			Notification.show("Debe indicar nombre de categoría", Type.ERROR_MESSAGE);
			return;
		}
		Categoria categoria = new Categoria();
		categoria.setNombre(this.textCategoria.getValue());
		ui.getiMovimientos().crearCategoria(categoria);
		this.cargarTabla();
		textCategoria.setValue("");
		if (observer != null){
			observer.update(null, null);
		}
	}
	
	public void borrarCategoria(Categoria categoria){
		try{
			this.ui.getiMovimientos().borrarCategoria(categoria);
		}catch(Exception e){
			Notification.show("No se pudo eliminar la categoría. "
					+ "Pueden haber movimientos asociados a la misma", 
					Type.ERROR_MESSAGE);
		}
		this.cargarTabla();
	}
	
}
