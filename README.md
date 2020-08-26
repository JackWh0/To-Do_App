# To-Do App

### :memo: Script SQL utilizado:
```
create database todo;
use todo;

create table Item(
    id int auto_increment primary key,
    descricao varchar(400) not null,
    realizado boolean not null default false,
    ativo boolean not null default true,
    data date not null
);
``` 
## :notebook_with_decorative_cover: Observações
* O arquivo mysql-connector-java-8.0.19.jar deve ser configurado no build path do projeto.
* Deve-se editar o ConnectionFactory para adicionar o usuário e senha do seu banco.
