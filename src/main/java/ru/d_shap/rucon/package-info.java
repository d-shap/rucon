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
 * RuCon is the application configuration helper.
 * </p>
 * <p>
 * Application configuration can be defined in the properties file, as the system properties
 * (specified as JVM -D arguments), as the system environment variables. Configuration can be defined
 * in the deployment descriptors (for example, web.xml, ejb-jar.xml). Configuration can be defined
 * in the framework-specific configuration files (for example, Spring's applicationContext.xml).
 * </p>
 * <p>
 * Also the possibility to override configuration is mandatory.
 * </p>
 * <p>
 * RuCon is intended to deal with this problems.
 * </p>
 * <p>
 * For example, application uses JDBC driver. To connect to the database, some configuration is needed.
 * This configuration is defined in the <i>jdbc.properties</i> file, that is located in the classpath.
 * Application is packaged as jar file and the <i>jdbc.properties</i> file is in this jar.
 * </p>
 * <p>
 * The content of the <i>jdbc.properties</i> file is the following:
 * </p>
 * <pre>{@code
 * # Database name
 * jdbc.database=tempbase
 * # Login info
 * jdbc.user=tempuser
 * jdbc.pass=tempsecret
 * }</pre>
 * <p>
 * <p>
 * The following code reads this configuration:
 * </p>
 * <pre>{@code
 * ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
 * configurationBuilder.addPropertiesResourceLoader("jdbc.properties");
 * Configuration configuration = configurationBuilder.buildAndLoad();
 * String database = configuration.getPropertyAsString("jdbc.database", "undefined");
 * String username = configuration.getPropertyAsString("jdbc.user", "undefined");
 * String password = configuration.getPropertyAsString("jdbc.pass", "undefined");
 * }</pre>
 * <p>
 * This application is deployed to different environments. Database configuration may not be the same
 * in each environment. And to adjust the application configuration to the environment the <i>jdbc.properties</i>
 * in the jar file should be updated.
 * </p>
 */
package ru.d_shap.rucon;
