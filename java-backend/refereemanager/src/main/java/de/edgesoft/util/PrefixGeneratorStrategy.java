package de.edgesoft.util;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

public class PrefixGeneratorStrategy extends DefaultGeneratorStrategy {
	
	static final String PREFIX = "Rfrmgr";
	
	@Override
    public String getJavaClassName(final Definition definition, final Mode mode) {
		String sTemp = super.getJavaClassName(definition, mode);
		if (sTemp.startsWith(PREFIX)) {
			sTemp = sTemp.substring(PREFIX.length());
		}
        return sTemp;
    }
	
}

/* EOF */
