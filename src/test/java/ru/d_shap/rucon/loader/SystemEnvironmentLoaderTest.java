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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link SystemEnvironmentLoader}.
 *
 * @author Dmitry Shapovalov
 */
public final class SystemEnvironmentLoaderTest {

    /**
     * Test class constructor.
     */
    public SystemEnvironmentLoaderTest() {
        super();
    }

    /**
     * {@link SystemEnvironmentLoader} class test.
     */
    @Test
    public void getNamesTest() {
        Map<String, String> aliases01 = null;
        Set<String> excludeProperties01 = null;
        SystemEnvironmentLoader loader01 = new SystemEnvironmentLoader(aliases01, excludeProperties01);
        Assertions.assertThat(loader01.getNames()).containsExactly();
        loader01.load();
        Assertions.assertThat(loader01.getNames()).isNotEmpty();

        Map<String, String> aliases02 = new HashMap<>();
        Set<String> excludeProperties02 = null;
        SystemEnvironmentLoader loader02 = new SystemEnvironmentLoader(aliases02, excludeProperties02);
        Assertions.assertThat(loader02.getNames()).containsExactly();
        loader02.load();
        Assertions.assertThat(loader02.getNames()).isNotEmpty();

        Map<String, String> aliases03 = new HashMap<>();
        aliases03.put("path1", "PATH");
        aliases03.put("path2", "ROOTPATH");
        aliases03.put("path3", "Path");
        Set<String> excludeProperties03 = null;
        SystemEnvironmentLoader loader03 = new SystemEnvironmentLoader(aliases03, excludeProperties03);
        Assertions.assertThat(loader03.getNames()).containsExactly();
        loader03.load();
        Assertions.assertThat(loader03.getNames()).containsAny("path1", "path2", "path3");

        Map<String, String> aliases04 = new HashMap<>();
        aliases04.put("path1", "PATH");
        aliases04.put("path2", "ROOTPATH");
        aliases04.put("path3", "Path");
        Set<String> excludeProperties04 = new HashSet<>();
        SystemEnvironmentLoader loader04 = new SystemEnvironmentLoader(aliases04, excludeProperties04);
        Assertions.assertThat(loader04.getNames()).containsExactly();
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsAny("path1", "path2", "path3");

        Map<String, String> aliases05 = new HashMap<>();
        aliases05.put("path1", "PATH");
        aliases05.put("path2", "ROOTPATH");
        aliases05.put("path3", "Path");
        Set<String> excludeProperties05 = new HashSet<>();
        excludeProperties05.add("path1");
        excludeProperties05.add("path2");
        excludeProperties05.add("path3");
        SystemEnvironmentLoader loader05 = new SystemEnvironmentLoader(aliases05, excludeProperties05);
        Assertions.assertThat(loader05.getNames()).containsExactly();
        loader05.load();
        Assertions.assertThat(loader05.getNames()).isNotEmpty();
        Assertions.assertThat(loader05.getNames()).containsNone("path1", "path2", "path3");

        Map<String, String> aliases06 = new HashMap<>();
        aliases06.put("path1", "PATH");
        aliases06.put("path2", "ROOTPATH");
        aliases06.put("path3", "Path");
        Set<String> excludeProperties06 = null;
        SystemEnvironmentLoader loader06 = new SystemEnvironmentLoader(aliases06, excludeProperties06);
        Assertions.assertThat(loader06.getNames()).containsExactly();
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsAny("path1", "path2", "path3");
        loader06.getNames().add("path");
        Assertions.assertThat(loader06.getNames()).containsAny("path1", "path2", "path3");
        Assertions.assertThat(loader06.getNames()).doesNotContain("path");
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsAny("path1", "path2", "path3");
        Assertions.assertThat(loader06.getNames()).doesNotContain("path");

        Map<String, String> aliases07 = new HashMap<>();
        aliases07.put("path1", "PATH");
        aliases07.put("path2", "ROOTPATH");
        aliases07.put("path3", "Path");
        Set<String> excludeProperties07 = new HashSet<>();
        excludeProperties07.add("path1");
        excludeProperties07.add("path2");
        excludeProperties07.add("path3");
        SystemEnvironmentLoader loader07 = new SystemEnvironmentLoader(aliases07, excludeProperties07);
        Assertions.assertThat(loader07.getNames()).containsExactly();
        loader07.load();
        Assertions.assertThat(loader07.getNames()).isNotEmpty();
        Assertions.assertThat(loader07.getNames()).containsNone("path1", "path2", "path3");
        aliases07.clear();
        Assertions.assertThat(loader07.getNames()).isNotEmpty();
        Assertions.assertThat(loader07.getNames()).containsNone("path1", "path2", "path3");
        loader07.load();
        Assertions.assertThat(loader07.getNames()).isNotEmpty();
        Assertions.assertThat(loader07.getNames()).containsNone("path1", "path2", "path3");
        excludeProperties07.clear();
        Assertions.assertThat(loader07.getNames()).isNotEmpty();
        Assertions.assertThat(loader07.getNames()).containsNone("path1", "path2", "path3");
        loader07.load();
        Assertions.assertThat(loader07.getNames()).isNotEmpty();
        Assertions.assertThat(loader07.getNames()).containsNone("path1", "path2", "path3");
    }

    /**
     * {@link SystemEnvironmentLoader} class test.
     */
    @Test
    public void getPropertyTest() {
        Map<String, String> aliases01 = new HashMap<>();
        aliases01.put("path1", "PATH");
        aliases01.put("path2", "ROOTPATH");
        aliases01.put("path3", "Path");
        Set<String> excludeProperties01 = null;
        SystemEnvironmentLoader loader01 = new SystemEnvironmentLoader(aliases01, excludeProperties01);
        Set<String> values011 = new HashSet<>();
        values011.add(loader01.getProperty("path1"));
        values011.add(loader01.getProperty("path2"));
        values011.add(loader01.getProperty("path3"));
        Assertions.assertThat(values011).hasSize(1);
        loader01.load();
        Set<String> values012 = new HashSet<>();
        values012.add(loader01.getProperty("path1"));
        values012.add(loader01.getProperty("path2"));
        values012.add(loader01.getProperty("path3"));
        Assertions.assertThat(values012).hasSize(2);

        Map<String, String> aliases02 = new HashMap<>();
        aliases02.put("path1", "PATH");
        aliases02.put("path2", "ROOTPATH");
        aliases02.put("path3", "Path");
        Set<String> excludeProperties02 = new HashSet<>();
        SystemEnvironmentLoader loader02 = new SystemEnvironmentLoader(aliases02, excludeProperties02);
        Set<String> values021 = new HashSet<>();
        values021.add(loader02.getProperty("path1"));
        values021.add(loader02.getProperty("path2"));
        values021.add(loader02.getProperty("path3"));
        Assertions.assertThat(values021).hasSize(1);
        loader02.load();
        Set<String> values022 = new HashSet<>();
        values022.add(loader02.getProperty("path1"));
        values022.add(loader02.getProperty("path2"));
        values022.add(loader02.getProperty("path3"));
        Assertions.assertThat(values022).hasSize(2);

        Map<String, String> aliases03 = new HashMap<>();
        aliases03.put("path1", "PATH");
        aliases03.put("path2", "ROOTPATH");
        aliases03.put("path3", "Path");
        Set<String> excludeProperties03 = new HashSet<>();
        excludeProperties03.add("path1");
        excludeProperties03.add("path2");
        excludeProperties03.add("path3");
        SystemEnvironmentLoader loader03 = new SystemEnvironmentLoader(aliases03, excludeProperties03);
        Set<String> values031 = new HashSet<>();
        values031.add(loader03.getProperty("path1"));
        values031.add(loader03.getProperty("path2"));
        values031.add(loader03.getProperty("path3"));
        Assertions.assertThat(values031).hasSize(1);
        loader03.load();
        Set<String> values032 = new HashSet<>();
        values032.add(loader03.getProperty("path1"));
        values032.add(loader03.getProperty("path2"));
        values032.add(loader03.getProperty("path3"));
        Assertions.assertThat(values032).hasSize(1);
    }

}
