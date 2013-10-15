package com.fisiosports.web.ui.componentes.pacientes;

import java.util.LinkedList;
import java.util.List;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.modelo.entidades.ConsultaEspecialista;
import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.modelo.entidades.SesionRehabilitacion;
import com.fisiosports.web.ui.contenedores.ContenedorSesionRehabilitacion;
import com.fisiosports.web.ui.contenedores.beantypes.SesionDT;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class ComponenteEvaluacion extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private Table tableTratamiento;
	private ContenedorSesionRehabilitacion contenedorSesiones;

	public ComponenteEvaluacion(Paciente paciente) {

		this.setMargin(true);
		this.paciente = paciente;

		// HorizontalLayout hl1 = new HorizontalLayout();
		Label labelEvaluacion = new Label("Evaluación " + paciente.getNombre()
				+ " " + paciente.getApellido());
		labelEvaluacion.setSizeFull();
		labelEvaluacion.setStyleName("titulo-1");
		this.addComponent(labelEvaluacion);

		Label labelDiagnóstico = new Label("Diagnóstico");
		labelDiagnóstico.setSizeFull();
		labelDiagnóstico.setStyleName("titulo-2");
		this.addComponent(labelDiagnóstico);
		TextArea textAreaDiagnostico = new TextArea();
		textAreaDiagnostico.setValue(paciente.getEvaluacionInicial()
				.getDiagnostico());
		this.addComponent(textAreaDiagnostico);

		Label labelIndicaciones = new Label("Indicaciones");
		labelIndicaciones.setSizeFull();
		labelIndicaciones.setStyleName("titulo-2");
		this.addComponent(labelIndicaciones);
		TextArea textAreaIndicaciones = new TextArea();
		textAreaIndicaciones.setValue(paciente.getEvaluacionInicial()
				.getIndicaciones());
		this.addComponent(textAreaIndicaciones);

		Label labelTratamiento = new Label("Tratamiento");
		labelTratamiento.setSizeFull();
		labelTratamiento.setStyleName("titulo-2");
		this.addComponent(labelTratamiento);

		this.tableTratamiento = new Table();
		this.contenedorSesiones = new ContenedorSesionRehabilitacion(SesionDT.class, getConsultas(paciente.getEvaluacionInicial()
				.getTratamiento().getConsultas()));
		this.tableTratamiento.setContainerDataSource(contenedorSesiones);

	}

	private List<SesionDT> getConsultas(List<Consulta> consultas) {
		List<SesionDT> listaConsultas = new LinkedList<SesionDT>();
		for (Consulta consulta : consultas) {

			SesionDT sesion = new SesionDT();
			sesion.setConsulta(consulta);
			if (consulta instanceof SesionRehabilitacion) {
				SesionRehabilitacion sesionRehabilitacion = (SesionRehabilitacion) consulta;
				sesion.setGimnasio(sesionRehabilitacion.getGimnasio() != null);
				sesion.setMasajes(sesionRehabilitacion.getMasaje() != null);
				sesion.setTerapiaFisica(sesionRehabilitacion.getTerapiaFisica() != null);
			}
			if (consulta instanceof ConsultaEspecialista) {
				ConsultaEspecialista especialista = (ConsultaEspecialista) consulta;
				sesion.setDeportologo(especialista.getDeportologo());
				sesion.setNutricionista(especialista.getNutricionista());
				sesion.setTraumatologo(especialista.getTraumatologo());
			}
			listaConsultas.add(sesion);
		}
		return listaConsultas;
	}

}
