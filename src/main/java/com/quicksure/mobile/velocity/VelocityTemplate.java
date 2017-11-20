package com.quicksure.mobile.velocity;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import com.quicksure.mobile.utility.SysConstantsConfig;

public class VelocityTemplate {
	private static final Logger logger = Logger
			.getLogger(VelocityTemplate.class);
	private static VelocityEngine velocityEngine = new VelocityEngine();
	private static final Properties VLY_INIT_PRO = new Properties();

	static {
		String sysRoot =VelocityTemplate.class.getResource("").getPath();
		VLY_INIT_PRO.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, sysRoot
				+ SysConstantsConfig.Velocity_Template_ForQuickApp_path);
		VLY_INIT_PRO.put("input.encoding", "UTF-8");
		VLY_INIT_PRO.put("output.encoding", "UTF-8");
	}

	static {
		try {
			velocityEngine.init(VLY_INIT_PRO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static VelocityEngine getVelocityEngine() throws Exception {
		return velocityEngine;
	}

	public static String getVelocityTemplate(String basePath,
			Map<String, Object> objAliasMap) throws Exception {
		StringWriter stringWriter = new StringWriter();
		VelocityEngine velocityEngine = getVelocityEngine();

		try {
			VelocityContext velocityContext = new VelocityContext();
			if (objAliasMap != null && !objAliasMap.isEmpty()) {
				Set<String> keySet = objAliasMap.keySet();
				Iterator<String> it = keySet.iterator();
				while (it.hasNext()) {
					String key = it.next();
					velocityContext.put(key, objAliasMap.get(key));
				}
			}
			Template template = velocityEngine.getTemplate(basePath, "UTF-8");
			template.merge(velocityContext, stringWriter);
		} catch (Exception e) {
			logger.error("Velocity处理模板发生错误。模板路径：" + basePath, e);
			e.printStackTrace();
			throw e;
		}
		return stringWriter.toString();
	}
}
