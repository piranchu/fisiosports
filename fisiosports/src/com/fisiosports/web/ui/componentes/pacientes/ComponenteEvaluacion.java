package com.fisiosports.web.ui.componentes.pacientes;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.pacientes.Consulta;
import com.fisiosports.modelo.entidades.pacientes.Evaluacion;
import com.fisiosports.modelo.entidades.pacientes.Gimnasio;
import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.modelo.entidades.pacientes.TerapiaFisica;
import com.fisiosports.modelo.tipos.TipoGimnasio;
import com.fisiosports.modelo.tipos.TipoTerapiaFisica;
import com.fisiosports.negocio.IPacientes;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.contenedores.ContenedorConsulta;
import com.fisiosports.web.ui.contenedores.ContenedorEvaluacion;
import com.fisiosports.web.ui.contenedores.beantypes.ConsultaDT;
import com.fisiosports.web.ui.contenedores.beantypes.EvaluacionDT;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteEvaluacion extends CustomComponent implements Observer {

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private Evaluacion evaluacion;
	private Table tableTratamiento;
	private Table tablaSesionRehabilitacion = new Table("Sesiones");
	private Table tableEvaluacion;	
	private ContenedorConsulta contenedorConsulta;
	private ContenedorEvaluacion contenedorEvaluacion;
	private VerticalLayout layout = new VerticalLayout();
	private ComponenteEvaluacion componenteActual;
	private TextArea textAreaIndicaciones = new TextArea("Indicaciones");;
	private IPacientes iPacientes;
	private ComponentePacientes componentePacientes;
	private FisiosportsUI ui;

	
	public ComponenteEvaluacion(Paciente paciente, ComponentePacientes componentePacientes) {

		ui = (FisiosportsUI) UI.getCurrent();
		this.iPacientes = ui.getiPacientes();
		this.paciente = paciente;
		this.componentePacientes = componentePacientes;
		
		this.componenteActual = this;
		
		layout.setMargin(true);
		layout.setSpacing(true);

		layout.addComponent(this.createMenuBar());

		Label labelEvaluacion = new Label("<b>"+paciente.getNombre() + " " + paciente.getApellido()+"</b>",
				ContentMode.HTML);
		layout.addComponent(labelEvaluacion);


		HorizontalLayout hl1 = new HorizontalLayout();
		hl1.setSpacing(true);
//		hl1.setWidth(100, Unit.PERCENTAGE);
		hl1.addComponent(this.createTablaEvaluacion());
		textAreaIndicaciones.setWidth(35, Unit.EM);;
		textAreaIndicaciones.setEnabled(false);
		hl1.addComponent(textAreaIndicaciones);
		layout.addComponent(hl1);
		
		layout.addComponent(this.createMenuTratamiento());
		
		HorizontalLayout hl2 = new HorizontalLayout();
		hl2.setSpacing(true);
		hl2.addComponent(this.createTablaTratamiento());
		hl2.addComponent(tablaSesionRehabilitacion);
		tablaSesionRehabilitacion.setVisible(false);
		layout.addComponent(hl2);
		

		this.setCompositionRoot(layout);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		consultarEvaluaciones();	
		cargarConsultas();
		this.markAsDirtyRecursive();
	}

	private void cargarConsultas() {
		contenedorConsulta.removeAllItems();
		if (evaluacion != null && evaluacion.getTratamiento() != null){
//			List<ConsultaDT> listaConsutaDT = new LinkedList<>();
			for (Consulta consulta:ui.getiPacientes().obtenerConsultas(evaluacion.getId())){
				contenedorConsulta.addBean(new ConsultaDT(consulta, this));
			}
//			contenedorConsulta.addAll(ui.getiPacientes().obtenerConsultas(evaluacion.getId()));
			
		}
		tableTratamiento.markAsDirty();
		tablaSesionRehabilitacion.setVisible(false);
	}
	
	private MenuBar createMenuBar(){
		MenuBar menu = new MenuBar();
		
		menu.addItem("", 
				FontAwesome.PLUS_CIRCLE, 
				this.addCommand)
				.setDescription("nueva evaluación");
		
		if (this.componentePacientes != null){
			menu.addItem("", 
					FontAwesome.HAND_O_LEFT, 
					this.prevCommand)
					.setDescription("volver");
			
		}
		menu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		
		return menu;
	}
	
	private Command addCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			Window window = new VentanaEvaluacion(
					ComponenteEvaluacion.this.paciente, 
					ComponenteEvaluacion.this,
					null);
			window.setModal(true);
			UI.getCurrent().addWindow(window);
		}
	};
	
	private Command prevCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			if (componentePacientes != null){
				componentePacientes.setComponentMantenimientoPacientes();
			}
		}
	};

	private Table createTablaEvaluacion(){
		
		this.tableEvaluacion  = new Table("Evaluaciones") {
			private static final long serialVersionUID = 1L;
			@Override
			protected String formatPropertyValue(Object rowId, Object colId,
					Property<?> property) {
				Object v = property.getValue();
				if (v instanceof Date) {
					Date dateValue = (Date) v;
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dateValue);
					return calendar.get(Calendar.DATE)+"/"+
							(calendar.get(Calendar.MONTH)+1)+"/"+
							calendar.get(Calendar.YEAR);
				}
				return super.formatPropertyValue(rowId, colId, property);
			}
		};
		
		List<EvaluacionDT> listaEvaliacionDT = new LinkedList<EvaluacionDT>();
		for (Evaluacion evaluacion:paciente.getEvaluaciones()){
			listaEvaliacionDT.add(new EvaluacionDT(evaluacion, this));
		}
		
		this.contenedorEvaluacion = new ContenedorEvaluacion(EvaluacionDT.class, listaEvaliacionDT);
		this.tableEvaluacion.setContainerDataSource(this.contenedorEvaluacion);
		tableEvaluacion.setVisibleColumns(ContenedorEvaluacion.columnasVisibles());
		tableEvaluacion.setColumnHeaders(ContenedorEvaluacion.cabeceraColumnas());
		tableEvaluacion.setSelectable(true);
		if (tableEvaluacion.size()<5){
			tableEvaluacion.setPageLength(tableEvaluacion.size());
		}else{
			tableEvaluacion.setPageLength(5);
		}
		
		tableEvaluacion.addItemClickListener(new ItemClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				EvaluacionDT evaluacionDT = (EvaluacionDT) event.getItemId();
				if (evaluacionDT != null){
					ComponenteEvaluacion.this.evaluacion = evaluacionDT.getEvaluacion();
					textAreaIndicaciones.setEnabled(true);
					ComponenteEvaluacion.this.textAreaIndicaciones.setValue(evaluacion.getIndicaciones());
					cargarConsultas();
					textAreaIndicaciones.setEnabled(false);
				}
			}
			
		});
		tableEvaluacion.setWidth(50, Unit.PERCENTAGE);
		return this.tableEvaluacion;
	}
	
	private void consultarEvaluaciones(){	
		contenedorEvaluacion.removeAllItems();
		ui.getiPacientes().obtenerPaciente(paciente.getDocumento());
		
		for (Evaluacion evaluacion:paciente.getEvaluaciones()){
			contenedorEvaluacion.addBean(new EvaluacionDT(evaluacion, this));
		}
		
		
		if (tableEvaluacion.size()<5){
			tableEvaluacion.setPageLength(tableEvaluacion.size());
		}else{
			tableEvaluacion.setPageLength(5);
		}
	}

	private Table createTablaTratamiento(){
		
		this.tableTratamiento  = new Table("Tratamiento") {
			private static final long serialVersionUID = 1L;
			@Override
			protected String formatPropertyValue(Object rowId, Object colId,
					Property<?> property) {
				Object v = property.getValue();
				if (v instanceof Date) {
					Date dateValue = (Date) v;
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(dateValue);
					return calendar.get(Calendar.DATE)+"/"+
							(calendar.get(Calendar.MONTH)+1)+"/"+
							calendar.get(Calendar.YEAR);
				}
				return super.formatPropertyValue(rowId, colId, property);
			}
		};

		this.contenedorConsulta = new ContenedorConsulta(ConsultaDT.class, null);
		this.tableTratamiento.setContainerDataSource(this.contenedorConsulta);
		tableTratamiento.setVisibleColumns(ContenedorConsulta.getColumnasVisibles());
		tableTratamiento.setColumnHeaders(ContenedorConsulta.getNonbresColumnas());
		tableTratamiento.setSelectable(true);
		if (tableTratamiento.size()<10){
			tableTratamiento.setPageLength(tableTratamiento.size());
		}else{
			tableTratamiento.setPageLength(10);
		}
		
		tableTratamiento.addItemClickListener(new ItemClickListener(){

			private static final long serialVersionUID = 1L;

			@Override
			public void itemClick(ItemClickEvent event) {
				
				ConsultaDT consultaDT = (ConsultaDT) event.getItemId();
				if (consultaDT != null){
					ComponenteEvaluacion.this.cargarSesiones(consultaDT.getConsulta());
				}
			}
			
		});
		return this.tableTratamiento;
	}
	
	private MenuBar createMenuTratamiento(){
		MenuBar menu = new MenuBar();
		
		menu.addItem("", 
				FontAwesome.PLUS_CIRCLE, 
				this.addTratamientoCommand)
				.setDescription("ingresar consulta/sesión");
		
		menu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		
		return menu;
	}
	
	private Command addTratamientoCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			if (evaluacion != null){
				Window window = new VentanaAltaConsultaSesion(iPacientes, componenteActual, evaluacion);
				window.setPositionY(25);
				window.setPositionX(25);
				getUI().addWindow(window);
			}else{
				Notification.show("Debe indicar evaluación", Type.WARNING_MESSAGE);
			}
		}
	};

	private void cargarSesiones(Consulta sesion){
		
		sesion = ui.getiPacientes().obtenerConsulta(sesion.getId());
		
		BeanItemContainer<TipoGimnasio> containerGimansio;
		BeanItemContainer<TipoTerapiaFisica> containerTerapiaFisica;
		tablaSesionRehabilitacion.setVisible(false);
		
		if (sesion instanceof Gimnasio){
			Gimnasio gimnasio = (Gimnasio) sesion;
			containerGimansio = new BeanItemContainer<TipoGimnasio>(
					TipoGimnasio.class, 
					this.ui.getiPacientes().obtenerTipos(gimnasio));
			tablaSesionRehabilitacion.setContainerDataSource(containerGimansio);
		}else if (sesion instanceof TerapiaFisica){
			TerapiaFisica terapia = (TerapiaFisica) sesion;
			containerTerapiaFisica = new BeanItemContainer<TipoTerapiaFisica>(
					TipoTerapiaFisica.class, 
					this.ui.getiPacientes().obtenerTipos(terapia));
			tablaSesionRehabilitacion.setContainerDataSource(containerTerapiaFisica);
		}else{
			return;
		}
		tablaSesionRehabilitacion.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
		tablaSesionRehabilitacion.setVisibleColumns(new Object[]{"nombre"});
		
		if (tablaSesionRehabilitacion.getContainerDataSource().size() > 0){
			tablaSesionRehabilitacion.setVisible(true);
			tablaSesionRehabilitacion.setPageLength(
			tablaSesionRehabilitacion.getContainerDataSource().size());
		}

	}
	
	public void borrarEvaluacion(EvaluacionDT evaluacionDT){
		this.ui.getiPacientes().borrarEvaluacion(evaluacionDT.getEvaluacion());
		this.evaluacion = null;
		this.tableEvaluacion.getContainerDataSource().removeItem(evaluacionDT);
		this.tableEvaluacion.markAsDirty();
		this.cargarConsultas();
		
	}
	
	public void borrarConsultaTratamiento(ConsultaDT consultaDT){
		this.ui.getiPacientes().borrarConsultaTratamiento(consultaDT.getConsulta());
		this.tableTratamiento.getContainerDataSource().removeItem(consultaDT);
		this.tableTratamiento.markAsDirty();
		this.cargarConsultas();
		
	}
	
}
