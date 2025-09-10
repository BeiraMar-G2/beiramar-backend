drop database if exists BeiraMar ;
create database BeiraMar;

use BeiraMar;

create table cargo (
id_cargo int primary key auto_increment,
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
id_usuario int primary key auto_increment,
nome varchar(100),
email varchar(80),
telefone varchar(45),
senha varchar(300),
dt_nasc date,
fk_cargo int,
constraint fkUsuarioCargo foreign key (fk_cargo) references cargo (id_cargo)
);

INSERT INTO usuario (nome, email, telefone, senha, dt_nasc, fk_cargo) VALUES
('Ana Silva', 'ana.silva@beiramar.com', '11987654321', 'senha12345', '1985-03-15', 1), -- Administrador
('Bruno Costa', 'bruno.costa@beiramar.com', '11991234567', 'senha67890', '1990-07-22', 2), -- Recepcionista
('Carla Dias', 'carla.dias@beiramar.com', '11977889900', 'carla12345', '1988-11-01', 3), -- Massagista
('Daniel Rocha', 'daniel.rocha@beiramar.com', '11966554433', 'daniel6789', '1982-05-10', 4), -- Fisioterapeuta
('Eduarda Souza', 'eduarda.s@beiramar.com', '11955443322', 'eduarda123', '1995-09-20', 5), -- Esteticista
('Fernanda Lima', 'fernanda.lima@example.com', '11944332211', 'fernanda98', '1970-01-05', 6); -- Cliente

create table logSenha (
id_logSenha int auto_increment,
fk_usuario int,
token char(6),
dataLog datetime,
status varchar(45),
primary key (id_logSenha, fk_usuario),
constraint fkUsuarioLog foreign key (fk_usuario) references usuario (id_usuario)
);

INSERT INTO logSenha (fk_usuario, token, dataLog, status) VALUES
(1, 'A1B2C3', '2024-05-01 10:30:00', 'Sucesso'),
(2, 'D4E5F6', '2024-05-02 11:00:00', 'Falha'),
(3, 'G7H8I9', '2024-05-03 09:15:00', 'Sucesso'),
(4, 'J0K1L2', '2024-05-04 14:00:00', 'Sucesso'),
(5, 'M3N4O5', '2024-05-05 16:45:00', 'Falha');

create table disponibilidade (
id_disponibilidade int auto_increment,
fk_funcionario int,
diaSemana varchar (45),
horaInicio time,
horaFim time,
diaMes varchar(45),
fk_disponibilidadeExcecao int,
primary key(id_disponibilidade, fk_funcionario),
constraint fkDispDisp foreign key (fk_disponibilidadeExcecao) references disponibilidade (id_disponibilidade)
);

INSERT INTO disponibilidade (fk_funcionario, diaSemana, horaInicio, horaFim, diaMes, fk_disponibilidadeExcecao) VALUES
(3, 'Segunda-feira', '09:00:00', '18:00:00', NULL, NULL), -- Carla (Massagista) - Segunda normal
(3, 'Terça-feira', '09:00:00', '18:00:00', NULL, NULL), -- Carla (Massagista) - Terça normal
(4, 'Quarta-feira', '10:00:00', '19:00:00', NULL, NULL), -- Daniel (Fisioterapeuta) - Quarta normal
(4, 'Quinta-feira', '10:00:00', '19:00:00', NULL, NULL), -- Daniel (Fisioterapeuta) - Quinta normal
(5, 'Sexta-feira', '08:00:00', '17:00:00', NULL, NULL), -- Eduarda (Esteticista) - Sexta normal
(5, 'Sábado', '09:00:00', '13:00:00', NULL, NULL), -- Eduarda (Esteticista) - Sábado normal
(3, 'Quinta-feira', '09:00:00', '12:00:00', '2025-06-05', NULL), -- Exceção para Carla em 05/06/2025 (meio período)
(4, 'Sexta-feira', '14:00:00', '18:00:00', '2025-06-06', NULL); -- Exceção para Daniel em 06/06/2025 (tarde)

create table pacote (
id_pacote int primary key auto_increment,
nome varchar(100),
preco_total_sem_desconto decimal(10,2),
qtd_sessoes_total int,
tempo_limite_dias int
);

INSERT INTO pacote (nome, preco_total_sem_desconto, qtd_sessoes_total, tempo_limite_dias) VALUES
('Pacote Relax Total', 500.00, 5, 60),
('Pacote Bem Estar', 350.00, 3, 45),
('Pacote Estética Premium', 700.00, 7, 90),
('Pacote Fitness Fisioterapia', 600.00, 6, 75);

create table valorPacoteComDesconto (
fk_usuario int,
fk_pacote int,
valorTotal decimal(10,2),
primary key (fk_usuario, fk_pacote),
constraint fkValorUsuario foreign key (fk_usuario) references usuario (id_usuario),
constraint fkValorPacote foreign key (fk_pacote) references pacote (id_pacote)
);

INSERT INTO valorPacoteComDesconto (fk_usuario, fk_pacote, valorTotal) VALUES
(3, 1, 450.00), -- Fernando com desconto no Pacote Relax Total
(4, 2, 300.00), -- Giovana com desconto no Pacote Bem Estar
(5, 3, 630.00), -- Heloisa com desconto no Pacote Estética Premium
(6, 1, 475.00); -- Igor com outro desconto no Pacote Relax Total

create table servico (
id_servico int primary key auto_increment,
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

create table sessoes_pacote (
id_sessoes_pacote int auto_increment,
fk_pacote int,
fk_servico int,
qtd_sessoes int,
primary key (id_sessoes_pacote, fk_pacote, fk_servico),
constraint fkSessoesPacote foreign key (fk_pacote) references pacote (id_pacote),
constraint fkSessoesServico foreign key (fk_servico) references servico (id_servico)
);

INSERT INTO sessoes_pacote (fk_pacote, fk_servico, qtd_sessoes) VALUES
(1, 1, 3), -- Pacote Relax Total inclui 3 Massagens Relaxantes
(1, 4, 2), -- Pacote Relax Total inclui 2 Drenagens Linfáticas
(2, 1, 1), -- Pacote Bem Estar inclui 1 Massagem Relaxante
(2, 2, 1), -- Pacote Bem Estar inclui 1 Limpeza de Pele Profunda
(3, 2, 3), -- Pacote Estética Premium inclui 3 Limpezas de Pele Profunda
(3, 5, 2), -- Pacote Estética Premium inclui 2 Peeling Facial
(4, 3, 6); -- Pacote Fitness Fisioterapia inclui 6 Sessões de Fisioterapia

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

INSERT INTO agendamento (fk_servico, fk_cliente, fk_funcionario, dt_hora, valor_pago, status_agendamento, status, dt_validade, fk_pacote) VALUES
(1, 3, 6, '2025-08-10 10:00:00', 120.00, 'AGENDADO', 'AGENDADO', NULL, NULL), -- Massagem Relaxante para Fernando com Carla
(2, 3, 6, '2025-08-11 14:30:00', 150.00, 'AGENDADO', 'AGENDADO', NULL, NULL), -- Limpeza de Pele para Giovana com Eduarda
(3, 5, 6, '2025-08-12 11:00:00', 100.00, 'CONCLUIDO', 'CONCLUIDO', NULL, NULL), -- Fisioterapia para Heloisa com Daniel (já concluído)
(1, 4, 6, '2025-07-13 16:00:00', 0.00, 'AGENDADO', 'AGENDADO', '2025-08-13', 1), -- Massagem do Pacote Relax Total para Igor com Carla (valor 0, pois vem do pacote)
(4, 5, 6, '2025-07-15 09:00:00', 0.00, 'AGENDADO', 'AGENDADO', '2025-08-15', 1), -- Drenagem do Pacote Relax Total para Fernando com Carla
(4, 4, 6, '2025-06-16 10:00:00', 180.00, 'CANCELADO', 'CANCELADO', NULL, NULL), -- Peeling para Giovana com Eduarda (cancelado)
(3, 5, 6, '2025-06-17 14:00:00', 0.00, 'AGENDADO', 'AGENDADO', '2025-09-17', 4); -- Fisioterapia do Pacote Fitness para Heloisa com Daniel

create table logStatus (
fk_agendamento int primary key,
status enum('Agendado', 'Concluido', 'Cancelado'),
dtAlteracao datetime,
constraint fkLogAgendamento foreign key (fk_agendamento) references agendamento (id_agendamento)
);

INSERT INTO logStatus (fk_agendamento, status, dtAlteracao) VALUES
(1, 'Agendado', '2025-06-01 10:00:00'),
(2, 'Agendado', '2025-06-01 10:15:00'),
(3, 'Concluido', '2025-06-12 12:00:00'),
(4, 'Agendado', '2025-06-02 09:30:00'),
(5, 'Agendado', '2025-06-02 10:00:00'),
(6, 'Cancelado', '2025-06-05 11:00:00'),
(7, 'Agendado', '2025-06-02 11:30:00');