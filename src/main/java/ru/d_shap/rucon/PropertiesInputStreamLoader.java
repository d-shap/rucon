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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Configuration loader for the properties input stream.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesInputStreamLoader extends AbstractConfigLoader {

    private final InputStream _inputStream;

    private final Map<String, String> _properties;

    /**
     * Create new object.
     *
     * @param inputStream       the properties input stream.
     * @param excludeProperties the properties to exclude.
     */
    public PropertiesInputStreamLoader(final InputStream inputStream, final Set<String> excludeProperties) {
        super(excludeProperties);
        _inputStream = inputStream;
        _properties = new HashMap<>();
    }

    @Override
    public void load() {
        try {
            try {
                Map<Object, Object> properties = new Properties();
                ((Properties) properties).load(_inputStream);
                fillProperties(properties, _properties);
                Set<String> excludeProperties = getExcludeProperties();
                _properties.keySet().removeAll(excludeProperties);
            } finally {
                _inputStream.close();
            }
        } catch (IOException ex) {
            throw new RuntimeIOException(ex);
        }
    }

    @Override
    public String getProperty(final String name) {
        return _properties.get(name);
    }

}
