# Grupo 03
## *ProWay Events Manager*

### Como configurar a conexão com o banco:
Você pode configurar o mysql criando um usuário, ou alterando as propriedades do arquivo persistence.xml. Veja a seguir como fazer cada uma das configurações:  

Arquivo **persistence.xml** na pasta *src/main/resources/META-INF/*.  

#### Configurar Mysql (terminal):
Crie o usuário 'mysql' com senha 'mysql' com os seguintes comandos:
```sql
CREATE USER 'mysql'@'localhost' IDENTIFIED BY 'mysql';
GRANT ALL PRIVILEGES ON * . * TO 'mysql'@'localhost';
FLUSH PRIVILEGES;
```

Disconecte do usuário atual com o comando *exit* e logue com o usuário criado, abra o terminal novamente e insira os seguintes comandos:
```sql
CREATE DATABASE IF NOT EXISTS prowayeventsmanager_db;
USE prowayeventsmanager_db;
```

Esses comandos criam o banco 'prowayeventsmanager_db'. Agora compile e execute o projeto a partir do método main() da classe Main no pacote Application.  

#### Configurar o arquivo persistence.xml:
Você também pode configurar o arquivo percistence.xml com o seu usuário e senha do mysql alterando as seguintes propriedades:
```xml
<property name="javax.persistence.jdbc.user" value="" />
<property name="javax.persistence.jdbc.password" value="" />
```

Adicionando ao atributo *value* o usuário e senha que você já possui no MySql.  
