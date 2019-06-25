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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Builder for the application configuration.
 *
 * @author Dmitry Shapovalov
 */
public final class ConfigurationBuilder {

    private final List<ConfigLoader> _configLoaders;

    private ConfigurationBuilder() {
        super();
        _configLoaders = new ArrayList<>();
    }

    /**
     * Create new instance of the application configuration builder.
     *
     * @return new instance of the application configuration builder.
     */
    public static ConfigurationBuilder newInstance() {
        return new ConfigurationBuilder();
    }

    public ConfigurationBuilder addSystemPropertiesLoader() {
        ConfigLoader configLoader = new SystemPropertiesLoader(null, null, null);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addSystemPropertiesLoader(final String prefix) {
        ConfigLoader configLoader = new SystemPropertiesLoader(prefix, null, null);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addSystemPropertiesLoader(final String prefix, final String suffix) {
        ConfigLoader configLoader = new SystemPropertiesLoader(prefix, suffix, null);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addSystemPropertiesLoader(final String prefix, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new SystemPropertiesLoader(prefix, null, excludeProperties);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addSystemPropertiesLoader(final String prefix, final String suffix, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new SystemPropertiesLoader(prefix, suffix, excludeProperties);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addSystemEnvironmentLoader() {
        ConfigLoader configLoader = new SystemEnvironmentLoader(null, null);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addSystemEnvironmentLoader(final Map<String, String> aliases) {
        ConfigLoader configLoader = new SystemEnvironmentLoader(aliases, null);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addSystemEnvironmentLoader(final Map<String, String> aliases, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new SystemEnvironmentLoader(aliases, excludeProperties);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addPropertiesLoader(final ClassLoader classLoader, final String resource) {
        ConfigLoader configLoader = new PropertiesResourceLoader(classLoader, resource, null);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addPropertiesLoader(final ClassLoader classLoader, final String resource, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new PropertiesResourceLoader(classLoader, resource, excludeProperties);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addPropertiesLoader(final Map<Object, Object> properties) {
        ConfigLoader configLoader = new PropertiesObjectLoader(properties, null);
        _configLoaders.add(configLoader);
        return this;
    }

    public ConfigurationBuilder addPropertiesLoader(final Map<Object, Object> properties, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new PropertiesObjectLoader(properties, excludeProperties);
        _configLoaders.add(configLoader);
        return this;
    }

    public Configuration buildAndLoad() {
        ConfigurationLoader configuration = new ConfigurationLoader(_configLoaders);
        _configLoaders.clear();
        configuration.load();
        return configuration;
    }

}
