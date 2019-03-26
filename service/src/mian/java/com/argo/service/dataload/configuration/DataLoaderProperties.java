package com.argo.service.dataload.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dataloader")
public class DataLoaderProperties {

    /**
     * Data source of region publish service
     */
    private final DataSource dataSource = new DataSource();
    public DataSource getDataSource() { return dataSource; }

    private final MQ mq = new MQ();
    public MQ getMq() { return mq;}

    public static class DataSource{
        private String url = "127.0.0.1";
        private String username = "root";
        private String password = "123456";
        private String databaseName = "core-master";

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDatabaseName() {
            return databaseName;
        }

        public void setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
        }
    }

    public static class MQ {
        private int maxThreads = 10;
        private int maxCount = 50;

        public int getMaxThreads() {
            return maxThreads;
        }

        public void setMaxThreads(int maxThreads) {
            this.maxThreads = maxThreads;
        }

        public int getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(int maxCount) {
            this.maxCount = maxCount;
        }
    }

}
