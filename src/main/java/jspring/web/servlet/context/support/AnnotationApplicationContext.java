package jspring.web.servlet.context.support;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import jspring.web.servlet.config.PackageScan;

public class AnnotationApplicationContext extends ConfigurableApplicationContext {
	
	private final PackageScan packageScan=new PackageScan();
	
	private final Set<Class<? extends Annotation>> annotatedClasses = 
			new LinkedHashSet<Class<? extends Annotation>>();

	private final Set<String> basePackages = new LinkedHashSet<String>();
	
	public AnnotationApplicationContext loadPackageConfig(String ...basePackages){
		if (basePackages!=null||basePackages.length>0) {
			for(String packages:basePackages){
				packageScan.addPackage(packages);
			}
		}
		return this;
	}
	

	
	@Override
	public void onCreate() throws Exception {
		for(String pkname:basePackages){
			System.out.println(pkname);
		}
	}
	
	
	@Override
	void setId(String id) {
		
	}



}
