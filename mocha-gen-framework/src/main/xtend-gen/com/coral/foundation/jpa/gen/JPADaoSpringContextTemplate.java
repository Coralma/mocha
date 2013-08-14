package com.coral.foundation.jpa.gen;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import com.coral.foundation.md.model.helper.VGenHelper;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class JPADaoSpringContextTemplate {
  private List<Mocha> corals;
  
  public List<Mocha> init(final List<Mocha> corals) {
    List<Mocha> _corals = this.corals = corals;
    return _corals;
  }
  
  public CharSequence generateSpringContextXml() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    _builder.newLine();
    _builder.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:task=\"http://www.springframework.org/schema/task\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("xmlns:context=\"http://www.springframework.org/schema/context\" xmlns:p=\"http://www.springframework.org/schema/p\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("xmlns:tx=\"http://www.springframework.org/schema/tx\" xmlns:jaxws=\"http://cxf.apache.org/jaxws\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("xsi:schemaLocation=\"");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("http://www.springframework.org/schema/beans");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("http://www.springframework.org/schema/beans/spring-beans.xsd");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("http://www.springframework.org/schema/tx ");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("http://www.springframework.org/schema/tx/spring-tx-3.0.xsd");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("http://www.springframework.org/schema/task");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("http://www.springframework.org/schema/task/spring-task-3.0.xsd");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("http://www.springframework.org/schema/context");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("http://www.springframework.org/schema/context/spring-context-3.0.xsd\">");
    _builder.newLine();
    _builder.newLine();
    {
      for(final Mocha coral : this.corals) {
        {
          List<Entity> _entityList = coral.getEntityList();
          for(final Entity entity : _entityList) {
            _builder.append("\t");
            final String entityName = entity.entityName;
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            final String daoImplClassName = VGenHelper.genDao(entityName);
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            final String daoImplPackage = coral.getDaoImplPackage();
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("\t");
            _builder.append("<bean id=\"");
            _builder.append(daoImplClassName, "		");
            _builder.append("\" class=\"");
            _builder.append(daoImplPackage, "		");
            _builder.append(".");
            _builder.append(daoImplClassName, "		");
            _builder.append("\"></bean>");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("</beans>");
    _builder.newLine();
    _builder.newLine();
    return _builder;
  }
}
