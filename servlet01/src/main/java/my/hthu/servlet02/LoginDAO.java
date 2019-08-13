package my.hthu.servlet02;


import java.io.*;
import java.sql.*;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

@ConfigurationProperties
public class LoginDAO {

    @Autowired
    private Environment env;

    public boolean checkValidate(String username, String password) throws ClassNotFoundException, IOException {

        InputStream inputStream;
        Properties prop = new Properties();
        String propFileName = "application.properties";

        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        String url = prop.getProperty("spring.datasource.url");
        String user = prop.getProperty("spring.datasource.username");
        String pass = prop.getProperty("spring.datasource.password");
        String classPath = prop.getProperty("spring.datasource.driverClassName");
        Class.forName(classPath);
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from User where username = ? and password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
