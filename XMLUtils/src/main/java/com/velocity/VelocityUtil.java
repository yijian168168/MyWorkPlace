package com.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.Properties;
/**
 * Created by Administrator on 2016/1/13 0013.
 */
public class VelocityUtil {

    private static final String ENCODING = "UTF-8";

    static {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        properties.setProperty("runtime.log.logsystem.class",
                "org.apache.velocity.runtime.log.NullLogChute");
        Velocity.init(properties);
    }

    public static String getXML(String templateName, Object xmlVo) {
        StringWriter sw = new StringWriter();
        VelocityContext context = new VelocityContext();
        context.put(xmlVo.getClass().getSimpleName(), xmlVo);
        Template template = Velocity.getTemplate(templateName, ENCODING);
        template.merge(context, sw);
        return sw.toString();
    }
}
