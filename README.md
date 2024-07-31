# SubwayScreen
How To Run Application:
1. Download the advertisement.sql file provided in the src directory of the application
2. Make sure MySQL is installed and configured on your laptop
3. Navigate to the Program Files directory in your laptop's file explorer using command prompt
4. Navigate into your MySQL server's bin directory
5. Run the command "mysql -u root -p" and enter the password you set when configuring MySQL as prompted
6. Enter the command "CREATE DATABASE advertisement;"
7. Enter the command "CREATE USER 'Mahdi'@'3306' IDENTIFIED BY 'ensf380';"
8. Enter the command "GRANT ALL PRIVILEGES ON advertisement.* TO 'Mahdi'@'3306';"
9. Enter the command "EXIT;"
10. Run the command "mysql -u Mahdi -p advertisement < c:/path/to/advertisement.sql and enter the password 'ensf380' when asked (NOTE: make sure to replace c:/path/to/advertisement.sql with the actual path to the location where you have saved the advertisement.sql file)

