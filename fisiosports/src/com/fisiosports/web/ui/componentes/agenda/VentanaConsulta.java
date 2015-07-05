package com.fisiosports.web.ui.componentes.agenda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Observable;
import java.util.Observer;

import com.fisiosports.modelo.entidades.agenda.AgendaConsulta;
import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.web.FisiosportsUI;
import com.fisiosports.web.ui.componentes.pacientes.ComponenteEvaluacion;
import com.fisiosports.web.ui.componentes.pacientes.VentanaSeleccionPaciente;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaConsulta extends Window implements Observer{

	private static final long serialVersionUID = 1L;

	private FisiosportsUI ui;
	private VerticalLayout content = new VerticalLayout();
	private AgendaConsulta agendaConsulta;
	private TextField nombrePaciente;
	private Button botonSeleccionPaciente;
	private Paciente paciente;
	private Button botonInfoPaciente;
	private PopupDateField start;
	private CheckBox masajes;
	private CheckBox terapiaFisica;
	private CheckBox gimnasio;
	private CheckBox deportologo;
	private CheckBox nutricionista;
	private CheckBox traumatologo;
	private CheckBox psicologo;
	private Calendar calendar;
	private TextArea observaciones;
	
	//private PopupDateField otraFecha;


	// Se crea cuando se selecciona la agenda
	public VentanaConsulta(FisiosportsUI ui, Calendar calendar) {
		this.calendar = calendar;
		this.ui = ui;
		this.initComponents();
		content.addComponent(this.createMenuBar(null));
	}

	// Se crea para una fecha en particular
	public VentanaConsulta(FisiosportsUI ui, Calendar calendar, Date startDate) {
		this.calendar = calendar;
		this.ui = ui;
		this.initComponents();
		this.start.setValue(startDate);
		content.addComponent(this.createMenuBar(null));
	}

	// Se crea para una consulta en particular (se pueden modificar los datos)
	public VentanaConsulta(FisiosportsUI ui, Calendar calendar, AgendaConsulta agendaConsulta) {
		this.calendar = calendar;
		this.agendaConsulta = agendaConsulta;
		this.ui = ui;
		this.initComponents();
		cargarDatosConsulta(agendaConsulta);
		content.addComponent(this.createMenuBar(agendaConsulta));
		botonSeleccionPaciente.setVisible(false);
		this.botonInfoPaciente.setVisible(true);
	}
	
	public Button obtenerBotonInfoPaciente(){
		Button boton = new Button("", FontAwesome.INFO_CIRCLE);
		boton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		boton.setDescription("evaluaciones del paciente");
		boton.setVisible(false);
		boton.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Window window = new Window ("Evaluaciones");
				window.setContent(new ComponenteEvaluacion(paciente, null));
				window.setModal(true);
				VentanaConsulta.this.ui.addWindow(window);
			}
		});
		return boton;		
	}
	
	public Button obtenerBotonSeleccionPaciente(){
		Button boton = new Button("", FontAwesome.SEARCH);
		boton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		boton.setDescription("seleccionar paciente");
		boton.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				VentanaSeleccionPaciente window = new VentanaSeleccionPaciente (
						VentanaConsulta.this.ui.getiPacientes(), 
						VentanaConsulta.this); 
				VentanaConsulta.this.ui.addWindow(window);
			}
		});
		return boton;		
	}
	
	public void setPaciente(Paciente paciente){
		if (paciente == null) return;
		this.paciente = paciente;
		this.nombrePaciente.setReadOnly(false);
		this.nombrePaciente.setValue(
						paciente.getNombre() + " " + 
						paciente.getApellido() + " ("+
						paciente.getTelefono()+")");
		this.nombrePaciente.setReadOnly(true);
		this.botonInfoPaciente.setVisible(true);
		this.nombrePaciente.markAsDirty();
		this.markAsDirtyRecursive();
	}

	private void initComponents(){
		this.center();
		this.setImmediate(true);
		this.setSizeUndefined();
		this.setCaption("Agendar horarios");
		this.setContent(content);
		this.setResizable(false);
		this.setModal(true);

		content.setMargin(true);
		content.setSpacing(true);
		
		HorizontalLayout layoutNombrePaciente = new HorizontalLayout();
		
		this.nombrePaciente = new TextField();
		this.nombrePaciente.setWidth(30, Unit.EX);
		this.nombrePaciente.setReadOnly(true);
		layoutNombrePaciente.addComponent(nombrePaciente);
		
		botonSeleccionPaciente = this.obtenerBotonSeleccionPaciente(); 
		layoutNombrePaciente.addComponent(botonSeleccionPaciente);
		botonInfoPaciente = obtenerBotonInfoPaciente();
		layoutNombrePaciente.addComponent(botonInfoPaciente);
		
		content.addComponent(layoutNombrePaciente);
		
		HorizontalLayout hl = new HorizontalLayout();
		VerticalLayout vl1 = new VerticalLayout();
		vl1.setMargin(new MarginInfo(false, true, false, false));
		VerticalLayout vl2 = new VerticalLayout();
		
		this.masajes = new CheckBox();
		this.masajes.setCaption("masajes");
		vl1.addComponent(masajes);
		this.terapiaFisica = new CheckBox();
		this.terapiaFisica.setCaption("terapia física");
		vl1.addComponent(terapiaFisica);
		this.gimnasio = new CheckBox();
		this.gimnasio.setCaption("gimnasio");
		vl1.addComponent(gimnasio);
		this.deportologo = new CheckBox();
		this.deportologo.setCaption("deportologo");
		vl2.addComponent(deportologo);
		this.nutricionista = new CheckBox();
		this.nutricionista.setCaption("nutricionista");
		vl2.addComponent(nutricionista);
		this.traumatologo = new CheckBox();
		this.traumatologo.setCaption("traumatólogo");
		vl2.addComponent(traumatologo);
		this.psicologo = new CheckBox();
		this.psicologo.setCaption("psicólogo");
		vl2.addComponent(psicologo);
		
		hl.addComponent(vl1);
		hl.addComponent(vl2);
		
		content.addComponent(hl);
		
		this.observaciones = new TextArea("Observaciones");
		observaciones.setSizeFull();
		content.addComponent(observaciones);
		this.start = new PopupDateField();
		this.start.setCaption("Indicar fecha/hora para agendar");
		this.start.setResolution(Resolution.MINUTE);
		this.start.setImmediate(true);
		content.addComponent(start);
	}
	
	public void cargarDatosConsulta(AgendaConsulta agendaConsulta){
		this.nombrePaciente.setReadOnly(false);
		this.paciente = agendaConsulta.getPaciente();
		this.nombrePaciente.setValue(paciente.getNombre() + " " + paciente.getApellido() + " ("+paciente.getTelefono()+")");
		this.nombrePaciente.setReadOnly(true);
		this.gimnasio.setValue(agendaConsulta.getGimnasio());
		this.masajes.setValue(agendaConsulta.getMasajes());
		this.terapiaFisica.setValue(agendaConsulta.getTerapiaFisica());
		this.start.setValue(agendaConsulta.getStart());
		this.deportologo.setValue(agendaConsulta.getDeportologo());
		this.traumatologo.setValue(agendaConsulta.getTraumatologo());
		this.psicologo.setValue(agendaConsulta.getPsicologo());
		this.nutricionista.setValue(agendaConsulta.getNutricionista());
		this.observaciones.setValue(agendaConsulta.getObservaciones());
		
	}

	private void cargarConsulta(){
		if (agendaConsulta==null){
			agendaConsulta = new AgendaConsulta();
		}
		agendaConsulta.setCaption(this.nombrePaciente.getValue());
		agendaConsulta.setStart(start.getValue());
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar
				.getInstance();
		gc.setTime(start.getValue());
		gc.add(GregorianCalendar.HOUR, 1);
		agendaConsulta.setEnd(gc.getTime());
		agendaConsulta.setPaciente(this.paciente);
		agendaConsulta.setMasajes(masajes.getValue());
		agendaConsulta.setGimnasio(gimnasio.getValue());
		agendaConsulta.setTerapiaFisica(terapiaFisica.getValue());
		agendaConsulta.setDeportologo(deportologo.getValue());
		agendaConsulta.setTraumatologo(traumatologo.getValue());
		agendaConsulta.setPsicologo(psicologo.getValue());
		agendaConsulta.setNutricionista(nutricionista.getValue());
		agendaConsulta.setObservaciones(observaciones.getValue());
	}
	
	
	private MenuBar createMenuBar(AgendaConsulta consulta){
		MenuBar menu = new MenuBar();
		
		if (consulta == null){
			MenuItem addItem = menu.addItem("", 
					FontAwesome.SAVE, 
					this.addCommand);
			addItem.setDescription("Guardar datos");
			
		}else{
			MenuItem modItem = menu.addItem("", 
					FontAwesome.SAVE, 
					this.modifyCommand);
			modItem.setDescription("Guardar datos");
			
			MenuItem copyItem = menu.addItem("", 
					FontAwesome.COPY, 
					this.copyCommand);
			copyItem.setDescription("copiar consulta");
			
			MenuItem delItem = menu.addItem("", 
					FontAwesome.TRASH_O, 
					this.deleteCommand);
			delItem.setDescription("Eliminar consulta");
		}
		

		menu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		menu.addStyleName(ValoTheme.TABSHEET_FRAMED);
		menu.setSizeFull();
		return menu;
	}
	
	private Command addCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			try {
				if (VentanaConsulta.this.paciente == null) {
					Notification.show("Debe indicar paciente",
							Notification.Type.WARNING_MESSAGE);
					return;
				}
				cargarConsulta();
				Notification.show("Se agendó la consulta", Notification.Type.HUMANIZED_MESSAGE);
				//paciente.getConsultasAgendadas().add(agendaConsulta);
				ui.getiAgenda().agregarConsulta(agendaConsulta);
				calendar.markAsDirty();
				close();
			} catch (Exception e) {
				e.printStackTrace();
				Notification.show("Error al intentar agendar la consulta:", e.getMessage(),
						Notification.Type.ERROR_MESSAGE);
				return;
			}
		}
	};

	private Command modifyCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			try {
				cargarConsulta();
				ui.getiAgenda().modificarConsulta(agendaConsulta);
			} catch (Exception e) {
				e.printStackTrace();
				Notification.show("Error al intentar modificar los datos de la consulta", e.getMessage(),
						Notification.Type.ERROR_MESSAGE);
				return;
			}
			calendar.markAsDirty();
			Notification.show("Se modificaron los datos");
			close();
		}
	};

	private Command deleteCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			try {
				ui.getiAgenda().borrarConsulta(agendaConsulta);
			} catch (Exception e) {
				e.printStackTrace();
				Notification.show("Error al intentar borrar la consulta de la agenda", e.getMessage(),
						Notification.Type.ERROR_MESSAGE);
				return;
			}
			calendar.markAsDirty();
			Notification.show("Se eliminó la consulta");
			close();
		}
	};
	
	private Command copyCommand = new Command(){
		private static final long serialVersionUID = 1L;
		@Override
		public void menuSelected(MenuItem selectedItem) {
			if (start.getValue().equals(VentanaConsulta.this.agendaConsulta.getStart())){
				Notification.show("Debe seleccionar otra fecha.", Notification.Type.WARNING_MESSAGE);
				return;
			}
			AgendaConsulta otraConsulta = new AgendaConsulta();
			otraConsulta.setCaption(nombrePaciente.getValue());
			otraConsulta.setStart(start.getValue());
			GregorianCalendar gc = (GregorianCalendar) GregorianCalendar
					.getInstance();
			gc.setTime(start.getValue());
			gc.add(GregorianCalendar.HOUR, 1);
			otraConsulta.setEnd(gc.getTime());
			otraConsulta.setPaciente(paciente);
			otraConsulta.setMasajes(masajes.getValue());
			otraConsulta.setGimnasio(gimnasio.getValue());
			otraConsulta.setTerapiaFisica(terapiaFisica.getValue());
			otraConsulta.setDeportologo(deportologo.getValue());
			otraConsulta.setTraumatologo(traumatologo.getValue());
			otraConsulta.setPsicologo(psicologo.getValue());
			otraConsulta.setNutricionista(nutricionista.getValue());
			otraConsulta.setObservaciones(observaciones.getValue());
			VentanaConsulta.this.ui.getiAgenda().agregarConsulta(otraConsulta);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:ss");
			String fechaString = df.format(start.getValue());
			Notification notification = new Notification("Se copió la consulta para la siguiente fecha/hora: "+fechaString);
			notification.setDelayMsec(3000);
			notification.show(ui.getPage());
			calendar.markAsDirty();
		}
	};

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Paciente){
			this.setPaciente((Paciente)arg1);
		}
		
	}
	

}
