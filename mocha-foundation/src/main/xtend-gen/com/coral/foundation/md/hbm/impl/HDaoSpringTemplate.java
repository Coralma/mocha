package com.coral.foundation.md.hbm.impl;

import com.coral.foundation.md.model.Entity;
import com.coral.foundation.md.model.Mocha;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class HDaoSpringTemplate {
  public CharSequence generate(final List<Mocha> coralList) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
    _builder.newLine();
    _builder.newLine();
    _builder.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("xmlns:context=\"http://www.springframework.org/schema/context\"");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("xmlns:p=\"http://www.springframework.org/schema/p\"");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("xsi:schemaLocation=\"");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-2.5.xsd\">");
    _builder.newLine();
    {
      for(final Mocha coral : coralList) {
        final String daoPkg = coral.getDaoImplPackage();
        _builder.newLineIfNotEmpty();
        {
          List<Entity> _entityList = coral.getEntityList();
          for(final Entity entity : _entityList) {
            String _entityName = entity.getEntityName();
            final String daoName = (_entityName + "DAO");
            _builder.newLineIfNotEmpty();
            _builder.append("<bean id=\"");
            _builder.append(daoName, "");
            _builder.append("\" class=\"");
            _builder.append(daoPkg, "");
            _builder.append(".");
            _builder.append(daoName, "");
            _builder.append("\"/>");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("</beans>");
    _builder.newLine();
    return _builder;
  }
}
