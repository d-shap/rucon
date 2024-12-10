# RuCon
RuCon is the application configuration helper.

Application configuration can be defined in the properties file, as the system properties (specified as JVM -D arguments), as the system environment variables.
Configuration can be defined in the deployment descriptors (for example, web.xml, ejb-jar.xml).
Configuration can be defined in the framework-specific configuration files (for example, Spring's applicationContext.xml).

Also, the possibility to override configuration is mandatory.

RuCon is intended to deal with these problems.

For example, application uses JDBC driver.
To connect to the database, some configuration is needed.
This configuration is defined in the *jdbc.properties* file, that is located in the classpath.
Application is packaged as jar, and the *jdbc.properties* file is in this jar.

The content of the *jdbc.properties* file is the following:
```
# Database name
jdbc.database=tempbase
# Login info
jdbc.user=tempuser
jdbc.pass=tempsecret
```

The following code reads this configuration:
```
ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
configurationBuilder.addPropertiesResourceLoader("jdbc.properties");
Configuration configuration = configurationBuilder.buildAndLoad();

String database = configuration.getPropertyAsString("jdbc.database", "undefined");
// database == "tempbase"
String username = configuration.getPropertyAsString("jdbc.user", "undefined");
// username == "tempuser"
String password = configuration.getPropertyAsString("jdbc.pass", "undefined");
// password == "tempsecret"
```

This application is deployed to different environments.
Database configuration may not be the same in each environment.
And to adjust the application configuration to the environment the *jdbc.properties* file in the jar should be updated.

To avoid this, configuration can be defined in the properties file, located somewhere in the environment.
If this file exists, then configuration is obtained from this file.
If this file does not exist, then configuration is obtained from the *jdbc.properties* file.

The content of the */somepath/externalJdbc.properties* file:
```
# Login info
jdbc.user=realuser
jdbc.pass=realsecret
```

The path to this file is specified as the JVM argument **-Djdbc.config.file.location=/somepath/externalJdbc.properties**.

The following code reads configuration with the external file:
```
ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
configurationBuilder.addPropertiesSystemPropertyFileLoader("jdbc.config.file.location");
configurationBuilder.addPropertiesResourceLoader("jdbc.properties");
Configuration configuration = configurationBuilder.buildAndLoad();

String database = configuration.getPropertyAsString("jdbc.database", "undefined");
// database == "tempbase"
String username = configuration.getPropertyAsString("jdbc.user", "undefined");
// username == "realuser"
String password = configuration.getPropertyAsString("jdbc.pass", "undefined");
// password == "realsecret"
```

Also, this configuration can be adjusted with the JVM arguments.
The database name can be specified as the JVM argument **-Dorg.mycompany.myproject.jdbc.database=realbase**

The following code reads configuration with the JVM arguments:
```
ConfigurationBuilder configurationBuilder = ConfigurationBuilder.newInstance();
configurationBuilder.addSystemPropertiesLoader("org.mycompany.myproject.");
configurationBuilder.addPropertiesSystemPropertyFileLoader("jdbc.config.file.location");
configurationBuilder.addPropertiesResourceLoader("jdbc.properties");
Configuration configuration = configurationBuilder.buildAndLoad();

String database = configuration.getPropertyAsString("jdbc.database", "undefined");
// database == "realbase"
String username = configuration.getPropertyAsString("jdbc.user", "undefined");
// username == "realuser"
String password = configuration.getPropertyAsString("jdbc.pass", "undefined");
// password == "realsecret"
```

This final code looks first looks for the configuration property in the JVM arguments that starts with **org.mycompany.myproject.**.
If the configuration property is not defined, then the code looks for the configuration property in the */somepath/externalJdbc.properties* external file.
Finally, if the configuration property is not defined in the external file, then the code looks for the configuration property in the *jdbc.properties* file in the classpath.
