package com.fisiosports.web.ui.componentes.caja;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.contenedores.ContenedorMovimientosCaja;
import com.fisiosports.web.ui.contenedores.beantypes.MovimientoDT;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteCaja extends VerticalLayout implements Observer{
	
	private static final long serialVersionUID = 1L;

	private MenuBar menu;
	
	private final Command addMovimiento = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
			Notification.show("Agregar movimiento", Type.TRAY_NOTIFICATION);
		}
		
	};
	
	private final Command commandCuentas = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
			Window window = new VentanaCuentaFinanciera(ComponenteCaja.this);
			getUI().addWindow(window);
		}
		
	};

	private final Command commandCategorias = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
			Window window = new VentanaCategoria(ComponenteCaja.this);
			getUI().addWindow(window);
		}
		
	};
	
	private Table table = new Table();
	private ContenedorMovimientosCaja contenedor;

	public ComponenteCaja(){
	
		this.setMargin(true);
		this.setSpacing(true);
		
		menu = new MenuBar();
		menu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		menu.addItem("", FontAwesome.PLUS_CIRCLE, addMovimiento).setDescription("Nuevo movimiento");;
		
		MenuItem menuConfiguracion = menu.addItem("", FontAwesome.GEAR, null);
		menuConfiguracion.setDescription("configuración");
		menuConfiguracion.addItem("cuentas", this.commandCuentas);
		menuConfiguracion.addItem("categorias", this.commandCategorias);
		

		List<? extends Movimiento> movimientos = ((FisiosportsUI) UI.getCurrent()).getiMovimientos().obtenerMovimientos(Movimiento.class, null, null, null, null, null);
		contenedor = new ContenedorMovimientosCaja(MovimientoDT.class, MovimientoDT.buildList(movimientos));
		
		table.setContainerDataSource(contenedor);
		table.setVisibleColumns(ContenedorMovimientosCaja.getColumnasVisibles());
		table.setColumnHeaders(ContenedorMovimientosCaja.getNonbresColumnas());
		
		this.addComponent(menu);
		this.addComponent(table);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
