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

import ru.d_shap.rucon.delegate.PropertiesObjectDelegate;
import ru.d_shap.rucon.delegate.SystemPropertiesDelegate;
import ru.d_shap.rucon.loader.PropertiesFilePathLoader;
import ru.d_shap.rucon.loader.PropertiesObjectLoader;
import ru.d_shap.rucon.loader.PropertiesResourceLoader;
import ru.d_shap.rucon.loader.PropertiesSystemPropertyFileLoader;
import ru.d_shap.rucon.loader.SystemEnvironmentLoader;
import ru.d_shap.rucon.loader.SystemPropertiesLoader;

/**
 * Builder for the application configuration.
 *
 * @author Dmitry Shapovalov
 */
public final class ConfigurationBuilder {

    private final List<ConfigDelegate> _configDelegates;

    private ConfigurationBuilder() {
        super();
        _configDelegates = new ArrayList<>();
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
     * Add the specified configuration delegate.
     *
     * @param configDelegate the specified configuration delegate.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addConfigDelegate(final ConfigDelegate configDelegate) {
        _configDelegates.add(configDelegate);
        return this;
    }

    /**
     * Add configuration delegate for the system properties.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesDelegate() {
        ConfigDelegate configDelegate = new SystemPropertiesDelegate(null, null, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration delegate for the system properties.
     *
     * @param prefix the prefix to add to the property name.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesDelegate(final String prefix) {
        ConfigDelegate configDelegate = new SystemPropertiesDelegate(prefix, null, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration delegate for the system properties.
     *
     * @param prefix the prefix to add to the property name.
     * @param suffix the suffix to add to the property name.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesDelegate(final String prefix, final String suffix) {
        ConfigDelegate configDelegate = new SystemPropertiesDelegate(prefix, suffix, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration delegate for the system properties.
     *
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesDelegate(final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new SystemPropertiesDelegate(null, null, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration delegate for the system properties.
     *
     * @param prefix            the prefix to add to the property name.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesDelegate(final String prefix, final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new SystemPropertiesDelegate(prefix, null, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration delegate for the system properties.
     *
     * @param prefix            the prefix to add to the property name.
     * @param suffix            the suffix to add to the property name.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesDelegate(final String prefix, final String suffix, final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new SystemPropertiesDelegate(prefix, suffix, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the system properties.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesLoader() {
        ConfigDelegate configDelegate = new SystemPropertiesLoader(null, null, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the system properties.
     *
     * @param prefix the prefix to add to the property name.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesLoader(final String prefix) {
        ConfigDelegate configDelegate = new SystemPropertiesLoader(prefix, null, null);
        return addConfigDelegate(configDelegate);
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
        ConfigDelegate configDelegate = new SystemPropertiesLoader(prefix, suffix, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the system properties.
     *
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemPropertiesLoader(final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new SystemPropertiesLoader(null, null, excludeProperties);
        return addConfigDelegate(configDelegate);
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
        ConfigDelegate configDelegate = new SystemPropertiesLoader(prefix, null, excludeProperties);
        return addConfigDelegate(configDelegate);
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
        ConfigDelegate configDelegate = new SystemPropertiesLoader(prefix, suffix, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the system environment.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemEnvironmentLoader() {
        ConfigDelegate configDelegate = new SystemEnvironmentLoader(null, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the system environment.
     *
     * @param aliases the property aliases, the key is the property name, the value is the alias.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addSystemEnvironmentLoader(final Map<String, String> aliases) {
        ConfigDelegate configDelegate = new SystemEnvironmentLoader(aliases, null);
        return addConfigDelegate(configDelegate);
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
        ConfigDelegate configDelegate = new SystemEnvironmentLoader(aliases, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param resource the resource.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesResourceLoader(final String resource) {
        ConfigDelegate configDelegate = new PropertiesResourceLoader(getClass().getClassLoader(), resource, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param classLoader the class loader to load the resource.
     * @param resource    the resource.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesResourceLoader(final ClassLoader classLoader, final String resource) {
        ConfigDelegate configDelegate = new PropertiesResourceLoader(classLoader, resource, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param resource          the resource.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesResourceLoader(final String resource, final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new PropertiesResourceLoader(getClass().getClassLoader(), resource, excludeProperties);
        return addConfigDelegate(configDelegate);
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
    public ConfigurationBuilder addPropertiesResourceLoader(final ClassLoader classLoader, final String resource, final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new PropertiesResourceLoader(classLoader, resource, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param filePath the path to the properties file.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesFilePathLoader(final String filePath) {
        ConfigDelegate configDelegate = new PropertiesFilePathLoader(filePath, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param filePath          the path to the properties file.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesFilePathLoader(final String filePath, final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new PropertiesFilePathLoader(filePath, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param name the name of the property with the path to the properties file.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesSystemPropertyFileLoader(final String name) {
        ConfigDelegate configDelegate = new PropertiesSystemPropertyFileLoader(name, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param name              the name of the property with the path to the properties file.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesSystemPropertyFileLoader(final String name, final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new PropertiesSystemPropertyFileLoader(name, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration delegate for the properties.
     *
     * @param properties the properties object.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesObjectDelegate(final Map<Object, Object> properties) {
        ConfigDelegate configDelegate = new PropertiesObjectDelegate(properties, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration delegate for the properties.
     *
     * @param properties        the properties object.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesObjectDelegate(final Map<Object, Object> properties, final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new PropertiesObjectDelegate(properties, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param properties the properties object.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesObjectLoader(final Map<Object, Object> properties) {
        ConfigDelegate configDelegate = new PropertiesObjectLoader(properties, null);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Add configuration loader for the properties.
     *
     * @param properties        the properties object.
     * @param excludeProperties the properties to exclude.
     *
     * @return current object for the method chaining.
     */
    public ConfigurationBuilder addPropertiesObjectLoader(final Map<Object, Object> properties, final Set<String> excludeProperties) {
        ConfigDelegate configDelegate = new PropertiesObjectLoader(properties, excludeProperties);
        return addConfigDelegate(configDelegate);
    }

    /**
     * Builder the configuration instance and load all properties.
     *
     * @return the configuration instance.
     */
    public Configuration buildAndLoad() {
        ConfigurationLoader configuration = new ConfigurationLoader(_configDelegates);
        _configDelegates.clear();
        configuration.load();
        return configuration;
    }

}
