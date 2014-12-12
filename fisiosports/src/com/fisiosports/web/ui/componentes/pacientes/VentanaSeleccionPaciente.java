package com.fisiosports.web.ui.componentes.pacientes;

import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.negocio.IPacientes;
import com.fisiosports.web.ui.componentes.agenda.VentanaConsulta;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VentanaSeleccionPaciente extends Window implements Observer{
	
	private static final long serialVersionUID = 1L;
	private VentanaConsulta ventanaConsulta;
	private TextField documento = new TextField();
	private TextField nombre = new TextField();
	private TextField apellido = new TextField();
	
	private Filter filterDocumento = new SimpleStringFilter("documento", "", true, false);
	private Filter filterNombre = new SimpleStringFilter("nombre", "", true, false);
	private Filter filterApellido = new SimpleStringFilter("apellido", "", true, false);

	private Table tablaPacientes = new Table();
	private IPacientes iPacientes; 
	private ContenedorPacientes contenedor;
	
	private Button botonAltaPaciente = new Button("Alta de paciente"); 
	
	private VerticalLayout layout = new VerticalLayout();
	private Observer ventanaActual;
		
	public VentanaSeleccionPaciente(final IPacientes iPacientes, final VentanaConsulta ventanaConsulta){
		this.iPacientes = iPacientes;
		this.ventanaActual = this;
		this.ventanaConsulta = ventanaConsulta;
		this.setModal(true);
		this.setCaption("Selección de pacientes");
		layout.setMargin(true);
		contenedor = new ContenedorPacientes(Paciente.class);
		tablaPacientes.setSizeFull();
		tablaPacientes.setContainerDataSource(contenedor);
		tablaPacientes.setVisibleColumns(ContenedorPacientes.columnasVisiblesReducidas());
		tablaPacientes.setColumnHeaders(ContenedorPacientes.nombresColumnasReducidas());
		tablaPacientes.setSelectable(true);
		tablaPacientes.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
               //implement your logic here
            	System.out.println("[VentanaSeleccionPaciente] event.getSource():"+event.getSource());
            	System.out.println("[VentanaSeleccionPaciente] item click:"+event.getItem());
            	System.out.println("[VentanaSeleccionPaciente] item click:"+event.getItem().getClass().getName());
            	System.out.println("[VentanaSeleccionPaciente] documento:"+event.getItem().getItemProperty("documento").getValue());
            	Long documento = (Long)event.getItem().getItemProperty("documento").getValue();
            	Paciente paciente = iPacientes.obtenerPaciente(documento);
            	System.out.println("[VentanaSeleccionPaciente] item click:"+paciente);
            	ventanaConsulta.setPaciente(paciente);
            	close();
            }
        });
		contenedor.addContainerFilter(filterDocumento);
		contenedor.addContainerFilter(filterNombre);
		contenedor.addContainerFilter(filterApellido);
		HorizontalLayout hl = new HorizontalLayout();
		
		documento.setInputPrompt("documento");
		documento.setImmediate(true);
		documento.setWidth("10em");
		tablaPacientes.setColumnWidth("documento", 110);
		documento.addTextChangeListener(new TextChangeListener(){
			@Override
			public void textChange(TextChangeEvent event) {
				System.out.println("[ComponentePacientes] documento.textChange");
				contenedor.removeContainerFilter(filterDocumento);
				filterDocumento = new SimpleStringFilter("documento", event.getText(), true, true);
				contenedor.addContainerFilter(filterDocumento);
			}
		});
		hl.addComponent(documento);
		nombre.setInputPrompt("nombre");
		nombre.setImmediate(true);
		nombre.setWidth("15em");
		tablaPacientes.setColumnWidth("nombre", 160);
		nombre.addTextChangeListener(new TextChangeListener(){
			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterNombre);
				filterNombre = new SimpleStringFilter("nombre", event.getText(), true, true);
				contenedor.addContainerFilter(filterNombre);
			}
		});
		hl.addComponent(nombre);
		
		apellido.setInputPrompt("apellido");
		apellido.setImmediate(true);
		apellido.setWidth("15em");
		tablaPacientes.setColumnWidth("apellido", 160);
		apellido.addTextChangeListener(new TextChangeListener(){
			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterApellido);
				filterApellido = new SimpleStringFilter("apellido", event.getText(), true, true);
				contenedor.addContainerFilter(filterApellido);
				for (Filter filter:contenedor.getContainerFilters()){
					System.out.println("\t filter:"+filter.toString());
				}
						
			}

		});
		hl.addComponent(apellido);
		this.layout.addComponent(hl);
		
		tablaPacientes.setImmediate(true);
		consultarPacientes();
		this.layout.addComponent(tablaPacientes);

		layout.addComponent(botonAltaPaciente);
		botonAltaPaciente.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Window window = new VentanaPaciente(iPacientes, ventanaActual);
				UI.getCurrent().addWindow(window);
			}
		});

		this.setContent(layout);
		
	}
	
	public void consultarPacientes(){
		contenedor.removeAllItems();
		contenedor.addAll(this.iPacientes.obtenerPacientes());
		
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
        	System.out.println("[VentanaSeleccionPaciente.update] paciente:"+paciente);
        	ventanaConsulta.setPaciente(paciente);
        	close();
		}
		
	}

	
	
}
