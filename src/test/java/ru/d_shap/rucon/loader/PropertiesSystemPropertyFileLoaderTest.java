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

        String name1 = null;
        Set<String> excludeProperties01 = null;
        PropertiesSystemPropertyFileLoader loader01 = new PropertiesSystemPropertyFileLoader(name1, excludeProperties01);
        Assertions.assertThat(loader01.getNames()).containsExactly();
        loader01.load();
        Assertions.assertThat(loader01.getNames()).containsExactly();

        String name2 = getClass().getName() + "_name2";
        Set<String> excludeProperties02 = null;
        PropertiesSystemPropertyFileLoader loader02 = new PropertiesSystemPropertyFileLoader(name2, excludeProperties02);
        Assertions.assertThat(loader02.getNames()).containsExactly();
        loader02.load();
        Assertions.assertThat(loader02.getNames()).containsExactly();

        String name3 = getClass().getName() + "_name3";
        System.setProperty(name3, filePath.replace("config1.properties", "configWrong.properties"));
        Set<String> excludeProperties03 = null;
        PropertiesSystemPropertyFileLoader loader03 = new PropertiesSystemPropertyFileLoader(name3, excludeProperties03);
        Assertions.assertThat(loader03.getNames()).containsExactly();
        loader03.load();
        Assertions.assertThat(loader03.getNames()).containsExactly();

        String name4 = getClass().getName() + "_name4";
        System.setProperty(name4, filePath);
        Set<String> excludeProperties04 = null;
        PropertiesSystemPropertyFileLoader loader04 = new PropertiesSystemPropertyFileLoader(name4, excludeProperties04);
        Assertions.assertThat(loader04.getNames()).containsExactly();
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsExactly("key1", "key2", "key3");

        String name5 = getClass().getName() + "_name5";
        System.setProperty(name5, filePath);
        Set<String> excludeProperties05 = new HashSet<>();
        PropertiesSystemPropertyFileLoader loader05 = new PropertiesSystemPropertyFileLoader(name5, excludeProperties05);
        Assertions.assertThat(loader05.getNames()).containsExactly();
        loader05.load();
        Assertions.assertThat(loader05.getNames()).containsExactly("key1", "key2", "key3");

        String name6 = getClass().getName() + "_name6";
        System.setProperty(name6, filePath);
        Set<String> excludeProperties06 = new HashSet<>();
        excludeProperties06.add("key1");
        PropertiesSystemPropertyFileLoader loader06 = new PropertiesSystemPropertyFileLoader(name6, excludeProperties06);
        Assertions.assertThat(loader06.getNames()).containsExactly();
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsExactly("key2", "key3");

        String name7 = getClass().getName() + "_name7";
        System.setProperty(name7, filePath);
        Set<String> excludeProperties07 = null;
        PropertiesSystemPropertyFileLoader loader07 = new PropertiesSystemPropertyFileLoader(name7, excludeProperties07);
        Assertions.assertThat(loader07.getNames()).containsExactly();
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key1", "key2", "key3");
        loader07.getNames().add("key");
        Assertions.assertThat(loader07.getNames()).containsExactly("key1", "key2", "key3");
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key1", "key2", "key3");

        String name8 = getClass().getName() + "_name8";
        System.setProperty(name8, filePath);
        Set<String> excludeProperties08 = new HashSet<>();
        excludeProperties08.add("key1");
        PropertiesSystemPropertyFileLoader loader08 = new PropertiesSystemPropertyFileLoader(name8, excludeProperties08);
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
     */
    @Test
    public void getPropertyTest() {
        // TODO
    }

}
