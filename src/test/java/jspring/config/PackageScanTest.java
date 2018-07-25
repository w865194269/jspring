package jspring.config;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import jspring.web.servlet.config.PackageScan;

/**
 * package scan test
 */
public class PackageScanTest {
	private PackageScan pkScan=null;
	
	@Before
	public void init(){
		pkScan=new PackageScan();
	}
	
	@Test
	public void testAll() throws Exception {
		pkScan.setBasepackages(new String[]{});
		Map<String, Class<?>> clazzs = pkScan.loadAllAnnotationClass();
		for(Map.Entry<String, Class<?>> entry:clazzs.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
	@Test
	public void testBasePackage() throws Exception {
		pkScan.setBasepackages(new String[]{"jspring.home"});
		Map<String, Class<?>> clazzs = pkScan.loadAllAnnotationClass();
		for(Map.Entry<String, Class<?>> entry:clazzs.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
}
