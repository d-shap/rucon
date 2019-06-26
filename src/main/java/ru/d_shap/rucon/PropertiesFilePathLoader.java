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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Configuration loader for the properties.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesFilePathLoader extends BaseConfig implements ConfigLoader, ConfigDelegate {

    private final String _filePath;

    private final Map<String, String> _properties;

    /**
     * Create new object.
     *
     * @param filePath          the path to the properties file.
     * @param excludeProperties the properties to exclude.
     */
    public PropertiesFilePathLoader(final String filePath, final Set<String> excludeProperties) {
        super(null, null, null, excludeProperties);
        _filePath = filePath;
        _properties = new HashMap<>();
    }

    @Override
    public void load() {
        try {
            if (_filePath == null) {
                return;
            }
            File file = new File(_filePath);
            if (!file.exists()) {
                return;
            }
            try (InputStream inputStream = Files.newInputStream(Paths.get(file.getAbsolutePath()))) {
                Map<Object, Object> properties = new Properties();
                ((Properties) properties).load(inputStream);
                fillProperties(properties, _properties);
                excludeProperties(_properties);
            }
        } catch (IOException ex) {
            throw new LoadException(ex);
        }
    }

    @Override
    public String getProperty(final String name) {
        return _properties.get(name);
    }

}
