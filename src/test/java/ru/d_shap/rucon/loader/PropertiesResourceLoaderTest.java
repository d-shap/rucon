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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link PropertiesResourceLoader}.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesResourceLoaderTest {

    /**
     * Test class constructor.
     */
    public PropertiesResourceLoaderTest() {
        super();
    }

    /**
     * {@link PropertiesResourceLoader} class test.
     */
    @Test
    public void getNamesTest() {
        Set<String> excludeProperties01 = null;
        PropertiesResourceLoader loader01 = new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties01);
        Assertions.assertThat(loader01.getNames()).containsExactly();
        loader01.load();
        Assertions.assertThat(loader01.getNames()).containsExactly("key1", "key2", "key3");

        Set<String> excludeProperties02 = new HashSet<>();
        PropertiesResourceLoader loader02 = new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties02);
        Assertions.assertThat(loader02.getNames()).containsExactly();
        loader02.load();
        Assertions.assertThat(loader02.getNames()).containsExactly("key1", "key2", "key3");

        Set<String> excludeProperties03 = new HashSet<>();
        excludeProperties03.add("key1");
        PropertiesResourceLoader loader03 = new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties03);
        Assertions.assertThat(loader03.getNames()).containsExactly();
        loader03.load();
        Assertions.assertThat(loader03.getNames()).containsExactly("key2", "key3");

        Set<String> excludeProperties04 = null;
        PropertiesResourceLoader loader04 = new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties04);
        Assertions.assertThat(loader04.getNames()).containsExactly();
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsExactly("key1", "key2", "key3");
        loader04.getNames().add("key");
        Assertions.assertThat(loader04.getNames()).containsExactly("key1", "key2", "key3");
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsExactly("key1", "key2", "key3");

        Set<String> excludeProperties05 = new HashSet<>();
        excludeProperties05.add("key1");
        PropertiesResourceLoader loader05 = new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties05);
        Assertions.assertThat(loader05.getNames()).containsExactly();
        loader05.load();
        Assertions.assertThat(loader05.getNames()).containsExactly("key2", "key3");
        excludeProperties05.add("key2");
        Assertions.assertThat(loader05.getNames()).containsExactly("key2", "key3");
        loader05.load();
        Assertions.assertThat(loader05.getNames()).containsExactly("key2", "key3");
    }

    /**
     * {@link PropertiesResourceLoader} class test.
     */
    @Test
    public void getPropertyTest() {
        Set<String> excludeProperties01 = null;
        PropertiesResourceLoader loader01 = new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties01);
        Assertions.assertThat(loader01.getProperty(null)).isNull();
        Assertions.assertThat(loader01.getProperty("")).isNull();
        Assertions.assertThat(loader01.getProperty(" ")).isNull();
        Assertions.assertThat(loader01.getProperty("key")).isNull();
        Assertions.assertThat(loader01.getProperty("key1")).isNull();
        Assertions.assertThat(loader01.getProperty("key2")).isNull();
        Assertions.assertThat(loader01.getProperty("key3")).isNull();
        loader01.load();
        Assertions.assertThat(loader01.getProperty(null)).isNull();
        Assertions.assertThat(loader01.getProperty("")).isNull();
        Assertions.assertThat(loader01.getProperty(" ")).isNull();
        Assertions.assertThat(loader01.getProperty("key")).isNull();
        Assertions.assertThat(loader01.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader01.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader01.getProperty("key3")).isEqualTo("value1-3");

        Set<String> excludeProperties02 = new HashSet<>();
        PropertiesResourceLoader loader02 = new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties02);
        Assertions.assertThat(loader02.getProperty(null)).isNull();
        Assertions.assertThat(loader02.getProperty("")).isNull();
        Assertions.assertThat(loader02.getProperty(" ")).isNull();
        Assertions.assertThat(loader02.getProperty("key")).isNull();
        Assertions.assertThat(loader02.getProperty("key1")).isNull();
        Assertions.assertThat(loader02.getProperty("key2")).isNull();
        Assertions.assertThat(loader02.getProperty("key3")).isNull();
        loader02.load();
        Assertions.assertThat(loader02.getProperty(null)).isNull();
        Assertions.assertThat(loader02.getProperty("")).isNull();
        Assertions.assertThat(loader02.getProperty(" ")).isNull();
        Assertions.assertThat(loader02.getProperty("key")).isNull();
        Assertions.assertThat(loader02.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader02.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader02.getProperty("key3")).isEqualTo("value1-3");

        Set<String> excludeProperties03 = new HashSet<>();
        excludeProperties03.add("key1");
        PropertiesResourceLoader loader03 = new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties03);
        Assertions.assertThat(loader03.getProperty(null)).isNull();
        Assertions.assertThat(loader03.getProperty("")).isNull();
        Assertions.assertThat(loader03.getProperty(" ")).isNull();
        Assertions.assertThat(loader03.getProperty("key")).isNull();
        Assertions.assertThat(loader03.getProperty("key1")).isNull();
        Assertions.assertThat(loader03.getProperty("key2")).isNull();
        Assertions.assertThat(loader03.getProperty("key3")).isNull();
        loader03.load();
        Assertions.assertThat(loader03.getProperty(null)).isNull();
        Assertions.assertThat(loader03.getProperty("")).isNull();
        Assertions.assertThat(loader03.getProperty(" ")).isNull();
        Assertions.assertThat(loader03.getProperty("key")).isNull();
        Assertions.assertThat(loader03.getProperty("key1")).isNull();
        Assertions.assertThat(loader03.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader03.getProperty("key3")).isEqualTo("value1-3");

        Set<String> excludeProperties04 = new HashSet<>();
        excludeProperties04.add("key1");
        PropertiesResourceLoader loader04 = new PropertiesResourceLoader(getClass().getClassLoader(), "config1.properties", excludeProperties04);
        Assertions.assertThat(loader04.getProperty(null)).isNull();
        Assertions.assertThat(loader04.getProperty("")).isNull();
        Assertions.assertThat(loader04.getProperty(" ")).isNull();
        Assertions.assertThat(loader04.getProperty("key")).isNull();
        Assertions.assertThat(loader04.getProperty("key1")).isNull();
        Assertions.assertThat(loader04.getProperty("key2")).isNull();
        Assertions.assertThat(loader04.getProperty("key3")).isNull();
        loader04.load();
        Assertions.assertThat(loader04.getProperty(null)).isNull();
        Assertions.assertThat(loader04.getProperty("")).isNull();
        Assertions.assertThat(loader04.getProperty(" ")).isNull();
        Assertions.assertThat(loader04.getProperty("key")).isNull();
        Assertions.assertThat(loader04.getProperty("key1")).isNull();
        Assertions.assertThat(loader04.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader04.getProperty("key3")).isEqualTo("value1-3");
        excludeProperties04.add("key2");
        Assertions.assertThat(loader04.getProperty(null)).isNull();
        Assertions.assertThat(loader04.getProperty("")).isNull();
        Assertions.assertThat(loader04.getProperty(" ")).isNull();
        Assertions.assertThat(loader04.getProperty("key")).isNull();
        Assertions.assertThat(loader04.getProperty("key1")).isNull();
        Assertions.assertThat(loader04.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader04.getProperty("key3")).isEqualTo("value1-3");
        loader04.load();
        Assertions.assertThat(loader04.getProperty(null)).isNull();
        Assertions.assertThat(loader04.getProperty("")).isNull();
        Assertions.assertThat(loader04.getProperty(" ")).isNull();
        Assertions.assertThat(loader04.getProperty("key")).isNull();
        Assertions.assertThat(loader04.getProperty("key1")).isNull();
        Assertions.assertThat(loader04.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader04.getProperty("key3")).isEqualTo("value1-3");
    }

}
