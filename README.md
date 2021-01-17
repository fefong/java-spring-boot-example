Java SpringBoot Application REST API - CRUD
======================

Getting started with Java using SpringBoot Application - CRUD

 * [Introduction](#introduction)
 * [Database](#database)
 * [SpringBoot](#springboot)
   * [Model](#model)
   * [Repository](#repository)
   * [Service](#service)
   * [Controller](#controller)

## Introduction

- [x] **C** - _CREATE_
- [x] **R** - _READ_
- [x] **U** - _UPDATE_
- [x] **D** - _DELETE_


Spring of modern applications, it is one of the main modern micro-service applications on top of Java, easy to develop and a tool with many possibilities

Stack Development:
 - [x] Java 11 - SpringBoot Application
   - [x] JPA
   - [x] Lombok
 - [x] MySQL Database


## Database

Run database script.

**Directory:** ./assets/script-mysql-database.sql

Script: [Database Structure](assets/script-mysql-database.sql)

## SpringBoot

Create _new project_ Springboot Application.

Open file [**pom.xml**](pom.xml) add the dependencies;

:warning: Open file example in project to see dependencies;

Rename file: **application.properties** to [application.yml](src/main/resources/application.yml)

Add configuration:
 * Server
  ```
  server:
  port: ${SERVER_PORT:9000}
  ```

 * Database
  ```
  spring:
  application:
    name: PROJECT-NAME
  datasource:  
    url: ${DATASOURCE_URL}
    username: ${DATASOURCE_USERNAME}
    password: ${DATASOURCE_USERNAME}
    driver-class-name: ${DRIVER_CLASS_NAME:com.mysql.cj.jdbc.Driver}
    sql-script-encoding: UTF-8
  ```
See example: [application.yml](src/main/resources/application.yml)

Create Project Structure (Default)
 * Folder: .\src\main\java\com\ffong\demo\
   * config
     * Config.java

In the **..\src\main\java\com\ffong\demo\config\Config.java** is possible add configurations for your service. 

Use annotation: ```@Configurarion``` and implements interface: **WebMvcConfigurer**.

**HTTP Methods - REST API**
 - [x] **GET** - list objects or return data/information only
 - [x] **POST** - insert or method executon
 - [x] **PUT** - update existing resource
 - [x] **DELETE** - delete or soft-delete
 - [x] **PATH** - to make partical update on a resource

:warning: Important, descriptions are relative.

Override method **addCorsMappings**
```java
@Override
public void addCorsMappings(CorsRegistry cors) {
    cors.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");
}
```
:information_source: add only methods necessary in your application.

See example: [Config.java](src/main/java/com/ffong/demo/config/Config.java)


Create Project Structure 
 * Folder: .\src\main\java\com\ffong\demo\
   * client
     * controller
     * model
     * repository
     * service

### Model

Create **Model** from object.
 * Folder: .\src\main\java\com\ffong\demo\
   * client
     * model
       * [Client.java](src/main/java/com/ffong/demo/client/model/Client.java)

Add annotations:
 * ```@Entity```
 * ```@Table(name = "TABLE_NAME")```

  ```java
  @AllArgsConstructor
  @NoArgsConstructor
  @Data
  @EqualsAndHashCode(callSuper = false)
  @Entity
  @Table(name = "CLIENT")
  public class Client {
  // TODO
  }
  ```

Import **pom.xml** dependencie **Lombok** add annotations lombook:
 * ```@AllArgsConstructor```
 * ```@NoArgsConstructor```
 * ```@Data```
 * ```@EqualsAndHashCode(callSuper = false)```

Using **Lombok** it will possible Generates an all-args constructor, a no-args constructor and Generates getters for all fields.

 * Add annotation: ```@Id``` in key table.

 * Add annotation: ```@Column(name = "COLUMN_NAME", nullable = false)``` in each field.

See example: [Client Model](src/main/java/com/ffong/demo/client/model/Client.java)

### Repository

Create Interface **Repository** from object.
 * Folder: .\src\main\java\com\ffong\demo\
   * client
     * repository
       * [ClientRepository.java](src/main/java/com/ffong/demo/client/repository/ClientRepository.java)

Add annotations:
 * ```@Repository```
    ```java
    @Repository
    public interface ClientRepository extends JpaRepository<Client, Long> {
    // TODO
    }
     ```

:information_source: If your application used only basic finds not necessary create another's methods.

:warning: ```JpaRepository< MODEL , TYPE OF ID >```

See example: [Client Repository](src/main/java/com/ffong/demo/client/repository/ClientRepository.java)

### Service

Create **Service** from object.
 * Folder: .\src\main\java\com\ffong\demo\
   * client
     * service
       * [ClientService.java](src/main/java/com/ffong/demo/client/repository/../service/ClientService.java)
       * [ClientServiceImpl.java](src/main/java/com/ffong/demo/client/repository/../service/ClientServiceImpl.java)

Add annotations:
 * ```@Service```
    ```java
    @Service
    public class ClientServiceImpl implements ClientService {
    // TODO
    }
     ```
Usually two files are created:
 * Interface
   * The Interface can also be called IClient (I + Client).
 * Implementation Interface
   * Implement the interface above and add annotations.

Create methods in Interface([Client](src/main/java/com/ffong/demo/client/model/Client.java)):
 * List<[Client](src/main/java/com/ffong/demo/client/model/Client.java)> list();
 * [Client](src/main/java/com/ffong/demo/client/model/Client.java) insert([Client](src/main/java/com/ffong/demo/client/model/Client.java) client);
 * [Client](src/main/java/com/ffong/demo/client/model/Client.java) udpate(Long idClient, [Client](src/main/java/com/ffong/demo/client/model/Client.java) client);
 * void delete(Long idClient);

In the Implementation Interface([ClientServiceImpl.java](src/main/java/com/ffong/demo/client/service/ClientServiceImpl.java)):
 * Add methods from interface;
 * Set:
    ```java
    @Autowired
    private ClientRepository clientRepository;
    ```
 * Method: List<[Client](src/main/java/com/ffong/demo/client/model/Client.java)> list():
    ```java
    @Override
	public List<Client> list() {
		List<Client> clients = new ArrayList<Client>();
		clients = clientRepository.findAll();
		return clients;
	}
    ```
* Method: insert([Client](src/main/java/com/ffong/demo/client/model/Client.java) client):
    ```java
	@Override
	public Client insert(Client client) {
		return clientRepository.save(client);
	}
    ```
* Method: udpate(Long idClient, [Client](src/main/java/com/ffong/demo/client/model/Client.java) client):
  ```java
  @Override
  public Client update(Long idClient, Client client) {
    Optional<Client> clientFind = clientRepository.findById(idClient);
    if (clientFind.isPresent()) {
      client.setId(idClient);
      return clientRepository.save(client);
    }
    return null;
  }
  ```
* Method: delete(Long idClient):
  ```java
  @Override
  public void delete(Long idClient) {
    clientRepository.deleteById(idClient);
  }
  ```

See example: [Client Service - Interface](src/main/java/com/ffong/demo/client/service/ClientService.java)

See example: [Client Service - Implementation](src/main/java/com/ffong/demo/client/service/ClientServiceImpl.java)

### Controller

Create **Controller** from object.
 * Folder: ./src/main/java/com/.../.../
   * client
     * controller
       * [ClientController.java](src/main/java/com/ffong/demo/client/controller/ClientController.java)

Add annotations:
 * ```@RestController```


If you want you can configure a default path for all methods on this controller with:
```@RequestMapping("/client")```

```java
@RestController
@RequestMapping("/client")
public class ClientController {
// TODO
}
```

If the ```@RequestMapping``` is not configured it will be necessary to add it to each request.

Example: 
```java
@GetMapping(/client)
@PostMapping(/client) 
@PutMapping("/client/{idClient}")
@DeleteMapping("/client/{idClient}")
```


 * Set:
    ```java
    @Autowired
	private ClientService clientService;
    ```
 * Method: List<[Client](src/main/java/com/ffong/demo/client/model/Client.java)> list():
    ```java
	@GetMapping()
	public List<Client> list() {
		return clientService.list();
	}
    ```
    ```@RequestMapping(method = RequestMethod.GET)```

    ```@GetMapping```: Annotation for mapping HTTP GET.
    * ```@GetMapping()```: Get in path default "/".
    * ```@GetMapping("list")```: Get in path: "/list".

 * Method: [Client](src/main/java/com/ffong/demo/client/model/Client.java) insert(@RequestBody [Client](src/main/java/com/ffong/demo/client/model/Client.java) client):
    ```java
	@PostMapping()
	public Client insert(@RequestBody Client client) {
		return clientService.insert(client);
	}
    ```
    ```@RequestMapping(method = RequestMethod.POST)```

    ```@PostMapping```: Annotation for mapping HTTP POST.
    * ```@PostMapping()```: Get in path default "/".
    * ```@RequestBody Object o```: necessary send body(JSON format);
  
 * Method: [Client](src/main/java/com/ffong/demo/client/model/Client.java) update(@PathVariable Long idClient, @RequestBody [Client](src/main/java/com/ffong/demo/client/model/Client.java) client):
    ```java
	@PutMapping("/{idClient}")
	public Client update(@PathVariable Long idClient, @RequestBody Client client) {
		return clientService.update(idClient, client);
	}
    ```
    ```@RequestMapping(method = RequestMethod.PUT)```

    ```@PutMapping```: Annotation for mapping HTTP PUT.
    * ```@PathVariable Long idClient```: variable in path url add {idClient}.
    * ```@RequestBody Object o```: necessary send body(JSON format);
  

 * Method: void delete(@PathVariable Long idClient):
    ```java
    @DeleteMapping("/{idClient}")
	public void delete(@PathVariable Long idClient) {
		clientService.delete(idClient);
	}
    ```
    ```@RequestMapping(method = RequestMethod.DELETE)```

    ```@DeleteMapping```: Annotation for mapping HTTP DELETE.
    * ```@PathVariable Long idClient```: necessário variable in path add {idClient}.

See example: [Client Controller](src/main/java/com/ffong/demo/client/controller/ClientController.java)
 

## Some links for more in depth learning

* [JAVA](https://github.com/search?q=fefong%2Fjava)
* [JAVA PRINT](https://github.com/fefong/java_print);
* [JAVA SWITCH CASE](https://github.com/fefong/java_switch);
* [JAVA IF/ELSE](https://github.com/fefong/java_ifElse);
