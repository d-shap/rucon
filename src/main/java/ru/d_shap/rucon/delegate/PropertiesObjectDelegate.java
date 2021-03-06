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
package ru.d_shap.rucon.delegate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ru.d_shap.rucon.BaseConfig;
import ru.d_shap.rucon.ConfigDelegate;

/**
 * Configuration delegate for the properties.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesObjectDelegate extends BaseConfig implements ConfigDelegate {

    private final Map<Object, Object> _properties;

    /**
     * Create new object.
     *
     * @param properties        the properties object.
     * @param excludeProperties the properties to exclude.
     */
    public PropertiesObjectDelegate(final Map<Object, Object> properties, final Set<String> excludeProperties) {
        super(null, null, null, excludeProperties);
        if (properties == null) {
            _properties = new HashMap<>();
        } else {
            _properties = properties;
        }
    }

    @Override
    public Set<String> getNames() {
        Map<String, String> properties = new HashMap<>();
        fillObjectMap(_properties, properties);
        excludeProperties(properties);
        return new HashSet<>(properties.keySet());
    }

    @Override
    public String getProperty(final String name) {
        if (isExcludeProperty(name)) {
            return null;
        } else {
            Object value = getValue(name);
            return getString(value);
        }
    }

    private Object getValue(final String name) {
        for (Map.Entry<Object, Object> entry : _properties.entrySet()) {
            String key = getString(entry.getKey());
            if (key == null) {
                if (name == null) {
                    return entry.getValue();
                }
            } else {
                if (key.equals(name)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

}
