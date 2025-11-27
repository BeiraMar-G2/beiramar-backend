drop database if exists BeiraMar ;
create database BeiraMar;

use BeiraMar;

create table cargo (
id_cargo int primary key auto_increment,
nome varchar(255)
);

create table files_entity (
id int primary key auto_increment,
content_type varchar(200),
created_at date,
original_name varchar(200),
size double,
stored_name varchar(200)
);

create table usuario (
id_usuario int primary key auto_increment,
foto_perfil_id int,
nome varchar(100),
email varchar(80),
telefone varchar(45),
senha varchar(300),
dt_nasc date,
fk_cargo int,
constraint fkUsuarioCargo foreign key (fk_cargo) references cargo (id_cargo),
constraint fkUsuarioFotoPerfil foreign key (foto_perfil_id) references files_entity(id)
);

create table logSenha (
id_logSenha int auto_increment,
fk_usuario int,
token char(6),
dataLog datetime,
status varchar(45),
primary key (id_logSenha, fk_usuario),
constraint fkUsuarioLog foreign key (fk_usuario) references usuario (id_usuario)
);

create table disponibilidade_entity (
id_disponibilidade int auto_increment,
fk_funcionario int,
dia_semana varchar (45),
hora_inicio time,
hora_fim time,
dia_mes varchar(45),
fk_disponibilidade_excecao int,
primary key(id_disponibilidade, fk_funcionario),
constraint fkDispDisp foreign key (fk_disponibilidade_excecao) references disponibilidade_entity (id_disponibilidade)
);

create table pacote (
id_pacote int primary key auto_increment,
nome varchar(100),
preco_total_sem_desconto decimal(10,2),
qtd_sessoes_total int,
tempo_limite_dias int
);

create table valorPacoteComDesconto (
fk_usuario int,
fk_pacote int,
valorTotal decimal(10,2),
primary key (fk_usuario, fk_pacote),
constraint fkValorUsuario foreign key (fk_usuario) references usuario (id_usuario),
constraint fkValorPacote foreign key (fk_pacote) references pacote (id_pacote)
);

create table servico (
id_servico int primary key auto_increment,
nome varchar(100),
duracao int,
descricao varchar(255),
preco decimal(10,2)
);

create table sessoes_pacote (
id_sessoes_pacote int auto_increment,
fk_pacote int,
fk_servico int,
qtd_sessoes int,
primary key (id_sessoes_pacote, fk_pacote, fk_servico),
constraint fkSessoesPacote foreign key (fk_pacote) references pacote (id_pacote),
constraint fkSessoesServico foreign key (fk_servico) references servico (id_servico)
);

create table agendamento (
id_agendamento int auto_increment,
fk_servico int,
fk_cliente int,
fk_funcionario int,
dt_hora datetime,
valor_pago decimal(10,2),
status_agendamento enum('Agendado', 'Concluido', 'Cancelado'),
status enum('Agendado', 'Concluido', 'Cancelado'),
dt_validade date,
fk_pacote int,
primary key(id_agendamento, fk_servico, fk_cliente, fk_funcionario),
constraint fkAgendamentoServico foreign key (fk_servico) references servico (id_servico),
constraint fkAgendamentoCliente foreign key (fk_cliente) references usuario (id_usuario),
constraint fkAgendamentoFuncionario foreign key (fk_funcionario) references usuario (id_usuario),
constraint fkAgendamentoPacote foreign key (fk_pacote) references pacote (id_pacote)
);

create table logStatus (
fk_agendamento int primary key,
status enum('Agendado', 'Concluido', 'Cancelado'),
dtAlteracao datetime,
constraint fkLogAgendamento foreign key (fk_agendamento) references agendamento (id_agendamento)
);
