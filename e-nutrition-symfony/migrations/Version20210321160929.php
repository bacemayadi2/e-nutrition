<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210321160929 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE fiche_consultation ADD patient_id INT DEFAULT NULL, ADD nutritionniste_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE fiche_consultation ADD CONSTRAINT FK_CAD698936B899279 FOREIGN KEY (patient_id) REFERENCES patient (id)');
        $this->addSql('ALTER TABLE fiche_consultation ADD CONSTRAINT FK_CAD69893279DA68A FOREIGN KEY (nutritionniste_id) REFERENCES nutritionniste (id)');
        $this->addSql('CREATE INDEX IDX_CAD698936B899279 ON fiche_consultation (patient_id)');
        $this->addSql('CREATE INDEX IDX_CAD69893279DA68A ON fiche_consultation (nutritionniste_id)');
        $this->addSql('ALTER TABLE utilisateur ADD password VARCHAR(255) NOT NULL, ADD is_verified TINYINT(1) NOT NULL');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE fiche_consultation DROP FOREIGN KEY FK_CAD698936B899279');
        $this->addSql('ALTER TABLE fiche_consultation DROP FOREIGN KEY FK_CAD69893279DA68A');
        $this->addSql('DROP INDEX IDX_CAD698936B899279 ON fiche_consultation');
        $this->addSql('DROP INDEX IDX_CAD69893279DA68A ON fiche_consultation');
        $this->addSql('ALTER TABLE fiche_consultation DROP patient_id, DROP nutritionniste_id');
        $this->addSql('ALTER TABLE utilisateur DROP password, DROP is_verified');
    }
}
