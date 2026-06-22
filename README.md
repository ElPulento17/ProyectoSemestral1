# Sistema de Compra y Venta de Zapatillas



DuocUC Escuela de Informática y Telecomunicaciones FullStack I  


Este sistema backend de la gestión de compra y venta de zapatillas. El sistema permite gestionar usuarios, catálogo de zapatillas, pedidos, pagos, envíos y más, con autenticación JWT y documentación Swagger centralizada.



## Herramientas y Tecnologías

|Herramienta|Versión|Uso|
|-|-|-|
|Java|21||
|Spring Boot|3.4.5|Framework principal|
|Spring Cloud Gateway|2023.0.x|API Gateway|
|Spring Security|No lo se|Autenticación|
|Spring Data JPA|No lo se|Persistencia|
|Maven|3.x|Gestión de dependencias|
|MySQL|8.x|Base de datos|
|XAMPP|No lo se |Servidor MySQL local|
|Lombok|no lo se||
|jjwt|0.11.5|Generación y validación de tokens JWT|
|springdoc-openapi|2.7.0|Documentación Swagger|
|JUnit 5|no lo se|Testing unitario|
|Mockito|no lo se|Mocking en tests|
|Postman|no lo se|Pruebas de endpoints|
|Visual Studio Code|no lo se|IDE de desarrollo|
|Git|no lo se|Control de versiones|
|GitHub|-|Repositorio colaborativo|

## Bibliotecas

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.7.0</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>



<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>




Arquitectura de Microservicios


Cliente / Postman
       ↓
  api-gateway (:8080)
       ↓
  ┌────┴─────────────────────────────────────────────┐
  │                                                   │
:8081          :8082          :8083    :8084    :8085  │
usuarios     zapatillas      pedidos   pagos   envios  │
                                                       │
:8086    :8087        :8088         :8089    :8090  :8091
resenas  descuentos   proveedores   reportes notif. auth


Bases de Datos y Puertos

|Microservicio|Puerto|Base de Datos|
|-|-|-|
|api-gateway|8080|—|
|service-usuarios|8081|db\_usuarios|
|service-zapatillas|8082|db\_zapatillas|
|service-pedidos|8083|db\_pedidos|
|service-pagos|8084|db\_pagos|
|service-envios|8085|db\_envios|
|service-resenas|8086|db\_resenas|
|service-descuentos|8087|db\_descuentos|
|service-proveedores|8088|db\_proveedores|
|service-reportes|8089|db\_reportes|
|service-notificaciones|8090|db\_notificaciones|
|service-auth|8091|db\_seguridad|

## Instalación y Configuración

### Requisitos previos

* Java 21 instalado
* XAMPP con MySQL activo
* Maven instalado

### Pasos para ejecutar

**1.** Clona el repositorio:

bash
git clone <url-del-repositorio>
cd PROYECTO\_ZAPAS



**2.** Enciende XAMPP y asegúrate de que MySQL esté corriendo.



**3.** Las bases de datos se crean automáticamente gracias a:


spring.datasource.url=jdbc:mysql://localhost:3306/db\_nombre?createDatabaseIfNotExist=true
spring.jpa.hibernate.ddl-auto=update


**4.** Levanta cada microservicio en este orden:


### Registrar usuario


POST http://localhost:8080/auth/registrar
Content-Type: application/json

{
    "nombreUsuario": "juanperez",
    "contrasena": "clave123",
    "correo": "juan.perez@zapas.com",
    "roles": \[ { "id": 1 } ]
}


### Iniciar sesión


POST http://localhost:8080/auth/login
Content-Type: application/json

{
    "nombreUsuario": "juanperez",
    "password": "clave123"
}


## Ejemplos de Rutas



### Usuarios

GET    /api/v1/usuario
GET    /api/v1/usuario/{id}
POST   /api/v1/usuario
PUT    /api/v1/usuario/{id}
DELETE /api/v1/usuario/{id}
GET    /api/v1/usuario/tipos
POST   /api/v1/usuario/tipos


### Zapatillas


GET    /api/v1/zapatillas
GET    /api/v1/zapatillas/{id}
POST   /api/v1/zapatillas
PUT    /api/v1/zapatillas/{id}
GET    /api/v1/zapatillas/{id}/stock
POST   /api/v1/zapatillas/{id}/stock
PUT    /api/v1/zapatillas/stock/{stockId}
GET    /api/v1/marcas
POST   /api/v1/marcas


### Pedidos


GET    /api/v1/pedidos
GET    /api/v1/pedidos/{id}
POST   /api/v1/pedidos
PATCH  /api/v1/pedidos/{id}/estado


#### Crear pedido


{
    "usuarioId": 1,
    "detalles": \[
        {
            "stockZapatillaId": 1,
            "cantidad": 1,
            "precioUnitario": 85000,
            "subtotal": 85000
        }
    ]
}


### Pagos


GET    /api/v1/pagos
GET    /api/v1/pagos/{id}
POST   /api/v1/pagos
GET    /api/v1/pagos/pedido/{pedidoId}


#### Crear pago


{
    "pedidoId": 1,
    "monto": 85000,
    "metodo": "webpay",
    "codigoTransaccion": "WP-00123"
}


### Envíos


GET    /api/v1/envios
GET    /api/v1/envios/{id}
POST   /api/v1/envios
GET    /api/v1/envios/pedido/{pedidoId}
```

### Reseñas


GET    /api/v1/resenas
GET    /api/v1/resenas/{id}
POST   /api/v1/resenas
DELETE /api/v1/resenas/{id}
GET    /api/v1/resenas/zapatilla/{zapatillaId}


### Descuentos


GET    /api/v1/descuentos
GET    /api/v1/descuentos/{id}
POST   /api/v1/descuentos
PUT    /api/v1/descuentos/{id}
DELETE /api/v1/descuentos/{id}


### Proveedores


GET    /api/v1/proveedores
GET    /api/v1/proveedores/{id}
POST   /api/v1/proveedores
DELETE /api/v1/proveedores/{id}
POST   /api/v1/proveedores/{id}/marcas
GET    /api/v1/proveedores/{id}/marcas


### Reportes


GET    /api/v1/reportes
POST   /api/v1/reportes/generar


### Notificaciones


GET    /api/v1/notificaciones
GET    /api/v1/notificaciones/{id}
POST   /api/v1/notificaciones
DELETE /api/v1/notificaciones/{id}
GET    /api/v1/notificaciones/usuario/{usuarioId}
PATCH  /api/v1/notificaciones/{id}/leida


## Swagger

http://localhost:8080/swagger-ui.html



|Microservicio|URL Swagger|
|-|-|
|Usuarios|http://localhost:8081/swagger-ui.html|
|Zapatillas|http://localhost:8082/swagger-ui.html|
|Pedidos|http://localhost:8083/swagger-ui.html|
|Pagos|http://localhost:8084/swagger-ui.html|
|Envíos|http://localhost:8085/swagger-ui.html|
|Reseñas|http://localhost:8086/swagger-ui.html|
|Descuentos|http://localhost:8087/swagger-ui.html|
|Proveedores|http://localhost:8088/swagger-ui.html|
|Reportes|http://localhost:8089/swagger-ui.html|
|Notificaciones|http://localhost:8090/swagger-ui.html|
|Auth|http://localhost:8091/swagger-ui.html|



## Integrantes del Equipo

|Nombre|GitHub|
|-|-|
|Amaro Marmaduque Osses Madrid|ElPulento17|
|Benjamin Tapia||
|Carolina Savedra||



