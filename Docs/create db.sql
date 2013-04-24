
create schema droid_hospital;


-- ---
-- Globals
-- ---

-- SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
-- SET FOREIGN_KEY_CHECKS=0;

-- ---
-- Table 'pessoas'
-- 
-- ---

DROP TABLE IF EXISTS `pessoas`;
		
CREATE TABLE `pessoas` (
  `idpessoa` INTEGER NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `endereco` VARCHAR(200) NULL DEFAULT NULL,
  `numero` VARCHAR(200) NULL DEFAULT NULL,
  `cidade` VARCHAR(50) NOT NULL,
  `estado` CHAR(2) NOT NULL,
  `pais` VARCHAR(20) NOT NULL,
  `telefone` VARCHAR(20) NULL DEFAULT NULL,
  `tipo_pessoa` CHAR(1) NULL DEFAULT NULL COMMENT 'M - Médico, E - Enfermeiro, P - Paciente',
  `especialidade` VARCHAR(40) NULL DEFAULT NULL,
  `data_nascimento` DATE NOT NULL,
  `tipo_sanguineo` CHAR(2) NOT NULL,
  `usuario` VARCHAR(200) NOT NULL,
  `senha` VARCHAR(32) NOT NULL,
  `alergias` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`idpessoa`)
);

-- ---
-- Table 'leitos'
-- 
-- ---

DROP TABLE IF EXISTS `leitos`;
		
CREATE TABLE `leitos` (
  `idleito` INTEGER NOT NULL AUTO_INCREMENT,
  `quarto` TINYINT(10) NOT NULL,
  `leito` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`idleito`)
);

-- ---
-- Table 'atendimentos'
-- 
-- ---

DROP TABLE IF EXISTS `atendimentos`;
		
CREATE TABLE `atendimentos` (
  `idatendimento` INTEGER NOT NULL AUTO_INCREMENT,
  `idleito` INTEGER NOT NULL,
  `idpaciente` INTEGER NOT NULL,
  `fuma` CHAR(1) NOT NULL DEFAULT 'N',
  `peso` DECIMAL NOT NULL DEFAULT 0,
  `data_entrada` DATETIME NOT NULL,
  `data_saida` DATETIME NOT NULL,
  PRIMARY KEY (`idatendimento`)
);

-- ---
-- Table 'prescricoes'
-- 
-- ---

DROP TABLE IF EXISTS `prescricoes`;
		
CREATE TABLE `prescricoes` (
  `idprescricao` INTEGER NOT NULL AUTO_INCREMENT,
  `idatendimento` INTEGER NOT NULL,
  `idmedicamento` INTEGER NOT NULL,
  `posologia` INTEGER NOT NULL DEFAULT 1,
  `intervalo_horas` INTEGER NOT NULL,
  `hora_inicio` TIME NULL DEFAULT NULL,
  `quantidade_aplicações` INTEGER NOT NULL DEFAULT 1,
  `idMedico` INTEGER NULL DEFAULT NULL,
  PRIMARY KEY (`idprescricao`)
);

-- ---
-- Table 'aplicacoes'
-- 
-- ---

DROP TABLE IF EXISTS `aplicacoes`;
		
CREATE TABLE `aplicacoes` (
  `idaplicacao` INTEGER NOT NULL AUTO_INCREMENT,
  `idEnfermeiro` INTEGER NULL DEFAULT NULL,
  `idprescricao` INTEGER NOT NULL,
  `hora_previsto` TIME NOT NULL,
  `hora_aplicado` TIME NOT NULL,
  PRIMARY KEY (`idaplicacao`)
);

-- ---
-- Table 'medicamentos'
-- Referência para a tabela: http://www.anvisa.gov.br/medicamentos/referencia/lmr_a.pdf
-- ---

DROP TABLE IF EXISTS `medicamentos`;
		
CREATE TABLE `medicamentos` (
  `idmedicamento` INTEGER NOT NULL AUTO_INCREMENT,
  `farmaco` VARCHAR(200) NULL DEFAULT NULL,
  `detentor` VARCHAR(200) NULL DEFAULT NULL,
  `medicamento_referencia` VARCHAR(150) NULL DEFAULT NULL,
  `concentracao` VARCHAR(15) NULL DEFAULT NULL,
  `forma_farmaceutica` CHAR(2) NOT NULL,
  PRIMARY KEY (`idmedicamento`)
) COMMENT 'Referência para a tabela: http://www.anvisa.gov.br/medicamen';

-- ---
-- Foreign Keys 
-- ---

ALTER TABLE `atendimentos` ADD FOREIGN KEY (idleito) REFERENCES `leitos` (`idleito`);
ALTER TABLE `atendimentos` ADD FOREIGN KEY (idpaciente) REFERENCES `pessoas` (`idpessoa`);
ALTER TABLE `prescricoes` ADD FOREIGN KEY (idatendimento) REFERENCES `atendimentos` (`idatendimento`);
ALTER TABLE `prescricoes` ADD FOREIGN KEY (idmedicamento) REFERENCES `medicamentos` (`idmedicamento`);
ALTER TABLE `prescricoes` ADD FOREIGN KEY (idMedico) REFERENCES `pessoas` (`idpessoa`);
ALTER TABLE `aplicacoes` ADD FOREIGN KEY (idEnfermeiro) REFERENCES `pessoas` (`idpessoa`);
ALTER TABLE `aplicacoes` ADD FOREIGN KEY (idprescricao) REFERENCES `prescricoes` (`idprescricao`);

-- ---
-- Table Properties
-- ---

-- ALTER TABLE `pessoas` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `leitos` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `atendimentos` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `prescricoes` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `aplicacoes` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
-- ALTER TABLE `medicamentos` ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ---
-- Test Data
-- ---

-- INSERT INTO `pessoas` (`idpessoa`,`nome`,`cpf`,`endereco`,`numero`,`cidade`,`estado`,`pais`,`telefone`,`tipo_pessoa`,`especialidade`,`data_nascimento`,`tipo_sanguineo`,`usuario`,`senha`,`alergias`) VALUES
-- ('','','','','','','','','','','','','','','','');
-- INSERT INTO `leitos` (`idleito`,`quarto`,`leito`) VALUES
-- ('','','');
-- INSERT INTO `atendimentos` (`idatendimento`,`idleito`,`idpaciente`,`fuma`,`peso`,`data_entrada`,`data_saida`) VALUES
-- ('','','','','','','');
-- INSERT INTO `prescricoes` (`idprescricao`,`idatendimento`,`idmedicamento`,`posologia`,`intervalo_horas`,`hora_inicio`,`quantidade_aplicações`,`idMedico`) VALUES
-- ('','','','','','','','');
-- INSERT INTO `aplicacoes` (`idaplicacao`,`idEnfermeiro`,`idprescricao`,`hora_previsto`,`hora_aplicado`) VALUES
-- ('','','','','');
-- INSERT INTO `medicamentos` (`idmedicamento`,`farmaco`,`detentor`,`medicamento_referencia`,`concentracao`,`forma_farmaceutica`) VALUES
-- ('','','','','','');

