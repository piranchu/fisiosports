package com.fisiosports.web.ui.componentes.seguridad;

import com.fisiosports.modelo.entidades.seguridad.Usuario;
import com.fisiosports.web.FisiosportsUI;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginComponent extends CustomComponent{
	
	private static final long serialVersionUID = 1L;
    private VerticalLayout errores = new VerticalLayout();
    private TextField username = new TextField();
    private PasswordField password = new PasswordField();
    private Label error;
    private FisiosportsUI ui;

    public LoginComponent(){
    	ui = (FisiosportsUI) UI.getCurrent();

//    	setSizeFull();
//    	setHeight(100, Unit.PERCENTAGE);

    	VerticalLayout layout = new VerticalLayout();
    	layout.setSizeFull();
//    	layout.setHeight(100, Unit.PERCENTAGE);
    	Panel loginPanel = createLoginPanel();
    	layout.addComponent(loginPanel);
    	layout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    	
    	setCompositionRoot(layout);
        
    }
    
    private Panel createLoginPanel() {
    	
        Panel panel = new Panel();
    	VerticalLayout layout = new VerticalLayout(); 
    	layout.setSpacing(true);
    	layout.setMargin(true);
        Responsive.makeResponsive(panel);
        layout.addComponent(createTitleComponent());
        layout.addComponent(createLoginComponents());
        layout.addComponent(errores);
        panel.setContent(layout);
        panel.setSizeUndefined();

        return panel;
    }
    
    private HorizontalLayout createLoginComponents() {
    	/*
    	 * Creates user & pass textfield, plus button for login
    	 */
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setWidth(100, Unit.PERCENTAGE);;
        fields.addStyleName("fields");

        username = new TextField("Usuario");
        username.setIcon(FontAwesome.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        username.focus();
        
        password = new PasswordField("Contraseña");
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        Button loginButton = createLoginButton();
        fields.addComponents(username, password, loginButton);
        fields.setComponentAlignment(loginButton, Alignment.BOTTOM_LEFT);
        
        return fields;
    }

    private Button createLoginButton(){
    	
        Button button = new Button("entrar");
//        button.setIcon(FontAwesome.SIGN_IN);
        button.addStyleName(ValoTheme.BUTTON_PRIMARY);
        button.setClickShortcut(KeyCode.ENTER);
        button.addClickListener(new ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final ClickEvent event) {
                doLogin();
            }
        });
    	return button;
    }
    
    
    protected void doLogin() {
    	errores.removeAllComponents();
        username.setComponentError(null);
        password.setComponentError(null);
        
        if (username.getValue() == null || username.getValue().trim().equalsIgnoreCase("")){
            username.setComponentError(new UserError("Ingrese usuario"));
            return;
        }
        if (password.getValue() == null || password.getValue().trim().equalsIgnoreCase("")){
        	password.setComponentError(new UserError("Ingrese clave"));
            return;
        }
        Usuario usuario = ui.getiSeguridad().login(
            		username.getValue(), password.getValue());
           
        if (usuario == null) {
            cargarError("Usuario o contraseña incorrectos");
        }else{
        	VaadinSession.getCurrent().setAttribute(Usuario.class, usuario);
        	ui.setMainView();
        }
    }

	protected void cargarError(String message) {
        errores.removeAllComponents();
        error = new Label(message);
        error.addStyleName(ValoTheme.LABEL_FAILURE);
        errores.addComponent(error);
    }

    private Component createTitleComponent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setSizeFull();
        Resource resource = new ThemeResource("img/logo.png");
        Image image = new Image(null, resource);
        image.setWidth(30, Unit.EM);
//        image.setHeight(50, Unit.PERCENTAGE);
        layout.addComponent(image);
        
//    	Label title = new Label("Gestión de centro estético");
//        title.setSizeUndefined();
//        title.addStyleName(ValoTheme.LABEL_H3);
//        title.addStyleName(ValoTheme.LABEL_LIGHT);
//        layout.addComponent(title);
        return layout;
    }
    
}
	