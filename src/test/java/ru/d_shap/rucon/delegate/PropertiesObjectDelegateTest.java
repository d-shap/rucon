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
package ru.d_shap.rucon.delegate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link PropertiesObjectDelegate}.
 *
 * @author Dmitry Shapovalov
 */
public final class PropertiesObjectDelegateTest {

    /**
     * Test class constructor.
     */
    public PropertiesObjectDelegateTest() {
        super();
    }

    /**
     * {@link PropertiesObjectDelegate} class test.
     */
    @Test
    public void getNamesTest() {
        Map<Object, Object> properties01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesObjectDelegate delegate01 = new PropertiesObjectDelegate(properties01, excludeProperties01);
        Assertions.assertThat(delegate01.getNames()).containsExactly();

        Map<Object, Object> properties02 = new HashMap<>();
        Set<String> excludeProperties02 = null;
        PropertiesObjectDelegate delegate02 = new PropertiesObjectDelegate(properties02, excludeProperties02);
        Assertions.assertThat(delegate02.getNames()).containsExactly();

        Map<Object, Object> properties03 = new HashMap<>();
        properties03.put("key", "value");
        Set<String> excludeProperties03 = null;
        PropertiesObjectDelegate delegate03 = new PropertiesObjectDelegate(properties03, excludeProperties03);
        Assertions.assertThat(delegate03.getNames()).containsExactly("key");

        Map<Object, Object> properties04 = new HashMap<>();
        properties04.put("key1", "value1");
        properties04.put("key2", "value2");
        Set<String> excludeProperties04 = null;
        PropertiesObjectDelegate delegate04 = new PropertiesObjectDelegate(properties04, excludeProperties04);
        Assertions.assertThat(delegate04.getNames()).containsExactly("key1", "key2");

        Map<Object, Object> properties05 = new HashMap<>();
        properties05.put("key1", null);
        properties05.put(null, "value2");
        Set<String> excludeProperties05 = null;
        PropertiesObjectDelegate delegate05 = new PropertiesObjectDelegate(properties05, excludeProperties05);
        Assertions.assertThat(delegate05.getNames()).containsExactly("key1", null);

        Map<Object, Object> properties06 = new HashMap<>();
        properties06.put("key1", new StringBuilder("value1"));
        properties06.put(new StringBuilder("key2"), "value2");
        Set<String> excludeProperties06 = null;
        PropertiesObjectDelegate delegate06 = new PropertiesObjectDelegate(properties06, excludeProperties06);
        Assertions.assertThat(delegate06.getNames()).containsExactly("key1", "key2");

        Map<Object, Object> properties07 = new HashMap<>();
        properties07.put("key1", "value1");
        properties07.put("key2", "value2");
        Set<String> excludeProperties07 = new HashSet<>();
        PropertiesObjectDelegate delegate07 = new PropertiesObjectDelegate(properties07, excludeProperties07);
        Assertions.assertThat(delegate07.getNames()).containsExactly("key1", "key2");

        Map<Object, Object> properties08 = new HashMap<>();
        properties08.put("key1", "value1");
        properties08.put("key2", "value2");
        Set<String> excludeProperties08 = new HashSet<>();
        excludeProperties08.add("key1");
        PropertiesObjectDelegate delegate08 = new PropertiesObjectDelegate(properties08, excludeProperties08);
        Assertions.assertThat(delegate08.getNames()).containsExactly("key2");

        Map<Object, Object> properties09 = new HashMap<>();
        properties09.put("key1", "value1");
        properties09.put("key2", "value2");
        Set<String> excludeProperties09 = null;
        PropertiesObjectDelegate delegate09 = new PropertiesObjectDelegate(properties09, excludeProperties09);
        Assertions.assertThat(delegate09.getNames()).containsExactly("key1", "key2");
        delegate09.getNames().add("key");
        Assertions.assertThat(delegate09.getNames()).containsExactly("key1", "key2");

        Map<Object, Object> properties10 = new HashMap<>();
        properties10.put("key1", "value1");
        properties10.put("key2", "value2");
        Set<String> excludeProperties10 = new HashSet<>();
        excludeProperties10.add("key1");
        PropertiesObjectDelegate delegate10 = new PropertiesObjectDelegate(properties10, excludeProperties10);
        Assertions.assertThat(delegate10.getNames()).containsExactly("key2");
        properties10.put("key", "value");
        properties10.remove("key2");
        Assertions.assertThat(delegate10.getNames()).containsExactly("key");
        excludeProperties10.add("key");
        Assertions.assertThat(delegate10.getNames()).containsExactly("key");
    }

    /**
     * {@link PropertiesObjectDelegate} class test.
     */
    @Test
    public void getPropertyTest() {
        Map<Object, Object> properties01 = null;
        Set<String> excludeProperties01 = null;
        PropertiesObjectDelegate loader01 = new PropertiesObjectDelegate(properties01, excludeProperties01);
        Assertions.assertThat(loader01.getProperty(null)).isNull();
        Assertions.assertThat(loader01.getProperty("")).isNull();
        Assertions.assertThat(loader01.getProperty(" ")).isNull();
        Assertions.assertThat(loader01.getProperty("key")).isNull();
        Assertions.assertThat(loader01.getProperty("key1")).isNull();
        Assertions.assertThat(loader01.getProperty("key2")).isNull();

        Map<Object, Object> properties02 = new HashMap<>();
        Set<String> excludeProperties02 = null;
        PropertiesObjectDelegate loader02 = new PropertiesObjectDelegate(properties02, excludeProperties02);
        Assertions.assertThat(loader02.getProperty(null)).isNull();
        Assertions.assertThat(loader02.getProperty("")).isNull();
        Assertions.assertThat(loader02.getProperty(" ")).isNull();
        Assertions.assertThat(loader02.getProperty("key")).isNull();
        Assertions.assertThat(loader02.getProperty("key1")).isNull();
        Assertions.assertThat(loader02.getProperty("key2")).isNull();

        Map<Object, Object> properties03 = new HashMap<>();
        properties03.put("key", "value");
        Set<String> excludeProperties03 = null;
        PropertiesObjectDelegate loader03 = new PropertiesObjectDelegate(properties03, excludeProperties03);
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
        PropertiesObjectDelegate loader04 = new PropertiesObjectDelegate(properties04, excludeProperties04);
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
        PropertiesObjectDelegate loader05 = new PropertiesObjectDelegate(properties05, excludeProperties05);
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
        PropertiesObjectDelegate loader06 = new PropertiesObjectDelegate(properties06, excludeProperties06);
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
        PropertiesObjectDelegate loader07 = new PropertiesObjectDelegate(properties07, excludeProperties07);
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
        PropertiesObjectDelegate loader08 = new PropertiesObjectDelegate(properties08, excludeProperties08);
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
        PropertiesObjectDelegate loader09 = new PropertiesObjectDelegate(properties09, excludeProperties09);
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
        Assertions.assertThat(loader09.getProperty("key")).isEqualTo("value");
        Assertions.assertThat(loader09.getProperty("key1")).isNull();
        Assertions.assertThat(loader09.getProperty("key2")).isNull();
        excludeProperties09.add("key2");
        Assertions.assertThat(loader09.getProperty(null)).isNull();
        Assertions.assertThat(loader09.getProperty("")).isNull();
        Assertions.assertThat(loader09.getProperty(" ")).isNull();
        Assertions.assertThat(loader09.getProperty("key")).isEqualTo("value");
        Assertions.assertThat(loader09.getProperty("key1")).isNull();
        Assertions.assertThat(loader09.getProperty("key2")).isNull();
    }

}
