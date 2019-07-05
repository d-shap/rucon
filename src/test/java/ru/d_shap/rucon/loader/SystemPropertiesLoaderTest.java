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
 * Tests for {@link SystemPropertiesLoader}.
 *
 * @author Dmitry Shapovalov
 */
public final class SystemPropertiesLoaderTest {

    /**
     * Test class constructor.
     */
    public SystemPropertiesLoaderTest() {
        super();
    }

    /**
     * {@link SystemPropertiesLoader} class test.
     */
    @Test
    public void getNamesTest() {
        String name01 = null;
        String prefix01 = null;
        String suffix01 = null;
        Set<String> excludeProperties01 = null;
        SystemPropertiesLoader loader01 = new SystemPropertiesLoader(prefix01, suffix01, excludeProperties01);
        Assertions.assertThat(loader01.getNames()).containsExactly();
        loader01.load();
        Assertions.assertThat(loader01.getNames()).containsAll("file.separator", "line.separator", "path.separator");

        String name02 = getClass().getName() + "_name02_n";
        String prefix02 = null;
        String suffix02 = null;
        Set<String> excludeProperties02 = null;
        System.setProperty(name02, "value");
        SystemPropertiesLoader loader02 = new SystemPropertiesLoader(prefix02, suffix02, excludeProperties02);
        Assertions.assertThat(loader02.getNames()).containsExactly();
        loader02.load();
        Assertions.assertThat(loader02.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader02.getNames()).contains(name02);

        String name03 = getClass().getName() + "_name03_n";
        String prefix03 = "prefix.";
        String suffix03 = null;
        Set<String> excludeProperties03 = null;
        System.setProperty(prefix03 + name03, "value");
        SystemPropertiesLoader loader03 = new SystemPropertiesLoader(prefix03, suffix03, excludeProperties03);
        Assertions.assertThat(loader03.getNames()).containsExactly();
        loader03.load();
        Assertions.assertThat(loader03.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader03.getNames()).contains(name03);

        String name04 = getClass().getName() + "_name04_n";
        String prefix04 = null;
        String suffix04 = ".suffix";
        Set<String> excludeProperties04 = null;
        System.setProperty(name04 + suffix04, "value");
        SystemPropertiesLoader loader04 = new SystemPropertiesLoader(prefix04, suffix04, excludeProperties04);
        Assertions.assertThat(loader04.getNames()).containsExactly();
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader04.getNames()).contains(name04);

        String name05 = getClass().getName() + "_name05_n";
        String prefix05 = "prefix.";
        String suffix05 = ".suffix";
        Set<String> excludeProperties05 = null;
        System.setProperty(prefix05 + name05 + suffix05, "value");
        SystemPropertiesLoader loader05 = new SystemPropertiesLoader(prefix05, suffix05, excludeProperties05);
        Assertions.assertThat(loader05.getNames()).containsExactly();
        loader05.load();
        Assertions.assertThat(loader05.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader05.getNames()).contains(name05);

        String name06 = getClass().getName() + "_name06_n";
        String prefix06 = null;
        String suffix06 = null;
        Set<String> excludeProperties06 = new HashSet<>();
        System.setProperty(name06, "value");
        SystemPropertiesLoader loader06 = new SystemPropertiesLoader(prefix06, suffix06, excludeProperties06);
        Assertions.assertThat(loader06.getNames()).containsExactly();
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader06.getNames()).contains(name06);

        String name07 = getClass().getName() + "_name07_n";
        String prefix07 = null;
        String suffix07 = null;
        Set<String> excludeProperties07 = new HashSet<>();
        excludeProperties07.add(name07);
        System.setProperty(name07, "value");
        SystemPropertiesLoader loader07 = new SystemPropertiesLoader(prefix07, suffix07, excludeProperties07);
        Assertions.assertThat(loader07.getNames()).containsExactly();
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader07.getNames()).doesNotContain(name07);

        String name08 = getClass().getName() + "_name08_n";
        String prefix08 = "prefix.";
        String suffix08 = null;
        Set<String> excludeProperties08 = new HashSet<>();
        excludeProperties08.add(prefix08 + name08);
        System.setProperty(prefix08 + name08, "value");
        SystemPropertiesLoader loader08 = new SystemPropertiesLoader(prefix08, suffix08, excludeProperties08);
        Assertions.assertThat(loader08.getNames()).containsExactly();
        loader08.load();
        Assertions.assertThat(loader08.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader08.getNames()).contains(name08);

        String name09 = getClass().getName() + "_name09_n";
        String prefix09 = "prefix.";
        String suffix09 = null;
        Set<String> excludeProperties09 = new HashSet<>();
        excludeProperties09.add(name09);
        System.setProperty(prefix09 + name09, "value");
        SystemPropertiesLoader loader09 = new SystemPropertiesLoader(prefix09, suffix09, excludeProperties09);
        Assertions.assertThat(loader09.getNames()).containsExactly();
        loader09.load();
        Assertions.assertThat(loader09.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader09.getNames()).doesNotContain(name09);

        String name10 = getClass().getName() + "_name10_n";
        String prefix10 = null;
        String suffix10 = ".suffix";
        Set<String> excludeProperties10 = new HashSet<>();
        excludeProperties10.add(name10 + suffix10);
        System.setProperty(name10 + suffix10, "value");
        SystemPropertiesLoader loader10 = new SystemPropertiesLoader(prefix10, suffix10, excludeProperties10);
        Assertions.assertThat(loader10.getNames()).containsExactly();
        loader10.load();
        Assertions.assertThat(loader10.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader10.getNames()).contains(name10);

        String name11 = getClass().getName() + "_name11_n";
        String prefix11 = null;
        String suffix11 = ".suffix";
        Set<String> excludeProperties11 = new HashSet<>();
        excludeProperties11.add(name11);
        System.setProperty(name11 + suffix11, "value");
        SystemPropertiesLoader loader11 = new SystemPropertiesLoader(prefix11, suffix11, excludeProperties11);
        Assertions.assertThat(loader11.getNames()).containsExactly();
        loader11.load();
        Assertions.assertThat(loader11.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader11.getNames()).doesNotContain(name11);

        String name12 = getClass().getName() + "_name12_n";
        String prefix12 = "prefix.";
        String suffix12 = ".suffix";
        Set<String> excludeProperties12 = new HashSet<>();
        excludeProperties12.add(prefix12 + name12 + suffix12);
        System.setProperty(prefix12 + name12 + suffix12, "value");
        SystemPropertiesLoader loader12 = new SystemPropertiesLoader(prefix12, suffix12, excludeProperties12);
        Assertions.assertThat(loader12.getNames()).containsExactly();
        loader12.load();
        Assertions.assertThat(loader12.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader12.getNames()).contains(name12);

        String name13 = getClass().getName() + "_name13_n";
        String prefix13 = "prefix.";
        String suffix13 = ".suffix";
        Set<String> excludeProperties13 = new HashSet<>();
        excludeProperties13.add(name13);
        System.setProperty(prefix13 + name13 + suffix13, "value");
        SystemPropertiesLoader loader13 = new SystemPropertiesLoader(prefix13, suffix13, excludeProperties13);
        Assertions.assertThat(loader13.getNames()).containsExactly();
        loader13.load();
        Assertions.assertThat(loader13.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader13.getNames()).doesNotContain(name13);

        String name14 = getClass().getName() + "_name14_n";
        String prefix14 = null;
        String suffix14 = null;
        Set<String> excludeProperties14 = null;
        System.setProperty(name14, "value");
        SystemPropertiesLoader loader14 = new SystemPropertiesLoader(prefix14, suffix14, excludeProperties14);
        Assertions.assertThat(loader14.getNames()).containsExactly();
        loader14.load();
        Assertions.assertThat(loader14.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader14.getNames()).contains(name14);
        Assertions.assertThat(loader14.getNames()).doesNotContain("key");
        loader14.getNames().add("key");
        Assertions.assertThat(loader14.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader14.getNames()).contains(name14);
        Assertions.assertThat(loader14.getNames()).doesNotContain("key");
        loader14.load();
        Assertions.assertThat(loader14.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader14.getNames()).contains(name14);
        Assertions.assertThat(loader14.getNames()).doesNotContain("key");

        String name151 = getClass().getName() + "_name151_n";
        String name152 = getClass().getName() + "_name152_n";
        String prefix15 = null;
        String suffix15 = null;
        Set<String> excludeProperties15 = new HashSet<>();
        excludeProperties15.add(name151);
        System.setProperty(name151, "value");
        SystemPropertiesLoader loader15 = new SystemPropertiesLoader(prefix15, suffix15, excludeProperties15);
        Assertions.assertThat(loader15.getNames()).containsExactly();
        loader15.load();
        Assertions.assertThat(loader15.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader15.getNames()).doesNotContain(name151);
        Assertions.assertThat(loader15.getNames()).doesNotContain(name152);
        excludeProperties15.add("file.separator");
        Assertions.assertThat(loader15.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader15.getNames()).doesNotContain(name151);
        Assertions.assertThat(loader15.getNames()).doesNotContain(name152);
        loader15.load();
        Assertions.assertThat(loader15.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader15.getNames()).doesNotContain(name151);
        Assertions.assertThat(loader15.getNames()).doesNotContain(name152);
        System.setProperty(name152, "value");
        Assertions.assertThat(loader15.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader15.getNames()).doesNotContain(name151);
        Assertions.assertThat(loader15.getNames()).doesNotContain(name152);
        loader15.load();
        Assertions.assertThat(loader15.getNames()).containsAll("file.separator", "line.separator", "path.separator");
        Assertions.assertThat(loader15.getNames()).doesNotContain(name151);
        Assertions.assertThat(loader15.getNames()).contains(name152);
    }

    /**
     * {@link SystemPropertiesLoader} class test.
     */
    @Test
    public void getPropertyTest() {
        String name01 = null;
        String prefix01 = null;
        String suffix01 = null;
        Set<String> excludeProperties01 = null;
        SystemPropertiesLoader loader01 = new SystemPropertiesLoader(prefix01, suffix01, excludeProperties01);
        Assertions.assertThat(loader01.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader01.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader01.getProperty("path.separator")).isNull();
        loader01.load();
        Assertions.assertThat(loader01.getProperty("file.separator")).isNotNull();
        Assertions.assertThat(loader01.getProperty("line.separator")).isNotNull();
        Assertions.assertThat(loader01.getProperty("path.separator")).isNotNull();

        String name02 = getClass().getName() + "_name02_v";
        String prefix02 = null;
        String suffix02 = null;
        Set<String> excludeProperties02 = null;
        System.setProperty(name02, "value");
        SystemPropertiesLoader loader02 = new SystemPropertiesLoader(prefix02, suffix02, excludeProperties02);
        Assertions.assertThat(loader02.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader02.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader02.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader02.getProperty(name02)).isNull();
        loader02.load();
        Assertions.assertThat(loader02.getProperty("file.separator")).isNotNull();
        Assertions.assertThat(loader02.getProperty("line.separator")).isNotNull();
        Assertions.assertThat(loader02.getProperty("path.separator")).isNotNull();
        Assertions.assertThat(loader02.getProperty(name02)).isEqualTo("value");

        String name03 = getClass().getName() + "_name03_v";
        String prefix03 = "prefix.";
        String suffix03 = null;
        Set<String> excludeProperties03 = null;
        System.setProperty(prefix03 + name03, "value");
        SystemPropertiesLoader loader03 = new SystemPropertiesLoader(prefix03, suffix03, excludeProperties03);
        Assertions.assertThat(loader03.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader03.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader03.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader03.getProperty(name03)).isNull();
        loader03.load();
        Assertions.assertThat(loader03.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader03.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader03.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader03.getProperty(name03)).isEqualTo("value");

        String name04 = getClass().getName() + "_name04_v";
        String prefix04 = null;
        String suffix04 = ".suffix";
        Set<String> excludeProperties04 = null;
        System.setProperty(name04 + suffix04, "value");
        SystemPropertiesLoader loader04 = new SystemPropertiesLoader(prefix04, suffix04, excludeProperties04);
        Assertions.assertThat(loader04.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader04.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader04.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader04.getProperty(name04)).isNull();
        loader04.load();
        Assertions.assertThat(loader04.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader04.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader04.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader04.getProperty(name04)).isEqualTo("value");

        String name05 = getClass().getName() + "_name05_v";
        String prefix05 = "prefix.";
        String suffix05 = ".suffix";
        Set<String> excludeProperties05 = null;
        System.setProperty(prefix05 + name05 + suffix05, "value");
        SystemPropertiesLoader loader05 = new SystemPropertiesLoader(prefix05, suffix05, excludeProperties05);
        Assertions.assertThat(loader05.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader05.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader05.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader05.getProperty(name05)).isNull();
        loader05.load();
        Assertions.assertThat(loader05.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader05.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader05.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader05.getProperty(name05)).isEqualTo("value");

        String name06 = getClass().getName() + "_name06_v";
        String prefix06 = null;
        String suffix06 = null;
        Set<String> excludeProperties06 = new HashSet<>();
        System.setProperty(name06, "value");
        SystemPropertiesLoader loader06 = new SystemPropertiesLoader(prefix06, suffix06, excludeProperties06);
        Assertions.assertThat(loader06.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader06.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader06.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader06.getProperty(name06)).isNull();
        loader06.load();
        Assertions.assertThat(loader06.getProperty("file.separator")).isNotNull();
        Assertions.assertThat(loader06.getProperty("line.separator")).isNotNull();
        Assertions.assertThat(loader06.getProperty("path.separator")).isNotNull();
        Assertions.assertThat(loader06.getProperty(name06)).isEqualTo("value");

        String name07 = getClass().getName() + "_name07_v";
        String prefix07 = null;
        String suffix07 = null;
        Set<String> excludeProperties07 = new HashSet<>();
        excludeProperties07.add(name07);
        System.setProperty(name07, "value");
        SystemPropertiesLoader loader07 = new SystemPropertiesLoader(prefix07, suffix07, excludeProperties07);
        Assertions.assertThat(loader07.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader07.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader07.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader07.getProperty(name07)).isNull();
        loader07.load();
        Assertions.assertThat(loader07.getProperty("file.separator")).isNotNull();
        Assertions.assertThat(loader07.getProperty("line.separator")).isNotNull();
        Assertions.assertThat(loader07.getProperty("path.separator")).isNotNull();
        Assertions.assertThat(loader07.getProperty(name07)).isNull();

        String name08 = getClass().getName() + "_name08_v";
        String prefix08 = "prefix.";
        String suffix08 = null;
        Set<String> excludeProperties08 = new HashSet<>();
        excludeProperties08.add(prefix08 + name08);
        System.setProperty(prefix08 + name08, "value");
        SystemPropertiesLoader loader08 = new SystemPropertiesLoader(prefix08, suffix08, excludeProperties08);
        Assertions.assertThat(loader08.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader08.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader08.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader08.getProperty(name08)).isNull();
        loader08.load();
        Assertions.assertThat(loader08.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader08.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader08.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader08.getProperty(name08)).isEqualTo("value");

        String name09 = getClass().getName() + "_name09_v";
        String prefix09 = "prefix.";
        String suffix09 = null;
        Set<String> excludeProperties09 = new HashSet<>();
        excludeProperties09.add(name09);
        System.setProperty(prefix09 + name09, "value");
        SystemPropertiesLoader loader09 = new SystemPropertiesLoader(prefix09, suffix09, excludeProperties09);
        Assertions.assertThat(loader09.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader09.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader09.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader09.getProperty(name09)).isNull();
        loader09.load();
        Assertions.assertThat(loader09.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader09.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader09.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader09.getProperty(name09)).isNull();

        String name10 = getClass().getName() + "_name10_v";
        String prefix10 = null;
        String suffix10 = ".suffix";
        Set<String> excludeProperties10 = new HashSet<>();
        excludeProperties10.add(name10 + suffix10);
        System.setProperty(name10 + suffix10, "value");
        SystemPropertiesLoader loader10 = new SystemPropertiesLoader(prefix10, suffix10, excludeProperties10);
        Assertions.assertThat(loader10.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader10.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader10.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader10.getProperty(name10)).isNull();
        loader10.load();
        Assertions.assertThat(loader10.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader10.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader10.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader10.getProperty(name10)).isEqualTo("value");

        String name11 = getClass().getName() + "_name11_v";
        String prefix11 = null;
        String suffix11 = ".suffix";
        Set<String> excludeProperties11 = new HashSet<>();
        excludeProperties11.add(name11);
        System.setProperty(name11 + suffix11, "value");
        SystemPropertiesLoader loader11 = new SystemPropertiesLoader(prefix11, suffix11, excludeProperties11);
        Assertions.assertThat(loader11.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader11.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader11.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader11.getProperty(name11)).isNull();
        loader11.load();
        Assertions.assertThat(loader11.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader11.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader11.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader11.getProperty(name11)).isNull();

        String name12 = getClass().getName() + "_name12_v";
        String prefix12 = "prefix.";
        String suffix12 = ".suffix";
        Set<String> excludeProperties12 = new HashSet<>();
        excludeProperties12.add(prefix12 + name12 + suffix12);
        System.setProperty(prefix12 + name12 + suffix12, "value");
        SystemPropertiesLoader loader12 = new SystemPropertiesLoader(prefix12, suffix12, excludeProperties12);
        Assertions.assertThat(loader12.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader12.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader12.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader12.getProperty(name12)).isNull();
        loader12.load();
        Assertions.assertThat(loader12.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader12.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader12.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader12.getProperty(name12)).isEqualTo("value");

        String name13 = getClass().getName() + "_name13_v";
        String prefix13 = "prefix.";
        String suffix13 = ".suffix";
        Set<String> excludeProperties13 = new HashSet<>();
        excludeProperties13.add(name13);
        System.setProperty(prefix13 + name13 + suffix13, "value");
        SystemPropertiesLoader loader13 = new SystemPropertiesLoader(prefix13, suffix13, excludeProperties13);
        Assertions.assertThat(loader13.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader13.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader13.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader13.getProperty(name13)).isNull();
        loader13.load();
        Assertions.assertThat(loader13.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader13.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader13.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader13.getProperty(name13)).isNull();

        String name141 = getClass().getName() + "_name141_v";
        String name142 = getClass().getName() + "_name142_v";
        String prefix14 = null;
        String suffix14 = null;
        Set<String> excludeProperties14 = new HashSet<>();
        excludeProperties14.add(name141);
        System.setProperty(name141, "value");
        SystemPropertiesLoader loader14 = new SystemPropertiesLoader(prefix14, suffix14, excludeProperties14);
        Assertions.assertThat(loader14.getProperty("file.separator")).isNull();
        Assertions.assertThat(loader14.getProperty("line.separator")).isNull();
        Assertions.assertThat(loader14.getProperty("path.separator")).isNull();
        Assertions.assertThat(loader14.getProperty(name141)).isNull();
        Assertions.assertThat(loader14.getProperty(name142)).isNull();
        loader14.load();
        Assertions.assertThat(loader14.getProperty("file.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("line.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("path.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty(name141)).isNull();
        Assertions.assertThat(loader14.getProperty(name142)).isNull();
        excludeProperties14.add("file.separator");
        Assertions.assertThat(loader14.getProperty("file.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("line.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("path.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty(name141)).isNull();
        Assertions.assertThat(loader14.getProperty(name142)).isNull();
        loader14.load();
        Assertions.assertThat(loader14.getProperty("file.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("line.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("path.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty(name141)).isNull();
        Assertions.assertThat(loader14.getProperty(name142)).isNull();
        System.setProperty(name142, "value");
        Assertions.assertThat(loader14.getProperty("file.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("line.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("path.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty(name141)).isNull();
        Assertions.assertThat(loader14.getProperty(name142)).isNull();
        loader14.load();
        Assertions.assertThat(loader14.getProperty("file.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("line.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty("path.separator")).isNotNull();
        Assertions.assertThat(loader14.getProperty(name141)).isNull();
        Assertions.assertThat(loader14.getProperty(name142)).isEqualTo("value");
    }

}
