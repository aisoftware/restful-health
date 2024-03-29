package com.restfulhealth.services.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SectionTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void convertXMLtoJSON() {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try{

			File file = new File("sectionJSONTest.txt");
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			InputStream is = LabResultTest.class.getResourceAsStream("/ccd_all_sections.xml");
				String xml = IOUtils.toString(is);
				
				XMLSerializer xmlSerializer = new XMLSerializer(); 
				JSON json = xmlSerializer.read( xml );
				json.write(bw);
				
				String jsonResult = json.toString(2);
				System.out.println( jsonResult);
		}
		catch(Throwable t){
			t.printStackTrace();
		}
		finally{
			try{
				if(bw != null)
					bw.close();
				if(fw != null)
					fw.close();
			}
			catch(Throwable t){
				t.printStackTrace();
			}
		}
	}

}
