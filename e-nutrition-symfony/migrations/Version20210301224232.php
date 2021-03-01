<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210301224232 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE aliment (id INT AUTO_INCREMENT NOT NULL, categorie_aliment_id INT NOT NULL, nom VARCHAR(255) NOT NULL, lipides DOUBLE PRECISION NOT NULL, glucides DOUBLE PRECISION NOT NULL, proteines DOUBLE PRECISION NOT NULL, poid DOUBLE PRECISION NOT NULL, code_abarre VARCHAR(255) DEFAULT NULL, INDEX IDX_70FF972BDF9255BD (categorie_aliment_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE categorie_aliment (id INT AUTO_INCREMENT NOT NULL, nom_categorie VARCHAR(255) NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE fiche_consultation (id INT AUTO_INCREMENT NOT NULL, creation_date DATE NOT NULL, poids DOUBLE PRECISION NOT NULL, taille DOUBLE PRECISION NOT NULL, imc DOUBLE PRECISION NOT NULL, symptome VARCHAR(255) DEFAULT NULL, apetit VARCHAR(255) NOT NULL, description VARCHAR(255) DEFAULT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE nourriture (id INT AUTO_INCREMENT NOT NULL, nom VARCHAR(255) NOT NULL, lipides DOUBLE PRECISION NOT NULL, glucides DOUBLE PRECISION NOT NULL, proteines DOUBLE PRECISION NOT NULL, poid DOUBLE PRECISION NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE aliment ADD CONSTRAINT FK_70FF972BDF9255BD FOREIGN KEY (categorie_aliment_id) REFERENCES categorie_aliment (id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE aliment DROP FOREIGN KEY FK_70FF972BDF9255BD');
        $this->addSql('DROP TABLE aliment');
        $this->addSql('DROP TABLE categorie_aliment');
        $this->addSql('DROP TABLE fiche_consultation');
        $this->addSql('DROP TABLE nourriture');
    }
}
