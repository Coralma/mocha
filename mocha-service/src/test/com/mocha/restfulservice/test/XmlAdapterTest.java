package com.mocha.restfulservice.test;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.junit.Before;
import org.junit.Test;

import com.mocha.cooperate.model.Discuss;
import com.mocha.cooperate.model.ToDo;
import com.mocha.service.model.DiscussWSElement;
import com.mocha.service.model.ToDoWSElement;
import com.mocha.service.model.UserFeeds;

public class XmlAdapterTest {

	@Before
	public void before() {

	}

	@Test
	public void runTest() {
		JAXBContext c;
		try {
			c = JAXBContext.newInstance(DiscussWSElement.class, Discuss.class);
			Marshaller m = c.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			Discuss dis = new Discuss();
			dis.setContent("vance");
			DiscussWSElement disWs = new DiscussWSElement();
			disWs.setContent(dis.getContent());
			m.marshal(disWs, System.out);
		}
		catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
