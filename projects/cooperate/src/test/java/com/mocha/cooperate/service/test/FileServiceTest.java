/**
 * 
 */
package com.mocha.cooperate.service.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coral.foundation.security.model.BasicUser;
import com.coral.foundation.security.service.BasicUserService;
import com.coral.foundation.utils.UUIDGenerater;
import com.mocha.cooperate.model.File;
import com.mocha.cooperate.service.UserFileService;

/**
 * @author Coral.Ma
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class FileServiceTest {

	@Test
	public void saveAndDeleteFile() {
		String fileName = "testFile.java";
		UserFileService fileService = new UserFileService(loadTestUser());
		File file = createFile(fileName);
		fileService.createFile(file);

		List<File> files = fileService.loadFiles();
		boolean isTrue = false;
		for(File existFile : files) {
			if(fileName.equals(existFile.getName())) {
				isTrue = true;
			}
		}
		Assert.assertTrue(isTrue);
	}
	
	@Test
	public void loadFileByUser() {
		UserFileService fileService = new UserFileService(loadTestUser());
		File file = createFile("test1.txt");
		fileService.createFile(file);
		File file2 = createFile("test2.txt");
		fileService.createFile(file2);
		File file3 = createFile("test3.txt");
		fileService.createFile(file3);

		List<File> files = fileService.loadFiles();
		for(File loadedFile : files) {
			Assert.assertEquals("TXT", loadedFile.getType());
		}
		Assert.assertEquals(3, files.size());
	}
	
	@Test
	public void fuzzySearch() {
		UserFileService fileService = new UserFileService(loadTestUser());
		File file = createFile("abcd.txt");
		fileService.createFile(file);
		File file2 = createFile("bcd.txt");
		fileService.createFile(file2);
		File file3 = createFile("cdef.txt");
		fileService.createFile(file3);
		
		Assert.assertEquals(1, fileService.fuzzySearchFile("abc").size());
		Assert.assertEquals(2, fileService.fuzzySearchFile("bc").size());
		Assert.assertEquals(3, fileService.fuzzySearchFile("cd").size());
	}
	
	@Test
	public void testFindFileByShareKey() {
		UserFileService fileService = new UserFileService(loadTestUser());
		File file = createFile("withKey.txt");
		String key = UUIDGenerater.generate();
		file.setShareKey(key);
		fileService.createFile(file);
		
		fileService = new UserFileService(null);
		File findedFile = fileService.findFileByShareKey(key);
		Assert.assertNotNull(findedFile);
	}
	
	@After
	public void deleteAll() {
		UserFileService fileService = new UserFileService(loadTestUser());
		List<File> files = fileService.loadFiles();
		for(File existFile : files) {
			fileService.delete(existFile);
		}
	}
	
	public File createFile(String fileName) {
		File file = new File();
		file.setName(fileName);
		file.setPath(fileName);
		file.setSize(new Long(100));
		file.setCreator(loadTestUser());
		return file;
	}
	
	public BasicUser loadTestUser() {
		BasicUserService service = new BasicUserService();
		return service.loadUserById(new Long(1));
	}
}
