package com.fisiosports.web.ui.componentes.seguridad;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


public class Login extends VerticalLayout{

	private static final long serialVersionUID = 1L;
	
	private final VerticalLayout loginPanel = new VerticalLayout();
    private VerticalLayout errores = new VerticalLayout();
    private TextField username = new TextField();
    private PasswordField clave = new PasswordField();
    private Label error;
	
	
	public Login(){
        this.setSpacing(true);
        this.setMargin(true);
        final Panel panel = new Panel();
        Responsive.makeResponsive(panel);
        this.addComponent(buildLabels());
        this.addComponent(errores);
        this.addComponent(buildFields());
        panel.setContent(loginPanel);
	}
	
	private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.addStyleName("fields");

        username = new TextField("Usuario");
        username.setIcon(FontAwesome.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        username.focus();
       
        clave = new PasswordField("Contraseña");
        clave.setIcon(FontAwesome.LOCK);
        clave.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final Button signin = new Button("Entrar");
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.setClickShortcut(KeyCode.ENTER);
       

        fields.addComponents(username, clave, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                doLogin();
            }
        });
        return fields;
    }

	private void doLogin() {

		
	}
	
	protected void cargarError(String message) {
        errores.removeAllComponents();
        error = new Label(message);
        error.addStyleName(ValoTheme.LABEL_FAILURE);
        errores.addComponent(error);
    }
	
	private Component buildLabels() {
        VerticalLayout labels = new VerticalLayout();

        Label title = new Label("FisioSports");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H3);
        title.addStyleName(ValoTheme.LABEL_LIGHT);
        labels.addComponent(title);
       
        return labels;
    }
	
}
