package com.fisiosports.web;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.client.ui.calendar.schedule.CalendarEvent;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.event.BasicEvent;

@SuppressWarnings("serial")
@Theme("fisiosports")
public class FisiosportsUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = FisiosportsUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	com.vaadin.ui.Calendar calendarVaadin = new com.vaadin.ui.Calendar();
	

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me now");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
				agregar(layout);
			}
		});
		layout.addComponent(button);
	}

	
	public void agregar(AbstractOrderedLayout layout){
		
		// Have an item
		PropertysetItem item = new PropertysetItem();
		item.addItemProperty("name", new ObjectProperty<String>("Zaphod"));
		item.addItemProperty("age", new ObjectProperty<Integer>(42));		

		// Have some layout and create the fields
		FormLayout form = new FormLayout();
		TextField nameField = new TextField("Name");
		form.addComponent(nameField);
		TextField ageField = new TextField("Age");
		form.addComponent(ageField);
		
		// Now create the binder and bind the fields
		FieldGroup binder = new FieldGroup(item);
		binder.bind(nameField, "name");
		binder.bind(ageField, "age");		

		
		
		layout.addComponent(form);
	}	
}