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

## Testes Manuais
### Populando o Banco de Dados com um Mock para testes manuais

#### Event Room

Importante que os IDs das salas de eventos comecem em 1. Pode ter N salas, sem problemas.
Mas os IDs devem ser numerados squencialmente iniciando em 1.

```sql
INSERT INTO tbEventRoom (idEventRoom, name, capacity) values (1, 'sala 1', 20);
INSERT INTO tbEventRoom (idEventRoom, name, capacity) values (2, 'sala 2', 10);

SELECT * FROM prowayeventsmanager_db.tbEventRoom;
```

#### Coffee Room

Importante que os IDs dos ambientes de café comecem em 1. Pode ter N ambientes, sem problemas.
Mas os IDs devem ser numerados squencialmente iniciando em 1.

```sql
INSERT INTO tbCoffeeRoom (idCoffeeRoom, name) values (1, 'Saguão 01');
INSERT INTO tbCoffeeRoom (idCoffeeRoom, name) values (2, 'Saguão 02');

SELECT * FROM prowayeventsmanager_db.tbCoffeeRoom;
```

#### Person
As pessoas devem ser cadastradas com o assento 0, pois ainda não têm um assento determinado.

```sql
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (1, 'Larine','Arleta', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (2, 'Ondrea', 'Michelle', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (3, 'Lotty', 'Franky', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (4, 'Gelya', 'Laetitia', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (5, 'Lindsay', 'Cammy', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (6, 'Lanni', 'Bert', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (7, 'Kirsten', 'Lilia', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (8, 'Helena', 'Maribelle', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (9, 'Dot', 'Audry', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (10, 'Delinda', 'Mignon', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (11, 'Ruth', 'Carolan', 0);
INSERT INTO tbPerson (idPerson, name, lastname, seat) values (12, 'Lula', 'Janna', 0);

SELECT * FROM prowayeventsmanager_db.tbPerson;
```

Após cadastrados duas salas de eventos, dois ambientes de café e 12 pessoas, pode-se executar
o programa e escolher a opção **7)Alocar pessoas as salas**

Para rodar este método uma segunda vez, é necessário antes, limpar as tabelas associativas.
```sql
DELETE FROM tbCoffeeRoomPerson WHERE idCoffeeRoomPerson > 0;
SELECT * FROM prowayeventsmanager_db.tbCoffeeRoomPerson;

DELETE FROM tbEventRoomPerson WHERE idEventRoomPerson > 0;
SELECT * FROM prowayeventsmanager_db.tbEventRoomPerson;
``` 

#### Validando os resultados
[Animação para ilustrar o algoritmo funcionando](https://docs.google.com/presentation/d/e/2PACX-1vSc5fS_vWU8Lneide2ZVfFdXYtpm1O-sUVa2I8lapEI7tDvs9NBDGZLwtYrhwvAI-U0jgh20tjs1scY/pub?start=true&loop=true&delayms=5000&slide=id.gc349b4edbe_0_221)

Opção **4)Consultar Pessoas**

1) Larine Arleta
2) Ondrea Michelle
3) Lotty Franky
4) Gelya Laetitia
5) Lindsay Cammy
6) Lanni Bert
7) Kirsten Lilia
8) Helena Maribelle
9) Dot Audry
10) Delinda Mignon
11) Ruth Carolan
12) Lula Janna

#### Pessoa 1 (Assento 1 sala 1 etapa 1 e 2)
    Etapa 1
    Sala de Evento: sala 1
    Sala de Café: Saguão 01
    
    Etapa 2
    Sala de Evento: sala 1
    Sala de Café: Saguão 01

#### Pessoa 2 (Assento 1 sala 2 etapa 1 e 2)
    Etapa 1
    Sala de Evento: sala 2
    Sala de Café: Saguão 02
    
    Etapa 2
    Sala de Evento: sala 2
    Sala de Café: Saguão 02

##### Pessoa 3 (Assento 2 sala 1 etapa 1 - sala 2 etapa 2)
    Etapa 1
    Sala de Evento: sala 1
    Sala de Café: Saguão 01
    
    Etapa 2
    Sala de Evento: sala 2
    Sala de Café: Saguão 02

##### Pessoa 4 (Assento 2 sala 2 etapa 1 - sala 1 etapa 2)
    Etapa 1
    Sala de Evento: sala 2
    Sala de Café: Saguão 02
    
    Etapa 2
    Sala de Evento: sala 1
    Sala de Café: Saguão 01

