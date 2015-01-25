package com.fisiosports.web.ui.componentes.pacientes;

import com.fisiosports.modelo.entidades.Paciente;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.ui.Panel;

public class ComponentePacientes extends Panel{
	
	private static final long serialVersionUID = 1L;
	
	private ComponenteMantenimientoPacientes mantenimientoPacientes;
	
	public ComponentePacientes(final FisiosportsUI ui){
		this.mantenimientoPacientes = new ComponenteMantenimientoPacientes(this);
		setComponentMantenimientoPacientes();
	}
	
	public void setComponentMantenimientoPacientes(){
		mantenimientoPacientes.consultarPacientes();
		this.setContent(mantenimientoPacientes);
	}
	
	public void setComponentEvaluacionPacientes(Paciente paciente){
		ComponenteEvaluacion componenteEvaluacion = new ComponenteEvaluacion(paciente, this);
		setContent(componenteEvaluacion);
	}
}