# Proyecto - Servicio Rest - CRUD

---


El proyecto consiste en generar una API-REST que exponga un servicio CRUD(Create, Read, Update, Delete) de personas mayores de 18 años

## Contenido

- [Tecnologías y herramientas](#install)
- [Instalacion](#instalacion)
- [Api](#api)
- [Local](#local)
- [Consideraciones](#consideraciones)


# Tecnologías y herramientas

* [Java17] - Lenguaje de programación
* [Git] - Versionado
* [Maven] - Paquetización y dependencias
* [Spring-boot] - Framework
* [MySql] - Base de datos
* [GitHub] - Repositorio y manual de uso


-------

# Instalación

- Básicamente se necesita tener esta tecnología instalada en el server.

| Requiere       |                                                 |
|----------------|-------------------------------------------------|
| Java17         | https://www.java.com/es/download/               |
| Git            | https://git-scm.com/downloads                   |
| Maven          | https://maven.apache.org                        |
| Docker Desktop | https://www.docker.com/products/docker-desktop/ |


Luego en el espacio de trabajo o workspace clonar el proyecto:

$ git clone https://github.com/CharleBone/crud-rest.git

------

# Archivo Postman: 

------
# Uso de Doker

Para usar el servicio en Docker, insertarmos esta linea en la terminal  "docker-compose up -d"
Se creara la imagen y los contenedores, se podra utilizar el arhivo postman para la prueba

------

------
# Local

Para usar el servicio de forma local se debe crear una base de datos en el puerto 3306 con el nombre  "db_abm_rest"
y en el archivo application.properties se debe comentar las lineas que especifica el archivo para usar en forma local
o en docker
------