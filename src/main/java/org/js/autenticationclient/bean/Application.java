package org.js.autenticationclient.bean;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


public class Application implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String id;


    private String appName;


    private String description;


    private String access;


    private Timestamp onBoardTime;


    private Boolean isActive;


    private String salt;

    /**
     *
     */
    public Application() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param id
     * @param appName

     * @param description
     * @param access
     * @param onBoardTime

     */
    public Application(String id, String appName, String description, String access,
                       Timestamp onBoardTime) {
        super();
        this.id = id;
        this.appName = appName;
        this.description = description;
        this.access = access;
        this.onBoardTime = onBoardTime;

    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }



    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the access
     */
    public String getAccess() {
        return access;
    }

    /**
     * @param access the access to set
     */
    public void setAccess(String access) {
        this.access = access;
    }

    /**
     * @return the onBoardTime
     */
    public Timestamp getOnBoardTime() {
        return onBoardTime;
    }

    /**
     * @param onBoardTime the onBoardTime to set
     */
    public void setOnBoardTime(Timestamp onBoardTime) {
        this.onBoardTime = onBoardTime;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(appName, that.appName) &&
                Objects.equals(description, that.description) &&
                Objects.equals(access, that.access) &&
                Objects.equals(onBoardTime, that.onBoardTime) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appName, description, access, onBoardTime, isActive, salt);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id='" + id + '\'' +
                ", appName='" + appName + '\'' +
                ", description='" + description + '\'' +
                ", access='" + access + '\'' +
                ", onBoardTime=" + onBoardTime +
                ", isActive=" + isActive +
                ", salt='" + salt + '\'' +
                '}';
    }

    public static class ApplicationBuilder {

        private Application application;

        /**
         *
         */
        public ApplicationBuilder() {
            application = new Application();
        }

        public ApplicationBuilder(Application application) {
            this.application = application;
        }

        public ApplicationBuilder(String id, String appName,  String description, String access) {
            application = new Application(id,appName,description,access, new Timestamp(System.currentTimeMillis()));
        }

        public ApplicationBuilder withOnboardTime() {
            application.setOnBoardTime(new Timestamp(System.currentTimeMillis()));
            return this;
        }

        public ApplicationBuilder withDescription(String value) {
            application.setDescription(value);
            return this;
        }

        public ApplicationBuilder withAppName(String appName) {
            application.setAppName(appName);
            return this;
        }



        public ApplicationBuilder withId(String id) {
            application.setId(id);
            return this;
        }
        public ApplicationBuilder withAccess(String access) {
            application.setAccess(access);
            return this;
        }

        public ApplicationBuilder withSalt(String salt) {
            application.setSalt(salt);
            return this;
        }


        public Application build(){
            return this.application;
        }

        private void validateUserObject(Authentication authentication) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }

        public String buildJson() throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this.application);
        }
    }

}