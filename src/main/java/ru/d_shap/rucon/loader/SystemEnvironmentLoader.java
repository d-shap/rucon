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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ru.d_shap.rucon.BaseConfig;
import ru.d_shap.rucon.ConfigDelegate;
import ru.d_shap.rucon.ConfigLoader;

/**
 * Configuration loader for the system environment.
 *
 * @author Dmitry Shapovalov
 */
public final class SystemEnvironmentLoader extends BaseConfig implements ConfigLoader, ConfigDelegate {

    private final Set<String> _names;

    private final Map<String, String> _properties;

    /**
     * Create new object.
     *
     * @param aliases           the property aliases, the key is the property name, the value is the alias.
     * @param excludeProperties the properties to exclude.
     */
    public SystemEnvironmentLoader(final Map<String, String> aliases, final Set<String> excludeProperties) {
        super(null, null, aliases, excludeProperties);
        _names = new HashSet<>();
        _properties = new HashMap<>();
    }

    @Override
    public void load() {
        Map<String, String> envProperties = System.getenv();
        fillStringMap(envProperties, _properties);
        replacePropertyAliases(_properties);
        excludeProperties(_properties);
        Set<String> names = _properties.keySet();
        fillStringSet(names, _names);
    }

    @Override
    public Set<String> getNames() {
        return new HashSet<>(_names);
    }

    @Override
    public String getProperty(final String name) {
        String propertyName = getFullPropertyName(name);
        return _properties.get(propertyName);
    }

}
