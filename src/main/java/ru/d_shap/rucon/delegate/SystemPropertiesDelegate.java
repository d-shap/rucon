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
 * Configuration delegate for the system properties.
 *
 * @author Dmitry Shapovalov
 */
public final class SystemPropertiesDelegate extends BaseConfig implements ConfigDelegate {

    /**
     * Create new object.
     *
     * @param prefix            the prefix to add to the property name.
     * @param suffix            the suffix to add to the property name.
     * @param excludeProperties the properties to exclude.
     */
    public SystemPropertiesDelegate(final String prefix, final String suffix, final Set<String> excludeProperties) {
        super(prefix, suffix, null, excludeProperties);
    }

    @Override
    public Set<String> getNames() {
        Map<Object, Object> sysProperties = System.getProperties();
        Map<String, String> properties = new HashMap<>();
        fillObjectMap(sysProperties, properties);
        excludeProperties(properties);
        Set<String> sysNames = properties.keySet();
        Set<String> names = new HashSet<>();
        for (String name : sysNames) {
            if (canExtractPropertyName(name)) {
                String extractedPropertyName = extractPropertyName(name);
                names.add(extractedPropertyName);
            }
        }
        return names;
    }

    @Override
    public String getProperty(final String name) {
        if (isExcludeProperty(name)) {
            return null;
        } else {
            String propertyName = getFullPropertyName(name);
            return System.getProperty(propertyName);
        }
    }

}
