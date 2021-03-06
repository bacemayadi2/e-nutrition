<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210306162114 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE blog_post CHANGE body body VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE medicament ADD fiche_id INT DEFAULT NULL, CHANGE quantite quantite VARCHAR(255) NOT NULL');
        $this->addSql('ALTER TABLE medicament ADD CONSTRAINT FK_9A9C723ADF522508 FOREIGN KEY (fiche_id) REFERENCES fiche_consultation (id)');
        $this->addSql('CREATE INDEX IDX_9A9C723ADF522508 ON medicament (fiche_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE blog_post CHANGE body body TEXT CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`');
        $this->addSql('ALTER TABLE medicament DROP FOREIGN KEY FK_9A9C723ADF522508');
        $this->addSql('DROP INDEX IDX_9A9C723ADF522508 ON medicament');
        $this->addSql('ALTER TABLE medicament DROP fiche_id, CHANGE quantite quantite DOUBLE PRECISION NOT NULL');
    }
}
