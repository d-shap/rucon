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

import java.util.HashSet;
import java.util.Set;

/**
 * Base configuration loader class.
 *
 * @author Dmitry Shapovalov
 */
public abstract class AbstractConfigLoader implements ConfigLoader {

    private final Set<String> _excludeProperties;

    /**
     * Create new object.
     *
     * @param excludeProperties the properties to exclude.
     */
    protected AbstractConfigLoader(final Set<String> excludeProperties) {
        super();
        _excludeProperties = new HashSet<>();
        if (excludeProperties != null) {
            _excludeProperties.addAll(excludeProperties);
        }
    }

    /**
     * Get the properties to exclude.
     *
     * @return the properties to exclude.
     */
    protected final Set<String> getExcludeProperties() {
        return _excludeProperties;
    }

}