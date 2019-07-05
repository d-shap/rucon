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

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.rucon.loader.PropertiesResourceLoader;

/**
 * Tests for {@link ConfigurationLoader}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConfigurationLoaderTest {

    /**
     * Test class constructor.
     */
    public ConfigurationLoaderTest() {
        super();
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getNamesTest() {
        List<ConfigDelegate> configDelegates01 = null;
        ConfigurationLoader loader01 = new ConfigurationLoader(configDelegates01);
        Assertions.assertThat(loader01.getNames()).containsExactly();
        loader01.load();
        Assertions.assertThat(loader01.getNames()).containsExactly();

        List<ConfigDelegate> configDelegates02 = new ArrayList<>();
        ConfigurationLoader loader02 = new ConfigurationLoader(configDelegates02);
        Assertions.assertThat(loader02.getNames()).containsExactly();
        loader02.load();
        Assertions.assertThat(loader02.getNames()).containsExactly();

        List<ConfigDelegate> configDelegates03 = new ArrayList<>();
        configDelegates03.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", null));
        ConfigurationLoader loader03 = new ConfigurationLoader(configDelegates03);
        Assertions.assertThat(loader03.getNames()).containsExactly();
        loader03.load();
        Assertions.assertThat(loader03.getNames()).containsExactly("key1", "key2", "key3");

        List<ConfigDelegate> configDelegates04 = new ArrayList<>();
        configDelegates04.add(null);
        configDelegates04.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", null));
        configDelegates04.add(null);
        ConfigurationLoader loader04 = new ConfigurationLoader(configDelegates04);
        Assertions.assertThat(loader04.getNames()).containsExactly();
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsExactly("key1", "key2", "key3");

        List<ConfigDelegate> configDelegates05 = new ArrayList<>();
        configDelegates05.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", null));
        configDelegates05.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", null));
        ConfigurationLoader loader05 = new ConfigurationLoader(configDelegates05);
        Assertions.assertThat(loader05.getNames()).containsExactly();
        loader05.load();
        Assertions.assertThat(loader05.getNames()).containsExactly("key1", "key2", "key3", "key4");

        List<ConfigDelegate> configDelegates06 = new ArrayList<>();
        configDelegates06.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", null));
        configDelegates06.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", null));
        ConfigurationLoader loader06 = new ConfigurationLoader(configDelegates06);
        Assertions.assertThat(loader06.getNames()).containsExactly();
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsExactly("key1", "key2", "key3", "key4");
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyTest() {
        // TODO
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsStringTest() {
        // TODO
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsBooleanTest() {
        // TODO
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsIntTest() {
        // TODO
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsLongTest() {
        // TODO
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsFloatTest() {
        // TODO
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsDoubleTest() {
        // TODO
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsCharTest() {
        // TODO
    }

}
