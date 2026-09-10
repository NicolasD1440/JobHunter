# JobHunter

Backend desarrollado con Java y Spring Boot para la gestión, búsqueda, normalización, filtrado y evaluación de ofertas laborales de desarrollo de software.

JobHunter busca centralizar ofertas de empleo y compararlas con el perfil profesional del usuario, utilizando criterios como habilidades, formación académica y experiencia.

> **Versión actual: V1.0**

---

## 🎯 Objetivo

JobHunter nace como un proyecto práctico para construir una herramienta capaz de:

* Buscar ofertas laborales desde diferentes fuentes.
* Recibir y trabajar con información de ofertas en diferentes formatos.
* Normalizar la información obtenida.
* Almacenar las ofertas en una base de datos.
* Registrar el perfil profesional del usuario.
* Comparar las ofertas con el perfil.
* Calcular un porcentaje de coincidencia.
* Filtrar ofertas según diferentes criterios.
* Exponer toda esta funcionalidad mediante una API REST.

El proyecto también funciona como una aplicación práctica para implementar conceptos de desarrollo backend utilizando **Java, Spring Boot, JPA, PostgreSQL y Docker**.

---

## 🚀 Características de V1.0

### Gestión de ofertas

JobHunter permite realizar operaciones CRUD sobre las ofertas laborales:

* Crear una oferta.
* Consultar todas las ofertas.
* Consultar una oferta por ID.
* Actualizar una oferta.
* Eliminar una oferta.

Las ofertas se almacenan en PostgreSQL mediante Spring Data JPA.

### Perfil profesional

El sistema permite crear y administrar un perfil profesional con información como:

* Nombre.
* Teléfono.
* Correo electrónico.
* Descripción.
* Formación académica.
* Años de experiencia.
* Habilidades.

Las habilidades y la formación académica se almacenan mediante colecciones persistidas con JPA.

### Búsqueda de ofertas

JobHunter cuenta con una estructura preparada para obtener ofertas desde fuentes externas.

La información obtenida inicialmente se representa mediante `RawJob`, antes de transformarse al modelo utilizado por la aplicación.

### Normalización

Las ofertas obtenidas desde fuentes externas pueden presentar información inconsistente.

Para solucionar esto, JobHunter utiliza un proceso de normalización:

```text
RawJob
   ↓
JobNormalizer
   ↓
Job
   ↓
PostgreSQL
```

Durante este proceso se extraen y normalizan datos como:

* Salario.
* Años de experiencia.
* Habilidades requeridas.
* Formación académica.
* Información general de la oferta.

### Matching de ofertas

JobHunter compara el perfil profesional con cada oferta mediante un sistema de puntuación.

La puntuación de coincidencia utiliza:

| Criterio            | Peso |
| ------------------- | ---: |
| Habilidades         |  50% |
| Formación académica |  20% |
| Experiencia         |  30% |

El resultado es un porcentaje de coincidencia entre el perfil y la oferta.

### Filtrado de ofertas

Además del porcentaje de coincidencia, las ofertas pueden filtrarse utilizando diferentes criterios:

* Modalidad de trabajo.
* Rango salarial.
* Ubicación.
* Años de experiencia.
* Habilidades requeridas.
* Porcentaje mínimo de coincidencia.

Los filtros pueden combinarse para obtener únicamente las ofertas que cumplen los criterios establecidos.

### Manejo de errores

La aplicación cuenta con un manejo global de excepciones mediante `@RestControllerAdvice`.

Se manejan, entre otros:

* Recursos no encontrados (`404`).
* Errores de validación (`400`).
* Errores relacionados con scraping (`502`).
* Errores inesperados (`500`).

Las respuestas de error utilizan una estructura común:

```json
{
  "status": 404,
  "path": "/api/jobs/999",
  "message": "Job no encontrado",
  "timestamp": "2026-09-10T17:00:00"
}
```

---

## 🏗️ Arquitectura

El backend está organizado siguiendo una arquitectura basada en capas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

También existen componentes especializados para diferentes responsabilidades:

```text
                    ┌──────────────┐
                    │  Controller  │
                    └──────┬───────┘
                           ↓
                    ┌──────────────┐
                    │   Service    │
                    └──────┬───────┘
                           ↓
              ┌────────────┴────────────┐
              ↓                         ↓
       ┌──────────────┐          ┌──────────────┐
       │  Repository  │          │   Matcher    │
       └──────┬───────┘          └──────────────┘
              ↓
       ┌──────────────┐
       │  PostgreSQL  │
       └──────────────┘
```

El proyecto utiliza el modelo de desarrollo web de **Spring MVC**, mediante controladores REST, servicios y repositorios.

### Principales componentes

#### Controller

Recibe las peticiones HTTP y expone los endpoints REST.

Ejemplos:

* `JobController`
* `ProfileController`

#### Service

Contiene la lógica de negocio de la aplicación.

Ejemplos:

* `JobService`
* `ProfileService`

#### Repository

Se encarga del acceso a los datos mediante Spring Data JPA.

Ejemplos:

* `JobRepository`
* `ProfileRepository`

#### DTO

Los DTO permiten definir los datos que recibe o devuelve la API sin depender directamente de las entidades.

Ejemplos:

* `CreateJobDTO`
* `CreateProfileDTO`
* `JobMatchDTO`
* `JobSearchRequest`
* `MatchFilterDTO`

#### Model

Contiene las entidades principales del sistema:

* `Job`
* `Profile`
* `RawJob`

#### Matcher

`JobMatcher` calcula la coincidencia entre el perfil profesional y una oferta laboral.

#### Filter

`JobFilter` determina si una oferta cumple los criterios establecidos por el usuario.

#### Scraper

Los servicios de scraping permiten obtener información desde fuentes externas.

Actualmente existe integración para obtener ofertas desde **El Empleo**.

---

## 🛠️ Tecnologías

| Tecnología      | Uso                      |
| --------------- | ------------------------ |
| Java 21         | Lenguaje principal       |
| Spring Boot     | Framework backend        |
| Spring Web      | API REST                 |
| Spring Data JPA | Persistencia             |
| Hibernate       | ORM                      |
| PostgreSQL 17   | Base de datos            |
| Maven           | Gestión del proyecto     |
| Docker          | Contenedor de PostgreSQL |
| Git             | Control de versiones     |

---

## 📁 Estructura del proyecto

```text
JobHunter/
│
├── Backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/example/jobhunter/
│   │   │   │       ├── Controller/
│   │   │   │       ├── DTO/
│   │   │   │       ├── ExceptionError/
│   │   │   │       ├── Filter/
│   │   │   │       ├── Matcher/
│   │   │   │       ├── Model/
│   │   │   │       ├── Repository/
│   │   │   │       ├── ScraperService/
│   │   │   │       └── Service/
│   │   │   │
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   │
│   │   └── test/
│   │
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── docker-compose.yml
├── .env.example
├── .gitignore
└── README.md
```

---

## 🔌 API

### Jobs

| Método   | Endpoint                 | Descripción                               |
| -------- | ------------------------ | ----------------------------------------- |
| `GET`    | `/api/jobs`              | Obtener todas las ofertas                 |
| `GET`    | `/api/jobs/{id}`         | Obtener una oferta                        |
| `POST`   | `/api/jobs`              | Crear una oferta                          |
| `PUT`    | `/api/jobs/{id}`         | Actualizar una oferta                     |
| `DELETE` | `/api/jobs/{id}`         | Eliminar una oferta                       |
| `POST`   | `/api/jobs/import`       | Importar y normalizar una oferta          |
| `POST`   | `/api/jobs/search`       | Buscar ofertas                            |
| `POST`   | `/api/jobs/search/jobs`  | Buscar y normalizar ofertas               |
| `POST`   | `/api/jobs/matches/{id}` | Obtener ofertas compatibles con un perfil |

### Profile

| Método   | Endpoint            | Descripción          |
| -------- | ------------------- | -------------------- |
| `GET`    | `/api/profile`      | Obtener perfiles     |
| `GET`    | `/api/profile/{id}` | Obtener un perfil    |
| `POST`   | `/api/profile`      | Crear un perfil      |
| `PUT`    | `/api/profile/{id}` | Actualizar un perfil |
| `DELETE` | `/api/profile/{id}` | Eliminar un perfil   |

---

## 🔄 Flujo de búsqueda y normalización

Una de las partes principales de JobHunter es transformar la información obtenida desde una fuente externa en una estructura común.

```text
Fuente externa
      ↓
Scraper
      ↓
RawJob
      ↓
JobNormalizer
      ↓
Job
      ↓
Repository
      ↓
PostgreSQL
```

Esto permite que diferentes fuentes puedan proporcionar información con estructuras distintas y que el sistema trabaje internamente con un único modelo `Job`.

---

## 🎯 Flujo de Matching

El proceso de comparación funciona de la siguiente manera:

```text
Profile
   │
   │
   ├── Skills ──────────── 50%
   │
   ├── Education ───────── 20%
   │
   └── Experience ──────── 30%
             │
             ↓
        JobMatcher
             │
             ↓
       Match Score
             │
             ↓
        JobFilter
             │
             ↓
      JobMatchDTO
```

Las ofertas resultantes se ordenan de mayor a menor porcentaje de coincidencia.

---

## 🐘 Base de datos

JobHunter utiliza PostgreSQL como sistema de gestión de base de datos.

La base de datos se ejecuta mediante Docker Compose.

### Configuración

La configuración utiliza variables de entorno para evitar almacenar credenciales directamente en el código.

Ejemplo de `.env.example`:

```env
POSTGRES_PASSWORD=change_me
```

El archivo `.env` real **no debe subirse al repositorio**.

---

## ▶️ Ejecución local

### Requisitos

Antes de ejecutar el proyecto necesitas tener instalado:

* Java 21
* Docker
* Git
* Maven (opcional, ya que el proyecto incluye Maven Wrapper)

### 1. Clonar el repositorio

```bash
git clone https://github.com/NicolasD1440/JobHunter.git
cd JobHunter
```

### 2. Configurar las variables de entorno

Crear un archivo `.env` en la raíz del proyecto:

```env
POSTGRES_PASSWORD=tu_password
```

### 3. Iniciar PostgreSQL

Desde la raíz:

```bash
docker compose up -d
```

Esto iniciará el contenedor de PostgreSQL utilizado por JobHunter.

### 4. Configurar la aplicación

La aplicación utiliza la variable de entorno:

```text
POSTGRES_PASSWORD
```

para conectarse a PostgreSQL.

### 5. Ejecutar el backend

Entrar al directorio:

```bash
cd Backend
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

En Linux/macOS:

```bash
./mvnw spring-boot:run
```

---

## 🧪 Pruebas

El proyecto incluye pruebas automatizadas mediante Spring Boot Test.

También se realizaron pruebas manuales de los principales flujos de la API, incluyendo:

* CRUD de ofertas.
* CRUD de perfiles.
* Validaciones.
* Manejo de errores.
* Búsqueda de ofertas.
* Normalización.
* Persistencia en PostgreSQL.
* Matching.
* Filtrado.

---

## 📌 Estado del proyecto

### V1.0 — Completada

La versión 1.0 establece la base funcional del backend de JobHunter.

Incluye:

* API REST.
* CRUD de ofertas.
* CRUD de perfiles.
* Persistencia con PostgreSQL.
* Docker Compose.
* Búsqueda de ofertas.
* Scraping.
* Normalización.
* Matching.
* Filtrado.
* Validación de datos.
* Manejo global de excepciones.

---

## 🗺️ Roadmap

Las siguientes funcionalidades pueden incorporarse en versiones posteriores:

### V1.1

* Mejorar la normalización de ubicaciones.
* Mejorar equivalencias entre habilidades.
* Mejorar la interpretación de formación académica.
* Mejorar el cálculo de experiencia.
* Reemplazar mensajes de depuración por logging donde corresponda.
* Ampliar las fuentes de ofertas.

### V2.0

* Desarrollo del frontend.
* Autenticación y usuarios.
* Interfaz para gestionar el perfil.
* Visualización de ofertas.
* Visualización del porcentaje de coincidencia.
* Filtros desde la interfaz.
* Automatización de búsquedas.
* Mayor integración con fuentes externas.

---

## 👨‍💻 Autor

**Nicolas D.**

Proyecto desarrollado como parte de mi proceso de aprendizaje y fortalecimiento de conocimientos en desarrollo backend con Java y Spring Boot.

---

## 📄 Licencia

Este proyecto actualmente no define una licencia de código abierto.
