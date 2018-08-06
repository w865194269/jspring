package jspring.config;

import java.util.Map;
import java.util.Set;

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
//		pkScan.setBasepackages(new String[]{});
		Set<Class<?>> clazzs = pkScan.loadAllAnnotationClass();
		for(Class<?> entry:clazzs){
			System.out.println(entry.getName());
		}
	}
	
	@Test
	public void testBasePackage() throws Exception {
		pkScan.addPackage("jspring.home");
		Set<Class<?>> clazzs = pkScan.loadAllAnnotationClass();
		for(Class<?> entry:clazzs){
			System.out.println(entry.getName());
		}
	}
	
	public static void main(String[] args) {
		PackageScan pkScan=new PackageScan();
		Set<Class<?>> clazzs = pkScan.loadAllAnnotationClass();
		for(Class<?> entry:clazzs){
			System.out.println(entry.getName());
		}
	}
}
