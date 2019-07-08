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
import ru.d_shap.rucon.LoadException;

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
        String resource01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesResourceLoader loader01 = new PropertiesResourceLoader(getClass().getClassLoader(), resource01, excludeProperties01);
        Assertions.assertThat(loader01.getNames()).containsExactly();
        loader01.load();
        Assertions.assertThat(loader01.getNames()).containsExactly();

        String resource02 = "configWrong.properties";
        Set<String> excludeProperties02 = null;
        PropertiesResourceLoader loader02 = new PropertiesResourceLoader(getClass().getClassLoader(), resource02, excludeProperties02);
        Assertions.assertThat(loader02.getNames()).containsExactly();
        loader02.load();
        Assertions.assertThat(loader02.getNames()).containsExactly();

        String resource03 = "config1.properties";
        Set<String> excludeProperties03 = null;
        PropertiesResourceLoader loader03 = new PropertiesResourceLoader(getClass().getClassLoader(), resource03, excludeProperties03);
        Assertions.assertThat(loader03.getNames()).containsExactly();
        loader03.load();
        Assertions.assertThat(loader03.getNames()).containsExactly("key1", "key2", "key3");

        String resource04 = "config1.properties";
        Set<String> excludeProperties04 = new HashSet<>();
        PropertiesResourceLoader loader04 = new PropertiesResourceLoader(getClass().getClassLoader(), resource04, excludeProperties04);
        Assertions.assertThat(loader04.getNames()).containsExactly();
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsExactly("key1", "key2", "key3");

        String resource05 = "config1.properties";
        Set<String> excludeProperties05 = new HashSet<>();
        excludeProperties05.add("key1");
        PropertiesResourceLoader loader05 = new PropertiesResourceLoader(getClass().getClassLoader(), resource05, excludeProperties05);
        Assertions.assertThat(loader05.getNames()).containsExactly();
        loader05.load();
        Assertions.assertThat(loader05.getNames()).containsExactly("key2", "key3");

        String resource06 = "config1.properties";
        Set<String> excludeProperties06 = null;
        PropertiesResourceLoader loader06 = new PropertiesResourceLoader(getClass().getClassLoader(), resource06, excludeProperties06);
        Assertions.assertThat(loader06.getNames()).containsExactly();
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsExactly("key1", "key2", "key3");
        loader06.getNames().add("key");
        Assertions.assertThat(loader06.getNames()).containsExactly("key1", "key2", "key3");
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsExactly("key1", "key2", "key3");

        String resource07 = "config1.properties";
        Set<String> excludeProperties07 = new HashSet<>();
        excludeProperties07.add("key1");
        PropertiesResourceLoader loader07 = new PropertiesResourceLoader(getClass().getClassLoader(), resource07, excludeProperties07);
        Assertions.assertThat(loader07.getNames()).containsExactly();
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key2", "key3");
        excludeProperties07.add("key2");
        Assertions.assertThat(loader07.getNames()).containsExactly("key2", "key3");
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key2", "key3");
    }

    /**
     * {@link PropertiesResourceLoader} class test.
     */
    @Test
    public void getPropertyTest() {
        String resource01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesResourceLoader loader01 = new PropertiesResourceLoader(getClass().getClassLoader(), resource01, excludeProperties01);
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
        Assertions.assertThat(loader01.getProperty("key1")).isNull();
        Assertions.assertThat(loader01.getProperty("key2")).isNull();
        Assertions.assertThat(loader01.getProperty("key3")).isNull();

        String resource02 = "configWrong.properties";
        Set<String> excludeProperties02 = null;
        PropertiesResourceLoader loader02 = new PropertiesResourceLoader(getClass().getClassLoader(), resource02, excludeProperties02);
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
        Assertions.assertThat(loader02.getProperty("key1")).isNull();
        Assertions.assertThat(loader02.getProperty("key2")).isNull();
        Assertions.assertThat(loader02.getProperty("key3")).isNull();

        String resource03 = "config1.properties";
        Set<String> excludeProperties03 = null;
        PropertiesResourceLoader loader03 = new PropertiesResourceLoader(getClass().getClassLoader(), resource03, excludeProperties03);
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
        Assertions.assertThat(loader03.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader03.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader03.getProperty("key3")).isEqualTo("value1-3");

        String resource04 = "config1.properties";
        Set<String> excludeProperties04 = new HashSet<>();
        PropertiesResourceLoader loader04 = new PropertiesResourceLoader(getClass().getClassLoader(), resource04, excludeProperties04);
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
        Assertions.assertThat(loader04.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader04.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader04.getProperty("key3")).isEqualTo("value1-3");

        String resource05 = "config1.properties";
        Set<String> excludeProperties05 = new HashSet<>();
        excludeProperties05.add("key1");
        PropertiesResourceLoader loader05 = new PropertiesResourceLoader(getClass().getClassLoader(), resource05, excludeProperties05);
        Assertions.assertThat(loader05.getProperty(null)).isNull();
        Assertions.assertThat(loader05.getProperty("")).isNull();
        Assertions.assertThat(loader05.getProperty(" ")).isNull();
        Assertions.assertThat(loader05.getProperty("key")).isNull();
        Assertions.assertThat(loader05.getProperty("key1")).isNull();
        Assertions.assertThat(loader05.getProperty("key2")).isNull();
        Assertions.assertThat(loader05.getProperty("key3")).isNull();
        loader05.load();
        Assertions.assertThat(loader05.getProperty(null)).isNull();
        Assertions.assertThat(loader05.getProperty("")).isNull();
        Assertions.assertThat(loader05.getProperty(" ")).isNull();
        Assertions.assertThat(loader05.getProperty("key")).isNull();
        Assertions.assertThat(loader05.getProperty("key1")).isNull();
        Assertions.assertThat(loader05.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader05.getProperty("key3")).isEqualTo("value1-3");

        String resource06 = "config1.properties";
        Set<String> excludeProperties06 = new HashSet<>();
        excludeProperties06.add("key1");
        PropertiesResourceLoader loader06 = new PropertiesResourceLoader(getClass().getClassLoader(), resource06, excludeProperties06);
        Assertions.assertThat(loader06.getProperty(null)).isNull();
        Assertions.assertThat(loader06.getProperty("")).isNull();
        Assertions.assertThat(loader06.getProperty(" ")).isNull();
        Assertions.assertThat(loader06.getProperty("key")).isNull();
        Assertions.assertThat(loader06.getProperty("key1")).isNull();
        Assertions.assertThat(loader06.getProperty("key2")).isNull();
        Assertions.assertThat(loader06.getProperty("key3")).isNull();
        loader06.load();
        Assertions.assertThat(loader06.getProperty(null)).isNull();
        Assertions.assertThat(loader06.getProperty("")).isNull();
        Assertions.assertThat(loader06.getProperty(" ")).isNull();
        Assertions.assertThat(loader06.getProperty("key")).isNull();
        Assertions.assertThat(loader06.getProperty("key1")).isNull();
        Assertions.assertThat(loader06.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader06.getProperty("key3")).isEqualTo("value1-3");
        excludeProperties06.add("key2");
        Assertions.assertThat(loader06.getProperty(null)).isNull();
        Assertions.assertThat(loader06.getProperty("")).isNull();
        Assertions.assertThat(loader06.getProperty(" ")).isNull();
        Assertions.assertThat(loader06.getProperty("key")).isNull();
        Assertions.assertThat(loader06.getProperty("key1")).isNull();
        Assertions.assertThat(loader06.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader06.getProperty("key3")).isEqualTo("value1-3");
        loader06.load();
        Assertions.assertThat(loader06.getProperty(null)).isNull();
        Assertions.assertThat(loader06.getProperty("")).isNull();
        Assertions.assertThat(loader06.getProperty(" ")).isNull();
        Assertions.assertThat(loader06.getProperty("key")).isNull();
        Assertions.assertThat(loader06.getProperty("key1")).isNull();
        Assertions.assertThat(loader06.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader06.getProperty("key3")).isEqualTo("value1-3");
    }

    /**
     * {@link PropertiesResourceLoader} class test.
     */
    @Test(expected = NullPointerException.class)
    public void loadNullClassLoaderFailTest() {
        String resource = "config1.properties";
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add("key");
        PropertiesResourceLoader loader = new PropertiesResourceLoader(null, resource, excludeProperties);
        loader.load();
    }

    /**
     * {@link PropertiesResourceLoader} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void loadTextFileTest() throws Exception {
        String resource = "dir/file.txt";
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add("key");
        PropertiesResourceLoader loader = new PropertiesResourceLoader(getClass().getClassLoader(), resource, excludeProperties);
        loader.load();
        Assertions.assertThat(loader.getNames()).containsExactly("some");
        Assertions.assertThat(loader.getProperty("some")).isEqualTo("other text");
    }

    /**
     * {@link PropertiesResourceLoader} class test.
     *
     * @throws Exception exception in test.
     */
    @Test(expected = LoadException.class)
    public void loadDirectoryFailTest() throws Exception {
        String resource = "dir";
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add("key");
        PropertiesResourceLoader loader = new PropertiesResourceLoader(getClass().getClassLoader(), resource, excludeProperties);
        loader.load();
    }

}
