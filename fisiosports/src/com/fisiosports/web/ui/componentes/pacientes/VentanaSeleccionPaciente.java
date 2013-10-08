package com.fisiosports.web.ui.componentes.pacientes;

import java.util.List;

import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.negocio.FabricaControladores;
import com.fisiosports.negocio.IPacientes;
import com.fisiosports.web.ui.componentes.agenda.VentanaConsulta;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VentanaSeleccionPaciente extends Window{
	
	private VentanaConsulta ventanaConsulta;
	private TextField documento = new TextField();
	private TextField nombre = new TextField();
	private TextField apellido = new TextField();
	
	private Filter filterDocumento = new SimpleStringFilter("documento", "", true, false);
	private Filter filterNombre = new SimpleStringFilter("nombre", "", true, false);
	private Filter filterApellido = new SimpleStringFilter("apellido", "", true, false);

	private Table tablaPacientes = new Table();
	private IPacientes iPacientes = FabricaControladores.getIClientes(); 
	private ContenedorPacientes contenedor;
	
	private VerticalLayout layout = new VerticalLayout();
		
	public VentanaSeleccionPaciente(VentanaConsulta ventanaConsulta){

		contenedor = new ContenedorPacientes(Paciente.class);
		tablaPacientes.setContainerDataSource(contenedor);
		tablaPacientes.setVisibleColumns(ContenedorPacientes.columnasVisiblesReducidas());
		tablaPacientes.setColumnHeaders(ContenedorPacientes.nombresColumnasReducidas());
		tablaPacientes.setSelectable(true);
		tablaPacientes.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
               //implement your logic here
            	System.out.println("[VentanaSeleccionPaciente] item click:"+event.getItem());
            }
        });
		contenedor.addContainerFilter(filterDocumento);
		contenedor.addContainerFilter(filterNombre);
		contenedor.addContainerFilter(filterApellido);
		HorizontalLayout hl = new HorizontalLayout();
		
		documento.setInputPrompt("documento");
		documento.setImmediate(true);
		documento.setWidth("12em");
		tablaPacientes.setColumnWidth("documento", 200);
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
		nombre.setWidth("18em");
		tablaPacientes.setColumnWidth("nombre", 240);
		nombre.addTextChangeListener(new TextChangeListener(){
			@Override
			public void textChange(TextChangeEvent event) {
				System.out.println("[ComponentePacientes] nombre.textChange");
				contenedor.removeContainerFilter(filterNombre);
				filterNombre = new SimpleStringFilter("nombre", event.getText(), true, true);
				contenedor.addContainerFilter(filterNombre);
			}
		});
		hl.addComponent(nombre);
		
		apellido.setInputPrompt("apellido");
		apellido.setImmediate(true);
		apellido.setWidth("18em");
		tablaPacientes.setColumnWidth("apellido", 240);
		apellido.addTextChangeListener(new TextChangeListener(){
			@Override
			public void textChange(TextChangeEvent event) {
				System.out.println("[ComponentePacientes] apellido.textChange:"+event.getText());
				System.out.println("[ComponentePacientes] apellido.textChange:"+apellido.getValue());
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

	
	
}
