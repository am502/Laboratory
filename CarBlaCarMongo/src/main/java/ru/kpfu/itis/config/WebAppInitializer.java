package ru.kpfu.itis.config;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Order(1)
public class WebAppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {


	@Override
    public Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {WebSecurityConfig.class, DataSourceConfig.class,
                PersistenceConfig.class, CoreConfig.class};
	}

	@Override
    public Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
    public String[] getServletMappings() {
		return new String[] { "/" };
	}

    @Override
    protected Filter[] getServletFilters() {
        DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[]{
                springSecurityFilterChain,
                characterEncodingFilter
        };
    }

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        super.onStartup(servletContext);

        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.register(CxfConfig.class);
        registerJAXWSServlet(servletContext);
    }

    private void registerJAXWSServlet(ServletContext servletContext) {
        ServletRegistration.Dynamic dispatcher
                = servletContext.addServlet("cxf", new CXFServlet());
        dispatcher.addMapping("/services");
    }

}