package com.ken.other.aware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;

/**
 * 在实际项目中，不可避免的要用到Spring容器自身的功能资源，
 * 这时Bean必须要意识到Spring容器的存在，才能调用Spring所提供的资源，
 * 这就是所谓的Spring Aware。
 * Spring Aware本来就是Spring设计用来框架内部使用的，若使用了Spring Aware,
 * Bean将会和Spring框架耦合。
 */

@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {

    private String beanName;

    private ResourceLoader resourceLoader;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    public void outputResult() {
        System.out.println("Bean的名称为：" + beanName);

        Resource resource = resourceLoader.getResource("classpath:com/ken/other/aware/testAware.txt");

        System.out.println("FileName: " + resource.getFilename());
        System.out.println("exists: " + resource.exists());
    }


}
