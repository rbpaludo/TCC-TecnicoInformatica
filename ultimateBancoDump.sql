CREATE DATABASE IF NOT EXISTS `tccbadminton` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `tccbadminton`;

CREATE TABLE IF NOT EXISTS `tb_pessoa` (
  `id_pessoa` int(11) NOT NULL AUTO_INCREMENT,
  `CPF_pessoa` double DEFAULT NULL,
  `AlunoTreinador` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email_pessoa` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `end_pessoa` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nasc_pessoa` date DEFAULT NULL,
  `nome_pessoa` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `senha_pessoa` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `usu_pessoa` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `tb_pessoa` (`id_pessoa`, `CPF_pessoa`, `AlunoTreinador`, `email_pessoa`, `end_pessoa`, `nasc_pessoa`, `nome_pessoa`, `senha_pessoa`, `usu_pessoa`) VALUES
	(7, 54288654, 'ROLE_TREINADOR', 'gustavodagnoni@gmail.com', 'Rua Das Flores', '0000-00-00', 'Gustavo Dagnoni', 'senhapadrao123', 'gustavodag'),
	(8, 23454323, 'ROLE_TREINADOR', 'chentazika@bol.com.br', 'Um cara Zikado', NULL, 'Chenta', 'gay', 'CH3NT4Z1K4'),
	(9, 23464323, 'ROLE_ALUNO', 'crackeiro@gmail.com', 'Crackolândia', NULL, 'Rafael Beffart', NULL, 'RafinhaGandula'),
	(10, 23464234, 'ROLE_ALUNO', 'jona@jonao.com', 'Rua das Flores', NULL, 'Jonas Filho', NULL, 'Joninhas'),
	(11, 2469666, 'ROLE_ALUNO', 'gustavodagnoni@gmail.com', 'Fortaleza, Brasil, Planeta Terra', NULL, 'Gustavo Dagnoni', NULL, 'Gusgus'),
	(12, 235464345, 'ROLE_ALUNO', 'josikwilkson@bol.com', 'Rua da Loucura', NULL, 'Josikwilkson da Silva', NULL, 'Josik'),
	(13, 234564352, 'ROLE_ALUNO', 'anaDahora@gmail.com', 'Longe pra caramba', NULL, 'Ana Carolina Kraemaer', NULL, 'Naninha'),
	(14, 123, 'ROLE_ALUNO', '213', 'sad', NULL, 'Robson FIlho', NULL, 'Robinho'),
	(15, 123, 'ROLE_ALUNO', '123', '123', NULL, 'Lariss Fraudão', NULL, 'Lari'),
	(16, 123, 'ROLE_ALUNO', '123', '123', NULL, 'Douglas Djalma', NULL, 'Djalma'),
	(17, 123, 'ROLE_ALUNO', '123', '123', NULL, 'Lucas Lemes', NULL, 'Lemes'),
	(18, 123, 'ROLE_ALUNO', '123', '123', NULL, 'Gabriel Salvador', NULL, 'Salvador'),
	(19, 123, 'ROLE_ALUNO', '123', '123', NULL, 'Gabriel Seiler', NULL, 'seiler'),
	(21, 123, 'ROLE_ALUNO', 'lol', 'lol', NULL, 'Lal', NULL, 'lol'),
	(22, 2312312, 'ROLE_ALUNO', 'heuehue', 'ZIkamenmo', NULL, 'Chenta Zika', NULL, 'gay');

CREATE TABLE IF NOT EXISTS `tb_treinador` (
  `id_treinador` int(11) NOT NULL AUTO_INCREMENT,
  `CREF_treinador` double DEFAULT NULL,
  `id_pessoa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_treinador`),
  KEY `FK_TB_TREINADOR_id_pessoa` (`id_pessoa`),
  CONSTRAINT `FK_TB_TREINADOR_id_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `tb_pessoa` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `tb_treinador` (`id_treinador`, `CREF_treinador`, `id_pessoa`) VALUES
	(6, 62568623, 7),
	(7, 6969696969, 8);

CREATE TABLE IF NOT EXISTS `tb_aluno` (
  `id_aluno` int(11) NOT NULL AUTO_INCREMENT,
  `id_treinador` int(11) DEFAULT NULL,
  `id_pessoa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_aluno`),
  KEY `FK_TB_ALUNO_id_pessoa` (`id_pessoa`),
  KEY `FK_TB_ALUNO_id_treinador` (`id_treinador`),
  CONSTRAINT `FK_TB_ALUNO_id_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `tb_pessoa` (`id_pessoa`),
  CONSTRAINT `FK_TB_ALUNO_id_treinador` FOREIGN KEY (`id_treinador`) REFERENCES `tb_treinador` (`id_treinador`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `tb_aluno` (`id_aluno`, `id_treinador`, `id_pessoa`) VALUES
	(2, 7, 9),
	(3, 7, 10),
	(4, 7, 11),
	(5, 7, 12),
	(6, 7, 13),
	(7, 7, 14),
	(8, 7, 15),
	(9, 7, 16),
	(10, 7, 17),
	(11, 7, 18),
	(12, 7, 19),
	(14, 7, 21),
	(15, 7, 22);

CREATE TABLE IF NOT EXISTS `tb_exercicio` (
  `id_exercicio` int(11) NOT NULL AUTO_INCREMENT,
  `NumCompartilhamentos` int(11) DEFAULT NULL,
  `adapt_exercicio` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `aon_exercicio` tinyint(1) DEFAULT '0',
  `desc_exercicio` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `funcao_exercicio` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nome_exercicio` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nMinAt_exercicio` int(11) DEFAULT NULL,
  `publico` tinyint(1) DEFAULT '0',
  `url_exercicio` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_treinador` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_exercicio`),
  KEY `FK_TB_EXERCICIO_id_treinador` (`id_treinador`),
  CONSTRAINT `FK_TB_EXERCICIO_id_treinador` FOREIGN KEY (`id_treinador`) REFERENCES `tb_treinador` (`id_treinador`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `tb_exercicio` (`id_exercicio`, `NumCompartilhamentos`, `adapt_exercicio`, `aon_exercicio`, `desc_exercicio`, `funcao_exercicio`, `nome_exercicio`, `nMinAt_exercicio`, `publico`, `url_exercicio`, `id_treinador`) VALUES
	(3, 2, 'Ter Braços Mecânicos', 1, 'Sacar sem ver de costas bem legal', 'Sacar sem Ver', 'Saque de Costas', 1, 1, NULL, 7),
	(5, 7, 'Não', 0, '123', 'Sacar pelo canto da quadra', 'Saque Lateral', 1, 1, NULL, 6),
	(6, 4, 'sad', 1, 'Ssad', 'Sacar muito Forte', 'Saque Forte', 1, 1, NULL, 6),
	(7, 7, '', 0, 'Olha bem dahora!', 'Nenhuma', 'Olhar', 2, 1, NULL, 7),
	(41, 0, 'Ter Braços Mecânicos', 1, 'Sacar sem ver de costas bem legal', 'Sacar sem Ver', 'Saque de Costas', 1, 0, NULL, 6),
	(42, 0, 'Não', 0, '123', 'Sacar pelo canto da quadra', 'Saque Lateral', 1, 0, NULL, 6),
	(43, 0, 'sad', 1, 'Ssad', 'Sacar muito Forte', 'Saque Forte', 1, 0, NULL, 6),
	(44, 0, 'Ter Braços Mecânicos', 1, 'Sacar sem ver de costas bem legal', 'Sacar sem Ver', 'Saque de Costas', 1, 0, NULL, 6);

CREATE TABLE IF NOT EXISTS `tb_fundamentos` (
  `id_fundamentos` int(11) NOT NULL AUTO_INCREMENT,
  `nome_fundamentos` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id_fundamentos`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `tb_fundamentos` (`id_fundamentos`, `nome_fundamentos`) VALUES
	(1, 'Smash'),
	(2, 'Drop'),
	(3, 'Clear'),
	(4, 'Clip'),
	(5, 'Curta'),
	(6, 'Lob'),
	(7, 'Net kill'),
	(8, 'Kill'),
	(9, 'Saque lateral'),
	(10, 'Saque frontal');

CREATE TABLE IF NOT EXISTS `exercicio_fundamentos` (
  `id_exercicio` int(11) NOT NULL,
  `id_fundamentos` int(11) NOT NULL,
  PRIMARY KEY (`id_exercicio`,`id_fundamentos`),
  KEY `FK_exercicio_fundamentos_id_fundamentos` (`id_fundamentos`),
  CONSTRAINT `FK_exercicio_fundamentos_id_exercicio` FOREIGN KEY (`id_exercicio`) REFERENCES `tb_exercicio` (`id_exercicio`),
  CONSTRAINT `FK_exercicio_fundamentos_id_fundamentos` FOREIGN KEY (`id_fundamentos`) REFERENCES `tb_fundamentos` (`id_fundamentos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `exercicio_fundamentos` (`id_exercicio`, `id_fundamentos`) VALUES
	(3, 1),
	(41, 1),
	(44, 1),
	(5, 3),
	(42, 3),
	(6, 9),
	(7, 9),
	(43, 9);

CREATE TABLE IF NOT EXISTS `tb_grupoalunos` (
  `id_grupo` int(11) NOT NULL AUTO_INCREMENT,
  `nome_grupoAlunos` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_treinador` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_grupo`),
  KEY `FK_TB_GRUPOALUNOS_id_treinador` (`id_treinador`),
  CONSTRAINT `FK_TB_GRUPOALUNOS_id_treinador` FOREIGN KEY (`id_treinador`) REFERENCES `tb_treinador` (`id_treinador`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `tb_grupoalunos` (`id_grupo`, `nome_grupoAlunos`, `id_treinador`) VALUES
	(2, 'Patotinha', 7),
	(3, 'Grupo Dos Dahora', 7),
	(4, 'Grupo Profissional Noite 2', 7),
	(5, 'Loucura', 7);

CREATE TABLE IF NOT EXISTS `grupo_aluno` (
  `id_aluno` int(11) NOT NULL,
  `id_grupo` int(11) NOT NULL,
  PRIMARY KEY (`id_aluno`,`id_grupo`),
  KEY `FK_grupo_aluno_id_grupo` (`id_grupo`),
  CONSTRAINT `FK_grupo_aluno_id_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `tb_aluno` (`id_aluno`),
  CONSTRAINT `FK_grupo_aluno_id_grupo` FOREIGN KEY (`id_grupo`) REFERENCES `tb_grupoalunos` (`id_grupo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `grupo_aluno` (`id_aluno`, `id_grupo`) VALUES
	(2, 2),
	(4, 2),
	(8, 2),
	(11, 2),
	(3, 3),
	(5, 3),
	(2, 4),
	(4, 4),
	(5, 4),
	(6, 4),
	(7, 4),
	(4, 5);

CREATE TABLE IF NOT EXISTS `tb_treino` (
  `id_treino` int(11) NOT NULL AUTO_INCREMENT,
  `data_treino` date DEFAULT NULL,
  `horario_treino` time DEFAULT NULL,
  `nome_treino` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id_treinador` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_treino`),
  KEY `FK_TB_TREINO_id_treinador` (`id_treinador`),
  CONSTRAINT `FK_TB_TREINO_id_treinador` FOREIGN KEY (`id_treinador`) REFERENCES `tb_treinador` (`id_treinador`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `tb_treino` (`id_treino`, `data_treino`, `horario_treino`, `nome_treino`, `id_treinador`) VALUES
	(3, '2014-12-10', '04:07:00', 'zika', 7),
	(5, '2014-12-24', '07:00:00', 'Treino dois', 7),
	(6, '2014-12-24', '12:35:00', 'Treino HardCore', 7),
	(7, '2014-12-24', '19:30:00', 'Treino da Noite 1', 7);

CREATE TABLE IF NOT EXISTS `grupo_treino` (
  `id_grupo` int(11) NOT NULL,
  `id_treino` int(11) NOT NULL,
  PRIMARY KEY (`id_grupo`,`id_treino`),
  KEY `FK_grupo_treino_id_treino` (`id_treino`),
  CONSTRAINT `FK_grupo_treino_id_grupo` FOREIGN KEY (`id_grupo`) REFERENCES `tb_grupoalunos` (`id_grupo`),
  CONSTRAINT `FK_grupo_treino_id_treino` FOREIGN KEY (`id_treino`) REFERENCES `tb_treino` (`id_treino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `grupo_treino` (`id_grupo`, `id_treino`) VALUES
	(2, 5),
	(4, 6),
	(2, 7);

CREATE TABLE IF NOT EXISTS `treino_exercicio` (
  `id_exercicio` int(11) NOT NULL,
  `id_treino` int(11) NOT NULL,
  PRIMARY KEY (`id_exercicio`,`id_treino`),
  KEY `FK_treino_exercicio_id_treino` (`id_treino`),
  CONSTRAINT `FK_treino_exercicio_id_exercicio` FOREIGN KEY (`id_exercicio`) REFERENCES `tb_exercicio` (`id_exercicio`),
  CONSTRAINT `FK_treino_exercicio_id_treino` FOREIGN KEY (`id_treino`) REFERENCES `tb_treino` (`id_treino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `treino_exercicio` (`id_exercicio`, `id_treino`) VALUES
	(3, 3),
	(7, 3),
	(3, 5),
	(7, 6),
	(3, 7);

CREATE TABLE IF NOT EXISTS `tb_avaliacao` (
  `id_avaliacao` int(11) NOT NULL AUTO_INCREMENT,
  `qAcertos_avaliacao` int(11) DEFAULT NULL,
  `qErros_avaliacao` int(11) DEFAULT NULL,
  `id_aluno` int(11) DEFAULT NULL,
  `id_exercicio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_avaliacao`),
  KEY `FK_TB_AVALIACAO_id_aluno` (`id_aluno`),
  KEY `FK_TB_AVALIACAO_id_exercicio` (`id_exercicio`),
  CONSTRAINT `FK_TB_AVALIACAO_id_aluno` FOREIGN KEY (`id_aluno`) REFERENCES `tb_aluno` (`id_aluno`),
  CONSTRAINT `FK_TB_AVALIACAO_id_exercicio` FOREIGN KEY (`id_exercicio`) REFERENCES `tb_exercicio` (`id_exercicio`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO `tb_avaliacao` (`id_avaliacao`, `qAcertos_avaliacao`, `qErros_avaliacao`, `id_aluno`, `id_exercicio`) VALUES
	(1, 0, 1, 5, 3),
	(2, 3, 1, 4, 3);