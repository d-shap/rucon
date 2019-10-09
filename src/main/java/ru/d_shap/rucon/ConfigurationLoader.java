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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Application configuration loader.
 *
 * @author Dmitry Shapovalov
 */
public final class ConfigurationLoader implements ConfigLoader, ConfigDelegate, Configuration {

    private final List<ConfigDelegate> _configDelegates;

    private final Set<String> _loadedNames;

    /**
     * Create new object.
     *
     * @param configDelegates configuration delegates.
     */
    public ConfigurationLoader(final List<ConfigDelegate> configDelegates) {
        super();
        _configDelegates = new ArrayList<>();
        if (configDelegates != null) {
            for (ConfigDelegate configDelegate : configDelegates) {
                if (configDelegate != null) {
                    _configDelegates.add(configDelegate);
                }
            }
        }
        _loadedNames = new HashSet<>();
    }

    @Override
    public void load() {
        for (ConfigDelegate configDelegate : _configDelegates) {
            if (configDelegate instanceof ConfigLoader) {
                ((ConfigLoader) configDelegate).load();
                Set<String> loadedNames = configDelegate.getNames();
                _loadedNames.addAll(loadedNames);
            }
        }
    }

    @Override
    public Set<String> getNames() {
        Set<String> allNames = new HashSet<>(_loadedNames);
        for (ConfigDelegate configDelegate : _configDelegates) {
            if (!(configDelegate instanceof ConfigLoader)) {
                Set<String> names = configDelegate.getNames();
                allNames.addAll(names);
            }
        }
        return allNames;
    }

    @Override
    public String getProperty(final String name) {
        for (ConfigDelegate configDelegate : _configDelegates) {
            String value = configDelegate.getProperty(name);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String getPropertyAsString(final String name, final String defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }

    @Override
    public boolean getPropertyAsBoolean(final String name, final boolean defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Boolean.parseBoolean(value);
        }
    }

    @Override
    public int getPropertyAsInt(final String name, final int defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Integer.parseInt(value);
        }
    }

    @Override
    public long getPropertyAsLong(final String name, final long defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Long.parseLong(value);
        }
    }

    @Override
    public float getPropertyAsFloat(final String name, final float defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Float.parseFloat(value);
        }
    }

    @Override
    public double getPropertyAsDouble(final String name, final double defaultValue) {
        String value = getProperty(name);
        if (value == null) {
            return defaultValue;
        } else {
            return Double.parseDouble(value);
        }
    }

    @Override
    public char getPropertyAsChar(final String name, final char defaultValue) {
        String value = getProperty(name);
        if (value == null || value.length() == 0) {
            return defaultValue;
        } else {
            return value.charAt(0);
        }
    }

}
