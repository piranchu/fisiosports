package com.fisiosports.web.ui.componentes;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;

public class FisioSportsTitulo extends HorizontalLayout{

	private static final long serialVersionUID = 1L;
	private Embedded embeddedImage;
	
	public FisioSportsTitulo(){
		this.setSizeFull();
		this.setMargin(new MarginInfo(false, true, false, true));
		embeddedImage = new Embedded();
		embeddedImage.setImmediate(false);
		embeddedImage.setWidth("80%");
		embeddedImage.setHeight("70px");
		embeddedImage.setSource(new ThemeResource("img/logo.png"));
		embeddedImage.setType(1);
		embeddedImage.setMimeType("image/png");
		this.addComponent(embeddedImage);
		this.setComponentAlignment(embeddedImage, Alignment.MIDDLE_CENTER);
	}
	
}
