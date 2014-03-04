package com.mocha.vaadin.entity.view;

import java.util.List;

import com.coral.foundation.oauth.APIKeys;
import com.coral.foundation.security.model.BasicUser;
import com.coral.vaadin.view.template.sat.panel.IActionPanel;
import com.coral.vaadin.view.template.sat.panel.ISectionPanel;
import com.coral.vaadin.view.template.sat.panel.IViewPanel;
import com.coral.vaadin.view.template.sat.panel.impl.EntityViewPanel;
import com.coral.vaadin.widget.Viewer;
import com.coral.vaadin.widget.component.DefaultUploadWidget;
import com.coral.vaadin.widget.fields.FieldStatus;
import com.mocha.ib.model.InsuranceCustomer;
import com.mocha.ib.widget.FileUpload;
import com.mocha.ib.widget.ImportProfileWidget;
import com.mocha.soicalAPI.AppAuthenciateWindow;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Table;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

public class ImportProfileView extends EntityViewPanel implements Viewer {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  private BasicUser user;
  private Table table;
  private NativeButton importIcBtn = new NativeButton("Import Customer Profile");
  private NativeButton importFbBtn = new NativeButton("Import Customer Profile");

  private ImportProfileWidget upload = new ImportProfileWidget();
  private VerticalLayout vlayout = new VerticalLayout();

  public ImportProfileView(BasicUser user) {
    super();
    this.user = user;
  }

  public void build() {
    final IViewPanel viewPanel = createViewPanel();
    ISectionPanel sectionPanel;
    FieldStatus fieldStatus;
    addComponent(viewPanel);
    addComponent(vlayout);
    vlayout.addComponent(getUpload());
  }

  public void buildTableLayout(List<InsuranceCustomer> list) {
    vlayout.setImmediate(true);
    table = new Table();
    table.setWidth("100%");
    table.setHeight("100%");
    table.setSelectable(true);
    table.setImmediate(true);
    BeanItemContainer<InsuranceCustomer> container =
        new BeanItemContainer<InsuranceCustomer>(InsuranceCustomer.class, list);
    table.setContainerDataSource(container);
    vlayout.addComponent(table);
    vlayout.setSpacing(true);

    // getImportIcBtn().addStyleName("mocha-button");
    // getImportIcBtn().setWidth("150px");
    // getImportIcBtn().setDescription("Import Insurance Customer Profile");
    // vlayout.addComponent(getImportIcBtn());

    getImportFbBtn().addStyleName("mocha-button");
    getImportFbBtn().setWidth("150px");
    getImportFbBtn().setDescription("Import Facebookr Friends Profile");
    vlayout.addComponent(getImportFbBtn());
  }

  public void buildFBAuthLayout() {
    // Facebook facebook = new FacebookFactory().getInstance();
    // facebook.setOAuthAppId(APIKeys.facebookAPIId, APIKeys.facebookSecertKey);
    // facebook.setOAuthCallbackURL(APIKeys.facebookCallBackUrl);
    // getApplication().getMainWindow().open(new ExternalResource(APIKeys.facebookOAuthUrl), "", -1,
    // -1, Window.BORDER_DEFAULT);
    getApplication().getMainWindow().addWindow(new AppAuthenciateWindow(user, APIKeys.facebookAPIName));
  }

  public Class getEntityClass() {
    return InsuranceCustomer.class;
  }

  public String getViewerTitle() {
    return "Import Insurance Customers";
  }

  public BasicUser getUser() {
    return user;
  }

  public void setUser(BasicUser user) {
    this.user = user;
  }

  public ImportProfileWidget getUpload() {
    return upload;
  }

  public void setUpload(ImportProfileWidget upload) {
    this.upload = upload;
  }

  public NativeButton getImportFbBtn() {
    return importFbBtn;
  }

  public void setImportFbBtn(NativeButton importFbBtn) {
    this.importFbBtn = importFbBtn;
  }

  public NativeButton getImportIcBtn() {
    return importIcBtn;
  }

  public void setImportIcBtn(NativeButton importIcBtn) {
    this.importIcBtn = importIcBtn;
  }

}
