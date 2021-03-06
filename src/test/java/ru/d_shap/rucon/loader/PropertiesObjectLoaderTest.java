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
 * Tests for {@link PropertiesObjectLoader}.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesObjectLoaderTest {

    /**
     * Test class constructor.
     */
    public PropertiesObjectLoaderTest() {
        super();
    }

    /**
     * {@link PropertiesObjectLoader} class test.
     */
    @Test
    public void getNamesTest() {
        Map<Object, Object> properties01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesObjectLoader loader01 = new PropertiesObjectLoader(properties01, excludeProperties01);
        Assertions.assertThat(loader01.getNames()).containsExactly();
        loader01.load();
        Assertions.assertThat(loader01.getNames()).containsExactly();

        Map<Object, Object> properties02 = new HashMap<>();
        Set<String> excludeProperties02 = null;
        PropertiesObjectLoader loader02 = new PropertiesObjectLoader(properties02, excludeProperties02);
        Assertions.assertThat(loader02.getNames()).containsExactly();
        loader02.load();
        Assertions.assertThat(loader02.getNames()).containsExactly();

        Map<Object, Object> properties03 = new HashMap<>();
        properties03.put("key", "value");
        Set<String> excludeProperties03 = null;
        PropertiesObjectLoader loader03 = new PropertiesObjectLoader(properties03, excludeProperties03);
        Assertions.assertThat(loader03.getNames()).containsExactly();
        loader03.load();
        Assertions.assertThat(loader03.getNames()).containsExactly("key");

        Map<Object, Object> properties04 = new HashMap<>();
        properties04.put("key1", "value1");
        properties04.put("key2", "value2");
        Set<String> excludeProperties04 = null;
        PropertiesObjectLoader loader04 = new PropertiesObjectLoader(properties04, excludeProperties04);
        Assertions.assertThat(loader04.getNames()).containsExactly();
        loader04.load();
        Assertions.assertThat(loader04.getNames()).containsExactly("key1", "key2");

        Map<Object, Object> properties05 = new HashMap<>();
        properties05.put("key1", null);
        properties05.put(null, "value2");
        Set<String> excludeProperties05 = null;
        PropertiesObjectLoader loader05 = new PropertiesObjectLoader(properties05, excludeProperties05);
        Assertions.assertThat(loader05.getNames()).containsExactly();
        loader05.load();
        Assertions.assertThat(loader05.getNames()).containsExactly("key1", null);

        Map<Object, Object> properties06 = new HashMap<>();
        properties06.put("key1", new StringBuilder("value1"));
        properties06.put(new StringBuilder("key2"), "value2");
        Set<String> excludeProperties06 = null;
        PropertiesObjectLoader loader06 = new PropertiesObjectLoader(properties06, excludeProperties06);
        Assertions.assertThat(loader06.getNames()).containsExactly();
        loader06.load();
        Assertions.assertThat(loader06.getNames()).containsExactly("key1", "key2");

        Map<Object, Object> properties07 = new HashMap<>();
        properties07.put("key1", "value1");
        properties07.put("key2", "value2");
        Set<String> excludeProperties07 = new HashSet<>();
        PropertiesObjectLoader loader07 = new PropertiesObjectLoader(properties07, excludeProperties07);
        Assertions.assertThat(loader07.getNames()).containsExactly();
        loader07.load();
        Assertions.assertThat(loader07.getNames()).containsExactly("key1", "key2");

        Map<Object, Object> properties08 = new HashMap<>();
        properties08.put("key1", "value1");
        properties08.put("key2", "value2");
        Set<String> excludeProperties08 = new HashSet<>();
        excludeProperties08.add("key1");
        PropertiesObjectLoader loader08 = new PropertiesObjectLoader(properties08, excludeProperties08);
        Assertions.assertThat(loader08.getNames()).containsExactly();
        loader08.load();
        Assertions.assertThat(loader08.getNames()).containsExactly("key2");

        Map<Object, Object> properties09 = new HashMap<>();
        properties09.put("key1", "value1");
        properties09.put("key2", "value2");
        Set<String> excludeProperties09 = null;
        PropertiesObjectLoader loader09 = new PropertiesObjectLoader(properties09, excludeProperties09);
        Assertions.assertThat(loader09.getNames()).containsExactly();
        loader09.load();
        Assertions.assertThat(loader09.getNames()).containsExactly("key1", "key2");
        loader09.getNames().add("key");
        Assertions.assertThat(loader09.getNames()).containsExactly("key1", "key2");
        loader09.load();
        Assertions.assertThat(loader09.getNames()).containsExactly("key1", "key2");

        Map<Object, Object> properties10 = new HashMap<>();
        properties10.put("key1", "value1");
        properties10.put("key2", "value2");
        Set<String> excludeProperties10 = new HashSet<>();
        excludeProperties10.add("key1");
        PropertiesObjectLoader loader10 = new PropertiesObjectLoader(properties10, excludeProperties10);
        Assertions.assertThat(loader10.getNames()).containsExactly();
        loader10.load();
        Assertions.assertThat(loader10.getNames()).containsExactly("key2");
        properties10.put("key", "value");
        properties10.remove("key2");
        Assertions.assertThat(loader10.getNames()).containsExactly("key2");
        excludeProperties10.add("key2");
        Assertions.assertThat(loader10.getNames()).containsExactly("key2");
        loader10.load();
        Assertions.assertThat(loader10.getNames()).containsExactly("key2");
    }

    /**
     * {@link PropertiesObjectLoader} class test.
     */
    @Test
    public void getPropertyTest() {
        Map<Object, Object> properties01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesObjectLoader loader01 = new PropertiesObjectLoader(properties01, excludeProperties01);
        Assertions.assertThat(loader01.getProperty(null)).isNull();
        Assertions.assertThat(loader01.getProperty("")).isNull();
        Assertions.assertThat(loader01.getProperty(" ")).isNull();
        Assertions.assertThat(loader01.getProperty("key")).isNull();
        Assertions.assertThat(loader01.getProperty("key1")).isNull();
        Assertions.assertThat(loader01.getProperty("key2")).isNull();
        loader01.load();
        Assertions.assertThat(loader01.getProperty(null)).isNull();
        Assertions.assertThat(loader01.getProperty("")).isNull();
        Assertions.assertThat(loader01.getProperty(" ")).isNull();
        Assertions.assertThat(loader01.getProperty("key")).isNull();
        Assertions.assertThat(loader01.getProperty("key1")).isNull();
        Assertions.assertThat(loader01.getProperty("key2")).isNull();

        Map<Object, Object> properties02 = new HashMap<>();
        Set<String> excludeProperties02 = null;
        PropertiesObjectLoader loader02 = new PropertiesObjectLoader(properties02, excludeProperties02);
        Assertions.assertThat(loader02.getProperty(null)).isNull();
        Assertions.assertThat(loader02.getProperty("")).isNull();
        Assertions.assertThat(loader02.getProperty(" ")).isNull();
        Assertions.assertThat(loader02.getProperty("key")).isNull();
        Assertions.assertThat(loader02.getProperty("key1")).isNull();
        Assertions.assertThat(loader02.getProperty("key2")).isNull();
        loader02.load();
        Assertions.assertThat(loader02.getProperty(null)).isNull();
        Assertions.assertThat(loader02.getProperty("")).isNull();
        Assertions.assertThat(loader02.getProperty(" ")).isNull();
        Assertions.assertThat(loader02.getProperty("key")).isNull();
        Assertions.assertThat(loader02.getProperty("key1")).isNull();
        Assertions.assertThat(loader02.getProperty("key2")).isNull();

        Map<Object, Object> properties03 = new HashMap<>();
        properties03.put("key", "value");
        Set<String> excludeProperties03 = null;
        PropertiesObjectLoader loader03 = new PropertiesObjectLoader(properties03, excludeProperties03);
        Assertions.assertThat(loader03.getProperty(null)).isNull();
        Assertions.assertThat(loader03.getProperty("")).isNull();
        Assertions.assertThat(loader03.getProperty(" ")).isNull();
        Assertions.assertThat(loader03.getProperty("key")).isNull();
        Assertions.assertThat(loader03.getProperty("key1")).isNull();
        Assertions.assertThat(loader03.getProperty("key2")).isNull();
        loader03.load();
        Assertions.assertThat(loader03.getProperty(null)).isNull();
        Assertions.assertThat(loader03.getProperty("")).isNull();
        Assertions.assertThat(loader03.getProperty(" ")).isNull();
        Assertions.assertThat(loader03.getProperty("key")).isEqualTo("value");
        Assertions.assertThat(loader03.getProperty("key1")).isNull();
        Assertions.assertThat(loader03.getProperty("key2")).isNull();

        Map<Object, Object> properties04 = new HashMap<>();
        properties04.put("key1", "value1");
        properties04.put("key2", "value2");
        Set<String> excludeProperties04 = null;
        PropertiesObjectLoader loader04 = new PropertiesObjectLoader(properties04, excludeProperties04);
        Assertions.assertThat(loader04.getProperty(null)).isNull();
        Assertions.assertThat(loader04.getProperty("")).isNull();
        Assertions.assertThat(loader04.getProperty(" ")).isNull();
        Assertions.assertThat(loader04.getProperty("key")).isNull();
        Assertions.assertThat(loader04.getProperty("key1")).isNull();
        Assertions.assertThat(loader04.getProperty("key2")).isNull();
        loader04.load();
        Assertions.assertThat(loader04.getProperty(null)).isNull();
        Assertions.assertThat(loader04.getProperty("")).isNull();
        Assertions.assertThat(loader04.getProperty(" ")).isNull();
        Assertions.assertThat(loader04.getProperty("key")).isNull();
        Assertions.assertThat(loader04.getProperty("key1")).isEqualTo("value1");
        Assertions.assertThat(loader04.getProperty("key2")).isEqualTo("value2");

        Map<Object, Object> properties05 = new HashMap<>();
        properties05.put("key1", null);
        properties05.put(null, "value2");
        Set<String> excludeProperties05 = null;
        PropertiesObjectLoader loader05 = new PropertiesObjectLoader(properties05, excludeProperties05);
        Assertions.assertThat(loader05.getProperty(null)).isNull();
        Assertions.assertThat(loader05.getProperty("")).isNull();
        Assertions.assertThat(loader05.getProperty(" ")).isNull();
        Assertions.assertThat(loader05.getProperty("key")).isNull();
        Assertions.assertThat(loader05.getProperty("key1")).isNull();
        Assertions.assertThat(loader05.getProperty("key2")).isNull();
        loader05.load();
        Assertions.assertThat(loader05.getProperty(null)).isEqualTo("value2");
        Assertions.assertThat(loader05.getProperty("")).isNull();
        Assertions.assertThat(loader05.getProperty(" ")).isNull();
        Assertions.assertThat(loader05.getProperty("key")).isNull();
        Assertions.assertThat(loader05.getProperty("key1")).isNull();
        Assertions.assertThat(loader05.getProperty("key2")).isNull();

        Map<Object, Object> properties06 = new HashMap<>();
        properties06.put("key1", new StringBuilder("value1"));
        properties06.put(new StringBuilder("key2"), "value2");
        Set<String> excludeProperties06 = null;
        PropertiesObjectLoader loader06 = new PropertiesObjectLoader(properties06, excludeProperties06);
        Assertions.assertThat(loader06.getProperty(null)).isNull();
        Assertions.assertThat(loader06.getProperty("")).isNull();
        Assertions.assertThat(loader06.getProperty(" ")).isNull();
        Assertions.assertThat(loader06.getProperty("key")).isNull();
        Assertions.assertThat(loader06.getProperty("key1")).isNull();
        Assertions.assertThat(loader06.getProperty("key2")).isNull();
        loader06.load();
        Assertions.assertThat(loader06.getProperty(null)).isNull();
        Assertions.assertThat(loader06.getProperty("")).isNull();
        Assertions.assertThat(loader06.getProperty(" ")).isNull();
        Assertions.assertThat(loader06.getProperty("key")).isNull();
        Assertions.assertThat(loader06.getProperty("key1")).isEqualTo("value1");
        Assertions.assertThat(loader06.getProperty("key2")).isEqualTo("value2");

        Map<Object, Object> properties07 = new HashMap<>();
        properties07.put("key1", "value1");
        properties07.put("key2", "value2");
        Set<String> excludeProperties07 = new HashSet<>();
        PropertiesObjectLoader loader07 = new PropertiesObjectLoader(properties07, excludeProperties07);
        Assertions.assertThat(loader07.getProperty(null)).isNull();
        Assertions.assertThat(loader07.getProperty("")).isNull();
        Assertions.assertThat(loader07.getProperty(" ")).isNull();
        Assertions.assertThat(loader07.getProperty("key")).isNull();
        Assertions.assertThat(loader07.getProperty("key1")).isNull();
        Assertions.assertThat(loader07.getProperty("key2")).isNull();
        loader07.load();
        Assertions.assertThat(loader07.getProperty(null)).isNull();
        Assertions.assertThat(loader07.getProperty("")).isNull();
        Assertions.assertThat(loader07.getProperty(" ")).isNull();
        Assertions.assertThat(loader07.getProperty("key")).isNull();
        Assertions.assertThat(loader07.getProperty("key1")).isEqualTo("value1");
        Assertions.assertThat(loader07.getProperty("key2")).isEqualTo("value2");

        Map<Object, Object> properties08 = new HashMap<>();
        properties08.put("key1", "value1");
        properties08.put("key2", "value2");
        Set<String> excludeProperties08 = new HashSet<>();
        excludeProperties08.add("key1");
        PropertiesObjectLoader loader08 = new PropertiesObjectLoader(properties08, excludeProperties08);
        Assertions.assertThat(loader08.getProperty(null)).isNull();
        Assertions.assertThat(loader08.getProperty("")).isNull();
        Assertions.assertThat(loader08.getProperty(" ")).isNull();
        Assertions.assertThat(loader08.getProperty("key")).isNull();
        Assertions.assertThat(loader08.getProperty("key1")).isNull();
        Assertions.assertThat(loader08.getProperty("key2")).isNull();
        loader08.load();
        Assertions.assertThat(loader08.getProperty(null)).isNull();
        Assertions.assertThat(loader08.getProperty("")).isNull();
        Assertions.assertThat(loader08.getProperty(" ")).isNull();
        Assertions.assertThat(loader08.getProperty("key")).isNull();
        Assertions.assertThat(loader08.getProperty("key1")).isNull();
        Assertions.assertThat(loader08.getProperty("key2")).isEqualTo("value2");

        Map<Object, Object> properties09 = new HashMap<>();
        properties09.put("key1", "value1");
        properties09.put("key2", "value2");
        Set<String> excludeProperties09 = new HashSet<>();
        excludeProperties09.add("key1");
        PropertiesObjectLoader loader09 = new PropertiesObjectLoader(properties09, excludeProperties09);
        Assertions.assertThat(loader09.getProperty(null)).isNull();
        Assertions.assertThat(loader09.getProperty("")).isNull();
        Assertions.assertThat(loader09.getProperty(" ")).isNull();
        Assertions.assertThat(loader09.getProperty("key")).isNull();
        Assertions.assertThat(loader09.getProperty("key1")).isNull();
        Assertions.assertThat(loader09.getProperty("key2")).isNull();
        loader09.load();
        Assertions.assertThat(loader09.getProperty(null)).isNull();
        Assertions.assertThat(loader09.getProperty("")).isNull();
        Assertions.assertThat(loader09.getProperty(" ")).isNull();
        Assertions.assertThat(loader09.getProperty("key")).isNull();
        Assertions.assertThat(loader09.getProperty("key1")).isNull();
        Assertions.assertThat(loader09.getProperty("key2")).isEqualTo("value2");
        properties09.put("key", "value");
        properties09.remove("key2");
        Assertions.assertThat(loader09.getProperty(null)).isNull();
        Assertions.assertThat(loader09.getProperty("")).isNull();
        Assertions.assertThat(loader09.getProperty(" ")).isNull();
        Assertions.assertThat(loader09.getProperty("key")).isNull();
        Assertions.assertThat(loader09.getProperty("key1")).isNull();
        Assertions.assertThat(loader09.getProperty("key2")).isEqualTo("value2");
        excludeProperties09.add("key2");
        Assertions.assertThat(loader09.getProperty(null)).isNull();
        Assertions.assertThat(loader09.getProperty("")).isNull();
        Assertions.assertThat(loader09.getProperty(" ")).isNull();
        Assertions.assertThat(loader09.getProperty("key")).isNull();
        Assertions.assertThat(loader09.getProperty("key1")).isNull();
        Assertions.assertThat(loader09.getProperty("key2")).isEqualTo("value2");
        loader09.load();
        Assertions.assertThat(loader09.getProperty(null)).isNull();
        Assertions.assertThat(loader09.getProperty("")).isNull();
        Assertions.assertThat(loader09.getProperty(" ")).isNull();
        Assertions.assertThat(loader09.getProperty("key")).isNull();
        Assertions.assertThat(loader09.getProperty("key1")).isNull();
        Assertions.assertThat(loader09.getProperty("key2")).isEqualTo("value2");
    }

}
