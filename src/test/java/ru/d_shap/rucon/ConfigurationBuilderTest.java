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

import java.util.Map;
import java.util.Properties;

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
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateWithPrefixTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateWithPrefixSuffixTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateWithPrefixExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesDelegateWithPrefixSuffixExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderWithPrefixTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderWithPrefixSuffixTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderWithPrefixExcludeTest() {
        // TODO
    }

    /**
     * {@link ConfigurationBuilder} class test.
     */
    @Test
    public void addSystemPropertiesLoaderWithPrefixSuffixExcludeTest() {
        // TODO
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

}
