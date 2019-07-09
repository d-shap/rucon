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
/**
 * <p>
 * Configuration delegate implementation classes.
 * </p>
 * <p>
 * {@link ru.d_shap.rucon.ConfigDelegate} instances are used to read the configuration from the different sources.
 * Several {@link ru.d_shap.rucon.ConfigDelegate} instances together can be used to union the configuration properties
 * from the different sources and to override the configuration properties.
 * </p>
 * <p>
 * If the {@link ru.d_shap.rucon.ConfigDelegate} instance implements the {@link ru.d_shap.rucon.ConfigLoader}
 * interface, then changes in the configuration source does NOT reflect the {@link ru.d_shap.rucon.ConfigDelegate} instance.
 * </p>
 */
package ru.d_shap.rucon.delegate;
