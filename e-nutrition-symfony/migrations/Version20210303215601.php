<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210303215601 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE aliment_categorie_aliment DROP FOREIGN KEY FK_C52A8992415B9F11');
        $this->addSql('ALTER TABLE aliment_categorie_aliment DROP FOREIGN KEY FK_C52A8992DF9255BD');
        $this->addSql('DROP TABLE aliment');
        $this->addSql('DROP TABLE aliment_categorie_aliment');
        $this->addSql('DROP TABLE categorie_aliment');
        $this->addSql('DROP TABLE nourriture');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE aliment (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, lipides DOUBLE PRECISION NOT NULL, glucides DOUBLE PRECISION NOT NULL, proteines DOUBLE PRECISION NOT NULL, poid DOUBLE PRECISION NOT NULL, code_abarre VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE aliment_categorie_aliment (aliment_id INT NOT NULL, categorie_aliment_id INT NOT NULL, INDEX IDX_C52A8992415B9F11 (aliment_id), INDEX IDX_C52A8992DF9255BD (categorie_aliment_id), PRIMARY KEY(aliment_id, categorie_aliment_id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE categorie_aliment (id INT AUTO_INCREMENT NOT NULL, nom_categorie VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('CREATE TABLE nourriture (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, lipides DOUBLE PRECISION NOT NULL, glucides DOUBLE PRECISION NOT NULL, proteines DOUBLE PRECISION NOT NULL, poid DOUBLE PRECISION NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8 COLLATE `utf8_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE aliment_categorie_aliment ADD CONSTRAINT FK_C52A8992415B9F11 FOREIGN KEY (aliment_id) REFERENCES aliment (id) ON UPDATE NO ACTION ON DELETE CASCADE');
        $this->addSql('ALTER TABLE aliment_categorie_aliment ADD CONSTRAINT FK_C52A8992DF9255BD FOREIGN KEY (categorie_aliment_id) REFERENCES categorie_aliment (id) ON UPDATE NO ACTION ON DELETE CASCADE');
    }
}
