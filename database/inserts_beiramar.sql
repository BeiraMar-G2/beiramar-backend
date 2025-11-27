-- Novos INSERTs (sem alterar cargos, usuários 1 e 6)
USE BeiraMar;

-- files_entity
INSERT INTO files_entity (content_type, created_at, original_name, size, stored_name) VALUES
('image/png','2025-06-01','carla_avatar.png',24567,'carla_20250601.png'),
('image/jpeg','2025-06-02','daniel_avatar.jpg',31200,'daniel_20250602.jpg'),
('image/png','2025-06-03','eduarda_avatar.png',27800,'eduarda_20250603.png'),
('image/jpeg','2025-06-04','bruno_avatar.jpg',30110,'bruno_20250604.jpg'),
('image/png','2025-06-05','fernanda_avatar.png',26500,'fernanda_20250605.png');

-- cargo
INSERT INTO cargo (nome) VALUES
('Administrador'),
('Recepcionista'),
('Massagista'),
('Fisioterapeuta'),
('Esteticista'),
('Cliente');

-- usuario
INSERT INTO usuario (foto_perfil_id, nome, email, telefone, senha, dt_nasc, fk_cargo) VALUES
(1,'Carla Dias','carla.dias@beiramar.com','11977889900','$2a$10$RWnGTZiqVGs9WqUXkbyps.78KPRu79gph1KQJL4sgWDHQKd/ETod.','1988-11-01',3),
(2,'Daniel Rocha','daniel.rocha@beiramar.com','11966554433','daniel6789','1982-05-10',4),
(3,'Eduarda Souza','eduarda.s@beiramar.com','11955443322','eduarda123','1995-09-20',5),
(4,'Bruno Costa','bruno.costa@beiramar.com','11991234567','senha67890','1990-07-22',2),
(5,'Fernanda Lima','fernanda@gmail.com','11944332211','$2a$10$RWnGTZiqVGs9WqUXkbyps.78KPRu79gph1KQJL4sgWDHQKd/ETod.','1970-01-05',6),
(NULL,'Mariana Prado','mariana.prado@beiramar.com','11970001122','mariana456','1993-10-12',3),
(NULL,'Thiago Martins','thiago.martins@beiramar.com','11975554433','thiago789','1987-02-27',4),
(NULL,'Julia Ferreira','julia.ferreira@beiramar.com','11981117766','julia234','1999-06-18',5),
(NULL,'Rafael Gomes','rafael.gomes@beiramar.com','11990002233','rafael345','1994-09-09',6),
(NULL,'Patricia Nunes','patricia.nunes@beiramar.com','11992223344','patricia567','1986-12-30',6);

-- logSenha
INSERT INTO logSenha (fk_usuario, token, dataLog, status) VALUES
(2,'Z1Y2X3','2025-06-10 09:15:00','Sucesso'),
(3,'Q4W5E6','2025-06-10 10:20:00','Falha'),
(4,'R7T8Y9','2025-06-11 08:05:00','Sucesso'),
(5,'U1I2O3','2025-06-11 14:45:00','Falha'),
(7,'P4A5S6','2025-06-12 11:30:00','Sucesso');

-- disponibilidade_entity (inclui recorrentes e exceções)
INSERT INTO disponibilidade_entity (fk_funcionario, dia_semana, hora_inicio, hora_fim, dia_mes, fk_disponibilidade_excecao) VALUES
(3,'Segunda-feira','09:00:00','18:00:00',NULL,NULL),
(3,'Terça-feira','09:00:00','18:00:00',NULL,NULL),
(4,'Quarta-feira','10:00:00','19:00:00',NULL,NULL),
(4,'Quinta-feira','10:00:00','19:00:00',NULL,NULL),
(5,'Sexta-feira','08:00:00','17:00:00',NULL,NULL),
(5,'Sábado','09:00:00','13:00:00',NULL,NULL),
(3,'Quinta-feira','09:00:00','12:00:00','2025-07-30',NULL),
(4,'Quarta-feira','14:00:00','18:00:00','2025-11-26',NULL),
(7,'Segunda-feira','08:00:00','16:00:00',NULL,NULL),
(8,'Terça-feira','11:00:00','20:00:00',NULL,NULL);

-- pacote
INSERT INTO pacote (nome, preco_total_sem_desconto, qtd_sessoes_total, tempo_limite_dias) VALUES
('Pacote Revitalização Corporal', 800.00, 6, 80),
('Pacote Foco Fisioterapia', 450.00, 4, 50),
('Pacote Estética Plus', 900.00, 8, 100);

-- valorPacoteComDesconto
INSERT INTO valorPacoteComDesconto (fk_usuario, fk_pacote, valorTotal) VALUES
(6,1,475.00),
(9,2,420.00),
(10,3,850.00),
(5,2,430.00),
(4,1,480.00);

-- servico
INSERT INTO servico (nome, duracao, descricao, preco) VALUES
('Massagem Relaxante',60,'Massagem corporal para alívio do estresse.',120.00),
('Limpeza de Pele Profunda',90,'Remoção de impurezas e tratamento da pele.',150.00),
('Sessão de Fisioterapia',60,'Tratamento individualizado para reabilitação.',100.00),
('Drenagem Linfática',90,'Redução de inchaços e melhora da circulação.',130.00),
('Peeling Facial',60,'Renovação celular para pele mais jovem.',180.00),
('Sessão de Acupuntura',60,'Equilíbrio energético com agulhas.',110.00),
('Massagem Desportiva',60,'Recuperação muscular pós treino.',140.00),
('Reflexologia Podal',30,'Estimulação de pontos reflexos nos pés.',90.00),
('Hidratação Facial Premium',60,'Tratamento profundo de hidratação.',160.00);

-- sessoes_pacote
INSERT INTO sessoes_pacote (fk_pacote, fk_servico, qtd_sessoes) VALUES
(1,1,3),
(1,4,3),
(2,3,4),
(3,2,4),
(3,5,4),
(3,9,2),
(2,7,1),
(1,6,2);

select * from usuario;

-- agendamento
INSERT INTO agendamento VALUES
-- Pacote Relax Total (id_pacote = 1) sessões (valor 0.00)
(NULL,1,6,3,'2025-06-10 09:00:00',0.00,'Concluido','Concluido','2025-08-09',1),
(NULL,4,6,3,'2025-06-10 10:30:00',0.00,'Concluido','Concluido','2025-08-09',1),
(NULL,1,6,3,'2025-06-17 09:00:00',0.00,'Concluido','Concluido','2025-08-16',1),
(NULL,4,6,3,'2025-06-24 10:30:00',0.00,'Concluido','Concluido','2025-08-23',1),
(NULL,1,6,3,'2025-07-01 09:00:00',0.00,'Cancelado','Cancelado','2025-08-30',1),

-- Pacote Foco Fisioterapia (id_pacote = 6) (duracao curta) cliente 10
(NULL,3,10,4,'2025-07-03 11:00:00',0.00,'Concluido','Concluido','2025-08-22',2),
(NULL,3,10,4,'2025-07-10 11:45:00',0.00,'Concluido','Concluido','2025-08-29',2),
(NULL,3,10,4,'2025-07-17 12:30:00',0.00,'Cancelado','Cancelado','2025-09-05',2),
(NULL,3,10,4,'2025-07-24 13:15:00',0.00,'Concluido','Concluido','2025-09-12',2),

-- Pacote Estética Plus (id_pacote = 7) cliente 11
(NULL,2,9,5,'2025-08-02 08:00:00',0.00,'Concluido','Concluido','2025-11-10',3),
(NULL,5,9,5,'2025-08-09 09:30:00',0.00,'Concluido','Concluido','2025-11-17',3),
(NULL,2,9,5,'2025-08-16 08:00:00',0.00,'Concluido','Concluido','2025-11-24',3),
(NULL,5,9,5,'2025-08-23 09:30:00',0.00,'Cancelado','Cancelado','2025-12-01',3),
(NULL,9,9,5,'2025-08-30 11:00:00',0.00,'Concluido','Concluido','2025-12-08',3),

-- Serviços avulsos (pagos)
(NULL,7,6,7,'2025-06-12 15:00:00',140.00,'Concluido','Concluido',NULL,NULL),
(NULL,8,10,7,'2025-06-19 15:50:00',90.00,'Concluido','Concluido',NULL,NULL),
(NULL,9,10,5,'2025-06-26 16:30:00',160.00,'Concluido','Concluido',NULL,NULL),
(NULL,2,6,5,'2025-07-05 08:15:00',150.00,'Concluido','Concluido',NULL,NULL),
(NULL,5,6,5,'2025-07-12 08:15:00',180.00,'Cancelado','Cancelado',NULL,NULL),
(NULL,6,10,4,'2025-07-19 14:00:00',110.00,'Concluido','Concluido',NULL,NULL),
(NULL,4,10,3,'2025-07-26 09:00:00',130.00,'Concluido','Concluido',NULL,NULL),
(NULL,1,10,3,'2025-08-02 09:00:00',120.00,'Concluido','Concluido',NULL,NULL),
(NULL,7,9,7,'2025-08-06 16:10:00',140.00,'Cancelado','Cancelado',NULL,NULL),
(NULL,8,9,8,'2025-08-13 17:40:00',90.00,'Concluido','Concluido',NULL,NULL),
(NULL,9,6,5,'2025-08-20 10:10:00',160.00,'Concluido','Concluido',NULL,NULL),
(NULL,2,10,5,'2025-08-27 08:20:00',150.00,'Concluido','Concluido',NULL,NULL),
(NULL,3,10,4,'2025-09-03 11:00:00',100.00,'Cancelado','Cancelado',NULL,NULL),
(NULL,4,6,3,'2025-09-10 09:00:00',130.00,'Concluido','Concluido',NULL,NULL),
(NULL,5,10,5,'2025-09-17 08:30:00',180.00,'Cancelado','Cancelado',NULL,NULL),
(NULL,6,9,4,'2025-09-24 14:00:00',110.00,'Concluido','Concluido',NULL,NULL),
(NULL,7,6,7,'2025-10-01 15:10:00',140.00,'Cancelado','Cancelado',NULL,NULL),
(NULL,8,10,8,'2025-10-08 16:50:00',90.00,'Concluido','Concluido',NULL,NULL),
(NULL,9,9,5,'2025-10-15 11:30:00',160.00,'Cancelado','Cancelado',NULL,NULL),
(NULL,1,10,3,'2025-10-22 09:00:00',120.00,'Cancelado','Cancelado',NULL,NULL),
(NULL,2,6,5,'2025-10-29 08:10:00',150.00,'Concluido','Concluido',NULL,NULL),
(NULL,3,9,4,'2025-11-05 11:45:00',100.00,'Cancelado','Cancelado',NULL,NULL),
(NULL,4,10,3,'2025-11-12 09:00:00',130.00,'Concluido','Concluido',NULL,NULL),
(NULL,5,6,5,'2025-11-19 08:40:00',180.00,'Concluido','Concluido',NULL,NULL),
(NULL,6,10,4,'2025-11-26 14:15:00',110.00,'Concluido','Concluido',NULL,NULL),
(NULL,7,9,7,'2025-12-03 15:20:00',140.00,'Cancelado','Cancelado',NULL,NULL),
(NULL,8,6,8,'2025-12-10 16:30:00',90.00,'Agendado','Agendado',NULL,NULL),
(NULL,9,10,5,'2025-12-17 10:50:00',160.00,'Agendado','Agendado',NULL,NULL),
(NULL,1,9,3,'2025-12-24 09:00:00',120.00,'Agendado','Agendado',NULL,NULL);

-- logStatus
INSERT INTO logStatus (fk_agendamento, status, dtAlteracao) VALUES
(1,'Agendado','2025-07-25 09:00:00'),
(2,'Concluido','2025-07-30 12:30:00'),
(3,'Agendado','2025-11-20 10:15:00'),
(4,'Agendado','2025-11-21 11:40:00'),
(5,'Agendado','2025-11-22 13:05:00'),
(6,'Agendado','2025-11-23 17:10:00'),
(7,'Cancelado','2025-07-30 14:30:00'),
(8,'Agendado','2025-11-24 08:55:00');