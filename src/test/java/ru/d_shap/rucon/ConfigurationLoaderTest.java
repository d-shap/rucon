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
        Assertions.assertThat(loader12.getProperty("key11")).isNull();
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
        List<ConfigDelegate> configDelegates = new ArrayList<>();
        configDelegates.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config3.properties", null));
        ConfigurationLoader loader = new ConfigurationLoader(configDelegates);
        loader.load();
        Assertions.assertThat(loader.getPropertyAsString("key.wrongName", null)).isNull();
        Assertions.assertThat(loader.getPropertyAsString("key.wrongName", "default")).isEqualTo("default");
        Assertions.assertThat(loader.getPropertyAsString("key.empty", "default")).isEqualTo("");
        Assertions.assertThat(loader.getPropertyAsString("key.string", null)).isEqualTo("string");
        Assertions.assertThat(loader.getPropertyAsString("key.boolean", null)).isEqualTo("true");
        Assertions.assertThat(loader.getPropertyAsString("key.int", null)).isEqualTo("15");
        Assertions.assertThat(loader.getPropertyAsString("key.long", null)).isEqualTo("123456789011");
        Assertions.assertThat(loader.getPropertyAsString("key.float", null)).isEqualTo("10.0");
        Assertions.assertThat(loader.getPropertyAsString("key.double", null)).isEqualTo("5.234234e245");
        Assertions.assertThat(loader.getPropertyAsString("key.char", null)).isEqualTo("char");
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsBooleanTest() {
        List<ConfigDelegate> configDelegates = new ArrayList<>();
        configDelegates.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config3.properties", null));
        ConfigurationLoader loader = new ConfigurationLoader(configDelegates);
        loader.load();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.wrongName", true)).isTrue();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.wrongName", false)).isFalse();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.empty", true)).isFalse();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.string", false)).isFalse();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.boolean", false)).isTrue();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.int", false)).isFalse();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.long", false)).isFalse();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.float", false)).isFalse();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.double", false)).isFalse();
        Assertions.assertThat(loader.getPropertyAsBoolean("key.char", false)).isFalse();
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsIntTest() {
        List<ConfigDelegate> configDelegates = new ArrayList<>();
        configDelegates.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config3.properties", null));
        ConfigurationLoader loader = new ConfigurationLoader(configDelegates);
        loader.load();
        Assertions.assertThat(loader.getPropertyAsInt("key.wrongName", 0)).isEqualTo(0);
        Assertions.assertThat(loader.getPropertyAsInt("key.wrongName", 10)).isEqualTo(10);
        try {
            loader.getPropertyAsInt("key.empty", 10);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"\"");
        }
        try {
            loader.getPropertyAsInt("key.string", 0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"string\"");
        }
        try {
            loader.getPropertyAsInt("key.boolean", 0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"true\"");
        }
        Assertions.assertThat(loader.getPropertyAsInt("key.int", 0)).isEqualTo(15);
        try {
            loader.getPropertyAsInt("key.long", 0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"123456789011\"");
        }
        try {
            loader.getPropertyAsInt("key.float", 0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"10.0\"");
        }
        try {
            loader.getPropertyAsInt("key.double", 0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"5.234234e245\"");
        }
        try {
            loader.getPropertyAsInt("key.char", 0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"char\"");
        }
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsLongTest() {
        List<ConfigDelegate> configDelegates = new ArrayList<>();
        configDelegates.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config3.properties", null));
        ConfigurationLoader loader = new ConfigurationLoader(configDelegates);
        loader.load();
        Assertions.assertThat(loader.getPropertyAsLong("key.wrongName", 0L)).isEqualTo(0L);
        Assertions.assertThat(loader.getPropertyAsLong("key.wrongName", 10L)).isEqualTo(10L);
        try {
            loader.getPropertyAsLong("key.empty", 10L);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"\"");
        }
        try {
            loader.getPropertyAsLong("key.string", 0L);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"string\"");
        }
        try {
            loader.getPropertyAsLong("key.boolean", 0L);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"true\"");
        }
        Assertions.assertThat(loader.getPropertyAsLong("key.int", 0L)).isEqualTo(15L);
        Assertions.assertThat(loader.getPropertyAsLong("key.long", 0L)).isEqualTo(123456789011L);
        try {
            loader.getPropertyAsLong("key.float", 0L);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"10.0\"");
        }
        try {
            loader.getPropertyAsLong("key.double", 0L);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"5.234234e245\"");
        }
        try {
            loader.getPropertyAsLong("key.char", 0L);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"char\"");
        }
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsFloatTest() {
        List<ConfigDelegate> configDelegates = new ArrayList<>();
        configDelegates.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config3.properties", null));
        ConfigurationLoader loader = new ConfigurationLoader(configDelegates);
        loader.load();
        Assertions.assertThat(loader.getPropertyAsFloat("key.wrongName", 0.0f)).isEqualTo(0.0f);
        Assertions.assertThat(loader.getPropertyAsFloat("key.wrongName", 10.0f)).isEqualTo(10.0f);
        try {
            loader.getPropertyAsFloat("key.empty", 10.0f);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("empty String");
        }
        try {
            loader.getPropertyAsFloat("key.string", 0.0f);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"string\"");
        }
        try {
            loader.getPropertyAsFloat("key.boolean", 0.0f);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"true\"");
        }
        Assertions.assertThat(loader.getPropertyAsFloat("key.int", 0.0f)).isEqualTo(15.0f);
        Assertions.assertThat(loader.getPropertyAsFloat("key.long", 0.0f)).isEqualTo(1.23456791E11f);
        Assertions.assertThat(loader.getPropertyAsFloat("key.float", 0.0f)).isEqualTo(10.0f);
        Assertions.assertThat(loader.getPropertyAsFloat("key.double", 0.0f)).isInfinity();
        try {
            loader.getPropertyAsFloat("key.char", 0.0f);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"char\"");
        }
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsDoubleTest() {
        List<ConfigDelegate> configDelegates = new ArrayList<>();
        configDelegates.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config3.properties", null));
        ConfigurationLoader loader = new ConfigurationLoader(configDelegates);
        loader.load();
        Assertions.assertThat(loader.getPropertyAsDouble("key.wrongName", 0.0)).isEqualTo(0.0);
        Assertions.assertThat(loader.getPropertyAsDouble("key.wrongName", 10.0)).isEqualTo(10.0);
        try {
            loader.getPropertyAsDouble("key.empty", 10.0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("empty String");
        }
        try {
            loader.getPropertyAsDouble("key.string", 0.0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"string\"");
        }
        try {
            loader.getPropertyAsDouble("key.boolean", 0.0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"true\"");
        }
        Assertions.assertThat(loader.getPropertyAsDouble("key.int", 0.0)).isEqualTo(15.0);
        Assertions.assertThat(loader.getPropertyAsDouble("key.long", 0.0)).isEqualTo(1.23456789011e11);
        Assertions.assertThat(loader.getPropertyAsDouble("key.float", 0.0)).isEqualTo(10.0);
        Assertions.assertThat(loader.getPropertyAsDouble("key.double", 0.0)).isEqualTo(5.234234e245);
        try {
            loader.getPropertyAsDouble("key.char", 0.0);
            Assertions.fail("ConfigurationLoader test fail");
        } catch (NumberFormatException ex) {
            Assertions.assertThat(ex).hasMessage("For input string: \"char\"");
        }
    }

    /**
     * {@link ConfigurationLoader} class test.
     */
    @Test
    public void getPropertyAsCharTest() {
        List<ConfigDelegate> configDelegates = new ArrayList<>();
        configDelegates.add(new PropertiesResourceLoader(getClass().getClassLoader(), "config3.properties", null));
        ConfigurationLoader loader = new ConfigurationLoader(configDelegates);
        loader.load();
        Assertions.assertThat(loader.getPropertyAsChar("key.wrongName", (char) 0)).isEqualTo(0);
        Assertions.assertThat(loader.getPropertyAsChar("key.wrongName", 'a')).isEqualTo('a');
        Assertions.assertThat(loader.getPropertyAsChar("key.empty", 'a')).isEqualTo('a');
        Assertions.assertThat(loader.getPropertyAsChar("key.string", (char) 0)).isEqualTo('s');
        Assertions.assertThat(loader.getPropertyAsChar("key.boolean", (char) 0)).isEqualTo('t');
        Assertions.assertThat(loader.getPropertyAsChar("key.int", (char) 0)).isEqualTo('1');
        Assertions.assertThat(loader.getPropertyAsChar("key.long", (char) 0)).isEqualTo('1');
        Assertions.assertThat(loader.getPropertyAsChar("key.float", (char) 0)).isEqualTo('1');
        Assertions.assertThat(loader.getPropertyAsChar("key.double", (char) 0)).isEqualTo('5');
        Assertions.assertThat(loader.getPropertyAsChar("key.char", (char) 0)).isEqualTo('c');
    }

}
