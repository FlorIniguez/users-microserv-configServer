🌟 Proyecto de Microservicios con Spring Boot
Este proyecto ilustra la arquitectura de microservicios utilizando Spring Boot, con una API REST de usuarios y una API REST de posts. Los servicios se comunican mediante Feign Client y se gestionan con Eureka para el descubrimiento y el balanceo de carga. La configuración se maneja centralizadamente a través de Config-Server.

🚀 Descripción
Microservicios: Usuarios y Posts
Comunicación: Feign Client
Descubrimiento de Servicios y Balanceo de Carga: Eureka
Configuración Centralizada: Config-Server
Base de Datos: MySQL
Datos de Prueba: MockAPI

🛠️ Tecnologías Utilizadas
Spring Boot: Para construir microservicios.
Eureka: Para descubrimiento de servicios y balanceo de carga.
Config-Server: Para configuración basada en repositorio.
Feign Client: Para comunicación entre microservicios.
MySQL: Base de datos para almacenar usuarios y posts.
MockAPI: Para generar datos de prueba.

🏗️ Construcción y Ejecución
1. Clonar el Repositorio
bash
Copiar código
git clone https://github.com/FlorIniguez/users-microserv-configServer.git
2. Configuración del Entorno
Asegúrate de tener las configuraciones necesarias para Eureka, Config-Server y MySQL.

Ejecutar los Microservicios:
Inicia cada microservicio de forma independiente.


🔗 Enlaces Útiles
Generar Usuarios: MockAPI - Usuarios  [Generar users](https://mockapi.io/clone/6642c1e43c01a059ea2056ef)
