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
 * Application is packaged as jar and the <i>jdbc.properties</i> file is in this jar.
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
 *
 * String database = configuration.getPropertyAsString("jdbc.database", "undefined");
 * // database == "tempbase"
 * String username = configuration.getPropertyAsString("jdbc.user", "undefined");
 * // username == "tempuser"
 * String password = configuration.getPropertyAsString("jdbc.pass", "undefined");
 * // password == "tempsecret"
 * }</pre>
 * <p>
 * This application is deployed to different environments. Database configuration may not be the same
 * in each environment. And to adjust the application configuration to the environment the
 * <i>jdbc.properties</i> file in the jar should be updated.
 * </p>
 * <p>
 * To avoid this, configuration can be defined in the properties file, located somewhere in the environment.
 * If this file exists, then configuration is obtained from this file. If this file does not exist, then
 * configuration is obtained from the <i>jdbc.properties</i> file.
 * </p>
 * <p>
 * The content of the <i>/somepath/externalJdbc.properties</i> file
 * </p>
 * <pre>{@code
 * # Login info
 * jdbc.user=realuser
 * jdbc.pass=realsecret
 * }</pre>
 * <p>
 * The path to this file is specified as the JVM argument <b>-Djdbc.config.file.location=/somepath/externalJdbc.properties</b>.
 * </p>
 * <p>
 * The following code reads configuration with the external file:
 * </p>
 * <pre>{@code
 * ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
 * configurationBuilder.addPropertiesSystemPropertyFileLoader("jdbc.config.file.location");
 * configurationBuilder.addPropertiesResourceLoader("jdbc.properties");
 * Configuration configuration = configurationBuilder.buildAndLoad();
 *
 * String database = configuration.getPropertyAsString("jdbc.database", "undefined");
 * // database == "tempbase"
 * String username = configuration.getPropertyAsString("jdbc.user", "undefined");
 * // username == "realuser"
 * String password = configuration.getPropertyAsString("jdbc.pass", "undefined");
 * // password == "realsecret"
 * }</pre>
 * <p>
 * Also this configuration can be adjusted with the JVM arguments. The database name can be specified
 * as the JVM argument <b>-Dorg.mycompany.myproject.jdbc.database=realbase</b>
 * </p>
 * <p>
 * The following code reads configuration with the JVM arguments:
 * </p>
 * <pre>{@code
 * ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
 * configurationBuilder.addSystemPropertiesLoader("org.mycompany.myproject.");
 * configurationBuilder.addPropertiesSystemPropertyFileLoader("jdbc.config.file.location");
 * configurationBuilder.addPropertiesResourceLoader("jdbc.properties");
 * Configuration configuration = configurationBuilder.buildAndLoad();
 *
 * String database = configuration.getPropertyAsString("jdbc.database", "undefined");
 * // database == "realbase"
 * String username = configuration.getPropertyAsString("jdbc.user", "undefined");
 * // username == "realuser"
 * String password = configuration.getPropertyAsString("jdbc.pass", "undefined");
 * // password == "realsecret"
 * }</pre>
 * <p>
 * This final code looks first looks for the configuration property in the JVM arguments that starts
 * with <b>org.mycompany.myproject.</b>. If the configuration property is not defined, then the code
 * looks for the configuration property in the <i>/somepath/externalJdbc.properties</i> external file.
 * Finally, if the configuration property is not defined in the external file, then the code looks for the
 * configuration property in the <i>jdbc.properties</i> file in the classpath.
 * </p>
 */
package ru.d_shap.rucon;
