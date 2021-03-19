<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210319095019 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE fiche_consultation ADD nutritionniste_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE fiche_consultation ADD CONSTRAINT FK_CAD69893279DA68A FOREIGN KEY (nutritionniste_id) REFERENCES nutritionniste (id)');
        $this->addSql('CREATE INDEX IDX_CAD69893279DA68A ON fiche_consultation (nutritionniste_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE fiche_consultation DROP FOREIGN KEY FK_CAD69893279DA68A');
        $this->addSql('DROP INDEX IDX_CAD69893279DA68A ON fiche_consultation');
        $this->addSql('ALTER TABLE fiche_consultation DROP nutritionniste_id');
    }
}
