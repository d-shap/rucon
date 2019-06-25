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

    /**
     * Add the specified configuration loader.
     *
     * @param configLoader the specified configuration loader.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addConfigLoader(final ConfigLoader configLoader) {
        _configLoaders.add(configLoader);
        return this;
    }

    /**
     * Add configuration loader for the system properties.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesLoader() {
        ConfigLoader configLoader = new SystemPropertiesLoader(null, null, null);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the system properties.
     *
     * @param prefix the prefix to add to the property name.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesLoader(final String prefix) {
        ConfigLoader configLoader = new SystemPropertiesLoader(prefix, null, null);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the system properties.
     *
     * @param prefix the prefix to add to the property name.
     * @param suffix the suffix to add to the property name.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesLoader(final String prefix, final String suffix) {
        ConfigLoader configLoader = new SystemPropertiesLoader(prefix, suffix, null);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the system properties.
     *
     * @param prefix            the prefix to add to the property name.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesLoader(final String prefix, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new SystemPropertiesLoader(prefix, null, excludeProperties);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the system properties.
     *
     * @param prefix            the prefix to add to the property name.
     * @param suffix            the suffix to add to the property name.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesLoader(final String prefix, final String suffix, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new SystemPropertiesLoader(prefix, suffix, excludeProperties);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the system environment.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemEnvironmentLoader() {
        ConfigLoader configLoader = new SystemEnvironmentLoader(null, null);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the system environment.
     *
     * @param aliases the property aliases, the key is the property name, the value is the alias.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemEnvironmentLoader(final Map<String, String> aliases) {
        ConfigLoader configLoader = new SystemEnvironmentLoader(aliases, null);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the system environment.
     *
     * @param aliases           the property aliases, the key is the property name, the value is the alias.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemEnvironmentLoader(final Map<String, String> aliases, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new SystemEnvironmentLoader(aliases, excludeProperties);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param classLoader the class loader to load the resource.
     * @param resource    the resource.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesLoader(final ClassLoader classLoader, final String resource) {
        ConfigLoader configLoader = new PropertiesResourceLoader(classLoader, resource, null);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param classLoader       the class loader to load the resource.
     * @param resource          the resource.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesLoader(final ClassLoader classLoader, final String resource, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new PropertiesResourceLoader(classLoader, resource, excludeProperties);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param properties the properties object.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesLoader(final Map<Object, Object> properties) {
        ConfigLoader configLoader = new PropertiesObjectLoader(properties, null);
        return addConfigLoader(configLoader);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param properties        the properties object.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesLoader(final Map<Object, Object> properties, final Set<String> excludeProperties) {
        ConfigLoader configLoader = new PropertiesObjectLoader(properties, excludeProperties);
        return addConfigLoader(configLoader);
    }

    /**
     * Builder the configuration instance and load all properties.
     *
     * @return the configuration instance.
     */
    public Configuration buildAndLoad() {
        ConfigurationLoader configuration = new ConfigurationLoader(_configLoaders);
        _configLoaders.clear();
        configuration.load();
        return configuration;
    }

}
