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

/**
 * Tests for {@link PropertiesFilePathLoader}.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesFilePathLoaderTest {

    /**
     * Test class constructor.
     */
    public PropertiesFilePathLoaderTest() {
        super();
    }

    /**
     * {@link PropertiesFilePathLoader} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void getNamesTest() throws Exception {
        URL url = getClass().getClassLoader().getResource("config1.properties");
        URI uri = url.toURI();
        File file = new File(uri);
        String filePath = file.getAbsolutePath();

        String filePath01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesFilePathLoader loader01 = new PropertiesFilePathLoader(filePath01, excludeProperties01);
        Assertions.assertThat(loader01.getNames()).containsExactly();
        loader01.load();
        Assertions.assertThat(loader01.getNames()).containsExactly();

        String filePath02 = filePath.replace("config1.properties", "configWrong.properties");
        Set<String> excludeProperties02 = null;
        PropertiesFilePathLoader loader02 = new PropertiesFilePathLoader(filePath02, excludeProperties02);
        Assertions.assertThat(loader02.getNames()).containsExactly();
        loader02.load();
        Assertions.assertThat(loader02.getNames()).containsExactly();

        String filePath03 = filePath;
        Set<String> excludeProperties03 = null;
        PropertiesFilePathLoader loader03 = new PropertiesFilePathLoader(filePath03, excludeProperties03);
        Assertions.assertThat(loader03.getNames()).containsExactly();
        loader03.load();
        Assertions.assertThat(loader03.getNames()).containsExactly("key1", "key2", "key3");

        String filePath04 = filePath;
        Set<String> excludeProperties04 = new HashSet<>();
        PropertiesFilePathLoader loader04 = new PropertiesFilePathLoader(filePath04, excludeProperties04);
        Assertions.assertThat(loader04.getNames()).containsExactly();
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsExactly("key1", "key2", "key3");

        String filePath05 = filePath;
        Set<String> excludeProperties05 = new HashSet<>();
        excludeProperties05.add("key1");
        PropertiesFilePathLoader loader05 = new PropertiesFilePathLoader(filePath05, excludeProperties05);
        Assertions.assertThat(loader05.getNames()).containsExactly();
        loader05.load();
        Assertions.assertThat(loader05.getNames()).containsExactly("key2", "key3");

        String filePath06 = filePath;
        Set<String> excludeProperties06 = null;
        PropertiesFilePathLoader loader06 = new PropertiesFilePathLoader(filePath06, excludeProperties06);
        Assertions.assertThat(loader06.getNames()).containsExactly();
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsExactly("key1", "key2", "key3");
        loader06.getNames().add("key");
        Assertions.assertThat(loader06.getNames()).containsExactly("key1", "key2", "key3");
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsExactly("key1", "key2", "key3");

        String filePath07 = filePath;
        Set<String> excludeProperties07 = new HashSet<>();
        excludeProperties07.add("key1");
        PropertiesFilePathLoader loader07 = new PropertiesFilePathLoader(filePath07, excludeProperties07);
        Assertions.assertThat(loader07.getNames()).containsExactly();
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key2", "key3");
        excludeProperties07.add("key2");
        Assertions.assertThat(loader07.getNames()).containsExactly("key2", "key3");
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key2", "key3");
    }

    /**
     * {@link PropertiesFilePathLoader} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void getPropertyTest() throws Exception {
        URL url = getClass().getClassLoader().getResource("config1.properties");
        URI uri = url.toURI();
        File file = new File(uri);
        String filePath = file.getAbsolutePath();

        String filePath01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesFilePathLoader loader01 = new PropertiesFilePathLoader(filePath01, excludeProperties01);
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

        String filePath02 = filePath.replace("config1.properties", "configWrong.properties");
        Set<String> excludeProperties02 = null;
        PropertiesFilePathLoader loader02 = new PropertiesFilePathLoader(filePath02, excludeProperties02);
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

        String filePath03 = filePath;
        Set<String> excludeProperties03 = null;
        PropertiesFilePathLoader loader03 = new PropertiesFilePathLoader(filePath03, excludeProperties03);
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

        String filePath04 = filePath;
        Set<String> excludeProperties04 = new HashSet<>();
        PropertiesFilePathLoader loader04 = new PropertiesFilePathLoader(filePath04, excludeProperties04);
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

        String filePath05 = filePath;
        Set<String> excludeProperties05 = new HashSet<>();
        excludeProperties05.add("key1");
        PropertiesFilePathLoader loader05 = new PropertiesFilePathLoader(filePath05, excludeProperties05);
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

        String filePath06 = filePath;
        Set<String> excludeProperties06 = new HashSet<>();
        excludeProperties06.add("key1");
        PropertiesFilePathLoader loader06 = new PropertiesFilePathLoader(filePath06, excludeProperties06);
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
     * {@link PropertiesFilePathLoader} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void loadTextFileTest() throws Exception {
        URL url = getClass().getClassLoader().getResource("dir/file.txt");
        URI uri = url.toURI();
        File file = new File(uri);
        String filePath = file.getAbsolutePath();

        PropertiesFilePathLoader loader = new PropertiesFilePathLoader(filePath, null);
        loader.load();
        Assertions.assertThat(loader.getNames()).containsExactly("some");
        Assertions.assertThat(loader.getProperty("some")).isEqualTo("other text");
    }

}
