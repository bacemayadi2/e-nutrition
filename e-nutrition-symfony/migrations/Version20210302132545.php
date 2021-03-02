<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210302132545 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE aliment DROP FOREIGN KEY FK_70FF972BDF9255BD');
        $this->addSql('DROP TABLE aliment');
        $this->addSql('DROP TABLE categorie_aliment');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE aliment (id INT AUTO_INCREMENT NOT NULL, categorie_aliment_id INT NOT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, lipides DOUBLE PRECISION NOT NULL, glucides DOUBLE PRECISION NOT NULL, proteines DOUBLE PRECISION NOT NULL, poid DOUBLE PRECISION NOT NULL, code_abarre VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, INDEX IDX_70FF972BDF9255BD (categorie_aliment_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE categorie_aliment (id INT AUTO_INCREMENT NOT NULL, nom_categorie VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE aliment ADD CONSTRAINT FK_70FF972BDF9255BD FOREIGN KEY (categorie_aliment_id) REFERENCES categorie_aliment (id) ON UPDATE NO ACTION ON DELETE NO ACTION');
    }
}
