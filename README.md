## Nombre del Proyecto 
UserCreactionApp
## Descripción
Este proyecto es una aplicación que expone una API RESTful que permite la creación de usuarios y su almacenamiento en una base de datos en memoria. Los usuarios pueden registrarse proporcionando su nombre, correo electrónico, contraseña y teléfono de contacto. La API acepta y retorna JSON, así como proporcionar mensajes de error.

La API utiliza un banco de datos en memoria, HSQLDB, para almacenar los usuarios registrados. La persistencia se logra a través del framework Hibernate, que interactúa con el banco de datos. Se utiliza el framework Spring para la configuración y gestión de la aplicación, y el servidor Tomcat se utiliza para alojar la API.

Además de la funcionalidad básica de creación de usuarios, la API también implementa características adicionales como la generación de un token de acceso para la API, la validación del formato del correo electrónico y la contraseña, y la comprobación de duplicados de correo electrónico en la base de datos.

## Instalación y ejecución
1. Clona este repositorio en tu máquina local.
```
git clone https://github.com/misdelymorales/UserCreactionApp
```
2. Accede al directorio raíz del proyecto.
```
cd UserCreactionApp
```
3. Ejecuta el comando **`gradle build`** para compilar el proyecto.

4. Ejecuta el comando **`gradle bootRun`** para iniciar la aplicación.
5. La API estará disponible en http://localhost:8081.

>Nota: Si desea probar el endpoint usando Postman puede utilizar el siguiente [archivo]()

## Uso
La API expone el siguiente endpoint:

``` JSON
POST http://localhost:8081/users
Content-Type: application/json

{
  "name": "Misdely Morales",
  "email": "misdely@mail.com",
  "password": "Misdjhjsdh88",
  "phones": [
    {
      "number": "1234567",
      "cityCode": "1",
      "countryCode": "57"
    }
  ]
}

```
## Ejemplo de respuesta OK
``` JSON
{
    "id": 1,
    "name": "Misdely",
    "email": "misdely@email.com",
    "password": "Misdjhjsdh88",
    "phones": [
        {
            "id": 1,
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "56"
        }
    ],
    "created": "2023-05-12T12:25:18.8072653",
    "modified": "2023-05-12T12:25:18.8072653",
    "lastLogin": "2023-05-12T12:25:18.8072653",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaXNkZWx5QGVtYWlsLmNvbSIsIm5hbWUiOiJNaXNkZWx5IiwiaWF0IjoxNjgzOTA4NzE4LCJleHAiOjE2ODM5MTA1MTh9.gyXcKeCjy46I7r7S4aAFuDlSXvtd3NSakxwk0fKD0cCnziKVxBt-OnOD0x6LYS9ThNzziefPnkbN3GYp7Sf9QQ",
    "active": true
}
```

## Formato de respuesta Error
El endpoint de la API devuelve las respuestas en formato JSON. En caso de error, la respuesta contendrá un mensaje de error con el siguiente formato:

``` JSON
{ "mensaje": "La contraseña es obligatoria" }
{ "mensaje": "El correo debe tener un formato válido" }
{ "mensaje": "El correo ya está registrado" }
{ "mensaje": "El nombre es obligatorio"}
{ "mensaje": "El correo es obligatorio"}
...
```

## Pruebas Unitarias

## Diagrama de Solución

``` mermaid
graph 
A(Inicio)
B(Recibir datos del usuario)
C(Verificar existencia del correo en la base de datos)
D(Validar correo y contraseña)
E(Generar token)
F(Crear usuario en la base de datos)
G(Generar mensaje de respuesta)
H(Fin)

A --> B
B --> C
C --> |Válidos| D
C --> |Datos Inválidos| G
D --> |Correo existente| G
D --> |Correo no existente| E
E --> F
F --> G
G --> H
```

    NOTA: Si no puedes visualizar el diagrama, ingresa a: https://mermaid.live/
    y pega el código anterior en la pestaña Code.