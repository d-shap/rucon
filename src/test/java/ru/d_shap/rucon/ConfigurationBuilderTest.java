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

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.rucon.delegate.PropertiesObjectDelegate;
import ru.d_shap.rucon.loader.PropertiesObjectLoader;

/**
 * Tests for {@link ConfigurationBuilder}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConfigurationBuilderTest {

    /**
     * Test class constructor.
     */
    public ConfigurationBuilderTest() {
        super();
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void newInstanceTest() {
        ConfigurationBuilder configurationBuilder1 = ConfigurationBuilder.newInstance();
        Assertions.assertThat(configurationBuilder1).isNotNull();
        ConfigurationBuilder configurationBuilder2 = ConfigurationBuilder.newInstance();
        Assertions.assertThat(configurationBuilder2).isNotNull();
        Assertions.assertThat(configurationBuilder1).isNotSameAs(configurationBuilder2);
        Assertions.assertThat(configurationBuilder2).isNotSameAs(configurationBuilder1);
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addConfigDelegateTest() {
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration1 = configurationBuilder.addConfigDelegate(new PropertiesObjectDelegate(createProperties(), null)).buildAndLoad();
        Assertions.assertThat(configuration1.getNames()).containsExactly("key1", "key2");
        Configuration configuration2 = configurationBuilder.addConfigDelegate(new PropertiesObjectLoader(createProperties(), null)).buildAndLoad();
        Assertions.assertThat(configuration2.getNames()).containsExactly("key1", "key2");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateTest() {
        String name = getPropertyName();
        System.setProperty(name, "value1");
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesDelegate().buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value1");
        System.setProperty(name, "value2");
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value2");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateWithPrefixTest() {
        String prefix = "prefix.";
        String name = getPropertyName();
        System.setProperty(prefix + name, "value1");
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesDelegate(prefix).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value1");
        System.setProperty(prefix + name, "value2");
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value2");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateWithPrefixSuffixTest() {
        String prefix = "prefix.";
        String suffix = ".suffix";
        String name = getPropertyName();
        System.setProperty(prefix + name + suffix, "value1");
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesDelegate(prefix, suffix).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value1");
        System.setProperty(prefix + name + suffix, "value2");
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value2");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateWithExcludeTest() {
        String name1 = getPropertyName();
        String name2 = getPropertyName();
        System.setProperty(name1, "value11");
        System.setProperty(name2, "value21");
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add(name1);
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesDelegate(excludeProperties).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value21");
        System.setProperty(name1, "value12");
        System.setProperty(name2, "value22");
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value22");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateWithPrefixExcludeTest() {
        String prefix = "prefix.";
        String name1 = getPropertyName();
        String name2 = getPropertyName();
        System.setProperty(prefix + name1, "value11");
        System.setProperty(prefix + name2, "value21");
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add(name1);
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesDelegate(prefix, excludeProperties).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value21");
        System.setProperty(prefix + name1, "value12");
        System.setProperty(prefix + name2, "value22");
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value22");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateWithPrefixSuffixExcludeTest() {
        String prefix = "prefix.";
        String suffix = ".suffix";
        String name1 = getPropertyName();
        String name2 = getPropertyName();
        System.setProperty(prefix + name1 + suffix, "value11");
        System.setProperty(prefix + name2 + suffix, "value21");
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add(name1);
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesDelegate(prefix, suffix, excludeProperties).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value21");
        System.setProperty(prefix + name1 + suffix, "value12");
        System.setProperty(prefix + name2 + suffix, "value22");
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value22");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderTest() {
        String name = getPropertyName();
        System.setProperty(name, "value1");
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesLoader().buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value1");
        System.setProperty(name, "value2");
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value1");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderWithPrefixTest() {
        String prefix = "prefix.";
        String name = getPropertyName();
        System.setProperty(prefix + name, "value1");
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesLoader(prefix).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value1");
        System.setProperty(prefix + name, "value2");
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value1");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderWithPrefixSuffixTest() {
        String prefix = "prefix.";
        String suffix = ".suffix";
        String name = getPropertyName();
        System.setProperty(prefix + name + suffix, "value1");
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesLoader(prefix, suffix).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value1");
        System.setProperty(prefix + name + suffix, "value2");
        Assertions.assertThat(configuration.getNames()).contains(name);
        Assertions.assertThat(configuration.getPropertyAsString(name, "default")).isEqualTo("value1");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderWithExcludeTest() {
        String name1 = getPropertyName();
        String name2 = getPropertyName();
        System.setProperty(name1, "value11");
        System.setProperty(name2, "value21");
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add(name1);
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesLoader(excludeProperties).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value21");
        System.setProperty(name1, "value12");
        System.setProperty(name2, "value22");
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value21");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderWithPrefixExcludeTest() {
        String prefix = "prefix.";
        String name1 = getPropertyName();
        String name2 = getPropertyName();
        System.setProperty(prefix + name1, "value11");
        System.setProperty(prefix + name2, "value21");
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add(name1);
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesLoader(prefix, excludeProperties).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value21");
        System.setProperty(prefix + name1, "value12");
        System.setProperty(prefix + name2, "value22");
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value21");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderWithPrefixSuffixExcludeTest() {
        String prefix = "prefix.";
        String suffix = ".suffix";
        String name1 = getPropertyName();
        String name2 = getPropertyName();
        System.setProperty(prefix + name1 + suffix, "value11");
        System.setProperty(prefix + name2 + suffix, "value21");
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add(name1);
        ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
        Configuration configuration = configurationBuilder.addSystemPropertiesLoader(prefix, suffix, excludeProperties).buildAndLoad();
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value21");
        System.setProperty(prefix + name1 + suffix, "value12");
        System.setProperty(prefix + name2 + suffix, "value22");
        Assertions.assertThat(configuration.getNames()).contains(name2);
        Assertions.assertThat(configuration.getPropertyAsString(name1, "default")).isEqualTo("default");
        Assertions.assertThat(configuration.getPropertyAsString(name2, "default")).isEqualTo("value21");
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemEnvironmentLoaderTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemEnvironmentLoaderWithAliasesTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemEnvironmentLoaderWithAliasesExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesResourceLoaderTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesResourceLoaderWithClassLoaderTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesResourceLoaderWithExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesResourceLoaderWithClassLoaderExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesFilePathLoaderTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesFilePathLoaderWithExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesSystemPropertyFileLoaderTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesSystemPropertyFileLoaderWithExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesObjectDelegateTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesObjectDelegateWithExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesObjectLoaderTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addPropertiesObjectLoaderWithExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void buildAndLoadTest() {
        // TODO
    }

    private Map<Object, Object> createProperties() {
        Map<Object, Object> properties = new Properties();
        properties.put("key1", "value1");
        properties.put("key2", "value2");
        return properties;
    }

    private String getPropertyName() {
        String name = getClass().getName() + "_name_";
        long time = new Date().getTime();
        long random = Math.round(Math.random() * 1000);
        return name + time + "_" + random;
    }

}
