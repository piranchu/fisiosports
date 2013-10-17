package com.fisiosports.web.ui.componentes.pacientes;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IPacientes;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window;

public class ComponentePacientes extends Panel implements Observer{
	
	private static final long serialVersionUID = 1L;
	private VerticalLayout layout = new VerticalLayout();
	private Table tablaPacientes = new Table();
	private HorizontalLayout layoutMenuPaciente = new HorizontalLayout();
	private ComponenteEvaluacion evaluacion; 
	
	private List<Paciente> listaPacientes;
	private IPacientes iPacientes = FabricaControladores.getIClientes(); 
	private Button botonAltaPaciente = new Button("Alta de paciente");
	private ComponentePacientes componentePacientes;
	private ContenedorPacientes contenedor;
	
	private TextField documento = new TextField();
	private TextField nombre = new TextField();
	private TextField apellido = new TextField();
	
	private Filter filterDocumento = new SimpleStringFilter("documento", "", true, false);
	private Filter filterNombre = new SimpleStringFilter("nombre", "", true, false);
	private Filter filterApellido = new SimpleStringFilter("apellido", "", true, false);
	
	public ComponentePacientes(final UI ui){
		
		this.setImmediate(true);
		this.componentePacientes = this;
		layout.setSpacing(true);
		layout.setMargin(true);
		
		layoutMenuPaciente.addComponent(botonAltaPaciente);
		botonAltaPaciente.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Window window = new VentanaPaciente(componentePacientes);
				ui.addWindow(window);
			}
		});
		this.layout.addComponent(layoutMenuPaciente);

		contenedor = new ContenedorPacientes(Paciente.class);
		tablaPacientes.setContainerDataSource(contenedor);
		tablaPacientes.setVisibleColumns(ContenedorPacientes.columnasVisibles());
		tablaPacientes.setColumnHeaders(ContenedorPacientes.nombresColumnas());
		tablaPacientes.setImmediate(true);
		contenedor.addContainerFilter(filterDocumento);
		contenedor.addContainerFilter(filterNombre);
		contenedor.addContainerFilter(filterApellido);
		
		HorizontalLayout hl = new HorizontalLayout();
		
		documento.setInputPrompt("documento");
		documento.setImmediate(true);
		documento.setWidth("115px");
		tablaPacientes.setColumnWidth("documento", 105);
		documento.addTextChangeListener(new TextChangeListener(){
			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterDocumento);
				filterDocumento = new SimpleStringFilter("documento", event.getText(), true, true);
				contenedor.addContainerFilter(filterDocumento);
			}
		});
		hl.addComponent(documento);
		nombre.setInputPrompt("nombre");
		nombre.setImmediate(true);
		nombre.setWidth("165px");
		tablaPacientes.setColumnWidth("nombre", 150);
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
		apellido.setWidth("165px");
		tablaPacientes.setColumnWidth("apellido", 150);
		apellido.addTextChangeListener(new TextChangeListener(){
			@Override
			public void textChange(TextChangeEvent event) {
				contenedor.removeContainerFilter(filterApellido);
				filterApellido = new SimpleStringFilter("apellido", event.getText(), true, true);
				contenedor.addContainerFilter(filterApellido);
				/*for (Filter filter:contenedor.getContainerFilters()){
					System.out.println("\t filter:"+filter.toString());
				}*/
						
			}

		});
		hl.addComponent(apellido);
		this.layout.addComponent(hl);
		
		tablaPacientes.setImmediate(true);
		tablaPacientes.addItemClickListener(new ItemClickListener(){

			private static final long serialVersionUID = 1L;
			@Override
			public void itemClick(ItemClickEvent event) {
				//Notification.show("Próximamente:"+event.getItem());
				Notification.show("Próximamente: evaluación, diagnóstico, tratamiento y antecedentes");
			}
			
		});
		consultarPacientes();
		this.layout.addComponent(tablaPacientes);
		
		this.setContent(layout);
	}
	
	public void consultarPacientes(){
		this.listaPacientes = this.iPacientes.obtenerPacientes();
		contenedor.removeAllItems();
		contenedor.addAll(listaPacientes);
		
		if (listaPacientes.size() == 0)
			tablaPacientes.setVisible(false);
		if (listaPacientes.size() > 15){
			tablaPacientes.setPageLength(15);
		}else{
			tablaPacientes.setPageLength(listaPacientes.size());
		}
		tablaPacientes.markAsDirty();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("[ComponentePacientes.update]");
		this.consultarPacientes();
		
	}

}
