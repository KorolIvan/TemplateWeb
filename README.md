#Automation scripts template for web

##technology:
1) java jdk 1.8.16x
2) maven
3) testNG, JUnit
4) selenium
5) allure

##Set up allure for test report on compute :

####Manual installation


1 Download the latest version as zip archive from (https://bintray.com/qameta/generic/allure2).

2 Unpack the archive to allure-commandline directory.

3 Navigate to bin directory.

4 Use allure.bat for Windows or allure for other Unix platforms.

5 Add allure to system PATH.




##command for console:
 
####_run tests:_

```
- mvn clean test
```

####_run specific test class(es) or method(s) for JUnit framework:_
```
1. full package : mvn -Dtest="com.oracle.tests.**" test
2. all method in a class : mvn -Dtest=CLASS_NAME1 test
3. single method from single class :mvn -Dtest=CLASS_NAME1#METHOD_NAME1 test
4. multiple method from multiple class : mvn -Dtest=CLASS_NAME1#METHOD_NAME1,CLASS_NAME2#METHOD_NAME2 test
```
####_run specific test class(es) or method(s) for TestNG framework:_
```$xslt
inside pom.xml file => <build> section => <maven-surefire-plugin> => <configuration>  need add next :
<suiteXmlFiles>
    <suiteXmlFile>${basedir}/path to *.xml file/fileName.xml</suiteXmlFile>
</suiteXmlFiles>
```
after this mvn test command will run all tests inside selected xml file
####_build result report:_
```
- mvn site 
```  
(after this command you will be able to see the result as a site, which located {project dir}\target\site\allure-maven-plugin\index.html)
                     
##some more info about allure:
https://docs.qameta.io/allure/#_copyright                