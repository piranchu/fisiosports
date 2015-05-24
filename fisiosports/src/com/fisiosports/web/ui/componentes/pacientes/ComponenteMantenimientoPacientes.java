package com.fisiosports.web.ui.componentes.pacientes;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.contenedores.ContenedorPacientes;
import com.fisiosports.web.ui.contenedores.beantypes.PacienteDT;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteMantenimientoPacientes extends VerticalLayout implements Observer{

	private static final long serialVersionUID = 1L;
	
	private FisiosportsUI ui;
	
	private Table tablaPacientes = new Table(){
		private static final long serialVersionUID = 1L;
		@Override
		protected String formatPropertyValue(Object rowId, Object colId,
				Property<?> property) {
			Object v = property.getValue();
			if (v instanceof Long) {
				Long longValue = (Long) v;
				DecimalFormat df = new DecimalFormat("###");
				return df.format(longValue);
			}
			return super.formatPropertyValue(rowId, colId, property);
		}
	};
	
	private List<Paciente> listaPacientes;
	List<PacienteDT> listaPacientesDT;
	private ContenedorPacientes contenedor;

	private TextField documento = new TextField();
	private TextField nombre = new TextField();
	private TextField apellido = new TextField();
	private TextField telefono = new TextField();
	
	private Filter filterDocumento = new SimpleStringFilter("paciente.documento", "", true, false);
	private Filter filterNombre = new SimpleStringFilter("paciente.nombre", "", true, false);
	private Filter filterApellido = new SimpleStringFilter("paciente.apellido", "", true, false);
	private Filter filterTelefono = new SimpleStringFilter("paciente.telefono", "", true, false);

	private ComponentePacientes componentePacientes;
	
	
	public ComponenteMantenimientoPacientes(ComponentePacientes componentePacientes){
		
		this.ui = (FisiosportsUI) UI.getCurrent();
		this.componentePacientes = componentePacientes;
		
		this.setSpacing(true);
		this.setMargin(new MarginInfo(false, true, false, true));
		
		this.addComponent(this.createMenuBar());
		
		contenedor = new ContenedorPacientes(PacienteDT.class);
		
		contenedor.addContainerFilter(filterDocumento);
		contenedor.addContainerFilter(filterNombre);
		contenedor.addContainerFilter(filterApellido);
		contenedor.addContainerFilter(filterTelefono);

		tablaPacientes.setContainerDataSource(contenedor);
		tablaPacientes.setVisibleColumns(ContenedorPacientes.columnasVisibles());
		tablaPacientes.setColumnHeaders(ContenedorPacientes.nombresColumnas());
		tablaPacientes.setImmediate(true);
		
		HorizontalLayout hl = new HorizontalLayout();
		
		documento.setInputPrompt("documento");
		documento.setImmediate(true);
		documento.setWidth("115px");
		documento.addTextChangeListener(new TextChangeListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterDocumento);
				filterDocumento = new SimpleStringFilter("paciente.documento", event.getText(), true, true);
				contenedor.addContainerFilter(filterDocumento);
			}
		});
		hl.addComponent(documento);
		
		nombre.setInputPrompt("nombre");
		nombre.setImmediate(true);
		nombre.setWidth("165px");
		nombre.addTextChangeListener(new TextChangeListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterNombre);
				filterNombre = new SimpleStringFilter("paciente.nombre", event.getText(), true, true);
				contenedor.addContainerFilter(filterNombre);
			}
		});
		hl.addComponent(nombre);
		
		apellido.setInputPrompt("apellido");
		apellido.setImmediate(true);
		apellido.setWidth("165px");
		apellido.addTextChangeListener(new TextChangeListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterApellido);
				filterApellido = new SimpleStringFilter("paciente.apellido", event.getText(), true, true);
				contenedor.addContainerFilter(filterApellido);
			}

		});
		hl.addComponent(apellido);

		telefono.setInputPrompt("teléfono");
		telefono.setImmediate(true);
		telefono.setWidth("165px");
		telefono.addTextChangeListener(new TextChangeListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterTelefono);
				filterTelefono = new SimpleStringFilter("paciente.telefono", event.getText(), true, true);
				contenedor.addContainerFilter(filterTelefono);
			}
		});
		hl.addComponent(telefono);
		
		this.addComponent(hl);
		
		tablaPacientes.setImmediate(true);
		consultarPacientes();
		this.addComponent(tablaPacientes);
	}
	

	private MenuBar createMenuBar(){
		MenuBar menu = new MenuBar();
		
		MenuItem item = menu.addItem("", 
				FontAwesome.PLUS_CIRCLE, 
				this.addCommand);
		item.setDescription("nuevo cliente");

		menu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		return menu;
	}
	
	private Command addCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			Window window = new VentanaPaciente(ui.getiPacientes(), ComponenteMantenimientoPacientes.this, null);
			window.setModal(true);
			ui.addWindow(window);
		}
	};
	
	public void consultarPacientes(){

		contenedor.removeAllItems();

		listaPacientes = ui.getiPacientes().obtenerPacientes();
		listaPacientesDT = new LinkedList<>();
		
		for (Paciente paciente:listaPacientes){
			listaPacientesDT.add(new PacienteDT(paciente, this));
		}
		contenedor.addAll(listaPacientesDT);
		
		if (listaPacientes.size() > 15){
			tablaPacientes.setPageLength(15);
		}else{
			tablaPacientes.setPageLength(listaPacientes.size());
		}
		
//		tablaPacientes.refreshRowCache();
		tablaPacientes.markAsDirty();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		this.consultarPacientes();
		this.markAsDirtyRecursive();
	}
	
	public void modifyPaciente(Paciente paciente){
		Window window = new VentanaPaciente(ui.getiPacientes(), ComponenteMantenimientoPacientes.this, paciente);
		window.setModal(true);
		ui.addWindow(window);
	}
	
	public void deletePaciente(Paciente paciente){
		try{
			this.ui.getiPacientes().borrarPaciente(paciente);
			this.update(null, null);
			Notification.show("Paciente eliminado");
		}catch (Exception e){
			Notification.show("No se pudo eliminar el paciente. Comuníquelo al analista.", Type.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void evaluacionPaciente(Paciente paciente){
		componentePacientes.setComponentEvaluacionPacientes(paciente);
	}

}
