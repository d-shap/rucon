///////////////////////////////////////////////////////////////////////////////////////////////////
// Application run configuration helper library.
// Copyright (C) 2019 Dmitry Shapovalov.
//
// This file is part of  Application run configuration helper.
//
//  Application run configuration helper is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
//  Application run configuration helper is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.rucon;

/**
 * Configuration loader interface.
 *
 * @author Dmitry Shapovalov
 */
public interface ConfigurationLoader {

    void load();

    String getProperty(String name);

}
