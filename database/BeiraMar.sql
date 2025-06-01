drop database BeiraMar;
create database BeiraMar;

use BeiraMar;

create table cargo (
idCargo int primary key auto_increment,
nome varchar(255)
);

create table usuario (
idPessoa int primary key auto_increment,
nome varchar(100),
email varchar(80),
telefone varchar(45),
senha char(10),
dtNasc date,
fkCargo int,
constraint fkUsuarioCargo foreign key (fkCargo) references cargo (idCargo)
);

create table logSenha (
idLogSenha int auto_increment,
fkPessoa int,
token char(6),
dataLog datetime,
status varchar(45),
primary key (idLogSenha, fkPessoa),
constraint fkPessoaLog foreign key (fkPessoa) references usuario (idPessoa)
);

create table disponibilidade (
idDisponibilidade int auto_increment,
fkFuncionario int,
diaSemana varchar (45),
horaInicio time,
horaFim time,
diaMes varchar(45),
fkDisponibilidadeExcecao int,
primary key(idDisponibilidade, fkFuncionario),
constraint fkDispDisp foreign key (fkDisponibilidadeExcecao) references disponibilidade (idDisponibilidade)
);

create table pacote (
idPacote int primary key auto_increment,
nome varchar(100),
precoTotalSemDesconto decimal(10,2),
qtdSessoesTotal int,
tempoLimiteDias int
);

create table valorPacoteComDesconto (
fkUsuario int,
fkPacote int,
valorTotal decimal(10,2),
primary key (fkUsuario, fkPacote),
constraint fkValorUsuario foreign key (fkUsuario) references usuario (idPessoa),
constraint fkValorPacote foreign key (fkPacote) references pacote (idPacote)
);

create table servico (
idServico int primary key auto_increment,
nome varchar(100),
duracao int,
descricao varchar(255),
preco decimal(10,2)
);

create table sessoesPacote (
fkPacote int,
fkServico int,
qtsSessoes int,
primary key (fkPacote, fkServico),
constraint fkSessoesPacote foreign key (fkPacote) references pacote (idPacote),
constraint fkSessoesServico foreign key (fkServico) references servico (idservico)
);

create table agendamento (
idAgendamento int auto_increment,
fkServico int,
fkCliente int,
fkFuncionario int,
dtHora datetime,
valorPago decimal(10,2),
status enum('Agendado', 'Concluido', 'Cancelado'),
dtValidade date,
fkPacote int,
primary key(idAgendamento, fkServico, fkCliente, fkFuncionario),
constraint fkAgendamentoServico foreign key (fkServico) references servico (idServico),
constraint fkAgendamentoCliente foreign key (fkCliente) references usuario (idPessoa),
constraint fkAgendamentoFuncionario foreign key (fkFuncionario) references usuario (idPessoa),
constraint fkAgendamentoPacote foreign key (fkPacote) references pacote (idPacote)
);

create table logStatus (
fkAgendamento int primary key,
status enum('Agendado', 'Concluido', 'Cancelado'),
dtAlteracao datetime,
constraint fkLogAgendamento foreign key (fkAgendamento) references agendamento (idAgendamento)
);