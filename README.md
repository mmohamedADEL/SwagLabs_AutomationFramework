# 🕸️ Selenium Automation Framework for SwagLabs

---

## 🚀 Overview
Welcome to the **Selenium-based automation framework** for the **SwagLabs** application!  
This framework uses **Selenium WebDriver with Java** to deliver robust UI test automation, integrated with key tools and features:

- **Maven** for dependency & build management  
- **Jenkins (CI/CD)** for automated testing pipelines  
- **Allure** for insightful, attractive test reports  
- **Log4j** for detailed logging  
- **JSON** files for clean, isolated test data  

---

## 🛠 Prerequisites
Make sure you have the following installed before running the tests:

- [Java JDK 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)  
- [Maven](https://maven.apache.org/install.html)  
- [Git](https://git-scm.com/downloads)  
- Browser drivers (e.g. ChromeDriver for Chrome) — ensure the driver is compatible with your browser version  
- Internet connection to download dependencies  

---

## 📥 How to Install & Setup

1. **Clone the repository**  
```bash
git clone https://github.com/mmohamedADEL/SwagLabs_AutomationFramework.git
cd SwagLabs_AutomationFramework

## 📥 How to Install & Setup

1. **Clone the repository**  
```bash
git clone https://github.com/mmohamedADEL/Playwright-Automation-SwagLabs.git
cd Playwright-Automation-SwagLabs
```
2. **Install dependencies**  
Run Maven to download all dependencies and build the project:  
```bash
mvn clean install
```
3. **Run tests**
   
Execute tests using Maven:
```bash
mvn clean install
```
4. **Generate allure report**
```bash
mvn allure:serve
```
---

## 🗂 Project Structure

```
├───main
│   ├───java
│   │   ├───factory
│   │   │       PageFactory.java
│   │   │
│   │   ├───pages
│   │   │       CartPage.java
│   │   │       CheckoutPage.java
│   │   │       FinishPage.java
│   │   │       HomePage.java
│   │   │       LoginPage.java
│   │   │       OverviewPage.java
│   │   │
│   │   └───utilities
│   │           DataUtility.java
│   │           LogUtility.java
│   │           Utility.java
│   │
│   └───resources
│           Allure.properties
│           Log4j2.properties
│
└───test
    ├───java
    │   ├───Listeners
    │   │       InvokedMethodListenersClass.java
    │   │       ITestListenerClass.java
    │   │
    │   └───Tests
    │           OverviewTest.java
    │
    └───resources
        └───TestData
                CheckOutData.json
                confirmation_msg.json
                Environment.properties
                error_msg.json
                LoginData.json
```
## ✨ Key Features
- 🕷️ **Selenium WebDriver** for flexible and reliable browser automation  
- 📦 **Maven** for dependency and build management  
- 🤖 **Jenkins** CI/CD for continuous integration & testing  
- 📊 **Allure** for visually rich test reports  
- 📝 **Log4j** for detailed and configurable logging  
- 📂 **JSON test data** for easy maintenance and scalability  
- 🎧 **TestNG Listeners** for advanced test lifecycle control

  ## 📌 Notes
- This framework is focused on Selenium WebDriver implementation.  
- For Playwright version, visit the other repository:  
  [SwagLabs Playwright Automation Framework](https://github.com/mmohamedADEL/Playwright-Automation-SwagLabs)  
- Both frameworks share a **Maven** base and utilize **similar data management strategies** for consistency.


---

## 👤 Author
**Mohamed Adel**  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Mohamed%20Adel-blue?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/mohamed-adel-elbaz-79239a272/)

---


