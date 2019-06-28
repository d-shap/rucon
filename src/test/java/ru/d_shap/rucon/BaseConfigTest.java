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
        Assertions.assertThat(new BaseConfig(null, null, null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, null, null, null).getPropertyName("")).isEqualTo("");
        Assertions.assertThat(new BaseConfig(null, null, null, null).getPropertyName(" ")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig(null, null, null, null).getPropertyName("name")).isEqualTo("name");

        Assertions.assertThat(new BaseConfig("", null, null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig("", null, null, null).getPropertyName("")).isEqualTo("");
        Assertions.assertThat(new BaseConfig("", null, null, null).getPropertyName(" ")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig("", null, null, null).getPropertyName("name")).isEqualTo("name");

        Assertions.assertThat(new BaseConfig(" ", null, null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(" ", null, null, null).getPropertyName("")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig(" ", null, null, null).getPropertyName(" ")).isEqualTo("  ");
        Assertions.assertThat(new BaseConfig(" ", null, null, null).getPropertyName("name")).isEqualTo(" name");

        Assertions.assertThat(new BaseConfig("pre.", null, null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig("pre.", null, null, null).getPropertyName("")).isEqualTo("pre.");
        Assertions.assertThat(new BaseConfig("pre.", null, null, null).getPropertyName(" ")).isEqualTo("pre. ");
        Assertions.assertThat(new BaseConfig("pre.", null, null, null).getPropertyName("name")).isEqualTo("pre.name");

        Assertions.assertThat(new BaseConfig(null, "", null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, "", null, null).getPropertyName("")).isEqualTo("");
        Assertions.assertThat(new BaseConfig(null, "", null, null).getPropertyName(" ")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig(null, "", null, null).getPropertyName("name")).isEqualTo("name");

        Assertions.assertThat(new BaseConfig(null, " ", null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, " ", null, null).getPropertyName("")).isEqualTo(" ");
        Assertions.assertThat(new BaseConfig(null, " ", null, null).getPropertyName(" ")).isEqualTo("  ");
        Assertions.assertThat(new BaseConfig(null, " ", null, null).getPropertyName("name")).isEqualTo("name ");

        Assertions.assertThat(new BaseConfig(null, ".post", null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig(null, ".post", null, null).getPropertyName("")).isEqualTo(".post");
        Assertions.assertThat(new BaseConfig(null, ".post", null, null).getPropertyName(" ")).isEqualTo(" .post");
        Assertions.assertThat(new BaseConfig(null, ".post", null, null).getPropertyName("name")).isEqualTo("name.post");

        Assertions.assertThat(new BaseConfig("pre.", ".post", null, null).getPropertyName(null)).isNull();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, null).getPropertyName("")).isEqualTo("pre..post");
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, null).getPropertyName(" ")).isEqualTo("pre. .post");
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, null).getPropertyName("name")).isEqualTo("pre.name.post");
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
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties1).isExcludeProperty("name")).isFalse();

        Set<String> excludeProperties2 = new HashSet<>();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties2).isExcludeProperty(null)).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties2).isExcludeProperty("")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties2).isExcludeProperty(" ")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties2).isExcludeProperty("name")).isFalse();

        Set<String> excludeProperties3 = new HashSet<>();
        excludeProperties3.add("name");
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties3).isExcludeProperty(null)).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties3).isExcludeProperty("")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties3).isExcludeProperty(" ")).isFalse();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties3).isExcludeProperty("name")).isTrue();

        Set<String> excludeProperties4 = new HashSet<>();
        excludeProperties4.add(null);
        excludeProperties4.add("");
        excludeProperties4.add(" ");
        excludeProperties4.add("name");
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties4).isExcludeProperty(null)).isTrue();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties4).isExcludeProperty("")).isTrue();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties4).isExcludeProperty(" ")).isTrue();
        Assertions.assertThat(new BaseConfig(null, null, null, excludeProperties4).isExcludeProperty("name")).isTrue();

        Set<String> excludeProperties5 = new HashSet<>();
        excludeProperties5.add("name");
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties5).isExcludeProperty(null)).isFalse();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties5).isExcludeProperty("")).isFalse();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties5).isExcludeProperty(" ")).isFalse();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties5).isExcludeProperty("name")).isTrue();

        Set<String> excludeProperties6 = new HashSet<>();
        excludeProperties6.add(null);
        excludeProperties6.add("");
        excludeProperties6.add(" ");
        excludeProperties6.add("name");
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties6).isExcludeProperty(null)).isTrue();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties6).isExcludeProperty("")).isTrue();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties6).isExcludeProperty(" ")).isTrue();
        Assertions.assertThat(new BaseConfig("pre.", ".post", null, excludeProperties6).isExcludeProperty("name")).isTrue();
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
