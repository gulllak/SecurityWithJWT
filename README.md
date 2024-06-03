### SecurityWithJWT - приложение демонстрирует работу регистрации и аутентификации с использованием Spring Security и JWT.

### REST API для регистрации и аутентификации пользователей:

Для простоты тестирования api для получения статистики, в корневой директории проекта находится файл **_Security.postman_collection.json_**, который можно импортировать в Postman.

1. Регистрация нового пользователя с ролями USER или ADMIN:

На эндпоинт по адресу:
```
/auth/register
```
Передаем Json в формате: 
```
{
    "username":"user",
    "password": "password",
    "role": "USER" или "ADMIN"
}
```
Получаем ответ в следующем формате:
```
   "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJhZG1pbl90ZXN0IiwiaWF0IjoxNzE3NDEyNDY1LCJleHAiOjE3MTc0MTYwNjV9.lhXfP5gkEmG4Ba7DH0lxLniAzYNijzO0EqEw8UgUdEE"
```
2. Аутентификация зарегестрированных пользователей:

На эндпоинт по адресу:
```
/auth/authenticate
```
Передаем Json в формате:
```
{
    "username":"user",
    "password": "password"
}
```
Получаем ответ в следующем формате:
```
   "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJzdWIiOiJhZG1pbl90ZXN0IiwiaWF0IjoxNzE3NDEyNDY1LCJleHAiOjE3MTc0MTYwNjV9.lhXfP5gkEmG4Ba7DH0lxLniAzYNijzO0EqEw8UgUdEE"
```

Если пользователь не аутентифицирован, получим ошибку 403. 

### Проверка доступа к закрытым эндпоинтам:
1. Доступ к эндпоинту GET имеют пользователи с ролями USER и ADMIN. При запросе необходимо передать JWT Token в разделе Authorization, тип Bearer Token. 
```
GET /items
```
Получаем ответ в следующем формате:
```
   Ответ из защищенного эндпоинта!
```
Если пользователь не аутентифицирован, получим ошибку 403.

2. Доступ к эндпоинту POST имеют пользователи с ролью ADMIN. При запросе необходимо передать JWT Token в разделе Authorization, тип Bearer Token.
```
POST /items
```
Передаем Json в формате:
```
{
    "name": "Spring",
    "price": 100.34
}
```
Получаем ответ в следующем формате:
```
{
  "id": 1,
  "name": "Spring",
  "price": 100.34
}
```
Если пользователь не аутентифицирован или не имеет прав доступа, получим ошибку 403.


## Как запустить
1. Склонировать проект.
    ```bash
   $ git clone https://github.com/gulllak/SecurityWithJWT.git
   ```
2. Создать БД и прописать значиния в application.properties
    ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/***
   spring.datasource.username=***
   spring.datasource.password=***
   ```
3. Настроить в application.properties
    ```
    jwt.secret_key=513e4d2e3d40377830653d35715d6b5623582e2a20263c6875707d6149 (секретный ключ) 
    jwt.expiration-time=60 (время жизни токена в минутах)
   ```
4. Запустить проект локально.
5. Подробная документация по эндпоинтам доступна после запуска проекта локально по адресу http://localhost:8080/swagger-ui.html

### 🏄 Стек:
Java 21, SpringBoot 3, Spring Security 6, PostgreSQL, Maven, SpringDoc OpenAPI.