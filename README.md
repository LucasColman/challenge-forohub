# Foro Hub - API REST para Gestión de Tópicos

¡Bienvenido a **Foro Hub**! Este proyecto es una API REST desarrollada con **Spring** para replicar el funcionamiento de un foro desde el lado del back end. Inspirado en la dinámica colaborativa de los foros educativos, **Foro Hub** permite gestionar tópicos de discusión, brindando un espacio para preguntas y respuestas entre estudiantes, profesores y moderadores.

---

## 🚀 Características

1. **Gestión de Tópicos**:
   - Crear un nuevo tópico.
   - Listar todos los tópicos creados.
   - Visualizar detalles de un tópico específico.
   - Actualizar información de un tópico.
   - Eliminar un tópico.

2. **Modelo REST**:
   - Rutas implementadas siguiendo las mejores prácticas de diseño RESTful.

3. **Validaciones**:
   - Reglas de negocio para garantizar la consistencia y validez de los datos.

4. **Persistencia de Datos**:
   - Base de datos configurada para almacenar y gestionar información de usuarios, tópicos, cursos y respuestas.

5. **Autenticación y Autorización**:
   - Seguridad implementada para controlar el acceso a la API según los roles de los usuarios.

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA** para acceso y manipulación de la base de datos.
- **Spring Security** para autenticación y autorización.
- **MySQL/PostgreSQL** (configurable) para entornos de producción.
- **Maven** para gestión de dependencias.
- **Flyway** para migracion de la base de datos

---

## 📖 Funcionalidades Detalladas

### Endpoints
- **POST /topicos**: Crear un nuevo tópico.
- **GET /topicos**: Listar todos los tópicos con opciones de paginación y ordenamiento por fecha o nombre.
- **GET /topicos/{id}**: Obtener detalles de un tópico específico.
- **PUT /topicos**: Actualizar un tópico existente.
- **DELETE /topicos/{id}**: Eliminar un tópico.

### Filtros y Ordenamiento
- Filtrar tópicos por el nombre del curso asociado.
- Ordenar resultados por:
  - Fecha de creación.
  - Nombre del tópico.

### Seguridad
- **Autenticación**: Basada en JWT (JSON Web Token).
---

## 📋 Requisitos Previos

- **Java 17** o superior.
- **Maven** instalado.
- **MySQL** u otro sistema de base de datos compatible.
- **Postman** o equivalente (para pruebas de endpoints)

---

## 🚀 Configuración

1. Clona el repositorio:
   ```bash
   git clone https://github.com/LucasColman/challenge-forohub.git
   cd challenge-forohub
   ```
2. Configura las credenciales de la base de datos en application.properties
3.  Ejecuta el proyecto:
 ```bash
mvn spring-boot:run
 ```
---
## 🚀 Mejoras futuras
- Agregar roles y perfiles para los usuarios
- Implementar controladores para las demas clases
- Implementacion de un frontend


