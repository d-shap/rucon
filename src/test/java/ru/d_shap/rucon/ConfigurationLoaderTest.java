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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.rucon.delegate.PropertiesObjectDelegate;
import ru.d_shap.rucon.loader.PropertiesObjectLoader;
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

        List<ConfigDelegate> configDelegates07 = new ArrayList<>();
        configDelegates07.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", new HashSet<String>()));
        configDelegates07.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", new HashSet<String>()));
        ConfigurationLoader loader07 = new ConfigurationLoader(configDelegates07);
        Assertions.assertThat(loader07.getNames()).containsExactly();
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key1", "key2", "key3", "key4");

        List<ConfigDelegate> configDelegates08 = new ArrayList<>();
        Set<String> excludeProperties081 = new HashSet<>();
        excludeProperties081.add("key2");
        configDelegates08.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties081));
        Set<String> excludeProperties082 = new HashSet<>();
        excludeProperties082.add("key4");
        configDelegates08.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", excludeProperties082));
        ConfigurationLoader loader08 = new ConfigurationLoader(configDelegates08);
        Assertions.assertThat(loader08.getNames()).containsExactly();
        loader08.load();
        Assertions.assertThat(loader08.getNames()).containsExactly("key1", "key3");

        List<ConfigDelegate> configDelegates09 = new ArrayList<>();
        Set<String> excludeProperties091 = new HashSet<>();
        excludeProperties091.add("key2");
        configDelegates09.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", excludeProperties091));
        Set<String> excludeProperties092 = new HashSet<>();
        excludeProperties092.add("key4");
        configDelegates09.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties092));
        ConfigurationLoader loader09 = new ConfigurationLoader(configDelegates09);
        Assertions.assertThat(loader09.getNames()).containsExactly();
        loader09.load();
        Assertions.assertThat(loader09.getNames()).containsExactly("key1", "key2", "key3", "key4");

        List<ConfigDelegate> configDelegates10 = new ArrayList<>();
        Set<String> excludeProperties101 = new HashSet<>();
        excludeProperties101.add("key1");
        configDelegates10.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties101));
        Set<String> excludeProperties102 = new HashSet<>();
        excludeProperties102.add("key3");
        configDelegates10.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", excludeProperties102));
        ConfigurationLoader loader10 = new ConfigurationLoader(configDelegates10);
        Assertions.assertThat(loader10.getNames()).containsExactly();
        loader10.load();
        Assertions.assertThat(loader10.getNames()).containsExactly("key1", "key2", "key3", "key4");

        List<ConfigDelegate> configDelegates11 = new ArrayList<>();
        Set<String> excludeProperties111 = new HashSet<>();
        excludeProperties111.add("key1");
        configDelegates11.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", excludeProperties111));
        Set<String> excludeProperties112 = new HashSet<>();
        excludeProperties112.add("key3");
        configDelegates11.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties112));
        ConfigurationLoader loader11 = new ConfigurationLoader(configDelegates11);
        Assertions.assertThat(loader11.getNames()).containsExactly();
        loader11.load();
        Assertions.assertThat(loader11.getNames()).containsExactly("key1", "key2", "key3", "key4");

        List<ConfigDelegate> configDelegates12 = new ArrayList<>();
        Map<Object, Object> properties121 = new Properties();
        properties121.put("key11", "value11");
        configDelegates12.add(new PropertiesObjectLoader(properties121, null));
        Map<Object, Object> properties122 = new Properties();
        properties122.put("key21", "value21");
        configDelegates12.add(new PropertiesObjectDelegate(properties122, null));
        ConfigurationLoader loader12 = new ConfigurationLoader(configDelegates12);
        Assertions.assertThat(loader12.getNames()).containsExactly("key21");
        loader12.load();
        Assertions.assertThat(loader12.getNames()).containsExactly("key11", "key21");
        properties121.put("key12", "value12");
        properties122.put("key22", "value22");
        Assertions.assertThat(loader12.getNames()).containsExactly("key11", "key21", "key22");
        loader12.load();
        Assertions.assertThat(loader12.getNames()).containsExactly("key11", "key21", "key22");
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyTest() {
        List<ConfigDelegate> configDelegates01 = null;
        ConfigurationLoader loader01 = new ConfigurationLoader(configDelegates01);
        Assertions.assertThat(loader01.getProperty("key1")).isNull();
        Assertions.assertThat(loader01.getProperty("key2")).isNull();
        Assertions.assertThat(loader01.getProperty("key3")).isNull();
        Assertions.assertThat(loader01.getProperty("key4")).isNull();
        loader01.load();
        Assertions.assertThat(loader01.getProperty("key1")).isNull();
        Assertions.assertThat(loader01.getProperty("key2")).isNull();
        Assertions.assertThat(loader01.getProperty("key3")).isNull();
        Assertions.assertThat(loader01.getProperty("key4")).isNull();

        List<ConfigDelegate> configDelegates02 = new ArrayList<>();
        ConfigurationLoader loader02 = new ConfigurationLoader(configDelegates02);
        Assertions.assertThat(loader02.getProperty("key1")).isNull();
        Assertions.assertThat(loader02.getProperty("key2")).isNull();
        Assertions.assertThat(loader02.getProperty("key3")).isNull();
        Assertions.assertThat(loader02.getProperty("key4")).isNull();
        loader02.load();
        Assertions.assertThat(loader02.getProperty("key1")).isNull();
        Assertions.assertThat(loader02.getProperty("key2")).isNull();
        Assertions.assertThat(loader02.getProperty("key3")).isNull();
        Assertions.assertThat(loader02.getProperty("key4")).isNull();

        List<ConfigDelegate> configDelegates03 = new ArrayList<>();
        configDelegates03.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", null));
        ConfigurationLoader loader03 = new ConfigurationLoader(configDelegates03);
        Assertions.assertThat(loader03.getProperty("key1")).isNull();
        Assertions.assertThat(loader03.getProperty("key2")).isNull();
        Assertions.assertThat(loader03.getProperty("key3")).isNull();
        Assertions.assertThat(loader03.getProperty("key4")).isNull();
        loader03.load();
        Assertions.assertThat(loader03.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader03.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader03.getProperty("key3")).isEqualTo("value1-3");
        Assertions.assertThat(loader03.getProperty("key4")).isNull();

        List<ConfigDelegate> configDelegates04 = new ArrayList<>();
        configDelegates04.add(null);
        configDelegates04.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", null));
        configDelegates04.add(null);
        ConfigurationLoader loader04 = new ConfigurationLoader(configDelegates04);
        Assertions.assertThat(loader04.getProperty("key1")).isNull();
        Assertions.assertThat(loader04.getProperty("key2")).isNull();
        Assertions.assertThat(loader04.getProperty("key3")).isNull();
        Assertions.assertThat(loader04.getProperty("key4")).isNull();
        loader04.load();
        Assertions.assertThat(loader04.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader04.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader04.getProperty("key3")).isEqualTo("value1-3");
        Assertions.assertThat(loader04.getProperty("key4")).isNull();

        List<ConfigDelegate> configDelegates05 = new ArrayList<>();
        configDelegates05.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", null));
        configDelegates05.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", null));
        ConfigurationLoader loader05 = new ConfigurationLoader(configDelegates05);
        Assertions.assertThat(loader05.getProperty("key1")).isNull();
        Assertions.assertThat(loader05.getProperty("key2")).isNull();
        Assertions.assertThat(loader05.getProperty("key3")).isNull();
        Assertions.assertThat(loader05.getProperty("key4")).isNull();
        loader05.load();
        Assertions.assertThat(loader05.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader05.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader05.getProperty("key3")).isEqualTo("value1-3");
        Assertions.assertThat(loader05.getProperty("key4")).isEqualTo("value2-4");

        List<ConfigDelegate> configDelegates06 = new ArrayList<>();
        configDelegates06.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", null));
        configDelegates06.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", null));
        ConfigurationLoader loader06 = new ConfigurationLoader(configDelegates06);
        Assertions.assertThat(loader06.getProperty("key1")).isNull();
        Assertions.assertThat(loader06.getProperty("key2")).isNull();
        Assertions.assertThat(loader06.getProperty("key3")).isNull();
        Assertions.assertThat(loader06.getProperty("key4")).isNull();
        loader06.load();
        Assertions.assertThat(loader06.getProperty("key1")).isEqualTo("value2-1");
        Assertions.assertThat(loader06.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader06.getProperty("key3")).isEqualTo("value2-3");
        Assertions.assertThat(loader06.getProperty("key4")).isEqualTo("value2-4");

        List<ConfigDelegate> configDelegates07 = new ArrayList<>();
        configDelegates07.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", new HashSet<String>()));
        configDelegates07.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", new HashSet<String>()));
        ConfigurationLoader loader07 = new ConfigurationLoader(configDelegates07);
        Assertions.assertThat(loader07.getProperty("key1")).isNull();
        Assertions.assertThat(loader07.getProperty("key2")).isNull();
        Assertions.assertThat(loader07.getProperty("key3")).isNull();
        Assertions.assertThat(loader07.getProperty("key4")).isNull();
        loader07.load();
        Assertions.assertThat(loader07.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader07.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader07.getProperty("key3")).isEqualTo("value1-3");
        Assertions.assertThat(loader07.getProperty("key4")).isEqualTo("value2-4");

        List<ConfigDelegate> configDelegates08 = new ArrayList<>();
        Set<String> excludeProperties081 = new HashSet<>();
        excludeProperties081.add("key2");
        configDelegates08.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties081));
        Set<String> excludeProperties082 = new HashSet<>();
        excludeProperties082.add("key4");
        configDelegates08.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", excludeProperties082));
        ConfigurationLoader loader08 = new ConfigurationLoader(configDelegates08);
        Assertions.assertThat(loader08.getProperty("key1")).isNull();
        Assertions.assertThat(loader08.getProperty("key2")).isNull();
        Assertions.assertThat(loader08.getProperty("key3")).isNull();
        Assertions.assertThat(loader08.getProperty("key4")).isNull();
        loader08.load();
        Assertions.assertThat(loader08.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader08.getProperty("key2")).isNull();
        Assertions.assertThat(loader08.getProperty("key3")).isEqualTo("value1-3");
        Assertions.assertThat(loader08.getProperty("key4")).isNull();

        List<ConfigDelegate> configDelegates09 = new ArrayList<>();
        Set<String> excludeProperties091 = new HashSet<>();
        excludeProperties091.add("key2");
        configDelegates09.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", excludeProperties091));
        Set<String> excludeProperties092 = new HashSet<>();
        excludeProperties092.add("key4");
        configDelegates09.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties092));
        ConfigurationLoader loader09 = new ConfigurationLoader(configDelegates09);
        Assertions.assertThat(loader09.getProperty("key1")).isNull();
        Assertions.assertThat(loader09.getProperty("key2")).isNull();
        Assertions.assertThat(loader09.getProperty("key3")).isNull();
        Assertions.assertThat(loader09.getProperty("key4")).isNull();
        loader09.load();
        Assertions.assertThat(loader09.getProperty("key1")).isEqualTo("value2-1");
        Assertions.assertThat(loader09.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader09.getProperty("key3")).isEqualTo("value2-3");
        Assertions.assertThat(loader09.getProperty("key4")).isEqualTo("value2-4");

        List<ConfigDelegate> configDelegates10 = new ArrayList<>();
        Set<String> excludeProperties101 = new HashSet<>();
        excludeProperties101.add("key1");
        configDelegates10.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties101));
        Set<String> excludeProperties102 = new HashSet<>();
        excludeProperties102.add("key3");
        configDelegates10.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", excludeProperties102));
        ConfigurationLoader loader10 = new ConfigurationLoader(configDelegates10);
        Assertions.assertThat(loader10.getProperty("key1")).isNull();
        Assertions.assertThat(loader10.getProperty("key2")).isNull();
        Assertions.assertThat(loader10.getProperty("key3")).isNull();
        Assertions.assertThat(loader10.getProperty("key4")).isNull();
        loader10.load();
        Assertions.assertThat(loader10.getProperty("key1")).isEqualTo("value2-1");
        Assertions.assertThat(loader10.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader10.getProperty("key3")).isEqualTo("value1-3");
        Assertions.assertThat(loader10.getProperty("key4")).isEqualTo("value2-4");

        List<ConfigDelegate> configDelegates11 = new ArrayList<>();
        Set<String> excludeProperties111 = new HashSet<>();
        excludeProperties111.add("key1");
        configDelegates11.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config2.properties", excludeProperties111));
        Set<String> excludeProperties112 = new HashSet<>();
        excludeProperties112.add("key3");
        configDelegates11.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties112));
        ConfigurationLoader loader11 = new ConfigurationLoader(configDelegates11);
        Assertions.assertThat(loader11.getProperty("key1")).isNull();
        Assertions.assertThat(loader11.getProperty("key2")).isNull();
        Assertions.assertThat(loader11.getProperty("key3")).isNull();
        Assertions.assertThat(loader11.getProperty("key4")).isNull();
        loader11.load();
        Assertions.assertThat(loader11.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader11.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader11.getProperty("key3")).isEqualTo("value2-3");
        Assertions.assertThat(loader11.getProperty("key4")).isEqualTo("value2-4");

        List<ConfigDelegate> configDelegates12 = new ArrayList<>();
        Map<Object, Object> properties121 = new Properties();
        properties121.put("key11", "value11");
        configDelegates12.add(new PropertiesObjectLoader(properties121, null));
        Map<Object, Object> properties122 = new Properties();
        properties122.put("key21", "value21");
        configDelegates12.add(new PropertiesObjectDelegate(properties122, null));
        ConfigurationLoader loader12 = new ConfigurationLoader(configDelegates12);
        Assertions.assertThat(loader12.getProperty("key11")).isEqualTo("value11");
        Assertions.assertThat(loader12.getProperty("key12")).isNull();
        Assertions.assertThat(loader12.getProperty("key21")).isEqualTo("value21");
        Assertions.assertThat(loader12.getProperty("key22")).isNull();
        loader12.load();
        Assertions.assertThat(loader12.getProperty("key11")).isEqualTo("value11");
        Assertions.assertThat(loader12.getProperty("key12")).isNull();
        Assertions.assertThat(loader12.getProperty("key21")).isEqualTo("value21");
        Assertions.assertThat(loader12.getProperty("key22")).isNull();
        properties121.put("key12", "value12");
        properties122.put("key22", "value22");
        Assertions.assertThat(loader12.getProperty("key11")).isEqualTo("value11");
        Assertions.assertThat(loader12.getProperty("key12")).isNull();
        Assertions.assertThat(loader12.getProperty("key21")).isEqualTo("value21");
        Assertions.assertThat(loader12.getProperty("key22")).isEqualTo("value22");
        loader12.load();
        Assertions.assertThat(loader12.getProperty("key11")).isEqualTo("value11");
        Assertions.assertThat(loader12.getProperty("key12")).isNull();
        Assertions.assertThat(loader12.getProperty("key21")).isEqualTo("value21");
        Assertions.assertThat(loader12.getProperty("key22")).isEqualTo("value22");
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
