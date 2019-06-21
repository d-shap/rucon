///////////////////////////////////////////////////////////////////////////////////////////////////
// RuCon is the application configuration helper.
// Copyright (C) 2019 Dmitry Shapovalov.
//
// This file is part of RuCon.
//
// RuCon is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// RuCon is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.rucon;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration helper class.
 *
 * @author Dmitry Shapovalov
 */
public final class Configuration implements ConfigLoader {

    private final List<ConfigLoader> _configLoaders;

    /**
     * Create new object.
     *
     * @param configLoaders configuration loaders.
     */
    public Configuration(final List<ConfigLoader> configLoaders) {
        super();
        _configLoaders = new ArrayList<>();
        if (configLoaders != null) {
            for (ConfigLoader configLoader : configLoaders) {
                if (configLoader != null) {
                    _configLoaders.add(configLoader);
                }
            }
        }
    }

    @Override
    public void load() {
        for (ConfigLoader configLoader : _configLoaders) {
            configLoader.load();
        }
    }

    @Override
    public String getProperty(final String name) {
        for (ConfigLoader configLoader : _configLoaders) {
            String value = configLoader.getProperty(name);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * Get the configuration property as String.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    public String getPropertyAsString(final String name, final String defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }

    /**
     * Get the configuration property as boolean.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    public boolean getPropertyAsBoolean(final String name, final boolean defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Boolean.parseBoolean(value);
        }
    }

    /**
     * Get the configuration property as int.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    public int getPropertyAsInt(final String name, final int defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Integer.parseInt(value);
        }
    }

    /**
     * Get the configuration property as long.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    public long getPropertyAsLong(final String name, final long defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Long.parseLong(value);
        }
    }

    /**
     * Get the configuration property as float.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    public float getPropertyAsFloat(final String name, final float defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Float.parseFloat(value);
        }
    }

    /**
     * Get the configuration property as double.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    public double getPropertyAsDouble(final String name, final double defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Double.parseDouble(value);
        }
    }

    /**
     * Get the configuration property as char.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    public char getPropertyAsChar(final String name, final char defaultValue) {
        String value = getProperty(name);
        if (value == null || value.length() == 0) {
            return defaultValue;
        } else {
            return value.charAt(0);
        }
    }

}
