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
    }

}
