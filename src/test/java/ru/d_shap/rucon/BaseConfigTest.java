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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link BaseConfig}.
 *
 * @author Dmitry Shapovalov
 */
public final class BaseConfigTest {

    /**
     * Test class constructor.
     */
    public BaseConfigTest() {
        super();
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void fillSetTest() {
        BaseConfig baseConfig = new BaseConfig(null, null, null, null);

        Set<String> fromSet1 = null;
        Set<String> toSet1 = new HashSet<>();
        baseConfig.fillSet(fromSet1, toSet1);
        Assertions.assertThat(toSet1).containsExactly();

        Set<String> fromSet2 = new HashSet<>();
        Set<String> toSet2 = new HashSet<>();
        baseConfig.fillSet(fromSet2, toSet2);
        Assertions.assertThat(toSet2).containsExactly();

        Set<String> fromSet3 = new HashSet<>();
        fromSet3.add("value");
        Set<String> toSet3 = new HashSet<>();
        baseConfig.fillSet(fromSet3, toSet3);
        Assertions.assertThat(toSet3).containsExactly("value");

        Set<String> fromSet4 = new HashSet<>();
        fromSet4.add("value1");
        fromSet4.add("value2");
        fromSet4.add("value3");
        fromSet4.add("value4");
        Set<String> toSet4 = new HashSet<>();
        baseConfig.fillSet(fromSet4, toSet4);
        Assertions.assertThat(toSet4).containsExactly("value1", "value2", "value3", "value4");

        Set<String> fromSet5 = new HashSet<>();
        fromSet5.add(null);
        Set<String> toSet5 = new HashSet<>();
        baseConfig.fillSet(fromSet5, toSet5);
        Assertions.assertThat(toSet5).containsExactly((String) null);
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test(expected = NullPointerException.class)
    public void fillSetNullFailTest() {
        BaseConfig baseConfig = new BaseConfig(null, null, null, null);

        Set<String> fromSet = new HashSet<>();
        fromSet.add("value");
        Set<String> toSet = null;
        baseConfig.fillSet(fromSet, toSet);
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void fillMapTest() {
        BaseConfig baseConfig = new BaseConfig(null, null, null, null);

        Map<String, String> fromMap1 = null;
        Map<String, String> toMap1 = new HashMap<>();
        baseConfig.fillMap(fromMap1, toMap1);
        Assertions.assertThat(toMap1).containsExactly();

        Map<String, String> fromMap2 = new HashMap<>();
        Map<String, String> toMap2 = new HashMap<>();
        baseConfig.fillMap(fromMap2, toMap2);
        Assertions.assertThat(toMap2).containsExactly();

        Map<String, String> fromMap3 = new HashMap<>();
        fromMap3.put("key", "value");
        Map<String, String> toMap3 = new HashMap<>();
        baseConfig.fillMap(fromMap3, toMap3);
        Assertions.assertThat(toMap3).containsExactly("key", "value");

        Map<String, String> fromMap4 = new HashMap<>();
        fromMap4.put("key1", "value1");
        fromMap4.put("key2", "value2");
        fromMap4.put("key3", "value3");
        fromMap4.put("key4", "value4");
        Map<String, String> toMap4 = new HashMap<>();
        baseConfig.fillMap(fromMap4, toMap4);
        Assertions.assertThat(toMap4).containsExactly("key1", "value1", "key2", "value2", "key3", "value3", "key4", "value4");

        Map<String, String> fromMap5 = new HashMap<>();
        fromMap5.put(null, "value");
        Map<String, String> toMap5 = new HashMap<>();
        baseConfig.fillMap(fromMap5, toMap5);
        Assertions.assertThat(toMap5).containsExactly(null, "value");

        Map<String, String> fromMap6 = new HashMap<>();
        fromMap6.put("key", null);
        Map<String, String> toMap6 = new HashMap<>();
        baseConfig.fillMap(fromMap6, toMap6);
        Assertions.assertThat(toMap6).containsExactly("key", null);
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test(expected = NullPointerException.class)
    public void fillMapNullFailTest() {
        BaseConfig baseConfig = new BaseConfig(null, null, null, null);

        Map<String, String> fromMap = new HashMap<>();
        fromMap.put("key", "value");
        Map<String, String> toMap = null;
        baseConfig.fillMap(fromMap, toMap);
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void fillPropertiesTest() {
        BaseConfig baseConfig = new BaseConfig(null, null, null, null);

        Map<Object, Object> fromMap1 = null;
        Map<String, String> toMap1 = new HashMap<>();
        baseConfig.fillProperties(fromMap1, toMap1);
        Assertions.assertThat(toMap1).containsExactly();

        Map<Object, Object> fromMap2 = new HashMap<>();
        Map<String, String> toMap2 = new HashMap<>();
        baseConfig.fillProperties(fromMap2, toMap2);
        Assertions.assertThat(toMap2).containsExactly();

        Map<Object, Object> fromMap3 = new HashMap<>();
        fromMap3.put("key", "value");
        Map<String, String> toMap3 = new HashMap<>();
        baseConfig.fillProperties(fromMap3, toMap3);
        Assertions.assertThat(toMap3).containsExactly("key", "value");

        Map<Object, Object> fromMap4 = new HashMap<>();
        fromMap4.put("key1", "value1");
        fromMap4.put("key2", "value2");
        fromMap4.put("key3", "value3");
        fromMap4.put("key4", "value4");
        Map<String, String> toMap4 = new HashMap<>();
        baseConfig.fillProperties(fromMap4, toMap4);
        Assertions.assertThat(toMap4).containsExactly("key1", "value1", "key2", "value2", "key3", "value3", "key4", "value4");

        Map<Object, Object> fromMap5 = new HashMap<>();
        fromMap5.put(new StringBuilder("key"), "value");
        Map<String, String> toMap5 = new HashMap<>();
        baseConfig.fillProperties(fromMap5, toMap5);
        Assertions.assertThat(toMap5).containsExactly("key", "value");

        Map<Object, Object> fromMap6 = new HashMap<>();
        fromMap6.put("key", new StringBuilder("value"));
        Map<String, String> toMap6 = new HashMap<>();
        baseConfig.fillProperties(fromMap6, toMap6);
        Assertions.assertThat(toMap6).containsExactly("key", "value");

        Map<Object, Object> fromMap7 = new HashMap<>();
        fromMap7.put(null, "value");
        Map<String, String> toMap7 = new HashMap<>();
        baseConfig.fillProperties(fromMap7, toMap7);
        Assertions.assertThat(toMap7).containsExactly(null, "value");

        Map<Object, Object> fromMap8 = new HashMap<>();
        fromMap8.put("key", null);
        Map<String, String> toMap8 = new HashMap<>();
        baseConfig.fillProperties(fromMap8, toMap8);
        Assertions.assertThat(toMap8).containsExactly("key", null);
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test(expected = NullPointerException.class)
    public void fillPropertiesNullFailTest() {
        BaseConfig baseConfig = new BaseConfig(null, null, null, null);

        Map<Object, Object> fromMap = new HashMap<>();
        fromMap.put("key", "value");
        Map<String, String> toMap = null;
        baseConfig.fillProperties(fromMap, toMap);
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void getPropertyNameTest() {
        // TODO
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void isExcludePropertyTest() {
        // TODO
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void getPropertyAliasTest() {
        // TODO
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void replacePropertyAliasesTest() {
        // TODO
    }

}
