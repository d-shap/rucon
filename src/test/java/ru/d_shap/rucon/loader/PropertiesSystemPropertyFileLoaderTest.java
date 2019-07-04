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

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.rucon.LoadException;

/**
 * Tests for {@link PropertiesSystemPropertyFileLoader}.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesSystemPropertyFileLoaderTest {

    /**
     * Test class constructor.
     */
    public PropertiesSystemPropertyFileLoaderTest() {
        super();
    }

    /**
     * {@link PropertiesSystemPropertyFileLoader} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void getNamesTest() throws Exception {
        URL url = getClass().getClassLoader().getResource("config1.properties");
        URI uri = url.toURI();
        File file = new File(uri);
        String filePath = file.getAbsolutePath();

        String name01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesSystemPropertyFileLoader loader01 = new PropertiesSystemPropertyFileLoader(name01, excludeProperties01);
        Assertions.assertThat(loader01.getNames()).containsExactly();
        loader01.load();
        Assertions.assertThat(loader01.getNames()).containsExactly();

        String name02 = getClass().getName() + "_name02_n";
        Set<String> excludeProperties02 = null;
        PropertiesSystemPropertyFileLoader loader02 = new PropertiesSystemPropertyFileLoader(name02, excludeProperties02);
        Assertions.assertThat(loader02.getNames()).containsExactly();
        loader02.load();
        Assertions.assertThat(loader02.getNames()).containsExactly();

        String name03 = getClass().getName() + "_name03_n";
        System.setProperty(name03, filePath.replace("config1.properties", "configWrong.properties"));
        Set<String> excludeProperties03 = null;
        PropertiesSystemPropertyFileLoader loader03 = new PropertiesSystemPropertyFileLoader(name03, excludeProperties03);
        Assertions.assertThat(loader03.getNames()).containsExactly();
        loader03.load();
        Assertions.assertThat(loader03.getNames()).containsExactly();

        String name04 = getClass().getName() + "_name04_n";
        System.setProperty(name04, filePath);
        Set<String> excludeProperties04 = null;
        PropertiesSystemPropertyFileLoader loader04 = new PropertiesSystemPropertyFileLoader(name04, excludeProperties04);
        Assertions.assertThat(loader04.getNames()).containsExactly();
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsExactly("key1", "key2", "key3");

        String name05 = getClass().getName() + "_name05_n";
        System.setProperty(name05, filePath);
        Set<String> excludeProperties05 = new HashSet<>();
        PropertiesSystemPropertyFileLoader loader05 = new PropertiesSystemPropertyFileLoader(name05, excludeProperties05);
        Assertions.assertThat(loader05.getNames()).containsExactly();
        loader05.load();
        Assertions.assertThat(loader05.getNames()).containsExactly("key1", "key2", "key3");

        String name06 = getClass().getName() + "_name06_n";
        System.setProperty(name06, filePath);
        Set<String> excludeProperties06 = new HashSet<>();
        excludeProperties06.add("key1");
        PropertiesSystemPropertyFileLoader loader06 = new PropertiesSystemPropertyFileLoader(name06, excludeProperties06);
        Assertions.assertThat(loader06.getNames()).containsExactly();
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsExactly("key2", "key3");

        String name07 = getClass().getName() + "_name07_n";
        System.setProperty(name07, filePath);
        Set<String> excludeProperties07 = null;
        PropertiesSystemPropertyFileLoader loader07 = new PropertiesSystemPropertyFileLoader(name07, excludeProperties07);
        Assertions.assertThat(loader07.getNames()).containsExactly();
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key1", "key2", "key3");
        loader07.getNames().add("key");
        Assertions.assertThat(loader07.getNames()).containsExactly("key1", "key2", "key3");
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key1", "key2", "key3");

        String name08 = getClass().getName() + "_name08_n";
        System.setProperty(name08, filePath);
        Set<String> excludeProperties08 = new HashSet<>();
        excludeProperties08.add("key1");
        PropertiesSystemPropertyFileLoader loader08 = new PropertiesSystemPropertyFileLoader(name08, excludeProperties08);
        Assertions.assertThat(loader08.getNames()).containsExactly();
        loader08.load();
        Assertions.assertThat(loader08.getNames()).containsExactly("key2", "key3");
        excludeProperties08.add("key2");
        Assertions.assertThat(loader08.getNames()).containsExactly("key2", "key3");
        loader08.load();
        Assertions.assertThat(loader08.getNames()).containsExactly("key2", "key3");
    }

    /**
     * {@link PropertiesSystemPropertyFileLoader} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void getPropertyTest() throws Exception {
        URL url = getClass().getClassLoader().getResource("config1.properties");
        URI uri = url.toURI();
        File file = new File(uri);
        String filePath = file.getAbsolutePath();

        String name01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesSystemPropertyFileLoader loader01 = new PropertiesSystemPropertyFileLoader(name01, excludeProperties01);
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

        String name02 = getClass().getName() + "_name02_v";
        Set<String> excludeProperties02 = null;
        PropertiesSystemPropertyFileLoader loader02 = new PropertiesSystemPropertyFileLoader(name02, excludeProperties02);
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

        String name03 = getClass().getName() + "_name03_v";
        System.setProperty(name03, filePath.replace("config1.properties", "configWrong.properties"));
        Set<String> excludeProperties03 = null;
        PropertiesSystemPropertyFileLoader loader03 = new PropertiesSystemPropertyFileLoader(name03, excludeProperties03);
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
        Assertions.assertThat(loader03.getProperty("key2")).isNull();
        Assertions.assertThat(loader03.getProperty("key3")).isNull();

        String name04 = getClass().getName() + "_name04_v";
        System.setProperty(name04, filePath);
        Set<String> excludeProperties04 = null;
        PropertiesSystemPropertyFileLoader loader04 = new PropertiesSystemPropertyFileLoader(name04, excludeProperties04);
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

        String name05 = getClass().getName() + "_name05_v";
        System.setProperty(name05, filePath);
        Set<String> excludeProperties05 = new HashSet<>();
        PropertiesSystemPropertyFileLoader loader05 = new PropertiesSystemPropertyFileLoader(name05, excludeProperties05);
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
        Assertions.assertThat(loader05.getProperty("key1")).isEqualTo("value1-1");
        Assertions.assertThat(loader05.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader05.getProperty("key3")).isEqualTo("value1-3");

        String name06 = getClass().getName() + "_name06_v";
        System.setProperty(name06, filePath);
        Set<String> excludeProperties06 = new HashSet<>();
        excludeProperties06.add("key1");
        PropertiesSystemPropertyFileLoader loader06 = new PropertiesSystemPropertyFileLoader(name06, excludeProperties06);
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

        String name07 = getClass().getName() + "_name07_v";
        System.setProperty(name07, filePath);
        Set<String> excludeProperties07 = new HashSet<>();
        excludeProperties07.add("key1");
        PropertiesSystemPropertyFileLoader loader07 = new PropertiesSystemPropertyFileLoader(name07, excludeProperties07);
        Assertions.assertThat(loader07.getProperty(null)).isNull();
        Assertions.assertThat(loader07.getProperty("")).isNull();
        Assertions.assertThat(loader07.getProperty(" ")).isNull();
        Assertions.assertThat(loader07.getProperty("key")).isNull();
        Assertions.assertThat(loader07.getProperty("key1")).isNull();
        Assertions.assertThat(loader07.getProperty("key2")).isNull();
        Assertions.assertThat(loader07.getProperty("key3")).isNull();
        loader07.load();
        Assertions.assertThat(loader07.getProperty(null)).isNull();
        Assertions.assertThat(loader07.getProperty("")).isNull();
        Assertions.assertThat(loader07.getProperty(" ")).isNull();
        Assertions.assertThat(loader07.getProperty("key")).isNull();
        Assertions.assertThat(loader07.getProperty("key1")).isNull();
        Assertions.assertThat(loader07.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader07.getProperty("key3")).isEqualTo("value1-3");
        excludeProperties07.add("key2");
        Assertions.assertThat(loader07.getProperty(null)).isNull();
        Assertions.assertThat(loader07.getProperty("")).isNull();
        Assertions.assertThat(loader07.getProperty(" ")).isNull();
        Assertions.assertThat(loader07.getProperty("key")).isNull();
        Assertions.assertThat(loader07.getProperty("key1")).isNull();
        Assertions.assertThat(loader07.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader07.getProperty("key3")).isEqualTo("value1-3");
        loader07.load();
        Assertions.assertThat(loader07.getProperty(null)).isNull();
        Assertions.assertThat(loader07.getProperty("")).isNull();
        Assertions.assertThat(loader07.getProperty(" ")).isNull();
        Assertions.assertThat(loader07.getProperty("key")).isNull();
        Assertions.assertThat(loader07.getProperty("key1")).isNull();
        Assertions.assertThat(loader07.getProperty("key2")).isEqualTo("value1-2");
        Assertions.assertThat(loader07.getProperty("key3")).isEqualTo("value1-3");
    }

    /**
     * {@link PropertiesSystemPropertyFileLoader} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void loadTextFileTest() throws Exception {
        URL url = getClass().getClassLoader().getResource("dir/file.txt");
        URI uri = url.toURI();
        File file = new File(uri);
        String filePath = file.getAbsolutePath();

        String name = getClass().getName() + "_name_t";
        System.setProperty(name, filePath);
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add("key");
        PropertiesSystemPropertyFileLoader loader = new PropertiesSystemPropertyFileLoader(name, excludeProperties);
        loader.load();
        Assertions.assertThat(loader.getNames()).containsExactly("some");
        Assertions.assertThat(loader.getProperty("some")).isEqualTo("other text");
    }

    /**
     * {@link PropertiesSystemPropertyFileLoader} class test.
     *
     * @throws Exception exception in test.
     */
    @Test(expected = LoadException.class)
    public void loadDirectoryFailTest() throws Exception {
        URL url = getClass().getClassLoader().getResource("dir");
        URI uri = url.toURI();
        File file = new File(uri);
        String filePath = file.getAbsolutePath();

        String name = getClass().getName() + "_name_d";
        System.setProperty(name, filePath);
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add("key");
        PropertiesSystemPropertyFileLoader loader = new PropertiesSystemPropertyFileLoader(name, excludeProperties);
        loader.load();
    }

}
