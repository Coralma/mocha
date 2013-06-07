/**
 * 
 */
package com.coral.vaadin.widget.component;

import java.util.List;

import com.coral.foundation.jpa.search.CommonSearchDao;
import com.coral.foundation.jpa.search.SearchFilter;
import com.coral.foundation.jpa.search.SearchFilterBuilder;
import com.coral.foundation.jpa.search.SearchFilterFactory;
import com.coral.foundation.spring.bean.SpringContextUtils;
import com.coral.foundation.utils.StrUtils;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.component.GlobleSearchWidget.GlobleSearchListener;
import com.google.common.collect.Lists;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class CommonEntitySearchWindow extends Window {

	private CommonSearchDao commonSearchDao = SpringContextUtils.getBean("commonSearchDao", CommonSearchDao.class);
	private String[] searchFields;
	private Class entityClass;
	private VerticalLayout layout = new VerticalLayout();
	private VerticalLayout resultLayout = new VerticalLayout();
	private Button selectBtn = WidgetFactory.createButton("Select");
	private Button cancelBtn = WidgetFactory.createButton("Cancel");
	private SearchResultCard selectedCard;
	private List<SearchResultCard> cards = Lists.newArrayList();
	
	public CommonEntitySearchWindow(String caption, Class entityClass, String[] searchFields) {
		this.setCaption(caption);
		this.center();
		this.addStyleName("mocha-app");
		this.setWidth("500px");
		this.setClosable(true);
		this.setResizeLazy(true);
		this.setResizable(false);
		this.setModal(true);
		this.addStyleName(Reindeer.WINDOW_LIGHT);
		this.searchFields = searchFields;
		this.entityClass = entityClass;
	}
	
	public void attach() {
		layout.setMargin(true);
		layout.setSpacing(true);
		GlobleSearchWidget globleSearchWidget = new GlobleSearchWidget();
		globleSearchWidget.setTextFieldWidth("420px");
		globleSearchWidget.setWidth("460px");
		globleSearchWidget.setListener(new GlobleSearchListener() {
			@Override
			public void search(String condition) {
				if(!StrUtils.isEmpty(condition)) {
					cards.clear();
					List<Object> results = commonSearchDao.searchByFilter(buildFilterBuilder(condition));
					buildResultLayout(results);
					setSelectedCard(null);
				}
			}
		});
		layout.addComponent(globleSearchWidget);
		resultLayout.setSpacing(true);
		layout.addComponent(resultLayout);
		
		selectBtn.setVisible(false);
		HorizontalLayout btnLayout = new HorizontalLayout();
		btnLayout.setSpacing(true);
		btnLayout.addComponent(selectBtn);
		btnLayout.addComponent(cancelBtn);
		layout.addComponent(btnLayout);
		layout.setComponentAlignment(btnLayout,Alignment.MIDDLE_RIGHT);
		this.setContent(layout);
		
		bind();
	}
	
	public void buildResultLayout(List<Object> results) {
		resultLayout.removeAllComponents();
		for(Object result : results) {
			SearchResultCard card = new SearchResultCard(result);
			cards.add(card);
			resultLayout.addComponent(card);
		}
	}
	
	public SearchFilterBuilder buildFilterBuilder(String condition) {
		SearchFilterBuilder filterBuilder = SearchFilterFactory.buildFuzzySearchFilter(entityClass);
		for(String searchField : searchFields) {
			filterBuilder.getSearchFilters().add(SearchFilter.like(searchField, condition));
		}
		return filterBuilder;
	}
	
	public void bind() {
		selectBtn.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				CommonEntitySearchWindow.this.close();
			}
		});
		cancelBtn.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				setSelectedCard(null);
				CommonEntitySearchWindow.this.close();
			}
		});
	}
	
	public void setSelectedCard(SearchResultCard card) {
		if(card == null) {
			selectBtn.setVisible(false);
		} else {
			selectBtn.setVisible(true);
		}
		this.selectedCard = card;
	}
	
	public class SearchResultCard extends VerticalLayout implements LayoutClickListener {
		private Object entity;
		public SearchResultCard(Object entity) {
			this.entity = entity;
			this.setStyleName("search-result-card");
			this.setWidth("450px");
			this.addListener(this);
		}
		
		public void attach() {
			String showText = "";
			for(String searchField : searchFields) {
				Property property = new NestedMethodProperty(entity, searchField);
				showText += property.getValue() + "  ";
			}
			Label resultLabel = new Label(showText);
			this.addComponent(resultLabel);
		}

		@Override
		public void layoutClick(LayoutClickEvent event) {
			for(SearchResultCard card : cards) {
				if(card.equals(event.getComponent())) {
					card.setStyleName("search-result-card-selected");
				} else {
					card.setStyleName("search-result-card");
				}
			}
			setSelectedCard(this);
		}

		/**
		 * @return the entity
		 */
		public Object getEntity() {
			return entity;
		}
	}

	/**
	 * @return the selectedCard
	 */
	public Object getEntity() {
		if(selectedCard != null) {
			return selectedCard.getEntity();
		}
		return null;
	}
}
