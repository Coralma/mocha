package com.mocha.vaadin.entity.view;

import java.util.List;

import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.view.template.sat.panel.impl.DefaultSectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityDisplayPanel;
import com.coral.vaadin.view.template.sat.panel.impl.ListSectionPanel;
import com.coral.vaadin.view.template.sat.panel.impl.SingleColumnSectionPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.WidgetFactory;
import com.coral.vaadin.widget.fields.FieldFactory;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.ib.model.InsuranceCustomer;
import com.mocha.ib.model.Policy;
import com.vaadin.data.Property;
import com.vaadin.data.util.NestedMethodProperty;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class InsCustomerDisplay extends EntityDisplayPanel implements Viewer {

  public InsCustomerDisplay() {
    super();
  }

  public void build() {
    DisplayNavigatorPanel displayNavigatorPanel = new DisplayNavigatorPanel();
    this.addComponent(displayNavigatorPanel);

    SingleColumnSectionPanel layout = new SingleColumnSectionPanel();
    Property nameProperty = new NestedMethodProperty(getEntity(), "name");
    Label title = WidgetFactory.createLabel(null);
    title.setStyleName("display-title");
    title.setPropertyDataSource(nameProperty);
    layout.addComponent(title);

    HorizontalLayout btnLayout = new HorizontalLayout();
    btnLayout.addStyleName("show-detail-button");
    final Button showDetailBtn = WidgetFactory.createLink("Show Detail");
    showDetailBtn.setIcon(new ThemeResource("icons/arrow-collapse.png"));
    showDetailBtn.addListener(new ClickListener() {
      /**
			 * 
			 */
      private static final long serialVersionUID = 1L;

      @Override
      public void buttonClick(ClickEvent event) {
        if (viewPanel.isVisible()) {
          showDetailBtn.setCaption("Show Detail");
          showDetailBtn.setIcon(new ThemeResource("icons/arrow-collapse.png"));
          viewPanel.setVisible(false);
        } else {
          showDetailBtn.setCaption("Hide Detail");
          showDetailBtn.setIcon(new ThemeResource("icons/arrow-expand.png"));
          viewPanel.setVisible(true);
        }
      }
    });
    btnLayout.addComponent(showDetailBtn);
    layout.addComponent(createFieldWidget(FieldStatus.create().setLabel("Customer Type:")
        .setPath("customerType").setType("String").setCodeTableName("ins-customer-type")));
    layout.addComponent(createFieldWidget(FieldStatus.create().setLabel("Status:")
        .setPath("status").setType("String").setCodeTableName("ins-customer-status")));
    layout.addComponent(createFieldWidget(FieldStatus.create().setLabel("Mobile:")
        .setPath("mobile").setType("String")));
    layout.addComponent(createFieldWidget(FieldStatus.create().setLabel("Email:").setPath("email")
        .setType("String")));
    layout.addComponent(btnLayout);
    this.addComponent(layout);

    final IViewPanel viewPanel = createViewPanel();
    viewPanel.setVisible(false);
    ISectionPanel sectionPanel;
    FieldStatus fieldStatus;

    sectionPanel = createSectionPanel("customerStatus");
    sectionPanel.setLabel("Customer Status");
    viewPanel.addSection(sectionPanel);

    fieldStatus =
        FieldStatus.create().setLabel("Customer Type").setPath("customerType").setType("String")
            .setCodeTableName("ins-customer-type");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Status").setPath("status").setType("String")
            .setCodeTableName("ins-customer-status");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    sectionPanel = createSectionPanel("indiCustInfo");
    sectionPanel.setLabel("Individual Customer Information");
    viewPanel.addSection(sectionPanel);

    fieldStatus =
        FieldStatus.create().setLabel("Customer Name").setPath("name").setType("String")
            .setRequired(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("District").setPath("district").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Address").setPath("address").setType("String")
            .setStyle("textarea").setWholeRow(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Postcode").setPath("postcode").setType("String")
            .setChangeLine(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("Mobile").setPath("mobile").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("Phone").setPath("phone").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("Fax").setPath("fax").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("Email").setPath("email").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    sectionPanel = createSectionPanel("companyCustInfo");
    sectionPanel.setLabel("Company Customer Information");
    viewPanel.addSection(sectionPanel);

    fieldStatus =
        FieldStatus.create().setLabel("Company Name").setPath("name").setType("String")
            .setRequired(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("District").setPath("district").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Address").setPath("address").setType("String")
            .setStyle("textarea").setWholeRow(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Postcode").setPath("postcode").setType("String")
            .setChangeLine(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    sectionPanel = createSectionPanel("companyContectPerson");
    sectionPanel.setLabel("Company Contect Person");
    viewPanel.addSection(sectionPanel);

    fieldStatus =
        FieldStatus.create().setLabel("Contect Person").setPath("contectPerson").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("Mobile").setPath("mobile").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("Phone").setPath("phone").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("Fax").setPath("fax").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus = FieldStatus.create().setLabel("Email").setPath("email").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    sectionPanel = createSectionPanel("accountInfo");
    sectionPanel.setLabel("Customer Bank Account");
    viewPanel.addSection(sectionPanel);

    fieldStatus =
        FieldStatus.create().setLabel("Account Bank").setPath("accountBank").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Account Number").setPath("accountNumber").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Account Person").setPath("accountPerson").setType("String");
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Account Mark").setPath("accountMark").setType("String")
            .setStyle("textarea").setWholeRow(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    sectionPanel = createSectionPanel("custMark");
    sectionPanel.setLabel("Company Customer Mark");
    viewPanel.addSection(sectionPanel);

    fieldStatus =
        FieldStatus.create().setLabel("Mark").setPath("mark").setType("String")
            .setStyle("textarea").setWholeRow(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    sectionPanel = createSectionPanel("socialNetworkProfile");
    sectionPanel.setLabel("Social Network Profile");

    fieldStatus =
        FieldStatus.create().setLabel("Linkedin Profile").setPath("linkedinProfileUrl")
            .setType("String").setStyle("textarea").setWholeRow(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Facebook Profile").setPath("fbProfileUrl").setType("String")
            .setStyle("textarea").setWholeRow(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));

    fieldStatus =
        FieldStatus.create().setLabel("Other Profile").setPath("otherProfileUrl").setType("String")
            .setStyle("textarea").setWholeRow(true);
    sectionPanel.addField(createFieldWidget(fieldStatus));
    viewPanel.addSection(sectionPanel);

    this.addComponent(viewPanel);

    TabSheet tabSheet = new TabSheet();
    tabSheet.addStyleName(Reindeer.TABSHEET_MINIMAL);
    tabSheet.setWidth("760px");
    ISectionPanel policySectionPanel = createListSectionPanel("Policy", buildPolicy());

    tabSheet.addTab(policySectionPanel, "General Insurance");
    tabSheet.addTab(createListSectionPanel("Life"), "Life Insurance");
    tabSheet.addTab(createListSectionPanel("Investment"), "Investment");
    tabSheet.addTab(createListSectionPanel("Morgage"), "Morgage");
    this.addComponent(tabSheet);
    // this.addComponent(createListSectionPanel("Policy"));

    // this.addComponent(createListSectionPanel("Serve"));
  }

  public Layout buildPolicy() {
    VerticalLayout layout = null;
    InsuranceCustomer customer = (InsuranceCustomer) getEntity();
    List<Policy> policies = customer.getPolicy();
    if (policies.size() > 0) {
      layout = new VerticalLayout();
      for (int i = 0; i < policies.size(); i++) {
        Policy policy = policies.get(i);
        ISectionPanel sectionPanel = new DefaultSectionPanel();
        if (i > 0) {
          sectionPanel.addStyleName("portal-section-more");
        } else {
          sectionPanel.addStyleName("portal-section");
        }
        sectionPanel.setWidth("760px");
        sectionPanel.setSpacing(false);
        FieldFactory fieldFactory = new FieldFactory(policy, true);

        FieldStatus fieldStatus = null;
        fieldStatus =
            FieldStatus.create().setLabel("Policy No").setPath("policyNo").setType("String");
        sectionPanel.addField(fieldFactory.create(fieldStatus));

        fieldStatus =
            FieldStatus.create().setLabel("Company").setPath("insuranceCompany").setType("String");
        sectionPanel.addField(fieldFactory.create(fieldStatus));

        fieldStatus =
            FieldStatus.create().setLabel("Category").setPath("category").setType("String");
        sectionPanel.addField(fieldFactory.create(fieldStatus));

        fieldStatus =
            FieldStatus.create().setLabel("Product").setPath("insuranceProduct").setType("String");
        sectionPanel.addField(fieldFactory.create(fieldStatus));

        fieldStatus =
            FieldStatus.create().setLabel("Effective Date").setPath("effectiveDate")
                .setType("Date");
        sectionPanel.addField(fieldFactory.create(fieldStatus));

        fieldStatus =
            FieldStatus.create().setLabel("Expiry Date").setPath("expiryDate").setType("Date");
        sectionPanel.addField(fieldFactory.create(fieldStatus));

        fieldStatus = FieldStatus.create().setLabel("Premium").setPath("premium").setType("String");
        sectionPanel.addField(fieldFactory.create(fieldStatus));

        fieldStatus =
            FieldStatus.create().setLabel("Commission").setPath("commission").setType("String");
        sectionPanel.addField(fieldFactory.create(fieldStatus));

        sectionPanel.setReadOnly(true);
        layout.addComponent(sectionPanel);
      }
    }
    return layout;
  }

  public Class getEntityClass() {
    return InsuranceCustomer.class;
  }

  public String getViewerTitle() {
    return "Create Insurance Customer";
  }

}
