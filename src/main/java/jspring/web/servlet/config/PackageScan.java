package jspring.web.servlet.config;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;

/**
 * package scan
 */
public class PackageScan {

    private String [] basepackages;

    public PackageScan(){
        this.basepackages=new String[]{};
    }
    public PackageScan(String [] basepackages){
        this.basepackages=basepackages;
    }
    /**
     * 获取默认包扫描根路径
     * @return
     */
    private String getInitPath(){
        return getInitPath("");
    }

    /**
     * 根据指定包，获取需要扫面的根路径
     * @param pkname
     * @return
     */
    private String getInitPath(String pkname){
        String rootpath="./";
        if(pkname!=null||!"".equals(pkname)){
            rootpath=Thread.currentThread().getContextClassLoader().getResource(
                    pkname.indexOf(".")!=-1?pkname.replaceAll("\\.","/"):pkname
            ).getFile();
        }
        return rootpath;
    }

    /**
     * 获取需要扫描的包路径
     * @return
     */
    private String[] getBasePackage(){
        return this.basepackages;
    }
    /**
     * 查找当前路径下的全部注解类
     * @param path
     * @return
     */
    private Map<String,Class<?>> findAllAnnotationClassByPath(String path,String basepackage){
        Map<String,Class<?>> clazzs=new HashMap<String,Class<?>>();
        Queue<String> dirs=new LinkedList<String>();
        dirs.add(path);
        File file=null;
        while (!dirs.isEmpty()){
            file=new File(dirs.poll());
            if (file.isFile()&&file.getName().endsWith(".class")){
                try {
                    String absolutePath=file.getAbsolutePath();
                    String className=absolutePath.substring(path.length()-1,absolutePath.length()-6).replaceAll(Matcher.quoteReplacement(File.separator),"\\.");
                    Class<?> clazz=Class.forName(basepackage+className);
                    if (clazz.getDeclaredAnnotations().length>0) {
                        clazzs.put(clazz.getName(), clazz);
                    }
                } catch (Exception e) {
                    //当前class找不到异常
                    e.printStackTrace();
                }
            }else if(file.isDirectory()){
                for (File item:file.listFiles()) {
                    dirs.add(item.getAbsolutePath());
                }
            }
        }
        return clazzs;
    }

    /**
     * 查找指定包路径下的所有的注解class，可以去重
     * @return
     */
    public Map<String,Class<?>> loadAllAnnotationClass(){
        Map<String,Class<?>> clazzs=new HashMap<String,Class<?>>();
        String[] basePackages=getBasePackage();
        if (basePackages==null||basePackages.length==0){//没有指定包路径，默认进行当前工程下全部查找
            clazzs.putAll(findAllAnnotationClassByPath(getInitPath(),""));
        }else{
            for (String pkname:basePackages) {//根据指定的多个包路径进行查找
                clazzs.putAll(findAllAnnotationClassByPath(getInitPath(pkname),pkname));
            }
        }
        return clazzs;
    }
    
	public void setBasepackages(String[] basepackages) {
		this.basepackages = basepackages;
	}

}
