drop database BeiraMar;
create database BeiraMar;

use BeiraMar;

create table cargo (
idCargo int primary key auto_increment,
nome varchar(255)
);

INSERT INTO cargo (nome) VALUES
('Administrador'),
('Recepcionista'),
('Massagista'),
('Fisioterapeuta'),
('Esteticista'),
('Cliente');

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

INSERT INTO usuario (nome, email, telefone, senha, dtNasc, fkCargo) VALUES
('Ana Silva', 'ana.silva@beiramar.com', '11987654321', 'senha12345', '1985-03-15', 1), -- Administrador
('Bruno Costa', 'bruno.costa@beiramar.com', '11991234567', 'senha67890', '1990-07-22', 2), -- Recepcionista
('Carla Dias', 'carla.dias@beiramar.com', '11977889900', 'carla12345', '1988-11-01', 3), -- Massagista
('Daniel Rocha', 'daniel.rocha@beiramar.com', '11966554433', 'daniel6789', '1982-05-10', 4), -- Fisioterapeuta
('Eduarda Souza', 'eduarda.s@beiramar.com', '11955443322', 'eduarda123', '1995-09-20', 5), -- Esteticista
('Fernando Lima', 'fernando.lima@example.com', '11944332211', 'fernando98', '1970-01-05', 6), -- Cliente
('Giovana Martins', 'giovana.m@example.com', '11933221100', 'giovana123', '1992-04-30', 6), -- Cliente
('Heloisa Pereira', 'heloisa.p@example.com', '11922110099', 'heloisa456', '1980-08-12', 6), -- Cliente
('Igor Almeida', 'igor.a@example.com', '11911009988', 'igor789012', '1998-02-28', 6); -- Cliente

create table logSenha (
idLogSenha int auto_increment,
fkPessoa int,
token char(6),
dataLog datetime,
status varchar(45),
primary key (idLogSenha, fkPessoa),
constraint fkPessoaLog foreign key (fkPessoa) references usuario (idPessoa)
);

INSERT INTO logSenha (fkPessoa, token, dataLog, status) VALUES
(1, 'A1B2C3', '2024-05-01 10:30:00', 'Sucesso'),
(2, 'D4E5F6', '2024-05-02 11:00:00', 'Falha'),
(3, 'G7H8I9', '2024-05-03 09:15:00', 'Sucesso'),
(4, 'J0K1L2', '2024-05-04 14:00:00', 'Sucesso'),
(5, 'M3N4O5', '2024-05-05 16:45:00', 'Falha');

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

INSERT INTO disponibilidade (fkFuncionario, diaSemana, horaInicio, horaFim, diaMes, fkDisponibilidadeExcecao) VALUES
(3, 'Segunda-feira', '09:00:00', '18:00:00', NULL, NULL), -- Carla (Massagista) - Segunda normal
(3, 'Terça-feira', '09:00:00', '18:00:00', NULL, NULL), -- Carla (Massagista) - Terça normal
(4, 'Quarta-feira', '10:00:00', '19:00:00', NULL, NULL), -- Daniel (Fisioterapeuta) - Quarta normal
(4, 'Quinta-feira', '10:00:00', '19:00:00', NULL, NULL), -- Daniel (Fisioterapeuta) - Quinta normal
(5, 'Sexta-feira', '08:00:00', '17:00:00', NULL, NULL), -- Eduarda (Esteticista) - Sexta normal
(5, 'Sábado', '09:00:00', '13:00:00', NULL, NULL), -- Eduarda (Esteticista) - Sábado normal
(3, 'Quinta-feira', '09:00:00', '12:00:00', '2025-06-05', NULL), -- Exceção para Carla em 05/06/2025 (meio período)
(4, 'Sexta-feira', '14:00:00', '18:00:00', '2025-06-06', NULL); -- Exceção para Daniel em 06/06/2025 (tarde)

create table pacote (
idPacote int primary key auto_increment,
nome varchar(100),
precoTotalSemDesconto decimal(10,2),
qtdSessoesTotal int,
tempoLimiteDias int
);

INSERT INTO pacote (nome, precoTotalSemDesconto, qtdSessoesTotal, tempoLimiteDias) VALUES
('Pacote Relax Total', 500.00, 5, 60),
('Pacote Bem Estar', 350.00, 3, 45),
('Pacote Estética Premium', 700.00, 7, 90),
('Pacote Fitness Fisioterapia', 600.00, 6, 75);

create table valorPacoteComDesconto (
fkUsuario int,
fkPacote int,
valorTotal decimal(10,2),
primary key (fkUsuario, fkPacote),
constraint fkValorUsuario foreign key (fkUsuario) references usuario (idPessoa),
constraint fkValorPacote foreign key (fkPacote) references pacote (idPacote)
);

INSERT INTO valorPacoteComDesconto (fkUsuario, fkPacote, valorTotal) VALUES
(6, 1, 450.00), -- Fernando com desconto no Pacote Relax Total
(7, 2, 300.00), -- Giovana com desconto no Pacote Bem Estar
(8, 3, 630.00), -- Heloisa com desconto no Pacote Estética Premium
(9, 1, 475.00); -- Igor com outro desconto no Pacote Relax Total

create table servico (
idServico int primary key auto_increment,
nome varchar(100),
duracao int,
descricao varchar(255),
preco decimal(10,2)
);

INSERT INTO servico (nome, duracao, descricao, preco) VALUES
('Massagem Relaxante', 60, 'Massagem corporal para alívio do estresse.', 120.00),
('Limpeza de Pele Profunda', 90, 'Remoção de impurezas e tratamento da pele.', 150.00),
('Sessão de Fisioterapia', 45, 'Tratamento individualizado para reabilitação.', 100.00),
('Drenagem Linfática', 75, 'Massagem para reduzir inchaços e melhorar circulação.', 130.00),
('Peeling Facial', 60, 'Renovação celular para uma pele mais jovem.', 180.00),
('Sessão de Acupuntura', 60, 'Técnica milenar para equilíbrio energético.', 110.00);

create table sessoesPacote (
fkPacote int,
fkServico int,
qtsSessoes int,
primary key (fkPacote, fkServico),
constraint fkSessoesPacote foreign key (fkPacote) references pacote (idPacote),
constraint fkSessoesServico foreign key (fkServico) references servico (idservico)
);

INSERT INTO sessoesPacote (fkPacote, fkServico, qtsSessoes) VALUES
(1, 1, 3), -- Pacote Relax Total inclui 3 Massagens Relaxantes
(1, 4, 2), -- Pacote Relax Total inclui 2 Drenagens Linfáticas
(2, 1, 1), -- Pacote Bem Estar inclui 1 Massagem Relaxante
(2, 2, 1), -- Pacote Bem Estar inclui 1 Limpeza de Pele Profunda
(2, 6, 1), -- Pacote Bem Estar inclui 1 Sessão de Acupuntura
(3, 2, 3), -- Pacote Estética Premium inclui 3 Limpezas de Pele Profunda
(3, 5, 2), -- Pacote Estética Premium inclui 2 Peeling Facial
(4, 3, 6); -- Pacote Fitness Fisioterapia inclui 6 Sessões de Fisioterapia

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

INSERT INTO agendamento (fkServico, fkCliente, fkFuncionario, dtHora, valorPago, status, dtValidade, fkPacote) VALUES
(1, 6, 3, '2025-06-10 10:00:00', 120.00, 'Agendado', NULL, NULL), -- Massagem Relaxante para Fernando com Carla
(2, 7, 5, '2025-06-11 14:30:00', 150.00, 'Agendado', NULL, NULL), -- Limpeza de Pele para Giovana com Eduarda
(3, 8, 4, '2025-06-12 11:00:00', 100.00, 'Concluido', NULL, NULL), -- Fisioterapia para Heloisa com Daniel (já concluído)
(1, 9, 3, '2025-06-13 16:00:00', 0.00, 'Agendado', '2025-08-13', 1), -- Massagem do Pacote Relax Total para Igor com Carla (valor 0, pois vem do pacote)
(4, 6, 3, '2025-06-15 09:00:00', 0.00, 'Agendado', '2025-08-15', 1), -- Drenagem do Pacote Relax Total para Fernando com Carla
(5, 7, 5, '2025-06-16 10:00:00', 180.00, 'Cancelado', NULL, NULL), -- Peeling para Giovana com Eduarda (cancelado)
(3, 8, 4, '2025-06-17 14:00:00', 0.00, 'Agendado', '2025-09-17', 4); -- Fisioterapia do Pacote Fitness para Heloisa com Daniel

create table logStatus (
fkAgendamento int primary key,
status enum('Agendado', 'Concluido', 'Cancelado'),
dtAlteracao datetime,
constraint fkLogAgendamento foreign key (fkAgendamento) references agendamento (idAgendamento)
);

INSERT INTO logStatus (fkAgendamento, status, dtAlteracao) VALUES
(1, 'Agendado', '2025-06-01 10:00:00'),
(2, 'Agendado', '2025-06-01 10:15:00'),
(3, 'Concluido', '2025-06-12 12:00:00'),
(4, 'Agendado', '2025-06-02 09:30:00'),
(5, 'Agendado', '2025-06-02 10:00:00'),
(6, 'Cancelado', '2025-06-05 11:00:00'),
(7, 'Agendado', '2025-06-02 11:30:00');