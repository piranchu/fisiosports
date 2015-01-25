package com.fisiosports.web.ui.componentes.pacientes;

import com.fisiosports.modelo.entidades.Consulta;
import com.fisiosports.modelo.entidades.Gimnasio;
import com.fisiosports.modelo.entidades.TerapiaFisica;
import com.fisiosports.modelo.tipos.TipoGimnasio;
import com.fisiosports.modelo.tipos.TipoTerapiaFisica;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnHeaderMode;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class VentanaSesionRehabilitacion extends Window {
	
	private static final long serialVersionUID = 1L;

	public VentanaSesionRehabilitacion(Consulta consulta){
		this.setModal(true);
		this.setResizable(false);
		VerticalLayout layout = new VerticalLayout();
		Table tabla = new Table();
		BeanItemContainer<TipoGimnasio> containerGimansio;
		BeanItemContainer<TipoTerapiaFisica> containerTerapiaFisica;
		
		if (consulta instanceof Gimnasio){
			Gimnasio gimnasio = (Gimnasio) consulta;
			containerGimansio = new BeanItemContainer<TipoGimnasio>(
					TipoGimnasio.class, 
					gimnasio.getTipos());
			tabla.setContainerDataSource(containerGimansio);
			tabla.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
			tabla.setVisibleColumns(new Object[]{"nombre"});			
		}else if (consulta instanceof TerapiaFisica){
			TerapiaFisica terapia = (TerapiaFisica) consulta;
			containerTerapiaFisica = new BeanItemContainer<TipoTerapiaFisica>(
					TipoTerapiaFisica.class, 
					terapia.getTipos());
			tabla.setContainerDataSource(containerTerapiaFisica);
			tabla.setColumnHeaderMode(ColumnHeaderMode.HIDDEN);
			tabla.setVisibleColumns(new Object[]{"nombre"});			
		}else{
			close();
		}
		tabla.setPageLength(tabla.size());
		layout.addComponent(tabla);
		setContent(layout);
		
	}	

}
