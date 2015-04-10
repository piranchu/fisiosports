package com.fisiosports.web.ui.componentes.pacientes;

import com.fisiosports.modelo.entidades.pacientes.Paciente;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.ui.VerticalLayout;

public class ComponentePacientes extends VerticalLayout{
	
	private static final long serialVersionUID = 1L;
	
	private ComponenteMantenimientoPacientes mantenimientoPacientes;
	
	public ComponentePacientes(final FisiosportsUI ui){
		this.mantenimientoPacientes = new ComponenteMantenimientoPacientes(this);
		setComponentMantenimientoPacientes();
	}
	
	public void setComponentMantenimientoPacientes(){
		this.removeAllComponents();
		mantenimientoPacientes.consultarPacientes();
//		this.setContent(mantenimientoPacientes);
		this.addComponent(mantenimientoPacientes);
	}
	
	public void setComponentEvaluacionPacientes(Paciente paciente){
		this.removeAllComponents();
		ComponenteEvaluacion componenteEvaluacion = new ComponenteEvaluacion(paciente, this);
//		setContent(componenteEvaluacion);
		this.addComponent(componenteEvaluacion);
	}
}