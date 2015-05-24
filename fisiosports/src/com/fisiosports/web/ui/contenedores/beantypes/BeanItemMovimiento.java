package com.fisiosports.web.ui.contenedores.beantypes;

import java.util.LinkedList;
import java.util.List;

import org.vaadin.dialogs.ConfirmDialog;

import com.fisiosports.modelo.entidades.caja.Egreso;
import com.fisiosports.modelo.entidades.caja.Ingreso;
import com.fisiosports.modelo.entidades.caja.Movimiento;
import com.fisiosports.web.ui.componentes.caja.ComponenteCaja;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class BeanItemMovimiento {

	private Button icon = new Button("");
	private Movimiento movimiento;
	private Button deleteButton = new Button("");
//	private ComponenteCaja componente;
	
	public BeanItemMovimiento(Movimiento movimiento, final ComponenteCaja componente){
		this.movimiento = movimiento;
//		this.componente = componente;
		
		icon.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		if (movimiento instanceof Ingreso){
			icon.setDescription("ingreso");
			icon.setIcon(FontAwesome.ARROW_CIRCLE_DOWN);
		}else if (movimiento instanceof Egreso){
			icon.setDescription("egreso");
			icon.setIcon(FontAwesome.ARROW_CIRCLE_UP);
			icon.addStyleName("redicon");
		}
		deleteButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		deleteButton.setIcon(FontAwesome.TRASH_O);
		deleteButton.addClickListener(new ClickListener(){
			private static final long serialVersionUID = 1L;
			@Override
			public void buttonClick(ClickEvent event) {
				ConfirmDialog.show(UI.getCurrent(), "Confirma eliminación del movimiento?",
				        new ConfirmDialog.Listener() {

							private static final long serialVersionUID = 1L;

							@Override
							public void onClose(ConfirmDialog dialog) {
				                if (dialog.isConfirmed()) {
				    				componente.borrarMovimiento(BeanItemMovimiento.this);
				                } else {
				                    // User did not confirm
									// CANCEL STUFF
				                }
				            }
				        });

			}
			
		});
		
	}
	
	static public List<BeanItemMovimiento> buildList(List<? extends Movimiento> listMovimiento, ComponenteCaja componente){
		
		List<BeanItemMovimiento> list = new LinkedList<>();
		for (Movimiento movimiento:listMovimiento){
			BeanItemMovimiento movDT = new BeanItemMovimiento(movimiento, componente);
			list.add(movDT);
		}
		
		return list;
		
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	public Button getIcon() {
		return icon;
	}

	public void setIcon(Button icon) {
		this.icon = icon;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}
	
	
}
