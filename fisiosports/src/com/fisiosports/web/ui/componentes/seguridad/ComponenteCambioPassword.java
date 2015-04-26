package com.fisiosports.web.ui.componentes.seguridad;

import com.fisiosports.modelo.entidades.seguridad.Usuario;
import com.fisiosports.negocio.ISeguridad;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

public class ComponenteCambioPassword extends CustomComponent{

	private static final long serialVersionUID = 1L;
	
    private PasswordField passwordAnterior = new PasswordField("Contraseña actual");
    private PasswordField passwordNuevo = new PasswordField("Nueva contraseña");
    private PasswordField passwordConfirmacion = new PasswordField("Confirme nueva contraseña");
    
    private ISeguridad seguridad;
	
	public ComponenteCambioPassword(){
	
		seguridad = ((FisiosportsUI)UI.getCurrent()).getiSeguridad();
		
		FormLayout layout = new FormLayout();
		layout.setSpacing(true);
		layout.setMargin(true);
		layout.addComponent(passwordAnterior);
		layout.addComponent(passwordNuevo);
		layout.addComponent(passwordConfirmacion);
		
		Button botonConfirmar = new Button("");
		botonConfirmar.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
		botonConfirmar.setIcon(FontAwesome.SAVE);
		botonConfirmar.setClickShortcut(KeyCode.ENTER);
		botonConfirmar.setClickShortcut(KeyCode.SPACEBAR);
		botonConfirmar.setDescription("guardar datos");
		botonConfirmar.addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				
				Usuario usuario = VaadinSession.getCurrent().getAttribute(Usuario.class);
				
		        if (passwordAnterior.getValue() == null || passwordAnterior.getValue().trim().equalsIgnoreCase("")){
//		        	passwordAnterior.setComponentError(new UserError("Ingrese contraseña anterior"));
					Notification.show("Ingrese contraseña anterior", Type.ERROR_MESSAGE);
		            return;
		        }
		        if (passwordNuevo.getValue() == null || passwordNuevo.getValue().trim().equalsIgnoreCase("")){
//		        	passwordNuevo.setComponentError(new UserError("Ingrese nueva contraseña"));
					Notification.show("Ingrese nueva contraseña", Type.ERROR_MESSAGE);

		            return;
		        }
		        if (passwordConfirmacion.getValue() == null || passwordConfirmacion.getValue().trim().equalsIgnoreCase("")){
//		        	passwordConfirmacion.setComponentError(new UserError("Ingrese contraseña anterior"));
					Notification.show("Ingrese contraseña anterior", Type.ERROR_MESSAGE);
		            return;
		        }
				if (!usuario.getPass().equals(passwordAnterior.getValue())){
//		        	passwordAnterior.setComponentError(new UserError("Contraseña incorrecta"));
					Notification.show("Contraseña incorrecta", Type.ERROR_MESSAGE);
					return;
				}
				if (!passwordNuevo.getValue().equals(passwordConfirmacion.getValue())){
//					passwordConfirmacion.setComponentError(new UserError("Confirmación de contraseña incorrecta"));
					Notification.show("Confirmación de contraseña incorrecta", Type.ERROR_MESSAGE);
					return;
				}
				usuario.setPass(passwordNuevo.getValue());
				seguridad.modificarUsuario(usuario);
				VaadinSession.getCurrent().setAttribute(Usuario.class, usuario);
				passwordAnterior.setValue("");
				passwordNuevo.setValue("");
				passwordConfirmacion.setValue("");
				Notification.show("Contraseña actualizada");
				
			}	
		});
		layout.addComponent(botonConfirmar);
		setCompositionRoot(layout);
		
	}
	
	
	
}
