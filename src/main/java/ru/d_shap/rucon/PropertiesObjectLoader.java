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
 * Configuration loader for the properties object.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesObjectLoader extends AbstractLoader {

    private final Map<String, String> _properties;

    /**
     * Create new object.
     *
     * @param properties        the properties object.
     * @param excludeProperties the properties to exclude.
     */
    public PropertiesObjectLoader(final Map<Object, Object> properties, final Set<String> excludeProperties) {
        super(null, null, null, excludeProperties);
        _properties = new HashMap<>();
        fillProperties(properties, _properties);
    }

    @Override
    public void load() {
        excludeProperties(_properties);
    }

    @Override
    public String getProperty(final String name) {
        return _properties.get(name);
    }

}
