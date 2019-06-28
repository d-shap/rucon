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
        Set<String> fromSet = new HashSet<>();
        fromSet.add("value");
        Set<String> toSet = null;
        new BaseConfig(null, null, null, null).fillSet(fromSet, toSet);
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
        Map<String, String> fromMap = new HashMap<>();
        fromMap.put("key", "value");
        Map<String, String> toMap = null;
        new BaseConfig(null, null, null, null).fillMap(fromMap, toMap);
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
        Map<Object, Object> fromMap = new HashMap<>();
        fromMap.put("key", "value");
        Map<String, String> toMap = null;
        new BaseConfig(null, null, null, null).fillProperties(fromMap, toMap);
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void getPropertyNameTest() {
        Assertions.assertThat(new BaseConfig(null, null, null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, null, null, null).getPropertyName("")).isEqualTo("");
        Assertions.assertThat(new BaseConfig(null, null, null, null).getPropertyName(" ")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig(null, null, null, null).getPropertyName("key")).isEqualTo("key");

        Assertions.assertThat(new BaseConfig("", null, null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig("", null, null, null).getPropertyName("")).isEqualTo("");
        Assertions.assertThat(new BaseConfig("", null, null, null).getPropertyName(" ")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig("", null, null, null).getPropertyName("key")).isEqualTo("key");

        Assertions.assertThat(new BaseConfig(" ", null, null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(" ", null, null, null).getPropertyName("")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig(" ", null, null, null).getPropertyName(" ")).isEqualTo("  ");
        Assertions.assertThat(new BaseConfig(" ", null, null, null).getPropertyName("key")).isEqualTo(" key");

        Assertions.assertThat(new BaseConfig("pre.", null, null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig("pre.", null, null, null).getPropertyName("")).isEqualTo("pre.");
        Assertions.assertThat(new BaseConfig("pre.", null, null, null).getPropertyName(" ")).isEqualTo("pre. ");
        Assertions.assertThat(new BaseConfig("pre.", null, null, null).getPropertyName("key")).isEqualTo("pre.key");

        Assertions.assertThat(new BaseConfig(null, "", null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, "", null, null).getPropertyName("")).isEqualTo("");
        Assertions.assertThat(new BaseConfig(null, "", null, null).getPropertyName(" ")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig(null, "", null, null).getPropertyName("key")).isEqualTo("key");

        Assertions.assertThat(new BaseConfig(null, " ", null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, " ", null, null).getPropertyName("")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig(null, " ", null, null).getPropertyName(" ")).isEqualTo("  ");
        Assertions.assertThat(new BaseConfig(null, " ", null, null).getPropertyName("key")).isEqualTo("key ");

        Assertions.assertThat(new BaseConfig(null, ".post", null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, ".post", null, null).getPropertyName("")).isEqualTo(".post");
        Assertions.assertThat(new BaseConfig(null, ".post", null, null).getPropertyName(" ")).isEqualTo(" .post");
        Assertions.assertThat(new BaseConfig(null, ".post", null, null).getPropertyName("key")).isEqualTo("key.post");

        Assertions.assertThat(new BaseConfig("pre.", ".post", null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, null).getPropertyName("")).isEqualTo("pre..post");
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, null).getPropertyName(" ")).isEqualTo("pre. .post");
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, null).getPropertyName("key")).isEqualTo("pre.key.post");
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void isExcludePropertyTest() {
        Set<String> excludeProperties1 = null;
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties1).isExcludeProperty(null)).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties1).isExcludeProperty("")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties1).isExcludeProperty(" ")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties1).isExcludeProperty("key")).isFalse();

        Set<String> excludeProperties2 = new HashSet<>();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties2).isExcludeProperty(null)).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties2).isExcludeProperty("")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties2).isExcludeProperty(" ")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties2).isExcludeProperty("key")).isFalse();

        Set<String> excludeProperties3 = new HashSet<>();
        excludeProperties3.add("key");
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties3).isExcludeProperty(null)).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties3).isExcludeProperty("")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties3).isExcludeProperty(" ")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties3).isExcludeProperty("key")).isTrue();

        Set<String> excludeProperties4 = new HashSet<>();
        excludeProperties4.add(null);
        excludeProperties4.add("");
        excludeProperties4.add(" ");
        excludeProperties4.add("key");
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties4).isExcludeProperty(null)).isTrue();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties4).isExcludeProperty("")).isTrue();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties4).isExcludeProperty(" ")).isTrue();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties4).isExcludeProperty("key")).isTrue();

        Set<String> excludeProperties5 = new HashSet<>();
        excludeProperties5.add("key");
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties5).isExcludeProperty(null)).isFalse();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties5).isExcludeProperty("")).isFalse();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties5).isExcludeProperty(" ")).isFalse();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties5).isExcludeProperty("key")).isTrue();

        Set<String> excludeProperties6 = new HashSet<>();
        excludeProperties6.add(null);
        excludeProperties6.add("");
        excludeProperties6.add(" ");
        excludeProperties6.add("key");
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties6).isExcludeProperty(null)).isTrue();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties6).isExcludeProperty("")).isTrue();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties6).isExcludeProperty(" ")).isTrue();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties6).isExcludeProperty("key")).isTrue();
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void excludePropertiesTest() {
        Set<String> excludeProperties1 = null;
        Map<String, String> properties11 = new HashMap<>();
        new BaseConfig(null, null, null, excludeProperties1).excludeProperties(properties11);
        Assertions.assertThat(properties11).containsExactly();
        Map<String, String> properties12 = new HashMap<>();
        properties12.put("key", "value");
        new BaseConfig(null, null, null, excludeProperties1).excludeProperties(properties12);
        Assertions.assertThat(properties12).containsExactly("key", "value");
        Map<String, String> properties13 = new HashMap<>();
        properties13.put("key", "value1");
        properties13.put("pre.key", "value2");
        properties13.put("key.post", "value3");
        properties13.put("pre.key.post", "value4");
        new BaseConfig(null, null, null, excludeProperties1).excludeProperties(properties13);
        Assertions.assertThat(properties13).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties14 = new HashMap<>();
        properties14.put(null, "value");
        new BaseConfig(null, null, null, excludeProperties1).excludeProperties(properties14);
        Assertions.assertThat(properties14).containsExactly(null, "value");
        Map<String, String> properties15 = new HashMap<>();
        properties15.put("key", null);
        new BaseConfig(null, null, null, excludeProperties1).excludeProperties(properties15);
        Assertions.assertThat(properties15).containsExactly("key", null);
        Map<String, String> properties16 = new HashMap<>();
        properties16.put("key", "value1");
        properties16.put("pre.key", "value2");
        properties16.put("key.post", "value3");
        properties16.put("pre.key.post", "value4");
        new BaseConfig("pre.", null, null, excludeProperties1).excludeProperties(properties16);
        Assertions.assertThat(properties16).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties17 = new HashMap<>();
        properties17.put("key", "value1");
        properties17.put("pre.key", "value2");
        properties17.put("key.post", "value3");
        properties17.put("pre.key.post", "value4");
        new BaseConfig(null, ".post", null, excludeProperties1).excludeProperties(properties17);
        Assertions.assertThat(properties17).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties18 = new HashMap<>();
        properties18.put("key", "value1");
        properties18.put("pre.key", "value2");
        properties18.put("key.post", "value3");
        properties18.put("pre.key.post", "value4");
        new BaseConfig("pre.", ".post", null, excludeProperties1).excludeProperties(properties18);
        Assertions.assertThat(properties18).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");

        Set<String> excludeProperties2 = new HashSet<>();
        Map<String, String> properties21 = new HashMap<>();
        new BaseConfig(null, null, null, excludeProperties2).excludeProperties(properties21);
        Assertions.assertThat(properties21).containsExactly();
        Map<String, String> properties22 = new HashMap<>();
        properties22.put("key", "value");
        new BaseConfig(null, null, null, excludeProperties2).excludeProperties(properties22);
        Assertions.assertThat(properties22).containsExactly("key", "value");
        Map<String, String> properties23 = new HashMap<>();
        properties23.put("key", "value1");
        properties23.put("pre.key", "value2");
        properties23.put("key.post", "value3");
        properties23.put("pre.key.post", "value4");
        new BaseConfig(null, null, null, excludeProperties2).excludeProperties(properties23);
        Assertions.assertThat(properties23).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties24 = new HashMap<>();
        properties24.put(null, "value");
        new BaseConfig(null, null, null, excludeProperties2).excludeProperties(properties24);
        Assertions.assertThat(properties24).containsExactly(null, "value");
        Map<String, String> properties25 = new HashMap<>();
        properties25.put("key", null);
        new BaseConfig(null, null, null, excludeProperties2).excludeProperties(properties25);
        Assertions.assertThat(properties25).containsExactly("key", null);
        Map<String, String> properties26 = new HashMap<>();
        properties26.put("key", "value1");
        properties26.put("pre.key", "value2");
        properties26.put("key.post", "value3");
        properties26.put("pre.key.post", "value4");
        new BaseConfig("pre.", null, null, excludeProperties2).excludeProperties(properties26);
        Assertions.assertThat(properties26).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties27 = new HashMap<>();
        properties27.put("key", "value1");
        properties27.put("pre.key", "value2");
        properties27.put("key.post", "value3");
        properties27.put("pre.key.post", "value4");
        new BaseConfig(null, ".post", null, excludeProperties2).excludeProperties(properties27);
        Assertions.assertThat(properties27).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties28 = new HashMap<>();
        properties28.put("key", "value1");
        properties28.put("pre.key", "value2");
        properties28.put("key.post", "value3");
        properties28.put("pre.key.post", "value4");
        new BaseConfig("pre.", ".post", null, excludeProperties2).excludeProperties(properties28);
        Assertions.assertThat(properties28).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");

        Set<String> excludeProperties3 = new HashSet<>();
        excludeProperties3.add("key");
        Map<String, String> properties31 = new HashMap<>();
        new BaseConfig(null, null, null, excludeProperties3).excludeProperties(properties31);
        Assertions.assertThat(properties31).containsExactly();
        Map<String, String> properties32 = new HashMap<>();
        properties32.put("key", "value");
        new BaseConfig(null, null, null, excludeProperties3).excludeProperties(properties32);
        Assertions.assertThat(properties32).containsExactly();
        Map<String, String> properties33 = new HashMap<>();
        properties33.put("key", "value1");
        properties33.put("pre.key", "value2");
        properties33.put("key.post", "value3");
        properties33.put("pre.key.post", "value4");
        new BaseConfig(null, null, null, excludeProperties3).excludeProperties(properties33);
        Assertions.assertThat(properties33).containsExactly("pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties34 = new HashMap<>();
        properties34.put(null, "value");
        new BaseConfig(null, null, null, excludeProperties3).excludeProperties(properties34);
        Assertions.assertThat(properties34).containsExactly(null, "value");
        Map<String, String> properties35 = new HashMap<>();
        properties35.put("key", null);
        new BaseConfig(null, null, null, excludeProperties3).excludeProperties(properties35);
        Assertions.assertThat(properties35).containsExactly();
        Map<String, String> properties36 = new HashMap<>();
        properties36.put("key", "value1");
        properties36.put("pre.key", "value2");
        properties36.put("key.post", "value3");
        properties36.put("pre.key.post", "value4");
        new BaseConfig("pre.", null, null, excludeProperties3).excludeProperties(properties36);
        Assertions.assertThat(properties36).containsExactly("key", "value1", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties37 = new HashMap<>();
        properties37.put("key", "value1");
        properties37.put("pre.key", "value2");
        properties37.put("key.post", "value3");
        properties37.put("pre.key.post", "value4");
        new BaseConfig(null, ".post", null, excludeProperties3).excludeProperties(properties37);
        Assertions.assertThat(properties37).containsExactly("key", "value1", "pre.key", "value2", "pre.key.post", "value4");
        Map<String, String> properties38 = new HashMap<>();
        properties38.put("key", "value1");
        properties38.put("pre.key", "value2");
        properties38.put("key.post", "value3");
        properties38.put("pre.key.post", "value4");
        new BaseConfig("pre.", ".post", null, excludeProperties3).excludeProperties(properties38);
        Assertions.assertThat(properties38).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3");

        Set<String> excludeProperties4 = new HashSet<>();
        excludeProperties4.add(null);
        excludeProperties4.add("");
        excludeProperties4.add(" ");
        excludeProperties4.add("key");
        Map<String, String> properties41 = new HashMap<>();
        new BaseConfig(null, null, null, excludeProperties4).excludeProperties(properties41);
        Assertions.assertThat(properties41).containsExactly();
        Map<String, String> properties42 = new HashMap<>();
        properties42.put("key", "value");
        new BaseConfig(null, null, null, excludeProperties4).excludeProperties(properties42);
        Assertions.assertThat(properties42).containsExactly();
        Map<String, String> properties43 = new HashMap<>();
        properties43.put("key", "value1");
        properties43.put("pre.key", "value2");
        properties43.put("key.post", "value3");
        properties43.put("pre.key.post", "value4");
        new BaseConfig(null, null, null, excludeProperties4).excludeProperties(properties43);
        Assertions.assertThat(properties43).containsExactly("pre.key", "value2", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties44 = new HashMap<>();
        properties44.put(null, "value");
        new BaseConfig(null, null, null, excludeProperties4).excludeProperties(properties44);
        Assertions.assertThat(properties44).containsExactly();
        Map<String, String> properties45 = new HashMap<>();
        properties45.put("key", null);
        new BaseConfig(null, null, null, excludeProperties4).excludeProperties(properties45);
        Assertions.assertThat(properties45).containsExactly();
        Map<String, String> properties46 = new HashMap<>();
        properties46.put("key", "value1");
        properties46.put("pre.key", "value2");
        properties46.put("key.post", "value3");
        properties46.put("pre.key.post", "value4");
        new BaseConfig("pre.", null, null, excludeProperties4).excludeProperties(properties46);
        Assertions.assertThat(properties46).containsExactly("key", "value1", "key.post", "value3", "pre.key.post", "value4");
        Map<String, String> properties47 = new HashMap<>();
        properties47.put("key", "value1");
        properties47.put("pre.key", "value2");
        properties47.put("key.post", "value3");
        properties47.put("pre.key.post", "value4");
        new BaseConfig(null, ".post", null, excludeProperties4).excludeProperties(properties47);
        Assertions.assertThat(properties47).containsExactly("key", "value1", "pre.key", "value2", "pre.key.post", "value4");
        Map<String, String> properties48 = new HashMap<>();
        properties48.put("key", "value1");
        properties48.put("pre.key", "value2");
        properties48.put("key.post", "value3");
        properties48.put("pre.key.post", "value4");
        new BaseConfig("pre.", ".post", null, excludeProperties4).excludeProperties(properties48);
        Assertions.assertThat(properties48).containsExactly("key", "value1", "pre.key", "value2", "key.post", "value3");
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test(expected = NullPointerException.class)
    public void excludePropertiesNullFailTest() {
        Set<String> excludeProperties = new HashSet<>();
        excludeProperties.add("key");
        Map<String, String> properties = null;
        new BaseConfig(null, null, null, excludeProperties).excludeProperties(properties);
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void getPropertyAliasTest() {
        Map<String, String> propertyAliases1 = null;
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases1, null).getPropertyAlias(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases1, null).getPropertyAlias("")).isNull();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases1, null).getPropertyAlias(" ")).isNull();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases1, null).getPropertyAlias("key")).isNull();

        Map<String, String> propertyAliases2 = new HashMap<>();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases2, null).getPropertyAlias(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases2, null).getPropertyAlias("")).isNull();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases2, null).getPropertyAlias(" ")).isNull();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases2, null).getPropertyAlias("key")).isNull();

        Map<String, String> propertyAliases3 = new HashMap<>();
        propertyAliases3.put("key", "KEY_ALIAS");
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases3, null).getPropertyAlias(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases3, null).getPropertyAlias("")).isNull();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases3, null).getPropertyAlias(" ")).isNull();
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases3, null).getPropertyAlias("key")).isEqualTo("KEY_ALIAS");

        Map<String, String> propertyAliases4 = new HashMap<>();
        propertyAliases4.put(null, "NULL_ALIAS");
        propertyAliases4.put("", "EMPTY_ALIAS");
        propertyAliases4.put(" ", "SPACE_ALIAS");
        propertyAliases4.put("key", "KEY_ALIAS");
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases4, null).getPropertyAlias(null)).isEqualTo("NULL_ALIAS");
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases4, null).getPropertyAlias("")).isEqualTo("EMPTY_ALIAS");
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases4, null).getPropertyAlias(" ")).isEqualTo("SPACE_ALIAS");
        Assertions.assertThat(new BaseConfig(null, null, propertyAliases4, null).getPropertyAlias("key")).isEqualTo("KEY_ALIAS");

        Map<String, String> propertyAliases5 = new HashMap<>();
        propertyAliases5.put("key", "KEY_ALIAS");
        Assertions.assertThat(new BaseConfig("pre.", ",post", propertyAliases5, null).getPropertyAlias(null)).isNull();
        Assertions.assertThat(new BaseConfig("pre.", ",post", propertyAliases5, null).getPropertyAlias("")).isNull();
        Assertions.assertThat(new BaseConfig("pre.", ",post", propertyAliases5, null).getPropertyAlias(" ")).isNull();
        Assertions.assertThat(new BaseConfig("pre.", ",post", propertyAliases5, null).getPropertyAlias("key")).isEqualTo("KEY_ALIAS");

        Map<String, String> propertyAliases6 = new HashMap<>();
        propertyAliases6.put(null, "NULL_ALIAS");
        propertyAliases6.put("", "EMPTY_ALIAS");
        propertyAliases6.put(" ", "SPACE_ALIAS");
        propertyAliases6.put("key", "KEY_ALIAS");
        Assertions.assertThat(new BaseConfig("pre.", ",post", propertyAliases6, null).getPropertyAlias(null)).isEqualTo("NULL_ALIAS");
        Assertions.assertThat(new BaseConfig("pre.", ",post", propertyAliases6, null).getPropertyAlias("")).isEqualTo("EMPTY_ALIAS");
        Assertions.assertThat(new BaseConfig("pre.", ",post", propertyAliases6, null).getPropertyAlias(" ")).isEqualTo("SPACE_ALIAS");
        Assertions.assertThat(new BaseConfig("pre.", ",post", propertyAliases6, null).getPropertyAlias("key")).isEqualTo("KEY_ALIAS");
    }

    /**
     * {@link BaseConfig} class test.
     */
    @Test
    public void replacePropertyAliasesTest() {
        Map<String, String> propertyAliases1 = null;
        Map<String, String> properties11 = new HashMap<>();
        new BaseConfig(null, null, propertyAliases1, null).replacePropertyAliases(properties11);
        Assertions.assertThat(properties11).containsExactly();
        Map<String, String> properties12 = new HashMap<>();
        properties12.put("KEY_ALIAS", "value");
        new BaseConfig(null, null, propertyAliases1, null).replacePropertyAliases(properties12);
        Assertions.assertThat(properties12).containsExactly("KEY_ALIAS", "value");
        Map<String, String> properties13 = new HashMap<>();
        properties13.put("NULL_ALIAS", "value1");
        properties13.put("EMPTY_ALIAS", "value2");
        properties13.put("SPACE_ALIAS", "value3");
        properties13.put("KEY_ALIAS", "value4");
        new BaseConfig(null, null, propertyAliases1, null).replacePropertyAliases(properties13);
        Assertions.assertThat(properties13).containsExactly("NULL_ALIAS", "value1", "EMPTY_ALIAS", "value2", "SPACE_ALIAS", "value3", "KEY_ALIAS", "value4");
        Map<String, String> properties14 = new HashMap<>();
        properties14.put(null, "value");
        new BaseConfig(null, null, propertyAliases1, null).replacePropertyAliases(properties14);
        Assertions.assertThat(properties14).containsExactly(null, "value");
        Map<String, String> properties15 = new HashMap<>();
        properties15.put("KEY_ALIAS", null);
        new BaseConfig(null, null, propertyAliases1, null).replacePropertyAliases(properties15);
        Assertions.assertThat(properties15).containsExactly("KEY_ALIAS", null);
        Map<String, String> properties16 = new HashMap<>();
        properties16.put("NULL_ALIAS", "value1");
        properties16.put("EMPTY_ALIAS", "value2");
        properties16.put("SPACE_ALIAS", "value3");
        properties16.put("KEY_ALIAS", "value4");
        new BaseConfig("pre.", null, propertyAliases1, null).replacePropertyAliases(properties16);
        Assertions.assertThat(properties16).containsExactly("NULL_ALIAS", "value1", "EMPTY_ALIAS", "value2", "SPACE_ALIAS", "value3", "KEY_ALIAS", "value4");
        Map<String, String> properties17 = new HashMap<>();
        properties17.put("NULL_ALIAS", "value1");
        properties17.put("EMPTY_ALIAS", "value2");
        properties17.put("SPACE_ALIAS", "value3");
        properties17.put("KEY_ALIAS", "value4");
        new BaseConfig(null, ".post", propertyAliases1, null).replacePropertyAliases(properties17);
        Assertions.assertThat(properties17).containsExactly("NULL_ALIAS", "value1", "EMPTY_ALIAS", "value2", "SPACE_ALIAS", "value3", "KEY_ALIAS", "value4");
        Map<String, String> properties18 = new HashMap<>();
        properties18.put("NULL_ALIAS", "value1");
        properties18.put("EMPTY_ALIAS", "value2");
        properties18.put("SPACE_ALIAS", "value3");
        properties18.put("KEY_ALIAS", "value4");
        new BaseConfig("pre.", ".post", propertyAliases1, null).replacePropertyAliases(properties18);
        Assertions.assertThat(properties18).containsExactly("NULL_ALIAS", "value1", "EMPTY_ALIAS", "value2", "SPACE_ALIAS", "value3", "KEY_ALIAS", "value4");

        Map<String, String> propertyAliases2 = null;
        Map<String, String> properties21 = new HashMap<>();
        new BaseConfig(null, null, propertyAliases2, null).replacePropertyAliases(properties21);
        Assertions.assertThat(properties21).containsExactly();
        Map<String, String> properties22 = new HashMap<>();
        properties22.put("KEY_ALIAS", "value");
        new BaseConfig(null, null, propertyAliases2, null).replacePropertyAliases(properties22);
        Assertions.assertThat(properties22).containsExactly("KEY_ALIAS", "value");
        Map<String, String> properties23 = new HashMap<>();
        properties23.put("NULL_ALIAS", "value1");
        properties23.put("EMPTY_ALIAS", "value2");
        properties23.put("SPACE_ALIAS", "value3");
        properties23.put("KEY_ALIAS", "value4");
        new BaseConfig(null, null, propertyAliases2, null).replacePropertyAliases(properties23);
        Assertions.assertThat(properties23).containsExactly("NULL_ALIAS", "value1", "EMPTY_ALIAS", "value2", "SPACE_ALIAS", "value3", "KEY_ALIAS", "value4");
        Map<String, String> properties24 = new HashMap<>();
        properties24.put(null, "value");
        new BaseConfig(null, null, propertyAliases2, null).replacePropertyAliases(properties24);
        Assertions.assertThat(properties24).containsExactly(null, "value");
        Map<String, String> properties25 = new HashMap<>();
        properties25.put("KEY_ALIAS", null);
        new BaseConfig(null, null, propertyAliases2, null).replacePropertyAliases(properties25);
        Assertions.assertThat(properties25).containsExactly("KEY_ALIAS", null);
        Map<String, String> properties26 = new HashMap<>();
        properties26.put("NULL_ALIAS", "value1");
        properties26.put("EMPTY_ALIAS", "value2");
        properties26.put("SPACE_ALIAS", "value3");
        properties26.put("KEY_ALIAS", "value4");
        new BaseConfig("pre.", null, propertyAliases2, null).replacePropertyAliases(properties26);
        Assertions.assertThat(properties26).containsExactly("NULL_ALIAS", "value1", "EMPTY_ALIAS", "value2", "SPACE_ALIAS", "value3", "KEY_ALIAS", "value4");
        Map<String, String> properties27 = new HashMap<>();
        properties27.put("NULL_ALIAS", "value1");
        properties27.put("EMPTY_ALIAS", "value2");
        properties27.put("SPACE_ALIAS", "value3");
        properties27.put("KEY_ALIAS", "value4");
        new BaseConfig(null, ".post", propertyAliases2, null).replacePropertyAliases(properties27);
        Assertions.assertThat(properties27).containsExactly("NULL_ALIAS", "value1", "EMPTY_ALIAS", "value2", "SPACE_ALIAS", "value3", "KEY_ALIAS", "value4");
        Map<String, String> properties28 = new HashMap<>();
        properties28.put("NULL_ALIAS", "value1");
        properties28.put("EMPTY_ALIAS", "value2");
        properties28.put("SPACE_ALIAS", "value3");
        properties28.put("KEY_ALIAS", "value4");
        new BaseConfig("pre.", ".post", propertyAliases2, null).replacePropertyAliases(properties28);
        Assertions.assertThat(properties28).containsExactly("NULL_ALIAS", "value1", "EMPTY_ALIAS", "value2", "SPACE_ALIAS", "value3", "KEY_ALIAS", "value4");
    }

}
