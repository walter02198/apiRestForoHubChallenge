# ForoHub API

API REST para un foro de discusión donde los usuarios pueden crear tópicos, responderlos y marcarlos como resueltos. Implementa autenticación JWT y documentación OpenAPI.

## 🛠️ Tecnologías

- Java 17
- Spring Boot 3.3.1
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Flyway (migraciones)
- OpenAPI/Swagger (springdoc)
- Lombok
- Maven

## 📋 Requisitos

- Java 17 o superior
- Maven 3.x
- MySQL 8.x

## 🚀 Cómo ejecutar

```bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/apiRestForoHubChallenge.git
cd apiRestForoHubChallenge

# Configurar variables de entorno
# Windows PowerShell:
$env:JWT_secret="tu_secreto_aqui"

# Linux/Mac:
export JWT_secret="tu_secreto_aqui"

# Ejecutar la aplicación
./mvnw spring-boot:run

# O compilar y ejecutar
./mvnw clean package
java -jar target/apiRest-0.0.1-SNAPSHOT.jar
```

## 📌 Endpoints

### Autenticación

| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| POST | `/login` | Iniciar sesión y obtener token JWT | No |

### Tópicos

| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | `/topicos` | Listar tópicos activos (paginado) | Sí |
| POST | `/topicos` | Crear nuevo tópico | Sí |
| PUT | `/topicos` | Actualizar tópico existente | Sí |
| DELETE | `/topicos/{id}` | Desactivar tópico (eliminación lógica) | Sí |

### Documentación

| Ruta | Descripción |
|------|-------------|
| `/swagger-ui.html` | Interfaz Swagger UI |
| `/v3/api-docs` | Especificación OpenAPI (JSON) |

## 🔐 Autenticación

1. Registrar usuario en la base de datos
2. Hacer POST a `/login` con email y contraseña
3. Obtener token JWT de la respuesta
4. Incluir header `Authorization: Bearer <token>` en todas las peticiones protegidas

### Ejemplo de login

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{"email": "usuario@ejemplo.com", "contrasena": "contrasena123"}'
```

### Ejemplo de crear tópico

```bash
curl -X POST http://localhost:8080/topicos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <tu_token>" \
  -d '{
    "titulo": "Mi pregunta sobre Java",
    "mensaje": "¿Cómo usar streams en Java?",
    "autor": "Usuario Ejemplo",
    "curso": "BACKEND"
  }'
```

## 📁 Estructura del proyecto

```
src/
├── main/
│   ├── java/foro/hub/apiRest/
│   │   ├── controller/         # Controladores REST
│   │   │   ├── TopicoController.java
│   │   │   └── AutenticacionController.java
│   │   ├── domain/
│   │   │   ├── topicos/       # Entidad y DTOs de tópicos
│   │   │   ├── usuarios/      # Entidad y DTOs de usuarios
│   │   │   └── respuesta/     # Entidad de respuestas
│   │   ├── infra/
│   │   │   ├── errores/       # Manejo de excepciones
│   │   │   ├── security/      # JWT y configuración de seguridad
│   │   │   └── springdoc/     # Configuración de Swagger
│   │   └── ApiRestApplication.java
│   └── resources/
│       ├── application.properties
│       └── db.migration/       # Migraciones Flyway
└── test/
    └── java/foro/hub/apiRest/  # Pruebas
```

## 🎯 Sobre el challenge

Proyecto desarrollado como parte del programa **Oracle Next Education (ONE)** - Alura Latam.

**Objetivo:** Construir una API REST completa para un foro de discusión con:
- CRUD de tópicos y respuestas
- Autenticación y autorización con JWT
- Paginación de resultados
- Documentación con OpenAPI
- Migraciones de base de datos con Flyway
- Eliminación lógica (soft delete)

## Autor

Walter Valverde
