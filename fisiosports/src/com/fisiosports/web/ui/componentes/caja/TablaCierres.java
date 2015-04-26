package com.fisiosports.web.ui.componentes.caja;

import java.util.List;

import com.fisiosports.modelo.entidades.caja.CierreCaja;
import com.fisiosports.web.ui.contenedores.ContenedorCierreCaja;
import com.fisiosports.web.ui.contenedores.beantypes.BeanItemCierreCaja;
import com.vaadin.ui.Table;

public class TablaCierres extends Table{
	

	private static final long serialVersionUID = 1L;
	private List<CierreCaja> cierres;

	public TablaCierres(List<CierreCaja> cierres, ComponenteConsultaCierres componente){
		this.cierres = cierres;
		
		if (this.cierres == null || this.cierres.isEmpty()){
			return;
		}
		ContenedorCierreCaja contenedor = new ContenedorCierreCaja(
				BeanItemCierreCaja.class);

		for(CierreCaja cierre:cierres){
			contenedor.addItem(new BeanItemCierreCaja(cierre, componente));
		}
		this.setContainerDataSource(contenedor);
		this.setVisibleColumns(ContenedorCierreCaja.getVisibleColumns());
		this.setColumnHeaders(ContenedorCierreCaja.getColumnHeaders());
		this.setPageLength(contenedor.size());
		
		
		
		
		
		
		
	}

}
