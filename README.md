# Financial Portfolio Management API

![MIT License](https://img.shields.io/badge/license-MIT-brown)

## Description

The Investment API is a sophisticated Spring Boot-based application tailored for managing and tracking various types
of investments, including stocks and bonds. Designed with the modern investor in mind, it provides a user-friendly
platform to oversee investment portfolios, offering real-time insights and detailed analyses of financial assets. The
API encapsulates a range of functionalities from basic CRUD operations to complex financial calculations, ensuring
versatility and robustness in investment management.

## Architecture

Below is the investment application's architecture diagram, inspired by the Model-View-Controller (MVC) design pattern,
chosen for its well-organized structure that enhances maintenance and scalability.
<br>
<br>

![Architecture Diagram](/images/design-pattern-mvc.PNG)

<br>
<br>
In this design, each component—Controller, Service, Repository, Model, and Utility—has a clear role, enabling
independent development and testing. This modularity is essential for the financial technology sector, ensuring the
application's efficiency and reliability.

## Table of Contents

1. [Key Features](#key-features)
2. [Technology Stack](#technology-stack)
3. [Documentation and Ease of Use](#documentation-and-ease-of-use)
4. [Installation](#installation)
    - [Prerequisites](#prerequisites)
    - [Step-by-Step Installation](#step-by-step-installation)
5. [Running the Application](#running-the-application)
6. [License](#license)
7. [Contributing](#contributing)
8. [Tests](#tests)
9. [Questions](#questions)
10. [Video](#video)

## Key Features:

* **Comprehensive Investment Management**: Users can create, read, update and delete investment dat, allowing full
  control over their investment portfolios.
* **Support for Multiple Investment Types**: Catering to a diverse range of financial assets, the API handles various
  investment types like bonds and stocks, providing tailored functionalities for each.
* **Real-time Portfolio Tracking**: The API offers up-to-date information on investments, giving users a current view of
  their financial standings.
* **Robust Error Handling**: Enhanced with custom exceptions like `InvestmentValidationException`, the API ensures data
  integrity and smooth operation under various scenarios.
* **Advanced Reporting**: Users can export investment data to Excel for offline Analysis, and the API supports JSON
  operations for efficient data interchange.

## Technology Stack

* **Java and Spring Boot**: The API leverages Java's reliability and Spring Boot's efficiency for rapid development and
  deployment.
* **In-Memory Data Management**: Utilises a `HashMap` for simulating database operations and storing data in memory,
  suitable for demonstration and testing purposes.
* **JSON File Handling**: Employs custom utility (`JsonUtil`) for reading and writing to JSON files, managing the
  persistence of investment data.
* **SpringDoc and Swagger**: Used for generating comprehensive and interactive API documentation.
* **JUnit and Mockito**: Incorporated for thorough testing, ensuring the API's reliability and robustness.
* **Git**: Utilised for version control, facilitating tracking of changes, manages code versions, and supporting
  collaborative development.

## Documentation and Ease of Use

The API documentation, generated using SpringDoc and Swagger, is accessible and user-friendly, providing clear insights
into the available endpoints and their usage. This feature is particularly beneficial for developers looking to
integrate or extend the API functionalities. The documentation is available
at [Swagger UI](http://localhost:8111/swagger-ui/index.html#/investment-controller).

1. **Ensure the Application is Running**: Before accessing the Swagger UI, make sure the Financial Portfolio
   ManagementAPI is running on you local machine.
2. **Access Swagger**: Note that the application must be running on port 8111 for this link to work. If you would like
   to use a different port. adjust the URL accordingly in the `application.properties` file located in
   the `src/main/resources` directory of the project.
3. **Change the Server Port**:  Look for a line that says `server.port=8111`. change the number `8111` to your desired
   port number.
4. **Save the File**: Save your changes to the `application.properties` file.
5. **Restart the Application**: After making changes to the port, restart the application for the changes to take
   effect.
6. **Access Swagger UI on New Port**: Once the application is running on the new port, adjust the Swagger UI URL
   accordingly. For example, if you've changed the port to `8080`, the Swagger UI can be accessed
   at http://localhost:8080/swagger-ui/index.html#/investment-controller.

## Target Audience

The Investment API is ideal for fintech applications, financial analysts, portfolio managers, and individual investors
who require a reliable and efficient tool for investment management and analysis.

## Installation

Before you begin, ensure you have the following installed on your system:

* ### Prerequisites

* This application uses the [Java](https://www.java.com/en/) programming language.
  Ensure you have Java 17 LTS (or higher) installed locally. The following commands should confirm your instillation.
* Maven 3.6 or newer
* Git (for cloning the repository)

  ```bash
  $ javac -version

  javac 17.0.4
  ```

  ```bash
  $ java -version

  openjdk version "17.0.4" 2022-07-19 LTS
  OpenJDK Runtime Environment Zulu17.36+13-CA (build 17.0.4+8-LTS)
  OpenJDK 64-Bit Server VM Zulu17.36+13-CA (build 17.0.4+8-LTS, mixed mode, sharing)
  ```

* ### Step-by-Step Installation
* **Clone the repository**:

 ```bash
 git clone git@github.com:cbfacademy/java-rest-api-assessment-jenieb3.git
  ```

* **Navigate to the Project Directory**:

```bash
cd java-rest-api-assessment
```

* **Build the Project**:

 ```bash
 mvn clean install
 ```

## Running the Application:

After installing, you can run the application using the following Maven command.

  ```bash
  mvn spring-boot:run
  ```

This will start the Spring Boot application. Once the application is running you can access the API endpoints as defined
in your swagger documentation.

## License

This project is licensed under the MIT license.

## Contributing

Contributions are welcome. To contribute:

1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Submit a pull request.

* For major changes, please open an issue first to discuss the intended change.

## Tests

Ensure you are in the main project directory, then run the test script:

  ```bash
  mvn test
  ```

## Credits

Alongside the course resources my Tutors, Teaching assistants and Mentors, I found the following websites useful:

1. https://www.baeldung.com/spring-boot
2. https://www.baeldung.com/jackson
3. https://www.baeldung.com/mockito-series
4. https://www.baeldung.com/junit-5
5. https://www.baeldung.com/resttemplate-page-entity-response
6. https://www.baeldung.com/spring-rest-openapi-documentation
7. https://medium.com/@dsforgood/json-to-excel-conversion-using-jackson-and-apache-poi-40c2a9c10a0c
8. https://stackoverflow.com/questions/45421453/java-8-stream-filter-sort-based-pdate
9. https://stackoverflow.com/questions/14515994/convert-json-string-to-pretty-print-json-output-using-jackson
10. https://www.baeldung.com/java-stream-filter-lambda

## Questions

For questions or support, please contact jenelle.garbrah3@gmail.com. To see other projects,
visit https://github.com/jenieb3/.

## Screenshots

**Terminal at the start of the application**. 
<br>
<br>
![Terminal at the start of Application](/images/start-application.PNG)

<br>
<br>


**Swagger documentation of the different endpoints found in this application**.
<br>
<br>
![Swagger Documentation of Endpoints](/images/documentation-api.PNG)

## Video

**Video showing how to run the application and navigate to swagger documentation. From here you can try out the
endpoints and download the data as an Excel file**.

* **Click the link below**.
* **Select the download button**.

[Financial Portfolio Management API](https://drive.google.com/file/d/1EDZnuDv5PPPNFs3YDl6Ic2NOLE02d6WA/view?usp=sharing) 



