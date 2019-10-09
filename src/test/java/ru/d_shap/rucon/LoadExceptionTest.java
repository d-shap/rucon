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

import java.io.IOException;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link LoadException}.
 *
 * @author Dmitry Shapovalov
 */
public final class LoadExceptionTest {

    /**
     * Test class constructor.
     */
    public LoadExceptionTest() {
        super();
    }

    /**
     * {@link LoadException} class test.
     */
    @Test
    public void getCauseTest() {
        Assertions.assertThat(new LoadException(null)).toCause().isNull();
        Assertions.assertThat(new LoadException(new IOException("message"))).hasCause(IOException.class);
        Assertions.assertThat(new LoadException(new IOException("message"))).hasCauseMessage("message");
        Assertions.assertThat(new LoadException(new IllegalArgumentException("message"))).hasCause(IllegalArgumentException.class);
        Assertions.assertThat(new LoadException(new IllegalArgumentException("message"))).hasCauseMessage("message");
    }

}
