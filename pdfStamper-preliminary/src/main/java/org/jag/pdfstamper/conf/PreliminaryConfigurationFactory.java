/*
 * MIT License
 *
 * Copyright (c) 2019 José A. García Sánchez
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.jag.pdfstamper.conf;

import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jose A. Garcia
 */
public class PreliminaryConfigurationFactory {
    private static final String CONF_FILENAME = "preliminary_configuration";
    private static final Logger LOGGER = LoggerFactory.getLogger(PreliminaryConfigurationFactory.class);
    private static final Map<String, PreliminaryConfigurationFactory> instances = new HashMap<>();
    private final StamperBundle bundle;

    private PreliminaryConfigurationFactory(final StamperBundle bundle) {
        this.bundle = new ConfigurationProxy(bundle);
    }

    public static PreliminaryConfigurationFactory getInstance() {
        return getInstance(CONF_FILENAME);
    }

    public static PreliminaryConfigurationFactory getInstance(final String filename) {
        if (!instances.containsKey(filename)) {
            try {
                instances.put(filename, new PreliminaryConfigurationFactory(new BundleLoader(filename)));
            } catch (IOException e) {
                LOGGER.warn("An IOException happened.", e);
                instances.put(filename, new PreliminaryConfigurationFactory(null));
            }
        }
        return instances.get(filename);
    }

    public StamperBundle getStamperBundle() {
        return bundle;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("instance", instances).toString();
    }
}
