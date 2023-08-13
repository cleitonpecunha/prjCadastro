CREATE TABLE tb_usuario (
  id_usuario int primary key not null auto_increment,
  nome_usuario VARCHAR(100),
  login VARCHAR(20),
  senha VARCHAR(100),
  administrador boolean
);