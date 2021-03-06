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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
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
        fillStringMap(aliases, _aliases);
        _excludeProperties = new HashSet<>();
        fillStringSet(excludeProperties, _excludeProperties);
    }

    /**
     * Copy the values from one set to the other.
     *
     * @param from the set to copy values from.
     * @param to   the set to copy values to.
     */
    protected final void fillStringSet(final Set<String> from, final Set<String> to) {
        if (from != null) {
            to.addAll(from);
        }
    }

    /**
     * Copy the values from one set to the other.
     *
     * @param from the set to copy values from.
     * @param to   the set to copy values to.
     */
    protected final void fillObjectSet(final Set<Object> from, final Set<String> to) {
        if (from != null) {
            for (Object entry : from) {
                String value = getString(entry);
                to.add(value);
            }
        }
    }

    /**
     * Copy the values from one map to the other.
     *
     * @param from the map to copy values from.
     * @param to   the map to copy values to.
     */
    protected final void fillStringMap(final Map<String, String> from, final Map<String, String> to) {
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
    protected final void fillObjectMap(final Map<Object, Object> from, final Map<String, String> to) {
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
        if (_prefix != null && _suffix != null) {
            return _prefix + name + _suffix;
        }
        if (_prefix != null) {
            return _prefix + name;
        }
        if (_suffix != null) {
            return name + _suffix;
        }
        return name;
    }

    /**
     * Check if the original property name without prefix and suffix can be obtained.
     *
     * @param name the property name with prefix and suffix.
     *
     * @return true, if the original property name without prefix and suffix can be obtained.
     */
    protected final boolean canExtractPropertyName(final String name) {
        if (name == null) {
            return false;
        }
        if (_prefix != null && !name.startsWith(_prefix)) {
            return false;
        }
        if (_suffix != null && !name.endsWith(_suffix)) {
            return false;
        }
        return true;
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
        if (_prefix != null && name.startsWith(_prefix) && _suffix != null && name.endsWith(_suffix)) {
            int beginIndex = _prefix.length();
            int endIndex = name.length() - _suffix.length();
            return name.substring(beginIndex, endIndex);
        }
        if (_prefix != null && name.startsWith(_prefix) && _suffix == null) {
            int beginIndex = _prefix.length();
            return name.substring(beginIndex);
        }
        if (_prefix == null && _suffix != null && name.endsWith(_suffix)) {
            int endIndex = name.length() - _suffix.length();
            return name.substring(0, endIndex);
        }
        return name;
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
            String fullPropertyName = getFullPropertyName(name);
            keySet.remove(fullPropertyName);
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
                String fullPropertyName = getFullPropertyName(name);
                String propertyValue = properties.get(alias);
                putProperties.put(fullPropertyName, propertyValue);
            }
        }
        properties.keySet().removeAll(removeKeys);
        properties.putAll(putProperties);
    }

    /**
     * Create new input stream to read the file with the specified path.
     *
     * @param filePath the specified path.
     *
     * @return new input stream to read the file.
     */
    protected final InputStream getInputStream(final String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.newInputStream(path);
        } catch (IOException ex) {
            throw new LoadException(ex);
        }
    }

    /**
     * Create new input stream to read the file with the specified URL.
     *
     * @param url the specified URL.
     *
     * @return new input stream to read the file.
     */
    protected final InputStream getInputStream(final URL url) {
        try {
            URI uri = url.toURI();
            Path path = Paths.get(uri);
            return Files.newInputStream(path);
        } catch (IOException | URISyntaxException ex) {
            throw new LoadException(ex);
        }
    }

    /**
     * Load the properties from the input stream.
     *
     * @param properties  the properties.
     * @param inputStream the input stream.
     */
    protected final void loadProperties(final Map<Object, Object> properties, final InputStream inputStream) {
        try {
            ((Properties) properties).load(inputStream);
        } catch (IOException ex) {
            throw new LoadException(ex);
        }
    }

    /**
     * Close the specified input stream.
     *
     * @param inputStream the specified input stream.
     */
    protected final void closeInputStream(final InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException ex) {
            throw new LoadException(ex);
        }
    }

}
