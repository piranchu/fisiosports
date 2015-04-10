package com.fisiosports.web.ui.componentes.caja;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Caja;
import com.fisiosports.modelo.entidades.caja.CierreCaja;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaCerrarCaja extends Window{

	private static final long serialVersionUID = 1L;

	private FisiosportsUI ui;
	private Observer observer;
	private DecimalFormat df;
	{
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
		df =  (DecimalFormat) nf;
		df.applyPattern("###,##0.00");
	}
	private Caja caja;
	
	public VentanaCerrarCaja(Caja caja, Observer observer){
		this.ui = (FisiosportsUI) UI.getCurrent();
		this.observer = observer;
		this.caja = caja;
		this.setCaption("Cierre de caja");
		this.setModal(true);
		
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		Label labelSaldoInicial = new Label("El saldo inicial de caja es: "+
				df.format(caja.getSaldoInicial()));
		layout.addComponent(labelSaldoInicial);		
//		layout.addComponent(this.obtenerTablaCuentas());
		Label labelSaldo = new Label("El saldo final de caja es: "+
				df.format(caja.getSaldo()));
		layout.addComponent(labelSaldo);		
		layout.addComponent(this.obtenerBotonConfirmar());		
		setContent(layout);
				
	}
	
	
//	private Table obtenerTablaCuentas(){
//		Table tabla = new Table(){
//			private static final long serialVersionUID = 1L;
//			@Override
//			protected String formatPropertyValue(Object rowId, Object colId,
//					Property<?> property) {
//				Object v = property.getValue();
//				if (v instanceof Double) {
//					Double value = (Double) v;
//					return df.format(value);
//				}
//				return super.formatPropertyValue(rowId, colId, property);
//			}
//		};
//		List<CuentaFinanciera> cuentas = ui.getiCaja().consultarCuentasFinancieras();
//		BeanItemContainer<CuentaFinanciera> container = new BeanItemContainer<>(CuentaFinanciera.class, cuentas);
//		tabla.setContainerDataSource(container);
//		tabla.setVisibleColumns(new Object[]{
//				"nombre", "descripcion", 
//				"moneda", "saldo"
//		});
//		tabla.setColumnHeaders(new String[]{
//				"nombre", "descripcion", "moneda", "total"
//		});
//		tabla.setColumnAlignment("saldo", Align.RIGHT);
//		
//		Double saldoCaja = 0.0;
//		for (CuentaFinanciera cuenta:cuentas){
//			saldoCaja += cuenta.getSaldo();
//			System.out.println("[VentanaCerrarCaja] \t saldo:"+saldoCaja);
//		}
//		
//		if (tabla.getContainerDataSource().size()>10){
//			tabla.setPageLength(10);
//		}else{
//			tabla.setPageLength(tabla.size());
//		}
//
//		tabla.setColumnFooter("saldo", df.format(saldoCaja));
//		tabla.setFooterVisible(true);
//		
//		return tabla;
//		
//	}
	
	private Button obtenerBotonConfirmar(){
		
		Button button = new Button("", FontAwesome.LOCK);
		button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		button.setDescription("cerrar caja");
		button.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				try {
					CierreCaja cierre = ui.getiCaja().cerrarCaja(caja);
					observer.update(null, cierre);
				}catch (Exception e) {
					e.printStackTrace();
					Notification.show(e.getMessage(),
							Notification.Type.ERROR_MESSAGE);
				}finally{
					close();
				}
				close();
			}			
		});
		return button;
		
	}
	
	
}
