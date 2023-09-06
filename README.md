## Principais Tecnologias

- Java 17
- PostgreSQL
- Spring v3.1.3
- JPA + Hibernate
- JUnit v5.9.1
- Lombok*
- Maven v3.1.0

## Configurações
- Certificar-se de que o Java e o Maven estejam instalados e configurados na sua máquina;
* *Algumas IDEs pedem que o lombok plugin seja instalado e habilitado antes de iniciar o projeto;
	- IntelliJ: https://projectlombok.org/setup/intellij
	- Eclipse (Spring Tool Suite): https://projectlombok.org/setup/eclipse

- Abra o projeto como projeto Mavem na sua IDE favorita;
- Certifique-se de que o banco esteja devidamente configurado com o nome da base, usuário e senha no arquivo ``backend/src/main/resources/application.properties``;
- O projeto está configurado para utilizar o perfil ``test`` caso não encontre a variável de ambiente ``APP_PROFILE``. Esse perfil utiliza como banco de dados o H2, que pode ser acessado através do endereço ``http://localhost:8080/h2-console``;
- Finalmente, tendo tudo devidamente configurado, abra o terminal e execute o seguinte comando:
```sh
mvn spring-boot:run
```

## Testes

- Com o projeto backend rodando, acessar através da URL ``http://localhost:8080/`` no seu navegador para certificar-se de que está executando.
- Utilize o endereço ``http://localhost:8080/swagger-ui/index.html`` para visualizar as URLs e seus devidos payloads, através do swagger.
