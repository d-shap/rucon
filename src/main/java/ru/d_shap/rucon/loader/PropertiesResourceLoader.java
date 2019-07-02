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
package ru.d_shap.rucon.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import ru.d_shap.rucon.BaseConfig;
import ru.d_shap.rucon.ConfigDelegate;
import ru.d_shap.rucon.ConfigLoader;
import ru.d_shap.rucon.LoadException;

/**
 * Configuration loader for the properties.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesResourceLoader extends BaseConfig implements ConfigLoader, ConfigDelegate {

    private final ClassLoader _classLoader;

    private final String _resource;

    private final Set<String> _names;

    private final Map<String, String> _properties;

    /**
     * Create new object.
     *
     * @param classLoader       the class loader to load the resource.
     * @param resource          the resource.
     * @param excludeProperties the properties to exclude.
     */
    public PropertiesResourceLoader(final ClassLoader classLoader, final String resource, final Set<String> excludeProperties) {
        super(null, null, null, excludeProperties);
        _classLoader = classLoader;
        _resource = resource;
        _names = new HashSet<>();
        _properties = new HashMap<>();
    }

    @Override
    public void load() {
        try {
            if (_resource == null) {
                return;
            }
            URL url = _classLoader.getResource(_resource);
            if (url == null) {
                return;
            }
            try (InputStream inputStream = _classLoader.getResourceAsStream(_resource)) {
                Map<Object, Object> properties = new Properties();
                ((Properties) properties).load(inputStream);
                fillObjectMap(properties, _properties);
                excludeProperties(_properties);
                Set<String> names = _properties.keySet();
                fillStringSet(names, _names);
            }
        } catch (IOException ex) {
            throw new LoadException(ex);
        }
    }

    @Override
    public Set<String> getNames() {
        return new HashSet<>(_names);
    }

    @Override
    public String getProperty(final String name) {
        return _properties.get(name);
    }

}
