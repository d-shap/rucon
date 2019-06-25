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

/**
 * Configuration interface.
 *
 * @author Dmitry Shapovalov
 */
public interface Configuration {

    /**
     * Get the configuration property as String.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    String getPropertyAsString(String name, String defaultValue);

    /**
     * Get the configuration property as boolean.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    boolean getPropertyAsBoolean(String name, boolean defaultValue);

    /**
     * Get the configuration property as int.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    int getPropertyAsInt(String name, int defaultValue);

    /**
     * Get the configuration property as long.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    long getPropertyAsLong(String name, long defaultValue);

    /**
     * Get the configuration property as float.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    float getPropertyAsFloat(String name, float defaultValue);

    /**
     * Get the configuration property as double.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    double getPropertyAsDouble(String name, double defaultValue);

    /**
     * Get the configuration property as char.
     *
     * @param name         the property name.
     * @param defaultValue the default property value.
     *
     * @return the property value.
     */
    char getPropertyAsChar(String name, char defaultValue);

}
