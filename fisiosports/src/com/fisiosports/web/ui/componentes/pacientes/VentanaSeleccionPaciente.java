package com.fisiosports.web.ui.componentes.pacientes;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.negocio.IPacientes;
import com.fisiosports.web.ui.contenedores.ContenedorPacientes;
import com.fisiosports.web.ui.contenedores.beantypes.PacienteDT;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaSeleccionPaciente extends Window implements Observer{
	
	private static final long serialVersionUID = 1L;
	private ComponenteSeleccionPaciente componenteSeleccion;
	private TextField documento = new TextField();
	private TextField nombre = new TextField();
	private TextField apellido = new TextField();
	
	private Filter filterDocumento = new SimpleStringFilter("paciente.documento", "", true, false);
	private Filter filterNombre = new SimpleStringFilter("paciente.nombre", "", true, false);
	private Filter filterApellido = new SimpleStringFilter("paciente.apellido", "", true, false);

	private Table tablaPacientes = new Table();
	private IPacientes iPacientes; 
	private ContenedorPacientes contenedor;
	
	private Button botonAltaPaciente; 
	
	private VerticalLayout layout = new VerticalLayout();
	private Observer observer;
		
	public VentanaSeleccionPaciente(final IPacientes iPacientes, final Observer observer){
		this.iPacientes = iPacientes;
		this.observer = observer;
		this.setModal(true);
		this.setCaption("Selección de pacientes");
		layout.setMargin(true);

		botonAltaPaciente = new Button("", FontAwesome.PLUS_CIRCLE);
		botonAltaPaciente.setDescription("Nuevo cliente");
		botonAltaPaciente.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		botonAltaPaciente.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Window window = new VentanaPaciente(iPacientes, VentanaSeleccionPaciente.this, null);
				UI.getCurrent().addWindow(window);
			}
		});
		layout.addComponent(botonAltaPaciente);
		
		contenedor = new ContenedorPacientes(PacienteDT.class);
//		tablaPacientes.setSizeFull();
		tablaPacientes.setContainerDataSource(contenedor);
		tablaPacientes.setVisibleColumns(ContenedorPacientes.columnasVisiblesReducidas());
		tablaPacientes.setColumnHeaders(ContenedorPacientes.nombresColumnasReducidas());
		tablaPacientes.setSelectable(true);
		tablaPacientes.addItemClickListener(new ItemClickEvent.ItemClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
            public void itemClick(ItemClickEvent event) {
            	Long documento = (Long)event.getItem().getItemProperty("paciente.documento").getValue();
            	Paciente paciente = iPacientes.obtenerPaciente(documento);
            	observer.update(null, paciente);
            	close();
            }
        });
		contenedor.addContainerFilter(filterDocumento);
		contenedor.addContainerFilter(filterNombre);
		contenedor.addContainerFilter(filterApellido);
		VerticalLayout hl = new VerticalLayout();
		hl.setMargin(new MarginInfo(false, false, true, false));
		
		documento.setInputPrompt("documento");
		documento.setImmediate(true);
		documento.setWidth("10em");
//		tablaPacientes.setColumnWidth("documento", 110);
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
		nombre.setWidth("15em");
//		tablaPacientes.setColumnWidth("nombre", 160);
		nombre.addTextChangeListener(new TextChangeListener(){
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
		apellido.setWidth("15em");
//		tablaPacientes.setColumnWidth("apellido", 160);
		apellido.addTextChangeListener(new TextChangeListener(){
			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterApellido);
				filterApellido = new SimpleStringFilter("paciente.apellido", event.getText(), true, true);
				contenedor.addContainerFilter(filterApellido);
//				for (Filter filter:contenedor.getContainerFilters()){
//					System.out.println("\t filter:"+filter.toString());
//				}
						
			}

		});
		hl.addComponent(apellido);
		this.layout.addComponent(hl);
		
		tablaPacientes.setImmediate(true);
		consultarPacientes();
		this.layout.addComponent(tablaPacientes);

		this.setContent(layout);
		
	}
	
	public void consultarPacientes(){
		contenedor.removeAllItems();
		List<PacienteDT> listaPacientesDT = new LinkedList<>();
		for (Paciente paciente:this.iPacientes.obtenerPacientes()){
			listaPacientesDT.add(new PacienteDT(paciente, null));
		}

		contenedor.addAll(listaPacientesDT);
		
		if (tablaPacientes.size() == 0)
			tablaPacientes.setVisible(false);
		if (tablaPacientes.size() > 15){
			tablaPacientes.setPageLength(15);
		}else{
			tablaPacientes.setPageLength(tablaPacientes.size());
		}
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Paciente){
        	Paciente paciente = (Paciente)arg1;
        	if (observer != null)
        		this.observer.update(null, paciente);
        	close();
		}
		
	}

	
	
}
