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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Base configuration class.
 *
 * @author Dmitry Shapovalov
 */
public class BaseConfig {

    private final String _prefix;

    private final String _suffix;

    private final Map<String, String> _aliases;

    private final Set<String> _excludeProperties;

    /**
     * Create new object.
     *
     * @param prefix            the prefix to add to the property name.
     * @param suffix            the suffix to add to the property name.
     * @param aliases           the property aliases, the key is the property name, the value is the alias.
     * @param excludeProperties the properties to exclude.
     */
    protected BaseConfig(final String prefix, final String suffix, final Map<String, String> aliases, final Set<String> excludeProperties) {
        super();
        _prefix = prefix;
        _suffix = suffix;
        _aliases = new HashMap<>();
        fillMap(aliases, _aliases);
        _excludeProperties = new HashSet<>();
        fillSet(excludeProperties, _excludeProperties);
    }

    /**
     * Copy the values from one set to the other.
     *
     * @param from the set to copy values from.
     * @param to   the set to copy values to.
     */
    protected final void fillSet(final Set<String> from, final Set<String> to) {
        if (from != null) {
            to.addAll(from);
        }
    }

    /**
     * Copy the values from one map to the other.
     *
     * @param from the map to copy values from.
     * @param to   the map to copy values to.
     */
    protected final void fillMap(final Map<String, String> from, final Map<String, String> to) {
        if (from != null) {
            to.putAll(from);
        }
    }

    /**
     * Copy the values from one map to the other.
     *
     * @param from the map to copy values from.
     * @param to   the map to copy values to.
     */
    protected final void fillProperties(final Map<Object, Object> from, final Map<String, String> to) {
        if (from != null) {
            for (Map.Entry<Object, Object> entry : from.entrySet()) {
                String key = getString(entry.getKey());
                String value = getString(entry.getValue());
                to.put(key, value);
            }
        }
    }

    /**
     * Get the string representation of the object.
     *
     * @param object the object.
     *
     * @return the string representation of the object.
     */
    protected final String getString(final Object object) {
        if (object == null) {
            return null;
        } else {
            return object.toString();
        }
    }

    /**
     * Get the property name with prefix and suffix.
     *
     * @param name the original property name.
     *
     * @return the property name with prefix and suffix.
     */
    protected final String getFullPropertyName(final String name) {
        if (name == null) {
            return null;
        }
        String propertyName = name;
        if (_prefix != null) {
            propertyName = _prefix + propertyName;
        }
        if (_suffix != null) {
            propertyName = propertyName + _suffix;
        }
        return propertyName;
    }

    /**
     * Get the original property name without prefix and suffix.
     *
     * @param name the property name with prefix and suffix.
     *
     * @return the original property name.
     */
    protected final String extractPropertyName(final String name) {
        if (name == null) {
            return null;
        }
        String propertyName = name;
        if (_prefix != null && propertyName.startsWith(_prefix)) {
            int beginIndex = _prefix.length();
            propertyName = propertyName.substring(beginIndex);
        }
        if (_suffix != null && propertyName.endsWith(_suffix)) {
            int endIndex = propertyName.length() - _suffix.length();
            propertyName = propertyName.substring(0, endIndex);
        }
        return propertyName;
    }

    /**
     * Check if the property should be excluded.
     *
     * @param name the original property name.
     *
     * @return true, if the property should be excluded.
     */
    protected final boolean isExcludeProperty(final String name) {
        return _excludeProperties.contains(name);
    }

    /**
     * Exclude the properties with the names, specified in constructor, from the original properties.
     *
     * @param properties the original properties.
     */
    protected final void excludeProperties(final Map<String, String> properties) {
        Set<String> keySet = properties.keySet();
        for (String name : _excludeProperties) {
            String propertyName = getFullPropertyName(name);
            keySet.remove(propertyName);
        }
    }

    /**
     * Get the property alias for the specified property name.
     *
     * @param name the specified property name.
     *
     * @return the property alias.
     */
    protected final String getPropertyAlias(final String name) {
        return _aliases.get(name);
    }

    /**
     * Replace the property aliases, specified in constructor, with the property names.
     *
     * @param properties the original properties.
     */
    protected final void replacePropertyAliases(final Map<String, String> properties) {
        Set<String> removeKeys = new HashSet<>();
        Map<String, String> putProperties = new HashMap<>();
        for (Map.Entry<String, String> entry : _aliases.entrySet()) {
            String alias = entry.getValue();
            if (properties.containsKey(alias)) {
                removeKeys.add(alias);
                String name = entry.getKey();
                String propertyName = getFullPropertyName(name);
                String propertyValue = properties.get(alias);
                putProperties.put(propertyName, propertyValue);
            }
        }
        properties.keySet().removeAll(removeKeys);
        properties.putAll(putProperties);
    }

}
