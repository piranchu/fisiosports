package com.fisiosports.web.ui.componentes.caja;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Caja;
import com.fisiosports.modelo.entidades.caja.Caja.Estado;
import com.fisiosports.modelo.entidades.caja.CierreCaja;
import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.modelo.entidades.caja.ProductoServicio;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.contenedores.ContenedorMovimientosCaja;
import com.fisiosports.web.ui.contenedores.ContenedorProductoServicio;
import com.fisiosports.web.ui.contenedores.beantypes.BeanItemMovimiento;
import com.fisiosports.web.ui.contenedores.beantypes.BeanItemProductoServicio;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteCaja extends VerticalLayout implements Observer{
	
	private static final long serialVersionUID = 1L;

	private static NumberFormat numberFormat = new DecimalFormat("#,###.00");
	private FisiosportsUI ui;
	private Caja caja;
	private Component mainComponent;
	private Component componentMovimientosActuales;
//	private Component componentConsultaMovimientos;
//	private Component componentConsultaCierres;
	
	private Table table = new Table(){

        @Override
        protected String formatPropertyValue(Object rowId, Object colId,
                Property property) {
            Object v = property.getValue();
            if (v instanceof Double) {
//                Double doubleValue = (Double) v;
                return numberFormat.format(v);
            }
            return super.formatPropertyValue(rowId, colId, property);
        }

    };
	private ContenedorMovimientosCaja contenedor;
	private MenuBar menu;
	private MenuItem menuAbrirCerrarCaja;
	private MenuItem menuNuevoMovimientoIngreso;
	private MenuItem menuNuevoMovimientoEgreso;
	private MenuItem menuConfiguracion;
	private MenuItem menuConsultas; 
	private MenuItem menuConsultaCuerres;
	private MenuItem menuConsultaMovimientos;
	
	private Filter filterProductoServicio = new SimpleStringFilter("movimiento.productoServicio.nombre", "", true, false);
	private Filter filterConcepto = new SimpleStringFilter("movimiento.concepto.nombre", "", true, false);
	private Filter filterDocumento = new SimpleStringFilter("movimiento.paciente.documento", "", true, false);
//	private Filter filterFecha = new com.vaadin.data.util.filter.Between("movimiento.fecha", new Date(new Long(0)), new Date());

	private Label labelEstadoCaja = new Label();
	
	private HorizontalLayout layoutSaldoCaja = new HorizontalLayout();

	public ComponenteCaja(){
	
		this.ui = (FisiosportsUI) UI.getCurrent();
		
		this.setMargin(true);
		this.setSpacing(true);
		
		this.caja = ui.getiCaja().obtenerCaja();
		

		this.componentMovimientosActuales = obtenerComponenteMovimientosActuales();
//		this.componentConsultaMovimientos = new ComponenteConsultaMovimientos(this);
//		this.componentConsultaCierres = new ComponenteConsultaCierres(this);
		
		
//		this.addComponent(menu);
//		this.addComponent(this.obtenerBuscador());
		this.mainComponent = componentMovimientosActuales; 
		this.addComponent(mainComponent);
	}
	
	public Component obtenerComponenteMovimientosActuales(){
		VerticalLayout layout = new VerticalLayout();
		
		HorizontalLayout hl = new HorizontalLayout();
		hl.setSizeFull();
		
		
		menu = new MenuBar();
		menu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		
		menuAbrirCerrarCaja = menu.addItem("", this.commandCaja);

		menuNuevoMovimientoIngreso = menu.addItem("", FontAwesome.ARROW_CIRCLE_DOWN, addMovimientoIngreso);
		menuNuevoMovimientoIngreso.setDescription("Nuevo ingreso de caja");
		
		menuNuevoMovimientoEgreso = menu.addItem("", FontAwesome.ARROW_CIRCLE_UP, addMovimientoEgreso);
		menuNuevoMovimientoEgreso.setDescription("Nuevo egreso de caja");
		
		if (caja.getEstado().equals(Estado.ABIERTA)){
			menuAbrirCerrarCaja.setIcon(FontAwesome.LOCK);
			menuAbrirCerrarCaja.setDescription("cerrar caja");
		}else{
			menuAbrirCerrarCaja.setIcon(FontAwesome.UNLOCK);
			menuAbrirCerrarCaja.setDescription("abrir caja");
			menuNuevoMovimientoEgreso.setEnabled(false);
			menuNuevoMovimientoIngreso.setEnabled(false);
		}

		menuConsultas= menu.addItem("", FontAwesome.SEARCH, null);
		menuConsultas.setDescription("consultas");
		menuConsultas.addItem("cierres", this.commandVerCierres);
		menuConsultas.addItem("movimientos", this.commandVerMovimientos);

		menuConfiguracion = menu.addItem("", FontAwesome.GEAR, null);
		menuConfiguracion.setDescription("configuración");
		menuConfiguracion.addItem("conceptos", this.commandConceptos);
		menuConfiguracion.addItem("productos y servicios", this.commandProductosServicios);
		
		hl.addComponent(menu);
		
		layoutSaldoCaja.setSizeFull();
		setLabelSaldoCaja();
		hl.addComponent(layoutSaldoCaja);
		
		layout.addComponent(hl);
		
		
		List<? extends Movimiento> movimientos = ((FisiosportsUI) UI.getCurrent()).getiCaja().obtenerMovimientosActuales();
		contenedor = new ContenedorMovimientosCaja(BeanItemMovimiento.class);
		contenedor.addAll(BeanItemMovimiento.buildList(movimientos, this));		
		
		table.setContainerDataSource(contenedor);
		table.setVisibleColumns(ContenedorMovimientosCaja.getColumnasVisibles());
		table.setColumnHeaders(ContenedorMovimientosCaja.getNonbresColumnas());
		resizeTable();
		
		layout.addComponent(table);
		
		return layout;
	}
	
	public void setComponentMovimientosActuales(){
		this.removeComponent(mainComponent);
		this.mainComponent = componentMovimientosActuales;
		this.addComponent(mainComponent);
		this.markAsDirtyRecursive();
	}
	
	private void setComponentConsultaMovimientos(){
		this.removeComponent(mainComponent);
		this.mainComponent = new ComponenteConsultaMovimientos(this);
		this.addComponent(mainComponent);
		this.markAsDirtyRecursive();
	}

	public void setComponentConsultaCierres(){
		this.removeComponent(mainComponent);
		this.mainComponent = new ComponenteConsultaCierres(this);
		this.addComponent(mainComponent);
		this.markAsDirtyRecursive();
	}
	
	
	
	private HorizontalLayout obtenerBuscador(){
		
		HorizontalLayout hl = new HorizontalLayout();
		
//		contenedor.addContainerFilter(filterCuenta);
//		contenedor.addContainerFilter(filterCategoria);
//		contenedor.addContainerFilter(filterDocumento);
//		contenedor.addContainerFilter(dateFilter);
		
		ContenedorProductoServicio contenedorProductoServicio = new ContenedorProductoServicio(BeanItemProductoServicio.class);
		ComboBox comboProductoServicio = new ComboBox();
		comboProductoServicio.setInputPrompt("producto/servicio");
		comboProductoServicio.setContainerDataSource(contenedorProductoServicio);
		comboProductoServicio.setItemCaptionPropertyId("nombre");
		List<BeanItemProductoServicio> beanItemsProductosServicios = new LinkedList<BeanItemProductoServicio>();
		for (ProductoServicio ps:ui.getiCaja().consultarProductosServicios(null)){
			beanItemsProductosServicios.add(new BeanItemProductoServicio(ps, null));
		}
		
		contenedorProductoServicio.addAll(beanItemsProductosServicios);
		comboProductoServicio.setNullSelectionAllowed(false);
		comboProductoServicio.setInvalidAllowed(false);
		comboProductoServicio.setImmediate(true);
		comboProductoServicio.addValueChangeListener(new ValueChangeListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				if (contenedor.getContainerFilters().contains(filterProductoServicio)){
					contenedor.removeContainerFilter(filterProductoServicio);
				}

				ProductoServicio cuentaFinanciera = (ProductoServicio)event.getProperty().getValue();
				if (cuentaFinanciera != null){
					filterProductoServicio = new SimpleStringFilter("movimiento.productoServicio.nombre", cuentaFinanciera.getNombre(), true, true);
					contenedor.addContainerFilter(filterProductoServicio);
				}
			}
			
		});
		hl.addComponent(comboProductoServicio);
		
		TextField documento = new TextField();
		documento.setInputPrompt("documento");
		documento.setImmediate(true);
		documento.addTextChangeListener(new TextChangeListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterDocumento);
				filterDocumento = new SimpleStringFilter("movimiento.paciente.documento", event.getText(), true, true);
				contenedor.addContainerFilter(filterDocumento);
			}
		});
		hl.addComponent(documento);
		
		return hl;
	}
	
	@Override
	public void update(Observable o, Object arg) {
//		System.out.println("[ComponenteCaja.update] arg="+arg);
		if (arg != null){
			if (arg instanceof Caja){
//				System.out.println("[ComponenteCaja.update] apertura caja");
				aperturaCaja((Caja) arg);
			}else if (arg instanceof CierreCaja){
//				System.out.println("[ComponenteCaja.update] cierre caja");
				cierreCaja((CierreCaja) arg);
			}else if(arg instanceof Movimiento){
//				System.out.println("[ComponenteCaja.update] nuevo movimiento");
				nuevoMovimiento((Movimiento) arg);
			}
		}
		
	}
	
	private void nuevoMovimiento(Movimiento movimiento) {
		this.contenedor.addBean(new BeanItemMovimiento(movimiento, this));
		caja = ui.getiCaja().obtenerCaja();
		setLabelSaldoCaja();
		resizeTable();
		table.refreshRowCache();
		table.markAsDirty();
	}

	private void cierreCaja(CierreCaja cierre) {
//		Notification.show("Cierre de caja");
		caja = ui.getiCaja().obtenerCaja();
		// Se modifica el menú de cierre/apertura de caja
//		menuAbrirCerrarCaja.setIcon(FontAwesome.UNLOCK);
//		menuAbrirCerrarCaja.setDescription("abrir caja");
//		menuNuevoMovimientoEgreso.setEnabled(false);
//		menuNuevoMovimientoIngreso.setEnabled(false);
		// Se eliminan todos los items de la tabla de movimientos
		this.contenedor.removeAllItems();
		// Se modifica label con estado
//		this.labelEstadoCaja.setCaption(caja.getEstado().toString());
//		labelEstadoCaja.markAsDirty();
		markAsDirty();

	}
	
	private void aperturaCaja(Caja caja) {
		Notification.show("Apertura de caja");
		this.caja = caja;
		// Se modifica el menú de cierre/apertura de caja
		menuAbrirCerrarCaja.setIcon(FontAwesome.LOCK);
		menuAbrirCerrarCaja.setDescription("cerrar caja");
		menuNuevoMovimientoEgreso.setEnabled(true);
		menuNuevoMovimientoIngreso.setEnabled(true);
		// Se modifica label con estado
		this.labelEstadoCaja.setCaption(caja.getEstado().toString());
		labelEstadoCaja.markAsDirty();
		markAsDirty();
	}

	private void resizeTable(){
		if (contenedor.size() > 15){
			table.setPageLength(15);
		}else{
			table.setPageLength(contenedor.size());
		}
		table.markAsDirtyRecursive();

	}
	
	public void borrarMovimiento(BeanItemMovimiento beanItemMovimiento){
		this.contenedor.removeItem(beanItemMovimiento);
		caja = ui.getiCaja().obtenerCaja();

		//this.ui.getiCaja().borrarMovimiento(beanItemMovimiento.getMovimiento().getId());
		this.ui.getiCaja().borrarMovimiento(beanItemMovimiento.getMovimiento());
	}

	private final Command addMovimientoIngreso = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
			Window window = new VentanaAltaMovimiento(ComponenteCaja.this, Movimiento.TipoMovimiento.INGRESO);
			getUI().addWindow(window);
		}
		
	};
	
	private final Command addMovimientoEgreso = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
			Window window = new VentanaAltaMovimiento(ComponenteCaja.this, Movimiento.TipoMovimiento.EGRESO);
			getUI().addWindow(window);
		}
		
	};

	private final Command commandProductosServicios = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
			Window window = new VentanaProductoServicio(ComponenteCaja.this);
			getUI().addWindow(window);
		}
		
	};

	private final Command commandConceptos = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
			Window window = new VentanaConcepto(ComponenteCaja.this);
			getUI().addWindow(window);
		}
		
	};
	
	private final Command commandCaja = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
			if (ComponenteCaja.this.caja.getEstado().equals(Caja.Estado.ABIERTA)){
				Window window = new VentanaCerrarCaja(caja, ComponenteCaja.this);
				getUI().addWindow(window);
			}else{
				
				try {
					caja = ui.getiCaja().abrirCaja(caja);
					update(null, caja);
					Notification.show("Caja abierta");
				}catch (Exception e) {
					e.printStackTrace();
					Notification.show("No se pudo abrir la caja:"+e.getMessage(),
							Notification.Type.ERROR_MESSAGE);
				}				
				
//				Window window = new VentanaAbrirCaja(caja, ComponenteCaja.this);
//				getUI().addWindow(window);
			}
		}
		
	};
	
	private final Command commandVerCierres = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
//			Window window = new VentanaConcepto(ComponenteCaja.this);
//			getUI().addWindow(window);
			setComponentConsultaCierres();
		}
		
	};

	private final Command commandVerMovimientos = new Command(){

		private static final long serialVersionUID = 1L;

		@Override
		public void menuSelected(MenuItem selectedItem) {
////			Window window = new VentanaConcepto(ComponenteCaja.this);
////			getUI().addWindow(window);
//			Notification.show("próximamuble saente: ver todos los movimientos de caja");
			setComponentConsultaMovimientos();
		}
		
	};

	private void setLabelSaldoCaja(){
		layoutSaldoCaja.removeAllComponents();
		Label label = new Label("Saldo de caja: "+numberFormat.format(this.caja.getSaldo()));
		layoutSaldoCaja.addComponent(label);
		layoutSaldoCaja.setComponentAlignment(label, Alignment.MIDDLE_RIGHT);
	}
	
	
}
