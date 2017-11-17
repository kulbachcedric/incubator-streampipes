package org.streampipes.pe.mixed.flink.samples;

import org.streampipes.config.SpConfig;

public enum FlinkConfig {
  INSTANCE;

  private SpConfig config;
  public static final String JAR_FILE = "./streampipes-pe-mixed-flink.jar";
//	public static final String JAR_FILE = "c:\\git\\semantic-epa-parent\\semantic-epa-flink-samples\\target\\semantic-epa-flink-samples-0.40.3-SNAPSHOT.jar";
//	public static final String JAR_FILE = "/Users/philippzehnder/Coding/fzi/semantic-epa-parent/semantic-epa-flink-samples/target/semantic-epa-flink-samples-0.40.3-SNAPSHOT.jar";

  private final static String HOST = "host";
  private final static String PORT = "port";
  private final static String FLINK_HOST = "flink_host";
  private final static String FLINK_PORT = "flink_port";
  private final static String ELASTIC_HOST = "elasticsearch_host";
  private final static String ELASTIC_PORT = "elasticsearch_port";

  private final static String ICON_HOST = "icon_host";
  private final static String ICON_PORT = "icon_port";

  FlinkConfig() {
    config = SpConfig.getSpConfig("pe/org.streampipes.pe.mixed.flink");

    config.register(HOST, "pe-flink-samples", "Hostname for the pe mixed flink component");
    config.register(PORT, 8090, "Port for the pe mixed flink component");
    config.register(FLINK_HOST, "jobmanager", "Host for the flink cluster");
    config.register(FLINK_PORT, 6123, "Port for the flink cluster");
    config.register(ELASTIC_HOST, "elasticsearch", "Elastic search host address");
    config.register(ELASTIC_PORT, 9300, "Elasitc search port");

    config.register(ICON_HOST, "backend", "Hostname for the icon host");
    config.register(ICON_PORT, 80, "Port for the icons in nginx");
  }

  public String getHost() {
    return config.getString(HOST);
  }

  public int getPort() {
    return config.getInteger(PORT);
  }

  public String getFlinkHost() {
    return config.getString(FLINK_HOST);
  }

  public int getFlinkPort() {
    return config.getInteger(FLINK_PORT);
  }

  public String getElasticsearchHost() {
    return config.getString(ELASTIC_HOST);
  }

  public int getElasticsearchPort() {
    return config.getInteger(ELASTIC_PORT);
  }


  public static final String iconBaseUrl = FlinkConfig.INSTANCE.getIconHost() + ":" + FlinkConfig.INSTANCE.getIconPort() + "/img/pe_icons";

  public static final String getIconUrl(String pictureName) {
    return iconBaseUrl + "/" + pictureName + ".png";
  }

  public String getIconHost() {
    return config.getString(ICON_HOST);
  }

  public int getIconPort() {
    return config.getInteger(ICON_PORT);
  }
}
