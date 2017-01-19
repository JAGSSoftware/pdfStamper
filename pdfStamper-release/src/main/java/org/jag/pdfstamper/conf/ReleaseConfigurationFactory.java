/*
 * Copyright (C) 2017 Jose A. Garcia Sanchez
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.jag.pdfstamper.conf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;

/**
 * @author Jose A. Garcia
 */
public class ReleaseConfigurationFactory {
    private static final String CONF_FILENAME = "release_configuration";
    private static final Logger LOGGER = LoggerFactory.getLogger(ReleaseConfigurationFactory.class);
    private static final Map<String, ReleaseConfigurationFactory> instances = new HashMap<>();
    private final StamperBundle bundle;

    private ReleaseConfigurationFactory(final StamperBundle bundle) {
        this.bundle = bundle;
    }

    public static ReleaseConfigurationFactory getInstance() {
        return getInstance(CONF_FILENAME);
    }

    public static ReleaseConfigurationFactory getInstance(final String filename) {
        if (!instances.containsKey(filename)) {
            try {
                instances.put(filename, new ReleaseConfigurationFactory(new BundleLoader(filename)));
            } catch (IOException e) {
                LOGGER.warn("An IOException happened.", e);
                instances.put(filename, new ReleaseConfigurationFactory(null));
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
