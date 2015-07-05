package com.fisiosports.web.ui.componentes.caja;

import java.util.Observer;

import com.fisiosports.modelo.entidades.caja.Caja;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class VentanaAbrirCaja extends Window{

	private static final long serialVersionUID = 1L;

	private FisiosportsUI ui;
	private Observer observer;
//	private TextField saldoInicial; 
	private Caja caja;
	
	
	public VentanaAbrirCaja(Caja caja, Observer observer){
		this.ui = (FisiosportsUI) UI.getCurrent();
		this.observer = observer;
		this.caja = caja;
		this.setCaption("Apertura de caja");
		this.setModal(true);
		
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		
//		saldoInicial = new TextField();
//		saldoInicial.setInputPrompt("saldo inicial");
//		
//		layout.addComponent(saldoInicial);
		layout.addComponent(this.obtenerBotonConfirmar());
		
		setContent(layout);
		
		
	}
	
	
	
	private Button obtenerBotonConfirmar(){
		
		Button button = new Button("", FontAwesome.UNLOCK);
		button.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		button.setDescription("abrir caja");
		button.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
//				if (saldoInicial.getValue() != null){
//					try{
//						saldo = Double.parseDouble(saldoInicial.getValue());
//					}catch (Exception e){
//						e.printStackTrace();
//					}
//				}
				try {
					caja = ui.getiCaja().abrirCaja(caja);
					observer.update(null, caja);
				}catch (Exception e) {
					e.printStackTrace();
					Notification.show("No se pudo abrir la caja:"+e.getMessage(),
							Notification.Type.ERROR_MESSAGE);
				}finally{
					close();
				}
			}			
		});
		return button;
		
	}
	
	
}
