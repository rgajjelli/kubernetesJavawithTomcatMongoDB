FROM tomcat:8.5-alpine
MAINTAINER raj.gajjelli@gmail.com
VOLUME /tmp

COPY target/kuberneteswithtomcatmongodb*.war /usr/local/tomcat/webapps/app.war
RUN sh -c 'touch /usr/local/tomcat/webapps/app.war'
ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/tomcat/webapps/app.war" ]
