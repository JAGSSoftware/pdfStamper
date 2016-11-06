/*
 * (c) 2013 - Jose A. Garcia Sanchez
 */
package org.jag.pdfstamper.stamp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jose A. Garcia
 */
public enum StampType {
    RELEASE("release"), PRELIMINARY("preliminary"), WATERMARK("watermark");

    private static final Map<String, StampType> map = new HashMap<String, StampType>();
    private final String type;

    static {
        for (final StampType type : StampType.values()) {
            map.put(type.type, type);
        }
    }

    /**
     * @param type
     */
    StampType(final String type) {
        this.type = type;
    }

    /**
     * @param type
     * @return
     */
    public static StampType find(final String type) {
        return map.get(type);
    }
}
