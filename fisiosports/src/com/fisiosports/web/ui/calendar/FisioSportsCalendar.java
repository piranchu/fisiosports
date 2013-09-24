package com.fisiosports.web.ui.calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import com.fisiosports.web.ui.componentes.VentanaConsulta;
import com.vaadin.client.ui.VCalendar.EventClickListener;
import com.vaadin.client.ui.calendar.schedule.CalendarEvent;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table.TableTransferable;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClick;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.EventClickHandler;
import com.vaadin.ui.components.calendar.CalendarTargetDetails;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.DateClickEvent;
import com.vaadin.ui.components.calendar.CalendarComponentEvents.MoveEvent;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.vaadin.ui.components.calendar.handler.BasicDateClickHandler;
import com.vaadin.ui.components.calendar.handler.BasicEventMoveHandler;

public class FisioSportsCalendar extends Calendar{

	private static final long serialVersionUID = 1L;
	private Calendar calendar;

	public FisioSportsCalendar(final UI ui) {

		this.calendar = this;
		this.setFirstVisibleHourOfDay(8);
		this.setFirstVisibleDayOfWeek(1);
		this.setLastVisibleDayOfWeek(6);
		this.setSizeFull();
		this.setEventProvider(new FisioSportsCalendarProvider());

		GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
		cal.setTime(new Date());
		cal.set(cal.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		this.setStartDate(cal.getTime());
		cal.add(cal.DAY_OF_WEEK, 6);
		this.setEndDate(cal.getTime());
//		System.out.println("[FisioSportsCalendar] start:"+this.getStartDate());
//		System.out.println("[FisioSportsCalendar] end:"+this.getEndDate());


		this.setHandler(new BasicEventMoveHandler() {
			private static final long serialVersionUID = 1L;
			public void eventMove(MoveEvent event) {
				/*
				System.out.println("[event] event.getCalendarEvent().getClass().getCanonicalName():"
								+ event.getCalendarEvent().getClass().getCanonicalName());
				 */
				super.eventMove(event);
			}
			protected void setDates(EditableCalendarEvent event, Date start,
					Date end) {
				/*
				System.out.println("[event] set dates");
				System.out.println("[event] 	start:" + start);
				System.out.println("[event] 	end" + end);
				*/
				super.setDates((EditableCalendarEvent) event, start, end);
			}
		});

		this.setDropHandler(new DropHandler() {
			public void drop(DragAndDropEvent event) {
				CalendarTargetDetails details = (CalendarTargetDetails) event
						.getTargetDetails();
				TableTransferable transferable = (TableTransferable) event
						.getTransferable();
			}

			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}
		});

		this.setHandler(new EventClickHandler() {
			@Override
			public void eventClick(EventClick event) {
				FisioSportsCalendarEvent fisioSportsEvent = (FisioSportsCalendarEvent) event.getCalendarEvent();
//				System.out.println("[FisioSportsCalendar.EventClickHandler] fisioSportsEvent:"+fisioSportsEvent.getCaption());
				Window window = new VentanaConsulta(calendar, fisioSportsEvent.getConsulta());
				ui.addWindow(window);
				//Notification.show(consulta.getCaption());
			}
		});

		this.setHandler(new BasicDateClickHandler() {
			public void dateClick(DateClickEvent event) {
				Calendar cal = event.getComponent();
				long currentCalDateRange = cal.getEndDate().getTime()
						- cal.getStartDate().getTime();
				if (currentCalDateRange < (24 * 60 * 60 * 1000)) {
					// Change the date range to the current week
					java.util.Calendar calendarEvent = GregorianCalendar
							.getInstance();
					calendarEvent.setTime(event.getDate());
					calendarEvent.set(java.util.Calendar.DAY_OF_WEEK,
							calendarEvent.getFirstDayOfWeek());
					cal.setStartDate(calendarEvent.getTime());
					calendarEvent.set(java.util.Calendar.DAY_OF_WEEK,
							calendarEvent.getFirstDayOfWeek() + 6);
					cal.setEndDate(calendarEvent.getTime());
				} else {
					// Default behaviour, change date range to one day
					super.dateClick(event);
				}
			}
		});
	}

}
