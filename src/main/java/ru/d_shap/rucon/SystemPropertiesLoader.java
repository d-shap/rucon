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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Configuration loader for the system properties.
 *
 * @author Dmitry Shapovalov
 */
public final class SystemPropertiesLoader extends AbstractConfigLoader {

    private final String _prefix;

    private final String _suffix;

    private final Map<String, String> _properties;

    /**
     * Create new object.
     *
     * @param prefix            the prefix to add to the property name.
     * @param suffix            the suffix to add to the property name.
     * @param excludeProperties the properties to exclude.
     */
    public SystemPropertiesLoader(final String prefix, final String suffix, final Set<String> excludeProperties) {
        super(excludeProperties);
        _prefix = prefix;
        _suffix = suffix;
        _properties = new HashMap<>();
    }

    @Override
    public void load() {
        Map<Object, Object> properties = System.getProperties();
        fillProperties(properties, _properties);
        Set<String> excludeProperties = getExcludeProperties();
        _properties.keySet().removeAll(excludeProperties);
    }

    @Override
    public String getProperty(final String name) {
        String key = name;
        if (_prefix != null) {
            key = _prefix + key;
        }
        if (_suffix != null) {
            key = key + _suffix;
        }
        return _properties.get(key);
    }

}
